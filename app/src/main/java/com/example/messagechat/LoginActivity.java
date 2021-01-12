package com.example.messagechat;

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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtEmail, editPassword;
    private TextView btnRegisterNow;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences sharedpreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.txtEmail);
        editPassword = findViewById(R.id.txtPwd);
        btnRegisterNow = findViewById(R.id.btnRegisterNow);
        sharedpreferences = getSharedPreferences("SESSION", Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                if (email.isEmpty()) {
                    edtEmail.setError("Email is required");
                }
                if (password.isEmpty()) {
                    edtEmail.setError("Password is required");
                }

                Map<String, Object> data = new HashMap<>();
                data.put("email", email);
                data.put("password", password);

                db.collection("Users")
                        .whereEqualTo("email", email)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (email.equals(document.getData().get("email").toString())
                                                && password.equals(document.getData().get("password").toString())) {
                                            //Lưu email, id vào session
                                            String emailDb = document.getData().get("email").toString();
                                            String fullnameDb = document.getData().get("fullname").toString();
                                            String phoneDb = document.getData().get("phone").toString();
                                            String idDb = document.getId();

                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString("email", emailDb);
                                            editor.putString("fullname", fullnameDb);
                                            editor.putString("phone", phoneDb);
                                            editor.putString("id", idDb);

                                            Log.d(emailDb, "emailDb:");
                                            Log.d(fullnameDb, "fullnameDb:");
                                            Log.d(phoneDb, "phoneDb:");

                                            //editor.putString("_id", document.getId());
                                            editor.apply();
                                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công",
                                                    Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Thất bại",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Thất bại",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
