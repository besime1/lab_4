package com.example.androidlabs;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mainactivity.TodoList;

import java.util.List;

public class TodoListAdapter extends BaseAdapter {

    List<TodoList> todoLists;

    public TodoListAdapter(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    @Override
    public int getCount() {
        return todoLists.size();
    }

    @Override
    public Object getItem(int position) {
        return todoLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
