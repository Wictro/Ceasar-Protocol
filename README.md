Ceasar Protocol is an Application Layer Protocol that encrypts or decrypts data with the simplest algorithm ever - the Ceasar Cipher. The code implementation of just performing the encrypt/decrypt shifting of a string is rather easy, but this is intended to create full protocol that a server can recieve, parse, proccess, and reply to requests.

The syntax for the protocol I all made up and it is quite stupid - but I make the rules so haha.

It is recommended that the server listens on port 6900 for all requests of this sort.

This app provides the Entry Point - CeasarProtocolServer.main(), where it can be run. It is built with a multi-threaded architecture so it can hold quite a few clients without needing to dive into multithreaded problems like synchronization and priorities and blocking and all that jargon since it doesn't do much.

To top this off, feel free to Telnet this server or just create a simple client that sends requests to it - It will respond.



