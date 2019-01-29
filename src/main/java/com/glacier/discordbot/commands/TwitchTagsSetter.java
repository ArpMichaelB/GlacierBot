package com.glacier.discordbot.commands;

import java.io.IOException;
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

public class TwitchTagsSetter implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		UtilsAndConstants.sendMessage(event.getChannel(), "Still working on it!");
		//TODO: the below, and check for authorization for the person using the command
		try {
			//so the actual process needs to be as follows:
			//pull tags
			//then store our pagination value
			//then send however many embedded messages are required for the batch of 20 (filtered to remove autoapplied tags) we've gotten
			//then react to those two messages with the number buttons and the delete button (same as glacier command) 
			//the last message also gets reacted to with a right arrow, and a checkmark 
			//right arrow goes and fetches more tags
			//checkmark applies all the stored tags
			//reacting with the number buttons stores the tag in our pile of tags to apply
			//if we have a pagination value passed as an argument we apply that to the request url
			//otherwise we can just use an empty string as pagination
			//below is the code that pulls the filtered sets of tags
			//here still needs the code that builds/sends the embedded messages
			//and the ReactionHandler needs to react with the buttons to those messages
			//as well as handle the pressing of those buttons
			//but I'm out of time today to do that
			//so that code will happen tomorrow
			//and providing a lack of wrinkles so will the update to project file creator
			//I still need to check if I can use the same client id for all bots as well
        	CloseableHttpClient client = HttpClients.createDefault();
        	String pagination = "";
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
        	String response = client.execute(get,responseHandler);
        	JSONParser parse = new JSONParser();
    		JSONObject obj = (JSONObject) parse.parse(response);
    		JSONArray arr = (JSONArray) obj.get("data");
    		for(Object objtag : arr)
    		{
    			JSONObject tag = (JSONObject) objtag;
    			if(!((boolean)tag.get("is_auto")))
    			{
    				System.out.println(tag.get("tag_id"));
    				JSONObject names = (JSONObject) tag.get("localization_names");
    				System.out.println(names.get("en-us"));
    			}
    		}
    		JSONObject cursor = (JSONObject) obj.get("pagination");
    		pagination = (String) cursor.get("cursor");
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
