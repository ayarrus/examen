package ar.com.app.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.app.examen.app.api.PurchasesApi;
import ar.com.app.examen.domain.model.Purchases;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class PurchasesMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Purchases.class, PurchasesApi.class)
		.field("client.idclient", "idclient")
		.field("product.idproduct", "idproduct")
		.field("seller.idseller", "idseller")
		.byDefault().register();
	}
	
}
