package controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import model.LostItem;

import cs2340teamandrew.wheresmystuff.R;

/**
 * Activity that handles the lost items page
 * @author teamAndrew
 * @version 1.0
 */
public class LostItemsActivity extends AppCompatActivity {

    private FloatingActionButton itemAdder;
    private ListView lostList;
    //private ArrayList<LostItems> daList = new ArrayList<LostItems>();
    private Button back;
    //private ArrayList<String> showitems = new ArrayList<String>();
    private ArrayList<LostItem> daList = new ArrayList<LostItem>();
    private ItemAdapter adapter;
    private DatabaseReference ref;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference foundRef;
    private SearchView searchView;
    private MapsActivity map = new MapsActivity();
    //Location
    LatLng latLng;
    /**
     * class of ItemAdapter that holds and display an array of LostItem
     */
    private class ItemAdapter extends ArrayAdapter<LostItem> {
        ItemAdapter(Context context, List<LostItem> list) {
            super(context,0,list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LostItem lost = getItem(position);
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

    /**
     * Create/add a LostItem to an arrayList of lostItems
     * @param dataSnapshot  input data of the lost item
     */
    private void createList(DataSnapshot dataSnapshot) {
        if (daList.isEmpty()) {
            LostItem item = new LostItem();
            item.setName(dataSnapshot.getValue(LostItem.class).getName());
            item.setDescription(dataSnapshot.getValue(LostItem.class).getDescription());
            item.setKey(dataSnapshot.getValue(LostItem.class).getKey());
            item.setUserName(dataSnapshot.getValue(LostItem.class).getUserName());
            daList.add(item);
            adapter.notifyDataSetChanged();
        } else {
            LostItem item = new LostItem();
            item.setName(dataSnapshot.getValue(LostItem.class).getName());
            item.setDescription(dataSnapshot.getValue(LostItem.class).getDescription());
            item.setKey(dataSnapshot.getValue(LostItem.class).getKey());
            item.setUserName(dataSnapshot.getValue(LostItem.class).getUserName());
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
        foundRef = FirebaseDatabase.getInstance().getReference().child("Founditems");

        //sets listview
        lostList = (ListView) findViewById(R.id.LostItemList);
        lostList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                final LostItem item = (LostItem) adapterView.getItemAtPosition(position);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LostItemsActivity.this);
                alertDialog.setTitle("A lost item");
                alertDialog.setMessage(item.getName() + "\n" + item.getDescription()
                    + "\n" + item.getUserName() + " is looking for this item!");
                alertDialog.setPositiveButton("Claim it!",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                foundRef.child(item.getKey()).setValue(item);
                                ref.child(item.getKey()).removeValue();
                                daList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                    }

                );

                alertDialog.setNegativeButton("Cancel", null);
                alertDialog.show();
            }
        });

        //sets logout
        back = (Button) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //makes adapter
        adapter = new ItemAdapter(getApplicationContext(), daList);

        //sets adapter
        lostList.setAdapter(adapter);

        // search
        searchView = (SearchView) findViewById(R.id.search_bar_lostItems);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){


            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean foundItem = false;
                for(int i=0; i < daList.size(); i++) {
                    final LostItem item = daList.get(i);
                    final int position = i;
                    if (item.getName().equals(query)) {
                        Log.d("LostItemsActivity", "found item");
                        foundItem = true;

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LostItemsActivity.this);
                        alertDialog.setTitle("A lost item");
                        alertDialog.setMessage(item.getName() + "\n" + item.getDescription()
                                + "\n" + item.getUserName() + " is looking for this item!");
                        alertDialog.setPositiveButton("Claim it!",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        foundRef.child(item.getKey()).setValue(item);
                                        ref.child(item.getKey()).removeValue();
                                        daList.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                }

                        );

                        alertDialog.setNegativeButton("Cancel", null);
                        alertDialog.show();

                    }
                }

                if(!foundItem) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LostItemsActivity.this);
                    alertDialog.setTitle("Search results");
                    alertDialog.setMessage("Item not found!");
                    alertDialog.create();
                    alertDialog.show();
                }

                Log.d("LostItemsActivity", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d("LostItemsActivity", newText);
                return false;
            }
        });



        //Firebase listeners
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                createList(dataSnapshot);
                //Toast.makeText(getApplicationContext(), ""  + lat, Toast.LENGTH_LONG).show();
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
