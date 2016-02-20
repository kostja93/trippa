package com.forhackupc2016.mat.android_trippa;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListingOfContentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_of_content);

        SwipeMenuListView menuListView = (SwipeMenuListView) findViewById(R.id.listView);
        menuListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(90);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_forever_black_48dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        menuListView.setMenuCreator(creator);

//        menuListView.setOnMenuItemClickListener(new View.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                switch (index) {
//                    case 0:
//                        // open
//                        break;
//                    case 1:
//                        // delete
//                        break;
//                }
//                // false : close the menu; true : not close the menu
//                return false;
//            }
//        });


    } //End Of onCreate

    public Gson getHTTP() throws IOException {
        URL url = new URL("http://10.4.180.249/location/41.37/-8.23/30000");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
        byte[] contents = new byte[1024];

        int bytesRead=0;
        String strFileContents="";
        while( (bytesRead = bis.read(contents)) != -1){
            strFileContents = new String(contents, 0, bytesRead);
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        gson.fromJson(strFileContents, ListedElement.class);

        return gson;
    }
} // End Of Class
