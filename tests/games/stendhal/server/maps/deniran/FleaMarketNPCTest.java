package games.stendhal.server.maps.deniran;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;


/**
 * Test The FleaMarketNPC.
 *
 * @author Heng Gao
 */
	public class FleaMarketNPCTest extends ZonePlayerAndNPCTestImpl {

		private static final String ZONE_NAME = "0_deniran";

		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			QuestHelper.setUpBeforeClass();

			setupZone(ZONE_NAME);
		}

		public void FleaMarkNPCTest() {
			setNpcNames("FM_test_assistant");
			setZoneForPlayer(ZONE_NAME);
			addZoneConfigurator(new FleaMarketNPC(), ZONE_NAME);
		}

		/**
		 * Tests for hi and bye.
		 */
		@Test
		public void testHiAndByeSimple() {
			final SpeakerNPC npc = getNPC("FM_test_assistant");
			assertNotNull(npc);
			final Engine en = npc.getEngine();

			assertTrue(en.step(player, "hi"));
			String reply = getReply(npc);
			assertNotNull(reply);
			assertEquals("Welcome to the Flea Market.", reply);

			assertTrue(en.step(player, "bye"));
			assertEquals("Bye..", getReply(npc));
		}

}
