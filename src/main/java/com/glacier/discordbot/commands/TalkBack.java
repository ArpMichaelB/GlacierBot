package com.glacier.discordbot.commands;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TalkBack implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		
		System.out.println("Talkback invoked at " + DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss").format(LocalDateTime.now()));
		
		if(arguments.isEmpty())
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "Oh dear, you didn't give me something to say.");
		}
		UtilsAndConstants.sendMessage(event.getChannel(), arguments.get(0));

	}

}
