package com.vida.mvvmreduxsample.mvvmreduxsample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class TodoFragment
        extends Fragment
        implements Observer<TodoViewModel> {

    TodoViewModel viewModel;
    ListView listView;
    FloatingActionButton fab;

    public static TodoFragment newInstance() {
        return new TodoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        RelativeLayout rootView = (RelativeLayout) inflater.inflate(R.layout.todo_fragment, container, false);
        listView = (ListView) rootView.findViewById(R.id.todo_list_list_view);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Todo Item");
                alertDialog.setMessage("Enter todo item title");
                final EditText input = new EditText(getContext());
                alertDialog.setView(input);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.addAction(input.getText().toString());
                            }
                        });
                alertDialog.show();

            }
        });

    }

    public void refreshView() {
        List<String> dataset = viewModel.getTodoStrings();

        listView.setAdapter(new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                dataset
        ));
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull TodoViewModel todoViewModel) {
        this.viewModel = todoViewModel;
        if (this.getContext() != null) {
            refreshView();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
