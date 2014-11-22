package com.classasignment.one;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TileAdapter extends BaseAdapter {
    Context mContext;
    String[] mStrings;
    int mState1, mState2;
    String mState1Symbol;

    TileAdapter(Context context, String[] strings){
        mContext = context;
        mStrings = strings;
        mState1 = Color.RED;
        mState2 = Color.GREEN;
        mState1Symbol = "X";

    }

    @Override
    public int getCount() {
        return mStrings.length;
    }

    @Override
    public String getItem(int position) {
        return mStrings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public TextView getView(int position, View convertView, ViewGroup parent) {
        TextView t;
        if(convertView == null){
            t = new TextView(mContext);
        }
        else{
            t = (TextView) convertView;
        }
        t.setHeight(100);
        t.setWidth(100);

        t.setBackgroundColor(mStrings[position].equals(mState1Symbol) ? mState1 : mState2);

        return t;
    }
}
