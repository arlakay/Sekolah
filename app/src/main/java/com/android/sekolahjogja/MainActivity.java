package com.android.sekolahjogja;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button menusekolah;
    private Button peta;
    private Button tentang;
    private Button keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menusekolah = (Button) findViewById(R.id.btn_menusekolah);

        peta = (Button) findViewById(R.id.btn_peta);

        tentang = (Button) findViewById(R.id.btn_tentang);

        keluar = (Button) findViewById(R.id.btn_keluar);


        menusekolah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MenuSekolah.class);
                startActivity(i);
                finish();
            }
        });

        peta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PosisiKita.class);
                startActivity(i);
                finish();
            }
        });

        tentang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Tentang.class);
                startActivity(i);
                finish();
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                close();
            }
        });
    }

    public void close(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Benar-Benar ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                MainActivity.this.finish();
                            }
                        })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        dialog.cancel();

                    }
                }).show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            close();

        }
        return super.onKeyDown(keyCode, event);
    }

}