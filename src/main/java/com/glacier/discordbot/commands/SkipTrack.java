package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SkipTrack implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		
		//TODO: Add the ability to use arguments to skip more than 1 track
		GuildMusicManager musicManager = UtilsAndConstants.getGuildAudioPlayer(event.getChannel().getGuild());
        musicManager.getScheduler().nextTrack();
        UtilsAndConstants.sendMessage(event.getChannel(), "Skipped to next track.");

	}

}
