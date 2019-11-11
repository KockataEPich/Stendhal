package games.stendhal.server.entity.item;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import games.stendhal.common.NotificationType;
import games.stendhal.server.core.engine.db.AchievementDAO;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.events.ExamineEvent;
import games.stendhal.server.events.PrivateTextEvent;
import marauroa.server.game.db.DAORegister;

public class DailyPost extends Item {
	private static final Logger logger = Logger.getLogger(DailyPost.class);

	public DailyPost(Item item) {
		super(item);
	}

	public DailyPost(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}

	@Override
	public boolean onUsed(RPEntity user) {
		AchievementDAO dao = DAORegister.get().get(AchievementDAO.class);
		List<NewsAchievement> todaysAcheivements;
		try {
			todaysAcheivements = dao.getTodaysNewsAchievements();
			Collections.sort(todaysAcheivements, Collections.reverseOrder());
			List<NewsAchievement> top3Acheivements = todaysAcheivements.subList(0,
					Math.min(3, todaysAcheivements.size()));

			String description = "";
			for (NewsAchievement a : top3Acheivements) {
				description += a.toString() + "\n";
			}

			user.addEvent(new ExamineEvent("newspaper_popup.png", "The Stendhal Daily Post", ""));
			user.addEvent(new PrivateTextEvent(NotificationType.NORMAL, description));
			user.notifyWorldAboutChanges();
		} catch (SQLException e) {
			logger.error(e);
		}
		return true;
	}
}
