package ar.com.app.examen.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "purchases")
@NoArgsConstructor
@AllArgsConstructor
public class Purchases implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(
            description = "Creation of Purchases",
            example = "1",
            required = true
    )	
	@Id
	@GeneratedValue
	private Integer idpurchase;
	
	@Schema(
            description = "Purchase ID",
            example = "1",
            required = true
    )	
	//@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="idclientpurchase")
	private Clients client;

	@Schema(
            description = "product id ",
            example = "3",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idproductpurchase")
	private Products product;

	@Schema(
            description = "id del vendedor",
            example = "1111334444",
            required = true
    )	
	@ManyToOne
	@JoinColumn(name="idsellerpurchase")
	private Vendors seller;
	
	@Schema(
            description = "date of purchase",
            example = "2021-08-01",
            required = true
    )	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date purchasedate;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchases other = (Purchases) obj;
		if (idpurchase == null) {
			if (other.idpurchase != null)
				return false;
		} else if (!idpurchase.equals(other.idpurchase))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idpurchase == null) ? 0 : idpurchase.hashCode());
		return result;
	}
}
