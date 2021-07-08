package com.nelhaouari.wefoxchallenge.service.impl;

import com.nelhaouari.wefoxchallenge.model.PaymentLogError;
import com.nelhaouari.wefoxchallenge.service.PaymentLoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentLoggerServiceImpl implements PaymentLoggerService {

    Logger logger = LoggerFactory.getLogger(PaymentLoggerServiceImpl.class);

    @Override
    public void sendLogError(PaymentLogError paymentLogError) {
        logger.info("Sending error to the logger ...");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentLogError> entity = new HttpEntity<>(paymentLogError, headers);

        RestTemplate restTemplate = new RestTemplate();
        String HOST_PAYMENT_LOGGER = "http://localhost:9000/log";
        restTemplate.exchange(HOST_PAYMENT_LOGGER,
                HttpMethod.POST,
                entity,
                String.class);

        logger.info("error sent ");
    }
}
