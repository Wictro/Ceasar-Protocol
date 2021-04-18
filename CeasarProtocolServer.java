import java.net.*;
import java.io.*;
/*
*The server that handles all incomin requests
*/
public class CeasarProtocolServer{
    private ServerSocket serverSocket;
		private static final int port = 6900;
		
		/*
		* Entry point for all incoming requests
		*/
    public void start(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        while(true){
            new EchoClientHandler(serverSocket.accept()).start();
        }
    }

    public void stop() throws IOException{
        serverSocket.close();
    }
		
		/*
		* Handles a single TCP connection
		*/
    private static class EchoClientHandler extends Thread{
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket){
            this.clientSocket = socket;
        }

        public void run(){
            try{out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						out.println(ResponseCode.START_GREET.toString());
            String inputLine;
						String[] responseLines;
            while((inputLine = in.readLine()) != null){
                if(inputLine.equals(OpCode.CLOSE.toString())){
                    stopConnection();
                    break;
                }
								
								try{
									responseLines = Cryptor.crypt(new ClientRequest(inputLine));
									for(String line : responseLines){
										out.println(line);
									}
								}
								catch(Exception e){
									out.println(e.getMessage());
								}
            }}
            catch(IOException e){}
        }

        private void stopConnection() throws IOException{
            out.println(ResponseCode.END_GREET.toString());
            in.close();
            out.close();
            clientSocket.close();
        }
    }
		
		/*
		* Initialize and run the server
		*/
    public static void main(String[] args) throws IOException{
        new CeasarProtocolServer().start(port);
    }
}
