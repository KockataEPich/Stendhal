package games.stendhal.server.maps.deniran;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.mapstuff.sign.Sign;
import marauroa.common.game.RPClass;


public class SignTest {
	@Before
	public void setUp() throws Exception {
		if (!RPClass.hasRPClass("entity")) {
			Entity.generateRPClass();
		}

		if (!RPClass.hasRPClass("Sign")) {
			Sign.generateRPClass();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests for size.
	 */
	@Test
	public final void testSize() {
		final Sign info_sign = new Sign();
		assertEquals("Welcome to FleaMarket, but it is still under development(open for amdins)!",info_sign.getText());
	}
}