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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import games.stendhal.server.core.rp.achievement.Category;
import games.stendhal.server.core.rule.defaultruleset.DefaultAchievement;

public final class AchievementXMLLoader extends DefaultHandler {

	private static final Logger logger = Logger.getLogger(AchievementXMLLoader.class);

	private String identifier;
	private String title;
	private Category category;
	private String description;
	private int score;
	private boolean active;
	private Map<String, String> conditions;
	private String text;

	private List<DefaultAchievement> defaultAchievements;

	public Collection<DefaultAchievement> load(URI uri) throws SAXException {
		defaultAchievements = new LinkedList<DefaultAchievement>();
		final SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			final SAXParser saxParser = factory.newSAXParser();
			final InputStream is = AchievementXMLLoader.class.getResourceAsStream(uri.getPath());
			if (is == null) {
				throw new FileNotFoundException("cannot find resource '" + uri + "' in classpath");
			}
			try {
				saxParser.parse(is, this);
			} finally {
				is.close();
			}

		} catch (final ParserConfigurationException t) {
			logger.error(t);
		} catch (final IOException e) {
			logger.error(e);
			throw new SAXException(e);
		} finally {
		}

		return defaultAchievements;
	}

	@Override
	public void startDocument() {
		// do nothing
	}

	@Override
	public void endDocument() {
		// do nothing
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		text = "";
		if (qName.equals("achievement")) {
			identifier = attributes.getValue("value");
		}
		if (qName.equals("title")) {
			title = attributes.getValue("value");
		}
		if (qName.equals("category")) {
			category = stringToCategory(attributes.getValue("value"));
		}
		if (qName.equals("description")) {
			description = attributes.getValue("value");
		}
		if (qName.equals("score")) {
			score = Integer.parseInt(attributes.getValue("value"));
		}
		if (qName.equals("active")) {
			active = Boolean.parseBoolean(attributes.getValue("value"));
		}
		if (qName.equals("condition")) {
			for (int i = 0; i < attributes.getLength(); i ++) {
				conditions.put(attributes.getLocalName(i), attributes.getValue(i));
			}
		}
	}

	private Category stringToCategory(String s) {
		String[] names = { "EXPERIENCE", "FIGHTING", "QUEST", "OUTSIDE_ZONE", "UNDERGROUND_ZONE", "INTERIOR_ZONE",
				"AGE", "ITEM", "OBTAIN", "FRIEND", "PRODUCTION", "QUEST_ADOS_ITEMS", "QUEST_SEMOS_MONSTER",
				"QUEST_KIRDNEH_ITEM", "QUEST_MITHRILBOURGH_ENEMY_ARMY" };
		Category[] categories = { Category.EXPERIENCE, Category.FIGHTING, Category.QUEST, Category.OUTSIDE_ZONE,
				Category.UNDERGROUND_ZONE, Category.INTERIOR_ZONE, Category.AGE, Category.ITEM, Category.OBTAIN,
				Category.FRIEND, Category.PRODUCTION, Category.QUEST_ADOS_ITEMS, Category.QUEST_SEMOS_MONSTER,
				Category.QUEST_KIRDNEH_ITEM, Category.QUEST_MITHRILBOURGH_ENEMY_ARMY };
		for (int i = 0; i < names.length; i++) {
			if (s.equals(names[i])) {
				return categories[i];
			}
		}
		return null;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("achievement")) {
			final DefaultAchievement achievement = new DefaultAchievement();
			achievement.setIdentifier(identifier);
			achievement.setTitle(title);
			achievement.setCategory(category);
			achievement.setDescription(description);
			achievement.setScore(score);
			achievement.setActive(active);
			achievement.setConditions(conditions);
			defaultAchievements.add(achievement);
		}
	}

	@Override
	public void characters(final char[] buf, final int offset, final int len) {
		text = text + (new String(buf, offset, len)).trim() + " ";
	}
}
