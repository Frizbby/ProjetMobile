package com.esiea.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImageTranscoderType;
import com.facebook.imagepipeline.core.MemoryChunkType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://api.giphy.com";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Context applicationContext;
    private DraweeHolder mSimpleDraweeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

        /*Fresco.initialize(
                applicationContext,
                ImagePipelineConfig.newBuilder(applicationContext)
                        .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                        .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                        .experiment().setNativeCodeDisabled(true)
                        .build());*/
        setContentView(R.layout.activity_main);



        Uri uri = Uri.parse("https://www.tela-botanica.org/wp-content/uploads/2019/08/tree-3822149_1920-700x466.jpg");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
   // webView=findViewById(R.id.web_view);

   // WebSettings webSettings = webView.getSettings();
   // webSettings.setJavaScriptEnabled(true);

   // String url = "https://media.giphy.com/media/QIkavkylBv0Z2/giphy.gif";
    //webView.loadUrl(url);


        sharedPreferences = getSharedPreferences("application_giphy", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

    List<Giphy> giphyList = getDataFromCache();

    if (giphyList != null){
            showList(giphyList);
        } else {
            makeApiCall();
        }

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
                        saveList(giphyList);
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

    private void saveList(List<Giphy> giphyList) {
    String jsonString = gson.toJson(giphyList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_GIPHY_LIST,jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "LIST saved ", Toast.LENGTH_SHORT).show();

    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }
}
