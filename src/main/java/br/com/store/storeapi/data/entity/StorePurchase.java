package br.com.store.storeapi.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "store-purchase")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    boolean isCreditCardValid;

    String fiscalNoteNumber;

    int cpf;

    String productId;

    int cardNumber;
}
