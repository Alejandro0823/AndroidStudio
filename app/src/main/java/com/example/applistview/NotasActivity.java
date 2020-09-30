package com.example.applistview;

import android.content.Intent;
import android.os.Bundle;

import com.example.applistview.adapters.NotaAdapter;
import com.example.applistview.models.NotaModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {

    private ListView lv_notas_lista;
    private FloatingActionButton fab_agregar_nota;
    private NotaModel model;
    private ArrayList<NotaModel> list;
    private NotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);


        init();
        adapter.openRead();
        list = adapter.selectNotasById();
        adapter.close();
        if (list !=null){
            lv_notas_lista.setAdapter(new NotaAdapter(this,list));
        }



        lv_notas_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                model = (NotaModel) adapterView.getItemAtPosition(i);
                Intent detalle = new Intent(NotasActivity.this, DetalleNotaActivity.class);
                detalle.putExtra("model", model);
                startActivity(detalle);
            }
        });

        fab_agregar_nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agregar = new Intent(NotasActivity.this,RegistroNotasActivity.class);
                startActivity(agregar);
            }
        });
    }

    public void init(){

        lv_notas_lista = findViewById(R.id.lv_notas_lista);
        fab_agregar_nota = findViewById(R.id.fab_agregar_nota);
        list = new ArrayList<>();
        model = new NotaModel();
        adapter = new NotaAdapter(getApplicationContext());



    }
}