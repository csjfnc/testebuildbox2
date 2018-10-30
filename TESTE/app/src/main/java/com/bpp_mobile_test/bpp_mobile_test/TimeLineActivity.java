package com.bpp_mobile_test.bpp_mobile_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bpp_mobile_test.bpp_mobile_test.Adapters.TimeLIneAdapterListView;
import com.bpp_mobile_test.bpp_mobile_test.Api.BppService;
import com.bpp_mobile_test.bpp_mobile_test.Model.Invoice;
import com.bpp_mobile_test.bpp_mobile_test.Model.TimeLineResult;
import com.bpp_mobile_test.bpp_mobile_test.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TimeLineActivity extends AppCompatActivity {

    private TimeLIneAdapterListView timeLIneAdapterListView;
    private ListView timelineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        timelineList = findViewById(R.id.timelineList);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BppService.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();

        BppService service = retrofit.create(BppService.class);
        Call<List<Invoice>> usuarioCall = service.timeLineTrasacoes();

        usuarioCall.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                if(response.isSuccessful()) {
                    List<Invoice> invoices =  response.body();
                    timeLIneAdapterListView = new TimeLIneAdapterListView(invoices, TimeLineActivity.this);
                    timelineList.setAdapter(timeLIneAdapterListView);
                }
            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {
                Toast.makeText(TimeLineActivity.this, "Errro", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sair:{

                SharedPreferences.Editor preferences = (SharedPreferences.Editor) getSharedPreferences("login", Context.MODE_PRIVATE).edit();
                preferences.clear();
                preferences.commit();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
