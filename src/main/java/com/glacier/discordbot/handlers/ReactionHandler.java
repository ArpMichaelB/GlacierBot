package com.glacier.discordbot.handlers;

import java.util.ArrayList;

import com.glacier.discordbot.commands.OrdinaryPlayer;
import com.glacier.discordbot.commands.TwitchTagsSetter;
import com.glacier.discordbot.commands.TwitchTagsGetter;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionEvent;
import sx.blah.discord.handle.impl.obj.ReactionEmoji;
import sx.blah.discord.handle.obj.Permissions;

public class ReactionHandler {
	@EventSubscriber
    public void onReactionMade(ReactionEvent event)
    {
    	try {
	    	if(event.getUser() == event.getChannel().getClient().getOurUser())
	    	{
	    		return;
	    	}
	    	if(event.getReaction().getEmoji().equals(ReactionEmoji.of("❌")))
	    	{
	    		event.getMessage().delete();
	    		return;
	    	}
	    	if(event.getCount() <1 || event.getCount() < 2)
	    	{
	    		return;
	    	}
	    	if(event.getMessage().getEmbeds().get(0).getTitle().equalsIgnoreCase("Tag Options"))
	    	{
	    		if(event.getReaction().getEmoji().equals(ReactionEmoji.of("✅")))
		    	{
		    		if(event.getUser().getPermissionsForGuild(event.getGuild()).contains(Permissions.MANAGE_SERVER) || event.getUser().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR))
		    		{
		    			new TwitchTagsSetter().runCommand(new MessageReceivedEvent(event.getMessage()), UtilsAndConstants.getSavedTags());
		    			UtilsAndConstants.sendMessage(event.getChannel(), "Tags set, go check it out!");
		    		}
		    	}
		    	if(event.getReaction().getEmoji().equals(ReactionEmoji.of("➡")))
		    	{
		    		String pagination = event.getMessage().getEmbeds().get(0).getFooter().getText();
		    		ArrayList<String> temp = new ArrayList<String>();
			    	temp.add(pagination);
			    	new TwitchTagsGetter().runCommand(new MessageReceivedEvent(event.getMessage()), temp);
			    	//use the pagination cursor to call the twitch tags setter for the next message
		    	}
	    	}
	    	if(!UtilsAndConstants.checkForValidChoice(event.getReaction().getEmoji().getName()))
	    	{
	    		if(event.getMessage().getEmbeds().get(0).getTitle().equalsIgnoreCase("Tag Options") && (event.getReaction().getEmoji().equals(ReactionEmoji.of("✅"))||event.getReaction().getEmoji().equals(ReactionEmoji.of("➡"))))
	    		//i.e. if it's the message that allows right arrow or checkmark, the choice actually _is_ valid
	    		{
	    			return;
	    		}
	    		System.out.println("Invalid Choice of " + event.getReaction().getEmoji().getName());
	    		return;
	    	}
	    	//if the message is being reacted to by the bot, ignore that
	    	//if the reaction made isn't one of the valid buttons, ignore it
	    	//if the message being reacted to isn't an embed, ignore it
	    	int fieldToGet = UtilsAndConstants.translateFromEmoji(event.getReaction().getEmoji().getName());
	    	if(event.getMessage().getEmbeds().get(0).getTitle().equalsIgnoreCase("Choose A Video"))
	    	{
	    		//i.e. if it's a list of videos to pick from
		    	String url = event.getMessage().getEmbeds().get(0).getEmbedFields().get(fieldToGet).getValue();
		    	url = url.substring(url.indexOf("(")+1, url.lastIndexOf(")"));
		    	//get the link based on what choice the user made
		    	ArrayList<String> temp = new ArrayList<String>();
		    	temp.add(url);
		    	new OrdinaryPlayer().runCommand(new MessageReceivedEvent(event.getMessage()), temp);
    		}
	    	if(event.getMessage().getEmbeds().get(0).getTitle().equalsIgnoreCase("Tag Options"))
	    	{
	    		String tagID = event.getMessage().getEmbeds().get(0).getEmbedFields().get(fieldToGet).getValue();
	    		//store the chosen tag's ID
	    		UtilsAndConstants.addSavedTag(tagID);
	    	}
    	}
    	catch(ArrayIndexOutOfBoundsException ex)
    	{
    		System.out.println("User " + event.getAuthor().getName() + " decided to be clever and pick a valid option which didn't exist, at " + UtilsAndConstants.getCurrentTimestamp());
    		UtilsAndConstants.sendMessage(event.getChannel(), "Really, you went out of your way to pick a valid option, not on the page?");
    	}
    }
}
