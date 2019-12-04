/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

package games.stendhal.server.core.config;

import java.net.URI;
import java.util.Collection;

import org.xml.sax.helpers.DefaultHandler;

import games.stendhal.server.core.rp.achievement.Achievement;

/**
 * Achievement XML loader stub
 * 
 *
 * @author Ben Piggott
 */
public final class AchievementXMLLoader extends DefaultHandler {

	protected URI uri;

	public AchievementXMLLoader(final URI uri) {
		this.uri = uri;
	}

	public Collection<Achievement> load(URI uri) {
		return null;
	}
	
	public Collection<Achievement> load() {
		return null;
	}
}

