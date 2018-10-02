package com.glacier.discordbot.commands;

import java.util.List;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public interface Command {
	void runCommand(MessageReceivedEvent event, List<String> arguments);
}
