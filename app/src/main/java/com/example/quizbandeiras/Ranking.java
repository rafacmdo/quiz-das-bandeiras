package com.example.quizbandeiras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ranking extends AppCompatActivity {

    Button btnRestart, btnMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().setTitle("Ranking");
        String nome = getIntent().getStringExtra("nome");


       Intent mIntent = getIntent();
       int score = mIntent.getIntExtra("score", 0);


        TextView txtFulano = (TextView) findViewById(R.id.txtFulano);
        TextView txtRanking = (TextView) findViewById(R.id.txtRanking);
        txtFulano.setText("Nome: " +nome);
        txtRanking.setText(String.valueOf(score));




    }



    public void refazer(View v){
        Intent it = new Intent(Ranking.this, Perguntas.class);
        String nome = it.getStringExtra("nome");
        it.putExtra("nome", nome);
        startActivity(it);
        finish();



    }

    public void resetar(View v){
        Intent it = new Intent(Ranking.this, MainActivity.class);
        startActivity(it);
        finish();
    }
}