package ar.com.app.examen.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Clients implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(
            description = "Customer creation ",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idclient;
	@Schema(
            description = "Customer document ",
            example = "123456789",
            required = true
    )	
	@Column(nullable = false, length = 15)
	private String document;
	
	@Schema(
            description = "Customer name ",
            example = "Agustin Yarrus",
            required = true
    )	
	@Column(nullable = false, length = 200)
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clients other = (Clients) obj;
		if (idclient == null) {
			if (other.idclient != null)
				return false;
		} else if (!idclient.equals(other.idclient))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idclient == null) ? 0 : idclient.hashCode());
		return result;
	}
	
	
}
