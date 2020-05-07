package com.esiea.projetmobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.Toast;

import com.esiea.projetmobile.Constants;
import com.esiea.projetmobile.R;
import com.esiea.projetmobile.controller.MainController;
import com.esiea.projetmobile.data.GiphyApi;
import com.esiea.projetmobile.model.Giphy;
import com.esiea.projetmobile.model.RestGiphyResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
/*
        final SimpleDraweeView mSimpleDraweeView = findViewById(R.id.my_image_view2);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri("https://media3.giphy.com/media/mlvseq9yvZhba/giphy.gif?cid=f6f3409eb5f7206b7232ffbffbaf2493ddc01a9cda8918a4&rid=giphy.gif")
                .setAutoPlayAnimations(true)
                .build();
        mSimpleDraweeView.setController(controller);
*/
       /* Uri uri = Uri.parse("https://media0.giphy.com/media/mlvseq9yvZhba/giphy.gif?cid=f6f3409e55ce6d8dca39c4b0f460214bea6654f8e7344cd2&rid=giphy.gif");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);*/
   // webView=findViewById(R.id.web_view);

   // WebSettings webSettings = webView.getSettings();
   // webSettings.setJavaScriptEnabled(true);

   // String url = "https://media.giphy.com/media/QIkavkylBv0Z2/giphy.gif";
    //webView.loadUrl(url);


        mainController = new MainController(
                this,
                new GsonBuilder()
                        .setLenient()
                        .create(),
                getSharedPreferences("application_giphy", Context.MODE_PRIVATE)
        );
        mainController.onStart();




    }



    public void showList(List<Giphy> giphyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this,2);
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




    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }
}
