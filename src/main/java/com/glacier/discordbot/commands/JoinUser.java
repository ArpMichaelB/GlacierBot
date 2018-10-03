package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class JoinUser implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		IVoiceChannel voiceChannel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();
		if(voiceChannel == null)
		{
			IVoiceChannel userVoiceChannel = event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();

	        if(userVoiceChannel == null)
	        {
	        	UtilsAndConstants.sendMessage(event.getChannel(), "Join a voice channel, and try again.");
	        	return;
	        }

	        userVoiceChannel.join();
		}
	}

}
