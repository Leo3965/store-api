package br.com.store.storeapi.service;

import br.com.store.storeapi.Integration.FiscalNoteIntegration;
import br.com.store.storeapi.Integration.GatewayIntegration;
import br.com.store.storeapi.data.dto.StoreRequest;
import br.com.store.storeapi.data.dto.StoreResponse;
import br.com.store.storeapi.data.entity.Log;
import br.com.store.storeapi.data.entity.StorePurchase;
import br.com.store.storeapi.data.repository.LogRepository;
import br.com.store.storeapi.data.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    private final GatewayIntegration gatewayIntegration;
    private final FiscalNoteIntegration fiscalNoteIntegration;
    private final StoreRepository repository;
    private final LogRepository logRepository;

    public StoreService(
            GatewayIntegration gatewayIntegration,
            FiscalNoteIntegration fiscalNoteIntegration,
            StoreRepository repository,
            LogRepository logRepository
    ) {
        this.gatewayIntegration = gatewayIntegration;
        this.fiscalNoteIntegration = fiscalNoteIntegration;
        this.repository = repository;
        this.logRepository = logRepository;
    }

    public StoreResponse register(StoreRequest dto) {
        var initialTransactionTime = System.currentTimeMillis();

        var initialGatewayTime = System.currentTimeMillis();
        var gatewayResponse = gatewayIntegration.checkCreditCard(dto.cardNumber());
        var finalGatewayTime = (System.currentTimeMillis() - initialGatewayTime) / 1000.0;

        var initialFiscalTime = System.currentTimeMillis();
        var fiscalNoteresponse = fiscalNoteIntegration.createNote();
        var finalFiscalTime = (System.currentTimeMillis() - initialFiscalTime) / 1000.0;

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
        var finalTransactionTime = (System.currentTimeMillis() - initialTransactionTime) / 1000.0;

        logRepository.save(
                Log.builder()
                        .fiscalNoteTime(finalFiscalTime)
                        .gatewayTime(finalGatewayTime)
                        .transactionTime(finalTransactionTime)
                        .purchaseId(purchase.getId())
                        .build()
        );

        return new StoreResponse(purchase);
    }
}
