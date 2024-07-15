package link.bolic.sym.jpaExperiments.entities.manyToMany.tripleJoins;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class CenterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStrField000() {
		return strField000;
	}

	@Override
	public String toString() {
		return "CenterEntity [id=" + id + ", strField000=" + strField000 + "]";
	}

	public void setStrField000(String strField000) {
		this.strField000 = strField000;
	}

	public List<LeftEntity> getLeftEntites() {
		return leftEntites;
	}

	public void setLeftEntites(List<LeftEntity> leftEntites) {
		this.leftEntites = leftEntites;
	}

	public List<RightEntity> getRightEntities() {
		return rightEntities;
	}

	public void setRightEntities(List<RightEntity> rightEntities) {
		this.rightEntities = rightEntities;
	}

	private String strField000;
	
	@ManyToMany(mappedBy = "centerEntities")
	private List<LeftEntity> leftEntites;
	
	@ManyToMany(mappedBy = "centerEntities")
	private List<RightEntity> rightEntities;
}
