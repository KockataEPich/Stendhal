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

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import games.stendhal.server.core.rule.defaultruleset.DefaultAchievement;

/**
 * XML based loader of achievement groups
 *
 * @author
 */
public class AchievementGroupsXMLLoader extends DefaultHandler {

	private static final Logger logger = Logger.getLogger(AchievementGroupsXMLLoader.class);

	protected URI uri;

	public AchievementGroupsXMLLoader(final URI uri) {
		this.uri = uri;
	}

	/**
	 * Load spells.
	 *
	 * @return list of spells
	 * @throws SAXException If a SAX error occurred.
	 * @throws IOException  If an I/O error occurred.
	 */
	public List<DefaultAchievement> load() throws SAXException, IOException {
		final GroupsXMLLoader groupsLoader = new GroupsXMLLoader(uri);

		final List<URI> groups = groupsLoader.load();
		final AchievementXMLLoader loader = new AchievementXMLLoader();

		final List<DefaultAchievement> list = new LinkedList<DefaultAchievement>();
		for (final URI groupUri : groups) {
			logger.debug("Loading spell group [" + groupUri + "]");
			list.addAll(loader.load(groupUri));
		}

		return list;
	}

}
