package com.example.android.shreygarg_spidertask3;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

public class HistoryActivity extends AppCompatActivity {
    Database mydb;
    RecyclerView list;
    List<String> word = new Vector<String>();
    List<String> etymology = new Vector<String>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.history_activity_layout);
            mydb = new Database(this);
            list = findViewById(R.id.result);
            list.setHasFixedSize(true);
            list.setLayoutManager(new LinearLayoutManager(this));
            Cursor res = mydb.getAllData();
            if(res.getCount()==0)
            {
                Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
            }
            else
            {
                while (res.moveToNext())
                {word.add(res.getString(0));
                    etymology.add(res.getString(1));
                }
                adapter = new Adapter(this,word,etymology);
                list.setAdapter(adapter);
            }
            EditText search = (EditText) findViewById(R.id.detailsearch);
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    serchaction(s.toString());
                }
            });
    }

    @Override
    protected void onResume() {

        super.onResume();
        if(adapter!=null)
            adapter.notifyDataSetChanged();
    }

    public void serchaction(String s) {
        List<String> searchword = new Vector<String>();
        List<String> searchetymology = new Vector<String>();
        if (!s.equals("")) {
            for (int i = 0; i < word.size(); i++) {
                if (word.get(i).toLowerCase().contains(s.toLowerCase())) {
                    searchword.add(word.get(i));
                    searchetymology.add(etymology.get(i));
                }
            }
            adapter = new Adapter(this, searchword,searchetymology);
            adapter.notifyDataSetChanged();
            list.setAdapter(adapter);
        } else {

            adapter = new Adapter(this, word,etymology);
            adapter.notifyDataSetChanged();
            list.setAdapter(adapter);

        }
    }
}
