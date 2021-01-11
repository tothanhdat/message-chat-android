package com.example.messagechat.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messagechat.LoginActivity;
import com.example.messagechat.R;

import android.widget.TextView;

public class AccountFragment extends Fragment {
    //private TextView btnLogout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView btnLogout = (TextView) view.findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //in.putExtra("some", "somedata");
                startActivity(intent);
            }
        });
        return view;
    }
}