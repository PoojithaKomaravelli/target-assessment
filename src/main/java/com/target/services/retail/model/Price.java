package com.target.services.retail.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.hateoas.ResourceSupport;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by PKomaravelli on 7/11/2018.
 */
@Builder
@Data
@JsonRootName("current_price")
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Price implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty(value = "value", required = true)
    @ApiModelProperty(example = "12.0", required = true)
    @NonNull
    @Nonnull
    @NotNull
    private BigDecimal value;

    @JsonProperty(value = "currency_code", required = true)
    @ApiModelProperty(example = "INR", required = true)
    @NonNull
    @Nonnull
    @NotNull
    private String currencyCode;
}
