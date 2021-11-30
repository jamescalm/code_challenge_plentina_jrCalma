# code_challenge_plentina_jrCalma
App showing list of Dota heroes and basic stats

I chose using https://api.opendota.com/ as my API and I created an app where the user can look at overview of all heroes in Dota 2.
The users can look at the heroes basic stats and also create a list of his/her favorite heroes. I chose to create this app because
I personally play Dota 2 and I am an avid fan of the game.

As for the coding aspect, in the basic setup, I used kotlin version 1.4.32 and gradle version 4.1.0.

I used Retrofit for retrieving JSON data and OkHttp to handle Http connections. I used them both because all of the recent project I worked
on uses both libraries

I used Dagger as dependency injector for the project since most of our projects uses this injector. Though I also have some experience using
Koin as injector.

I think MVVM is a more flexible and modern approach to coding. As such I tried to apply MVVM architectural structure to the app
to the my understanding and knowledge of MVVM. I would want to learn more of MVVM if ever I am employed to your company as a developer,
specially from other devs with experience.

I also used LiveData in receiving data from the API and setting up for the persistence function I applied to the app.

I also used navigation component in passing arguments from one fragment to another.

The overview output of the app is that it list all current heroes from Dota 2. They are arranged by Hero Primary Attribute and Hero Name.
Upon clicking Hero Image, a brief description of the hero and stats will appear in the new fragment. You can also see the base stats of the
Hero by adjusting its level. Upon changing level, some stats such as HP, Mana and Damage will change according to the level entered. I also
created a function where if the user long press on certain icons, it will describe the stats. I put this in place to help the user be familiar
with the icons I used for the stats.

As for the persistence of the app, the user can create a list of his/her favorite Heroes. This can be done by clicking a button on the Details
Page. This button may add or delete a hero from the favorite list. Even if the user closes the app, the hero favorite list will still be saved
in the apps cache/data so upon reopening the app, the hero favorite list is not lost.

You can look at the git logs of the project to see when I started the project and see the incremental progress of the project through time.
I incorporated basic pushing methods to an online repo and used Git Extensions as repository manager since I'm familiar with using it.
