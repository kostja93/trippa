package com.forhackupc2016.mat.android_trippa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SelectFavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_favorits);

        RelativeLayout buttonSelectItem1 = (RelativeLayout) findViewById(R.id.buttonSelectItem1);

        buttonSelectItem1.setClickable(true);

        buttonSelectItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RankingForFavortisActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
