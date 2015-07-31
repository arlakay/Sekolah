package com.android.sekolahjogja;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Pencarian extends Activity {



    private Intent i;

    private String[] namasekolah;
    private String[] deskripsi;
    private Double[] lat;
    private Double[] lng;

     @TargetApi(Build.VERSION_CODES.GINGERBREAD) @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listsekolah);

        if (android.os.Build.VERSION.SDK_INT > 9) {
         StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
           StrictMode.setThreadPolicy(policy);
        }

        String hasil = null;
        InputStream is = null;
        StringBuilder sb = null;

        // http post
        try {
            HttpClient httpclient = new DefaultHttpClient();
            // 10.0.2.2 ip nya localhost, kalau ada domain yang online tinggal ganti aja ip nya pake domain (misal: example.com)
            // androidOnlineJSON.php buat akses ke databasenya, ditaro di htdocs
            String jenissekolah = getIntent().getExtras().getString("jenissekolah");
            String url = "";
            if(jenissekolah.equalsIgnoreCase("SD")){
                url = "datasd.php";
            }else if (jenissekolah.equalsIgnoreCase("SMP")){
                url = "datasmp.php";
            }else if (jenissekolah.equalsIgnoreCase("SMA")){
                url = "datasma.php";
            }else{
                url = "datapt.php";
            }

            HttpPost httppost = new HttpPost("http://10.0.2.2/"+url);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        // convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");
            String line = "0";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            hasil = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        // parsing data
        JSONArray jArray;
        try {
            jArray = new JSONArray(hasil);
            JSONObject json_data = null;

            namasekolah = new String [jArray.length()];
            deskripsi = new String [jArray.length()];
            lat = new Double[jArray.length()];
            lng = new Double [jArray.length()];

            for (int i = 0; i < jArray.length(); i++) {
                json_data = jArray.getJSONObject(i);

                namasekolah[i] = json_data.getString("nama_sekolah");
                deskripsi[i] = json_data.getString("info_sekolah");
                lat[i] = json_data.getDouble("latitude");
                lng[i] = json_data.getDouble("longitude");
            }
        } catch (JSONException e1) {
            Toast.makeText(getBaseContext(), "sekolah belum ada", Toast.LENGTH_LONG).show();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        i = new Intent(this, DetailSekolah.class);




        ListView lv = (ListView) findViewById(R.id.list_sekolah);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namasekolah));
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int indexSelectedItem,
                                    long arg3) {
                Toast.makeText(getApplicationContext(),
                        namasekolah[indexSelectedItem] + " wasClicked",
                        Toast.LENGTH_SHORT).show();


                i.putExtra("namasekolah", namasekolah[indexSelectedItem]);
                i.putExtra("deskripsi", deskripsi[indexSelectedItem]);
                i.putExtra("lat", lat[indexSelectedItem]);
                i.putExtra("lng", lng[indexSelectedItem]);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        startActivity(new Intent(this, MenuSekolah.class));
    }
}