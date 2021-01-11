package com.example.messagechat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private EditText edtEmail, editPassword, edtPhone, edtFullname;
    private FirebaseAuth mAuth;
    private TextView isHaveAccount;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        edtEmail = findViewById(R.id.txtEmail);
        editPassword = findViewById(R.id.txtPwd);
        edtFullname = findViewById(R.id.txtFullname);
        edtPhone = findViewById(R.id.txtPhone);
        isHaveAccount = findViewById(R.id.haveAccount);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String fullname = edtFullname.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();

                if (email.isEmpty()) {
                    edtEmail.setError("Email is required");
                    return;
                }
                if (password.isEmpty()) {
                    edtEmail.setError("Password is required");
                    return;
                }

                if (fullname.isEmpty()) {
                    edtEmail.setError("Fullname is required");
                    return;
                }
                if (phone.isEmpty()) {
                    edtEmail.setError("Phone is required");
                    return;
                }

                Map<String, Object> data = new HashMap<>();
                data.put("email", email);
                data.put("password", password);
                data.put("fullname", fullname);
                data.put("phone", phone);

                db.collection("Users")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterActivity.this, "Register successed",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Register failed",
                                Toast.LENGTH_LONG).show();
                    }
                });

//                db.collection("Users")
//                .whereEqualTo("email", email)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(RegisterActivity.this, "aaaaaaaaaaaa",
//                                    Toast.LENGTH_LONG).show();
//                        } else {
//
//
//                        }
//                    }
//                });

            }
        });

        isHaveAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
