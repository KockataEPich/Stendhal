package games.stendhal.server.maps.deniran;


import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.maps.deniran.Lon_Jatham;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.BeforeClass;
import org.junit.Test;


public class Lon_JathamTest extends ZonePlayerAndNPCTestImpl {


	private static final String ZONE_NAME = "testzone";


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);
	}

	public Lon_JathamTest() {
		setNpcNames("Lon Jatham");
		setZoneForPlayer(ZONE_NAME);
		addZoneConfigurator(new Lon_Jatham(), ZONE_NAME);
	}
	 // Tests for hiAndBye.
	 
	@Test
	public void testHiAndBye() {

		final SpeakerNPC npc = getNPC("Lon Jatham");
		final Engine en = npc.getEngine();

		en.step(player, "hi");
		assertTrue(npc.isTalking());
		assertEquals("Hello, I am the famous lecturer Lon Jatham. You are just in time.", getReply(npc));
		en.step(player, "bye");
		assertFalse(npc.isTalking());
		assertEquals("Bye! Have a great time and don't forget about the coffee time.", getReply(npc));
	}
	 
	@Test
	public void testHiAndJob() {

		final SpeakerNPC npc = getNPC("Lon Jatham");
		final Engine en = npc.getEngine();

		en.step(player, "hi");
		assertTrue(npc.isTalking());
		assertEquals(
				"Hello, I am the famous lecturer Lon Jatham. You are just in time.",
				getReply(npc));
		en.step(player, "job");
		assertTrue(npc.isTalking());
		assertEquals("I can teach you java.I am java wizard.", getReply(npc));
		en.step(player, "bye");
		assertFalse(npc.isTalking());
		assertEquals("Bye! Have a great time and don't forget about the coffee time.", getReply(npc));
	}

		
	
}