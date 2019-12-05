package games.stendhal.server.core.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

//import games.stendhal.server.core.rp.achievement.Category;
import games.stendhal.server.core.rule.defaultruleset.DefaultAchievement;
import games.stendhal.server.maps.MockStendlRPWorld;;

/**
 * @author Ben Piggott
 */
public class AchievementGroupsXMLLoaderTest {
	
	@Before
	public void setUp() {
		MockStendlRPWorld.get();
	}

	@After
	public void tearDown() {
		MockStendlRPWorld.reset();
	}

	@Test
	public void testLoad() throws URISyntaxException, SAXException, IOException {
		AchievementGroupsXMLLoader loader = new AchievementGroupsXMLLoader(new URI("testachievementgroups.xml"));
		List<DefaultAchievement> list = loader.load();
		assertNotNull(list);
		assertThat(Boolean.valueOf(list.isEmpty()), is(Boolean.FALSE));
		DefaultAchievement achievement = list.get(0);
		assertThat(achievement.getIdentifier(), is("quest.special.daily.0010"));
		//assertThat(achievement.getCategory(), is(Category.QUEST_ADOS_ITEMS));
		assertThat(achievement.getDescription(), is("Finish daily monster quest 10 times"));
		assertThat(achievement.getTitle(), is("Semos' Protector"));
		assertThat(achievement.isActive(), is(Boolean.TRUE));
		//assertThat(achievement.getScore(), is(1));
		Map<String, String> conditions = achievement.getConditions();
		assertThat(conditions.get("param1"), is("daily_item"));
		assertThat(conditions.get("param2"), is(2));
		assertThat(conditions.get("param3"), is(9));
	}

}
