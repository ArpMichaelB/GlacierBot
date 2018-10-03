package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class OrdinaryYoutubePlayer implements Command {

	public static final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    @Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		AudioSourceManagers.registerRemoteSources(playerManager);
		IVoiceChannel voiceChannel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();
		IChannel textChannel = event.getChannel();
		if(voiceChannel == null)
		{
			IVoiceChannel userVoiceChannel = event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();

	        if(userVoiceChannel == null)
	        {
	        	UtilsAndConstants.sendMessage(textChannel, "Join a voice channel, and try again.");
	        	return;
	        }

	        userVoiceChannel.join();
		}
        //if the bot isn't in a voice channel, join the one the user is in
        
		//Turn the args back into a string separated by space
        String searchStr = String.join(" ", arguments);
        
        GuildMusicManager musicManager = UtilsAndConstants.getGuildAudioPlayer(voiceChannel.getGuild());
        //get the queue manager for the channel the bot's in

        playerManager.loadItemOrdered(musicManager, searchStr,
    		new AudioLoadResultHandler() {
	            @Override
	            public void trackLoaded(AudioTrack track) {
	                UtilsAndConstants.sendMessage(textChannel, "Adding to queue " + track.getInfo().title);
	                musicManager.getScheduler().queue(track);
	                //on the track loading, send a message that the track's been added to the queue
	                //and then add it to the queue
	            }
	
	            @Override
	            public void playlistLoaded(AudioPlaylist playlist) {
	                AudioTrack firstTrack = playlist.getSelectedTrack();
	
	                if (firstTrack == null) {
	                    firstTrack = playlist.getTracks().get(0);
	                }
	
	                UtilsAndConstants.sendMessage(textChannel, "Adding to queue " + firstTrack.getInfo().title + " (first/chosen track of playlist " + playlist.getName() + ")");
	
	                musicManager.getScheduler().queue(firstTrack);
	                //on loading of a playlist, add the selected track of it to the queue
	                //if no track's selected, add the first track
	            }
	
	            @Override
	            public void noMatches() {
	                UtilsAndConstants.sendMessage(textChannel, "Nothing found from " + searchStr);
	                //if nothing matched the string they asked for, point that out
	            }

	            @Override
	            public void loadFailed(FriendlyException exception) {
	                UtilsAndConstants.sendMessage(textChannel, "Could not play: " + exception.getMessage());
	            }
        	});
        //TODO: get rid of this inline declaration, perhaps make a class that extends AudioLoadResultHandler
        //a la how we have individual command classes implementing the command class
	}
}
