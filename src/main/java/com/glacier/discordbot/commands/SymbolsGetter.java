package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class SymbolsGetter implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments)
	{
		String toSend = "";
		if(arguments.contains("^tm"))
		{
			toSend+="™";
		}
		if(arguments.contains("ae"))
		{
			toSend+="Æ";
		}
		if(arguments.contains("eye"))
		{
			toSend+="ꙮ";
		}
		if(arguments.contains("lenny"))
		{
			toSend+="( ͡° ͜ʖ ͡°)";
		}
		if(arguments.contains("shrug"))
		{
			toSend+="¯\\_(ツ)_/¯";
		}
		if(arguments.contains("ooouuu"))
		{
			toSend+="ü";
		}
		UtilsAndConstants.sendMessage(event.getChannel(), toSend);
	}

}
