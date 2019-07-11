package com.example.android.shreygarg_spidertask3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void settosearchactivity(View view) {
        Intent set = new Intent(this, SearchActivity.class);
        startActivity(set);

    }

    public void settohistoryactivity(View view) {
        Intent set = new Intent(this, HistoryActivity.class);
        startActivity(set);

    }
}
