package com.glacier.discordbot.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.glacier.discordbot.util.UtilsAndConstants;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IVoiceChannel;

public class OrdinaryYoutubePlayer implements Command {

	private static final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    private static final Map<Long, GuildMusicManager> musicManagers  = new HashMap<>();
	
	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		IVoiceChannel channel = event.getClient().getOurUser().getVoiceStateForGuild(event.getGuild()).getChannel();
		if(channel == null)
		{
			IVoiceChannel userVoiceChannel = event.getAuthor().getVoiceStateForGuild(event.getGuild()).getChannel();

	        if(userVoiceChannel == null)
	        {
	        	UtilsAndConstants.sendMessage(event.getChannel(), "Join a voice channel, and try again.");
	        	return;
	        }

	        userVoiceChannel.join();
		}
        //if the bot isn't in a voice channel, join the one the user is in
        
		//Turn the args back into a string separated by space
        String searchStr = String.join(" ", arguments);
        
        GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild());

        playerManager.loadItemOrdered(musicManager, searchStr, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                UtilsAndConstants.sendMessage(channel, "Adding to queue " + track.getInfo().title);

                musicManager.getScheduler().queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                UtilsAndConstants.sendMessage(channel, "Adding to queue " + firstTrack.getInfo().title + " (first track of playlist " + playlist.getName() + ")");

                musicManager.getScheduler().queue(firstTrack);
            }

            @Override
            public void noMatches() {
                UtilsAndConstants.sendMessage(channel, "Nothing found from " + searchStr);
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                UtilsAndConstants.sendMessage(channel, "Could not play: " + exception.getMessage());
            }
        });
	}

	private static synchronized GuildMusicManager getGuildAudioPlayer(IGuild guild) {
        long guildId = guild.getLongID();
        GuildMusicManager musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setAudioProvider(musicManager.getAudioProvider());

        return musicManager;
    }
}
