package com.example.android.shreygarg_spidertask3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_layout);
        editText = findViewById(R.id.searchbar);
        textView = findViewById(R.id.result);
        textView.setVisibility(View.GONE);
        mydb = new Database(this);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {

                    searchword(v);
                    return true;
                }
                return false;
            }
        });
    }

    String appid = "278573d0";
    String appkey = "5fc98e610518a75d300f134bf18e5960";

    public void searchword(View view) {
        if (!editText.getText().toString().equals("")) {
            InputMethodManager imm = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            Toast.makeText(getApplicationContext(),"Just a Sec!!!",Toast.LENGTH_SHORT).show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://od-api.oxforddictionaries.com:443/api/v2/entries/en-gb/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api api = retrofit.create(Api.class);
            Call<Words> call = api.getResult(editText.getText().toString(),appid,appkey);
            call.enqueue(new Callback<Words>() {
                @Override
                public void onResponse(Call<Words> call, Response<Words> response) {

                    if(!response.isSuccessful()){
                        textView.setText("Word Not Found!");
                        textView.setVisibility(View.VISIBLE);
                        return;}
                    Words words = response.body();
                    if(words.getResults().get(0).getLexicalEntries().get(0).getEntries().get(0).getEtymologies().get(0)!=null){
                        mydb.insertData(editText.getText().toString().toLowerCase(),words.getResults().get(0).getLexicalEntries().get(0).getEntries().get(0).getEtymologies().get(0));
                        textView.setText(editText.getText().toString()+"\n\nEtymology :-\n                        "+words.getResults().get(0).getLexicalEntries().get(0).getEntries().get(0).getEtymologies().get(0));
                        textView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        textView.setText("Word Not Found!");
                        textView.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<Words> call, Throwable t) {
                    textView.setText("Word Not Found!");
                    textView.setVisibility(View.VISIBLE);
                }
            });
        }


    }
}
