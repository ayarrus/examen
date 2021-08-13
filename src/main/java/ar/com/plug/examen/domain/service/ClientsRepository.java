package ar.com.app.examen.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.app.examen.domain.model.Clients;

public interface ClientsRepository extends JpaRepository<Clients, Integer>{

	public Clients findByDocument(String document);
}
