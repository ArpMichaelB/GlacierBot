package com.glacier.discordbot.commands;

import java.util.ArrayList;

import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionEvent;

public class ReactionHandler {
	@EventSubscriber
    public void onReactionMade(ReactionEvent event)
    {
    	try {
	    	if(event.getUser() == event.getChannel().getClient().getOurUser())
	    	{
	    		return;
	    	}
	    	if(event.getCount() <1 || event.getCount() == 2)
	    	{
	    		return;
	    	}
	    	if(!UtilsAndConstants.checkForValidChoice(event.getReaction().getEmoji().getName()))
	    	{
	    		System.out.println("Invalid Choice of " + event.getReaction().getEmoji().getName());
	    		return;
	    	}
	    	//if the message is being reacted to by the bot, ignore that
	    	//if the reaction made isn't one of the valid buttons, ignore it
	    	//if the message being reacted to isn't an embed, ignore it
	    	int fieldToGet = UtilsAndConstants.translateFromEmoji(event.getReaction().getEmoji().getName());
	    	String name = event.getMessage().getEmbeds().get(0).getEmbedFields().get(fieldToGet).getValue();
	    	name = name.substring(name.indexOf("(")+1, name.lastIndexOf(")"));
	    	//get the link based on what choice the user made
	    	ArrayList<String> temp = new ArrayList<String>();
	    	temp.add(name);
	    	new OrdinaryPlayer().runCommand(new MessageReceivedEvent(event.getMessage()), temp);
    	}
    	catch(ArrayIndexOutOfBoundsException ex)
    	{
    		UtilsAndConstants.sendMessage(event.getChannel(), "Really, you went out of your way to pick a valid option, not on the page?");
    	}
    }
}