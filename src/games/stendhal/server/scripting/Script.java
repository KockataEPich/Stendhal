package games.stendhal.server.scripting;

import games.stendhal.server.entity.Player;

import java.util.List;

/**
 * A script, which can be reloading at runtime
 *
 * @author hendrik
 */
public interface Script {

	/**
	 * Initial load of the script
	 *
	 * @param admin   the admin who load it or <code>null</code> on server start.
	 * @param args    the arguments the admin specified or <code>null</code> on server start.
	 * @param sandbox all modifications to the game must be done using this object in order for the script to be unloadable
	 */
	public void load(Player admin, List<String> args, ScriptingSandbox sandbox);

	/**
	 * Unloads the script
	 *
	 * @param admin   the admin who load it or <code>null</code> on server start.
	 * @param args    the arguments the admin specified or <code>null</code> on server start.
	 */
	public void unload(Player admin, List<String> args);
	
	/**
	 * Execution of this script
	 *
	 * @param admin   the admin who load it or <code>null</code> on server start.
	 * @param args    the arguments the admin specified or <code>null</code> on server start.
	 */
	public void execute(Player admin, List<String> args);
}
