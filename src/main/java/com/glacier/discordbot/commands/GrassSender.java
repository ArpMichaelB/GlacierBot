package com.glacier.discordbot.commands;

import java.util.List;
import java.util.Random;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class GrassSender implements Command
{

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		String grass = buildGrass();
		if(!arguments.isEmpty())
		{
			grass += "\n No arguments, only grass.";
		}
		UtilsAndConstants.sendMessage(event.getChannel(), grass);
	}

	private String buildGrass() {
		String one = "⎛";

		String two = "⎞";
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < 6; j++)
		{
			for(int i = 0; i < 48; i++) 
			{
				if(new Random().nextBoolean())
				{
					sb.append(one);
				}
				else
				{
					sb.append(two);
				}
			}
		}
		return sb.toString();
	}	
}
