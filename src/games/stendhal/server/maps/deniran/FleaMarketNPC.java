package games.stendhal.server.maps.deniran;
import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;


public class FleaMarketNPC implements ZoneConfigurator {
	/* $Id$ */
	/**
	 * An Flea Market Assistance who can be hired by player to manage players' flea stall.
	 * It is just initialised.
	 */

	@Override
	public void configureZone(StendhalRPZone zone,
			Map<String, String> attributes) {
		buildNPC(zone);
	}

	private void buildNPC(final StendhalRPZone zone) {
		final SpeakerNPC npc = new SpeakerNPC("FM_test_NPC");
		
		npc.setPosition(10, 10);
		npc.setEntityClass("oldmannpc");
		npc.setDescription("Fleamarket assistant at the flea stall ");
		npc.setDirection(Direction.DOWN);
		zone.add(npc);
	}
	

}
