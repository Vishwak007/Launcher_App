package com.example.launcher_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListOfActivity extends AppCompatActivity implements View.OnDragListener {
    private PackageManager packageManager;
    private List<Item> appInfolist;
    GridView gridView;
    GridAdapter adapter;
    int posi = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of);
        loadAppData();
        loadGridView();
        addClickListener();
    }

    private void addClickListener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent goToApp = packageManager.getLaunchIntentForPackage(appInfolist.get(i).getLabel().toString());
                startActivity(goToApp);
            }
        });
        RelativeLayout parent = findViewById(R.id.main_cont);
        parent.setOnDragListener(this);
//        gridView.setOnDragListener(this);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int[] coords = {0,0};
                view.getLocationOnScreen(coords);
                int absoluteTop = coords[1];
                int absoluteBottom = coords[1] + view.getHeight();
//                Toast.makeText(getApplicationContext(),Integer.toString(absoluteTop)+","+Integer.toString(absoluteBottom), Toast.LENGTH_SHORT).show();
                posi = position;
//                CharSequence pos =  Integer.toString(position);
//                ClipData clipData = new ClipData(pos, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, new ClipData.Item(pos));

                view.startDragAndDrop(null, new View.DragShadowBuilder(view), view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });
    }

    private void loadGridView() {
        gridView = findViewById(R.id.grid_view);
        adapter = new GridAdapter(getApplicationContext(), R.layout.item, appInfolist);
        gridView.setAdapter(adapter);
    }

    private void loadAppData() {
        packageManager = getPackageManager();
        appInfolist = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableAppInfo = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo it:availableAppInfo){
            String appLabel = it.activityInfo.packageName;
            CharSequence appName = it.loadLabel(packageManager);
            Drawable icon = it.loadIcon(packageManager);
            Item app = new Item(appLabel, appName, icon);
            appInfolist.add(app);
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_STARTED:{
//                Toast.makeText(this, "ACTION_DRAG_STARTED", Toast.LENGTH_SHORT).show();
                return true;
            }
            case DragEvent.ACTION_DRAG_ENTERED:{
                v.invalidate();
//                Toast.makeText(this, "ACTION_DRAG_ENTERED", Toast.LENGTH_SHORT).show();
                return true;
            }
            case DragEvent.ACTION_DRAG_LOCATION:{
//                Toast.makeText(this, "ACTION_DRAG_LOCATION"+ event.getY(), Toast.LENGTH_SHORT).show();
                return true;
            }
            case DragEvent.ACTION_DRAG_EXITED:{
                v.invalidate();
//                Toast.makeText(this, "ACTION_DRAG_EXITED", Toast.LENGTH_SHORT).show();
                return true;
            }
            case DragEvent.ACTION_DROP:{
//                Toast.makeText(this, "ACTION_DROP", Toast.LENGTH_SHORT).show();
                if (posi != -1){

                    Item temp = appInfolist.get(posi);
                    float itemSize = 230;
                    float y = event.getY();
                    float val = (y/itemSize);
                    if (posi % 2 == 0){
                        if (event.getX() < 540){
                            val = val * 2;
                            val = val-1;
                        }else{
                            val = val * 2;
                        }
                    }else{
                        if (event.getX() < 540){
                            val = val * 2;
                            val = val-1;
                        }else{
                            val = val * 2;

                        }
                    }
                    int newPos = Math.round(val) % appInfolist.size();
                    appInfolist.set(posi, appInfolist.get(newPos));
                    appInfolist.set(newPos, temp);
                    adapter.notifyDataSetChanged();
//                    Toast.makeText(this, "POs changed", Toast.LENGTH_SHORT).show();
                    v.setVisibility(View.VISIBLE);
                    View view = (View) event.getLocalState();
                    view.setVisibility(View.VISIBLE);
                }

                return true;


            }
            case  DragEvent.ACTION_DRAG_ENDED:{
                v.invalidate();
//                Toast.makeText(this, "ACTION_DRAG_ENDED", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:return false;
        }
    }
}