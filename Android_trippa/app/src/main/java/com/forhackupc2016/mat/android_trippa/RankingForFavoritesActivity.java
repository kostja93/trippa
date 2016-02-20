package com.forhackupc2016.mat.android_trippa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RankingForFavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_for_favortis);

        Button btnContinue = (Button) findViewById(R.id.buttonRankingContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListingOfContentActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
