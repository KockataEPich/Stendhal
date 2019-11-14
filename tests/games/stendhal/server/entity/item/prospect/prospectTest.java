package games.stendhal.server.entity.item.prospect;

import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.item.Item;
public class prospectTest {
	// A test to verify the description of an item.
	@Test
	public void testDescribe() {
		// Create a new item
		final Item newprospectus = SingletonRepository.getEntityManager().getItem("prospectus");
		// Check the description of the item is good.
		assertThat(newprospectus.getDescription(), is("You see the prospectus that Lon gave to you."));

	}
}
