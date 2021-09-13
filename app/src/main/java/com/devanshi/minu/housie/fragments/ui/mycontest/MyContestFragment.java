package com.devanshi.minu.housie.fragments.ui.mycontest;

import android.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.annotation.Nullable;
import androidx.core.content.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;

import com.devanshi.minu.housie.R;
import com.devanshi.minu.housie.adapters.*;
import com.devanshi.minu.housie.apis.*;
import com.devanshi.minu.housie.interfaces.*;
import com.devanshi.minu.housie.models.*;
import com.devanshi.minu.housie.utils.Utils;
import com.devanshi.minu.housie.utils.*;

import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

import retrofit2.*;
import retrofit2.Callback;


public class MyContestFragment extends Fragment implements UpcomingGameClickInterface{

    private ArrayList<ContestList> upcomingArrayList;
    private UpcomingGameAdapter upcomingGameAdapter;
    private String fileExtension;
    private Preference preference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_contest, container, false);
        preference = new Preference(requireActivity());

        RecyclerView upcoming_game_recyclerview = root.findViewById(R.id.upcoming_game_recyclerview);
        upcomingArrayList = new ArrayList<>();
        upcoming_game_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        upcomingGameAdapter = new UpcomingGameAdapter(upcomingArrayList, getActivity(), this);
        upcoming_game_recyclerview.setAdapter(upcomingGameAdapter);

        callGetListApi();
        return root;
    }

    private void callGetListApi() {
        Utils.showProgress(requireActivity());
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<ContestListModel> call = apiInterface.getContestListApi(preference.getUserId());

        call.enqueue(new Callback<ContestListModel>() {
            @Override
            public void onResponse(@NotNull Call<ContestListModel> call, @NotNull Response<ContestListModel> response) {
                ContestListModel contestListModel = response.body();
                Utils.hideProgress();
                assert contestListModel != null;
                Log.e(Utils.getTag(), "onResponse: " + contestListModel.toString());
                if (contestListModel.getMeta().getStatus().equalsIgnoreCase("success")){
                    upcomingArrayList = contestListModel.getData();
                    upcomingGameAdapter.refreshList(upcomingArrayList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ContestListModel> call, @NotNull Throwable t) {
                t.printStackTrace();
                Utils.hideProgress();
                Utils.showSnackBar(getView(),"Failed", true, getActivity());
            }
        });
    }

    @Override
    public void descriptionClick(String description) {
        Utils.showAlertDialogWithOneButton(requireActivity(), "Full Details", description, "Dismiss");
    }

    @Override
    public void whatsappClick(ContestList contestList) {
        Utils.showProgress(requireActivity());
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<ContestListModel> call = apiInterface.joinGroupApi(contestList.getId(), contestList.getPlayerId());

        call.enqueue(new Callback<ContestListModel>() {
            @Override
            public void onResponse(@NotNull Call<ContestListModel> call, @NotNull Response<ContestListModel> response) {
                ContestListModel contestListModel = response.body();
                Utils.hideProgress();
                assert contestListModel != null;
                Log.e(Utils.getTag(), "onResponse: " + contestListModel.toString());
                if (contestListModel.getMeta().getStatus().equalsIgnoreCase("success")){
                    upcomingArrayList = contestListModel.getData();
                    upcomingGameAdapter.refreshList(upcomingArrayList);
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(contestList.getWhatsappGroupUrl())));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ContestListModel> call, @NotNull Throwable t) {
                t.printStackTrace();
                Utils.hideProgress();
                Utils.showSnackBar(getView(),"Failed", true, getActivity());
            }
        });
    }

    @Override
    public void shareClick(String imageUrl) {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            requireActivity().registerReceiver(downloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            int extensionPosition = imageUrl.indexOf(".", imageUrl.length()-5)+1;
            String extension = imageUrl.substring(extensionPosition);
            File file = new File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share."+extension);
            if (file.exists()){
                boolean delete = file.delete();
                if (!delete) {
                    Utils.showSnackBar(getView(), "Please try again later...", true, requireActivity());
                    return;
                }
            }
            DownloadManager downloadManager = (DownloadManager) requireActivity().getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(imageUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            request.setDestinationInExternalFilesDir(requireActivity(), Environment.DIRECTORY_PICTURES, "share."+extension);
            assert downloadManager != null;
            downloadManager.enqueue(request);
            fileExtension = extension;
        } else {
            requestPermissions(
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},200);
        }
    }

    @Override
    public void downloadClick(String imageUrl) {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            DownloadManager downloadManager = (DownloadManager) requireActivity().getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(imageUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            String[] splits = imageUrl.split("/");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, splits[splits.length - 1]);
            assert downloadManager != null;
            downloadManager.enqueue(request);
        } else {
            requestPermissions(
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireActivity(), "Storage permission is required to download or share image", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private BroadcastReceiver downloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (fileExtension != null) {
                File imageFileToShare = new File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES),"share."+ fileExtension);
                Uri imageUri = FileProvider.getUriForFile(requireContext(), "com.devanshi.minu.housie.provider",
                        imageFileToShare);
                Intent sharingIntent = new Intent(Intent.ACTION_SEND).setDataAndType(imageUri, requireActivity().getContentResolver().getType(imageUri));
                sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                List<ResolveInfo> resolveInfoList = requireActivity().getPackageManager().queryIntentActivities(sharingIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resolveInfoList){
                    String packageName = resolveInfo.activityInfo.packageName;
                    requireActivity().grantUriPermission(packageName, imageUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                }
                sharingIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivityForResult(Intent.createChooser(sharingIntent,"Share using"), 201);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 201) {
            requireActivity().unregisterReceiver(downloadComplete);
            fileExtension = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
