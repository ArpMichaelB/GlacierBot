package com.glacier.discordbot.commands;


import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TalkBack implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		
		System.out.println("Talkback invoked by " + event.getAuthor().getName() + " at " + UtilsAndConstants.getCurrentTimestamp());
		
		if(arguments.isEmpty())
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "Oh dear, you didn't give me something to say.");
		}
		String thingToSay = String.join(" ", arguments);
		UtilsAndConstants.sendMessage(event.getChannel(), thingToSay);
		
	}

}
