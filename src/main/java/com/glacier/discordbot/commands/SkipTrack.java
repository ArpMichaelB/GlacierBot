package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SkipTrack implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
			
		int counter = 1;
		GuildMusicManager musicManager = UtilsAndConstants.getGuildAudioPlayer(event.getChannel().getGuild());
        if(arguments.get(0).matches("\\d"))
        {
        	counter = Integer.parseInt(arguments.get(0));
        }
        for(int i = 0; i<counter;i++)
        {
        	musicManager.getScheduler().nextTrack();
        	System.out.println("Track skipped at the reqeust of " + event.getAuthor().getName() + " at " + UtilsAndConstants.getCurrentTimestamp());
        }
        UtilsAndConstants.sendMessage(event.getChannel(), "Skipped to next track.");

	}

}
