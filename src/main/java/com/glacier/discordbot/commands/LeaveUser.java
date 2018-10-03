package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.lavaplayer.TrackScheduler;
import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class LeaveUser implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		IVoiceChannel voiceChannel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();

        if(voiceChannel == null)
        {
        	UtilsAndConstants.sendMessage(event.getChannel(), "Come now, I can't exactly leave if I haven't showed up!");
            return;
        }

        TrackScheduler scheduler = UtilsAndConstants.getGuildAudioPlayer(event.getGuild()).getScheduler();
        scheduler.getQueue().clear();
        scheduler.nextTrack();

        voiceChannel.leave();
	}

}
