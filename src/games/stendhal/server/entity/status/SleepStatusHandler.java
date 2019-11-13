/***************************************************************************
 *                   (C) Copyright 2013 - Faiumoni e. V.                   *
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
//import games.stendhal.common.NotificationType;
import games.stendhal.server.core.events.TurnListener;
import games.stendhal.server.core.events.TurnNotifier;
import games.stendhal.server.entity.Entity;
//import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.RPEntity;

/**
 * handles SleepStatusHandler
 */
public class SleepStatusHandler implements StatusHandler<SleepStatus> {

	/**
	 * inflicts a status
	 *
	 * @param status Status to inflict
	 * @param statusList StatusList
	 * @param attacker the attacker
	 */
	@Override
	public void inflict(SleepStatus status, StatusList statusList, Entity attacker)
	{

		// We check if the status is already present
		int count = statusList.countStatusByType(status.getStatusType());
		if (count == 0)
		{
			// Add the status
			statusList.addInternal(status);

			TurnListener turnListener = new SleepStatusTurnListener(statusList);

			RPEntity entity = statusList.getEntity();
			entity.sendPrivateText(NotificationType.SCENE_SETTING, "You have fallen asleep!");
			TurnNotifier.get().notifyInTurns(0, turnListener);

		}//if
	}
	/**
	 * removes a status
	 *
	 * @param status Status to inflict
	 * @param statusList StatusList
	 */
	@Override
	public void remove(SleepStatus status, StatusList statusList) {

		RPEntity entity = statusList.getEntity();
		entity.sendPrivateText(NotificationType.SCENE_SETTING, "You are now awake!");
		statusList.removeInternal(status);

	}//remove
}//class SleepStatusHandler