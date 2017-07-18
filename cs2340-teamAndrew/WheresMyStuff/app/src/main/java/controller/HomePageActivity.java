package controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Map;

import cs2340teamandrew.wheresmystuff.R;

/**
 * activity that handles the home page
 * @author teamAndrew
 * @version 1.0
 */
public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button LostItemsButton = (Button) findViewById(R.id.LostItems);
        LostItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LostItemsActivity.class);
                startActivity(intent);
            }
        });

        Button FoundItemsButton = (Button) findViewById(R.id.FoundItems);
        FoundItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FoundItemsActivity.class);
                startActivity(intent);
            }
        });

        Button AddItemButton = (Button) findViewById(R.id.AddItem);
        AddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        Button MapScreenButton = (Button) findViewById(R.id.MapScreen);
        MapScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }


}
