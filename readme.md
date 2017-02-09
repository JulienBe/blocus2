# Inner working
The idea here is to architecture the game around events.

The hope is that it would make easy to plug in new things.

For example, a screenshake effect (They are almost as important as particles) could listen to 'enemyDestroyed' events

Look at this to see how it begins : https://github.com/JulienBe/blocus2/blob/master/core/src/main/scala/systems/eventhub/EventHub.scala

I don't know how strictly it can be implemented. 

The hurdles that I can imagine are : 

- too much delay
- Somewhere linked to that, what happens if we need two things to happen at the same time. For example event A happens on frame 1, Class B knows it at frame 2, and so on..

The advantages I see are :

- low coupling, easy to plug in / replace an element
- easy (or less painful) to implement networking as you would send those events.
- depending on how strictly it's implemented, easy to log events and replay

