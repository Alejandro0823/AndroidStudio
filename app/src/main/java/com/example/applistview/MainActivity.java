package com.example.applistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnagregarnota = (Button)findViewById(R.id.btnagregarnota);

        btnagregarnota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agregarnota = new Intent(MainActivity.this,NotasActivity.class);
                startActivity(agregarnota);
            }
        });
    }
}