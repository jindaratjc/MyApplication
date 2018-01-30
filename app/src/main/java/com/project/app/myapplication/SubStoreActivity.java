package com.project.app.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
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
    String substore_id, substore_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substore);

        Bundle extras = getIntent().getExtras();
        // เช็คว่ามีค่าที่ส่งมาจากหน้าอื่นหรือไม่ถ้ามีจะไม่เท่ากับ null
        if (extras != null) {
            substore_id = extras.getString("substore_id");
            substore_name = extras.getString("substore_name");
        }
    }
}
