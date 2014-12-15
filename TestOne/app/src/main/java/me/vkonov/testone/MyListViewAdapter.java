package me.vkonov.testone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Венци on 15.12.2014 г..
 */
public class MyListViewAdapter extends BaseAdapter{


    private ArrayList<Product> products;
    private Context context;

    public MyListViewAdapter(Context context, ArrayList<Product> products) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LinearLayout l = new LinearLayout(context);
            TextView name = new TextView(context);
            name.setText(products.get(position).getName());
            name.setTag(1);
            l.addView(name);
            view = l;
        }

        else{
            ((TextView) view.findViewWithTag(1)).setText(products.get(position).getName());
        }
        return view;
    }
}
