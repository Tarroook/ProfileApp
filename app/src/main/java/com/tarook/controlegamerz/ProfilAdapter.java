package com.tarook.controlegamerz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProfilAdapter extends ArrayAdapter<Profile> {

    public ProfilAdapter(Context context, List<Profile> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Profile profile = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_cell, parent, false);

        TextView name = convertView.findViewById(R.id.cellName);
        TextView address = convertView.findViewById(R.id.cellAddress);
        TextView email = convertView.findViewById(R.id.cellEmail);

        name.setText(profile.getName());
        address.setText(profile.getAddress());
        email.setText(profile.getEmail());

        return convertView;
    }
}
