package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TalkBack implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		
		if(arguments.isEmpty())
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "Oh dear, you didn't give me something to say.");
		}
		UtilsAndConstants.sendMessage(event.getChannel(), arguments.get(0));

	}

}
