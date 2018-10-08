package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class GoodNight implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		System.out.println("Bot closed at " + UtilsAndConstants.getCurrentTimestamp());
		System.exit(0);
		//I never use system.exit, but it seems appropriate here
		//since without this command, you'd have to go close it in the task manager
		//and that's a lot of work
	}

}
