package com.target.services.retail.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false, of = {"message"})
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class BasicResponseResource extends ResourceSupport implements Serializable {

	public static final long serialVersionUID = 1L;

	@Nullable
	private String message;

	@Nonnull
	public static BasicResponseResource withMessage(@Nullable String message) {
		return new BasicResponseResource(message);
	}

}
