package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TwitchTagsSetter implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		// TODO Auto-generated method stub
		UtilsAndConstants.sendMessage(event.getChannel(), "Still working on it!");
	}

}
