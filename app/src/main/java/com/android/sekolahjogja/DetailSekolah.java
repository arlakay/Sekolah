package com.android.sekolahjogja;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class DetailSekolah extends Activity {

    private String TAG = DetailSekolah.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_sekolah);

        final String namasekolah = getIntent().getExtras().getString("namasekolah");
        String deskripsi = getIntent().getExtras().getString("deskripsi");
        final Double lat = getIntent().getExtras().getDouble("lat");
        final Double lng = getIntent().getExtras().getDouble("lng");

        TextView nama = (TextView) findViewById(R.id.nama_sekolah);
        nama.setText(namasekolah);

        TextView info = (TextView) findViewById(R.id.deskripsi);
        info.setText(deskripsi);

        Button lokasi =  (Button)findViewById(R.id.lokasi);
        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&daddr=%f,%f (%s)", lat, lng, namasekolah);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                try
                {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        Toast.makeText(getApplicationContext(), "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Ngeprint di logcat
        Log.d(TAG, "string: " + lat);
        Log.d(TAG, "string: " + lng);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cari) {
            startActivity(new Intent(this, Pencarian.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
