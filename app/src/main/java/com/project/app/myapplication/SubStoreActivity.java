package com.project.app.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.project.app.database.DatabaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 30/1/2561.
 */

public class SubStoreActivity extends Activity {
    private DatabaseActivity myDb = new DatabaseActivity(this);
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();

    //    TextView txtStoreName;
//    ListView listSubStore;
//    Button btnBack, btnHome;
    Button imgSubStore, imgTopLeft, imgTopRight, imgButtomLeft, imgButtomRight, btnMap;
    String substore_id, substore_name, substore_image, top_left, top_right, bottom_left, bottom_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substore);

        imgSubStore = (Button) findViewById(R.id.imgSubStore);
        imgTopLeft = (Button) findViewById(R.id.imgTopLeft);
        imgTopRight = (Button) findViewById(R.id.imgTopRight);
        imgButtomLeft = (Button) findViewById(R.id.imgButtomLeft);
        imgButtomRight = (Button) findViewById(R.id.imgButtomRight);
        btnMap = (Button) findViewById(R.id.btnMap);

        Bundle extras = getIntent().getExtras();
        // เช็คว่ามีค่าที่ส่งมาจากหน้าอื่นหรือไม่ถ้ามีจะไม่เท่ากับ null
        if (extras != null) {
            //  //top_left, top_right, bottom_left, bottom_right;
            substore_id = extras.getString("substore_id");
            substore_name = extras.getString("substore_name");
            substore_image = extras.getString("substore_image");
            top_left = extras.getString("top_left");
            top_right = extras.getString("top_right");
            bottom_left = extras.getString("bottom_left");
            bottom_right = extras.getString("bottom_right");
           // store_image = extras.getString("store_image");
        }

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PointActivity.class);
                intent.putExtra("substore_name", substore_name);
                intent.putExtra("substore_id", substore_id);
                intent.putExtra("substore_image", substore_image);
                startActivity(intent);
            }
        });

        if (!"".equals(substore_image) && substore_image != null) {
            Resources res = getResources();
            String mDrawableName = substore_image;
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            //Drawable drawable = res.getDrawable(resID);
            imgSubStore.setBackgroundResource(resID);
        }
        //top_left, top_right, bottom_left, bottom_right;
        if (!"".equals(top_left) && top_left != null) {
            Resources res = getResources();
            String mDrawableName = top_left;
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            //Drawable drawable = res.getDrawable(resID);
            imgTopLeft.setBackgroundResource(resID);
        }
        if (!"".equals(top_right) && top_right != null) {
            Resources res = getResources();
            String mDrawableName = top_right;
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            //Drawable drawable = res.getDrawable(resID);
            imgTopRight.setBackgroundResource(resID);
        }
        if (!"".equals(bottom_left) && bottom_left != null) {
            Resources res = getResources();
            String mDrawableName = bottom_left;
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            //Drawable drawable = res.getDrawable(resID);
            imgButtomLeft.setBackgroundResource(resID);
        }
        if (!"".equals(bottom_right) && bottom_right != null) {
            Resources res = getResources();
            String mDrawableName = bottom_right;
            int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
            //Drawable drawable = res.getDrawable(resID);
            imgButtomRight.setBackgroundResource(resID);
        }
    }
}
