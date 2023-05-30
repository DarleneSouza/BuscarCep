package com.example.buscarcep;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.buscarcep.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    private EditText cepEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cepEditText = findViewById(R.id.edit_text_cep);
        searchButton = findViewById(R.id.button_search);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = cepEditText.getText().toString();
                if (cep.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Informe o CEP", Toast.LENGTH_SHORT).show();
                } else {
                    searchCEP(cep);
                }
            }
        });


    }


    private void searchCEP(String cep) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEPResponse> call = cepService.getCEP(cep);

        call.enqueue(new Callback<CEPResponse>() {
            @Override
            public void onResponse(Call<CEPResponse> call, Response<CEPResponse> response) {
                if (response.isSuccessful()) {
                    CEPResponse cepResponse = response.body();
                    if (cepResponse != null) {
                        String result = "CEP: " + cepResponse.getCep() +
                                "\nLogradouro: " + cepResponse.getLogradouro() +
                                "\nBairro: " + cepResponse.getBairro() +
                                "\nCidade: " + cepResponse.getLocalidade();

                        // Outros campos que você queira exibir

                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("result", result);
                        startActivity(intent);
                    }
                } else {
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<CEPResponse> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Erro ao buscar o CEP", Toast.LENGTH_LONG).show();
    }
}
