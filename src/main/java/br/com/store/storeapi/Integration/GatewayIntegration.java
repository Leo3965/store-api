package br.com.store.storeapi.Integration;

import br.com.store.storeapi.data.dto.GatewayRequest;
import br.com.store.storeapi.data.dto.GatewayResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GatewayIntegration {
    public GatewayResponse checkCreditCard(Integer cardNumber) {
        try {
            var url = "http://localhost:3000/";
            var restTemplate = new RestTemplate();
            var request = new HttpEntity<>(new GatewayRequest(cardNumber));
            var response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    GatewayResponse.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
