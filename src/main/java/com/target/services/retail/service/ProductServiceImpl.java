package com.target.services.retail.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.target.services.retail.exception.RetailServiceException;
import com.target.services.retail.model.Price;
import com.target.services.retail.model.PriceEntity;
import com.target.services.retail.model.Product;
import com.target.services.retail.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PKomaravelli on 7/11/2018.
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {


    @Value("${target.redsky.url}")
    private URI baseUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PriceRepository repository;

    private String productName;
    private static final String TITLE= "title";
    private static final String PRODUCT_DESCRIPTION= "product_description";
    private static final String ITEM= "item";
    private static final List<String> EXCLUSIONLIST;


    static {
        List<String> exclusionList = new ArrayList<>();
        exclusionList.add("price");
        exclusionList.add("taxonomy");
        exclusionList.add("promotion");
        exclusionList.add("rating_and_review_statistics");
        exclusionList.add("bulk_ship");
        exclusionList.add("question_answer_statistics");
        exclusionList.add("rating_and_review_reviews");
        EXCLUSIONLIST = Collections.unmodifiableList(exclusionList);
    }


    @Override
    public Product getProductDetails(String productId) throws RetailServiceException {
    

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUri + productId + "/")
                .queryParam("excludes", String.join(",", EXCLUSIONLIST));

        URI uri = builder.build().encode().toUri();
        try {
            ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(uri, JSONObject.class);
            JSONObject responseJsonObject = responseEntity.getBody();
            JsonFactory jsonFactory = new JsonFactory();
            try {
                JsonParser jParser = jsonFactory.createJsonParser(responseJsonObject.toString());
                while (jParser.nextToken() != JsonToken.END_ARRAY) {
                    jParser.nextToken();
                    if (ITEM.equals(jParser.getCurrentName())) {
                        jParser.nextToken();
                        while (jParser.nextToken() != JsonToken.END_ARRAY) {
                            if (PRODUCT_DESCRIPTION.equals(jParser.getCurrentName())) {
                                jParser.nextToken();
                                while (jParser.nextToken() != JsonToken.END_ARRAY) {
                                    if (TITLE.equals(jParser.getCurrentName())) {
                                        jParser.nextToken();
                                        productName = jParser.getText();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (productName == null) {

                throw new RetailServiceException(String.format("Couldnot fetch product name for:%1$d", productId));
            }
            PriceEntity priceEntiy = repository.findByProductId(productId);
            if (priceEntiy == null) {
                throw new RetailServiceException(String.format("pricing details are not available for:%1$d", productId));
            }
            Product product = new Product();
            product.setId(productId);
            product.setName(productName);
            product.setCurrentPrice(Price.builder()
                    .value(priceEntiy.getValue())
                    .currencyCode(priceEntiy.getCurrencyCode())
                    .build());
            return product;
        }catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RetailServiceException(String.format("No data found for given productId:%1$s",productId),e);
            } else {
                throw new RetailServiceException(String.format("Third party service returned with negative response %1$d", e.getStatusCode()),e);
            }
        }


    }

    @Override
    public void updatePricingDetails(Product product) throws RetailServiceException {
        log.info("{}",repository.findAll());

        PriceEntity entity =repository.findByProductId(product.getId());
        if(entity == null ){
            throw new RetailServiceException(String.format("No records found for %1$d",product.getId()));
        }
        entity.setCurrencyCode(product.getCurrentPrice().getCurrencyCode());
        entity.setValue(product.getCurrentPrice().getValue());
        repository.save(entity);
    }
}
