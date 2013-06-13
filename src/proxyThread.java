import java.net.* ;
import java.io.* ;
import java.lang.* ;
import java.util.* ;

class proxyThread extends Thread {
    Socket incoming, outgoing;
    
    //Thread constructor
    
    proxyThread(Socket in, Socket out){
        incoming = in;
        outgoing = out;
    }
    
    //Overwritten run() method of thread -- does the data transfers
    
    public void run(){
        byte[] buffer = new byte[60];
      int numberRead = 0;
      OutputStream ToClient;
      InputStream FromClient;
      
      try{
          ToClient = outgoing.getOutputStream();      
          FromClient = incoming.getInputStream();
         while( true){
           numberRead = FromClient.read(buffer, 0, 50);
	   System.out.println("read " + numberRead);
           //buffer[numberRead] = buffer[0] = (byte)'+';
           
	    if(numberRead == -1){
	      incoming.close();
	      outgoing.close();
	    }
	   
	   ToClient.write(buffer, 0, numberRead);
	   
	  
	 }
 
      }
      catch(IOException e) {}
      catch(ArrayIndexOutOfBoundsException e) {}
      
    }
    
}
