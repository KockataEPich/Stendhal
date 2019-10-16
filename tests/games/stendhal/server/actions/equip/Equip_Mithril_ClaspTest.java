package games.stendhal.server.actions.equip;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.item.Item;

public class Equip_Mithril_ClaspTest {
	@Test
	public void equipMithrilClaspTest(){
		Item mc = SingletonRepository.getEntityManager().getItem("mithril clasp");
		assertTrue(mc.canBeEquippedIn("keyring"));
	}

}
