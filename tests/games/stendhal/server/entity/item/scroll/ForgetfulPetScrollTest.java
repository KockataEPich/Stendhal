package games.stendhal.server.entity.item.scroll;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

//import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.creature.BabyDragon;
import games.stendhal.server.entity.creature.Cat;
import games.stendhal.server.entity.creature.PurpleDragon;
//import games.stendhal.server.entity.creature.Pet;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.PetTestHelper;

public class ForgetfulPetScrollTest 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PetTestHelper.generateRPClasses();
	}
	
	//test for baby dragon
	@Test
	public void test() 
	{
		//initiate the zone
		final StendhalRPZone testZone = new StendhalRPZone("zone")
		{
			@Override
			public boolean isInProtectionArea(final Entity entity)
			{
				return false;
			}
		};
		MockStendlRPWorld.get().addRPZone(testZone);
		//initiate the player and the pet, put them both in the zone
		final Player testPlayer = PlayerTestHelper.createPlayer("bob");
		//final Item blankPetScroll = SingletonRepository.getEntityManager().getItem("blank pet scroll");
		testZone.add(testPlayer);
		//testPlayer.equip("bag", blankPetScroll);
		final BabyDragon testPet = new BabyDragon(testPlayer);
		testZone.add(testPet);
		testPlayer.setPet(testPet);
		
		//alter the pet stats
		testPet.setName("little baby dragon");
		testPet.setBaseHP(1000);
		testPet.setHP(999);
		testPet.setXP(1370);
		testPet.setWeight(90);
		testPet.setAtkXP(730);
		testPet.setDefXP(710);
		
		//get and use the blank scroll
		PlayerTestHelper.equipWithItem(testPlayer, "blank pet scroll");
		final Item testBlankPetScroll = testPlayer.getFirstEquipped("blank pet scroll");
		testBlankPetScroll.onUsed(testPlayer);
		
		//get the summon scroll from the player inventory
		final Item testSummonPetScroll = testPlayer.getFirstEquipped("summon pet scroll");
		testSummonPetScroll.onUsed(testPlayer);
		
		//chck the actual values in order to test the bug
		assertEquals("little baby dragon", testPlayer.getPet().getName());
		assertEquals(1000, testPlayer.getPet().getBaseHP());
		assertEquals(999, testPlayer.getPet().getHP());
		assertEquals(1370, testPlayer.getPet().getXP());
		assertEquals(90, testPlayer.getPet().getWeight());
		assertEquals(730, testPlayer.getPet().getAtkXP());
		assertEquals(710, testPlayer.getPet().getDefXP());
		
		
	}
	
	//test for purple dragon
		@Test
		public void test2() 
		{
			//initiate the zone
			final StendhalRPZone testZone2 = new StendhalRPZone("zone")
			{
				@Override
				public boolean isInProtectionArea(final Entity entity)
				{
					return false;
				}
			};
			MockStendlRPWorld.get().addRPZone(testZone2);
			//initiate the player and the pet, put them both in the zone
			final Player testPlayer2 = PlayerTestHelper.createPlayer("bob");
			//final Item blankPetScroll = SingletonRepository.getEntityManager().getItem("blank pet scroll");
			testZone2.add(testPlayer2);
			//testPlayer.equip("bag", blankPetScroll);
			final PurpleDragon testPet2 = new PurpleDragon(testPlayer2);
			testZone2.add(testPet2);
			testPlayer2.setPet(testPet2);
			
			//alter the pet stats
			testPet2.setName("little purple dragon");
			testPet2.setBaseHP(1000);
			testPet2.setHP(999);
			testPet2.setXP(1370);
			testPet2.setWeight(90);
			testPet2.setAtkXP(730);
			testPet2.setDefXP(710);
			
			//get and use the blank scroll
			PlayerTestHelper.equipWithItem(testPlayer2, "blank pet scroll");
			final Item testBlankPetScroll2 = testPlayer2.getFirstEquipped("blank pet scroll");
			testBlankPetScroll2.onUsed(testPlayer2);
			
			//get the summon scroll from the player inventory
			final Item testSummonPetScroll2 = testPlayer2.getFirstEquipped("summon pet scroll");
			testSummonPetScroll2.onUsed(testPlayer2);
			
			//chck the actual values in order to test the bug
			assertEquals("little purple dragon", testPlayer2.getPet().getName());
			assertEquals(1000, testPlayer2.getPet().getBaseHP());
			assertEquals(999, testPlayer2.getPet().getHP());
			assertEquals(1370, testPlayer2.getPet().getXP());
			assertEquals(90, testPlayer2.getPet().getWeight());
			assertEquals(730, testPlayer2.getPet().getAtkXP());
			assertEquals(710, testPlayer2.getPet().getDefXP());
			
			
		}
		
		//test for cat
		@Test
		public void test1() 
		{
			//initiate the zone
			final StendhalRPZone testZone1 = new StendhalRPZone("zone")
			{
				@Override
				public boolean isInProtectionArea(final Entity entity)
				{
					return false;
				}
			};
			MockStendlRPWorld.get().addRPZone(testZone1);
			//initiate the player and the pet, put them both in the zone
			final Player testPlayer1 = PlayerTestHelper.createPlayer("bob");
			//final Item blankPetScroll = SingletonRepository.getEntityManager().getItem("blank pet scroll");
			testZone1.add(testPlayer1);
			//testPlayer.equip("bag", blankPetScroll);
			final Cat testPet1 = new Cat(testPlayer1);
			testZone1.add(testPet1);
			testPlayer1.setPet(testPet1);
			
			//alter the pet stats
			testPet1.setName("little cat");
			testPet1.setBaseHP(1000);
			testPet1.setHP(999);
			testPet1.setXP(1370);
			testPet1.setWeight(90);
			testPet1.setAtkXP(730);
			testPet1.setDefXP(710);
			
			//get and use the blank scroll
			PlayerTestHelper.equipWithItem(testPlayer1, "blank pet scroll");
			final Item testBlankPetScroll1 = testPlayer1.getFirstEquipped("blank pet scroll");
			testBlankPetScroll1.onUsed(testPlayer1);
			
			//get the summon scroll from the player inventory
			final Item testSummonPetScroll1 = testPlayer1.getFirstEquipped("summon pet scroll");
			testSummonPetScroll1.onUsed(testPlayer1);
			
			//chck the actual values in order to test the bug
			assertEquals("little cat", testPlayer1.getPet().getName());
			assertEquals(1000, testPlayer1.getPet().getBaseHP());
			assertEquals(999, testPlayer1.getPet().getHP());
			assertEquals(1370, testPlayer1.getPet().getXP());
			assertEquals(90, testPlayer1.getPet().getWeight());
			assertEquals(730, testPlayer1.getPet().getAtkXP());
			assertEquals(710, testPlayer1.getPet().getDefXP());
			
			
		}

}
