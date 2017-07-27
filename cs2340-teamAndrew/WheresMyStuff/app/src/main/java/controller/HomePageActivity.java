package controller;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


import cs2340teamandrew.wheresmystuff.R;

/**
 * activity that handles the home page
 * @author teamAndrew
 * @version 1.0
 */
public class HomePageActivity extends AppCompatActivity {
   private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        img = findViewById(R.id.img);
        img.post(new Runnable() {

            @Override
            public void run() {
                ((AnimationDrawable) img.getBackground()).start();
            }

        });

        img.setVisibility(View.INVISIBLE);
        final Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);
        final TranslateAnimation anim = new TranslateAnimation(-1000, 1000f, 0f, 0f);
        anim.setDuration(1200);
        img.startAnimation(animSlide);
        animSlide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                img.startAnimation(animSlide);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Button LostItemsButton = findViewById(R.id.LostItems);
        LostItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LostItemsActivity.class);
                startActivity(intent);
            }
        });

        Button FoundItemsButton = findViewById(R.id.FoundItems);
        FoundItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FoundItemsActivity.class);
                startActivity(intent);
            }
        });

        Button AddItemButton = findViewById(R.id.AddItem);
        AddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        Button MapScreenButton = findViewById(R.id.MapScreen);
        MapScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }


}
