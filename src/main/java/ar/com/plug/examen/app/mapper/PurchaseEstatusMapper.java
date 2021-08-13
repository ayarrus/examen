package ar.com.app.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.app.examen.app.api.PurchaseStatusApi;
import ar.com.app.examen.domain.model.PurchaseStatus;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class PurchaseEstatusMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(PurchaseStatus.class, PurchaseStatusApi.class)
		.field("purchase.idpurchase", "idpurchase")
		.byDefault().register();
	}
	
}
