package com.example.willroy.subsist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private List<Integer> selected = new ArrayList<>();
    private boolean remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.list);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        setupListViewListener2();
    }

    public void onClick(View v) {
        String empty = new String();
        TextView input = findViewById(R.id.input_text);
        String content = input.getText().toString();
        if (content.equals(empty)) {

        } else {
            itemsAdapter.add(content);
            input.setText("");
        }
    }


    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {



                        if (remove == false) {

                            selected = new ArrayList<>();
                            for (int i = 0; i < adapter.getChildCount(); i++) {
                                if (i == pos) {
                                    adapter.getChildAt(i).setBackgroundColor(Color.GRAY);
                                    if (!selected.contains(pos)) {
                                        try {
                                            selected.add(pos);
                                        } catch (IndexOutOfBoundsException e) {

                                        }
                                        System.out.println(selected);
                                    }
                                } else {

                                }
                            }
                            remove = true;
                        } else {
                            for (int i = 0; i < selected.size(); i++) {
                                int x = selected.get(i);
                                System.out.println(selected);
                                System.out.println(x);
                                try { items.remove(x); itemsAdapter.notifyDataSetChanged();}
                                catch (IndexOutOfBoundsException e) {}
                                System.out.println(selected);
                            }
                            for (int i = 0; i < adapter.getChildCount(); i++) {
                                adapter.getChildAt(i).setBackgroundColor(Color.WHITE);
                            }
                            selected = null;
                            remove = false;
                        }
                        return true;
                    }
                });

    }
    public static boolean contains(int[] arr, int item) {
        for (int n : arr) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }
    private void setupListViewListener2() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {

                        if (remove == true) {
                            for (int i = 0; i < adapter.getChildCount(); i++) {
                                if (i == pos) {
                                    if (selected.contains(pos)) {
                                        adapter.getChildAt(i).setBackgroundColor(Color.WHITE);
                                        try {
                                            selected.remove(pos);
                                            System.out.println("Removed" + pos);
                                        } catch (IndexOutOfBoundsException e) {

                                        }

                                        System.out.println(selected);
                                    } else {
                                        adapter.getChildAt(i).setBackgroundColor(Color.GRAY);
                                        if (!selected.contains(pos)) {
                                            try {
                                                selected.add(pos);
                                                System.out.println("Added" + pos);

                                            } catch (IndexOutOfBoundsException e) {

                                            }

                                            System.out.println(selected);
                                        }
                                    }
                                }
                            }
                            if (selected.isEmpty()) {
                                remove = false;
                            }
                        }
                    }

                });
    }

}