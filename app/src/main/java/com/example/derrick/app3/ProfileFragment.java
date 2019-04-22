package com.example.derrick.app3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;

public class ProfileFragment extends Fragment {

    Button btnLogout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout = v.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Toast.makeText(getActivity().getApplicationContext(), "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
