package com.devanshi.minu.housie.fragments.ui.menu;

import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.fragment.app.*;

import com.devanshi.minu.housie.*;
import com.devanshi.minu.housie.activity.*;
import com.devanshi.minu.housie.apis.*;
import com.devanshi.minu.housie.models.*;
import com.devanshi.minu.housie.utils.Utils;
import com.devanshi.minu.housie.utils.*;

import org.jetbrains.annotations.*;

import retrofit2.*;

public class MenuFragment extends Fragment {

    private Preference preference;
    private TextView user_name_detail, email, wallet_side_textview, txt_version;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        preference = new Preference(requireActivity());
        initUi(view);

        return view;
    }

    private void initUi(View view) {
        ImageView imgMore = view.findViewById(R.id.img_more);
        imgMore.setOnClickListener(v -> {
            Utils.showWebDialog(requireActivity(),ServerConfig.supportUrl);
        });

        ImageView img_htp = view.findViewById(R.id.img_htp);
        img_htp.setOnClickListener(v -> {
            Utils.showWebDialog(requireActivity(),ServerConfig.howToPlayUrl);
        });

        ImageView whatsapp = view.findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(ServerConfig.whatsappCupURL));
            startActivity(i);
        });

        ImageView imgSetting = view.findViewById(R.id.img_setting);
        imgSetting.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            requireActivity().finish();
        });

        ImageView imgEdit = view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), EditProfileActivity.class));
            requireActivity().finish();
        });

        user_name_detail = view.findViewById(R.id.user_name_detail);
        email = view.findViewById(R.id.user_email);

        wallet_side_textview = view.findViewById(R.id.wallet_side_textview);
        callGetProfileApi(preference.getUserName());

        txt_version = view.findViewById(R.id.txt_version);
        txt_version.setText(getString(R.string.version, BuildConfig.VERSION_NAME));
    }

    private void callGetProfileApi(String userName) {
        user_name_detail.setText(preference.getUserName());
        email.setText(preference.getUserEmail());
        callGetWalletDetails(String.valueOf(preference.getUserId()));

        /*Utils.showProgress(requireActivity());
        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<UserModel> call = apiInterface.getProfileDetailApi(userName);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                Utils.hideProgress();
                if (response.body()!=null){
                    Log.e(Utils.getTag(), "onResponse: "+response.body().toString());
                    UserModel userModel=response.body();
                    if (userModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        UserData userData =userModel.getData();
                        UserDetailsToPreference.setDataToPreference(getActivity(), userData);
                        user_name_detail.setText(userData.getName());
                        email.setText(userData.getEmail());
                        callGetWalletDetails(String.valueOf(userData.getId()));
                    } else {
                        Utils.showSnackBar(getView(), userModel.getMeta().getMessage(), true, getActivity());
                    }
                    Log.v("TAG_RESPONSE","SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(getView(),"Failed", true, getActivity());
                t.printStackTrace();
                Log.v("TAG_RESPONSE","FAIL \n"+t.getMessage());
            }
        });*/
    }

    private void callGetWalletDetails(String user_id) {
        Utils.showProgress(requireActivity());

        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<WalletModel> call = apiInterface.getWalletBalanceApi(user_id);

        call.enqueue(new Callback<WalletModel>() {
            @Override
            public void onResponse(@NotNull Call<WalletModel> call, @NotNull Response<WalletModel> response) {
                Utils.hideProgress();
                if (response.body()!=null){
                    Log.e(Utils.getTag(), "onResponse: "+response.body().toString());
                    WalletModel walletModel=response.body();
                    if (walletModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        WalletData walletData = walletModel.getData().get(0);
//                        wallet_side_textview.setText(Utils.formatDigitToUnitValues((long)Double.parseDouble(walletData.getBalance().trim())));
                        wallet_side_textview.setText(String.valueOf(walletData.getAmount()));
                    } else {
                        Utils.showSnackBar(getView(), walletModel.getMeta().getMessage(), true, getActivity());
                    }
                    Log.v("TAG_RESPONSE","SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<WalletModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(getView(),"Failed", true, getActivity());
                t.printStackTrace();
                Log.v("TAG_RESPONSE","FAIL \n"+t.getMessage());
            }
        });
    }
}
