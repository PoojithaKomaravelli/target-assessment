package com.target.services.retail.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.ResourceSupport;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by PKomaravelli on 7/11/2018.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("product")
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class Product  implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty(value = "id", required = true)
    @ApiModelProperty(example = "12345678", required = true)
    @NonNull
    @NotNull
    @Nonnull
    private int id;

    @JsonProperty(value = "name", required = true)
    @ApiModelProperty(example = "The Big Lebowski (Blu-ray) (Widescreen)", required = true)
    @NonNull
    @NotNull
    @Nonnull
    private String name;

    @JsonProperty(value = "current_price", required = true)
    @ApiModelProperty(required = true)
    @Nonnull
    private Price currentPrice;


}
