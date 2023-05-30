package com.example.buscarcep;

import com.google.gson.annotations.SerializedName;

public class CEPResponse {
    @SerializedName("cep")
    private String cep;

    @SerializedName("logradouro")
    private String logradouro;

    @SerializedName("bairro")
    private String bairro;

    @SerializedName("localidade")
    private String localidade;

    // Outros campos que você queira mapear

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }
    public String getLocalidade() {
        return localidade;
    }

    // Métodos getter para outros campos
}
