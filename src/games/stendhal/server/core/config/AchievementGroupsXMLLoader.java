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
import java.util.List;

import org.xml.sax.helpers.DefaultHandler;

/**
 * XML based loader of achievement groups
 *
 * @author 
 */
public class AchievementGroupsXMLLoader extends DefaultHandler {


	protected URI uri;

	public AchievementGroupsXMLLoader(final URI uri) {
		this.uri = uri;
	}

	public List load() {
		return null;
	}

}
