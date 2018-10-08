package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;

public class HowManyChanger implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		String newAmount = arguments.get(0);
		try
		{
			int newAmountInt = Integer.parseInt(newAmount);
			if(newAmount == null)
			{
				UtilsAndConstants.sendMessage(event.getChannel(), "Try again, with something for me to work with!");
			}
			else if(event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR) || event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.MANAGE_SERVER))
			{
				//if they're a admin or server manager, they probably are trusted enough to change the bot
				if(newAmountInt>10 || newAmountInt<=0)
				{
					throw new NumberFormatException("Too big/too small");
				}
				UtilsAndConstants.MAX_ITEMS_TO_FETCH = newAmountInt;
				UtilsAndConstants.sendMessage(event.getChannel(), "Amount changed to " + newAmountInt);
			}
			else
			{
				UtilsAndConstants.sendMessage(event.getChannel(), "Oh dear, doesn't look like you've got permission to do that. Good try!");
				System.out.println("User " + event.getAuthor().getName() + " tried to edit how much to search for at "+ UtilsAndConstants.getCurrentTimestamp() +", you may want to look into that.");
			}
		}
		catch(NumberFormatException ex)
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "That doesn't look like something that'd work. Try again?");
			System.err.println("Number Format Error in changing the amount to search for, with reason: " + ex.getMessage() + " at " + UtilsAndConstants.getCurrentTimestamp());
		}
	}

}
