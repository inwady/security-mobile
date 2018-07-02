package com.example.introductory.introductory.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.introductory.introductory.Auth;
import com.example.introductory.introductory.MainActivity;
import com.example.introductory.introductory.R;
import com.example.introductory.introductory.model.User;

public class LoginFragment extends Fragment {
    MainActivity act;

    EditText email, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        act = (MainActivity) getActivity();

        email = view.findViewById(R.id.fieldEmail);
        password = view.findViewById(R.id.fieldPassword);

        Button signIn = view.findViewById(R.id.button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(email.getText().toString(), password.getText().toString());
                if (!Auth.authUser(user)) {
                    Toast.makeText(getContext(), "server error", Toast.LENGTH_SHORT).show();
                    return;
                }

                act.replaceFragment(new TwitterFragment());
            }
        });

        return view;
    }
}
