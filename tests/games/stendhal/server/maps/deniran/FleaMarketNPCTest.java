package games.stendhal.server.maps.deniran;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;



import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.RPClass.ItemTestHelper;

/**
 * Test for LeaderNPC - excepting his idle conversations with cadets.
 *
 * @author kymara
 */
 public class FleaMarketNPCTest {
	private Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();

		ItemTestHelper.generateRPClasses();

		final StendhalRPZone zone = new StendhalRPZone("admin_test");
		new FleaMarketNPC().configureZone(zone, null);

	}

	@Before
	public void setUp() {
		player = PlayerTestHelper.createPlayer("player");
	}

	/**
	 * Tests for hiAndBye.
	 */
	@Test
	public void testHiAndBye() {
		npc = SingletonRepository.getNPCList().get("FM_test_NPC");
		en = npc.getEngine();

		assertTrue(en.step(player, "hi"));
		assertEquals("Welcome to this flea stall,you could buy anything from the stall.", getReply(npc));

		assertTrue(en.step(player, "bye"));
		assertEquals("Bye.", getReply(npc));
	}

	/**
	 * Tests for buy second hand items.
	 */

}
