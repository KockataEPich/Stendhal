/* $Id$ */
package games.stendhal.server.script;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalPlayerDatabase;
import games.stendhal.server.core.scripting.ScriptImpl;
import games.stendhal.server.entity.npc.ConversationStates;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Transition;
import games.stendhal.server.entity.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Dumps information of all SpeakerNPCs to the database
 * 
 * @author hendrik
 */
public class DumpSpeakerNPCtoDB extends ScriptImpl {
	private static Logger logger = Logger.getLogger(DumpSpeakerNPCtoDB.class);

	@Override
	public void execute(final Player admin, final List<String> args) {
		
		
		try {
			long start = System.currentTimeMillis();
			Connection con = StendhalPlayerDatabase.getDatabase().getTransaction().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO npcs " +
				"(name, title, class, outfit, hp, base_hp, zone, x, y, level, description, job)" +
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			stmt.execute("start transaction;");
			stmt.execute("DELETE FROM npcs");
			for (SpeakerNPC npc : SingletonRepository.getNPCList()) {
				dumpNPC(stmt, npc);
			}
			stmt.executeBatch();

			con.commit();
			stmt.close();
			logger.debug((System.currentTimeMillis() - start));
		} catch (SQLException e) {
			logger.error(e, e);
			admin.sendPrivateText(e.toString());
		}
	}

	/**
	 * dumps the properties of the specified SpeakerNPC into the prepared statement as an operation in a batch.
	 * 
	 * @param stmt PreparedStatement in batch mode
	 * @param npc  SpeakerNPC
	 * @throws SQLException in case a database error is thrown.
	 */
	private void dumpNPC(PreparedStatement stmt, SpeakerNPC npc) throws SQLException {
		stmt.setString(1, npc.getName());
		stmt.setString(2, npc.getTitle());
		stmt.setString(3, npc.get("class"));
		stmt.setString(4, getOutfit(npc));
		stmt.setInt(5, npc.getHP());
		stmt.setInt(6, npc.getBaseHP());
		stmt.setString(7, npc.getZone().getName());
		stmt.setInt(8, npc.getX());
		stmt.setInt(9, npc.getY());
		stmt.setInt(10, npc.getLevel());
		stmt.setString(11, npc.getDescription());
		stmt.setString(12, getJob(npc));
		stmt.addBatch();
	}

	/**
	 * gets the answer to the "job" question in ATTENTING state.
	 *
	 * @param npc SpeakerNPC object
	 * @return the answer to the job question or null in case there is no job specified
	 */
	private String getJob(SpeakerNPC npc) {
		List<Transition> transitions = npc.getEngine().getTransitions();
		for (Transition transition : transitions) {
			if (transition.getState() == ConversationStates.ATTENDING) {
				if (transition.getTrigger().getOriginal().equals("job")) {
					return transition.getReply();
				}
			}
		}
		return null;
	}

	/**
	 * gets the outfit code as string
	 *
	 * @param npc SpeakerNPC object
	 * @return outfit code as string or null incase there is not outfit specified
	 */
	private String getOutfit(SpeakerNPC npc) {
		String outfit = null;
		if (npc.getOutfit() != null) {
			outfit = Integer.toString(npc.getOutfit().getCode());
		}
		return outfit;
	}
}
