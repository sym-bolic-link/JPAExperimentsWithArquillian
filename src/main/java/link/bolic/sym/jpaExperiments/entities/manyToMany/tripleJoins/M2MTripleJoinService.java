package link.bolic.sym.jpaExperiments.entities.manyToMany.tripleJoins;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Transactional(value = TxType.REQUIRED)
public class M2MTripleJoinService {

	@PersistenceContext(unitName = "M2MTripleJoinServicePersistenceUnit")
	private EntityManager em;

	public void persist(CenterEntity e) {
		em.persist(e);
	}

	public void persist(LeftEntity e) {
		em.persist(e);
	}

	public void persist(RightEntity e) {
		em.persist(e);
	}
	
	public List<Tuple> joinQuery(List<String> centerEntityStrField000Valeus, List<String> rightEntityIds) {
		TypedQuery<Tuple> query = em.createQuery("SELECT le, leftCenter "
				+ "FROM LeftEntity le LEFT JOIN le.centerEntities leftCenter "
				+ "LEFT JOIN leftCenter.rightEntities leftCenterRight "
				+ "WHERE leftCenter.strField000 in (?1) AND leftCenterRight.strField000 in (?2)", Tuple.class);
		
		query.setParameter(1, centerEntityStrField000Valeus);
		query.setParameter(2, rightEntityIds);
		return query.getResultList();
	}

}
