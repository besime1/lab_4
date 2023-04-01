package com.example.mainactivity;
import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
     private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.listView);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        items= new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        setUpListVievListener();



    }

    private void setUpListVievListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context =getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                AlertDialog.Builder builder;
                builder.setTitle("Do you want to delete this?");
                String position;
                builder.setMessage("The selected row is: " + position);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete the selected row
                    }
                });

                builder.setNegativeButton("No", null);

            }
        });
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.editText2);
        String item = input.getText().toString();

        String itemText = null;
        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(), "Please enter text..", Toast.LENGTH_LONG).show();
        }

    }
}