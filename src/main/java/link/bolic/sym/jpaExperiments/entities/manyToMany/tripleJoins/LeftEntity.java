package link.bolic.sym.jpaExperiments.entities.manyToMany.tripleJoins;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class LeftEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToMany()
	@JoinTable(name = "left_center_join_table",
		joinColumns = @JoinColumn (name = "left_id"),
		inverseJoinColumns = @JoinColumn (name = "center_id"))
	private List<CenterEntity> centerEntities;

	public UUID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "LeftEntity [id=" + id + "]";
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<CenterEntity> getCenterEntities() {
		return centerEntities;
	}

	public void setCenterEntities(List<CenterEntity> centerEntities) {
		this.centerEntities = centerEntities;
	}
}
