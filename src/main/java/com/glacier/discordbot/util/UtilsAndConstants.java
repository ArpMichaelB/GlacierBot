package com.glacier.discordbot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import com.glacier.discordbot.handlers.CommandHandler;
import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

public class UtilsAndConstants {
	public static String BOT_PREFIX = "~";
	private static final String PROPERTIES_FILENAME = "discordbot.properties";	
	public static final String BEGINNING_PIECE_OF_URL = "http://www.youtube.com/watch?v=";
	public static final double MENU_SIZE = 500;
	public static final double MENU_SIZE_TWO = 500;
	public static Properties properties = setupProperties();
	public static int MAX_ITEMS_TO_FETCH = 5;
	//public static Logger logger = LoggerFactory.getLogger(App.class);
	//I'm going to persist in using system.err for my logging because I don't need anything special
	//additionally adding this logger didn't solve the "Defaulting to slf4j" whatnot it screms about
	//so I'm not going to include it, just leave this commented in line as a reminder to sort out how to fix this
	
	public static void setupLogFiles()
	{
		setOutToLogFile();
		setErrorToLogFile();
	}
	
	public static Properties setupProperties()
	{
		Properties properties = new Properties();
        try {
        	ClassLoader classLoader = UtilsAndConstants.class.getClassLoader();
        	File propertiesFile = new File(classLoader.getResource(PROPERTIES_FILENAME).getFile());
            InputStream in = new FileInputStream(propertiesFile);
            properties.load(in);
            return properties;
        } catch (IOException e) {
            System.err.println("There was an error reading " + UtilsAndConstants.PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage() + " at " + getCurrentTimestamp());
            return null;
        }
        catch(NullPointerException ex)
        {
        	System.err.println("There's no properties file available, launching interface to create it now.");
        	String[] args = new String[0];
        	ProjectFileCreator.launch(ProjectFileCreator.class,args);
        	return null;
        }
	}
	
	
	private static void setOutToLogFile() {
		String baseDrive = File.listRoots()[0].getPath();
		File logFolder = new File(baseDrive + "Glacier Nester/logs");
    	File file = null;
    	if(!logFolder.exists())
    	{
    		logFolder.setWritable(true);
    		if(logFolder.mkdirs())
    		{
    			file = new File(baseDrive + "Glacier Nester/logs/GlacierBot.log");
    		}
    	}
    	else
    	{
    		file = new File(baseDrive + "Glacier Nester/logs/GlacierBot.log");
    	}
    	try {
	    	FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
			System.out.println("Started GlacierBot at " + getCurrentTimestamp());
    	}
    	catch(IOException ex)
    	{
    		System.err.println("Oh dear, making the log failed. That's an issue!");
    	}
	}


	private static void setErrorToLogFile() {
		String baseDrive = File.listRoots()[0].getPath();
		File logFolder = new File(baseDrive + "Glacier Nester/logs");
    	File file = null;
    	if(!logFolder.exists())
    	{
    		logFolder.setWritable(true);
    		if(logFolder.mkdirs())
    		{
    			file = new File(baseDrive + "Glacier Nester/logs/GlacierBotErrors.log");
    		}
    	}
    	else
    	{
    		file = new File(baseDrive + "Glacier Nester/logs/GlacierBotErrors.log");
    	}
    	try {
	    	FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setErr(ps);
			System.err.println("Started GlacierBot at " + getCurrentTimestamp());
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
                //look at the docs for what shards are in detail
                //but the short version is that they manage how much threads your bot can use
                //based on how many discord servers it's in
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
                System.err.println("Message could not be sent, timestamp " + getCurrentTimestamp() + " and following trace");
                e.printStackTrace();
            }
            catch(NullPointerException ex)
            {
            	System.err.println("NullPointerException, timestamp " + getCurrentTimestamp() + " and following trace");
                ex.printStackTrace();
            }
        });

    }

	public static synchronized GuildMusicManager getGuildAudioPlayer(IGuild guild) {
        long guildId = guild.getLongID();
        GuildMusicManager musicManager = CommandHandler.musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(new DefaultAudioPlayerManager());
            CommandHandler.musicManagers.put(guildId, musicManager);
        }
        guild.getAudioManager().setAudioProvider(musicManager.getAudioProvider());

        return musicManager;
    }

	public static String getCurrentTimestamp() {
		return DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss").format(LocalDateTime.now());
	}

	public static String translateToEmoji(int input) {
		switch (input) {
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		case 10:
			return "keycap_ten";
		default:
			return "one";
		}
		
	}
	
	public static int translateFromEmoji(String emoji) 
	{	
		if(emoji.equals(EmojiManager.getForAlias("one").getUnicode()))
			return 0;
		else if (emoji.equals(EmojiManager.getForAlias("two").getUnicode()))
			return 1;
		else if (emoji.equals(EmojiManager.getForAlias("three").getUnicode()))
			return 2;
		else if(emoji.equals(EmojiManager.getForAlias("four").getUnicode()))
			return 3;
		else if(emoji.equals(EmojiManager.getForAlias("five").getUnicode()))
			return 4;
		else if(emoji.equals(EmojiManager.getForAlias("six").getUnicode()))
			return 5;
		else if(emoji.equals(EmojiManager.getForAlias("seven").getUnicode()))
			return 6;
		else if(emoji.equals(EmojiManager.getForAlias("eight").getUnicode()))
			return 7;
		else if(emoji.equals(EmojiManager.getForAlias("nine").getUnicode()))
			return 8;
		else if(emoji.equals(EmojiManager.getForAlias("keycap_ten").getUnicode()))
			return 9;
		else
			return 0;
		//are there better ways of doing this? maybe
		//do I know them? no
	}
	
	public static boolean checkForValidChoice(String reaction) {
		for(String s : validChoices())
		{
			if(s.equals(reaction))
			{
				return true;
			}
		}
		return false;
	}

	private static ArrayList<String> validChoices() {
		ArrayList<String> ret = new ArrayList<String>();
		for(int i = 0; i<10;i++)
		{
			ret.add(EmojiManager.getForAlias(translateToEmoji(i)).getUnicode());
		}
		return ret;
	}
	
	public static void reactToMessage(IMessage message, Emoji reaction) {
		RequestBuffer.request(() -> {
			message.addReaction(reaction);
		});
	}
}
