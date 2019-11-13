
package games.stendhal.server.entity.item;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
//import games.stendhal.server.core.engine.StendhalRPZone;
//import games.stendhal.server.entity.Entity;
//import games.stendhal.server.entity.creature.BabyDragon;
import games.stendhal.server.entity.player.Player;
//import games.stendhal.server.entity.status.SleepStatus;
import games.stendhal.server.entity.status.StatusType;
//import games.stendhal.server.maps.MockStendlRPWorld;
//import games.stendhal.server.maps.quests.houses.HouseUtilities;
import utilities.PlayerTestHelper;

public class SleepingBagTest
{
	/**
	 * Test for checking if the item exists.
	 */
	@Test
	public void testCreation()
	{

		//initiate the player and the pet, put them both in the zone
		final Player testPlayer = PlayerTestHelper.createPlayer("testplayer");

		final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");

		testPlayer.equip("bag", sleepingBag);

		assertNotNull("The item is not a not null", sleepingBag);
		assertTrue("The item is not a SleepingBag", sleepingBag instanceof SleepingBag);
	}//test

	/**
	 * Tests for testing infliction.
	 */
	@Test
	public void testInflicts()
	{

		//initiate the player and the pet, put them both in the zone
		final Player testPlayer = PlayerTestHelper.createPlayer("testplayer");
		final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");

		testPlayer.equip("bag", sleepingBag);
		sleepingBag.onUsed(testPlayer);

		assertEquals("You have fallen asleep!", PlayerTestHelper.getPrivateReply(testPlayer));

		assertTrue(testPlayer.getStatusList().hasStatus(StatusType.SLEEPING));
 }//test
}//class sleepingBagTest