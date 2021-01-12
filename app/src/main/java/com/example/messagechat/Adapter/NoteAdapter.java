package com.example.messagechat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.messagechat.Fragment.ListNote;
import com.example.messagechat.R;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<ListNote> {

    private Context mContext;
    private int mResource;

    public NoteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListNote> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView txtName = convertView.findViewById(R.id.nameNote);
        TextView txtMessage = convertView.findViewById(R.id.messageNote);

        txtName.setText(getItem(position).getName());
        txtMessage.setText(getItem(position).getMessage());

        return convertView;
    }
}
