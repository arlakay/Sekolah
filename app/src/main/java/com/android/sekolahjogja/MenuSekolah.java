package com.android.sekolahjogja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuSekolah extends Activity{
    Button sd;
    Button smp;
    Button sma;
    Button univ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_sekolah);


        sd = (Button) findViewById(R.id.btn_sd);
        smp = (Button) findViewById(R.id.btn_smp);
        sma = (Button) findViewById(R.id.btn_sma);
        univ = (Button) findViewById(R.id.btn_univ);


        sd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListSekolah.class);
                i.putExtra("jenissekolah", "SD");
                startActivity(i);
                finish();
            }
        });

        smp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListSekolah.class);
                i.putExtra("jenissekolah", "SMP");
                startActivity(i);
                finish();
            }
        });
        sma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListSekolah.class);
                i.putExtra("jenissekolah", "SMA");
                startActivity(i);
                finish();
            }
        });
        univ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ListSekolah.class);
                i.putExtra("jenissekolah", "Univ");
                startActivity(i);
                finish();
            }
        });
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
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}
