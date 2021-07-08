package com.nelhaouari.wefoxchallenge.service;

import com.nelhaouari.wefoxchallenge.model.PaymentLogError;

public interface PaymentLoggerService {

    void sendLogError(PaymentLogError paymentLogError);
}
