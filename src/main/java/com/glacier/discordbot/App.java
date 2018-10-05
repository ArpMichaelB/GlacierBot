package com.glacier.discordbot;

import com.glacier.discordbot.commands.CommandHandler;
import com.glacier.discordbot.commands.EmbedHandler;
import com.glacier.discordbot.commands.ReactionHandler;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.api.IDiscordClient;

/**
 * As the project continues, I think I've stepped far enough away from the example discord bot to call this code entirely my own
 * but I will state that the starting point for this project was the example bot found at https://github.com/decyg/d4jexamplebot
 * There are bits and pieces that remain of it if you look closely
 * but I've layered in enough changes that I'd say this is essentially all mine now
 * @author Michael Arp (aka Glacier Nester)
 */
public class App 
{
    public static void main( String[] args )
    {
    	UtilsAndConstants.setupLogFiles();
    	
    	if(UtilsAndConstants.properties == null)
    	{
    		System.err.println("Error, no properties file found. Cancelling launch at " + UtilsAndConstants.getCurrentTimestamp());
    		return;
    	}
    	else if(!UtilsAndConstants.properties.containsKey("discord.key"))
        {
        	System.err.println("Error, no discord token found. Cancelling Launch at " + UtilsAndConstants.getCurrentTimestamp());
        	return;
        }
        
        IDiscordClient cli = UtilsAndConstants.getBuiltDiscordClient(UtilsAndConstants.properties.getProperty("discord.key"));

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        cli.getDispatcher().registerListener(new CommandHandler());
        cli.getDispatcher().registerListener(new EmbedHandler());
        cli.getDispatcher().registerListener(new ReactionHandler());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();
    }
}
