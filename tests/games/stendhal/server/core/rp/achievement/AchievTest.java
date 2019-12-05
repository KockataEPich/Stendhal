package games.stendhal.server.core.rp.achievement;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.server.game.db.DatabaseFactory;
import utilities.PlayerTestHelper;

public class AchievTest {
	

	private AchievementNotifier achievementNotifier;
	Player player = PlayerTestHelper.createPlayer("dave");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		new DatabaseFactory().initializeDatabase();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MockStendlRPWorld.reset();
	}

	
	@Before
	public void setUp()
	{
		// make an anchievmenet notifier object.
		achievementNotifier = SingletonRepository.getAchievementNotifier();
		// Initialize the notifier
		achievementNotifier.initialize();
		
	}
	
	@Test
	public void testExperience()
	{
		// Get the achievements in a list.
		List<Achievement> achievements = achievementNotifier.getAchievements();
		//Initialise an achievement with null.
		Achievement achievementDone = null;
		
		// Search for the achievement in the iterator.
		for(Iterator<Achievement>achievementIterator = achievements.iterator(); achievementIterator.hasNext();)
		{
			Achievement achievement = achievementIterator.next();
			if(achievement.getIdentifier() == "xp.level.100")
				achievementDone = achievement;
		}
		//Cheack if all the attributes of the achievements are how they are supposed.
		assertNotEquals(achievementDone, null);
		assertEquals(achievementDone.getDescription(), "Reach level 100");
		assertEquals(achievementDone.getBaseScore(), Achievement.EASY_BASE_SCORE);
		assertEquals(achievementDone.getTitle(), "Apprentice");
		assertTrue(achievementDone.isActive());
		assertThat(achievementDone.toString(), is("Achievement<id: xp.level.100, title: Apprentice>"));
	
		// See that the player is not fulfilled.
		player.initReachedAchievements();
		assertFalse(achievementDone.isFulfilled(player));
		
		assertFalse(player.hasReachedAchievement("xp.level.100"));
		
		// Set the level of the player at 100.
		player.setLevel(100);
		player.addReachedAchievement("xp.level.100");
		// See that now the player is fulfilled
		assertTrue(achievementDone.isFulfilled(player));
		assertTrue(player.hasReachedAchievement("xp.level.100"));
		
		
		
		
	}
	
	public void testItem()
	{
		// Get the achievements in a list.
		List<Achievement> achievements = achievementNotifier.getAchievements();
		//Initialise an achievement with null.
		Achievement achievementDone = null;
		
		// Search for the achievement in the iterator.
		for(Iterator<Achievement>achievementIterator = achievements.iterator(); achievementIterator.hasNext();)
		{
			Achievement achievement = achievementIterator.next();
			if(achievement.getIdentifier() == "item.money.100")
				achievementDone = achievement;
		}
		assertNotNull(achievementDone);
		Player player2 = PlayerTestHelper.createPlayer("chip");
		assertFalse(achievementDone.isFulfilled(player2));

		
		assertFalse(player2.hasReachedAchievement("item.money.100"));
		player2.incLootForItem("money", 100);
		achievementNotifier.onItemLoot(player2);
		assertTrue(achievementDone.isFulfilled(player2));
		assertTrue(player2.hasReachedAchievement("item.money.100"));
		
		
	}

}
