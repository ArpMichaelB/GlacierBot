package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;

public class PrefixChanger implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		String newPrefix = arguments.get(0);
		
		if(newPrefix == null)
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "Try again, with something for me to look for!");
		}
		else if(event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR) || event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.MANAGE_SERVER))
		{
			//if they're a admin or server manager, they probably are trusted enough to change the bot
			UtilsAndConstants.BOT_PREFIX = newPrefix;
			UtilsAndConstants.sendMessage(event.getChannel(), "Command prefix changed to " + UtilsAndConstants.BOT_PREFIX + " so the commands now look like " + UtilsAndConstants.BOT_PREFIX + "command, hope that's what you meant!");
			System.out.println("Prefix changed to " + UtilsAndConstants.BOT_PREFIX + " by " + event.getAuthor().getName() +" at " + UtilsAndConstants.getCurrentTimestamp());
		}
		else
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "Oh dear, doesn't look like you've got permission to do that. Good try!");
			System.out.println("User " + event.getAuthor().getName() + " tried to edit the bot's prefix at "+ UtilsAndConstants.getCurrentTimestamp() +", you may want to look into that.");
		}
	}

}
