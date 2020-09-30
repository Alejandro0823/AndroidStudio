package com.example.applistview;

import android.content.Intent;
import android.os.Bundle;

import com.example.applistview.adapters.NotaAdapter;
import com.example.applistview.models.NotaModel;


import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroNotasActivity extends AppCompatActivity {

    private EditText et_titulo,et_descripcion;
    private Button btnagregarn;
    private NotaAdapter adapter;
    private NotaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_notas);


        init();


       btnagregarn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String titulo = et_titulo.getText().toString();
               String descripcion = et_descripcion.getText().toString();

               model = new NotaModel(titulo,descripcion);
               adapter.openWrite();
               long valorinsert = adapter.insert(model);
               adapter.close();

               if(valorinsert > 0 ){
                   Toast.makeText(RegistroNotasActivity.this, "NOTA AGREGADA", Toast.LENGTH_LONG).show();
                   Intent regresar = new Intent(RegistroNotasActivity.this,NotasActivity.class);
                   regresar.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(regresar);
               }else{
                   Toast.makeText(RegistroNotasActivity.this, "NO SE PUDO AGREGAR LA NOTA", Toast.LENGTH_LONG).show();
               }
           }
       });

    }

    private void init(){
        et_titulo = findViewById(R.id.et_titulo);
        et_descripcion = findViewById(R.id.et_descripcion);
        btnagregarn = findViewById(R.id.btnagregarn);

        adapter = new NotaAdapter(getApplicationContext());
        model = new NotaModel();

    }
}