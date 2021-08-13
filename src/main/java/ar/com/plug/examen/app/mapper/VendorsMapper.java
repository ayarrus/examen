package ar.com.app.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.app.examen.app.api.VendorsApi;
import ar.com.app.examen.domain.model.Vendors;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class VendorsMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Vendors.class, VendorsApi.class).byDefault().register();
	}
	
}
