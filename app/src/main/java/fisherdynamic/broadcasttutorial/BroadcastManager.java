package fisherdynamic.broadcasttutorial;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;

/**
 * Created by mf on 10/4/17.
 */

public class BroadcastManager {
    @Override
    public void onReceive(Context context, Intent intent){
        if(!isNetworkAvailable(context)){
            Notification(context, "Wifi Connection is Off");
        } else {
            Notification(context, "Wifi Connection On");
        }
    }

    public void Notification(Context context, String message){
        String strtitle = context.getString(R.string.notificationtitle);
        Intent intent = new Intent(context, NotificationView.class);
        intent.putExtra("title", strtitle);
        intent.putExtra("text", message);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logosmall)
                .setTicker(message)
                .setContentTitle(context.getString(R.string.notificationtitle))
                .setContentText(message)
                .addAction(R.drawable.ic_launcher, "Action Button", pIntent)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    private boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
