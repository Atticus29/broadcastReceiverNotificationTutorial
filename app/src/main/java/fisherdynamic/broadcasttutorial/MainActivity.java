package fisherdynamic.broadcasttutorial;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton wifitoggle;
    CheckBox brcheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("personal", "created main Activity");

        wifitoggle = (ToggleButton) findViewById(R.id.wifitoggle);

        brcheckbox = (CheckBox) findViewById(R.id.brcheckbox);

        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        wifitoggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(wifitoggle.isChecked()){
                    wifiManager.setWifiEnabled(true);
                } else {
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        brcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brcheckbox.isChecked()){
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, BroadcastManager.class);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "Broadcast Receiver Started", Toast.LENGTH_LONG).show();
                } else {
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(MainActivity.this, BroadcastManager.class);
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(), "Broadcast Receiver Stopped", Toast.LENGTH_LONG).show();
                }
            }
        });
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            wifitoggle.setChecked(true);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}
