package br.com.store.storeapi.data.dto;

import br.com.store.storeapi.data.dto.entity.StorePurchase;

public record StoreResponse(
        Boolean isCreditCardValid,
        String fiscalNoteNumber,
        Integer clientCpf,
        String productId
) {
    public StoreResponse(StorePurchase purchase) {
        this(purchase.isCreditCardValid(), purchase.getFiscalNoteNumber(), purchase.getCpf(), purchase.getProductId());
    }
}
