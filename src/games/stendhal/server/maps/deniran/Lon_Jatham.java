package games.stendhal.server.maps.deniran;
import games.stendhal.server.entity.npc.SpeakerNPC;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;

public class Lon_Jatham implements ZoneConfigurator  {


	@Override
	public void configureZone(StendhalRPZone zone,
			Map<String, String> attributes) {
		buildNPC(zone);
	}
	
    private void buildNPC(final StendhalRPZone zone) {
	    final SpeakerNPC npc = new SpeakerNPC("Lon Jatham") {
	    	@Override
        protected void createPath() {
	               List<Node> nodes=new LinkedList<Node>();
	                nodes.add(new Node(63,6));
	                nodes.add(new Node(60,6));
	                setPath(new FixedPath(nodes,true));
        }

	    	@Override
        protected void createDialog() {
            // Lets the NPC reply with "Hallo" when a player greets him. But we could have set a custom greeting inside the ()
            addGreeting("Hello, I am the famous lecturer Lon Jatham. You are just in time.");
            // Lets the NPC reply when a player says "job"
            addJob("I can teach you java.I am java wizard.");
            // use standard goodbye, but you can also set one inside the ()
            addGoodbye("Bye! Have a great time and don't forget about the coffee time.");
        }
    };

    // This determines how the NPC will look like. welcomernpc.png is a picture in data/sprites/npc/
    npc.setEntityClass("wisemannpc");
    // set a description for when a player does 'Look'
    npc.setDescription("You see Mr Lon Jatham, he looks a a bit busy at the moment but perhaps he can help you anyway.");
    // Set the initial position to be the first node on the Path you defined above.
    npc.setPosition(63, 6);
    npc.initHP(100);

    zone.add(npc);   
    }
	
	
	
	
	
}
	