package cn.jitash.exercise.android_advanced_light.chapter_1.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

import cn.jitash.exercise.R;

public class NofiticationActivity extends AppCompatActivity {
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nofitication);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jitash"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);


        sendNormalNotification(builder);
        sendFoldNotification(builder);
    }

    private void sendFoldNotification(Notification.Builder builder) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable
                .ic_launcher_background));
        builder.setAutoCancel(true);
        builder.setContentTitle("折叠式通知");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomBigContentView(remoteViews);
            mNotificationManager.notify(1, builder.build());
        } else {
            Notification notification = builder.build();
            notification.bigContentView = remoteViews;
            mNotificationManager.notify(1, notification);
        }
    }

    private void sendNormalNotification(Notification.Builder builder) {
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable
                .ic_launcher_background));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
        mNotificationManager.notify(0, builder.build());
    }
}
