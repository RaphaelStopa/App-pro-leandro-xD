package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.domain.Political;

public class PoliticalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DeputyAdapter adapterDeputy = new DeputyAdapter();
    private SenatorAdapter adapterSenator = new SenatorAdapter();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String type = extras.getString("political");


        //important
        Data.getInstance().createDatabase(getApplicationContext());

        setContentView(R.layout.activity_political);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(PoliticalActivity.this)
        );

        intent = new Intent(PoliticalActivity.this, DetailsActivity.class);


        if(type.equals("DEPUTADO_FEDERAL")){
            recyclerView.setAdapter(adapterDeputy);

            adapterDeputy.setOnItemClickListener(new DeputyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Political political = Data.getInstance().getDeputies().get(position);
                    intent.putExtra("index",  political.getId().toString());
                    startActivity(intent);
                }
            }
            );
        } else {
            recyclerView.setAdapter(adapterSenator);

            adapterSenator.setOnItemClickListener(new SenatorAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Political political = Data.getInstance().getSenators().get(position);
                    intent.putExtra("index",  political.getId().toString());
                    startActivity(intent);
                }
            });
        }
    }
}