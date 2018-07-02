package com.example.introductory.introductory.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.introductory.introductory.MainActivity;
import com.example.introductory.introductory.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SendFragment extends Fragment {
    MainActivity act;

    private TextView uriView;

    private Uri filePath;
    public static SendFragment newInstance(String uriFile) {
        SendFragment myFragment = new SendFragment();

        Bundle args = new Bundle();
        args.putString("fileUri", uriFile);
        myFragment.setArguments(args);

        return myFragment;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        fin.close();
        return ret;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);

        act = (MainActivity) getActivity();

        filePath = Uri.parse(getArguments().getString("fileUri"));

        uriView = view.findViewById(R.id.textSend);
        uriView.setText(filePath.getLastPathSegment());

        Button signIn = view.findViewById(R.id.buttonFileSend);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String twit = null;

                try {
                    String path = filePath.getPath();
                    Log.d("app", path);
                    twit = getStringFromFile(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                act.saveTwitInDatabase(twit);
                act.replaceFragment(new TwitterFragment());
            }
        });

        return view;
    }
}
