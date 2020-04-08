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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }

        // define an adapter
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);

        makeApiCall();
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

            Call<List<RestGIPHYResponse>> call = giphyApi.getGiphyResponse();
            call.enqueue(new Callback<List<RestGIPHYResponse>>() {
                @Override
                public void onResponse(Call<List<RestGIPHYResponse>> call, Response<List<RestGIPHYResponse>> response) {
                    if(response.isSuccessful() & response.body()!=null){

                        List<Giphy> giphyList = response.body().getData();
                    }
                    /*else{
                        showError();
                    }*/

                }

                @Override
                public void onFailure(Call<List<RestGIPHYResponse>> call, Throwable t) {
                   // ShowError();
                }
            });


    }
}
