package ar.com.app.examen.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.app.examen.app.api.PurchaseStatusApi;
import ar.com.app.examen.app.exception.ExamenException;
import ar.com.app.examen.app.mapper.PurchaseEstatusMapper;
import ar.com.app.examen.domain.model.Purchases;
import ar.com.app.examen.domain.model.PurchaseStatus;
import ar.com.app.examen.domain.service.PurchasesRepository;
import ar.com.app.examen.domain.service.PurchaseStatusRepository;

@Service
public class PurchaseEstatusServiceImpl {
	
	@Autowired
	private PurchaseStatusRepository estadocomprasRepository;
	
	@Autowired
	private PurchasesRepository comprasRepository;

	@Transactional
	public PurchaseStatusApi saveEstadocompras(PurchaseStatusApi estadocomprasApi) throws Exception{
		try {
			Optional<Purchases> compras = comprasRepository.findById(estadocomprasApi.getIdpurchase());
			if(compras.isPresent()) {
				PurchaseEstatusMapper mapper = new PurchaseEstatusMapper();
				PurchaseStatus estadocompras = estadocomprasRepository.save(mapper.map(estadocomprasApi, PurchaseStatus.class));
				return mapper.map(estadocompras, PurchaseStatusApi.class); 
			} else {
				throw new ExamenException("No purchase was found with the id " + estadocomprasApi.getIdpurchase());
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
