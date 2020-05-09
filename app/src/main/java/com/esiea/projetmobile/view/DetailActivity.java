package com.esiea.projetmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esiea.projetmobile.R;
import com.esiea.projetmobile.Singletons;
import com.esiea.projetmobile.model.Giphy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    public SimpleDraweeView mSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtDetail = findViewById(R.id.detail_txt);
        mSimpleDraweeView = findViewById(R.id.my_image_view_detail);
        Intent intent = getIntent();
        String giphyJson = intent.getStringExtra("giphyKey"); //if it's a string you stored.
       Giphy giphy = Singletons.getGson().fromJson(giphyJson, Giphy.class);
       showDetail(giphy);
    }



    private void showDetail(Giphy giphy) {
        txtDetail.setText(giphy.getTitle());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(giphy.getImages().getDownsizedMedium().getUrl())
                .setAutoPlayAnimations(true)
                .build();
        mSimpleDraweeView.setController(controller);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
