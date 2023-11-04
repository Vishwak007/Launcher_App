package com.example.launcher_app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GridAdapter extends ArrayAdapter{
    Context context;
    List<Item> appIinfoList;
    int posi;
    public GridAdapter(@NonNull Context context, int resource,@NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.appIinfoList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        posi = position;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
        }
        ImageView imageView = convertView.findViewById(R.id.app_icon);
        imageView.setImageDrawable(appIinfoList.get(position).getIcon());
        TextView appName = convertView.findViewById(R.id.app_name);
        appName.setText(appIinfoList.get(position).getName());
        return convertView;
    }

}
