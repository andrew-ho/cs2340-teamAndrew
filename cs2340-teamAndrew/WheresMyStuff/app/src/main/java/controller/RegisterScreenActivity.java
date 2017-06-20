package controller;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import model.User;
import model.FileSave;
import model.User;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
//import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs2340teamandrew.wheresmystuff.R;

import static model.User.getUsers;

/**
 * Created by Alex on 6/20/2017.
 */

public class RegisterScreenActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    private User dummy;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //populateAutoComplete();
        getUsers();
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    //attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mExitButton = (Button) findViewById(R.id.ExitApp);
        mExitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.registration);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptRegistration();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), RegisterScreenActivity.ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {

    List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
        emails.add(cursor.getString(ProfileQuery.ADDRESS));
        cursor.moveToNext();
        }

    addEmailsToAutoComplete(emails);

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(RegisterScreenActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }
}

