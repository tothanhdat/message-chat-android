package com.example.messagechat.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messagechat.R;

import android.widget.TextView;

public class AccountFragment extends Fragment {
    //private TextView btnLogout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //btnLogout = (TextView) getView().findViewById(R.id.logout);
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
}