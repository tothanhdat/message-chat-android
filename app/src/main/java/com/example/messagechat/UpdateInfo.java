package com.example.messagechat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.messagechat.Fragment.AccountFragment;

public class UpdateInfo extends AppCompatActivity {
    //Button btnSave = findViewById(R.id.btnSave);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        EditText emailCl = findViewById(R.id.txtEmail);
        EditText fullnameCl = findViewById(R.id.txtFullname);
        EditText phoneCl = findViewById(R.id.txtPhone);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String fullname = intent.getStringExtra("fullname");
        String phone = intent.getStringExtra("phone");

        fullnameCl.setText(fullname);
        emailCl.setText(email);
        phoneCl.setText(phone);
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(UpdateInfo.this, AccountFragment.class);
//                //intent.putExtra("email", emailDB);
//                intent.putExtra("fullnameBundel", fullnameCl.getText().toString());
//                intent.putExtra("emailBundel", emailCl.getText().toString());
//                intent.putExtra("phoneBundel", phoneCl.getText().toString());
//                startActivity(intent);
//            }
//        });
    }


}