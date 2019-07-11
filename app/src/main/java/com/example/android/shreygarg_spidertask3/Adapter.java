package com.example.android.shreygarg_spidertask3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class Adapter extends RecyclerView.Adapter<Adapter.LocationViewHolder> {
    private List<String> etymology = new Vector<String>();
    private List<String> word = new Vector<String>();
    private Context context;
    public Adapter(Context context, List<String> word, List<String> etymology) {
        this.context = context;
        this.word = word;
        this.etymology = etymology;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.search_blocks, viewGroup, false);
        return new LocationViewHolder(view);
    }

    public static int position;
    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int i) {
        locationViewHolder.textView.setText(word.get(i));
        final int pos = i;
        locationViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Result.class);
                intent.putExtra("word",word.get(pos));
                intent.putExtra("etymology",etymology.get(pos));
                context.startActivity(intent);
            }
        });
    }

    public static int getPosition()
    {
        return position;
    }
    @Override
    public int getItemCount() {
        return word.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout parent;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.blocktext);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}

