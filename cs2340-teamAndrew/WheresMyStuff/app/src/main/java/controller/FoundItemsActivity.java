package controller;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cs2340teamandrew.wheresmystuff.R;
import model.FoundItem;

/**
 * activity that handles the found item page
 * @author team11
 * @version 1.0
 */
public class FoundItemsActivity extends AppCompatActivity {

    private FloatingActionButton itemAdder;
    private ListView foundList;
    private Button back;
    private ArrayList<FoundItem> daList = new ArrayList<>();
    private ItemAdapter adapter;
    private DatabaseReference ref;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private SearchView searchView;

    /**
     * class of ItemAdapter that holds and display an array of FoundItem
     */
    class ItemAdapter extends ArrayAdapter<FoundItem> {
        ItemAdapter(Context context, List<FoundItem> list) {
            super(context,0,list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FoundItem found = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.lost_item, parent, false);
            }

            TextView itemName = (TextView) convertView.findViewById(R.id.item_name);
            ImageView itemImage = (ImageView) convertView.findViewById(R.id.item_picture);

            itemName.setText(found.getName());
            itemImage.setImageResource(R.mipmap.default_image);
            return convertView;
        }
    }

    /**
     * Create/add a found item to an array list
     * @param dataSnapshot  found item data
     */
    private void createList(DataSnapshot dataSnapshot) {
        if (daList.isEmpty()) {
            FoundItem item = new FoundItem();
            item.setName(dataSnapshot.getValue(FoundItem.class).getName());
            item.setDescription(dataSnapshot.getValue(FoundItem.class).getDescription());
            item.setKey(dataSnapshot.getValue(FoundItem.class).getKey());
            item.setUserName(dataSnapshot.getValue(FoundItem.class).getUserName());
            daList.add(item);
            adapter.notifyDataSetChanged();
        } else {
            FoundItem item = new FoundItem();
            item.setName(dataSnapshot.getValue(FoundItem.class).getName());
            item.setDescription(dataSnapshot.getValue(FoundItem.class).getDescription());
            item.setKey(dataSnapshot.getValue(FoundItem.class).getKey());
            item.setUserName(dataSnapshot.getValue(FoundItem.class).getUserName());
            daList.add(item);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_items);

        //get user data
        //ref = FirebaseDatabase.getInstance().getReference().child("Lostitems").child(user.getUid());
        ref = FirebaseDatabase.getInstance().getReference().child("Founditems");

        //sets listview
        foundList = (ListView) findViewById(R.id.FoundItemList);
        foundList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                FoundItem item = (FoundItem) adapterView.getItemAtPosition(i);
                AlertDialog alertDialog = new AlertDialog.Builder(FoundItemsActivity.this).create();
                alertDialog.setTitle("A found item");
                alertDialog.setMessage(item.getName() + "\n" + item.getDescription()
                    + "\n" + item.getUserName() + " has found this item!");
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
        foundList.setAdapter(adapter);


        // search through found items
        searchView = (SearchView) findViewById(R.id.search_bar_foundItems);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){


            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean foundItem = false;
                for(int i=0; i<daList.size(); i++) {
                    final FoundItem item = daList.get(i);
                    if (item.getName().equals(query)) {
                        Log.d("FoundItemActivity", "found item");
                        foundItem = true;

                        AlertDialog alertDialog = new AlertDialog.Builder(FoundItemsActivity.this).create();
                        alertDialog.setTitle("A found item");
                        alertDialog.setMessage(item.getName() + "\n" + item.getDescription()
                                + "\n" + item.getUserName() + " has found this item!");
                        alertDialog.show();

                    }
                }

                if(!foundItem) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoundItemsActivity.this);
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
