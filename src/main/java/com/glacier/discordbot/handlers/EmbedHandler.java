package com.glacier.discordbot.handlers;

import com.glacier.discordbot.util.UtilsAndConstants;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageUpdateEvent;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.handle.obj.IEmbed.IEmbedField;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageHistory;

public class EmbedHandler {
	@EventSubscriber
    public void onMessageEmbedded(MessageUpdateEvent event)
    {
        if(event.getMessage().getAuthor() == event.getChannel().getClient().getOurUser())
    	{
        	System.out.println("Self-Message-Edit detected at " + UtilsAndConstants.getCurrentTimestamp());
    		MessageHistory history = event.getChannel().getMessageHistory();
    		IMessage message = history.getLatestMessage();
    		if(message.getEmbeds().isEmpty())
    		{
    			System.out.println("Updated Message was not an Embed, cancelling operation at " + UtilsAndConstants.getCurrentTimestamp());
    			return;
    			//if the message that was edited isn't an embedded message, do nothing
    		}
    		IEmbed embeddedMessage = message.getEmbeds().get(0);
    		if(embeddedMessage.getTitle().equals("Choose a Video") || embeddedMessage.getTitle().equals("Tag Options"))
    		{
    			//if the embedded message's first field contains the right title
    			//run through the whole thing and react with the appropriate reactions
    			int counter = 1;
	    		for(IEmbedField data : embeddedMessage.getEmbedFields())
	    		{
	    			data.getName();
					Emoji reaction = EmojiManager.getForAlias(UtilsAndConstants.translateToEmoji(counter));
	    			UtilsAndConstants.reactToMessage(message, reaction);
	    			//essentially requestbuffer makes the bot wait until it is able to do the thing, then does it
	    			//Like sending a message, I might make this a method in utils and constants 
	    			counter++;
				}
	    		UtilsAndConstants.reactToMessage(message, EmojiManager.getForAlias("x"));
    		}
    		if(embeddedMessage.getTitle().equals("Tag Options"))
    		{
    			UtilsAndConstants.reactToMessage(message, EmojiManager.getForAlias("white_check_mark"));
    			if(!embeddedMessage.getFooter().getText().isEmpty())
    			{
    				UtilsAndConstants.reactToMessage(message, EmojiManager.getForAlias("arrow_right"));
    			}
    		}
    	}
    }
}

