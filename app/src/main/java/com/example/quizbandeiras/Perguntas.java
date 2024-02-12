package com.example.quizbandeiras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Perguntas extends AppCompatActivity implements View.OnClickListener {
    int score = 0;
    int questaoAtual = 0;


    int totalQuestao = 9;
    RadioGroup rdg;
    RadioButton rdbA, rdbB, rdbC, rdbD;
    Button btnResponder;
    ImageView imgBandeira;

    String mCorrectAnswers[] = {
            "Alemanha",
            "Bolívia",
            "Brasil",
            "Colômbia",
            "Etiópia",
            "Itália",
            "Haiti",
            "Jamaica",
            "França",
            "Reino Unido",
    };

    String mChoice[][] = {
            {"França", "Colômbia", "Alemanha", "Bolívia"},
            {"Etiópia", "Inglaterra", "Síria", "Bolívia"},
            {"Albânia", "Áustria", "Cabo Verde", "Brasil"},
            {"Inglaterra", "Colômbia", "Canadá", "Estados Unidos"},
            {"Costa do Marfim", "Costa Rica", "Etiópia", "Guatemala"},
            {"Itália", "Jordânia", "Grécia", "Maldivas"},
            {"Marrocos", "Haiti", "México", "Nepal"},
            {"Paraguai", "Peru", "Jamaica", "Suriname"},
            {"Zimbábue", "França", "Iraque", "Rússia"},
            {"Inglaterra", "Estados Unidos", "Portugal", "Reino Unido"},
    };


    int images [] = {
            R.drawable.alemanha,
            R.drawable.bolivia,
            R.drawable.brasil,
            R.drawable.colombia,
            R.drawable.etiopia,
            R.drawable.italia,
            R.drawable.haiti,
            R.drawable.jamaica,
            R.drawable.franca,
            R.drawable.reinounido,
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Que país é este?");
        setContentView(R.layout.activity_perguntas);

        rdg = findViewById(R.id.rdg);
        rdbA = findViewById(R.id.rdbA);
        rdbB = findViewById(R.id.rdbB);
        rdbC = findViewById(R.id.rdbC);
        rdbD = findViewById(R.id.rdbD);
        imgBandeira = findViewById(R.id.imgBandeira);

        btnResponder = findViewById(R.id.btnResponder);
        btnResponder.setOnClickListener(this);

        imgBandeira.setImageResource(images[questaoAtual]);
        rdbA.setText(mChoice[questaoAtual][0]);
        rdbB.setText(mChoice[questaoAtual][1]);
        rdbC.setText(mChoice[questaoAtual][2]);
        rdbD.setText(mChoice[questaoAtual][3]);


    }


    @Override
    public void onClick(View view) {

        // Verificar qual RadioButton foi selecionado
        int selectedId = rdg.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);

        if(selectedRadioButton == null){
            btnResponder.setEnabled(true);

        }
        if (selectedRadioButton != null) {
            String respostaSelecionada = selectedRadioButton.getText().toString();
            String respostaCorreta = mCorrectAnswers[questaoAtual];

            if (respostaSelecionada.equals(respostaCorreta)) {
                score++;
            }

            // Verificar se todas as perguntas foram respondidas
            if (questaoAtual < totalQuestao) {
                questaoAtual++;

                // Atualize a interface do usuário com a próxima pergunta
                imgBandeira.setImageResource(images[questaoAtual]);
                rdbA.setText(mChoice[questaoAtual][0]);
                rdbB.setText(mChoice[questaoAtual][1]);
                rdbC.setText(mChoice[questaoAtual][2]);
                rdbD.setText(mChoice[questaoAtual][3]);
                // Limpe a seleção do RadioGroup para a próxima pergunta
                rdg.clearCheck();

            }

            else {
                // Inicia atividade de Ranking
                Intent intent = new Intent(this, Ranking.class);
                String nome = getIntent().getStringExtra("nome");
                intent.putExtra("nome", nome); // Passa o nome para a próxima atividade (Ranking)
                intent.putExtra("score", score);
                startActivity(intent);
                finish();

            }


        }


        }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}


