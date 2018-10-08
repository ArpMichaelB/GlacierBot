# GlacierBot
I run a community discord server for my youtube channel Glacier Nester, (hence the bot's name) and found myself curious how difficult it would be to write a bot that acts as a music DJ.
After a bit of digging, I found that there is a java discord API, called D4J. Additionally, this api provides support for an audio player library that can play directly from youtube.
However, ordinary music bots are a dime-a-dozen, and I wanted a unique bot that would potentially be useful to folks outside my own discord.
A coworker of mine noted that a great deal of my youtube content is easily consumed as pure audio, and the idea hit me.
I could, rather than writing a simple bot that requires the user to go get a youtube URL to play the sound, simply have something that searches my own youtube backlog for the given title.
The youtube API allows one to limit search results to a specific channel, even if the front-end searchbar, far as I can tell, doesn't allow that anymore.
So, this bot is my attempts at creating that automated response system, not to mention an exercise in learning to use new API. 
I'll include the link to invite the bot in this description, once I have the bot set up to a point that I'd consider complete.
For now, if you'd like to include this bot in your server, there's a jar file in the releases folder that you as a user can run, provided you have the required properties. 

Firstly, you'll need a bot account to run this with. There's plenty of tutorials on that sort of thing, but essentially, you'll need a token to give the bot, so it has a way to connect.

Secondly, you'll need a google API key, so the bot has something to search the channel with. Again, there's tutorials aplenty on how to manage that sort of thing, but I assure you, it's entirely free and painless. 

Finally, you'll need the channel ID of the channel you wish to search. 
  The easiest way I've found to get that channel id is via this project: http://johnnythetank.github.io/youtube-channel-name-converter/

There's a readme telling you how to use these three items in the releases folder, and I highly reccomend giving it a look, as it also details the available commands.
