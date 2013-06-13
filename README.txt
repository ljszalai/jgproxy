Dan Goldberg

proxy.java, proxyThread.java

To use this proxy server, one must either compile proxy.java themselves by typing javac proxy.java on the command line from within the directory where the file proxy.java resides, or use the compiled version I have handed in with the source files for proxy.java and proxyThread.java (proxy.class and proxyThread.class).  Either method will work.

Once you have the compiled classes, you are free to run the program.
This is done by entering the following commands, from the command line, within the directory where the class files reside:

> java proxy [local port number] [remote machine] [remote port number]

This sequence of commands will start the proxy server listening on the [local port number] which is specified.  If it recieves a request on that port, it will forward it to the specified [remote port number] of the [remote machine]  

A user can then connect to the port on the local machine where the proxy server is running, and it will simulate being connected to the remote port on the remote machine.

An example of this could be:

(Start the proxy server listening to local port 1234 and sending any requests made to that port to port 23 (telnet) on the romulus machine) 

>java proxy.java 1234 romulus 23

Once the proxy server is running (listening) on port 1234 of the local machine, a user would enter the following to begin communicating with the telnet server on romulus:

> telnet [local machine name] 1234

where [local machine name] is the name of the machine where the proxy server is running.

When this command is entered, the user will be prompted for the telnet login, and they will be able to proceed using the telnet service as if they had contacted it directly, without the proxy server.  Thus, the proxy has done it's job and is invisible to both client and server.  Any port which uses the TCP/IP protocol is support, and can proxied to by the proxy server (ECHO, FTP, Telnet, etc.)

To terminate an active proxy server, the user must have the window where the proxy server is running open and active, and press Ctrl+C, the standard break command.  This proxy server can not be terminated through any other means, such as command line arugements, because it is multi-threaded (supports multiple users at the same time).  If one user were to terminate the active proxy server, all users would loose their connections, which is undesireable.   Instead, only the person who initiated the proxy server can cancel it, thus closing all open connections.








