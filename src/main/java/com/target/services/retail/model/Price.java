package com.target.services.retail.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by PKomaravelli on 7/11/2018.
 */
@Builder
@Data
public class Price implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty(value = "value", required = true)
    @ApiModelProperty(example = "12.0", required = true)
    private BigDecimal value;

    @JsonProperty(value = "currency_code", required = true)
    @ApiModelProperty(example = "INR", required = true)
    private String currencyCode;
}
