package br.com.store.storeapi.data.dto;

public record GatewayResponse(
        Integer card,
        Boolean valid
) {
}
