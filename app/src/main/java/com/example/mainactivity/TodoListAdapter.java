package com.example.androidlabs;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mainactivity.R;
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoList todo = todoLists.get(position);
        View view = convertView;
        TextView label;
        if(convertView==null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_layout,parent,false);
        }
        label = view.findViewById(R.id.textView_todo);
        label.setText(todo.getName());
        if(todo.isUrgent)
        {
            label.setBackgroundColor(Color.RED);
        }
        else
        {
            label.setBackgroundColor(Color.WHITE);
        }

        return view;
    }

    void addToDo(TodoList todo)
    {
        todoLists.add(todo);
        notifyDataSetChanged();
    }
    void removeItem(int index)
    {
        todoLists.remove(index);
        notifyDataSetChanged();
    }
}
