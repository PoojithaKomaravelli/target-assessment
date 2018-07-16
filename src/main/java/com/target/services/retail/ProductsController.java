package com.target.services.retail;

import com.target.services.retail.exception.RetailServiceException;
import com.target.services.retail.model.BasicResponseResource;
import com.target.services.retail.model.Product;
import com.target.services.retail.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PKomaravelli on 7/11/2018.
 */
@RestController
@RequestMapping(value="/api/products")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class ProductsController {

    @NonNull
    ProductService productService;

    @ApiOperation(value = "Get product details")
    @RequestMapping(method = RequestMethod.GET,
            path = "/{id}")
    public HttpEntity<Product> getProductById(@PathVariable("id") String productId) throws RetailServiceException {

        Product product = productService.getProductDetails(productId);

        return ResponseEntity.ok(product);
    }

    @ApiOperation(value = "update Pricing details")
    @RequestMapping(method = RequestMethod.PUT,
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicResponseResource> updatePricingDetails(@PathVariable("id") String productId,
            @Validated @RequestBody Product product) throws RetailServiceException {

        if (!StringUtils.equalsIgnoreCase(productId, product.getId())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(BasicResponseResource.withMessage("The URL does not match the input data product Id."));
        }
         productService.updatePricingDetails(product);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BasicResponseResource.withMessage("Updates applied."));
    }
}
