package com.devhero.apptodolist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.devhero.apptodolist.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Tarefa implements Parcelable {
    private String id;
    private String nome;

    public Tarefa() {
    }

    protected Tarefa(Parcel in) {
        id = in.readString();
        nome = in.readString();
    }

    public static final Creator<Tarefa> CREATOR = new Creator<Tarefa>() {
        @Override
        public Tarefa createFromParcel(Parcel in) {
            return new Tarefa(in);
        }

        @Override
        public Tarefa[] newArray(int size) {
            return new Tarefa[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void salvar() {
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference databaseTarefas = databaseReference.child("Tarefas").child(getId());

        databaseTarefas.setValue(this);
    }

    public void atualizar(String id) {
        DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference databaseTarefas = databaseReference.child("Tarefas").child(getId());


        Map<String, Object> valoresTarefa = convertparaMap();

        databaseTarefas.updateChildren(valoresTarefa);
    }

    @Exclude
    public Map<String, Object> convertparaMap() {
        HashMap<String, Object> tarefaMap = new HashMap<>();
        tarefaMap.put("id", getId());
        tarefaMap.put("nome", getNome());
        return tarefaMap;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nome);
    }
}
