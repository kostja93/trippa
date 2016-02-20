package com.forhackupc2016.mat.android_trippa;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
public class ListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final Integer[] imageUrl;
        public ListAdapter(Activity context,  Integer[] imageUrl) {

            super(context, R.layout.input_of_listing);
            this.context = context;
            this.imageUrl = imageUrl;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View singleView= inflater.inflate(R.layout.input_of_listing, null, true);

            ImageView imageView = (ImageView) singleView.findViewById(R.id.imageViewSingleItem);

            imageView.setImageResource(imageUrl[position]);
            return singleView;
        }
    }

