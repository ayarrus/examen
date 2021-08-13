package ar.com.app.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.app.examen.domain.model.Purchases;

public interface PurchasesRepository extends JpaRepository<Purchases, Integer>{

}
