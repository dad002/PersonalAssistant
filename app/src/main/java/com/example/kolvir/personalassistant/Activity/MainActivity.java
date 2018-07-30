package com.example.kolvir.personalassistant.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kolvir.personalassistant.Adapter.DaysAdapter;
import com.example.kolvir.personalassistant.Dialog.MyDialog;
import com.example.kolvir.personalassistant.R;
import com.example.kolvir.personalassistant.pojo.Day;
import com.melnykov.fab.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.Arrays;
import java.util.Collection;

//проверить callback на приватность и насколько она важна
//TODO выпить и не закусывать
//TODO Без перерыва выпить ещё
public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyDialog.MyCallback{

    RecyclerView daysList;
    MyDialog df;
    FloatingActionButton floatingActionButton;
    private DaysAdapter daysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        df = new MyDialog();
        df.registerCallback(this);
        floatingActionButton = findViewById(R.id.fab);

        initDaysRecyclerView();
        loadDays();
        fabMenuCreating();
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

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
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
        switch (v.getId()){
            case R.id.btn1:
                Toast.makeText(this,"btn1",Toast.LENGTH_SHORT).show();
                df.show(getFragmentManager(), "dl");
                break;
            case R.id.btn2:
                Toast.makeText(this,"btn2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Toast.makeText(this,"btn3",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void returnCallback(String name, String description) {

    }

    private void loadDays(){
        Collection<Day> days = getDays();
        daysAdapter.setItems(days);
    }

    private Collection<Day> getDays(){
        return Arrays.asList(
                new Day("Мое 1 дело", "очень длинное описание дела"),
                new Day("Мое 2 дело", "очень длинное описание дела"),
                new Day("Мое 3 дело", "очень длинное описание дела"),
                new Day("Мое 4 дело", "очень длинное описание дела"),
                new Day("Мое 5 дело", "очень длинное описание дела"),
                new Day("Мое 6 дело", "очень длинное описание дела"),
                new Day("Мое 7 дело", "очень длинное описание дела"),
                new Day("Мое 8 дело", "очень длинное описание дела"),
                new Day("Мое 9 дело", "очень длинное описание дела")
        );
    }

    private void initDaysRecyclerView(){
        daysList = findViewById(R.id.days_list);
        daysList.setLayoutManager(new LinearLayoutManager(this));
        daysAdapter = new DaysAdapter();
        daysList.setAdapter(daysAdapter);
    }
}
