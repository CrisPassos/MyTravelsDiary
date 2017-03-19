package br.com.cristiana.mytravelsdiary.api;

import br.com.cristiana.mytravelsdiary.model.Login;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Cristiana on 14/03/2017.
 */

public interface UserApi {

    @GET("/v2/58b9b1740f0000b614f09d2f")
    Call<Login> login();
}
