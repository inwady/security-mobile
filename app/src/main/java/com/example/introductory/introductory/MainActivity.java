package com.example.introductory.introductory;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.introductory.introductory.database.TwitterDatabaseHelper;
import com.example.introductory.introductory.fragments.SendFragment;
import com.example.introductory.introductory.fragments.TwitterFragment;
import com.example.introductory.introductory.fragments.LoginFragment;
import com.example.introductory.introductory.model.User;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    public TwitterDatabaseHelper twitterDatabaseHelper;

    public void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twitterDatabaseHelper = new TwitterDatabaseHelper(this);

        Storage.initStorage(getApplicationContext());

        Log.i("app", "Im started");
        fragmentManager = this.getSupportFragmentManager();

        User user = Auth.checkUser();
        if (user == null) {
            replaceFragment(new LoginFragment());
            return;
        }

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null && type.startsWith("image/")) {
            String pathFile = intent.getStringExtra(Intent.EXTRA_STREAM);
            replaceFragment(SendFragment.newInstance(pathFile));
            return;
        }

        replaceFragment(new TwitterFragment());
    }

    public void saveTwitInDatabase(String twit) {
        SQLiteDatabase db = twitterDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("twit", twit);

        db.insert("twitter", null, values);
    }
}
