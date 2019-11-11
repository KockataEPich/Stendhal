package games.stendhal.server.entity.item;

import java.util.Map;

import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.events.ExamineEvent;

public class DailyPost extends Item {

	public DailyPost(Item item) {
		super(item);
	}
	
	public DailyPost(final String name, final String clazz, final String subclass, final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}

	@Override
	public boolean onUsed(RPEntity user) {
		user.addEvent(new ExamineEvent("newspaper_popup.png", "title", "caption"));
		user.notifyWorldAboutChanges();
		return true;
	}
}
