package com.example.innovafit;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyAndroidFirebaseMsgService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d("====>","NEW_TOKEN: "+token);
        //sendRegistrationToPubNub(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);
        Log.i("==========>", "De: " + remoteMessage.getFrom());
        Log.i("==========>", "Mensaje: " + remoteMessage.getNotification().getBody());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.i("======>", "Message data payload: " + remoteMessage.getData());
            Map<String, String> data = remoteMessage.getData();

            //
        } else if (remoteMessage.getNotification() != null) {
            Log.i("======>", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            createNotification(remoteMessage.getNotification().getBody());
        }// Check if message contains a notification payload.//

    }

    private void createNotification( String messageBody) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            System.out.println("=====================FIREBASE===================================");
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            //notificationChannel.setSound();
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);


        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        //Intent stopped = new Intent(this,MainActivity.class);
        //stopped.setAction("test");
        Intent intent = new Intent( this , MapaBasicoActivity. class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent actionPendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                //.addAction(R.drawable.common_google_signin_btn_icon_light, "ACEPTAR", actionPendingIntent)
                //.setWhen(System.currentTimeMillis())
                .setContentIntent(actionPendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setBadgeIconType(R.mipmap.ic_launcher)
                .setContentTitle("Curso de Android")
                //.setSound(Uri.parse("android.resource://"+getPackageName()+"/" + R.raw.beeep))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setContentInfo("SMART");

        notificationManager.notify(/*notification id*/1, notificationBuilder.build());
    }
}

