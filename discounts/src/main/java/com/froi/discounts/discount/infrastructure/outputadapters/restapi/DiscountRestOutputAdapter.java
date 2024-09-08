package com.froi.discounts.discount.infrastructure.outputadapters.restapi;

import com.froi.discounts.discount.infrastructure.outpuports.restapi.FindBillsInfoOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Component
public class DiscountRestOutputAdapter implements FindBillsInfoOutputPort {

    @Value("${payments.url}")
    private String paymentsUrl;

    private RestTemplate restTemplate;

    @Autowired
    public DiscountRestOutputAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean findCustomerDiscountAvailability(String customerId) {
        String url = paymentsUrl + "/v1/bills/isOneOfTheBest/" + customerId;
        return findEntity(url);
    }

    private boolean findEntity(String url) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<Void> response = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.HEAD,
                    requestEntity,
                    Void.class
            );
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
