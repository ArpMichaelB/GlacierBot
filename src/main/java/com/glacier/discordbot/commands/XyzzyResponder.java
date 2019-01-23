package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class XyzzyResponder implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		//TODO: add zorkly responses
		if(arguments.isEmpty())
		{
			UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice mutters \"Fool.\"");
		}
		if(arguments.get(0).equals("keys"))
		{
			UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice sings:\r\n" + 
					"\"I xyz zy spiders on the wall... I xyz zy cobwebs in the hall...\r\n" + 
					"I xyz zy candles on the shelf... \"");
		}
	}

}
