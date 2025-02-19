package ar.com.app.examen.app.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.collections.Lists;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.com.app.examen.app.api.VendorsApi;
import ar.com.app.examen.domain.service.impl.VendorsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VendorsControllerTest extends AbstractControllerTest{

	public VendorsControllerTest() throws Exception {
		super();
	}

	private static final String URLADDCLIENT = "/api/v1/vendors/addseller";
	private static final String URLGETCLIENTS = "/api/v1/vendors/getsellers";
	private static final String URLGETCLIENT = "/api/v1/vendors/getseller";
	private static final String URLPUTCLIENT = "/api/v1/vendors/updateseller";
	private static final String URLDELCLIENT = "/api/v1/vendors/deleteseller";
	
	@InjectMocks
	private VendorsController vendedoresController;
	
	@Mock
	private VendorsServiceImpl service;

	@Override
	protected Object getTarget() {
		return vendedoresController;
	}

	@Test
	public void testAddseller() throws Exception{
		
		final VendorsApi cliente = RANDOM.nextObject(VendorsApi.class);
		final VendorsApi response = RANDOM.nextObject(VendorsApi.class);
		
		Mockito.when(service.saveVendors(cliente)).thenReturn(response);
		
        assertEquals(perform(post(URLADDCLIENT), cliente, new TypeReference<VendorsApi>() {}, status().isCreated()), response);
	}

	@Test
	public void testGetsellers() {
		
		final List<VendorsApi> response = Lists.newArrayList(RANDOM.nextObject(VendorsApi.class));
        
		Mockito.when(service.getVendors()).thenReturn(response);
        
		assertEquals(perform(get(URLGETCLIENTS), null, new TypeReference<List<VendorsApi>>() {}, status().isOk()), response);
	}

	@Test
	public void testGetseller() {
		
		final VendorsApi cliente = RANDOM.nextObject(VendorsApi.class);
		final VendorsApi response = RANDOM.nextObject(VendorsApi.class);
		
		Mockito.when(service.getVendorsById(cliente.getIdseller())).thenReturn(response);
		
        assertEquals(perform(get(URLGETCLIENT + "/"+ cliente.getIdseller()), null, new TypeReference<VendorsApi>() {}, status().isOk()), response);
	}

	@Test
	public void testUpdateseller() throws Exception {
		final VendorsApi cliente = RANDOM.nextObject(VendorsApi.class);
		final VendorsApi response = RANDOM.nextObject(VendorsApi.class);
		
		Mockito.when(service.updateVendors(cliente)).thenReturn(response);
		
        assertEquals(perform(put(URLPUTCLIENT), cliente, new TypeReference<VendorsApi>() {}, status().isCreated()), response);
	}
}
