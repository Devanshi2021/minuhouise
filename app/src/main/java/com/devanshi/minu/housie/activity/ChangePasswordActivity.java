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

import java.util.*;

import retrofit2.*;

public class ChangePasswordActivity extends BaseActivity {

    ActivityChangePasswordBinding binding;
    Toast toast;
    Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        preference = new Preference(this);
        setupUI(binding.rootView);

        initUI();
    }

    private void initUI() {
        binding.imgBackPress.setOnClickListener(v -> onBackPressed());

        binding.btnSubmit.setOnClickListener(v -> {
            String userName = preference.getUserId();
            String oldPassword = Objects.requireNonNull(binding.edtOldPassword.getText()).toString();
            String newPassword = Objects.requireNonNull(binding.edtNewPassword.getText()).toString();
            String confirmNewPassword = Objects.requireNonNull(binding.edtConfirmNewPassword.getText()).toString();
            if (oldPassword.isEmpty()) {
                Utils.showSnackBar(binding.rootView, "Old Password cannot be empty", true, getApplicationContext());
            } else if (newPassword.isEmpty()) {
                Utils.showSnackBar(binding.rootView, "New Password cannot be empty", true, getApplicationContext());
            } else if (confirmNewPassword.isEmpty()) {
                Utils.showSnackBar(binding.rootView, "Confirm New Password cannot be empty", true, getApplicationContext());
            } else if (!newPassword.equals(confirmNewPassword)) {
                Utils.showSnackBar(binding.rootView, "Passwords don't match", true, getApplicationContext());
            } else {
                callChangePasswordApi(userName, oldPassword, newPassword, confirmNewPassword);
            }
        });
    }

    private void callChangePasswordApi(String userName, String oldPassword, String newPassword, String confirmNewPassword) {
        Utils.showProgress(ChangePasswordActivity.this);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserModel> call = apiInterface.changePasswordApi(userName, oldPassword, newPassword, confirmNewPassword);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                Utils.hideProgress();
                if (response.body() != null) {
                    Log.e(Utils.getTag(), "onResponse: "+response.body().toString());
                    UserModel userModel = response.body();
                    if (userModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        if (toast != null) toast.cancel();
                        toast = Utils.customToast(ChangePasswordActivity.this, "Password changed successfully");
                        toast.show();
                        binding.edtOldPassword.setText("");
                        binding.edtNewPassword.setText("");
                        binding.edtConfirmNewPassword.setText("");
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