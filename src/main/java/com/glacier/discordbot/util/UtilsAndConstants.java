package com.glacier.discordbot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class UtilsAndConstants {
	public static String BOT_PREFIX = "~";
	
	
	public static void setErrorToLog() {
		File logFolder = new File("C:\\Glacier Nester\\logs");
    	File file = null;
    	if(!logFolder.exists())
    	{
    		logFolder.setWritable(true);
    		if(logFolder.mkdirs())
    		{
    			file = new File("C:\\Glacier Nester\\logs\\GlacierBot.log");
    		}
    	}
    	else
    	{
    		file = new File("C:\\Glacier Nester\\logs\\GlacierBot.log");
    	}
    	try {
	    	FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setErr(ps);
			System.err.println("Started GlacierBot at " + DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss").format(LocalDateTime.now()));
    	}
    	catch(IOException ex)
    	{
    		System.out.println("Oh dear, making the log failed. That's an issue!");
    	}
	}
	
	/**
	 * Creates a discord Client (what's needed to send messages/interact) for the token it's passed
	 * @param token
	 * @return A DiscordClient for the bot to work with
	 */
    public static IDiscordClient getBuiltDiscordClient(String token){

        return new ClientBuilder()//build a client
                .withToken(token)//with the token it's been passed
                .withRecommendedShardCount()//and the reccomended shard count
                //look at the docs for what shards are
                .build();

    }
    /**
     * a function that, given a channel and a message, sends that message to the channel
     * I'm considering including a command to set a default channel which is stored as a constant above
     * In addition to that, a function to change the prefix, but I need to make this work simply first
     * @param channel The channel to send the message to
     * @param message the message to send to the channel
     */
    public static void sendMessage(IChannel channel, String message){

    	//arrow functions don't always make much sense to me
    	//but I think what's going down here is, we pass RequestBuffer's request method a function to run
    	//which, in this case, sends a message or passes information to the log file
        RequestBuffer.request(() -> {
            try{
                channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent, timestamp " + DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss").format(LocalDateTime.now()) + " and following trace");
                e.printStackTrace();
            }
        });

    }
}
