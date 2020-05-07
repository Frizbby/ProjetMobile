package com.esiea.projetmobile.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.esiea.projetmobile.Constants;
import com.esiea.projetmobile.Singletons;
import com.esiea.projetmobile.model.Giphy;
import com.esiea.projetmobile.model.RestGiphyResponse;
import com.esiea.projetmobile.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity,Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
}

public void onStart(){


    List<Giphy> giphyList = getDataFromCache();

    if (giphyList != null){
        view.showList(giphyList);
    } else {
        makeApiCall();
    }

}
    private void makeApiCall(){

        //Toast.makeText(getApplicationContext(), "API SUCCESS", Toast.LENGTH_SHORT).show();
        Call<RestGiphyResponse> call = Singletons.getGiphyApi().getGiphyResponse();


        call.enqueue(new Callback<RestGiphyResponse>() {

            @Override
            public void onResponse(Call<RestGiphyResponse> call, Response<RestGiphyResponse> response) {

                if (response.isSuccessful() & response.body() != null) {

                    List<Giphy> giphyList = response.body().getData();
                    Toast.makeText(view.getApplicationContext(), "API SUCCESS", Toast.LENGTH_SHORT).show();
                    saveList(giphyList);
                    view.showList(giphyList);
                } else {
                   view.showError();
                }

            }

            @Override
            public void onFailure(Call<RestGiphyResponse> call, Throwable t) {
                view.showError();
            }

        });


    }

    private void saveList(List<Giphy> giphyList) {
        String jsonString = gson.toJson(giphyList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_GIPHY_LIST,jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "LIST saved ", Toast.LENGTH_SHORT).show();

    }

    private List<Giphy> getDataFromCache() {
        String jsonGiphy = sharedPreferences.getString(Constants.KEY_GIPHY_LIST,null);

        if (jsonGiphy == null){
            return null;
        } else{
            Type listType = new TypeToken<List<Giphy>>(){}.getType();
            return gson.fromJson(jsonGiphy, listType);
        }

    }


    public void onItemClick(Giphy giphy){
        view.navigateToDetails(giphy);
}


}