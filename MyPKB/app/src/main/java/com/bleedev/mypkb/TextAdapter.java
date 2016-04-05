package com.bleedev.mypkb;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter {
    private Context mContext;

    public TextAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, final ViewGroup parent) {
        final TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);

            textView.setLayoutParams(new GridView.LayoutParams(210, 70));
           // textView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }

       // textView.setImageResource(mThumbIds[position]);
        textView.setText(mThumbIds[position]);
        return textView;
    }

    // references to our images
    private String[] mThumbIds = {
           "Sign", "Name", "Address", "phone",
            "Email", "CV Template", "*"
    };
}