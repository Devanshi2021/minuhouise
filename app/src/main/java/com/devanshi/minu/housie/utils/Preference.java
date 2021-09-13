package com.devanshi.minu.housie.utils;

import android.content.*;

import com.devanshi.minu.housie.*;


/**
 * Preference class to use SharedPreference class through out application. Use this class to store or retrieve data from SharedPreference.
 */
public class Preference {
    /**
     * Preference key for userId
     */
    private final String USER_NAME = "USER_NAME";
    private final String USER_ID = "USER_ID";
    private final String MOBILE = "MOBILE";
    private final String USER_ROLE = "USER_ROLE";
    private final String USER_EMAIL = "USER_EMAIL";
    private final String NUMBER_OF_TICKETS = "NUMBER_OF_TICKETS";
    private final String USER_STATUS = "USER_STATUS";
    private final String USER_REF_ID = "USER_REF_ID";
    private final String IS_USER_ADMIN = "IS_USER_ADMIN";
    private final String DEVICE_TOKEN = "DEVICE_TOKEN";
    private final String CONNECTED_ADMIN_ID = "CONNECTED_ADMIN_ID";
    private final String REFERRAL_CODE = "REFERRAL_CODE";

    /**
     * Shared Preference instance
     */
    private SharedPreferences sharedPreferences;


    public Preference(Context context) {
        sharedPreferences = (SharedPreferences) context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }


    /**
     * Returns the user name from Shared Preference file
     */
    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }

    /**
     * Stores the user type into Shared Preference file if not stored
     */
    void setUserName(String userName) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    /**
     * Returns the login timestamp from Shared Preference file
     */
    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    /**
     * Stores the login timestamp into Shared Preference file if not stored
     */
    void setUserId(String userId) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, userId);
        editor.commit();
    }

    /**
     * Returns the User First Name from Shared Preference file
     */
    public String getMobile() {
        return sharedPreferences.getString(MOBILE, "");
    }

    /**
     * Stores the User First Name into Shared Preference file
     */
    void setMobile(String mobile) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MOBILE, mobile);
        editor.commit();
    }

    /**
     * Returns the User role from Shared Preference file
     */
    public Integer getUserRole() {
        return sharedPreferences.getInt(USER_ROLE, 0);
    }

    /**
     * Stores the User role into Shared Preference file
     */
    void setUserRole(Integer userRole) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ROLE, userRole);
        editor.commit();
    }

    /**
     * Returns the User email from Shared Preference file
     */
    public String getUserEmail() {
        return sharedPreferences.getString(USER_EMAIL, "");
    }

    /**
     * Stores the User email into Shared Preference file
     */
    void setUserEmail(String userEmail) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_EMAIL, userEmail);
        editor.commit();
    }

    /**
     * Returns the string from the Shared Preference file
     *
     * @return deviceToken
     */
    public String getDeviceToken() {
        return sharedPreferences.getString(DEVICE_TOKEN, "");
    }

    /**
     * Stores the Device Token into Shared Preference file
     */
    public void setDeviceToken(final String deviceToken) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DEVICE_TOKEN, deviceToken);
        editor.commit();
    }

    /**
     * Clears the all Shared Preference data
     */
    public void clearAllPreferenceData() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void setConnectedAdminName(String connectedAdminId) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CONNECTED_ADMIN_ID, connectedAdminId);
        editor.commit();
    }

    public String getConnectedAdminName() {
        return sharedPreferences.getString(CONNECTED_ADMIN_ID,"");
    }

    /**
     * Returns the string from the Shared Preference file
     *
     * @return deviceToken
     */
    public String getReferralCode() {
        return sharedPreferences.getString(REFERRAL_CODE, "");
    }

    /**
     * Stores the Device Token into Shared Preference file
     */
    public void setReferralCode(final String referralCode) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REFERRAL_CODE, referralCode);
        editor.commit();
    }
}
