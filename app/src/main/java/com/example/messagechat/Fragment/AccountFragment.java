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
import com.example.messagechat.UpdateInfo;

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

    private TextView fullname, fullnameTitle;
    private TextView email;
    private TextView phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView btnLogout = (TextView) view.findViewById(R.id.logout);
        Button btnUpdate = (Button) view.findViewById(R.id.btnUpdateBasic);
        SharedPreferences shared = this.getActivity().getSharedPreferences("SESSION", MODE_PRIVATE);
        fullnameDB = (shared.getString("fullname", null));
        emailDB = (shared.getString("email", null));
        phoneDB = (shared.getString("phone", null));

        Log.d(fullnameDB, "fullnameDB");
        Log.d(emailDB, "emailDB");
        Log.d(phoneDB, "phoneDB");
        if (fullnameDB != null)
        {
            fullname = view.findViewById(R.id.namedt);
            fullnameTitle = view.findViewById(R.id.fullname);
            email = view.findViewById(R.id.emaildt);
            phone = view.findViewById(R.id.phonedt);

            //Set value
            fullname.setText(fullnameDB);
            fullnameTitle.setText(fullnameDB);
            email.setText(emailDB);
            phone.setText(phoneDB);

//            Intent intent = getActivity().getIntent();
//            String emailNew = intent.getStringExtra("emailBundel");
//            String fullnameNew = intent.getStringExtra("fullnameBundel");
//            String phoneNew = intent.getStringExtra("phoneBundel");
//
//            if(fullnameNew.length() > 0){
//                Log.d(fullnameNew, "fullnameNew: ");
//                Log.d(emailNew, "emailNew: ");
//                Log.d(phoneNew, "phoneNew: ");
//
//                fullname.setText(fullnameNew);
//                fullnameTitle.setText(fullnameNew);
//                email.setText(emailNew);
//                phone.setText(phoneNew);
//            }
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateInfo.class);
                //intent.putExtra("email", emailDB);
                intent.putExtra("fullname", fullnameDB);
                intent.putExtra("email", emailDB);
                intent.putExtra("phone", phoneDB);
                //intent.putExtra("phone", phoneDB);
                startActivity(intent);
            }
        });
        return view;
    }
}