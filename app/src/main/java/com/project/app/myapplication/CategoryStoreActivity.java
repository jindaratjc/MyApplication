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

public class CategoryStoreActivity extends Activity {


    private DatabaseActivity myDb = new DatabaseActivity(this);
    ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();

    TextView txtStoreName;
    ListView listStore;
    Button btnBack, btnHome;
    String category_store_id, category_store_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Bundle extras = getIntent().getExtras();
        // เช็คว่ามีค่าที่ส่งมาจากหน้าอื่นหรือไม่ถ้ามีจะไม่เท่ากับ null
        if (extras != null) {
            category_store_id = extras.getString("category_store_id");
            category_store_name = extras.getString("category_store_name");
        }

        txtStoreName = (TextView) findViewById(R.id.txtStoreName);
        listStore = (ListView) findViewById(R.id.listStore);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnHome = (Button) findViewById(R.id.btnHome);

        txtStoreName.setText("ชื่อหมวดหมู่ : " + category_store_name);

        MyArrList = myDb.GetStoreByCategoryId(category_store_id);


       /* SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(getBaseContext(), MyArrList, R.layout.column_store,
                new String[]{"store_name"}, new int[]{R.id.ColName});
        listSubStore.setAdapter(sAdap);*/
        listStore.setAdapter(new ImageAdapter(this,MyArrList));

        listStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), StoreActivity.class);
                intent.putExtra("store_name", MyArrList.get(i).get("store_name"));
                intent.putExtra("store_id", MyArrList.get(i).get("store_id"));
                intent.putExtra("store_image", MyArrList.get(i).get("store_image"));
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

    public class ImageAdapter extends BaseAdapter
    {
        private Context context;
        private ArrayList<HashMap<String, String>> MyArr = new ArrayList<HashMap<String, String>>();

        public ImageAdapter(Context c, ArrayList<HashMap<String, String>> list)
        {
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
            int ResID = context.getResources().getIdentifier(MyArr.get(position).get("store_image"), "drawable", context.getPackageName());
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
            txtName.setText(" " + MyArr.get(position).get("store_name"));


            return convertView;

        }

    }

}
