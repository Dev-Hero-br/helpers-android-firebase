package com.devhero.apptodolist.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devhero.apptodolist.R;
import com.devhero.apptodolist.config.ConfiguracaoFirebase;
import com.devhero.apptodolist.model.Tarefa;
import com.google.firebase.database.DatabaseReference;

public class EditarActivity extends AppCompatActivity {
    private EditText editNome;
    private Button btnExluir, btnEditar;

    Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Intent intent = getIntent();
        tarefa = intent.getParcelableExtra("tarefa");

        editNome = findViewById(R.id.editnome);
        btnEditar = findViewById(R.id.btnAtualizar);
        btnExluir = findViewById(R.id.btnExcluir);

editNome.setText(tarefa.getNome());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editNome.getText().toString().isEmpty()){
                    Tarefa tarefaAtualizada = new Tarefa();
                    tarefaAtualizada.setId(tarefa.getId());
                    tarefaAtualizada.setNome(editNome.getText().toString());
                    try {
                        tarefaAtualizada.atualizar(tarefaAtualizada.getId());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(EditarActivity.this, "Preencha o campo nome", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnExluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirAlert();
            }
        });
    }

    public void exibirAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alerta");
        builder.setMessage("Deseja realmente EXCLUIR essa tarefa?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                excluir();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void excluir(){
        try {
            DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
            DatabaseReference databaseTarefas = databaseReference.child("Tarefas").child(tarefa.getId());

            databaseTarefas.removeValue();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}