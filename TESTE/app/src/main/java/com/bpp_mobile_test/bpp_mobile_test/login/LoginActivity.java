package com.bpp_mobile_test.bpp_mobile_test.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bpp_mobile_test.bpp_mobile_test.Api.BppService;
import com.bpp_mobile_test.bpp_mobile_test.BO.UsuarioBO;
import com.bpp_mobile_test.bpp_mobile_test.Model.Usuario;
import com.bpp_mobile_test.bpp_mobile_test.R;
import com.bpp_mobile_test.bpp_mobile_test.TimeLineActivity;

import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEdt;
    private TextInputEditText passwordEdt;
    private Button btn_entrar;
    private Usuario mUsuario;
    private UsuarioBO mUsuarioBO;
    private SharedPreferences preference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_entrar = findViewById(R.id.btn_entrar);
        mUsuarioBO = new UsuarioBO(this);

        preference  = getSharedPreferences("login", Context.MODE_PRIVATE);
        if(preference.getString("email", null) != null){
            Intent intent = new Intent(this, TimeLineActivity.class);
            startActivity(intent);
            finish();
        }


        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mUsuario = mUsuarioBO.pegaDados(emailEdt, passwordEdt);
                    if(mUsuario != null){
                        mUsuarioBO.logar(mUsuario);
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
