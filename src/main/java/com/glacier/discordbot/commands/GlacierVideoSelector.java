package com.glacier.discordbot.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

public class GlacierVideoSelector implements Command {

	
	private static YouTube Youtube;
	
	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {

		//in a similar way to the base of the bot, I'm borrowing from the sample code provided by youtube
		
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
        	search.setFields("items(id/videoId,snippet/title)");
        	search.setChannelId(UtilsAndConstants.properties.getProperty("youtube.channelid"));
        	search.setQ(String.join(" ", arguments));
        	search.setMaxResults((long) UtilsAndConstants.MAX_ITEMS_TO_FETCH);
        	//then set it's properties appropriately
        	//give it the api key we need, the id of the channel you want to search through
        	//the type of thing we're searching for (video)
        	//what we need back in response (the videoId)
        	//and finally, the query string
        	SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if(searchResultList.isEmpty())
            {
            	UtilsAndConstants.sendMessage(event.getChannel(), "Oh dear, I didn't find anything. Try a different search, perhaps?");
            	return;
            }
            Map<String,String> details = new HashMap<String,String>();
            for(int i = 0; i<UtilsAndConstants.MAX_ITEMS_TO_FETCH;i++)
            {
            	if(searchResultList.size()>i)
            	{
		            SearchResult singleVideo = searchResultList.get(i);
		            ResourceId rId = singleVideo.getId();
		            String videoID = rId.getVideoId();
		            String title = singleVideo.getSnippet().getTitle();
		            //then we execute the search, and pull out the first 5 video ids and titles
		            //as long as there are 5 to get
		            //if there's less just get all of them
		            String youtubeURL = UtilsAndConstants.BEGINNING_PIECE_OF_URL + videoID;
		            details.put(title, youtubeURL);
            	}
            }
            EmbedBuilder messageBuilder = new EmbedBuilder();
            messageBuilder.withAuthorName("GlacierBot");
            messageBuilder.withTitle("Choose a Video");
            messageBuilder.appendDescription("Click the reaction number which matches the video you want to watch, or click ❌ to delete the message.");
            int counter = 1;
            for (Entry<String, String> entry : details.entrySet()) {
        		messageBuilder.appendField(counter + ". " + entry.getKey(),"[video link]("+entry.getValue() + ")",false);
        		counter++;
        	}
            RequestBuffer.request(() -> event.getChannel().sendMessage(messageBuilder.build()));
            //normally I'd call the util method to send the message
            //but the messageBuilder doesn't return a string so I have to do it manually here
            
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
