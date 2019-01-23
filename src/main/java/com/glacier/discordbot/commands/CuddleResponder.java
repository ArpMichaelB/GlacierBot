package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class CuddleResponder implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		UtilsAndConstants.sendMessage(event.getChannel(), "Aww, that's real nice of you,"
				+ " but I don't have a body to cuddle! Perhaps later.");
	}

}
