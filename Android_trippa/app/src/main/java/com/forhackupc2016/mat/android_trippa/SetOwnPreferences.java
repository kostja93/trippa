package com.forhackupc2016.mat.android_trippa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetOwnPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_own_preferences);
        Button buttonLogin = (Button) findViewById(R.id.buttonRankingContinue);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListOfBarsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
