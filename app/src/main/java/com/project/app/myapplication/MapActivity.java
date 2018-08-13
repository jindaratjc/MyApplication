package com.project.app.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.project.app.database.DatabaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 30/1/2561.
 */

public class MapActivity extends Activity {


    private DatabaseActivity myDb = new DatabaseActivity(this);
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();

    Button imgMap;
    String map_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle extras = getIntent().getExtras();
        // เช็คว่ามีค่าที่ส่งมาจากหน้าอื่นหรือไม่ถ้ามีจะไม่เท่ากับ null
        if (extras != null) {
            map_img = extras.getString("map_img");
        }
        imgMap = (Button) findViewById(R.id.imgMap);

        if(!"".equals(map_img) && map_img != null){
            Resources res = getResources();
            String mDrawableName = map_img;
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            //Drawable drawable = res.getDrawable(resID);
            imgMap.setBackgroundResource(resID);
        }
    }
}
