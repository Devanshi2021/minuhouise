package com.devanshi.minu.housie.utils;

import android.content.*;

import com.devanshi.minu.housie.models.*;

public class UserDetailsToPreference {

    public static void setDataToPreference(Context context, UserData userData){
        Preference preference = new Preference(context);
        preference.setUserId(String.valueOf(userData.getId()));
        preference.setUserRole(userData.getRoleId());
        preference.setUserName(userData.getName());
        preference.setUserEmail(userData.getEmail());
        preference.setConnectedAdminName(userData.getAdminName());
        preference.setReferralCode(userData.getReferralCode());
        preference.setMobile(userData.getMobileNo());
    }
}