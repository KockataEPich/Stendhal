package games.stendhal.server.core.rule.defaultruleset;

import java.util.Map;

import games.stendhal.server.core.rp.achievement.Category;

/*
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import java.util.Map.Entry;
*/
/**
 * Default achievement stub
 * All default achievement
 * 
 *
 * 
 * @author Heng Gao
 */
public class DefaultSampleAchievement {
	
	

	/** Achievement name. */
	private String name;

	/** optional Achievement description. * */
	private String description;

	/** Achievement identifier. */
	private String identifier;

	/** Achievement title. */
	private String title;

	/** Achievement score. */
	private int score;

	/** Achievement active. */
	private boolean active;

	/** Achievement category. */
	private Category category;

	/** The list of conditions for this achievement. */ // change this
	private Map<String, String> conditions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Map<String, String> getConditions() {
		return conditions;
	}


	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}

	public void addConditions(String name, String cond) {
		this.conditions.put(name, cond);
	}


	
	public String toXML() {
		final StringBuilder os = new StringBuilder();
		os.append("    <identifier> " + identifier + "</identifier>\n");
		os.append("    <title> " + title + "</title>\n");
		os.append("    <category> " + category + "</category>\n");
		os.append("    <description>" + description + "</description>\n");
		os.append("    <score>" + score + "</score>\n");
		os.append("    <active>" + active + "</active>\n");
		os.append("    <condition ");

		for (Map.Entry<String, String> entry : conditions.entrySet()) {
			os.append("  " + entry.getKey() + "=\"" + entry.getValue() + "\n");

		}
		os.append("\"/>\n");
		return os.toString();
	}
}
