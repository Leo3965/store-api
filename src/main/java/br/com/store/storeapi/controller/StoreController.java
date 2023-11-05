package br.com.store.storeapi.controller;

import br.com.store.storeapi.data.dto.StoreRequest;
import br.com.store.storeapi.data.dto.StoreResponse;
import br.com.store.storeapi.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<StoreResponse> register(@RequestBody StoreRequest dto) {
        return ResponseEntity.ok(service.register(dto));
    }
}
