/***************************************************************************
 *                (C) Copyright 2005-2013 - Faiumoni e. V.                 *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.status;

import games.stendhal.common.NotificationType;
import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.TurnNotifier;
import games.stendhal.server.entity.RPEntity;

/**
 * sleeping turn listener
 */
public class SleepStatusTurnListener implements TurnListener {
	private StatusList statusList;

	/**
	 * SleepStatusTurnListener constructor
	 *
	 * @param statusList StatusList
	 */
	public SleepStatusTurnListener(StatusList statusList) {
		this.statusList = statusList;
	}//SleepStatusTurnListener


	@Override
	public void onTurnReached(int turn)
	{
		RPEntity entity = statusList.getEntity();

		// We check if the player has made steps and we awake the players if they do
	    int haveStepsBeenMade = entity.getStepsTaken();
	    if(haveStepsBeenMade > 0)
	    {
		  entity.sendPrivateText(NotificationType.SCENE_SETTING, "You are awake");
		  statusList.removeAll(SleepStatus.class);
	    }//if
	    else
		{
	      // If the player is still sleeping then we heal
	      entity.heal(entity.getBaseHP() / 200, true);
		  TurnNotifier.get().notifyInSeconds(1, this);
		}//else
	 }//onTurnReached
}//class SleepStatusTurnListener