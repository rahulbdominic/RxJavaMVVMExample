package com.vida.mvvmreduxsample.mvvmreduxsample;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class TodoViewModel
        extends Observable {
    TodoViewModelState state;
    Observer observer;

    public TodoViewModel() {
        this.state = new TodoViewModelState();
    }

    public void addAction(String title) {
        this.state = new TodoViewModelState(this.state, new TodoItem(
                String.valueOf(System.currentTimeMillis()),
                title,
                false
        ));
        observer.onNext(this);
    }

    public List<String> getTodoStrings() {
        List<TodoItem> items = state.getItems();
        List<String> dataset = new ArrayList<>();

        for (TodoItem item : items) {
            dataset.add(item.getName());
        }
        return dataset;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        this.observer = observer;
        observer.onNext(this);
    }
}
