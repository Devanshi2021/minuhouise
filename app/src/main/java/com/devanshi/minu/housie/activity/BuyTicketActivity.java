package com.devanshi.minu.housie.activity;

import android.content.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;

import androidx.appcompat.app.*;
import androidx.databinding.*;

import com.devanshi.minu.housie.*;
import com.devanshi.minu.housie.apis.*;
import com.devanshi.minu.housie.databinding.*;
import com.devanshi.minu.housie.models.*;
import com.devanshi.minu.housie.utils.Utils;
import com.devanshi.minu.housie.utils.*;

import org.jetbrains.annotations.*;

import retrofit2.*;

public class BuyTicketActivity extends AppCompatActivity {

    ActivityBuyTicketBinding binding;
    Preference preference;
    String column_type;
    Integer amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_buy_ticket);
        preference = new Preference(getApplicationContext());
        Integer gameId = getIntent().getIntExtra("gameId", 0);
        int sixColumnAmount = getIntent().getIntExtra("getSixColumnAmount", 0);
        int twelveColumnAmount = getIntent().getIntExtra("getTwelveColumnAmount", 0);
        int eighteenColumnAmount = getIntent().getIntExtra("getEighteenColumnAmount", 0);
        int twentyFourColumnAmount = getIntent().getIntExtra("getTwentyfourColumnAmount", 0);

        binding.sixTicketPrice.setText(String.valueOf(sixColumnAmount));
        binding.twelveTicketPrice.setText(String.valueOf(twelveColumnAmount));
        binding.eighteenTicketPrice.setText(String.valueOf(eighteenColumnAmount));
        binding.twentyfourTicketPrice.setText(String.valueOf(twentyFourColumnAmount));

        Drawable disabledDrawable = getDrawable(R.drawable.purple_rounded_rectangle);
        assert disabledDrawable != null;
        disabledDrawable.setAlpha(50);

        if (sixColumnAmount > 0) {
            binding.sixTicketDetailCl.setOnClickListener(v -> {
                binding.sixTicketDetailCl.setBackground(getDrawable(R.drawable.layer_list_selected_item_rectangle));
                if (twelveColumnAmount > 0) binding.twelveTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                if (eighteenColumnAmount > 0) binding.eighteenTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                if (twentyFourColumnAmount > 0) binding.twentyfourTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                amount = sixColumnAmount;
                column_type = "6";
            });
        } else {
            binding.sixTicketDetailCl.setBackground(disabledDrawable);
        }

        if (twelveColumnAmount > 0) {
            binding.twelveTicketDetailCl.setOnClickListener(v -> {
                if (sixColumnAmount > 0)
                    binding.sixTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                binding.twelveTicketDetailCl.setBackground(getDrawable(R.drawable.layer_list_selected_item_rectangle));
                if (eighteenColumnAmount > 0)
                    binding.eighteenTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                if (twentyFourColumnAmount > 0)
                    binding.twentyfourTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                amount = twelveColumnAmount;
                column_type = "12";
            });
        }  else {
            binding.twelveTicketDetailCl.setBackground(disabledDrawable);
        }

        if (eighteenColumnAmount > 0) {
            binding.eighteenTicketDetailCl.setOnClickListener(v -> {
                if (sixColumnAmount > 0)
                    binding.sixTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                if (twelveColumnAmount > 0)
                    binding.twelveTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                binding.eighteenTicketDetailCl.setBackground(getDrawable(R.drawable.layer_list_selected_item_rectangle));
                if (twentyFourColumnAmount > 0)
                    binding.twentyfourTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
                amount = eighteenColumnAmount;
                column_type = "18";
            });
        } else {
            binding.eighteenTicketDetailCl.setBackground(disabledDrawable);
        }

        if (twentyFourColumnAmount > 0) {
            binding.twentyfourTicketDetailCl.setOnClickListener(v->{
            if (sixColumnAmount > 0) binding.sixTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
            if (twelveColumnAmount > 0) binding.twelveTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
            if (eighteenColumnAmount > 0) binding.eighteenTicketDetailCl.setBackground(getDrawable(R.drawable.purple_rounded_rectangle));
            binding.twentyfourTicketDetailCl.setBackground(getDrawable(R.drawable.layer_list_selected_item_rectangle));
            amount = twentyFourColumnAmount;
            column_type = "24";
            });
        } else {
            binding.twentyfourTicketDetailCl.setBackground(disabledDrawable);
        }

        binding.imgBackPress.setOnClickListener(v -> onBackPressed());

        binding.btnAddCash.setOnClickListener(v-> callBuyTickets(gameId,preference.getUserId()));

        callGetWalletDetails(preference.getUserId());
    }

    private void callBuyTickets(Integer gameId, String user_id) {
        Utils.showProgress(BuyTicketActivity.this);

        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<BuyTicketResponseModel> call = apiInterface.getBuyTicketsApi(gameId, user_id, column_type, amount);

        call.enqueue(new Callback<BuyTicketResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<BuyTicketResponseModel> call, @NotNull Response<BuyTicketResponseModel> response) {
                Utils.hideProgress();
                if (response.body()!=null){
                    BuyTicketResponseModel userModel=response.body();
                    if (userModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        Utils.customToast(BuyTicketActivity.this, userModel.getMeta().getMessage()).show();
                        openMyBookings();
                    } else {
                        Utils.showSnackBar(binding.rootView, userModel.getMeta().getMessage(), true, getApplicationContext());
                    }
                    Log.v("TAG_RESPONSE","SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<BuyTicketResponseModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(binding.rootView,"Failed", true, getApplicationContext());
                t.printStackTrace();
                Log.v("TAG_RESPONSE","FAIL \n"+t.getMessage());
            }
        });
    }

    private void openMyBookings() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(getString(R.string.fragment_to_be_opened),getString(R.string.menu_my_contest));
        startActivity(intent);
        finish();
    }

    private void callGetWalletDetails(String user_id) {
        Utils.showProgress(BuyTicketActivity.this);

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
                        binding.walletSideTextview.setText(String.valueOf(walletData.getAmount()));
                    } else {
                        Utils.showSnackBar(binding.rootView, walletModel.getMeta().getMessage(), true, getApplicationContext());
                    }
                    Log.v("TAG_RESPONSE","SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<WalletModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(binding.rootView,"Failed", true, getApplicationContext());
                t.printStackTrace();
                Log.v("TAG_RESPONSE","FAIL \n"+t.getMessage());
            }
        });
    }
}
