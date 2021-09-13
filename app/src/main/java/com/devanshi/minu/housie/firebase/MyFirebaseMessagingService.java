package com.devanshi.minu.housie.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.devanshi.minu.housie.*;
import com.devanshi.minu.housie.activity.*;
import com.devanshi.minu.housie.utils.*;
import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    Preference preference;
    @Override
    public void onNewToken(@NonNull String token) {
//        super.onNewToken(token);
        Log.d("TAG_TOKEN", token);
        preference = new Preference(this);
        preference.setDeviceToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d("TAG_TOKEN", "From: " + remoteMessage.getFrom());
        // Check if message contains a notification payload.
        String title = "";
        String body = "";
        JSONObject object = null;
        if (remoteMessage.getNotification() != null) {
            title = remoteMessage.getNotification().getTitle();
            body = remoteMessage.getNotification().getBody();
            assert body != null;
            body = body.concat(" ");
            Log.d("TAG_TOKEN", "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.d("TAG_TOKEN", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            Log.d("TAG_TOKEN", "Message data payload: " + remoteMessage.getData());
            Map<String, String> params = remoteMessage.getData();
            object = new JSONObject(params);
            Log.e("JSON_OBJECT", object.toString());
        }
        showNotification(title, body, object);
    }

    private void showNotification(String title, String body, JSONObject object) {
        NotificationManager mNotificationManager;
        NotificationCompat.Builder mBuilder;
        String NOTIFICATION_CHANNEL_ID = "default_channel_id";
        String message = "";
        if (object != null) {
            message = object.optString("message");
        }

        /**Creates an explicit intent for an Activity in your app**/
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel);
            NotificationChannel notificationChannel1 = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID);
            assert notificationChannel1 != null;
        }
        mBuilder = new NotificationCompat.Builder(getApplicationContext(),NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(body+message)
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        if (!title.isEmpty()){
            mBuilder.setContentTitle(title);
        }

        assert mNotificationManager != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null){
                Log.e("niraj", "showNotification: channel null");
                return;
            }
        }

        mNotificationManager.notify(1 /* Request Code */, mBuilder.build());
    }

}
