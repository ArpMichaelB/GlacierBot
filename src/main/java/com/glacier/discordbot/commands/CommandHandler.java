package com.glacier.discordbot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionEvent;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.handle.obj.IEmbed.IEmbedField;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageHistory;
import sx.blah.discord.util.RequestBuffer;

public class CommandHandler {
	
	// A static map of commands mapping from command string to the functional impl
    private static Map<String, Command> commandMap = new HashMap<>();
    //the music managers map is put here so that all the commands can get to it
    public static final Map<Long, GuildMusicManager> musicManagers  = new HashMap<>();
	
    //TODO: set up this command map to contain the commands
    //TODO: set up the reactionEvent handler
    
    static
    {
    	//I suppose since commandMap has to be static, 
    	//the placement of said commands have to be static
    	commandMap.put("say", new TalkBack());
    	commandMap.put("join", new JoinUser());
    	commandMap.put("leave", new LeaveUser());
    	commandMap.put("play", new OrdinaryPlayer());
    	commandMap.put("glacier", new GlacierVideoSelector());
    	commandMap.put("skip", new SkipTrack());
    }
    
    @EventSubscriber
    public void onReactionMade(ReactionEvent event)
    {
    	
    	if(event.getUser() == event.getChannel().getClient().getOurUser())
    	{
    		return;
    	}
    	if(event.getCount() <=1)
    	{
    		return;
    	}
    	if(!UtilsAndConstants.checkForValidChoice(event.getReaction().getEmoji().getName()))
    	{
    		System.out.println("Invalid Choice of " + event.getReaction().getEmoji().getName());
    		return;
    	}
    	if(event.getMessage().getEmbeds().isEmpty())
    	{
    		System.out.println("not an embed");
    		return;
    	}
    	//if the message is being reacted to by the bot, ignore that
    	//if the reaction made isn't one of the valid buttons, ignore it
    	//if the message being reacted to isn't an embed, ignore it
    	int fieldToGet = UtilsAndConstants.translateFromEmoji(event.getReaction().getEmoji().getName());
    	String name = event.getMessage().getEmbeds().get(0).getEmbedFields().get(fieldToGet).getValue();
    	name = name.substring(name.indexOf("(")+1, name.lastIndexOf(")")-1);
    	//get the link based on what choice the user made
    	System.out.println(name + " field chosen at " + UtilsAndConstants.getCurrentTimestamp());
    }
    
    @EventSubscriber
    public void onMessageEmbedded(MessageUpdateEvent event)
    {
        if(event.getMessage().getAuthor() == event.getChannel().getClient().getOurUser())
    	{
    		System.out.println("Detected Self Authored message change at " + UtilsAndConstants.getCurrentTimestamp());
    		MessageHistory history = event.getChannel().getMessageHistory();
    		IMessage message = history.getLatestMessage();
    		if(message.getEmbeds().isEmpty())
    		{
    			System.out.println("Updated Message was not an Embed, cancelling operation at " + UtilsAndConstants.getCurrentTimestamp());
    			return;
    			//if the message that was edited isn't an embedded message, do nothing
    		}
    		IEmbed embeddedMessage = message.getEmbeds().get(0);
    		if(embeddedMessage.getEmbedFields().get(0).getName().contains("1. "))
    		{
    			//if the embedded message's first field contains the first number heading
    			//run through the whole thing and react with the appropriate reaction
    			//TODO: check into replacing the name with checking the embedded message's title instead
	    		int counter = 1;
	    		for(IEmbedField data : embeddedMessage.getEmbedFields())
	    		{
					Emoji reaction = EmojiManager.getForAlias(UtilsAndConstants.translateToEmoji(counter));
	    			RequestBuffer.request(() -> {
	    				message.addReaction(reaction);
	    			});
	    			//essentially requestbuffer makes the bot wait until it is able to do the thing, then does it
	    			//Like sending a message, I might make this a method in utils and constants 
	    			counter++;
				}
    		}
    	}
    }
    
	@EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
		//If there's an error, we'll be outputting about it to the log file
		//At least initially
		//Eventually, I'll look into messaging the user which tried to make the command 
		//and send them their error/a help dialog
		
        //split the message into the command and the arguments
		String[] argArray = event.getMessage().getContent().split(" ");

		//if there's not anything in the message, stop the method
		//It's a bit odd that the example bot uses returns to stop the method despite it being a void
		//I had no idea that's a technique until now, and I like it, a lot!
		//might start using this in my own code, actually
        if(argArray.length == 0)
            return;

        //if the "command" doesn't start with the right prefix, don't do anything with it
        if(!argArray[0].startsWith(UtilsAndConstants.BOT_PREFIX))
            return;

        //remove the prefix from the command
        String commandStr = argArray[0].substring(UtilsAndConstants.BOT_PREFIX.length());

        //rather than trying to handle the array with the command and args in it
        //we're tossing the rest of them into an arraylist to make it easier to handle
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // Remove the command

        //this is INCREDIBLY clever
        //essentially, we get the command's code from the map of all commands
        //and then call its runCommand method right here
        //I personally would have like, done some really inefficient stuff with a bunch of if/elses or a switch
        //but this is much better
        if(commandMap.containsKey(commandStr))
            commandMap.get(commandStr).runCommand(event, argsList);
        else
        {
        	System.err.println("Failed command " + commandStr + " at " + UtilsAndConstants.getCurrentTimestamp());
        }
    }
}
