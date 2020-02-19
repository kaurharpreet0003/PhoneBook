package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    DatabaseHelper mDatabase;
    List<Person> personList;
    //     ListView listView;
    SwipeMenuListView listView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.listview);
        personList = new ArrayList<>();
        mDatabase = new DatabaseHelper(this);
        loadPersons();


        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));

                editItem.setWidth((250));


                editItem.setTitleSize(18);

                editItem.setTitleColor(Color.WHITE);

                editItem.setTitle("Edit");

                menu.addMenuItem(editItem);
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());

                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));

                deleteItem.setWidth((250));

                deleteItem.setTitle("Delete");

                menu.addMenuItem(deleteItem);

            }
        };

        listView.setMenuCreator(creator);



        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:

                        Toast.makeText(Main2Activity.this, "update clicked", Toast.LENGTH_SHORT).show();


                        break;

                    case 1:
                        // delete
                        Toast.makeText(Main2Activity.this, "delete clicked", Toast.LENGTH_SHORT).show();
                        Person person2 = personList.get(position);

                        int id2 = person2.getId();

                        if(mDatabase.deletePerson(id2))
                            personList.remove(position);
                        adapter.notifyDataSetChanged();

                        break;
                }


                return true;
            }
        });

    }


    private void loadPersons() {

        Cursor cursor = mDatabase.getAllPersons();

        if(cursor.moveToFirst()){
            do {
                personList.add(new Person(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));


            }while (cursor.moveToNext());
            cursor.close();

            adapter = new Adapter(this, R.layout.list_item, personList, mDatabase);

            listView.setAdapter(adapter);

        }
    }

}
