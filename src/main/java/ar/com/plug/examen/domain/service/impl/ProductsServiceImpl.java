package ar.com.app.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.app.examen.app.api.MessageApi;
import ar.com.app.examen.app.api.ProductsApi;
import ar.com.app.examen.app.exception.ExamenException;
import ar.com.app.examen.app.mapper.ProductsMapper;
import ar.com.app.examen.domain.model.Products;
import ar.com.app.examen.domain.service.ProductRepository;

@Service
public class ProductsServiceImpl {
	
	@Autowired
	private ProductRepository productoRepository;

	@Transactional
	public ProductsApi saveProducts(ProductsApi productosApi) throws Exception {
		ProductsMapper mapper = new ProductsMapper();
		Products prod = mapper.map(productosApi, Products.class);
		Products prod2 = productoRepository.save(prod);
		return mapper.map(prod2, ProductsApi.class);
	}

	public List<ProductsApi> getProducts() {
		ProductsMapper mapper = new ProductsMapper();
		return mapper.mapAsList(productoRepository.findAll(), ProductsApi.class);
	}

	public ProductsApi getProductById(Integer idProducto) {
		ProductsMapper mapper = new ProductsMapper();
		Products prod = productoRepository.findById(idProducto).orElse(null);
		return mapper.map(prod, ProductsApi.class);
	}

	@Transactional
	public ProductsApi updateProducts(ProductsApi productos) throws Exception {
		ProductsMapper mapper = new ProductsMapper();
		Products producto = productoRepository.findById(productos.getIdproduct()).orElse(null);
		if(producto != null && producto.getIdproduct() != null) {
			producto.setCode(productos.getCode());
			producto.setDescription(productos.getDescription());
			producto.setEnabled(productos.getEnabled());
			producto.setPrice(productos.getPrice());
		}
		return mapper.map(productoRepository.save(producto), ProductsApi.class);
	}
	
	@Transactional
	public MessageApi deleteProductById(Integer idProducto) throws Exception{
		if(idProducto == null) {
			throw new ExamenException("The productid field cannot be null.");
		}
		MessageApi api = new MessageApi();
		Products producto = productoRepository.findById(idProducto).orElse(null);
		if(producto != null && producto.getIdproduct() != null) {
			productoRepository.deleteById(idProducto);
			api.setMessage("product removed successfully.");
		} else {
			api.setMessage("Product with the id " + idProducto + " Not found in the system.");
		}

		return api;
	}
}
