package com.software2_ucc.proy2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button est, env, cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        est = (Button) findViewById(R.id.est);
        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent est = new Intent(MainActivity.this, Establecer.class);
                startActivity(est);
            }
        });
        env=(Button)findViewById(R.id.env);
        env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent env=new Intent(MainActivity.this,Alerta.class);
                //startActivity(env);
            }
        });
        cerrar=(Button)findViewById(R.id.cerrar);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();

            }
        });
    }
}
