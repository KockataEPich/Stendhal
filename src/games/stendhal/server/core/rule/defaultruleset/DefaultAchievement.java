/* $Id$ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.core.rule.defaultruleset;

import java.util.Map;

/*
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import java.util.Map.Entry;
*/
/**
 * All default achievement
 * 
 *
 * @author Mario Kaymakanov
 */
public class DefaultAchievement {
		
	
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
	
	/** Achievement enumeration. */
	private String enumeration;
	
	/** The list of conditions for this achievement. */  // change this
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

	public String getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

	public Map<String, String> getConditions() { 
		return conditions;
	}

	public void setConditions(Map<String, String> conditions)  {
		this.conditions = conditions;
	}	
	
	public void addConditions(String name, String cond)  {
		this.conditions.put(name, cond);
	}	
	
	
	public String toXML() {
		final StringBuilder os = new StringBuilder();
		os.append("    <identifier>" + identifier + "</identifier>\n"); 
		os.append("    <title> " + title + "</title>\n");	
		os.append("    <catagory>\n"
			+ "<enumeration>" + enumeration + "</enumeration>\n"
			+ "    </catagory>\n");	
		if (description != null) {
			os.append("    <description>" + description + "</description>\n");
		}	
		os.append("    <score>" +  score + "</score>\n");
		os.append("    <active>" +  active + "</active>\n");
		
		os.append("    <condition ");
		
		for (Map.Entry<String,String> entry : conditions.entrySet()) {
			os.append("  " + entry.getKey() + "=\"" + entry.getValue() + "\n");
			
		}
		os.append("\"/>\n");
		return os.toString();
	}
}
