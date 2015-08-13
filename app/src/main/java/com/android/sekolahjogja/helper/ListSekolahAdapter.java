package com.android.sekolahjogja.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.sekolahjogja.R;
import com.android.sekolahjogja.app.MyApplication;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Class Adapter buat menangani listsekolah_item.xml
 * yang dipanggil melaui listview di layout listsekolah.xml
 */

public class ListSekolahAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Sekolah> sekolahList;
    private Context context;
    private Typeface fontLemonMilk, fontHabibi;

    //private String[] bgColors;

    ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

    public ListSekolahAdapter(Activity activity, List<Sekolah> sekolahList) {
        this.activity = activity;
        this.sekolahList = sekolahList;
        //context       = activity.getApplicationContext();
        //fontLemonMilk = FontCache.get(this.context, "LemonMilk");
        //fontHabibi    = FontCache.get(this.context, "Habibi-Regular");
        //bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return sekolahList.size();
    }

    @Override
    public Object getItem(int location) {
        return sekolahList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.listsekolah_item, null);

        if (imageLoader == null)
            imageLoader = MyApplication.getInstance().getImageLoader();

        //NetworkImageView pic = (NetworkImageView) convertView.findViewById(R.id.photo);
        TextView nama = (TextView) convertView.findViewById(R.id.name);
        //TextView alamat = (TextView) convertView.findViewById(R.id.itemDescription);
        //title.setTypeface(fontLemonMilk);
        //alamat.setTypeface(fontHabibi);

        //pic.setImageUrl(sekolahList.get(position).urlpic, imageLoader);
        nama.setText(sekolahList.get(position).nama_sekolah);
        //alamat.setText(sekolahList.get(position).alamat);

        //String color = bgColors[position % bgColors.length];
        //serial.setBackgroundColor(Color.parseColor(color));
        return convertView;
    }

}