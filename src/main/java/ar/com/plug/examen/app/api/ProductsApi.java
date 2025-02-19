package ar.com.app.examen.app.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonRootName(value = "producto")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value=Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsApi {

	@JsonProperty(value = "idproduct")
	private Integer idproduct;
	
	@NotBlank
	@NotNull(message = "code required")
	@JsonProperty(value = "code")
	private String code;

	@NotBlank
	@NotNull(message = "description required")
	@JsonProperty(value = "description")
	private String description;

	@NotBlank
	@NotNull(message = "price required")
	@JsonProperty(value = "price")
	private Double price;

	@NotBlank
	@NotNull(message = "enabled required")
	@JsonProperty(value = "enabled")
	private Boolean enabled;
}
