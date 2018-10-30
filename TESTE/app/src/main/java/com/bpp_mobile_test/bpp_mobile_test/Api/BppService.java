package com.bpp_mobile_test.bpp_mobile_test.Api;

import com.bpp_mobile_test.bpp_mobile_test.Model.Invoice;
import com.bpp_mobile_test.bpp_mobile_test.Model.ResponseLogin;
import com.bpp_mobile_test.bpp_mobile_test.Model.TimeLineResult;
import com.bpp_mobile_test.bpp_mobile_test.Model.Usuario;
import com.bpp_mobile_test.bpp_mobile_test.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by fjesus on 25/05/2018.
 */

public interface BppService {

    public static final String BASE_URL = "http://test-mobile.dev-bpp.com.br/";

    @POST("login")
    Call<ResponseLogin> loginUsuario(@Query("email") String email, @Query("password") String password);

    @GET("invoice")
    Call<List<Invoice>> timeLineTrasacoes();

}
