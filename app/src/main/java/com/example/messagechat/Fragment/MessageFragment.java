package com.example.messagechat.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.messagechat.ChatActivity;
import com.example.messagechat.R;

public class MessageFragment extends Fragment {
    Button moveActivityChat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        Button moveActivityChat = view.findViewById(R.id.btnOpen);
        moveActivityChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
                //Intent intent = new Intent(getActivity(), ChatActivity.class);
                //in.putExtra("some", "somedata");
                //startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}