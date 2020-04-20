package com.esiea.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://api.giphy.com";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        makeApiCall();


    }

    private void showList(List<Giphy> giphyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


      /*  List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }*/

        // define an adapter
        mAdapter = new ListAdapter(giphyList);
        recyclerView.setAdapter(mAdapter);


    }
    /*public void page1 (View view){
        startActivity(new Intent(this, page_2.class));
    }

*/



    private void makeApiCall(){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            GiphyApi giphyApi = retrofit.create(GiphyApi.class);
            //Toast.makeText(getApplicationContext(), "API SUCCESS", Toast.LENGTH_SHORT).show();
            Call<RestGIPHYResponse> call = giphyApi.getGiphyResponse();


            call.enqueue(new Callback<RestGIPHYResponse>() {

                @Override
                public void onResponse(Call<RestGIPHYResponse> call, Response<RestGIPHYResponse> response) {

                    if (response.isSuccessful() & response.body() != null) {

                        List<Giphy> giphyList = response.body().getData();
                        Toast.makeText(getApplicationContext(), "API SUCCESS", Toast.LENGTH_SHORT).show();
                        showList(giphyList);
                    } else {
                        showError();
                    }

                }

                @Override
                public void onFailure(Call<RestGIPHYResponse> call, Throwable t) {
                    showError();
                }

            });


    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }
}
