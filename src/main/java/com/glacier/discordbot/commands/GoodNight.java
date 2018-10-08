package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;

public class GoodNight implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		
		
		if(event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR) || event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.MANAGE_SERVER))
		{
			System.out.println("Bot closed at " + UtilsAndConstants.getCurrentTimestamp());
			System.exit(0);
		}
		else
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "Sorry, you're not allowed to put the boi to sleep. Maybe ask to do that?");
			System.out.println("Hey, the user " + event.getAuthor().getName() + " tried to turn off the bot at "+ UtilsAndConstants.getCurrentTimestamp() +" maybe check into that?");
		}
		//I never use system.exit, but it seems appropriate here
		//since without this command, you'd have to go close it in the task manager
		//and that's a lot of work
	}

}
