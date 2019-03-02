package com.fatkhun.agriculture.mvp.data.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.utils.AppConstants;



public class MyNotificationManager extends AppCompatActivity {
    private Context mCtx;
    private static MyNotificationManager mInstance;
    private MyNotificationManager(Context context) {
        mCtx = context;
    }

    public static synchronized MyNotificationManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body, String content) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, AppConstants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_mindorks_logo)
                        .setContentTitle(title)
                        .setAutoCancel(true)
                        .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                        .setContentText(body);


        /*
         *  Clicking on the notification will take us to this intent
         *  Right now we are using the MainActivity as this is the only activity we have in our application
         *  But for your project you can customize it as you want
         * */

//        Intent resultIntent = new Intent(mCtx, HomeActivity.class);

        /*
         *  Now we will create a pending intent
         *  The method getActivity is taking 4 parameters
         *  All paramters are describing themselves
         *  0 is the request code (the second parameter)
         *  We can detect this code in the activity that will open by this we can get
         *  Which notification opened the activity
         * */
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
        notificationIntent.setData(Uri.parse(content));
        PendingIntent pending = PendingIntent.getActivity(mCtx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        /*
         *  Setting the pending intent to notification builder
         * */

        mBuilder.setContentIntent(pending);


        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);

        /*
         * The first parameter is the notification id
         * better don't give a literal here (right now we are giving a int literal)
         * because using this id we can modify it later
         * */
        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }
}
