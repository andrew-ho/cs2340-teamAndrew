package controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Scanner;

import model.LostItems;

import cs2340teamandrew.wheresmystuff.R;

public class LostItemsActivity extends AppCompatActivity {

    private FloatingActionButton itemAdder;
    private ListView lostList;
    //private ArrayList<LostItems> daList = new ArrayList<LostItems>();
    private FloatingActionButton logout;
    private ArrayList<String> showitems = new ArrayList<String>();

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Lostitems");

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

    private void showItems(DataSnapshot dataSnapshot) {
        showitems.clear();
        for (DataSnapshot data: dataSnapshot.getChildren()) {
            LostItems item = new LostItems();
            item.setName(data.getValue(LostItems.class).getName());
            item.setDescription(data.getValue(LostItems.class).getDescription());

            //Toast.makeText(getApplicationContext(), item.getName() + " " + item.getDescription(), Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), data.getValue(LostItems.class).getName(), Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), data.getValue().toString(), Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), data.child(data.getKey()).getValue().toString(), Toast.LENGTH_LONG).show();

            //ArrayList<String> showitems = new ArrayList<String>();
            showitems.add(item.getName() + ": " + item.getDescription());
        }
        lostList = (ListView) findViewById(R.id.LostItemList);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showitems);
        lostList.setAdapter(adapter);
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


        ValueEventListener listener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                showItems(dataSnapshot);
            }

            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                System.out.println(databaseError.toException());
                // ...
            }
        };
        ref.addValueEventListener(listener);

        //ItemAdapter adapter = new ItemAdapter(this, daList);
        //lostList = (ListView) findViewById(R.id.LostItemList);
        //lostList.setAdapter(adapter);
        //ListView listView = (ListView) findViewById(R.id.yourOwnListView);
        //listView.setAdapter(adapter);

    }

}
