package com.glacier.discordbot.audiohandlers;

import com.glacier.discordbot.lavaplayer.GuildMusicManager;
import com.glacier.discordbot.util.UtilsAndConstants;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import sx.blah.discord.handle.obj.IChannel;

public class OrdinaryResultHandler implements AudioLoadResultHandler {
	
	private IChannel textChannel;
	private GuildMusicManager musicManager;
	private String searchStr;
	
	public OrdinaryResultHandler(IChannel textChannel, GuildMusicManager musicManager, String searchStr)
	{
		this.textChannel = textChannel;
		this.musicManager = musicManager;
		this.searchStr = searchStr;
	}
	
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

}
