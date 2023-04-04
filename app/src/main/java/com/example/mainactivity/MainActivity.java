package com.example.mainactivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.do_you_want_to_delete);
                int rowNo= position+1;
                builder.setMessage(getString(R.string.selected_row)+rowNo);
                builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        appDatabase.delete(todoList.get(position));
                        todoAdapter.removeItem(position);


                    }
                });
                builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todo = new TodoList();
                String task = todoText.getText().toString();
                Boolean isUrgent = isUrgentSwitch.isChecked();
                todo.setName(task);
                todo.setUrgent(isUrgent);
                todoAdapter.addToDo(todo);
                appDatabase.addTodo(todo);
                todoText.setText(null);
                isUrgentSwitch.setChecked(false);

            }
        });
    }
}