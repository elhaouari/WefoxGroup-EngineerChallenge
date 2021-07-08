package com.nelhaouari.wefoxchallenge.service;

import com.nelhaouari.wefoxchallenge.model.PaymentDTO;

public interface PaymentService {

    public void handleIncomingPayment(PaymentDTO payment);

    public boolean isPaymentValid(PaymentDTO payment);

}
