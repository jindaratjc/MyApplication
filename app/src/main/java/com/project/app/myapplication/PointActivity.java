package com.project.app.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;

import com.project.app.database.DatabaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class PointActivity extends Activity {

    private DatabaseActivity myDb = new DatabaseActivity(this);
    AutoCompleteTextView txtStartPoint, txtEndPoint;
    Button btnSearch;
    String substore_id, substore_name, substore_image, s_substore_id, e_substore_id;
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> MapArrList = new ArrayList<HashMap<String, String>>();

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

        txtStartPoint = (AutoCompleteTextView) findViewById(R.id.txtStartPoint);
        txtEndPoint = (AutoCompleteTextView) findViewById(R.id.txtEndPoint);


        btnSearch = (Button) findViewById(R.id.btnSearch);

        MyArrList = myDb.GetSubStoreAll();
        String[] listSubStore = new String[MyArrList.size()];
        for (int i = 0; i < MyArrList.size(); i++) {
            listSubStore[i] = MyArrList.get(i).get("substore_name");
        }
        ArrayAdapter sAdap = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listSubStore);
        txtStartPoint.setAdapter(sAdap);
        txtEndPoint.setAdapter(sAdap);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startPoint = txtStartPoint.getText().toString().trim();
                String endPoint = txtEndPoint.getText().toString().trim();
                if (!"".equals(startPoint) && !"".equals(endPoint)) {
                    s_substore_id = "";
                    e_substore_id = "";
                    for (int i = 0; i < MyArrList.size(); i++) {
                        if (startPoint.equals(MyArrList.get(i).get("substore_name"))) {
                            s_substore_id = MyArrList.get(i).get("subpoint_id");
                        }
                        ;
                        if (endPoint.equals(MyArrList.get(i).get("substore_name"))) {
                            e_substore_id = MyArrList.get(i).get("subpoint_id");
                        }
                        ;
                    }
                    if (!"".equals(s_substore_id) && !"".equals(e_substore_id)) {
                        String map_id = s_substore_id + "-" + e_substore_id;
                        MapArrList = myDb.GetMapByMapID(map_id);
                        if(MapArrList != null && MapArrList.size() > 0){
                            Intent intent = new Intent(getBaseContext(), MapActivity.class);
                            intent.putExtra("map_img", MapArrList.get(0).get("map_img"));
                            startActivity(intent);
                        }
                        else {
                            AlertDialogMsg();
                        }
                    } else {
                        AlertDialogMsg();
                    }
                } else {
                    AlertDialogMsg();
                }
            }
        });
    }

    private void AlertDialogMsg() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getBaseContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getBaseContext());
        }
        builder.setTitle("แจ้งเตือน")
                .setMessage("กรุณาระบุข้อมูลให้ครบ!!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                /* .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         // do nothing
                     }
                 })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}

