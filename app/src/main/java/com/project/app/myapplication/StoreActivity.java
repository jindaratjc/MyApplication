package com.project.app.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.project.app.database.DatabaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 30/1/2561.
 */

public class StoreActivity extends Activity {


    private DatabaseActivity myDb = new DatabaseActivity(this);
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();

    TextView txtStoreName;
    ListView listSubStore;
    Button btnBack, btnHome;
    String store_id, store_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Bundle extras = getIntent().getExtras();
        // เช็คว่ามีค่าที่ส่งมาจากหน้าอื่นหรือไม่ถ้ามีจะไม่เท่ากับ null
        if (extras != null) {
            store_id = extras.getString("store_id");
            store_name = extras.getString("store_name");
        }

        txtStoreName = (TextView) findViewById(R.id.txtStoreName);
        listSubStore = (ListView) findViewById(R.id.listSubStore);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnHome = (Button) findViewById(R.id.btnHome);

        txtStoreName.setText("ชื่อร้านค้า : " + store_name);

        MyArrList = myDb.GetSubStoreById(store_id);


        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(getBaseContext(), MyArrList, R.layout.column_store,
                new String[]{"substore_name"}, new int[]{R.id.ColName});
        listSubStore.setAdapter(sAdap);

        listSubStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), SubStoreActivity.class);
                intent.putExtra("substore_name", MyArrList.get(i).get("substore_name"));
                intent.putExtra("substore_id", MyArrList.get(i).get("substore_id"));
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
