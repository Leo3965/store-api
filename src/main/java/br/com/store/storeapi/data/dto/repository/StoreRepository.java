package br.com.store.storeapi.data.dto.repository;

import br.com.store.storeapi.data.dto.entity.StorePurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StorePurchase, Long> {
}
