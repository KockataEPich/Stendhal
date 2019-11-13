package games.stendhal.server.maps.deniran;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.mapstuff.chest.Chest;
import games.stendhal.server.entity.mapstuff.sign.Sign;
import marauroa.common.game.RPClass;



public class SignTest {
	@Before
	public void setUp() throws Exception {
		if (!RPClass.hasRPClass("entity")) {
			Entity.generateRPClass();
		}

		if (!RPClass.hasRPClass("sign")) {
			Chest.generateRPClass();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests for a info_sign.
	 */
	@Test
	public final void testText() {
		final Sign info_sign = new Sign();
		String text = info_sign.getText();
		assertNull(text);
		
		String string = info_sign.describe();
		assertEquals("You see a sign without any text",string);
		
		info_sign.setText("Welcome to FleaMarket, but it is still under development（open for amdins!）");
		text = info_sign.getText();
		assertNotNull(text);
		string = info_sign.describe();
		String expect = "You read: \"" + text + "\"";
		assertEquals(expect,string);
	
	}
	
	
	@Test
	public final void testOpen() {
		final Chest ch = new Chest();
		assertFalse(ch.isOpen());
		ch.open();

		assertTrue(ch.isOpen());
		ch.close();
		assertFalse(ch.isOpen());
	}
}