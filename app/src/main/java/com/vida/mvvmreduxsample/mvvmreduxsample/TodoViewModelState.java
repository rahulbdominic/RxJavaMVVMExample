package com.vida.mvvmreduxsample.mvvmreduxsample;

import java.util.ArrayList;
import java.util.List;

public class TodoViewModelState {
    List<TodoItem> items;

    public TodoViewModelState() {
        this.items = new ArrayList<>();
    }

    public TodoViewModelState(TodoViewModelState state, TodoItem item) {
        this.items = state.getItems();
        this.items.add(0, item);
    }

    public List<TodoItem> getItems() {
        return items;
    }
}
