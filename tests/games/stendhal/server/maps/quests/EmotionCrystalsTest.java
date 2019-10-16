/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2011 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

package games.stendhal.server.maps.quests;

import games.stendhal.server.maps.ados.snake_pit.PurpleCrystalNPC;
import games.stendhal.server.maps.ados.wall.GreeterSoldierNPC;
import games.stendhal.server.maps.fado.hut.BlueCrystalNPC;
import games.stendhal.server.maps.nalwor.forest.RedCrystalNPC;
import games.stendhal.server.maps.nalwor.river.PinkCrystalNPC;
import games.stendhal.server.maps.semos.mountain.YellowCrystalNPC;

import static org.junit.Assert.*;
import static utilities.SpeakerNPCTestHelper.getReply;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player; //

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;



public class EmotionCrystalsTest extends ZonePlayerAndNPCTestImpl {
	
	private static final String QUEST_SLOT = "emotion_crystals";
	private static final String ZONE_NAME = "admin_test";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		setupZone(ZONE_NAME);	
	}
	
	public EmotionCrystalsTest() {
		setNpcNames("Julius","Blue Crystal","Red Crystal","Blue Crystal","yellow Crystal","Purple Crystal","Pink Crystal");
		setZoneForPlayer(ZONE_NAME);
		addZoneConfigurator(new GreeterSoldierNPC(), ZONE_NAME);
		addZoneConfigurator(new BlueCrystalNPC(), ZONE_NAME);
		addZoneConfigurator(new RedCrystalNPC(), ZONE_NAME);
		addZoneConfigurator(new YellowCrystalNPC(), ZONE_NAME);
		addZoneConfigurator(new PinkCrystalNPC(), ZONE_NAME);
		addZoneConfigurator(new PurpleCrystalNPC(), ZONE_NAME);
	
	}
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
        quest = new EmotionCrystals();
		quest.addToWorld();
		
	}
	
	// 1.Test quest can start
	@Test
	public void testQuestStartQuest() {
		SpeakerNPC npc = SingletonRepository.getNPCList().get("Julius");
		Engine en = npc.getEngine();

		en.step(player, "hi");
		assertEquals("Hi, welcome to Ados City!", getReply(npc));
		en.step(player, "quest");
		assertEquals("I don't get to see my wife very often because I am so busy guarding this entrance. I would like to do something for her. Would you help me?", getReply(npc));
		en.step(player, "yes");
		assertEquals("Thank you. I would like to gather five #emotion #crystals as a gift for my wife. Please find all that you can and bring them to me.", getReply(npc));
		assertEquals("start", player.getQuest(QUEST_SLOT));
		en.step(player, "bye");
		assertEquals("I hope you will enjoy your visit to Ados.", getReply(npc));
	}
	
	// 2.Found Crystal while holding
	@Test
	public void testPlayerFoundItemsWhileHolding()
	{		
		
		final Player player = PlayerTestHelper.createPlayer("Mike");
		
		
		// start quest and give items
		player.setQuest(QUEST_SLOT, "start");
		PlayerTestHelper.equipWithItem(player, "blue emotion crystal");
		PlayerTestHelper.equipWithItem(player, "red emotion crystal");
		PlayerTestHelper.equipWithItem(player, "yellow emotion crystal");
		PlayerTestHelper.equipWithItem(player, "purple emotion crystal");
		PlayerTestHelper.equipWithItem(player, "pink emotion crystal");
		
		//expected outcome
        final List<String> expectedHistory = new ArrayList<String>();
        expectedHistory.add("I have talked to Julius, the soldier that guards the entrance to Ados.");
        expectedHistory.add("I promised to gather five crystals from all across Faiumoni.");
        expectedHistory.add("I have found the following crystals: red emotion crystal, purple emotion crystal, yellow emotion crystal, pink emotion crystal, and blue emotion crystal");
        expectedHistory.add("I have obtained all of the emotion crystals and should bring them to Julius in Ados.");
        
        //check the history 
		assertEquals(expectedHistory, quest.getHistory(player));	
		
	}

	// 3.Found Crystal after dropping one crystal
	@Test
	public void testPlayerFoundItemsAfterDroppingItem()
	{	
		
		final Player player = PlayerTestHelper.createPlayer("Gustas");
	
		// start quest and give items
		player.setQuest(QUEST_SLOT, "start");
		PlayerTestHelper.equipWithItem(player, "blue emotion crystal");
		PlayerTestHelper.equipWithItem(player, "red emotion crystal");
		PlayerTestHelper.equipWithItem(player, "yellow emotion crystal");
		PlayerTestHelper.equipWithItem(player, "purple emotion crystal");
		PlayerTestHelper.equipWithItem(player, "pink emotion crystal");
		
		//expected outcome
        final List<String> expectedHistory = new ArrayList<String>();
        expectedHistory.add("I have talked to Julius, the soldier that guards the entrance to Ados.");
        expectedHistory.add("I promised to gather five crystals from all across Faiumoni.");
        expectedHistory.add("I have found the following crystals: red emotion crystal, purple emotion crystal, yellow emotion crystal, pink emotion crystal, and blue emotion crystal");
        expectedHistory.add("I have obtained all of the emotion crystals and should bring them to Julius in Ados.");
        
        //drop two of the crystals
        player.drop("blue emotion crystal");
        player.drop("pink emotion crystal");
        
        //check the history 
		assertEquals(expectedHistory, quest.getHistory(player));	
		
	}

}

