package com.glacier.discordbot.commands;

import java.util.List;
import java.util.Random;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class XyzzyResponder implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		if(!arguments.isEmpty())
		{
			if(arguments.get(0).equals("keys"))
				UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice sings:\r\n" + 
						"\"I xyz zy spiders on the wall... I xyz zy cobwebs in the hall...\r\n" + 
						"I xyz zy candles on the shelf... \"");
			if(arguments.get(0).equals("twice"))
			{
				UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice mutters \"Fool.\"");
				UtilsAndConstants.sendMessage(event.getChannel(),
				"A disembodied voice speaks. \"This magic word is down for repairs, necessitated by its overuse. Please try another.\"");
			}
			if(arguments.get(0).equals("maze"))
				UtilsAndConstants.sendMessage(event.getChannel(), "You have a sudden vision of a maze of twisty passages, all alike.");
			if(arguments.get(0).equalsIgnoreCase("Toon"))
				UtilsAndConstants.sendMessage(event.getChannel(), "You have a sudden vision of a small white house with a mailbox in front.");
			if(arguments.get(0).equalsIgnoreCase("german"))
				UtilsAndConstants.sendMessage(event.getChannel(), "Eine hohle Stimme sagt: 'Du bist offenbar im falschen Spiel.");
			if(arguments.get(0).equalsIgnoreCase("cheese"))
				UtilsAndConstants.sendMessage(event.getChannel(), "Xyzzy? Tinny, tinny sort of word.");
			if(arguments.get(0).equalsIgnoreCase("breakfast"))
				UtilsAndConstants.sendMessage(event.getChannel(), "A hollow voice sighs, exasperated.");
			if(arguments.get(0).equalsIgnoreCase("six"))
				UtilsAndConstants.sendMessage(event.getChannel(), "A huge cloud of orange smoke fails to appear.");
			if(arguments.get(0).equalsIgnoreCase("stories"))
				UtilsAndConstants.sendMessage(event.getChannel(), "You listen intently for a hollow response, but hear nothing.");
			if(arguments.get(0).equalsIgnoreCase("exhibit"))
				UtilsAndConstants.sendMessage(event.getChannel(), "A woman in a smoky orange gown glides close by and whispers, \"That sort of thing doesn't work here.\"");
			if(arguments.get(0).equalsIgnoreCase("fine-tuned"))
				UtilsAndConstants.sendMessage(event.getChannel(), "All 69,105 leaves have said \"yoho\" and disappeared down a 2-inch slit -- along with some cheese, a bowl of sodden Cheerios, losers named Mercury and BVE sniveling under a bridge.");
			if(arguments.get(0).equalsIgnoreCase("guest"))
				UtilsAndConstants.sendMessage(event.getChannel(), "No, no. I don't want to be magically transported anywhere before I secure my property.");
			if(arguments.get(0).equalsIgnoreCase("glossary"))
				UtilsAndConstants.sendMessage(event.getChannel(), "All the other deities have USEFUL magic words, but you're stuck with this stupid one that DOESN'T EVEN DO ANYTHING!");
			if(arguments.get(0).equalsIgnoreCase("toaster"))
				UtilsAndConstants.sendMessage(event.getChannel(), "Ancient words of power may cut it in some other profession, but not in the exciting world of toaster repair!");
			if(arguments.get(0).equalsIgnoreCase("banana"))
				UtilsAndConstants.sendMessage(event.getChannel(), "Your easter egg could go here! Call now at 555-1212 to order...");
			if(arguments.get(0).equalsIgnoreCase("curses"))
				UtilsAndConstants.sendMessage(event.getChannel(), "For a moment you can almost hear a hoarse voice say something to you. But it passes.");
			if(arguments.get(0).equalsIgnoreCase("artifact"))
				UtilsAndConstants.sendMessage(event.getChannel(), "You speak the arcane word of power and - nothing happens. Maybe your voice isn't hollow enough. In any case, you'll have to find another way to get out of here.");
			if(arguments.get(0).equalsIgnoreCase("delusions"))
				UtilsAndConstants.sendMessage(event.getChannel(), "It has been said that anything can happen in VR, but in this case \"anything\" turns out to be nothing at all.");
			if(arguments.get(0).equalsIgnoreCase("darkness"))
				UtilsAndConstants.sendMessage(event.getChannel(), "It is pitch dark in here, you are likely to be eaten by a grue.");
			if(arguments.get(0).equalsIgnoreCase("york"))
				UtilsAndConstants.sendMessage(event.getChannel(), "Sadly, recent city budget cuts have led to the elimination of the Department of Magic.");
			if(arguments.get(0).equalsIgnoreCase("haggis"))
				UtilsAndConstants.sendMessage(event.getChannel(), "I used the magic word XYZZY and all I got was this stupid t-shirt.");
			if(arguments.get(0).equalsIgnoreCase("jabberwocky"))
				UtilsAndConstants.sendMessage(event.getChannel(), "Don't bother trying \"plugh\".");
			if(arguments.get(0).equalsIgnoreCase("harlequin"))
				UtilsAndConstants.sendMessage(event.getChannel(), "That's an amateur spell. The only good spells are said in Latin. The best spells are said in Latin backwards.");
			if(arguments.get(0).equalsIgnoreCase("world"))
				UtilsAndConstants.sendMessage(event.getChannel(), "You now know it is only a computer simulation, but somehow it feels strangely reassuring.");
			if(arguments.get(0).equalsIgnoreCase("minesweeper"))
				UtilsAndConstants.sendMessage(event.getChannel(), "This ain't minesweeper, bro.");
			if(arguments.get(0).equalsIgnoreCase("zork"))
				UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice mutters \"Fool.\"");
		}
		else
		{
			int rand = new Random().nextInt(26);
			rand++;
			switch(rand)
			{
			case 1:
				UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice sings:\r\n" + 
						"\"I xyz zy spiders on the wall... I xyz zy cobwebs in the hall...\r\n" + 
						"I xyz zy candles on the shelf... \"");
				break;
			case 2:
				UtilsAndConstants.sendMessage(event.getChannel(), "This ain't minesweeper, bro.");				
				break;
			case 3:
				UtilsAndConstants.sendMessage(event.getChannel(), "You now know it is only a computer simulation, but somehow it feels strangely reassuring.");				
				break;				
			case 4:
				UtilsAndConstants.sendMessage(event.getChannel(), "That's an amateur spell. The only good spells are said in Latin. The best spells are said in Latin backwards.");
				break;
			case 5:
				UtilsAndConstants.sendMessage(event.getChannel(), "Don't bother trying \"plugh\".");
				break;
			case 6:
				UtilsAndConstants.sendMessage(event.getChannel(), "I used the magic word XYZZY and all I got was this stupid t-shirt.");				
				break;
			case 7:
				UtilsAndConstants.sendMessage(event.getChannel(), "Sadly, recent city budget cuts have led to the elimination of the Department of Magic.");				
				break;
			case 8:
				UtilsAndConstants.sendMessage(event.getChannel(), "It is pitch dark in here, you are likely to be eaten by a grue.");				
				break;
			case 9:
				UtilsAndConstants.sendMessage(event.getChannel(), "It has been said that anything can happen in VR, but in this case \"anything\" turns out to be nothing at all.");				
				break;
			case 10:
				UtilsAndConstants.sendMessage(event.getChannel(), "You speak the arcane word of power and - nothing happens. Maybe your voice isn't hollow enough. In any case, you'll have to find another way to get out of here.");				
				break;
			case 11:
				UtilsAndConstants.sendMessage(event.getChannel(), "For a moment you can almost hear a hoarse voice say something to you. But it passes.");				
				break;
			case 12:
				UtilsAndConstants.sendMessage(event.getChannel(), "Your easter egg could go here! Call now at 555-1212 to order...");				
				break;
			case 13:
				UtilsAndConstants.sendMessage(event.getChannel(), "Ancient words of power may cut it in some other profession, but not in the exciting world of toaster repair!");				
				break;
			case 14:
				UtilsAndConstants.sendMessage(event.getChannel(), "All the other deities have USEFUL magic words, but you're stuck with this stupid one that DOESN'T EVEN DO ANYTHING!");				
				break;
			case 15:
				UtilsAndConstants.sendMessage(event.getChannel(), "No, no. I don't want to be magically transported anywhere before I secure my property.");				
				break;
			case 16:
				UtilsAndConstants.sendMessage(event.getChannel(), "All 69,105 leaves have said \"yoho\" and disappeared down a 2-inch slit -- along with some cheese, a bowl of sodden Cheerios, losers named Mercury and BVE sniveling under a bridge.");				
				break;
			case 17:
				UtilsAndConstants.sendMessage(event.getChannel(), "A woman in a smoky orange gown glides close by and whispers, \"That sort of thing doesn't work here.\"");				
				break;
			case 18:
				UtilsAndConstants.sendMessage(event.getChannel(), "You listen intently for a hollow response, but hear nothing.");				
				break;
			case 19:
				UtilsAndConstants.sendMessage(event.getChannel(), "A huge cloud of orange smoke fails to appear.");				
				break;
			case 20:
				UtilsAndConstants.sendMessage(event.getChannel(), "A hollow voice sighs, exasperated.");				
				break;
			case 21:
				UtilsAndConstants.sendMessage(event.getChannel(), "Xyzzy? Tinny, tinny sort of word.");				
				break;
			case 22:
				UtilsAndConstants.sendMessage(event.getChannel(), "Eine hohle Stimme sagt: 'Du bist offenbar im falschen Spiel.");
				break;
			case 23:
				UtilsAndConstants.sendMessage(event.getChannel(), "You have a sudden vision of a small white house with a mailbox in front.");				
				break;
			case 24:
				UtilsAndConstants.sendMessage(event.getChannel(), "You have a sudden vision of a maze of twisty passages, all alike.");
				break;
			case 25:
				UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice mutters \"Fool.\"");
				UtilsAndConstants.sendMessage(event.getChannel(), ">xyzzy");
				UtilsAndConstants.sendMessage(event.getChannel(),
				"A disembodied voice speaks. \"This magic word is down for repairs, necessitated by its overuse. Please try another.\"");
				break;
			default:
				UtilsAndConstants.sendMessage(event.getChannel(),"A hollow voice mutters \"Fool.\"");
				break;
			}
		}
	}

}
