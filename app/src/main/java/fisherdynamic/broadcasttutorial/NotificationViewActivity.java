package fisherdynamic.broadcasttutorial;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationViewActivity extends AppCompatActivity {
    String title;
    String text;
    TextView txttitle;
    TextView txttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);

        Intent i = getIntent();
        title = i.getStringExtra("title");
        text = i.getStringExtra("text");

        txttitle = (TextView) findViewById(R.id.title);
        txttext = (TextView) findViewById(R.id.text);

        txttitle.setText(title);
        txttext.setText(text);
    }
}
