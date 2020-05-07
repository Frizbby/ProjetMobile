package com.esiea.projetmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esiea.projetmobile.R;
import com.esiea.projetmobile.Singletons;
import com.esiea.projetmobile.model.Giphy;
import com.facebook.drawee.backends.pipeline.Fresco;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String giphyJson = intent.getStringExtra("giphyKey"); //if it's a string you stored.
       Giphy giphy = Singletons.getGson().fromJson(giphyJson, Giphy.class);
       showDetail(giphy);
    }

    private void showDetail(Giphy giphy) {
        txtDetail.setText(giphy.getTitle());
    }

}
