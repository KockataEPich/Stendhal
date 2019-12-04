package games.stendhal.server.core.rp.achievement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.rp.achievement.condition.KilledRareCreatureCondition;
import games.stendhal.server.core.rp.achievement.condition.KilledSoloAllCreaturesCondition;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.server.game.db.DatabaseFactory;
import utilities.PlayerTestHelper;

public class AchievementTest {
	
	private StendhalRPZone zone;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MockStendlRPWorld.get();
		new DatabaseFactory().initializeDatabase();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PlayerTestHelper.removeAllPlayers();
	}

	@Before
	public void setUp() {
		zone = new StendhalRPZone("zone", 20, 20);
		zone.protectionMap.init(1, 1);
		MockStendlRPWorld.get().addRPZone(zone);
	}

	@After
	public void tearDown() {
		MockStendlRPWorld.get().removeZone(zone);
	}
	
	
	@Test
	public void createAchievementTest()
	{
		
		final Player player = PlayerTestHelper.createPlayer("dave");
		zone.add(player);
		Achievement newAchievement = new Achievement("identifier", "title", Category.FIGHTING,
							"description", 4, true, new KilledSoloAllCreaturesCondition());
		assertThat(newAchievement.getIdentifier(), is("identifier"));
		assertThat(newAchievement.getCategory(), is(Category.FIGHTING));
		assertThat(newAchievement.getDescription(), is("description"));
		assertThat(newAchievement.getTitle(), is("title"));
		assertThat(newAchievement.isActive(), is(Boolean.TRUE));
		assertThat(newAchievement.getBaseScore(), is(4));
		assertThat(newAchievement.isFulfilled(player), is(Boolean.FALSE));
		assertThat(newAchievement.toString(), is("Achievement<id: identifier, title: title>"));
			
		
	}
	
	@Test
	public void createAchievementTest2()
	{
		
		final Player player = PlayerTestHelper.createPlayer("dave");
		zone.add(player);
		Achievement newAchievement = new Achievement("identifier", "title", Category.FIGHTING,
							"description", 4, true, new KilledRareCreatureCondition());
		assertThat(newAchievement.getIdentifier(), is("identifier"));
		assertThat(newAchievement.getCategory(), is(Category.FIGHTING));
		assertThat(newAchievement.getDescription(), is("description"));
		assertThat(newAchievement.getTitle(), is("title"));
		assertThat(newAchievement.isActive(), is(Boolean.TRUE));
		assertThat(newAchievement.getBaseScore(), is(4));
		assertThat(newAchievement.isFulfilled(player), is(Boolean.FALSE));
		assertThat(newAchievement.toString(), is("Achievement<id: identifier, title: title>"));
			
		
	}

}
