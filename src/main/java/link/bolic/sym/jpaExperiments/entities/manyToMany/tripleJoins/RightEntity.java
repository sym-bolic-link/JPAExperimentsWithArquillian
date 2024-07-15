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
public class RightEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToMany
	@JoinTable(name = "center_right_join_table", joinColumns = @JoinColumn(name = "right_id"), inverseJoinColumns = @JoinColumn(name = "center_id"))
	private List<CenterEntity> centerEntities;

	private String strField000;

	public String getStrField000() {
		return strField000;
	}

	@Override
	public String toString() {
		return "RightEntity [id=" + id + ", strField000=" + strField000 + "]";
	}

	public void setStrField000(String strField000) {
		this.strField000 = strField000;
	}

	public UUID getId() {
		return id;
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
