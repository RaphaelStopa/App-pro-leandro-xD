package com.example.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.example.recyclerview.domain.Political;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView;
    private DetailsAdapter detailsAdapter = new DetailsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("index");

        detailsAdapter.setId(Long.parseLong(id));

        Data.getInstance().createDatabase(getApplicationContext());

        setContentView(R.layout.activity_details);
        recyclerView = findViewById(R.id.recyclerViewDetails);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(DetailsActivity.this)
        );

        recyclerView.setAdapter(detailsAdapter);


        Data.getInstance().createDatabase(getApplicationContext());
        Political political = Data.getInstance().getPoliticalFromID(Integer.parseInt(id));

        textView = findViewById(R.id.textView5);
        textView.setText(political.getName());

    }
}