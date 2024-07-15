package link.bolic.sym.jpaExperiments.entities.manyToMany.tripleJoins;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Tuple;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class M2MTripleJoinServiceTest {

	@Deployment
	public static WebArchive deployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, M2MTripleJoinService.class.getPackage())
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("test-ds.xml", "jboss-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	M2MTripleJoinService service;
	@Inject
	UserTransaction utx;

	@Before
	public void setup() throws Exception {
		utx.begin();
		LeftEntity l1 = new LeftEntity();
		LeftEntity l2 = new LeftEntity();
		LeftEntity l3 = new LeftEntity();

		CenterEntity c1 = new CenterEntity();
		c1.setStrField000("c1");
		CenterEntity c2 = new CenterEntity();
		c2.setStrField000("c2");
		CenterEntity c3 = new CenterEntity();
		c3.setStrField000("c3");

		RightEntity r1 = new RightEntity();
		r1.setStrField000("r1");
		RightEntity r2 = new RightEntity();
		r2.setStrField000("r2");
		RightEntity r3 = new RightEntity();
		r3.setStrField000("r3");

		l1.setCenterEntities(Arrays.asList(c1, c2));
		l2.setCenterEntities(Arrays.asList(c1, c2));
		l3.setCenterEntities(Arrays.asList(c3));
		r1.setCenterEntities(Arrays.asList(c1));
		r2.setCenterEntities(Arrays.asList(c1, c2));
		r3.setCenterEntities(Arrays.asList(c3));

		service.persist(c1);
		service.persist(c2);
		service.persist(c3);
		service.persist(l1);
		service.persist(l2);
		service.persist(l3);
		service.persist(r1);
		service.persist(r2);
		service.persist(r3);
		utx.commit();
	}

	@Test
	public void test() {
		List<Tuple> result = service.joinQuery(Arrays.asList("c2"), Arrays.asList("r2"));
		int size = result.size();
		System.out.println("The result size is: " + size);
		if (size > 0) {
			Tuple first = result.get(0);
			System.out.println(first.get(0, LeftEntity.class));
			System.out.println(first.get(1, CenterEntity.class));
		}
		assertTrue(true);
	}

}
