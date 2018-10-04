package com.glacier.discordbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import com.glacier.discordbot.commands.CommandHandler;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.api.IDiscordClient;

/**
 * Overall, this bot is using the D4J example bot as a base, which I then build upon.
 * The major work which reflects my own efforts is in the response to the commands, 
 * rather than what wraps around them to make it interact with discord
 * Comparison with the code available at https://github.com/decyg/d4jexamplebot 
 * will make the difference between my work and what I've been borrowing incredibly evident
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

        // Only login after all events are registered otherwise some may be missed.
        cli.login();
    }
}
