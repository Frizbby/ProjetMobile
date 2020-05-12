package com.esiea.projetmobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import com.esiea.projetmobile.R;
import com.esiea.projetmobile.Singletons;
import com.esiea.projetmobile.controller.MainController;
import com.esiea.projetmobile.model.Giphy;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context applicationContext;

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);

        mainController = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())
        );
        mainController.onStart();

    }


    public void showList(List<Giphy> giphyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(giphyList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Giphy item) {
                mainController.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Giphy giphy) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("giphyKey", Singletons.getGson().toJson(giphy));
        MainActivity.this.startActivity(myIntent);

    }
}
