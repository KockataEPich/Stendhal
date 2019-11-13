package games.stendhal.server.maps.deniran;
/* $Id$ */
/***************************************************************************
 * 	This is a flea market npc which can sell 							   *
 * 	you unwanted staff from other playser.								   *
 * 	Arthur Heng.Gao 												   	   *
 ***************************************************************************/


import java.util.Map;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;


public class FleaMarketNPC implements ZoneConfigurator {
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */

	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildNPC(zone);
	}

	
	private void buildNPC(final StendhalRPZone zone) {
		final SpeakerNPC npc = new SpeakerNPC("FM_test_NPC");
		zone.add(npc);
		}
}