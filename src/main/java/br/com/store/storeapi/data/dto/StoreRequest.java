package br.com.store.storeapi.data.dto;

public record StoreRequest(
        Integer cpf,
        Integer cardNumber,
        String productId
) {
}
