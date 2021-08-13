package ar.com.app.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.app.examen.domain.model.Vendors;

public interface VendorsRepository extends JpaRepository<Vendors, Integer>{

}
