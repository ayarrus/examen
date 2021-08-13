package ar.com.app.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.app.examen.app.api.ClientsApi;
import ar.com.app.examen.domain.model.Clients;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ClientsMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Clients.class, ClientsApi.class).byDefault().register();
	}
	
}
