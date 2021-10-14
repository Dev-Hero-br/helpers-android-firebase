package com.devhero.apptodolist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.devhero.apptodolist.R;
import com.devhero.apptodolist.adapter.TarefaAdapter;
import com.devhero.apptodolist.config.ConfiguracaoFirebase;
import com.devhero.apptodolist.model.Tarefa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnNovo;
    private ListView listView;
    private ArrayList<Tarefa> arrayList;
    private ValueEventListener valueEventListener;
    private DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference databaseTarefas = databaseReference.child("Tarefas");
    TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNovo = findViewById(R.id.idBtnNovo);
        listView = findViewById(R.id.idListadetarefas);
        arrayList = new ArrayList<>();
        listView.setAdapter(tarefaAdapter);



        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });


        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    Tarefa tarefa = data.getValue(Tarefa.class);
                    arrayList.add(tarefa);

                    tarefaAdapter = new TarefaAdapter(MainActivity.this, R.layout.layout_tarefa, arrayList);
                    listView.setAdapter(tarefaAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditarActivity.class);
                i.putExtra("tarefa", (Parcelable) arrayList.get(position));
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseTarefas.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseTarefas.removeEventListener(valueEventListener);
    }
}