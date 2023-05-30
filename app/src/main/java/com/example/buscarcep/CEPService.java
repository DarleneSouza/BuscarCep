package com.example.buscarcep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {
    @GET("{cep}/json")
    Call<CEPResponse> getCEP(@Path("cep") String cep);
}
