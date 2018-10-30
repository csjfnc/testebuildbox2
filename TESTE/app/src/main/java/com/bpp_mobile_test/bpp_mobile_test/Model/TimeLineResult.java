package com.bpp_mobile_test.bpp_mobile_test.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fjesus on 25/05/2018.
 */

public class TimeLineResult implements Serializable {

    private List<Invoice> invoice;

    public TimeLineResult(){

    }

    public List<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(List<Invoice> invoice) {
        this.invoice = invoice;
    }
}
