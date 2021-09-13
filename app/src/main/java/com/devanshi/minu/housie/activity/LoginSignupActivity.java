package com.devanshi.minu.housie.activity;

import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import androidx.databinding.*;

import com.google.firebase.iid.*;
import com.devanshi.minu.housie.R;
import com.devanshi.minu.housie.apis.*;
import com.devanshi.minu.housie.databinding.*;
import com.devanshi.minu.housie.models.*;
import com.devanshi.minu.housie.utils.Utils;
import com.devanshi.minu.housie.utils.*;

import org.jetbrains.annotations.*;

import java.util.*;

import retrofit2.*;

public class LoginSignupActivity extends BaseActivity implements View.OnClickListener {

    ActivityLoginSignupBinding binding;
    Preference preference;
    String deviceToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new Preference(getApplicationContext());
        getDeviceToken();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_signup);
        setupUI(binding.rootView);
        adminId = new ArrayList<>();
        adminName = new ArrayList<>();
        adminName.add("Choose your admin");
        adminId.add("-1");
        getConnectedAdmins();

        binding.chkboxText.setOnClickListener(v -> {
            binding.chkboxRememberMe.setChecked(!binding.chkboxRememberMe.isChecked());
            Utils.hideSoftKeyBoard(LoginSignupActivity.this,v);
        });

        binding.imgLogin.setOnClickListener(v -> {
            if (binding.clLogin.getVisibility() != View.VISIBLE){
                binding.edtLoginUsername.setText("");
                binding.edtLoginPassword.setText("");
                binding.chkboxRememberMe.setChecked(false);
                binding.clLogin.setVisibility(View.VISIBLE);
                binding.clSignup.setVisibility(View.GONE);
                binding.imgLoginBg.setVisibility(View.GONE);
                binding.imgSignupBg.setVisibility(View.VISIBLE);
            }
        });

        binding.imgSignup.setOnClickListener(v -> {
            if (binding.clSignup.getVisibility() != View.VISIBLE){
                binding.name.setText("");
                binding.edtEmail.setText("");
                binding.edtReferral.setText("");
                binding.edtRegistrationPassword.setText("");
                binding.edtRegistrationCpassword.setText("");
                binding.clSignup.setVisibility(View.VISIBLE);
                binding.clLogin.setVisibility(View.GONE);
                binding.imgLoginBg.setVisibility(View.VISIBLE);
                binding.imgSignupBg.setVisibility(View.GONE);
            }
        });

        binding.connectedAdminId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedId = adminId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.txtForgotPassword.setOnClickListener(v -> startActivity(new Intent(LoginSignupActivity.this, ForgotPasswordActivity.class)));

        binding.btnLogin.setOnClickListener(v -> {
            String loginUsername = binding.edtLoginUsername.getText().toString();
            String loginPassword = binding.edtLoginPassword.getText().toString();
            if (loginUsername.isEmpty()) {
                Utils.showSnackBar(v, "Username can't be empty", true, LoginSignupActivity.this);
            } else if (loginPassword.isEmpty()) {
                Utils.showSnackBar(v, "Password can't be empty", true, LoginSignupActivity.this);
            } else {
                callLoginApi(loginUsername, loginPassword);
            }
        });

        binding.btnRequestOtp.setOnClickListener(v -> {
            String firstName = binding.name.getText().toString();
            String email = binding.edtEmail.getText().toString();
            String password = binding.edtRegistrationPassword.getText().toString();
            String confirmPassword = binding.edtRegistrationCpassword.getText().toString();
            String mobile = binding.edtMobile.getText().toString();
            String referral = binding.edtReferral.getText().toString();
            if (firstName.isEmpty()) {
                Utils.showSnackBar(v, "Name can't be empty", true, LoginSignupActivity.this);
            } else if (email.isEmpty()) {
                Utils.showSnackBar(v, "E-mail can't be empty", true, LoginSignupActivity.this);
            } else if (password.isEmpty()) {
                Utils.showSnackBar(v, "Password number can't be empty", true, LoginSignupActivity.this);
            } else if (confirmPassword.isEmpty()) {
                Utils.showSnackBar(v, "Confirm Password can't be empty", true, LoginSignupActivity.this);
            } else if (!password.equals(confirmPassword)) {
                Utils.showSnackBar(v, "Passwords don't match", true, LoginSignupActivity.this);
            } else if (mobile.isEmpty()) {
                Utils.showSnackBar(v, "Mobile can't be empty", true, LoginSignupActivity.this);
            } else if (selectedId.isEmpty() || selectedId.equalsIgnoreCase("-1")) {
                Utils.showSnackBar(v, "Connected Admin Id can't be empty", true, LoginSignupActivity.this);
            } else {
               callRegisterApi(firstName, email, password, mobile, selectedId, referral, deviceToken, "android");
            }
        });
    }

    ArrayList<String> adminName;
    ArrayList<String> adminId;
    String selectedId="";

    private void getConnectedAdmins() {
        Utils.showProgress(LoginSignupActivity.this);

        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<AdminResponseModel> call = apiInterface.getConnectedAdminsApi("");

        call.enqueue(new Callback<AdminResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<AdminResponseModel> call, @NotNull Response<AdminResponseModel> response) {
                Utils.hideProgress();
                if (response.body()!=null){
                    Log.e(Utils.getTag(), "onResponse: "+response.body().toString());
                    AdminResponseModel userModel=response.body();
                    if (userModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        ArrayList<AdminData> data = userModel.getData();
                        for (AdminData adminData : data){
                            adminName.add(adminData.getName());
                            adminId.add(String.valueOf(adminData.getId()));
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_adapter_custom_item, adminName);
                        adapter.setDropDownViewResource(R.layout.spinner_custom_item);
                        binding.connectedAdminId.setAdapter(adapter);
                        binding.connectedAdminId.setSelection(0);
                    } else {
                        Utils.showSnackBar(binding.rootView, userModel.getMeta().getMessage(), true, getApplicationContext());
                    }
                    Log.v("TAG_RESPONSE","SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<AdminResponseModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(binding.rootView,"Failed", true, getApplicationContext());
                t.printStackTrace();
                Log.v("TAG_RESPONSE","FAIL \n"+t.getMessage());
            }
        });
    }

    private void callLoginApi(String loginUsername, String loginPassword) {
        Utils.showProgress(LoginSignupActivity.this);

        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<UserModel> call = apiInterface.loginApi(loginUsername, loginPassword, "android", deviceToken);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                Utils.hideProgress();
                if (response.body()!=null){
                    Log.e(Utils.getTag(), "onResponse: "+response.body().toString());
                    UserModel userModel=response.body();
                    if (userModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        UserDetailsToPreference.setDataToPreference(getApplicationContext(), userModel.getData());
                        startActivity(new Intent(LoginSignupActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Utils.showSnackBar(binding.rootView, userModel.getMeta().getMessage(), true, getApplicationContext());
                    }
                    Log.v("TAG_RESPONSE","SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(binding.rootView,"Failed", true, getApplicationContext());
                t.printStackTrace();
                Log.v("TAG_RESPONSE","FAIL \n"+t.getMessage());
            }
        });
    }

    private void callRegisterApi(String name, String email, String password, String mobile_no, String connected_admin_id, String referral, String device_token, String device_type) {
        Utils.showProgress(LoginSignupActivity.this);

        APIInterface apiInterface= APIClient.getClient().create(APIInterface.class);
        Call<UserModel> call = apiInterface.registerApi(name, email, password, mobile_no, connected_admin_id, referral, device_token, device_type);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                Utils.hideProgress();
                if (response.body()!=null){
                    Log.e(Utils.getTag(), "onResponse: "+response.body().toString());
                    UserModel userModel=response.body();
                    if (userModel.getMeta().getStatus().equalsIgnoreCase("success")){
                        UserDetailsToPreference.setDataToPreference(getApplicationContext(), userModel.getData());
                        startActivity(new Intent(LoginSignupActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Utils.showSnackBar(binding.rootView, userModel.getMeta().getMessage(), true, getApplicationContext());
                    }
                    Log.v("TAG_RESPONSE","SUCCESS");
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                Utils.hideProgress();
                Utils.showSnackBar(binding.rootView,"Failed", true, getApplicationContext());
                t.printStackTrace();
                Log.v("TAG_RESPONSE","FAIL \n"+t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Utils.hideSoftKeyBoard(this,v);
    }

    private void getDeviceToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("TAG_TOKEN", "getInstanceId failed", task.getException());
                        return;
                    }
                    // Get new Instance ID token
                    String token = Objects.requireNonNull(task.getResult()).getToken();
                    Log.d("TAG_TOKEN", token);
                    preference.setDeviceToken(token);
                    deviceToken = token;
                });
    }
}
