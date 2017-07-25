package controller;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import cs2340teamandrew.wheresmystuff.R;
import model.FoundItem;
import model.Item;
import model.LocationItems;
import model.LostItem;

/**
 * Activity for the Maps Page
 * @author teamAndrew
 * @version 1.0
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private final DatabaseReference locat = FirebaseDatabase.getInstance().getReference().child("Lostitems");

    private final DatabaseReference foundPos = FirebaseDatabase.getInstance().getReference().child("Founditems");
    private final Map<Marker, Item> hash = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * @param googleMap the google map to be used
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    LocationItems locate = new LocationItems();
                    if (data.child("Location").exists()) {
                        locate.setLatitude(data.child("Location").getValue(LocationItems.class).getLatitude());
                        locate.setLongitude(data.child("Location").getValue(LocationItems.class).getLongitude());
                    }
                    LatLng latLng = new LatLng(locate.getLatitude(), locate.getLongitude());
                    LostItem item = new LostItem();
                    item.setName(data.getValue(LostItem.class).getName());
                    item.setDescription(data.getValue(LostItem.class).getDescription());
                    Marker a = mMap.addMarker(new MarkerOptions().position(latLng).title(data.getValue(LostItem.class).getName()));
                    hash.put(a, item);
                    mMap.setOnMarkerClickListener(MapsActivity.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        foundPos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    LocationItems locate = new LocationItems();
                    if (data.child("Location").exists()) {
                        locate.setLatitude(data.child("Location").getValue(LocationItems.class).getLatitude());
                        locate.setLongitude(data.child("Location").getValue(LocationItems.class).getLongitude());
                    }
                    LatLng latLng = new LatLng(locate.getLatitude(), locate.getLongitude());
                    Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(data.getValue(LostItem.class).getName()));
                    FoundItem item = new FoundItem();
                    item.setName(data.getValue(FoundItem.class).getName());
                    item.setDescription(data.getValue(FoundItem.class).getDescription());
                    //Toast.makeText(getApplicationContext(), data.getValue(FoundItem.class).getName(), Toast.LENGTH_LONG).show();
                    hash.put(marker, item);
                    mMap.setOnMarkerClickListener(MapsActivity.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /** Called when the user clicks a marker.
     *
     * @param marker marker for map
     * @return boolean true or false for marker
     * */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        Item item = hash.get(marker);
        // Retrieve the data from the marker.
        marker.showInfoWindow();
        // Check if a click count was set, then display the click count.
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        boolean found = item.getClass() == FoundItem.class;
        String msg;
        if (found) {
            msg = "Found item:\n";
        } else {
            msg = "Lost item:\n";
        }
        builder1.setMessage(msg + item.getName() + '\n' + item.getDescription() + '\n');
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Positive button",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

}