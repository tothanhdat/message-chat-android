package com.example.messagechat.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.messagechat.ChatActivity;
import com.example.messagechat.LoginActivity;
import com.example.messagechat.R;
import static android.content.Context.MODE_PRIVATE;

public class MessageFragment extends Fragment {
    String fullname;
    SharedPreferences sharedpreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        SharedPreferences shared = this.getActivity().getSharedPreferences("SESSION", MODE_PRIVATE);

        fullname = (shared.getString("fullname", null));
        Button moveActivityChat = view.findViewById(R.id.btnOpen);
        moveActivityChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullname.length() > 0){
                    startActivity(new Intent(getActivity(), ChatActivity.class));
                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

                //Intent intent = new Intent(getActivity(), ChatActivity.class);
                //in.putExtra("some", "somedata");
                //startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}