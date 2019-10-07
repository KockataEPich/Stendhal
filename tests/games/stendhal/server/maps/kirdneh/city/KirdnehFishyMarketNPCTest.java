package games.stendhal.server.maps.kirdneh.city;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

/**
 * Test selling a red lion fish.
 * @author Benjamin Piggott
 */
public class KirdnehFishyMarketNPCTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "int_ados_felinas_house";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}

	public KirdnehFishyMarketNPCTest() {
		setNpcNames("Fishmonger");
		setZoneForPlayer(ZONE_NAME);
		addZoneConfigurator(new KirdnehFishyMarketNPC(), ZONE_NAME);
	}

	/**
	 * Tests for sellRedLionFish.
	 */
	@Test
	public void testSellRedLionFish() {
		final SpeakerNPC npc = getNPC("Fishmonger");
		final Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi"));
		assertEquals("Ahoy, me hearty! Back from yer swashbucklin, ah see.", getReply(npc));

		assertTrue(en.step(player, "job"));
		assertEquals("By the Powers! I be buyin. You be sellin?", getReply(npc));

		assertTrue(en.step(player, "aye"));
		assertEquals("Well, shiver me timbers! Check out that blackboard o'er thar fer me prices an' what i be buyin", getReply(npc));

		assertFalse(en.step(player, "quest"));
		assertEquals("Ye don't ha'e the guts ta do whut I need done.", getReply(npc));

		assertTrue(en.step(player, "offer"));
		assertEquals("Check out that thar blackboard fer how many dubloons I be givin.", getReply(npc));
		
		assertTrue(equipWithItem(player, "red lion fish"));
		assertTrue(en.step(player, "sell red lion fish"));
		assertEquals("A red lion fish is worth 120. Do you want to sell it?", getReply(npc));
		
		assertTrue(en.step(player, "yes"));
		assertEquals("Thanks! Here is your money.", getReply(npc));
		assertTrue(player.isEquipped("money", 120));
	}

}

