package auction.domain;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Furniture extends Item implements Serializable{
	private String madeFrom;

	public Furniture(User seller, Category category, String description, String madeFrom) {
		super(seller, category, description);
		this.madeFrom = madeFrom;
	}

	public Furniture() {
	}
}
