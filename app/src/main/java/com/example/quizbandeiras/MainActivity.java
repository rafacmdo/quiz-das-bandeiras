package com.example.quizbandeiras;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    TextView txtNome, txtRGM;

    EditText edtNome, edtRGM;

    Button btnIniciar, btnSair, btnImagem;
    String nome;
    String rgm;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Início");


        txtNome = findViewById(R.id.txtNome);
        txtRGM = findViewById(R.id.txtRGM);
        edtNome = findViewById(R.id.edtNome);
        edtRGM = findViewById(R.id.edtRGM);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnSair = findViewById(R.id.btnSair);
        imageView = findViewById(R.id.imageView);
        btnImagem = findViewById(R.id.btnImagem);


        TextWatcher loginTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nome = edtNome.getText().toString().trim();
                rgm = edtRGM.getText().toString().trim();

                btnIniciar.setEnabled(!nome.isEmpty() && !rgm.isEmpty());
                txtNome.setText("Nome: "+nome);
                txtRGM.setText("RGM: "+rgm);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        };
        edtNome.addTextChangedListener(loginTextWatcher);
        edtRGM.addTextChangedListener(loginTextWatcher);




        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIniciar.setEnabled(true);
                Intent intent = new Intent(MainActivity.this, Perguntas.class);
                intent.putExtra("nome", nome); // Passa o nome para a próxima atividade
                startActivity(intent);
                txtNome.setText("");
                txtRGM.setText("");
                finish();


            }
        });


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            },100);
        }
        btnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
