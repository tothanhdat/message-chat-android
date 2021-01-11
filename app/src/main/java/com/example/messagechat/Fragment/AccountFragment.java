package com.example.messagechat.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.messagechat.LoginActivity;
import com.example.messagechat.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends Fragment {
    //private TextView btnLogout;
    SharedPreferences sharedpreferences;
    private String fullnameDB;
    private String emailDB;
    private String phoneDB;

    private EditText fullname;
    private EditText email;
    private EditText phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView btnLogout = (TextView) view.findViewById(R.id.logout);
        SharedPreferences shared = this.getActivity().getSharedPreferences("SESSION", MODE_PRIVATE);

        fullnameDB = (shared.getString("fullname", null));
        emailDB = (shared.getString("email", null));
        phoneDB = (shared.getString("phone", null));

        Log.d(fullnameDB, "fullnameDB");
//        if (emailDB != null)
//        {
//            Log.d(fullnameDB, "fullname");
//            Log.d(emailDB, "email");
//            Log.d(phoneDB, "phone");
//
//            fullname = view.findViewById(R.id.namedt);
//            email = view.findViewById(R.id.emaildt);
//            phone = view.findViewById(R.id.phonedt);
//
//            fullname.setText(fullnameDB);
//            email.setText(emailDB);
//            phone.setText(phoneDB);
//        }

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