package com.example.messagechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messagechat.Fragment.AccountFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateInfo extends AppCompatActivity {
    SharedPreferences sharedpreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        EditText emailCl = findViewById(R.id.txtEmail);
        EditText fullnameCl = findViewById(R.id.txtFullname);
        EditText phoneCl = findViewById(R.id.txtPhone);
        Button btnSave = findViewById(R.id.btnSave);

        SharedPreferences shared = UpdateInfo.this.getSharedPreferences("SESSION", MODE_PRIVATE);
        sharedpreferences = getSharedPreferences("SESSION", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String fullname = intent.getStringExtra("fullname");
        String phone = intent.getStringExtra("phone");

        String id = (shared.getString("id", null));

        Log.d(id, "ididididididid");

        fullnameCl.setText(fullname);
        emailCl.setText(email);
        phoneCl.setText(phone);
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DocumentReference documentReference = FirebaseFirestore.getInstance()
                        .collection("Users")
                        .document(id);
                Map<String, Object> map = new HashMap<>();
                map.put("fullname", fullnameCl.getText().toString());
                map.put("email", emailCl.getText().toString());
                map.put("phone", phoneCl.getText().toString());
                documentReference.update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateInfo.this, "Cập nhật database thành công",
                                Toast.LENGTH_LONG).show();
                    }
                });

                //Update SharedPreferences
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("email", emailCl.getText().toString());
                editor.putString("fullname", fullnameCl.getText().toString());
                editor.putString("phone", phoneCl.getText().toString());
                editor.apply();

//                SharedPreferences shared = getSharedPreferences("SESSION", MODE_PRIVATE);
//                String aaaaa = (shared.getString("fullname", null));
//
//                Toast.makeText(UpdateInfo.this, aaaaa,
//                        Toast.LENGTH_LONG).show();

                //Send data from activity to fragment
                AccountFragment fragobj = new AccountFragment();
                Bundle bundle = new Bundle();
                bundle.putString("fullname", fullnameCl.getText().toString());
                bundle.putString("email", emailCl.getText().toString());
                bundle.putString("phone", phoneCl.getText().toString());
                fragobj.setArguments(bundle);
                fragmentTransaction.add(R.id.myLineatLayot, fragobj);
                //fragmentTransaction.commit();
            }
        });
    }


}