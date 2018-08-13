package com.project.app.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PointActivity extends Activity {

    EditText txtStartPoint, txtEndPoint;
    Button btnSearchStartPoint,btnSearchEndPoint,btnSearch;
    String substore_id, substore_name, substore_image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        Bundle extras = getIntent().getExtras();
        // เช็คว่ามีค่าที่ส่งมาจากหน้าอื่นหรือไม่ถ้ามีจะไม่เท่ากับ null
        if (extras != null) {
            substore_id = extras.getString("substore_id");
            substore_name = extras.getString("substore_name");
            substore_image = extras.getString("substore_image");
        }

        txtStartPoint = (EditText) findViewById(R.id.txtStartPoint);
        txtEndPoint = (EditText) findViewById(R.id.txtEndPoint);

        btnSearchStartPoint = (Button) findViewById(R.id.btnSearchStartPoint);
        btnSearchEndPoint = (Button) findViewById(R.id.btnSearchEndPoint);
        btnSearch = (Button) findViewById(R.id.btnSearch);

    }

}

