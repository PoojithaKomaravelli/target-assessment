package com.target.services.retail;

import com.target.services.retail.model.Price;
import com.target.services.retail.model.Product;

import java.math.BigDecimal;

/**
 * Created by PKomaravelli on 7/15/2018.
 */
public class TestData {


    public  static final String TEST_PRODUCT_ID = "123456";
    public static final String  TEST_PRODUCT_NAME = "product-name";

    public static  Price buildProductPrice(){

        return Price.builder().currencyCode("INR").value(new BigDecimal(123.05)).build();
    }

    public static Product buildProductEntity(){

        return Product.builder().currentPrice(buildProductPrice()).id(Integer.parseInt(TEST_PRODUCT_ID)).name(TEST_PRODUCT_NAME).build();
    }
}
