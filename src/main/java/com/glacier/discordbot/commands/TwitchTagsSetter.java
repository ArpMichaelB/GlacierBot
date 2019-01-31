package com.glacier.discordbot.commands;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TwitchTagsSetter implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		//this one's a sneakyboi command that can't get accessed by the user
		//but it does get called by the bot, on pressing of checkmark
		String twitchChannelID = (String) UtilsAndConstants.properties.getOrDefault("twitchChannelID", "adefaultvalue");
		String twitchClientID = "yye6c1ahafhtcafi5aagoij7uccfec";
		String twitchKey = (String) UtilsAndConstants.properties.getOrDefault("twitchKey", "adefaultvalue");
		if(twitchChannelID.equals("adefaultvalue") || twitchKey.equals("adefaultvalue"))
		{
			UtilsAndConstants.sendMessage(event.getChannel(), "This bot doesn't have a twitch to set things for!");
			return;
		}
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut put = new HttpPut("https://api.twitch.tv/helix/streams/tags?broadcaster_id="+twitchChannelID);
		put.addHeader("Client-ID", twitchClientID);
		//question of the day: will I have to change title and game setter when v5 is completely removed?
		//the answer is probably yes
		//i mean in theory it should be small changes to the url structure and the header design
		//and the interaction stuff shouldn't have to change :v
		put.addHeader("Authorization","Bearer " + twitchKey);
		put.addHeader("Content-Type","application/json");
		String json = "{\"tag_ids\":[";
		for(String s : arguments)
		{
			System.out.println(s);
			json += "\""+s+"\",";
		}
		json=json.substring(0,json.length()-1)+"]}";
		
		try {
			StringEntity params = new StringEntity(json);
			put.setEntity(params);
			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
	            if (status >= 200 && status < 300) {
	                HttpEntity entity = response.getEntity();
	                return entity != null ? EntityUtils.toString(entity) : null;
	              //TODO: remove/document ternary operator
	            } else {
	            	UtilsAndConstants.sendMessage(event.getChannel(), "Having a little trouble, have the botowner check the logs!");
	                throw new ClientProtocolException("Unexpected response status: " + status);
	            }
	        };
	        System.out.println("here");
			client.execute(put,responseHandler);
			System.out.println("there");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
