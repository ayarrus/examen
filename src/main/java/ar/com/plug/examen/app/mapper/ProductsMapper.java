package ar.com.app.examen.app.mapper;

import org.springframework.stereotype.Component;

import ar.com.app.examen.app.api.ProductsApi;
import ar.com.app.examen.domain.model.Products;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class ProductsMapper extends ConfigurableMapper{

	@Override
	protected void configure(MapperFactory factory) {
		factory.classMap(Products.class, ProductsApi.class).byDefault().register();
	}
	
}
