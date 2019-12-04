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

/**
 * Default achievement stub
 * 
 *
 * @author Ben Piggott
 */
public class DefaultAchievement {
	
	
	public String getName() {
		return null;
	}

	public void setName(String name) {
		return;
	}
	
	
	public String getDescription() {
		return null;
	}

	public void setDescription(String description) {
		return;
	}

	public String getIdentifier() {
		return null;
	}

	public void setIdentifier(String identifier) {
		return;
	}

	public String getTitle() {
		return null;
	}

	public void setTitle(String title) {
		return;
	}

	public int getScore() {
		return 0;
	}

	public void setScore(int score) {
		return;
	}

	public boolean isActive() {
		return false;
	}

	public void setActive(boolean active) {
		return;
	}

	public String getEnumeration() {
		return null;
	}

	public void setEnumeration(String enumeration) {
		return;
	}

	public Map<String, String> getConditions() { 
		return null;
	}

	public void setConditions(Map<String, String> conditions)  {
		return;
	}	
	
	public void addConditions(String name, String cond)  {
		return;
	}	
	
	public String toXML() {
		return null;
	}
}
