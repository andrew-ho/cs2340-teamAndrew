package controller;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
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
    //private ArrayList<String> showitems = new ArrayList<String>();
    private ArrayList<LostItems> daList = new ArrayList<LostItems>();
    private ItemAdapter adapter;
    private DatabaseReference ref;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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

    private void createList(DataSnapshot dataSnapshot) {
        if (daList.isEmpty()) {
            LostItems item = new LostItems();
            item.setName(dataSnapshot.getValue(LostItems.class).getName());
            item.setDescription(dataSnapshot.getValue(LostItems.class).getDescription());
            item.setKey(dataSnapshot.getValue(LostItems.class).getKey());
            item.setUserName(dataSnapshot.getValue(LostItems.class).getUserName());
            daList.add(item);
            adapter.notifyDataSetChanged();
        } else {
            LostItems item = new LostItems();
            item.setName(dataSnapshot.getValue(LostItems.class).getName());
            item.setDescription(dataSnapshot.getValue(LostItems.class).getDescription());
            item.setKey(dataSnapshot.getValue(LostItems.class).getKey());
            item.setUserName(dataSnapshot.getValue(LostItems.class).getUserName());
            daList.add(item);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item);

        //get user data
        //ref = FirebaseDatabase.getInstance().getReference().child("Lostitems").child(user.getUid());
        ref = FirebaseDatabase.getInstance().getReference().child("Lostitems");

        //sets listview
        lostList = (ListView) findViewById(R.id.LostItemList);
        lostList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*// Get selected item text
                LostItems item = (LostItems) adapterView.getItemAtPosition(i);
                //ref.getKey();
                // Display the selected item
                Toast.makeText(getApplicationContext(),"Selected : " + item,Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), item.getKey(),Toast.LENGTH_SHORT).show();
                ref.child(item.getKey()).removeValue();
                daList.remove(i);
                adapter.notifyDataSetChanged();*/
                LostItems item = (LostItems) adapterView.getItemAtPosition(i);
                AlertDialog alertDialog = new AlertDialog.Builder(LostItemsActivity.this).create();
                alertDialog.setTitle("A lost item");
                alertDialog.setMessage(item.getName() + "\n" + item.getDescription()
                    + "\n" + item.getUserName() + " is looking for this item!");
                alertDialog.show();
            }
        });
        //sets logout
        logout = (FloatingActionButton) findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //adds item
        itemAdder = (FloatingActionButton) findViewById(R.id.AddLostItem);
        itemAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });
        //makes adapter
        adapter = new ItemAdapter(getApplicationContext(), daList);
        //sets adapter
        lostList.setAdapter(adapter);

        //Firebase listeners
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                createList(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
