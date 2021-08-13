package ar.com.app.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.app.examen.app.api.PurchasesApi;
import ar.com.app.examen.app.mapper.PurchasesMapper;
import ar.com.app.examen.domain.model.PurchaseStatus;
import ar.com.app.examen.domain.model.Purchases;
import ar.com.app.examen.domain.service.PurchaseStatusRepository;
import ar.com.app.examen.domain.service.PurchasesRepository;

@Service
public class PurchasesServiceImpl {
	
	@Autowired
	private PurchasesRepository comprasRepository;
	@Autowired
	private PurchaseStatusRepository estadocomprasRepository;

	@Transactional
	public PurchasesApi savePurchase(PurchasesApi comprasApi) throws Exception{
		PurchasesMapper mapper = new PurchasesMapper();
		Purchases compras = mapper.map(comprasApi, Purchases.class);
		Purchases comprasSave = comprasRepository.save(compras);
		PurchaseStatus estadocompras = new PurchaseStatus();
		estadocompras.setPurchase(comprasSave);
		estadocompras.setState(1);
		estadocompras.setStatedate(comprasSave.getPurchasedate());
		estadocomprasRepository.save(estadocompras);
		return mapper.map(comprasSave, PurchasesApi.class);
	}

	public List<PurchasesApi> getPurchases() {
		PurchasesMapper mapper = new PurchasesMapper();
		return mapper.mapAsList(comprasRepository.findAll(), PurchasesApi.class);
	}

	public PurchasesApi getPurchasesById(Integer idCompra) {
		PurchasesMapper mapper = new PurchasesMapper();
		Purchases compras = comprasRepository.findById(idCompra).orElse(null);
		return mapper.map(compras, PurchasesApi.class); 
	}
}
