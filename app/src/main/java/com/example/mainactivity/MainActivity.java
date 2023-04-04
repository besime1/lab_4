package com.example.mainactivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btnAdd;
    EditText todoText;
    Switch isUrgentSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_todo);
        btnAdd = findViewById(R.id.button_add);
        todoText = findViewById(R.id.edittext_todo_input);
        isUrgentSwitch = findViewById(R.id.switch_urgent);
        TodoDatabase appDatabase = new TodoDatabase(this);
        List<TodoList> todoList = appDatabase.getAllTasks();
        TodoListAdapter todoAdapter = new TodoListAdapter(todoList);
        listView.setAdapter(todoAdapter);
    }
}