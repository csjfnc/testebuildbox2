package com.bpp_mobile_test.bpp_mobile_test.BO;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.util.Base64;
import android.widget.Toast;

import com.bpp_mobile_test.bpp_mobile_test.Api.BppService;
import com.bpp_mobile_test.bpp_mobile_test.Model.ResponseLogin;
import com.bpp_mobile_test.bpp_mobile_test.Model.Usuario;
import com.bpp_mobile_test.bpp_mobile_test.R;
import com.bpp_mobile_test.bpp_mobile_test.TimeLineActivity;
import com.bpp_mobile_test.bpp_mobile_test.login.LoginActivity;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fjesus on 25/05/2018.
 */

public class UsuarioBO {

    private static final String SUCCESS = "success";

    private String email;
    private String password;
    private TextInputEditText emailEdt;
    private TextInputEditText passwordEdt;
    private Usuario mUsuario;
    private Activity mActivity;
    private boolean result_ok;
    private Preference preference;


    public UsuarioBO(Activity activity) {
        this.mActivity = activity;

    }

    public Usuario pegaDados(TextInputEditText emailEdt, TextInputEditText passwordEdt) throws UnsupportedEncodingException {
        result_ok = true;
        mUsuario = new Usuario();
        emailEdt = mActivity.findViewById(R.id.emailEdt);
        passwordEdt = mActivity.findViewById(R.id.passwordEdt);

        email = emailEdt.getText().toString();
        password = passwordEdt.getText().toString();

        if (!email.equals("")) {
            mUsuario.setEmail(email);
        } else {
            emailEdt.setError("Informe o email");
            result_ok = false;
        }

        if (!password.equals("")) {

            byte[] data = password.getBytes("UTF-8");
            String passwordbase64 = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "");


            mUsuario.setPassword(passwordbase64);
        } else {
            passwordEdt.setError("Informe a senha");
            result_ok = false;
        }
        if (result_ok) {  return mUsuario; }else{ return null;}
    }

    public void logar(Usuario usuario) {

        final SharedPreferences sharedPreferences = mActivity.getSharedPreferences("login", Context.MODE_PRIVATE);

        final ProgressDialog alertDialog = new ProgressDialog(mActivity, R.style.Theme_AppCompat_Light_Dialog_Alert);
        alertDialog.setTitle("Autenticando");
        alertDialog.setMessage("Aguarde");
        alertDialog.setCancelable(false);
        alertDialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BppService.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();


        BppService service = retrofit.create(BppService.class);
        Call<ResponseLogin> usuarioCall = service.loginUsuario(mUsuario.getEmail(), mUsuario.getPassword());
        usuarioCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.isSuccessful()){
                    ResponseLogin responseLogin = response.body();
                    if(responseLogin.getStatus().equals(SUCCESS)) {
                        Intent intent = new Intent(mActivity, TimeLineActivity.class);
                        mActivity.startActivity(intent);
                        alertDialog.dismiss();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", mUsuario.getEmail());
                        editor.commit();

                        mActivity.finish();


                    }else{
                        Toast.makeText(mActivity, "Usuário Inválido", Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(mActivity, "Erro", Toast.LENGTH_LONG).show();
            }
        });
    }
}
