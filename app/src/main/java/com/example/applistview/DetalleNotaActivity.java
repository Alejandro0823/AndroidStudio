package com.example.applistview;

import android.os.Bundle;

import com.example.applistview.adapters.NotaAdapter;
import com.example.applistview.models.NotaModel;


import androidx.appcompat.app.AppCompatActivity;



import android.widget.Button;

import android.widget.TextView;

public class DetalleNotaActivity extends AppCompatActivity {

    private TextView tv_detallenota_titulo,tv_detallenota_descripcion;
    private Button btneditar,btneliminar;
    private NotaAdapter adapter;
    private NotaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_nota);


        init();

        model = (NotaModel) getIntent().getSerializableExtra("model");
        if (model != null){
            tv_detallenota_titulo.setText(model.get_titulo());
            tv_detallenota_descripcion.setText(model.get_descripcion());

        }

    }

    private void init(){
        tv_detallenota_titulo = findViewById(R.id.tv_detallenota_titulo);
        tv_detallenota_descripcion = findViewById(R.id.tv_detallenota_descripcion);
        btneditar = findViewById(R.id.btneditar);
        btneliminar = findViewById(R.id.btneliminar);

        adapter = new NotaAdapter(getApplicationContext());
        model = new NotaModel();

    }
}