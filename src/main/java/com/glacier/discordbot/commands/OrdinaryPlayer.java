package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.audiohandlers.OrdinaryResultHandler;
import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class OrdinaryPlayer implements Command {

	public static final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    @Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		AudioSourceManagers.registerRemoteSources(playerManager);
		IVoiceChannel voiceChannel = event.getGuild().getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();
		IChannel textChannel = event.getChannel();
		if(voiceChannel == null)
		{
			UtilsAndConstants.sendMessage(textChannel, "Have me join a voice channel first, you won't be able to hear me if I try right now!");
			return;
		}
        
		//Turn the args back into a string separated by space
        String searchStr = String.join(" ", arguments);
        
        GuildMusicManager musicManager = UtilsAndConstants.getGuildAudioPlayer(voiceChannel.getGuild());
        //get the queue manager for the channel the bot's in

        System.out.println("Queueing item " + searchStr + " because of " + event.getAuthor().getName() + " at " + UtilsAndConstants.getCurrentTimestamp());
        
        playerManager.loadItemOrdered(musicManager, searchStr,
    		new OrdinaryResultHandler(textChannel,musicManager,searchStr));        
	}
}
