package com.example.mainactivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public class TodoItem {
        private String text;
        private boolean isUrgent;

        public TodoItem(String text, boolean isUrgent) {
            this.text = text;
            this.isUrgent = isUrgent;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isUrgent() {
            return isUrgent;
        }

        public void setUrgent(boolean urgent) {
            isUrgent = urgent;
        }

        public class mainActivity extends AppCompatActivity {

            private List<TodoItem> todoItemList;
            private ArrayAdapter<TodoItem> adapter;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                // Initialize todoItemList
                todoItemList = new ArrayList<>();

                // Initialize adapter
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItemList);

                // Set adapter to ListView
                ListView listView = findViewById(R.id.listView);
                listView.setAdapter(Carousel.adapter);

            }
        }

    }
    Button addButton = findViewById(R.id.addButton);
addButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        EditText editText = findViewById(R.id.editText);
        Switch switchUrgent = findViewById(R.id.switchUrgent);

        String text = editText.getText().toString();
        boolean isUrgent = switchUrgent.isChecked();

        TodoItem todoItem = new TodoItem(text, isUrgent);
        todoItemList.add(todoItem);

        editText.setText("");
        adapter.notifyDataSetChanged();
    }
        private class TodoItemAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return todoItemList.size();
            }

            @Override
            public TodoItem getItem(int position) {
                return todoItemList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;

                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.list_item_todo, parent, false);
                }

                TodoItem todoItem = getItem(position);
                TextView textView = view.findViewById(R.id.textView);
                textView.setText(todoItem.getText());

                if (todoItem.isUrgent()) {
                    view.setBackgroundColor(Color.RED);
                    textView.setTextColor(Color.WHITE);
                } else {
                    view.setBackgroundColor(Color.WHITE);
                    textView.setTextColor(Color.BLACK);
                }

                return view;
            }
        }

    }
}