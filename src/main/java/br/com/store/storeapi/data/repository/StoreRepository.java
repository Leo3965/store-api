package br.com.store.storeapi.data.repository;

import br.com.store.storeapi.data.entity.StorePurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StorePurchase, Long> {
}
