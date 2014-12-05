package app.android.development.bangkokguy.mthread;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {

    private static final String TAG = "mthread.main";
    private static final boolean DEBUG = true;

    public static ArrayAdapter<String> itemsAdapter;
    public ListView listView;
    public BroadcastReceiver rcvNotificationBroadcast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        List<String> notSoCoolStringList = new ArrayList<String>();
        notSoCoolStringList.add("Java");
        notSoCoolStringList.add("Scala");
        notSoCoolStringList.add("Groovy");

        itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notSoCoolStringList);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);
        itemsAdapter.add("a");

        IntentFilter ifNotificationBroadcast = new IntentFilter(MyNls.NOTIFICATION_BROADCAST);

        rcvNotificationBroadcast = new MyReceiver();
        registerReceiver(rcvNotificationBroadcast, ifNotificationBroadcast);

        Log.d(TAG, "onCreate()");
        itemsAdapter.add("onCreate()");
        listView.invalidate();
        Intent ix=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(ix);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        itemsAdapter.add("onCreateOptionsMenu()");
        listView.invalidate();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected()");
        int id = item.getItemId();
        itemsAdapter.add("onOptionsItemSelected()");
        listView.invalidate();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "MyReceive.onReceive()");
            itemsAdapter.add(intent.getStringExtra("msg"));
            listView.invalidate();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        itemsAdapter.add("onDestroy()");
        listView.invalidate();
        unregisterReceiver(rcvNotificationBroadcast);
    }
}