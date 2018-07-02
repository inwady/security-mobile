package com.example.introductory.introductory.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.introductory.introductory.Auth;
import com.example.introductory.introductory.MainActivity;
import com.example.introductory.introductory.R;
import com.example.introductory.introductory.model.User;

import java.util.ArrayList;
import java.util.Collections;

public class TwitterFragment extends Fragment {
    MainActivity act;

    private EditText twitterEdit;

    ArrayList<String> twitterItems;
    private ArrayAdapter<String> twitterAdapter;
    private ListView twitterListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter, container, false);

        act = (MainActivity) getActivity();

        User user = Auth.checkUser();
        TextView greetingText = view.findViewById(R.id.textGreeting);
        greetingText.setText("Hello, " + user.getEmail());

        twitterItems = restoreTwits();
        twitterAdapter = new ArrayAdapter<String>(act,
                android.R.layout.simple_list_item_1,
                twitterItems);

        twitterListView = view.findViewById(R.id.twitterList);
        twitterListView.setAdapter(twitterAdapter);

        twitterEdit = view.findViewById(R.id.fieldTwitterText);
        Button buttonSend = view.findViewById(R.id.buttonTwitterSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String twit = twitterEdit.getText().toString();

                twitterEdit.setText("");
                addTwit(twit);
            }
        });

        return view;
    }

    public void addTwit(String twit) {
        twitterItems.add(0, twit);
        twitterAdapter.notifyDataSetChanged();
        act.saveTwitInDatabase(twit);
    }


    public ArrayList<String> restoreTwits() {
        ArrayList<String> result = new ArrayList<>();

        SQLiteDatabase db = act.twitterDatabaseHelper.getReadableDatabase();

        String[] projection = { "twit" };
        Cursor cursor = db.query("twitter",
                projection,
                null,
                null,
                null,
                null,
                null);


        try {
            int twitIndex = cursor.getColumnIndex("twit");

            while (cursor.moveToNext()) {
                result.add(cursor.getString(twitIndex));
            }
        } finally {
            db.close();
        }

        Collections.reverse(result);
        return result;
    }
}
