package br.com.store.storeapi.service;

import br.com.store.storeapi.Integration.FiscalNoteIntegration;
import br.com.store.storeapi.Integration.GatewayIntegration;
import br.com.store.storeapi.data.dto.StoreRequest;
import br.com.store.storeapi.data.dto.StoreResponse;
import br.com.store.storeapi.data.entity.StorePurchase;
import br.com.store.storeapi.data.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    private final GatewayIntegration gatewayIntegration;
    private final FiscalNoteIntegration fiscalNoteIntegration;
    private final StoreRepository repository;

    public StoreService(
            GatewayIntegration gatewayIntegration,
            FiscalNoteIntegration fiscalNoteIntegration,
            StoreRepository repository
    ) {
        this.gatewayIntegration = gatewayIntegration;
        this.fiscalNoteIntegration = fiscalNoteIntegration;
        this.repository = repository;
    }

    public StoreResponse register(StoreRequest dto) {
        var gatewayResponse = gatewayIntegration.checkCreditCard(dto.cardNumber());
        var fiscalNoteresponse = fiscalNoteIntegration.createNote();
        var purchase = repository.save(
                StorePurchase.builder()
                        .fiscalNoteNumber(fiscalNoteresponse.fiscalNote())
                        .isCreditCardValid(gatewayResponse.valid())
                        .cpf(dto.cpf())
                        .cardNumber(dto.cardNumber())
                        .fiscalNoteNumber(fiscalNoteresponse.fiscalNote())
                        .productId(dto.productId())
                        .build()
        );
        return new StoreResponse(purchase);
    }
}
