package com.glacier.discordbot.commands;

import java.io.IOException;
import java.net.MalformedURLException;
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
import sx.blah.discord.handle.obj.Permissions;

public class TwitchGameSetter implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		if(!event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR) || !event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.MANAGE_SERVER))
		{
			return;
		}
		try 
		{
			String twitchChannelID = (String) UtilsAndConstants.properties.getOrDefault("twitchChannelID", "adefaultvalue");
			String twitchClientID = "yye6c1ahafhtcafi5aagoij7uccfec";
			String twitchKey = (String) UtilsAndConstants.properties.getOrDefault("twitchKey", "adefaultvalue");
			if(twitchChannelID.equals("adefaultvalue") || twitchClientID.equals("adefaultvalue") || twitchKey.equals("adefaultvalue"))
			{
				UtilsAndConstants.sendMessage(event.getChannel(), "This bot doesn't have a twitch to set things for!");
				return;
			}
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPut put = new HttpPut("https://api.twitch.tv/kraken/channels/" + twitchChannelID);
			put.addHeader("Accept", "application/vnd.twitchtv.v5+json");
			put.addHeader("Client-ID", twitchClientID);
			put.addHeader("Authorization","OAuth " + twitchKey);
			put.addHeader("Content-Type","application/json");
			String newGame = "";
			for(int i = 0; i<arguments.size(); i++)
			{
				newGame+=arguments.get(i)+" ";
			}
			String json = "{\"channel\":{\"game\":\""+ newGame +"\"}}";
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
			client.execute(put,responseHandler);
			UtilsAndConstants.sendMessage(event.getChannel(), "Game set to " + newGame);
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
