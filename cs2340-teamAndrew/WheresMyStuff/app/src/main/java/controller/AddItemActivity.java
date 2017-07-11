package controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import cs2340teamandrew.wheresmystuff.R;
import model.Item;
import model.LostItem;

/**
 * A login screen that offers login via email/password.
 */
public class AddItemActivity extends AppCompatActivity {

    // UI references.
    private EditText mName;
    private EditText mDescription;
    private Button mCancel;
    private Button mAdd;
    private LocationManager locationManager;

    private DatabaseReference mMyRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private Location location; // location
    private double latitude; // latitude
    private double longitude; // longitude
    private LocationManager mLocationManager;
    /*private LocationManager lm = (LocationManager)getSystemService(getApplicationContext().LOCATION_SERVICE);
    private Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    private double latitude = location.getLatitude();
    private double longitude = location.getLongitude();
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
*/private boolean checkWriteExternalPermission()
    {

        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(getApplicationContext(), "got here", Toast.LENGTH_LONG).show();
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        // Set up the login form.
        mName = (EditText) findViewById(R.id.NameOfItem);

        mDescription = (EditText) findViewById(R.id.ItemDescription);

        mCancel = (Button) findViewById(R.id.CancelItemList);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ActivityCompat.requestPermissions(AddItemActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                123);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        mAdd = (Button) findViewById(R.id.ListItemButton);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mName.getText().toString();
                final Editable description = mDescription.getText();
                //LostItems item = new LostItems(name,description.toString());
                String key = mMyRef.child("Lostitems").push().getKey();
                String userName = user.getEmail();
                LostItem item = new LostItem(name, description.toString(), key, userName);
                //mMyRef.child("Lostitems").child(user.getUid()).child(key).setValue(item);
                mMyRef.child("Lostitems").child(key).setValue(item);
                if (checkWriteExternalPermission()) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
                }
                //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
                //Toast.makeText(getApplicationContext(), "got here", Toast.LENGTH_LONG).show();
                mMyRef.child("Lostitems").child(key).child("Location").child("Latitude").setValue(latitude);
                mMyRef.child("Lostitems").child(key).child("Location").child("Longitude").setValue(longitude);
                finish();
            }
        });


    }
}


