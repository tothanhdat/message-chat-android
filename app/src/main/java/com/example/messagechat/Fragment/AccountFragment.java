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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends Fragment {
    //private TextView btnLogout;
    SharedPreferences sharedpreferences;
    private String fullnameDB, emailDB, phoneDB, idDB;

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

        fullname = view.findViewById(R.id.namedt);
        fullnameTitle = view.findViewById(R.id.fullname);
        email = view.findViewById(R.id.emaildt);
        phone = view.findViewById(R.id.phonedt);

        Bundle bundle = getArguments();
        if(bundle != null){
            String fullnameShared = getArguments().getString("fullname");
            String emailShared = getArguments().getString("email");
            String phoneShared = getArguments().getString("phone");

            Log.d(fullnameShared, "fullnameShared");

            fullnameTitle.setText(fullnameShared);
            fullname.setText(fullnameShared);
            email.setText(emailShared);
            phone.setText(phoneShared);
        }

        fullnameDB = (shared.getString("fullname", null));
        emailDB = (shared.getString("email", null));
        phoneDB = (shared.getString("phone", null));

        Log.d("emaillllllllllllllllll", emailDB);
        if (fullnameDB != null)
        {
//            //Set value
//            fullname.setText(fullnameDB);
//            fullnameTitle.setText(fullnameDB);
//            email.setText(emailDB);
//            phone.setText(phoneDB);
            FirebaseFirestore.getInstance()
            .collection("Users").whereEqualTo("email", emailDB)
            .get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    Log.d("TAG", "get data success");
                    List<DocumentSnapshot> snapshotsList = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot snapshot: snapshotsList){
                        Log.d("TAG", "onSuccess: " + snapshot.getData().toString());
                        fullname.setText( snapshot.getData().get("fullname").toString());
                        fullnameTitle.setText( snapshot.getData().get("fullname").toString());
                        email.setText( snapshot.getData().get("email").toString());
                        phone.setText( snapshot.getData().get("phone").toString());
                    }
                }
            });
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
                intent.putExtra("id", idDB);
                //intent.putExtra("phone", phoneDB);
                startActivity(intent);
            }
        });
        return view;
    }
}