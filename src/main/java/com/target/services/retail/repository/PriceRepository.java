package com.target.services.retail.repository;

import com.target.services.retail.model.PriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by PKomaravelli on 7/12/2018.
 */
public interface  PriceRepository extends MongoRepository<PriceEntity,Integer>{

    PriceEntity findByProductId(String productId);
}
