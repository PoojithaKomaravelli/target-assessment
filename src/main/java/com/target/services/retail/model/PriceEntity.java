package com.target.services.retail.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


/**
 * Created by PKomaravelli on 7/12/2018.
 */
@Document
@Data
@RequiredArgsConstructor
public class PriceEntity {

    @Id
    private String id;

    @NonNull
    @Indexed(unique = true)
    private String productId;

    @NonNull
    private String currencyCode;

    @NonNull
    private BigDecimal value;
}
