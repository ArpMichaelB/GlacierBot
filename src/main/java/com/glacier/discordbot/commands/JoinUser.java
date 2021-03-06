package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class JoinUser implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		IVoiceChannel voiceChannel = event.getGuild().getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();
		if(voiceChannel == null)
		{
			if(event.getAuthor() == event.getGuild().getClient().getOurUser())
			{
				UtilsAndConstants.sendMessage(event.getChannel(), "I'm not in a voice channel, get me to join one please!");
				return;
			}
			IVoiceChannel userVoiceChannel = event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();

	        if(userVoiceChannel == null)
	        {
	        	UtilsAndConstants.sendMessage(event.getChannel(), "Join a voice channel, and try again.");
	        	return;
	        }

	        userVoiceChannel.join();
	      //since it already logs when voice socket is connected/disconnected by default, we don't need to log this event in the log file
		}
	}

}
