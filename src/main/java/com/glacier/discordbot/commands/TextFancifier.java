package com.glacier.discordbot.commands;

import java.util.List;

import com.glacier.discordbot.model.Command;
import com.glacier.discordbot.util.UtilsAndConstants;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class TextFancifier implements Command {

	@Override
	public void runCommand(MessageReceivedEvent event, List<String> arguments) {
		String aesthetic = "";
		for(String s : arguments)
		{
			for(char c : s.toCharArray())
			{
				switch(c)
				{
				case 'a':
					aesthetic+="α";
					break;
				case 'b':
					aesthetic+="𝖇";
					break;
				case 'c':
					aesthetic+="¢";
					break;
				case 'd':
					aesthetic+="∂";
					break;
				case 'e':
					aesthetic+="𝒆";
					break;
				case 'f':
					aesthetic+="ƒ";
					break;
				case 'g':
					aesthetic+="g";
					break;
				case 'h':
					aesthetic+="𝖍";
					break;
				case 'i':
					aesthetic+="𝒾";
					break;
				case 'j':
					aesthetic+="נ";
					break;
				case 'l':
					aesthetic+="ℓ";
					break;
				case 'm':
					aesthetic+="𝓶";
					break;
				case 'n':
					aesthetic+="η";
					break;
				case 'o':
					aesthetic+="σ";
					break;
				case 'p':
					aesthetic+="ρ";
					break;
				case 'q':
					aesthetic+="q";
					break;
				case 'r':
					aesthetic+="𝕣";
					break;
				case 's':
					aesthetic+="ˢ";
					break;
				case 't':
					aesthetic+="ᵗ";
					break;
				case 'u':
					aesthetic+="𝔲";
					break;
				case 'v':
					aesthetic+="ν";
					break;
				case 'w':
					aesthetic+="ω";
					break;
				case 'x':
					aesthetic+="ˣ";
					break;
				case 'y':
					aesthetic+="у";
					break;
				case 'z':
					aesthetic+="𝓩";
					break;
				case 'A':
					aesthetic+="𝔸";
					break;
				case 'B':
					aesthetic+="ᗷ";
					break;
				case 'C':
					aesthetic+="Ⓒ";
					break;
				case 'D':
					aesthetic+="ᗪ";
					break;
				case 'E':
					aesthetic+="є";
					break;
				case 'F':
					aesthetic+="𝔽";
					break;
				case 'G':
					aesthetic+="𝔾";
					break;
				case 'H':
					aesthetic+="ᕼ";
					break;
				case 'I':
					aesthetic+="Į";
					break;
				case 'J':
					aesthetic+="𝐉";
					break;
				case 'K':
					aesthetic+="ķ";
					break;
				case 'L':
					aesthetic+="Ⓛ";
					break;
				case 'M':
					aesthetic+="м";
					break;
				case 'N':
					aesthetic+="Ň";
					break;
				case 'O':
					aesthetic+="Ⓞ";
					break;
				case 'P':
					aesthetic+="Ｐ";
					break;
				case 'Q':
					aesthetic+="𝓠";
					break;
				case 'R':
					aesthetic+="尺";
					break;
				case 'S':
					aesthetic+="𝓢";
					break;
				case 'T':
					aesthetic+="Ť";
					break;
				case 'U':
					aesthetic+="𝕌";
					break;
				case 'V':
					aesthetic+="Ѷ";
					break;
				case 'W':
					aesthetic+="𝕎";
					break;
				case 'X':
					aesthetic+="乂";
					break;
				case 'Y':
					aesthetic+="Ƴ";
					break;
				case 'Z':
					aesthetic+="𝓩";
					break;
				default:
					aesthetic+=c;
					break;
				}
			}
		}
		UtilsAndConstants.sendMessage(event.getChannel(), aesthetic);
	}

}
