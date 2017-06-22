package controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import model.LostItems;

import cs2340teamandrew.wheresmystuff.R;

public class LostItemsActivity extends AppCompatActivity {

    private FloatingActionButton itemAdder;
    private ListView lostList;
    private ArrayList<LostItems> daList = LostItems.getItems();
    private FloatingActionButton logout;

    class ItemAdapter extends ArrayAdapter<LostItems> {
        ItemAdapter(Context context, ArrayList<LostItems> list) {
            super(context,0,list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LostItems lost = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.lost_item, parent, false);
            }

            TextView itemName = (TextView) convertView.findViewById(R.id.item_name);
            ImageView itemImage = (ImageView) convertView.findViewById(R.id.item_picture);

            itemName.setText(lost.getName());
            itemImage.setImageResource(R.mipmap.default_image);
            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item);

        logout = (FloatingActionButton) findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        itemAdder = (FloatingActionButton) findViewById(R.id.AddLostItem);
        itemAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        lostList = (ListView) findViewById(R.id.LostItemList);
        ItemAdapter adapaBoi = new ItemAdapter(this,daList);
        lostList.setAdapter(adapaBoi);

    }

}
