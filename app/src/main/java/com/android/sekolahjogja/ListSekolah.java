package com.android.sekolahjogja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.sekolahjogja.app.MyApplication;
import com.android.sekolahjogja.helper.ListSekolahAdapter;
import com.android.sekolahjogja.helper.Sekolah;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ListSekolah extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = ListSekolah.class.getSimpleName();
    private String URL_TOP_250 = "http://herisekolah.esy.es/";
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListSekolahAdapter adapter;
    private List<Sekolah> sekolahList;
    private ListView myList;
    private String jenissekolah;

    //@TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listsekolah);

        myList  = (ListView) findViewById(R.id.list_sekolah);
        sekolahList = new ArrayList<>();
        adapter = new ListSekolahAdapter(this, sekolahList);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Sekolah dataModel = (Sekolah) adapterView.getItemAtPosition(pos);
                //Log.d(TAG, "string: " + dataModel.getTitle());
                String namaSch = dataModel.getNama_sekolah();
                String alamatSch = dataModel.getAlamat_sekolah();
                String detail = dataModel.getInfo_sekolah();
                String lat = String.valueOf(dataModel.getLati());
                String lng = String.valueOf(dataModel.getLongi());
                String urlPicRes = dataModel.getGambar();

                Intent intent = new Intent(ListSekolah.this, DetailSekolah.class);
                intent.putExtra("nama",namaSch);
                intent.putExtra("alamat",alamatSch);
                intent.putExtra("detail",detail);
                intent.putExtra("url",urlPicRes);
                intent.putExtra("lat",lat);
                intent.putExtra("lng",lng);
                startActivity(intent);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        // Showing Swipe Refresh animation on activity create
        // As animation won't start on onCreate, post runnable is used
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                fetchSekolah();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MenuSekolah.class));
    }

    //This method is called when swipe refresh is pulled down
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        fetchSekolah();
    }

    //Fetching movies json by making http call
    private void fetchSekolah() {

        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        // appending offset to url
        jenissekolah = getIntent().getExtras().getString("jenissekolah");
        String ext;
        if(jenissekolah.equalsIgnoreCase("SD")){
            ext = "datasd.php";
        }else if (jenissekolah.equalsIgnoreCase("SMP")){
            ext = "datasmp.php";
        }else if (jenissekolah.equalsIgnoreCase("SMA")){
            ext = "datasma.php";
        }else{
            ext = "datapt.php";
        }
        String url = URL_TOP_250+ext;

        // Volley's json array request object
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        if (response.length() > 0) {

                            // looping through json and adding to movies list
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject movieObj = response.getJSONObject(i);

                                    int id_sekolah = movieObj.getInt("id_sekolah");
                                    int id_kategori = movieObj.getInt("id_kategori");
                                    String nama = movieObj.getString("nama_sekolah");
                                    String alamat = movieObj.getString("alamat_sekolah");
                                    String deskripsi = movieObj.getString("info_sekolah");
                                    Double lati =  movieObj.getDouble("latitude");
                                    Double longi = movieObj.getDouble("longitude");
                                    String urlpic = movieObj.getString("gambar");
                                    String kategori = movieObj.getString("nama_kategori");


                                    Sekolah m = new Sekolah(id_sekolah, id_kategori, nama, alamat, deskripsi, urlpic, kategori, lati, longi);

                                    sekolahList.add(m);

                                } catch (JSONException e) {
                                    Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                        // stopping swipe refresh
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Error: " + error.getMessage());

                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Error Retrieving Data\nPull Down to Refresh", Toast.LENGTH_LONG).show();
                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req);
    }

}