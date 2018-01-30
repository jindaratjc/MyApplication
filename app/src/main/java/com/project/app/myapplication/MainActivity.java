package com.project.app.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.project.app.database.DatabaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatabaseActivity myDb = new DatabaseActivity(this);
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
    private EditText txtName;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb.openDatabase();

        txtName = (EditText) findViewById(R.id.txtName);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyArrList = myDb.GetStoreByName(txtName.getText().toString().trim());
                DialogStore(MyArrList);
            }
        });

    }

    private void DialogStore(ArrayList<HashMap<String, String>> myArrList) {
        View dialogBoxView = View.inflate(this, R.layout.dialog_store, null);

        final ListView listStore = (ListView) dialogBoxView.findViewById(R.id.listStore);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(getBaseContext(), myArrList, R.layout.column_store,
                new String[] {"store_name"}, new int[] {R.id.ColName});
        listStore.setAdapter(sAdap);

        listStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), StoreActivity.class);
                intent.putExtra("store_name", MyArrList.get(i).get("store_name"));
                intent.putExtra("store_id", MyArrList.get(i).get("store_id"));
                startActivity(intent);
            }
        });

        AlertDialog.Builder builderInOut = new AlertDialog.Builder(this);
        builderInOut.setTitle("รายชิ่อร้านค้า");
        builderInOut.setMessage("")
                .setView(dialogBoxView)
                .setCancelable(false)
       /*         .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })*/
                .setNegativeButton("ปิด",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
    }


}
