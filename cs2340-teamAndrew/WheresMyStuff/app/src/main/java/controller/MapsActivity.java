package controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cs2340teamandrew.wheresmystuff.R;
import model.Item;
import model.LocationItems;
import model.LostItem;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private DatabaseReference locat = FirebaseDatabase.getInstance().getReference().child("Lostitems");
    private DatabaseReference foundPos = FirebaseDatabase.getInstance().getReference().child("Founditems");
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
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        /*locat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                LostItem item = new LostItem();
                item.setKey(dataSnapshot.getValue(LostItem.class).getKey());
                //DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child("Lostitems").child(item.getKey()).child("Location");
                LocationItems locate = new LocationItems();
                //locate.setLatitude(dataSnapshot.child(dataSnapshot.getKey()).child("Location").getValue(LocationItems.class).getLatitude());
                //locate.setLongitude(dataSnapshot.child(dataSnapshot.getKey()).child("Location").getValue(LocationItems.class).getLongitude());
                //LatLng latLng = new LatLng(locate.getLatitude(), locate.getLongitude());
                //locate.setLatitude(dataSnapshot.getValue(LocationItems.class).getLatitude());
                //Marker a = mMap.addMarker(new MarkerOptions().position(latLng).title(dataSnapshot.getValue(LostItem.class).getName()));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                //a.setTag(0);
                //mMap.setOnMarkerClickListener(MapsActivity.this);
                //Toast.makeText(getApplicationContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), "" + locate.getLatitude(), Toast.LENGTH_LONG).show();
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                }
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
        });*/
        locat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    LocationItems locate = new LocationItems();
                    locate.setLatitude(data.child("Location").getValue(LocationItems.class).getLatitude());
                    locate.setLongitude(data.child("Location").getValue(LocationItems.class).getLongitude());
                    LatLng latLng = new LatLng(locate.getLatitude(), locate.getLongitude());
                    Marker a = mMap.addMarker(new MarkerOptions().position(latLng).title(data.getValue(LostItem.class).getName()));
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
                    locate.setLatitude(data.child("Location").getValue(LocationItems.class).getLatitude());
                    locate.setLongitude(data.child("Location").getValue(LocationItems.class).getLongitude());
                    LatLng latLng = new LatLng(locate.getLatitude(), locate.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(data.getValue(LostItem.class).getName()));
                    mMap.setOnMarkerClickListener(MapsActivity.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();
        marker.showInfoWindow();
        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

}
