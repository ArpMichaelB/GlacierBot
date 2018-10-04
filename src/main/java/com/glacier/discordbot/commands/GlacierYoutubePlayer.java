package com.glacier.discordbot.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class GlacierYoutubePlayer implements Command {

	
	private static YouTube Youtube;
	
	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {

		//in a similar way to the base of the bot, I'm borrowing from the sample code provided by youtube
		
		//TODO: insert an embedded message that allows one to pick which search result they want to hear
		
		System.out.println("Started Channel Specific Search at " + UtilsAndConstants.getCurrentTimestamp());
		
		
        try {
        	Youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("GlacierBot").build();
        	//first, set up the Youtube client with
        	//a new jacksonfactory so it can handle json, 
        	//a new nethttptransport so (I assume) it can handle http transport of data (I'm not entirely sure what nethttptransport does)
        	//and a new initializer for the request to set things up each time a request is sent
        	//however there isn't something that needs to be set each time the request is sent so it does nothing
        	YouTube.Search.List search =Youtube.search().list("id,snippet");
        	//we make a search:list object
        	search.setKey(UtilsAndConstants.properties.getProperty("youtube.apikey"));
        	search.setType("video");
        	search.setFields("items(id/videoId)");
        	search.setChannelId(UtilsAndConstants.properties.getProperty("youtube.channelid"));
        	search.setQ(String.join(" ", arguments));
        	//then set it's properties appropriately
        	//give it the api key we need, the id of the channel you want to search through
        	//the type of thing we're searching for (video)
        	//what we need back in response (the videoId)
        	//and finally, the query string
        	SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            /* so I'm thinking 
             * we'll make an embedded message that contains, visible to the user, 
             * the titles of the video (we'll have to change the search fields a bit)
             * these titles will be links to the videos themselves,
             * and the links in these titles will be what the reaction add Event uses to call the play command
             * if not in code then by sending the message
             * */
            SearchResult singleVideo = searchResultList.get(0);
            ResourceId rId = singleVideo.getId();
            String videoID = rId.getVideoId();
            //then we execute the search, and pull out the first video id that occurs
            String youtubeURL = UtilsAndConstants.BEGINNING_PIECE_OF_URL + videoID;
            //given this youtube url, we can just call the ordinary youtube player now
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(youtubeURL);
            new OrdinaryPlayer().runCommand(event, temp);
        }
        catch (GoogleJsonResponseException e) {
            System.err.println("There was an error in the Youtube Service: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage() + " at " + UtilsAndConstants.getCurrentTimestamp());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage() + " at " + UtilsAndConstants.getCurrentTimestamp());
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}

}
