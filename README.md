# GlacierBot
## How-To-Use
### What you'll need
1. Discord bot account to run this with. There's plenty of tutorials on that sort of thing, but essentially, you'll need a token to give the bot, so it has a way to connect.

2. Google API key, so the bot has something to search the channel with. Again, there's tutorials aplenty on how to manage that sort of thing, but I assure you, it's entirely free and painless. 

3. The channel ID of the channel you wish to search. 
  The easiest way I've found to get that channel id is via this project: http://johnnythetank.github.io/youtube-channel-name-converter/

### Properties File


Now I hear you. 
>What am I going to do now that I have these things?

Well, that's exceedingly simple! You just make a text document, that contains the following lines:

youtube.apikey=`Youtube API Key`

youtube.channelid=`Channel ID for the channel to search`

discord.key=`discord token for the bot to use`
  
### Commands

Currently, the list of available commands are as follows: 

* ~say <args> 
    * repeats whatever you put after the command
* ~join 
    * has the bot join the voice channel you're in
* ~leave
    * has the bot leave the voice channel it's in
* ~play <args> 
    * has the bot try to play the link you hand it (Supported types are Youtube, SoundCloud, Bandcamp, Vimeo, Twitch streams, and some HTTP URLs)
* ~glacier <args> 
    * searches the channel id you gave it in the properties file, for the arguments you give the command.
* ~skip <args> 
    * skips the number of tracks you ask for, or just one if you don't add any arguments
* ~naptime 
    * Closes the process that's running the bot, "putting him to sleep", so to speak

## Ok, but why?
Well, I run a community discord server for my youtube channel Glacier Nester, (hence the bot's name) and found myself curious how difficult it would be to write a bot that acts as a music DJ.

After a bit of digging, I found that there is a java discord API, called D4J. Additionally, this api provides support for an audio player library that can play directly from youtube.

However, ordinary music bots are a dime-a-dozen, and I wanted a unique bot that would potentially be useful to folks outside my own discord.

A coworker of mine noted that a great deal of my youtube content is easily consumed as pure audio, and given that point, the idea hit me.

I could, rather than writing a simple bot that requires the user to go get a youtube URL to play the sound, simply have something that searches my own youtube backlog for the given title.

The youtube API allows one to limit search results to a specific channel, even if the normal searchbar doesn't allow that anymore.

So, this bot is my attempts at creating that automated response system, not to mention an exercise in learning to use new API. 

I'll include the link to invite the bot in this description, once I have the bot set up to a point that I'd consider complete.

For now, if you'd like to include this bot in your server, there's a jar file in the releases folder that you as a user can run, provided you have the required properties. 
