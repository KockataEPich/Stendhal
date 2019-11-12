package games.stendhal.server.entity.item.prospect;

import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.item.Item;
public class prospectTest {
	@Test
	public void testDescribe() {
		final Item newprospectus = SingletonRepository.getEntityManager().getItem("prospectus");
		assertThat(newprospectus.getDescription(), is("You see the prospectus that Lon gave to you."));

	}
}
