package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TwitchTagsSetter implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		UtilsAndConstants.sendMessage(event.getChannel(), "Still working on it!");
		//TODO: pull tags, in a batch of ten. then react to the message with buttons 1 thru 10, and the right arrow for more tags
		//on right arrow, pull the pagination string out of the message and pass it as an argument to this method, using it to find out the next batch of 10 tags to get
		//on 1-10, add that tag's ID to the List of tags we're going to add
		//on delete of message, make the list an empty one
		//finally have a commit button (maybe a checkmark? is there a limit to how many reactions you can add to a message?) to send off all the tag ids in the list to apply to the stream
		//however all this command itself actually does is build the embedded message based on the argument of the message
		//if none, make a request without the after parameter
		//else make the request with the after parameter being the argument
		//i.e. https://api.twitch.tv/helix/tags/streams?first=10?after=arguments.get(0)
	}

}
