package games.stendhal.server.maps;

import games.stendhal.server.core.engine.StendhalRPRuleProcessor;
import games.stendhal.server.entity.player.Player;

public class MockStendhalRPRuleProcessor extends StendhalRPRuleProcessor {

	public static MockStendhalRPRuleProcessor get() {
		if (!(instance instanceof MockStendhalRPRuleProcessor)) {
			instance = new MockStendhalRPRuleProcessor();
		}

		return (MockStendhalRPRuleProcessor) instance;
	}

	@Override
	public void addGameEvent(String source, String event, String... params) {
		// do not log to database during test
	}

	@Override
	public int getTurn() {
		return 0;
	}

	/**
	 * Adds a player object to the list of players
	 *
	 * @param player Player
	 */
	public void addPlayer(Player player) {
		while (getPlayers().contains(player)){
			this.onlinePlayers.getPlayers().remove(player);
			
		}
			
		
			this.onlinePlayers.getPlayers().add(player);
		
	}

	/**
	 * Removes a player from the list of players
	 * 
	 * @param player Player
	 */
	public void removePlayer(Player player) {
		getPlayers().remove(player);
	}

	public void clearPlayers() {
		onlinePlayers.getPlayers().clear();
		
	}
}
