import java.net.* ;
import java.io.* ;
import java.lang.* ;
import java.util.* ;

class proxy {
    public static void main(String args[]) throws IOException{
        
        //parse arguments from command line
        
        int localport = -1;
        int remoteport = -1;
        String remotehost = null;
        boolean error = false;
        
        int i = 0;
        // First bug identified: index out of bounds :-)
        Integer parselocalport = new Integer(args[i]);
        Integer parseremoteport = new Integer(args[i+2]);
        Socket incoming, outgoing = null;
        ServerSocket Server = null;
        
        try
        {
            localport = parselocalport.parseInt(args[i], 10);
            remotehost = args[i+1];
            remoteport = parseremoteport.parseInt(args[i+2], 10);
        }
        
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage() + "\n");
            error = true;
        }
        
        // Check for valid local and remote port, hostname not null
        // Second bug: no space in text :-)
        System.out.println("Checking: Port" + localport + " to " + remotehost + " Port " + remoteport);
        
        if(localport <= 0){
            System.err.println("Error: Invalid Local Port Specification " + "\n");
            error = true;
        }
        if(remoteport <=0){
            System.err.println("Error: Invalid Remote Port Specification " + "\n");
            error = true;
        }
        if(remotehost == null){
            System.err.println("Error: Invalid Remote Host Specification " + "\n");
            error = true;
        }
        
        //If any errors so far, exit program
        
        if(error)
            System.exit(-1);
        
        
        //Test and create a listening socket at proxy
        
        try{
            Server = new ServerSocket(localport);
        }
	catch(IOException e) {
	    e.printStackTrace();
        }
        
        //Loop to listen for incoming connection, and accept if there is one
        
        while(true)
	    {
		try{
		    incoming = Server.accept();
		    //Create the 2 threads for the incoming and outgoing traffic of proxy server
		    outgoing = new Socket(remotehost, remoteport); 

		    proxyThread thread1 = new proxyThread(incoming, outgoing);
		    thread1.start();
		    
		    proxyThread thread2 = new proxyThread(outgoing, incoming);
		    thread2.start();
		} 
		catch (UnknownHostException e) {
		    //Test and make connection to remote host
		    System.err.println("Error: Unknown Host " + remotehost);
		    System.exit(-1);
		} 
		catch(IOException e){
		    System.exit(-2);//continue;
		}

		/*		catch (IOException e) {
		    System.err.println("Error: Couldn't Initiate I/O connection for " + remotehost);
		    System.exit(-1);
		}
		*/
	    }
    }
    

}


        
      
            
            
            
            
            
            
            
            
            
            
