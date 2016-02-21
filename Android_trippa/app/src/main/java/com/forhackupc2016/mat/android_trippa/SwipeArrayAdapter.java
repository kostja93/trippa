package com.forhackupc2016.mat.android_trippa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by mat on 21.02.16.
 */
public class SwipeArrayAdapter extends ArrayAdapter<String> {
    protected LayoutInflater inflater;
    protected int layout;
    Integer[] imgID;

    //    public SwipeArrayAdapter(Activity activity, int resourceId, List<Integer> objects){
//        super(activity, resourceId);
//        imgID = objects.toArray(new Integer[objects.size()]);
//        layout = resourceId;
//        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
////TODO textview auf img umstellen
//    //TODO inflater auf handler umstellen, für smoother
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = inflater.inflate(layout, parent, false);
//        ImageView imageView = (ImageView)v.findViewById(R.id.imageViewSingleItem);
//        imageView.setImageResource(imgID[position]);
//        return v;
//    }
//}
    private final Activity context;
    private final Integer[] imgid;

    public SwipeArrayAdapter(Activity context, String[] itemName, Integer[] imgid) {
        super(context, R.layout.list_row_item, itemName);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.imgid = imgid;
    }

    //TODO inflater smooth machen, via Holder+recycling
    public View getView(int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.list_row_item, null, true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewSingleItem);

        imageView.setImageResource(imgid[position]);

        return rowView;

    }
}

