package com.devhero.apptodolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.devhero.apptodolist.R;
import com.devhero.apptodolist.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {
    private ArrayList<Tarefa> arrayList;
    private Context context;
    int resource;

    public TarefaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Tarefa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(arrayList != null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView  = inflater.inflate(R.layout.layout_tarefa, parent, false);

            TextView textView = convertView.findViewById(R.id.idnomeTarefa);

            Tarefa tarefa = arrayList.get(position);
            textView.setText(tarefa.getNome());
            textView.setText(arrayList.get(position).getNome());
        }

        return convertView;
    }

    public ArrayList<Tarefa> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Tarefa> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
