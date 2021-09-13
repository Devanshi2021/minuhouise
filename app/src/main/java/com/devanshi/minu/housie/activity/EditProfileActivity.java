package com.devanshi.minu.housie.activity;

import android.content.*;
import android.os.*;
import android.util.*;
import android.widget.*;

import androidx.databinding.*;

import com.devanshi.minu.housie.*;
import com.devanshi.minu.housie.apis.*;
import com.devanshi.minu.housie.databinding.*;
import com.devanshi.minu.housie.models.*;
import com.devanshi.minu.housie.utils.Utils;
import com.devanshi.minu.housie.utils.*;

import org.jetbrains.annotations.*;

import retrofit2.*;

public class EditProfileActivity extends BaseActivity {

    ActivityEditProfileBinding binding;
    Toast toast;
    Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        preference = new Preference(this);
        setupUI(binding.rootView);

        initUI();
    }

    private void initUI() {
        binding.edtFullName.setText(preference.getUserName());
        binding.txtEmail.setText(preference.getUserEmail());
        binding.txtMobile.setText(preference.getMobile());
        binding.txtAdmin.setText(preference.getConnectedAdminName());
        binding.txtReferral.setText(preference.getReferralCode());

        binding.imgBackPress.setOnClickListener(v -> onBackPressed());

        binding.txtEmail.setOnClickListener(v -> Utils.showSnackBar(binding.rootView, "Cannot update E-mail", true, EditProfileActivity.this));
        binding.txtMobile.setOnClickListener(v -> Utils.showSnackBar(binding.rootView, "Cannot update Mobile Number", true, EditProfileActivity.this));
        binding.txtAdmin.setOnClickListener(v -> Utils.showSnackBar(binding.rootView, "Cannot update Admin", true, EditProfileActivity.this));
        binding.txtReferral.setOnClickListener(v -> Utils.showSnackBar(binding.rootView, "Cannot update Referral Code", true, EditProfileActivity.this));

        binding.btnSave.setOnClickListener(v -> {
            String firstName = binding.edtFullName.getText().toString();
            if (firstName.isEmpty()) {
                Utils.showSnackBar(binding.rootView, "Full name cannot be empty", true, getApplicationContext());
            }else {
                callChangeProfileApi(preference.getUserId(), firstName);
            }
        });
    }

    private void callChangeProfileApi(String userName, String firstName) {
        Utils.showProgress(EditProfileActivity.this);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserModel> call = apiInterface.updateProfileApi(userName, firstName);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                Utils.hideProgress();
                if (response.body() != null) {
                    Log.e(Utils.getTag(), "onResponse: "+response.body().toString());
                    UserModel userModel = response.body();
                    if (userModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        if (toast != null) toast.cancel();
                        UserDetailsToPreference.setDataToPreference(getApplicationContext(), userModel.getData());
                        toast = Utils.customToast(EditProfileActivity.this, "Profile updated successfully");
                        toast.show();
                    } else {
                        Utils.showSnackBar(binding.rootView, userModel.getMeta().getMessage(), true, getApplicationContext());
                    }
                    Log.v("TAG_RESPONSE", "SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(binding.rootView, "Failed", true, getApplicationContext());
                t.printStackTrace();
                Log.v("TAG_RESPONSE", "FAIL \n" + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(getString(R.string.fragment_to_be_opened),getString(R.string.menu_winners));
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}