package controller;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    private DatabaseReference mMyRef = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


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
                finish();
            }
        });


    }
}


