 package games.stendhal.server.entity.status;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.creature.Creature;
import games.stendhal.server.entity.item.Item;

import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.CreatureTestHelper;
import utilities.RPClass.ItemTestHelper;

public class SleepStatusTest 
{
    @BeforeClass
	public static void setUpBeforeClass()
	{
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();
		CreatureTestHelper.generateRPClasses();
	}
	
	/**
	* Test for checking if the sleep status cannot  be applied more than once
	 * @throws InterruptedException 
 	*/
	@Test
 	public void testCreation() throws InterruptedException
 	{
		
     
	 final Player testPlayer = PlayerTestHelper.createPlayer("testplayer");
	 
	 final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");	 
 
	 testPlayer.equip("bag", sleepingBag);
	 sleepingBag.onUsed(testPlayer);
 
	 // Check 
	 int count = testPlayer.getStatusList().countStatusByType(StatusType.SLEEPING);
	 assertEquals(1, count);
 
	 sleepingBag.onUsed(testPlayer);
	 count = testPlayer.getStatusList().countStatusByType(StatusType.SLEEPING);
 	 assertEquals(1, count); 
 	}//test
	
	/**
	* Test for checking if the sleep status is correctly applied..
	 * @throws InterruptedException 
 	*/
	@Test
	public void testRemoveStatus() throws InterruptedException
 	{
		// ADD the test player, item and creature
		final Player testPlayer = PlayerTestHelper.createPlayer("testplayer");
		 
		final Item sleepingBag = SingletonRepository.getEntityManager().getItem("sleeping bag");	 
		
		// Add the zone
		final StendhalRPZone testZone = new StendhalRPZone("ZONE"); 
		SingletonRepository.getRPWorld().addRPZone(testZone);
 	  
		Creature creature = new Creature();
		
		// Use the sleeping bag to enter the sleeping state
		testPlayer.equip("bag", sleepingBag);
	    sleepingBag.onUsed(testPlayer);
	    
	    // Add the player and the creature
		testZone.add(testPlayer);
		testZone.add(creature);
		
		// Set creature attributes
		creature.setAtkXP(100);
		creature.setTarget(testPlayer);
		creature.attack();

	    assertTrue(!testPlayer.hasStatus(StatusType.SLEEPING)); 
 	}//test
} 