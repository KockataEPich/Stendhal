package games.stendhal.server.core.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.rp.achievement.*;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import marauroa.server.game.db.DatabaseFactory;
import utilities.PlayerTestHelper;

/**
 * @author Ben Piggott
 */
public class AchievementGroupsXMLLoaderTest {
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
	public void testLoad() throws URISyntaxException, SAXException, IOException {
		final Player player = PlayerTestHelper.createPlayer("dave");
		zone.add(player);
		AchievementGroupsXMLLoader loader = new AchievementGroupsXMLLoader(new URI("testachievementgroup.xml"));
		List<Achievement> list = loader.load();
		assertNotNull(list);
		assertThat(Boolean.valueOf(list.isEmpty()), is(Boolean.FALSE));
		Achievement achievement = list.get(0);
		assertThat(achievement.getIdentifier(), is("quest.special.daily.0010"));
		assertThat(achievement.getCategory(), is(Category.QUEST_ADOS_ITEMS));
		assertThat(achievement.getDescription(), is("Finish daily monster quest 10 times"));
		assertThat(achievement.getTitle(), is("Semos' Protector"));
		assertThat(achievement.isActive(), is(Boolean.TRUE));
		assertThat(achievement.getBaseScore(), is(1));
		assertThat(achievement.isFulfilled(player), is(Boolean.FALSE));
	}

}
