package com.forhackupc2016.mat.android_trippa;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class SelectFavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_favorits);

        RelativeLayout buttonSelectItem1 = (RelativeLayout) findViewById(R.id.buttonSelectItem1);
        RelativeLayout buttonSelectItem2 = (RelativeLayout) findViewById(R.id.buttonSelectItem2);
        RelativeLayout buttonSelectItem3 = (RelativeLayout) findViewById(R.id.buttonSelectItem3);

        buttonSelectItem1.setClickable(true);
        buttonSelectItem2.setClickable(true);
        buttonSelectItem3.setClickable(true);

        buttonSelectItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SetOwnPreferences.class);
                startActivityForResult(intent, 0);
            }
        });
        buttonSelectItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        buttonSelectItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinnerAmbiance);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinnerFurnishings);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinnerPriceRange);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.ambiance_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.furnishings_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.pricerange_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
    }
}
