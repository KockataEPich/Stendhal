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

	/**
	 * Copy constructor.
	 *
	 * @param item item to copy
	 */
	public DailyPost(final DailyPost item) {
		super(item);
	}

	public DailyPost(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}

	@Override
	public boolean onUsed(RPEntity user) {
		AchievementDAO dao = DAORegister.get().get(AchievementDAO.class);
		List<NewsAchievement> todaysachievements;
		try {
			// Get all achievements that have been completed in the last 24 hours.
			todaysachievements = dao.getTodaysNewsAchievements();

			// Sort them in descending order (i.e. achievements with highest score first).
			Collections.sort(todaysachievements, Collections.reverseOrder());

			// Take the first 3 achievements, or the whole list if less than 3 achievements
			// have been completed today.
			List<NewsAchievement> top3achievements = todaysachievements.subList(0,
					Math.min(3, todaysachievements.size()));

			String achievementsText;
			if (top3achievements.size() == 0) {
				achievementsText = "No achievements have been completed in the last 24 hours\n";
			} else {
				achievementsText = "Top 3 achievements from the last 24 hours:\n";
				for (int i = 0; i < top3achievements.size(); i++) {
					achievementsText += String.format("%d: %s\n", i + 1, top3achievements.get(i).toString());
				}
			}

			String newspaperText = "The Stendhal Daily Post is brought to you by Xoderos's Tool Shop in Semos\n"
					+ achievementsText + "Classified ads:\n" + "NOT YET IMPLEMENTED";

			// Open the popup window and write the newspaper contents into the player's chat
			// log
			user.addEvent(new ExamineEvent("newspaper_popup.png", "The Stendhal Daily Post", ""));
			user.addEvent(new PrivateTextEvent(NotificationType.NORMAL, newspaperText));
			user.notifyWorldAboutChanges();
		} catch (SQLException e) {
			logger.error(e);
		}
		return true;
	}
}
