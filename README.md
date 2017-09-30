## SaleMove.Droid

Implementing SaleMove API in Android, a proof of concept, for SaleMove's **Hack the CX** hackathon.

* Team: Swift
* Members: Anna, John, Aare

### Plan

Build an interactive user interface in Android, implementing Salemove's API, which features, among other things, [WebRTC](https://webrtc.org/). Essentially creating a prototype for SaleMove's Android SDK

... and all that utilizing both Kotlin and Java (because why the hell wouldn't we want to make our lives even more difficult), because Java was common ground for us, but Kotlin is fun.

### Process

Soon after we had started, it became apparent that SaleMove does not have a public API for their WebSocket solution, so for a while I tried to decode the crypic messages to write a solution from scratch, using an Android implementation of Socket.IO. But all that backfired in a tremendous way when by the end of Friday I still had no idea what the hell was going on. It was an ambitious task, and I admit defeat.

The next-best thing was to create a WebView-based solution, so I threw together a small site (available in the [/web](/web) folder) with the help of [site_hosting_and_conf.md](https://gist.github.com/deiwin/a5d35325fa185863e7a30cea3b6cf093), implemented the SDK, asked the mentors for all the necessary access keys and hosted it on a server where our application could access it from.

Sounds great, right? Wrong. SaleMove's audio and video features require the use of https protocol... and I don't own any site with that license, so it had to be purely text-chat-based. Drats, foiled again.

That's still fine: so we created a WebView, loaded the site from the url, thinking everything would be great, but the chat button wouldn't appear. What!?

After some debugging, as it turns out, Android WebViews didn't even support sockets until v4.4 (KitKat), but we had newer operation systems, so everything should have been fine. But no. It didn't give any errors, just didn't open the Websocket connection. I have no idea why. Fun fact: the only reason I could debug the application and find out everything is fine **is because I'm using the newer, Chromium-based WebView**. Else I would've been pulling my hair out by now.

So the final, worst, solution would be to implement a hack that opens the Chrome app from our App without asking the user for confirmation, so it would, at least somewhat, look like the user was still in the application.

And here we are: an application featuring a list where each item has two buttons: one to close the app and start a phone call with a SaleMove operator, and another to open Chrome that goes on a website where SaleMove SDK is implemented, but only with text-based chat. **>:(**


