package com.example.myapplication;

import android.bluetooth.BluetoothAdapter; import android.content.Context; import android.content.Intent; import android.net.ConnectivityManager; import android.net.NetworkInfo; import android.net.Uri; import android.net.wifi.WifiManager; import android.os.Bundle; import android.support.v7.app.AppCompatActivity; import android.view.View; import android.widget.Button; import android.widget.TextView; import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

static int bluetoothStatus = -1;
BluetoothAdapter Adapter;
TextView Status;
TextView Fail;
TextView Exception;
Button check;
@Override

protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Status = findViewById(R.id.statusBluetooth);
    Adapter = BluetoothAdapter.getDefaultAdapter();
    Fail = findViewById(R.id.failAlert);
    Exception = findViewById(R.id.TurnOnRequest);
    check = findViewById(R.id.check);

    ConnectivityManager conn = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

    check.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean wifi = false;

            int connectStats = WifiManager.WIFI_STATE_ENABLED;

            ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = conn.getActiveNetworkInfo();

            if(connectStats==WifiManager.WIFI_STATE_ENABLED) {
                wifi = info.getType() == ConnectivityManager.TYPE_WIFI;
                if (!wifi) {
                    Status.setText(null);
                    Exception.setText(null);
                    Fail.setText("The package is stolen, please report!");
                    Toast.makeText(MainActivity.this, "Message Send",
                            Toast.LENGTH_SHORT).show();

                    String messagephone = ""; //insert the number between the quotations
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", messagephone, null));
                    startActivity(intent);

                } else {
                    Fail.setText(null);
                    Status.setText("The package is safely in transportation");

                }
            }



        }


    });

}
}
