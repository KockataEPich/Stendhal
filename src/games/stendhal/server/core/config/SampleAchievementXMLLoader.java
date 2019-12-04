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
import games.stendhal.server.core.rule.defaultruleset.DefaultSampleAchievement;


/* @author Heng Gao */




public final class SampleAchievementXMLLoader extends DefaultHandler {

	private static final Logger logger = Logger.getLogger(SampleAchievementXMLLoader.class);


	private static final String EXPERIENCE = null;


	private static final String OUTSIDE_ZONE = null;


	private static final String FIGHTING = null;


	private static final String UNDERGROUND_ZONE = null;


	private static final String INTERIOR_ZONE = null;


	private static final String QUEST = null;


	private static final String AGE = null;


	private static final String ITEM = null;


	private static final String OBTAIN = null;


	private static final String FRIEND = null;


	private static final String PRODUCTION = null;


	private static final String QUEST_ADOS_ITEMS = null;


	private static final String QUEST_SEMOS_MONSTER = null;


	private static final String QUEST_KIRDNEH_ITEM = null;


	private static final String QUEST_MITHRILBOURGH_ENEMY_ARMY = null;


	private String identifier;
	
	private String title;
	
	private Category category;
	
	private String whatCategory;
	
	private String description;
	
	private int score;
	
	private boolean active;
	
	private Map<String, String> conditions;
	
	private String text;

	private List<DefaultSampleAchievement> loadedSampleAchievements;
	
	
	public List<DefaultSampleAchievement> load(URI uri) throws SAXException {
		loadedSampleAchievements = new LinkedList<DefaultSampleAchievement>();
		// Use the default (non-validating) parser
		final SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			// Parse the input
			final SAXParser saxParser = factory.newSAXParser();

			final InputStream is = SampleAchievementXMLLoader.class.getResourceAsStream(uri.getPath());

			if (is == null) {
				throw new FileNotFoundException("cannot find resource '" + uri
						+ "' in classpath");
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
		}
		return loadedSampleAchievements;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(qName.equals("SampleAchievement")) {
			identifier = attributes.getValue("name");
		}
		if(qName.equals("title")) {
			title = attributes.getValue("value");
		}
		if(qName.equals("category")) {
			if(qName.equals("whatCategory")) 
				whatCategory = attributes.getValue("whatCategeory");
					category = Category.EXPERIENCE;
			}
	
		if(qName.equals("description")) {
			description = attributes.getValue("value");
		}
		if(qName.equals("score")) {
			score = Integer.parseInt(attributes.getValue("value"));
		}
		if(qName.equals("active")) {
			active = Boolean.parseBoolean(attributes.getValue("value"));
		}
		if(qName.equals("condition")) {
			for(int i = 0; i < attributes.getLength(); i++) {
				conditions.put(attributes.getLocalName(i),attributes.getValue(i));
				}
		}
	}

	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("SampleAchievement")) {
			final DefaultSampleAchievement SampleAchievement = new DefaultSampleAchievement();
			SampleAchievement.setIdentifier(identifier);
	
			SampleAchievement.setTitle(title);
	
			SampleAchievement.setCategory(category);
	
			SampleAchievement.setDescription(description);
	
			SampleAchievement.setScore(score);
	
			SampleAchievement.setActive(active);
	
			SampleAchievement.setConditions(conditions);
	
			loadedSampleAchievements.add(SampleAchievement);
	
		}
	
	}
}
