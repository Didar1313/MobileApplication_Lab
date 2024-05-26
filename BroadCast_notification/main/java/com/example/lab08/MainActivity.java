package com.example.lab08;
import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab08.R;
public class MainActivity extends AppCompatActivity {
    private TextView batteryLvTxt;
    private BatteryReceiver batteryRcv;
    private int prevLv = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batteryLvTxt = findViewById(R.id.battery_level_text);
        batteryRcv = new BatteryReceiver();

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryRcv, filter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryRcv);
    }
    private class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryPct = (int) ((level / (float) scale) * 100);
            if (batteryPct != prevLv) {
                prevLv = batteryPct;
                batteryLvTxt.setText("Battery Level: " + batteryPct + "%");
                Toast.makeText(context, "Battery Level: " + batteryPct + "%", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
