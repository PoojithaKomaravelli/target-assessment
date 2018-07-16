package com.target.services.retail.repository;

import com.target.services.retail.model.PriceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by PKomaravelli on 7/12/2018.
 */
public interface  PriceRepository extends MongoRepository<PriceDocument,Integer>{

    PriceDocument findByProductId(String productId);
}
