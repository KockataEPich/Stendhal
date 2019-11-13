package games.stendhal.server.entity.item;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.db.AchievementDAO;
import games.stendhal.server.core.rp.achievement.AchievementNotifier;
import games.stendhal.server.entity.player.Player;
import marauroa.server.game.db.DAORegister;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.RPClass.ItemTestHelper;

public class DailyPostTest extends QuestHelper {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ItemTestHelper.generateRPClasses();
	}

	@Test
	public void testOnUsedDailyPost() {
		String name = "daily post";
		String clazz = "";
		String subclass = "";

		// Clear the reached_achievements table so that previous runs of the test don't
		// affect this run
		AchievementDAO dao = DAORegister.get().get(AchievementDAO.class);
		try {
			dao.resetReachedAchievements();
		} catch (SQLException e) {
		}

		Map<String, String> attributes = new HashMap<String, String>();
		DailyPost dp = new DailyPost(name, clazz, subclass, attributes);
		Player user1 = PlayerTestHelper.createPlayer("testplayer1");
		Player user2 = PlayerTestHelper.createPlayer("testplayer2");
		Player user3 = PlayerTestHelper.createPlayer("testplayer3");
		Player user4 = PlayerTestHelper.createPlayer("testplayer4");

		// necessary to avoid NullPointerException
		user1.initReachedAchievements();
		user2.initReachedAchievements();
		user3.initReachedAchievements();
		user4.initReachedAchievements();

		AchievementNotifier notifier = AchievementNotifier.get();
		notifier.initialize();

		String advertText = "The Stendhal Daily Post is brought to you by Xoderos's Tool Shop in Semos\n";
		String noneText = "No achievements have been completed in the last 24 hours\n";
		String someText = "Top 3 achievements from the last 24 hours:\n";
		String user1Text = "testplayer1 completed the 'Greenhorn' achievement";
		String user2Text = "testplayer2 completed the 'Adventurer' achievement";
		String user3Text = "testplayer3 completed the 'Stendhal Master' achievement";
		String user4Text = "testplayer4 completed the 'Stendhal High Master' achievement";
		String classifiedsText = "Classified ads:\n" + "NOT YET IMPLEMENTED";

		// 0 achievements in last 24 hours
		dp.onUsed(user1);
		assertEquals(advertText + noneText + classifiedsText, PlayerTestHelper.getPrivateReply(user1));

		// 1 achievement in last 24 hours
		notifier.awardAchievementIfNotYetReached(user1, "xp.level.010");
		user1.notifyWorldAboutChanges();
		dp.onUsed(user1);
		assertEquals(advertText + someText + String.format("1: %s\n", user1Text) + classifiedsText,
				PlayerTestHelper.getPrivateReply(user1));

		// 2 achievements in last 24 hours
		notifier.awardAchievementIfNotYetReached(user2, "xp.level.200");
		user2.notifyWorldAboutChanges();
		dp.onUsed(user1);
		assertEquals(advertText + someText + String.format("1: %s\n2: %s\n", user2Text, user1Text) + classifiedsText,
				PlayerTestHelper.getPrivateReply(user1));

		// 3 achievements in last 24 hours
		notifier.awardAchievementIfNotYetReached(user3, "xp.level.500");
		user3.notifyWorldAboutChanges();
		dp.onUsed(user1);
		assertEquals(advertText + someText + String.format("1: %s\n2: %s\n3: %s\n", user3Text, user2Text, user1Text)
				+ classifiedsText, PlayerTestHelper.getPrivateReply(user1));

		// 4 achievements in last 24 hours
		// "Stendhal High Master" requires a higher level than "Stendhal Master", but
		// both have the same score, so "Stendhall Master" comes before it
		notifier.awardAchievementIfNotYetReached(user4, "xp.level.597");
		user4.notifyWorldAboutChanges();
		dp.onUsed(user1);
		assertEquals(advertText + someText + String.format("1: %s\n2: %s\n3: %s\n", user3Text, user4Text, user2Text)
				+ classifiedsText, PlayerTestHelper.getPrivateReply(user1));
	}

	@Test
	public void testDailyPost() throws Exception {
		String name = "daily post";
		String clazz = "";
		String subclass = "";
		Map<String, String> attributes = new HashMap<String, String>();
		new DailyPost(name, clazz, subclass, attributes);

	}

}
