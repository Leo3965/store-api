package br.com.store.storeapi.Integration;

import br.com.store.storeapi.data.dto.FiscalNoteResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FiscalNoteIntegration {
    public FiscalNoteResponse createNote() {
        try {
            var url = "http://localhost:3001/";
            var restTemplate = new RestTemplate();
            var request = new HttpEntity<>(null);
            var response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    FiscalNoteResponse.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
