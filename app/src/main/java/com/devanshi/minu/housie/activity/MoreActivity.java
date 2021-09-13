package com.devanshi.minu.housie.activity;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import androidx.databinding.*;

import com.devanshi.minu.housie.*;
import com.devanshi.minu.housie.databinding.*;
import com.devanshi.minu.housie.utils.*;

public class MoreActivity extends BaseActivity implements View.OnClickListener {

    ActivityMoreBinding binding;
    Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more);
        preference = new Preference(getApplicationContext());
        setupUI(binding.rootView);

        binding.imgBackPress.setOnClickListener(this);
        binding.clHelpSupport.setOnClickListener(this);
        binding.clTerms.setOnClickListener(this);
        binding.clPrivacy.setOnClickListener(this);
        binding.clLegal.setOnClickListener(this);
        binding.clLogout.setOnClickListener(this);

    }

    Toast toast;
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_press:{
                onBackPressed();
                break;
            }
            case R.id.cl_help_support:{
                if (toast != null) toast.cancel();
                toast = Toast.makeText(getApplicationContext(),getString(R.string.str_help_amp_support),Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.cl_terms:{
                if (toast != null) toast.cancel();
                toast = Toast.makeText(getApplicationContext(),getString(R.string.str_terms_amp_conditions),Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.cl_privacy:{
                if (toast != null) toast.cancel();
                toast = Toast.makeText(getApplicationContext(),getString(R.string.str_privacy_policy),Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.cl_legal:{
                if (toast != null) toast.cancel();
                toast = Toast.makeText(getApplicationContext(),getString(R.string.str_legal),Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.cl_logout:{
                if (toast != null) toast.cancel();
                preference.clearAllPreferenceData();
                onBackPressed();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + v);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(getString(R.string.fragment_to_be_opened),getString(R.string.menu_menu));
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
