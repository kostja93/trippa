package com.forhackupc2016.mat.android_trippa;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by mat on 20.02.16.
 */
//abstract public class ListAdapter extends BaseAdapter {
//
//
//    @Override
//    public int getViewTypeCount() {
//        // menu type count
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        // current menu type
//        int type=0;
//        return type;
//    }
//
//}
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
//    private final String[] itemname;
    private final Integer[] imgid;

    public ListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.activity_listofbars, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
//        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_listofbars, null,true);

//        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemNameForListDemo);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewSingleItem);

        if (imageView != null) {
//            txtTitle.setText(itemname[position]);
           imageView.setImageResource(imgid[position]);
        }
        return rowView;

    }
}

