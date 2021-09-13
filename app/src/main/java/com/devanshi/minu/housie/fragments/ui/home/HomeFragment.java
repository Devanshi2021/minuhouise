package com.devanshi.minu.housie.fragments.ui.home;

import android.annotation.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.constraintlayout.widget.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;

import com.devanshi.minu.housie.R;
import com.devanshi.minu.housie.activity.*;
import com.devanshi.minu.housie.adapters.*;
import com.devanshi.minu.housie.apis.*;
import com.devanshi.minu.housie.customui.*;
import com.devanshi.minu.housie.interfaces.*;
import com.devanshi.minu.housie.models.*;
import com.devanshi.minu.housie.utils.Utils;
import com.devanshi.minu.housie.utils.*;

import org.jetbrains.annotations.*;

import java.util.*;

import retrofit2.*;

public class HomeFragment extends Fragment implements GameTicketClickInterface {

    private View rootView = null;
    private TextView wallet_side_textview, variation_cl, rules_cl;
    private ArrayList<GameData> gameArrayList;
    private GameListAdapter gameListAdapter;
    private Preference preference;
    private ImageView whatsapp;
    private ConstraintLayout how_to_play_cl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        preference = new Preference(requireActivity());
        initUI();
        return rootView;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initUI() {
        wallet_side_textview = rootView.findViewById(R.id.wallet_side_textview);
        whatsapp = rootView.findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(v->{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(ServerConfig.whatsappCupURL));
            startActivity(i);
        });
        how_to_play_cl = rootView.findViewById(R.id.how_to_play_cl);
        how_to_play_cl.setOnClickListener(v->Utils.showWebDialog(requireActivity(),ServerConfig.howToPlayUrl));
        variation_cl = rootView.findViewById(R.id.variation_cl);
        variation_cl.setOnClickListener(v->Utils.showWebDialog(requireActivity(),ServerConfig.variationUrl));
        rules_cl = rootView.findViewById(R.id.rules_cl);
        rules_cl.setOnClickListener(v->Utils.showWebDialog(requireActivity(),ServerConfig.rulesUrl));
        ImageView imgView_add_icon = rootView.findViewById(R.id.imgView_add_icon);
        RecyclerView gameListRecyclerView = rootView.findViewById(R.id.gameListRecyclerView);
        gameListRecyclerView.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                ((MainActivity) requireActivity()).clickLeft();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                ((MainActivity) requireActivity()).clickRight();
            }
        });
        gameArrayList = new ArrayList<>();


        gameListAdapter = new GameListAdapter(gameArrayList, HomeFragment.this, getActivity());
        gameListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gameListRecyclerView.setAdapter(gameListAdapter);
        callGetGameDetails();


        imgView_add_icon.setOnClickListener(v -> {
        });
    }

    private void callGetGameDetails() {
        Utils.showProgress(requireActivity());

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<GameListResponseModel> call = apiInterface.getDashboardApi(preference.getUserName());

        call.enqueue(new Callback<GameListResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<GameListResponseModel> call, @NotNull Response<GameListResponseModel> response) {
                Utils.hideProgress();
                if (response.body() != null) {
                    Log.e(Utils.getTag(), "onResponse: " + response.body().toString());
                    GameListResponseModel dashboardModel = response.body();
                    if (dashboardModel.getMeta().getStatus().equalsIgnoreCase("success")) {
                        gameArrayList = dashboardModel.getData();
                        gameListAdapter.refreshList(gameArrayList);
                        if (!preference.getUserName().isEmpty()) {
                            callGetWalletDetails(preference.getUserId());
                        }
                    } else {
                        Utils.showSnackBar(getView(), dashboardModel.getMeta().getMessage(), true, getActivity());
                    }
                    Log.v("TAG_RESPONSE", "SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<GameListResponseModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(getView(), "Failed", true, getActivity());
                t.printStackTrace();
                Log.v("TAG_RESPONSE", "FAIL \n" + t.getMessage());
            }
        });
    }

    @Override
    public void onDescriptionClick(String description) {
        Utils.showAlertDialogWithOneButton(requireActivity(), "Full Details", description, "Dismiss");
    }

    @Override
    public void onButtonClick(GameData gameData) {
        if (!preference.getUserName().isEmpty()) {
            Intent intent = new Intent(getContext(), BuyTicketActivity.class);
            intent.putExtra("gameId", gameData.getId());
            intent.putExtra("getSixColumnAmount",gameData.getSixColumnAmount());
            intent.putExtra("getTwelveColumnAmount",gameData.getTwelveColumnAmount());
            intent.putExtra("getEighteenColumnAmount",gameData.getEighteenColumnAmount());
            intent.putExtra("getTwentyfourColumnAmount",gameData.getTwentyfourColumnAmount());
            startActivity(intent);
        } else
            startActivity(new Intent(getContext(), LoginSignupActivity.class));
    }

    private void callGetWalletDetails(String user_id) {
        Utils.showProgress(requireActivity());

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<WalletModel> call = apiInterface.getWalletBalanceApi(user_id);

        call.enqueue(new Callback<WalletModel>() {
            @Override
            public void onResponse(@NotNull Call<WalletModel> call, @NotNull Response<WalletModel> response) {
                Utils.hideProgress();
                if (response.body() != null) {
                    Log.e(Utils.getTag(), "onResponse: " + response.body().toString());
                    WalletModel walletModel = response.body();
                    if (walletModel.getMeta().getStatus().equalsIgnoreCase("success")) {
                        WalletData walletData = walletModel.getData().get(0);
//                        wallet_side_textview.setText(Utils.formatDigitToUnitValues((long)Double.parseDouble(walletData.getBalance().trim())));
                        wallet_side_textview.setText(String.valueOf(walletData.getAmount()));
                    } else {
                        Utils.showSnackBar(getView(), walletModel.getMeta().getMessage(), true, getActivity());
                    }
                    Log.v("TAG_RESPONSE", "SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<WalletModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(getView(), "Failed", true, getActivity());
                t.printStackTrace();
                Log.v("TAG_RESPONSE", "FAIL \n" + t.getMessage());
            }
        });
    }
}
