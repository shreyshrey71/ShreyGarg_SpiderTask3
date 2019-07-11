package com.example.android.shreygarg_spidertask3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    EditText editText;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_layout);
        assert  getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = findViewById(R.id.searchbar);
        editText.setVisibility(View.INVISIBLE);
        imageView = findViewById(R.id.searchicon);
        imageView.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.result);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("word")+"\n\nEtymology :-\n                        "+intent.getStringExtra("etymology"));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            {
                onBackPressed();
            }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
