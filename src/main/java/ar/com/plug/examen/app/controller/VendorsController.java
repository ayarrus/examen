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

import ar.com.app.examen.app.api.VendorsApi;
import ar.com.app.examen.app.config.ConstantsMessage;
import ar.com.app.examen.domain.model.ErrorObject;
import ar.com.app.examen.domain.service.impl.VendorsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/vendors")
@Slf4j
@Tag(name = "Vendors", description = "ABM Vendors")
public class VendorsController {

	@Autowired
	private VendorsServiceImpl service;

	@Operation(
			summary = "Create Sellers ",
			description = "Creation of Sellers .",
			tags = "Vendors"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_CREATE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = VendorsApi.class)
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
    @PostMapping(path = "/addseller", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addseller(@RequestBody VendorsApi sellers)throws Exception{
		log.info("[addseller] adicionando vendedor.");
		return new ResponseEntity<>(service.saveVendors(sellers), HttpStatus.CREATED);
    }
    
	@Operation(
			summary = "Returns all sellers ",
			description = "consult all sellers. ",
			tags = "Vendors"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = VendorsApi.class)
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
    @GetMapping(path = "/getsellers")
    public ResponseEntity<?> getsellers(){
		log.info("[getsellers] consultando todos los vendedores.");
		return new ResponseEntity<>(service.getVendors(), HttpStatus.OK);
    }

	@Operation(
			summary = "Returns seller by id ",
			description = "consult seller by ID. ",
			tags = "Vendors"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = VendorsApi.class)
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
    @GetMapping(path = "/getseller/{idsellere}")
    public ResponseEntity<?> getseller(@PathVariable Integer idsellere){
		log.info("[getseller] consultando vendedor por id.");
		VendorsApi vendedores = service.getVendorsById(idsellere);
		
		if(vendedores != null){
			return new ResponseEntity<>(vendedores, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No vendor found with code" + idsellere, HttpStatus.BAD_REQUEST);
		}
    }

	@Operation(
			summary = "update seller information ",
			description = "update seller by id .",
			tags = "Vendors"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = VendorsApi.class)
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
    @PutMapping(path = "/updateseller", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateseller(@RequestBody VendorsApi vendors) throws Exception{
		log.info("[updateseller] actualizando vendedor.");
		VendorsApi vendedores = service.updateVendors(vendors);
		
		if(vendedores == null) {
			return new ResponseEntity<>("No vendor found with the name " + vendors.getName(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(service.updateVendors(vendors), HttpStatus.CREATED);
    }

	@Operation(
			summary = "remove seller by id ",
			description = "remove seller by id .",
			tags = "Vendors"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "202",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = VendorsApi.class)
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
    @DeleteMapping(path = "/deleteseller/{idsellere}")
    public ResponseEntity<?> deleteseller(@PathVariable Integer idsellere)throws Exception{
		log.info("[deleteseller] eliminando vendedor.");
		return new ResponseEntity<>(service.deleteVendorsById(idsellere), HttpStatus.ACCEPTED);
    }
    
}
