package com.project.app.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class StoreActivity extends Activity {


    private DatabaseActivity myDb = new DatabaseActivity(this);
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();

    TextView txtStoreName;
    ListView listStore;
    Button btnBack, btnHome;
    String store_id, store_name, store_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Bundle extras = getIntent().getExtras();
        // เช็คว่ามีค่าที่ส่งมาจากหน้าอื่นหรือไม่ถ้ามีจะไม่เท่ากับ null
        if (extras != null) {
            store_id = extras.getString("store_id");
            store_name = extras.getString("store_name");
            store_image = extras.getString("store_image");
        }

        txtStoreName = (TextView) findViewById(R.id.txtStoreName);
        listStore = (ListView) findViewById(R.id.listStore);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnHome = (Button) findViewById(R.id.btnHome);

        txtStoreName.setText("ชื่อร้านค้า : " + store_name);

        MyArrList = myDb.GetSubStoreById(store_id);


        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(getBaseContext(), MyArrList, R.layout.column_store,
                new String[]{"substore_name"}, new int[]{R.id.ColName});
        //listStore.setAdapter(sAdap);
        listStore.setAdapter(new ImageAdapter(this, MyArrList));

        listStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //top_left, top_right, bottom_left, bottom_right;
                Intent intent = new Intent(getBaseContext(), SubStoreActivity.class);
                intent.putExtra("substore_name", MyArrList.get(i).get("substore_name"));
                intent.putExtra("substore_id", MyArrList.get(i).get("substore_id"));
                intent.putExtra("substore_image", MyArrList.get(i).get("substore_image"));
                intent.putExtra("top_left", MyArrList.get(i).get("top_left"));
                intent.putExtra("top_right", MyArrList.get(i).get("top_right"));
                intent.putExtra("bottom_left", MyArrList.get(i).get("bottom_left"));
                intent.putExtra("bottom_right", MyArrList.get(i).get("bottom_right"));
               // intent.putExtra("store_image", store_image);
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

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<HashMap<String, String>> MyArr = new ArrayList<HashMap<String, String>>();

        public ImageAdapter(Context c, ArrayList<HashMap<String, String>> list) {
            // TODO Auto-generated method stub
            context = c;
            MyArr = list;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return MyArr.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.column_store, null);
            }

            // ColImage
            ImageView imageView = (ImageView) convertView.findViewById(R.id.ColImage);
            imageView.getLayoutParams().height = 100;
            imageView.getLayoutParams().width = 100;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int ResID = context.getResources().getIdentifier(store_image, "drawable", context.getPackageName());
            imageView.setImageResource(ResID);

            // ColPosition
/*
            TextView txtPosition = (TextView) convertView.findViewById(R.id.ColImage);
            txtPosition.setPadding(10, 0, 0, 0);
            txtPosition.setText("ID : " + MyArr.get(position).get("ImageID"));
*/

            // Colname
            TextView txtName = (TextView) convertView.findViewById(R.id.ColName);
            txtName.setPadding(50, 0, 0, 0);
            txtName.setText(" " + MyArr.get(position).get("substore_name"));

//            TextView txtFloor = (TextView) convertView.findViewById(R.id.ColFloor);
//            txtFloor.setPadding(50, 0, 0, 0);
//            txtFloor.setText(" " + MyArr.get(position).get("point_name"));


            return convertView;

        }
    }

}
