package com.bpp_mobile_test.bpp_mobile_test.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bpp_mobile_test.bpp_mobile_test.Model.Invoice;
import com.bpp_mobile_test.bpp_mobile_test.R;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fjesus on 25/05/2018.
 */

public class TimeLIneAdapterListView  extends BaseAdapter{

    private List<Invoice> invoices;
    private Activity activity;

    public TimeLIneAdapterListView(List<Invoice> invoices, Activity activity){
        this.invoices = invoices;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return invoices.size();
    }

    @Override
    public Object getItem(int i) {
        return invoices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View views = activity.getLayoutInflater().inflate(R.layout.linha_timeline, viewGroup, false);
        TextView descricao = views.findViewById(R.id.descricao);
        TextView local = views.findViewById(R.id.local);
        TextView valor = views.findViewById(R.id.valor);
        TextView status = views.findViewById(R.id.status);
        TextView data = views.findViewById(R.id.data);

        Invoice invoice = invoices.get(i);

        descricao.setText(invoice.getMccDescription());
        local.setText(invoice.getMerchantName());

        status.setText(invoice.getTransactionStatus());

        BigDecimal valorTransaction = new BigDecimal ("12000000.12");
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        double valorCorrente = Double.parseDouble(invoice.getTransactionAmount());
        valor.setText(nf.format(valorCorrente));


        String dataa = invoice.getTransactionFormattedDate().substring(0, 10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateCorrente = simpleDateFormat.parse(dataa);

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM");
            data.setText(simpleDateFormat2.format(dateCorrente));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return views;
    }
}
