package com.example.kolvir.personalassistant.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kolvir.personalassistant.Adapter.DaysAdapter;
import com.example.kolvir.personalassistant.Database.DatabaseHelper;
import com.example.kolvir.personalassistant.Dialog.MyDialog;
import com.example.kolvir.personalassistant.R;
import com.example.kolvir.personalassistant.pojo.Day;
import com.melnykov.fab.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;


//TODO проверить callback на приватность и насколько она важна
//TODO Убрать loadDATA в отдельный поток
public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyDialog.MyCallback{

    DatabaseHelper dbhelper;

    RecyclerView daysList;
    MyDialog df;
    FloatingActionButton floatingActionButton;
    DaysAdapter daysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        df = new MyDialog();
        df.registerCallback(this);
        floatingActionButton = findViewById(R.id.fab);

        dbhelper = new DatabaseHelper(this);
        loadDATA();
        initDaysRecyclerView();
        fabMenuCreating();
    }

    private void rendering(ArrayList<Day> data){
        daysAdapter.setItems(data);
    }

    private void loadDATA() {
        ArrayList<Day> tmp = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null,null,null,null,null,null);

        if (cursor.moveToFirst()){

            int titleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
            int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION);

            do{
                tmp.add(new Day(cursor.getString(titleIndex), cursor.getString(descriptionIndex)));
            }while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        rendering(tmp);
    }

    private void fabMenuCreating(){

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageResource(R.drawable.ic_details);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        button1.setId(R.id.btn1);

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.ic_details2);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        button2.setId(R.id.btn2);

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.ic_details3);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        button3.setId(R.id.btn3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(floatingActionButton)
                .setRadius(300)
                .setStartAngle(180)
                .setEndAngle(270)
                .build();
    }

    @Override
    public void onClick(View v) {
        boolean btn3 = false;
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        switch (v.getId()){
            case R.id.btn1:
                Toast.makeText(this,"btn1",Toast.LENGTH_SHORT).show();
                df.show(getFragmentManager(), "dl");
                break;
            case R.id.btn2:
                Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null,null,null,null,null,null);

                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DatabaseHelper.KEY_ID);
                    int titleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
                    int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION);
                        do{
                            Log.d("mLog", "ID =" + cursor.getInt(idIndex) +
                                    ", title = " + cursor.getString(titleIndex) +
                                    ", description = " + cursor.getString(descriptionIndex));
                        }while(cursor.moveToNext());
                }else
                    Log.d("mLog","0 rows");
                cursor.close();
                dbhelper.close();
                break;
            case R.id.btn3:

                daysAdapter.clearItems();
                database.delete(DatabaseHelper.TABLE_NAME, null, null);

                Toast.makeText(this,"btn3",Toast.LENGTH_SHORT).show();
                break;
        }
        dbhelper.close();

    }

    @Override
    public void returnCallback(String name, String description) {
        SQLiteDatabase database = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUMN_TITLE, name);
        contentValues.put(DatabaseHelper.COLUMN_DESCRIPTION, description);

        database.insert(DatabaseHelper.TABLE_NAME, null , contentValues);
        dbhelper.close();

        ArrayList<Day> tmp = new ArrayList<Day>();
        tmp.add(new Day(name,description));
        rendering(tmp);
    }

    private void initDaysRecyclerView(){
        daysList = findViewById(R.id.days_list);
        daysList.setLayoutManager(new LinearLayoutManager(this));
        daysAdapter = new DaysAdapter();
        daysList.setAdapter(daysAdapter);
    }

}
