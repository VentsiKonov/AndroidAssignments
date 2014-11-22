package com.classasignment.one;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TileAdapter extends BaseAdapter {
    Context mContext;
    int[] mMatrix;
    int mState1;

    TileAdapter(Context context, int[] matrix){
        mContext = context;
        mMatrix = matrix;
        mState1 = 1;
    }

    @Override
    public int getCount() {
        return mMatrix.length;
    }

    @Override
    public Integer getItem(int position) {
        return mMatrix[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.tile, parent, false);
        }
        else{
            v = convertView;
        }

        TextView block = (TextView) v.findViewById(R.id.tv);
        block.setBackgroundColor(mMatrix[position] == mState1 ? Color.RED : Color.GREEN);

        return v;
    }


    public void reinit(int[] matrix){
        mMatrix = matrix;
    }
}
