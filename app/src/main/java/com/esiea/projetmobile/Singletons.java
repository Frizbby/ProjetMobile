package com.esiea.projetmobile;

import android.content.Context;
import android.content.SharedPreferences;

import com.esiea.projetmobile.data.GiphyApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static GiphyApi giphyApiInstance;
    private static SharedPreferences sharedPreferencesInstance;


    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance =  new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

public static GiphyApi getGiphyApi(){
    if (giphyApiInstance == null){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();

        giphyApiInstance = retrofit.create(GiphyApi.class);

    } return giphyApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance =  context.getSharedPreferences("ProjetMobile",Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;

    }
}
