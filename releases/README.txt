To run this bot, you'll need the following things:

1. A file called discordbot.properties, containing the following lines of text.

youtube.apikey=<Youtube API Key>
youtube.channelid=<Channel ID for the channel to search>
discord.key=<discord token for the bot to use>

2. The jar file in this folder

To start the bot, just click the jar file, and you should be up and running! If you're having trouble, check your main drive for a folder called Glacier Nester, there should be a log in there that explains things.

~say <args>
	repeats whatever you put after the command
~join
	has the bot join the voice channel you're in
~leave
	has the bot leave the voice channel it's in
~play <args>
	has the bot try to play the link you hand it (Supported types are Youtube, SoundCloud, Bandcamp, Vimeo, Twitch streams, and some HTTP URLs)
~glacier <args>
	searches the channel id you gave it in the properties file, for the arguments you give the command.
~skip <args>
	skips the number of tracks you ask for, or just one if you don't add any arguments
~naptime
	Closes the process that's running the bot, "putting him to sleep", so to speak
