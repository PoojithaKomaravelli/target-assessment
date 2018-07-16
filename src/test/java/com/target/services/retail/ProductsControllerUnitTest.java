package com.target.services.retail;

import com.target.services.retail.model.BasicResponseResource;
import com.target.services.retail.model.Product;
import com.target.services.retail.service.ProductService;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by PKomaravelli on 7/15/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerUnitTest {


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private ProductsController controller;

    @Mock
    private ProductService productService;



    @Test
    public void testGetProductById() throws Exception{

        // given
        given(productService.getProductDetails(TestData.TEST_PRODUCT_ID)).willReturn(Product.builder().id(TestData.TEST_PRODUCT_ID).name(TestData.TEST_PRODUCT_NAME).currentPrice(TestData.buildProductPrice()).build());

        // when
        ResponseEntity<Product> result = (ResponseEntity<Product>) controller.getProductById(TestData.TEST_PRODUCT_ID);

        // then
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody().getName(), is(TestData.TEST_PRODUCT_NAME));
    }

    @Test
    public void testUpdatePricingDetails() throws Exception{

        // when
        HttpEntity<BasicResponseResource> response= controller.updatePricingDetails(TestData.TEST_PRODUCT_ID,TestData.buildProductEntity());


        assertThat(response, is(CoreMatchers.<HttpEntity<BasicResponseResource>>equalTo(
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(BasicResponseResource.withMessage("Updates applied.")))));
    }


}
