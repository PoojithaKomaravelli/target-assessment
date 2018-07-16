package com.target.services.retail.service;

import com.target.services.retail.exception.RetailServiceException;
import com.target.services.retail.model.Product;
import org.springframework.stereotype.Service;

/**
 * Created by PKomaravelli on 7/11/2018.
 */
@Service
public interface ProductService {

    Product getProductDetails(int productId) throws RetailServiceException;

    void updatePricingDetails(Product product) throws RetailServiceException;
}
