package app.android.development.bangkokguy.mthread;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyNls extends NotificationListenerService {

    private static final boolean DEBUG = true;
    private static final String TAG = "mthread.MyNls";
    public static final String NOTIFICATION_BROADCAST = "notification_broadcast.development.bangkokguy";
    private Bundle extras;

    public Intent notificationBroadcast;

    public MyNls() {
        Log.d(TAG, "constructor()");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        Log.d(TAG, "onNotificationPosted:" + statusBarNotification.toString());
        extras = statusBarNotification.getNotification().extras;
        notificationBroadcast.putExtra("msg", "Posted:" + extras.getString(Notification.EXTRA_TEXT) + extras.getString(Notification.EXTRA_TITLE));
        sendBroadcast(notificationBroadcast);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        Log.d(TAG, "onNotificationRemoved:" + statusBarNotification.toString());
        extras = statusBarNotification.getNotification().extras;
        notificationBroadcast.putExtra("msg", "Removed:" + extras.getString(Notification.EXTRA_TEXT) + extras.getString(Notification.EXTRA_TITLE));
        sendBroadcast(notificationBroadcast);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "onStartCommand()");
        notificationBroadcast.putExtra("msg", "onStartCommand()");
        sendBroadcast(notificationBroadcast);
        return NotificationListenerService.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        notificationBroadcast = new Intent();
        notificationBroadcast.setAction(NOTIFICATION_BROADCAST);
        notificationBroadcast.putExtra("msg", "onCreate()");
        sendBroadcast(notificationBroadcast);
    }

    /*@Override
    public IBinder onBind(Intent intent) {
        return null;
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(mReceiver);
    }
}