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
package games.stendhal.server.entity.item;

import java.util.Map;

import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.entity.status.SleepStatus;
import marauroa.common.game.RPObject;

public class SleepingBag extends Item {

	// Constructor for the item
	public SleepingBag(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {

		super(name, clazz, subclass, attributes);
	}

	/**
	 * copy constructor.
	 *
	 * @param item
	 *            item to copy
	 */
	public SleepingBag(final Item item)
	{
		super(item);
	}//SleepingBag

	/**
	 * method that is used when the option "sleep" is selected
	 */
	@Override
	public boolean onUsed(final RPEntity user) {

		// We check if the one who initiates the sleep is a player
		if (user instanceof Player) {

			if (isContained())
			{
				// We modify the base container if the object change.
				RPObject base = getContainer();

				while (base.isContained())
				{
					base = base.getContainer();
				}//while

				if (!user.nextTo((Entity) base))
				{
					user.sendPrivateText("The consumable item is too far away");
					return false;
				}//if
			}//if
			else
			{
				if (!nextTo(user))
				{
					user.sendPrivateText("The consumable item is too far away");
					return false;
				}//if
			}//else

			SleepStatus status = new SleepStatus();

			user.getStatusList().inflictStatus(status, user);
			user.notifyWorldAboutChanges();
			return true;


		}//if
		else
		{
			return false;
		}//else
	}//onused
}//class SleepingBag