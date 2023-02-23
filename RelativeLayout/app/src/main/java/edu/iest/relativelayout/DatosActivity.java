package edu.iest.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosActivity extends AppCompatActivity {

    private String canal;
    private String programa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        Button bnback = findViewById(R.id.bnback);
        TextView tvValorCanal = findViewById(R.id.tvValorCanal);
        TextView tvValorPrograma = findViewById(R.id.tvValorPrograma);

        canal = getIntent().getStringExtra("Canal");
        programa = getIntent().getStringExtra("Programa");
        tvValorCanal.setText(canal);
        tvValorPrograma.setText(programa);


        bnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(DatosActivity.this, MainActivity.class);
                startActivity(b);
                finish();
            }
        });

    }
}