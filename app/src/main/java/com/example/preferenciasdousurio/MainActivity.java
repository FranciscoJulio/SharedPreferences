package com.example.preferenciasdousurio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvarID);
        editNome = findViewById(R.id.editNomeID);
        textResultado = findViewById(R.id.textResultadoID);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if( editNome.getText().toString().equals("") ){
                    Toast.makeText( getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG).show();
                }else{

                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                    textResultado.setText("Olá, " + nome);
                }
            }
        });



        //Recuperar dados salvos sharedPreferences
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //Valida se temos o nome em preferencias
        if( preferences.contains("nome") ){
            String nome = preferences.getString("nome", "usuário não definido.");
            textResultado.setText("Olá, " + nome);
        }else{
            textResultado.setText("Olá, usuário não definido.");
        }

    }

}
