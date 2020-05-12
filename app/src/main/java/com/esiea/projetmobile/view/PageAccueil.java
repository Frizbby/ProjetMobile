package com.esiea.projetmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.esiea.projetmobile.R;
import com.esiea.projetmobile.Singletons;
import com.esiea.projetmobile.model.Giphy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;


public class PageAccueil extends AppCompatActivity{

    public SimpleDraweeView mSimpleDraweeView;
    public SimpleDraweeView mSimpleDraweeViewtwo;
    private Button btnmove;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.accueil_page);

        btnmove = findViewById(R.id.button_accueil);

        btnmove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               navigateToMainActivity();
            }
        });

        mSimpleDraweeView = findViewById(R.id.my_image_view_accueil);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri("https://media.giphy.com/media/l0MYC0LajbaPoEADu/giphy.gif")
                .setAutoPlayAnimations(true)
                .build();
        mSimpleDraweeView.setController(controller);

    }



    public void navigateToMainActivity() {
        Intent myIntent = new Intent(PageAccueil.this, MainActivity.class);
        PageAccueil.this.startActivity(myIntent);

    }

}
