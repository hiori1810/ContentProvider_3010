package com.example.contentprovider_3010;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static  final String AUTHORITY = "com.example.demokiemtra1610";
    static final String CONTEN_PATH="bookdata";
    static final String URL="content://" + AUTHORITY + "/" + CONTEN_PATH;
    static final Uri CONTENT_URI = Uri.parse(URL);

    EditText et_id,et_title,et_author;
    Button bt_savebook,bt_select,bt_exit,bt_update;
    GridView gv_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_id=(EditText) findViewById(R.id.edtNhapms);
        et_title=(EditText) findViewById(R.id.edtNhaptd);
        et_author=(EditText) findViewById(R.id.edtNhaptentg);
        gv_display=(GridView)findViewById(R.id.grvDisplay);

        bt_savebook=(Button)findViewById(R.id.btn_Savebook);
        bt_savebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("id_book",et_id.getText().toString());
                values.put("title",et_title.getText().toString());
                values.put("id_author",et_author.getText().toString());
                Uri insert_uri = getContentResolver().insert(CONTENT_URI,values);
                Toast.makeText(getApplicationContext(),"Lưu thành công",Toast.LENGTH_LONG).show();
            }
        });
        bt_select=(Button)findViewById(R.id.btnSelect);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list=new ArrayList<>();
                Cursor cursor = getContentResolver().query(CONTENT_URI,null,null,null,"title");
                if(cursor != null){
                    cursor.moveToFirst();
                    do{
                        list.add(cursor.getInt(0)+"");
                        list.add(cursor.getString(1)+"");
                        list.add(cursor.getInt(2)+"");
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                    gv_display.setAdapter(adapter);
                }
            }
        });
    }
}
