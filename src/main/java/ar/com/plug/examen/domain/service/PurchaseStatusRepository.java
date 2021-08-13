package ar.com.app.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.app.examen.domain.model.PurchaseStatus;

public interface PurchaseStatusRepository extends JpaRepository<PurchaseStatus, Integer> {

}
