package games.stendhal.server.maps.deniran;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	 * Tests for buyFlower.
	 */
	@Test
	public void testBuyFlower() {
		npc = SingletonRepository.getNPCList().get("FM_test_NPC");
		en = npc.getEngine();

		assertTrue(en.step(player, "hi"));
		assertEquals("Hi! Are you here to #trade?", getReply(npc));

		assertTrue(en.step(player, "job"));
		assertEquals("I sell roses in this here market.", getReply(npc));

		assertTrue(en.step(player, "trade"));
		assertEquals("I sell rose.", getReply(npc));

		// There is currently no quest response defined for Fleur.
		assertFalse(en.step(player, "quest"));

		assertTrue(en.step(player, "buy"));
		assertEquals("please tell what item you prefer.", getReply(npc));
		assertTrue(en.step(player, "no"));
		assertEquals("Ok, how else may I help you?", getReply(npc));

		assertTrue(en.step(player, "buy dog"));
		assertEquals("Sorry, I don't sell dogs.", getReply(npc));

		assertTrue(en.step(player, "buy candle"));
		assertEquals("Sorry, I don't sell candles.", getReply(npc));

		assertTrue(en.step(player, "buy a glass of wine"));
		assertEquals("Sorry, I don't sell glasses of wine.", getReply(npc));

		assertTrue(en.step(player, "buy rose"));
		assertEquals("A rose will cost 50. Do you want to buy it?", getReply(npc));

		assertTrue(en.step(player, "no"));
		assertEquals("Ok, how else may I help you?", getReply(npc));

		assertTrue(en.step(player, "buy rose"));
		assertEquals("A rose will cost 50. Do you want to buy it?", getReply(npc));

		assertTrue(en.step(player, "yes"));
		assertEquals("Sorry, you don't have enough money!", getReply(npc));

		assertTrue(en.step(player, "buy two roses"));
		assertEquals("2 roses will cost 100. Do you want to buy them?", getReply(npc));

		assertTrue(en.step(player, "yes"));
		assertEquals("Sorry, you don't have enough money!", getReply(npc));
		assertTrue(player.isEquipped("rose", 6));
	}

	
}
