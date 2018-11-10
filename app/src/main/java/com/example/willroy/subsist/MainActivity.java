package com.example.willroy.subsist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private int count = 0;

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
        if (remove == false) {
            String empty = new String();
            TextView input = findViewById(R.id.input_text);
            String content = input.getText().toString();
//            items.add("String1");
//            items.add("String2");
//            items.add("String3");
            if (content.equals(empty)) {
            } else {

                itemsAdapter.add(content);
                input.setText("");
            }
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } else if (remove == true) {
        }
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        if (remove == false) {
                            for (int i = 0; i < adapter.getChildCount(); i++) {
                                adapter.getChildAt(i).setTag(0);
                                if (i == pos) {
                                    adapter.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                                    adapter.getChildAt(i).setTag(1);
                                }
                            }
                            TextView input = findViewById(R.id.input_text);
                            input.setEnabled(false);
                            remove = true;
                        } else if (remove == true) {
                            ListView output = findViewById(R.id.list);
//                            System.out.println("ChildCount: " + output.getChildCount());
                            for (int i = output.getChildCount()-1; i >= 0; i--) {
                                if (adapter.getChildAt(i).getTag().toString().equals("1")) {
                                    String value = items.get(i);
//                                    System.out.println(value);
                                    for (int a = 0; a < items.size(); a++) {
//                                        System.out.println("a: " + a);
                                        if (items.get(a).equals(value)) {
                                            if (a == i) {
//                                                System.out.println("TRUE");
                                                try {items.remove(a);itemsAdapter.notifyDataSetChanged();} catch (IndexOutOfBoundsException e) {System.out.println("Couldn't Remove");}
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            for (int i = 0; i < output.getChildCount(); i++) {
                                output.getChildAt(i).setBackgroundColor(Color.WHITE);
                                output.getChildAt(i).setTag(0);
                            }
                            remove = false;
                            TextView input = findViewById(R.id.input_text);
                            input.setEnabled(true);
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
                                    if (adapter.getChildAt(pos).getTag().toString().equals("1")) {
                                        adapter.getChildAt(i).setBackgroundColor(Color.WHITE);
                                        adapter.getChildAt(i).setTag(0);
                                    } else {
                                        adapter.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                                        adapter.getChildAt(i).setTag(1);
                                    }
                                }
                            }
                            for (int i = 0; i < adapter.getChildCount(); i++) {
                                if (adapter.getChildAt(i).getTag().toString().equals("1")) {
                                    count = 0;
                                } else if (adapter.getChildAt(i).getTag().toString().equals("0")) {
                                    count += 1;
                                }
                            }
                            if (adapter.getChildCount() == count) {
                                count = 0;
                                remove = false;
                            }
                        }
                    }
                });
    }
}