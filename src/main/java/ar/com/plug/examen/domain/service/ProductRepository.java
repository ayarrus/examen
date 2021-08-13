package ar.com.app.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.app.examen.domain.model.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{

}
