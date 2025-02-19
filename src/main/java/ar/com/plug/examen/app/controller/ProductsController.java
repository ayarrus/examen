package ar.com.app.examen.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.app.examen.app.api.ProductsApi;
import ar.com.app.examen.app.config.ConstantsMessage;
import ar.com.app.examen.domain.model.ErrorObject;
import ar.com.app.examen.domain.service.impl.ProductsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/products")
@Slf4j
@Tag(name = "Products", description = "ABM Products")
public class ProductsController {

	@Autowired
	private ProductsServiceImpl service;

	@Operation(
			summary = "Create Products ",
			description = "Product creation .",
			tags = "Products"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_CREATE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ProductsApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)
    @PostMapping(path = "/addproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody ProductsApi products)throws Exception{
		log.info("[addProduct] adicionando producto.");
		return new ResponseEntity<>(service.saveProducts(products), HttpStatus.CREATED);
    }

	@Operation(
			summary = "Returns all products ",
			description = "consult all products. ",
			tags = "Products"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ProductsApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @GetMapping(path = "/getproducts")
    public ResponseEntity<?> getProducts(){
		log.info("[getProducts] consultando todos los productos.");
		return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

	@Operation(
			summary = "Returns product by id ",
			description = "consult product by ID. ",
			tags = "Products"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ProductsApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @GetMapping(path = "/getproduct/{idproducto}")
    public ResponseEntity<?> getProduct(@PathVariable Integer idproducto){
		log.info("[getProduct] consultando producto por id.");
		ProductsApi productos = service.getProductById(idproducto);
		
		if(productos != null) {
			return new ResponseEntity<>(productos, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No product found with the code "+ idproducto, HttpStatus.BAD_REQUEST);
		}
    }

	@Operation(
			summary = "update product information ",
			description = "update product by id .",
			tags = "Products"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ProductsApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @PutMapping(path = "/updateproduct", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody ProductsApi products) throws Exception{
		log.info("[updateProduct] actualizando producto.");
		ProductsApi productos = service.updateProducts(products);
		
		if(productos == null) {
			return new ResponseEntity<>("No vendor found with the name" + products.getDescription(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(productos, HttpStatus.CREATED);
    }

	@Operation(
			summary = "remove product by id ",
			description = "remove product by id .",
			tags = "Products"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "202",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ProductsApi.class)
									)
							)
					),
                    @ApiResponse(
                            responseCode = "400",
                            description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_BAD_REQUEST,
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
			}
	)	
    @DeleteMapping(path = "/deleteproduct/{idproducto}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer idproducto)throws Exception{
		log.info("[deleteProduct] eliminando producto.");
		return new ResponseEntity<>(service.deleteProductById(idproducto), HttpStatus.ACCEPTED);
    }

}
