package ar.com.app.examen.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.app.examen.app.api.ClientsApi;
import ar.com.app.examen.app.config.ConstantsMessage;
import ar.com.app.examen.domain.model.ErrorObject;
import ar.com.app.examen.domain.service.impl.ClientsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/clients")
@AllArgsConstructor
@Slf4j
@Tag(name = "Clients", description = "ABM Clients")
@Validated
public class ClientsController {

	@Autowired
	private ClientsServiceImpl service;

	private final String IPGOOGLECLOUD = "104.197.232.113";
	private final String IPGOOGLECLOUD_LOCAL = "0:0:0:0:0:0:0:1";
	private final Integer TIPOINSTALCIONCLOUD = 91;

	@Operation(
			summary = ConstantsMessage.SWAGGER_INFO_CLIENT_SUMMARY,
			description = ConstantsMessage.SWAGGER_INFO_CLIENT_DESCRIPTION,
			tags = "Clients"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "201",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE_CREATE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ClientsApi.class)
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
    @PostMapping(path = "/addclient", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addclient(@Valid @RequestBody ClientsApi clients, HttpServletRequest request)throws Exception{
			log.info("[addclient] adicionando clientes.");
			
    		System.out.println(request.getRemoteAddr());
    		System.out.println(request.getRemoteHost());

			return new ResponseEntity<>(service.saveClients(clients), HttpStatus.CREATED);
    }

	@Operation(
			summary = "Returns all clients ",
			description = "consult all clients.",
			tags = "Clients"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ClientsApi.class)
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
    @GetMapping(path = "/getclients")
    public ResponseEntity<?> getclients(HttpServletRequest request){
    		log.info("[getclients] consultando todos los clientes.");

    		System.out.println(request.getRemoteAddr());
    		System.out.println(request.getRemoteHost());
    		
			if(request != null && request.getRemoteAddr() != null) {
			
				System.out.println("el request no esta vacio");
				
				if(request.getRemoteAddr().equals(IPGOOGLECLOUD) && request.getRemoteHost().equals(IPGOOGLECLOUD)){
					
					System.out.println("la ip es igual a IPGOOGLECLOUD : " + IPGOOGLECLOUD);
				}
				
				if(request.getRemoteAddr().equals(IPGOOGLECLOUD_LOCAL) && !request.getRemoteHost().equals(IPGOOGLECLOUD_LOCAL)) {
					
					System.out.println("la ip es igual aIPGOOGLECLOUD_LOCAL: " + IPGOOGLECLOUD_LOCAL);
				}
				
				
				if((!request.getRemoteAddr().equals(IPGOOGLECLOUD) && !request.getRemoteHost().equals(IPGOOGLECLOUD))
								&& (!request.getRemoteAddr().equals(IPGOOGLECLOUD_LOCAL) && !request.getRemoteHost().equals(IPGOOGLECLOUD_LOCAL))){
					
					System.out.println("801 - No tiene permisos para consumir los servicios de facturacion eléctronica.");
				}				
			}
    		
    		return new ResponseEntity<>(service.getClients(), HttpStatus.OK);
    }

	@Operation(
			summary = "Returns customer by id ",
			description = "consult client by ID. ",
			tags = "Clients"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ClientsApi.class)
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
    @GetMapping(path = "/getclient/{idcliente}")
    public ResponseEntity<?> getclient(@Valid @PathVariable Integer idcliente){
		log.info("[getclient] consultando cliente por id.");
		ClientsApi clientes = service.getClientsById(idcliente);
		if(clientes != null) {
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No customer found with code  "+ idcliente, HttpStatus.BAD_REQUEST);
		}
    }

	@Operation(
			summary = "update customer information ",
			description = "update client by ID.",
			tags = "Clients"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "200",
							description = ConstantsMessage.SWAGGER_INFO_APIRESPONSE,
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ClientsApi.class)
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
    @PutMapping(path = "/updateclient", produces = {MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateclient(@Valid @RequestBody ClientsApi clientes) throws Exception{
		log.info("[updateclient] actualizando cliente.");
		ClientsApi clientes2 = service.updateClients(clientes);
		
		if(clientes2 == null) {
			return new ResponseEntity<>("No vendor found with the name " + clientes.getName(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(clientes2, HttpStatus.CREATED);
    }

	@Operation(
			summary = "eliminar cliente por id",
			description = "elimina cliente por Id.",
			tags = "Clientes"
	)
	@ApiResponses(
			value = {
					@ApiResponse(
							responseCode = "202",
							description = "Request is sccuessful - accepted",
							content = @Content(
									array = @ArraySchema(
											schema = @Schema(implementation = ClientsApi.class)
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
    @DeleteMapping(path = "/deleteclient/{idcliente}")
    public ResponseEntity<?> deleteclient(@Valid @PathVariable Integer idcliente)throws Exception{
		log.info("[deleteclient] eliminando cliente.");
		return new ResponseEntity<>(service.deleteclientById(idcliente), HttpStatus.ACCEPTED);
    }
}
