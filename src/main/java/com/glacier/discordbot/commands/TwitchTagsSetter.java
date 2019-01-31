package com.glacier.discordbot.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

public class TwitchTagsSetter implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		UtilsAndConstants.sendMessage(event.getChannel(), "Still working on it!");
		if(event.getAuthor().getClient().getOurUser().equals(event.getAuthor()))
		//usually I just say event.getclient.getouruser but I think 
		//because I'm calling this by declaring a new instance of this
		//rather than using the instance that it's called with
		//we have to get the client from the author
		//to have the right user
		{
			System.out.println("Called from next arrow");
		}
		else if(!event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.ADMINISTRATOR) || !event.getAuthor().getPermissionsForGuild(event.getGuild()).contains(Permissions.MANAGE_SERVER))
		{
			return;
		}
		try {
			//reacting with the number buttons stores the tag in our pile of tags to apply
			CloseableHttpClient client = HttpClients.createDefault();
        	String pagination = "";
        	if(!arguments.isEmpty())
        	{
        		pagination = arguments.get(0);
        	}
        	HttpGet get = new HttpGet("https://api.twitch.tv/helix/tags/streams?after="+pagination);
    		get.addHeader("Client-ID", "yye6c1ahafhtcafi5aagoij7uccfec");
    		ResponseHandler<String> responseHandler = response -> {
    			int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                    //i hate ternary operators
                    //I'll fix it if I ever get this to work
                } else {
                	throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            ArrayList<JSONObject> tags = new ArrayList<JSONObject>();
        	String response = client.execute(get,responseHandler);
        	JSONParser parse = new JSONParser();
    		JSONObject obj = (JSONObject) parse.parse(response);
    		JSONArray arr = (JSONArray) obj.get("data");
    		for(Object objtag : arr)
    		{
    			JSONObject tag = (JSONObject) objtag;
    			if(!((boolean)tag.get("is_auto")))
    			{
    				JSONObject tagToSend = new JSONObject();
    				JSONObject names = (JSONObject) tag.get("localization_names");
    				tagToSend.put("tag_id", tag.get("tag_id"));
    				tagToSend.put("name", names.get("en-us"));
    				//maybe have a setting command that picks the display language for a tag? idk
    				tags.add(tagToSend);
    			}
    		}
    		JSONObject cursor = (JSONObject) obj.get("pagination");
    		pagination = (String) cursor.get("cursor");
    		ArrayList<EmbedBuilder> messages = new ArrayList<EmbedBuilder>();	
			int marker = 0;
			while(marker < tags.size()-1)
			{
				if(marker%10 == 0)
				{
					messages.add(new EmbedBuilder().withTitle("Tag Options").withDesc("Choose a tag to add.").withAuthorName("GlacierBot"));
					EmbedBuilder message = messages.get(messages.size()-1);
					message.appendField(((marker%10)+1) + "." + (String)tags.get(marker).get("name"),(String) tags.get(marker).get("tag_id"),false);
					messages.set(messages.size()-1, message);
					marker++;
				}
				for(;marker%10<=9 && marker<tags.size() && marker%10!=0;marker++)
				{
					//I love the way this looks because it's SUCH minor arcana compared to how I use for loops normally
					//basically for every 10 tags fill the last message in the list
					//with those ten tags
					EmbedBuilder message = messages.get(messages.size()-1);
					message.appendField(((marker%10)+1) + "." + (String)tags.get(marker).get("name"),(String) tags.get(marker).get("tag_id"),false);
					messages.set(messages.size()-1, message);
				}
			}
			if(marker == 0)
			{
				UtilsAndConstants.sendMessage(event.getChannel(), "No more tags, pick from what you've got!");
				return;
			}
			//fill the list of each message which contains up to 10 available tags
			EmbedBuilder temp = messages.get(messages.size()-1);
			temp.withFooterText(pagination);
			messages.set(messages.size()-1, temp);
			//then add the pagination cursor to the last message
			for(EmbedBuilder message : messages)
			{
				RequestBuffer.request(() -> event.getChannel().sendMessage(message.build()));
				//like the video selector I'd normally call the utils send message method
				//but it takes a string, not an embed object
				//I should be a good noodle and just overload the method but that's effort and I only use it twice	
			}
			//then send each built message
		} 
        catch (ClientProtocolException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        catch (ParseException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
