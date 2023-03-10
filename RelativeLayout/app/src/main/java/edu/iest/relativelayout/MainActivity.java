package edu.iest.relativelayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView ivContinuara;
    private String texto,programa;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bnCanal = findViewById(R.id.bnCambiar);
        EditText etCanal = findViewById(R.id.etCanal);
        TextView tvCanal = findViewById(R.id.tvCanal);
        Spinner spProgramas = findViewById(R.id.spPrograma);
        ivContinuara = findViewById(R.id.ivContinuara);

        //cambiar colores sin usar xml (solo colores dentro del archivo colors)
        //tvCanal.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500));
        //tvCanal.setTextColor(ContextCompat.getColor(this, R.color.black));

        //color mediante hexadecimal
        //tvCanal.setBackgroundColor(Color.parseColor("FFF012"));
        //tvCanal.setBackgroundColor(Color.RED);


        spProgramas.setOnItemSelectedListener(this);

        bnCanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aquí la logica de que hacer al hacer click
                //Obtendremos el valor del edittext y lo asignaremos al TextView inferior
                texto = etCanal.getText().toString();
                if (texto.isEmpty()){
                    Toast.makeText(MainActivity.this,"Escribe en la parte de arriba", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"El valor era "+texto, Toast.LENGTH_LONG).show();
                    tvCanal.setText(texto);
                }

            }
        });

        ivContinuara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this, DatosActivity.class);
                i.putExtra("Canal", texto);
                i.putExtra("Programa", programa);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
        programa = adapterView.getItemAtPosition(posicion).toString();
        Snackbar.make(adapterView, "Programa seleccionado " + programa,Snackbar.LENGTH_LONG).show();

        if (posicion == 0){
            ivContinuara.setImageResource(R.drawable.tobecontinued);
        }else if (posicion == 1){
            ivContinuara.setImageResource(R.drawable.tbconti);
        }else {
            ivContinuara.setImageResource(R.drawable.tbcontinue);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}