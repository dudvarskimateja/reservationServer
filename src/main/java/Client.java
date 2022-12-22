import java.io.*;
import java.net.*;

public class Client {
    public static void main(String args[]) throws Exception {
        String userInput = null, serverResponse = null, serverMachine;
        int port;

        /* The first argument is the server's name */
        serverMachine = args[0];

        /* The second argument the port that the server accepts connections */
        port = Integer.parseInt(args[1]);

        /* Create a buffer to hold the user's input */
        BufferedReader inFromUser = new BufferedReader( new InputStreamReader( System.in ) );

        /* Create the client socket according to the server's address and port */
        Socket clientSocket = new Socket( serverMachine, port );

        /* Display a connection established message  */
        System.out.println("Connected to: " + clientSocket.getInetAddress() + " on port " + port);

        /* Create a writing buffer to the socket */
        PrintStream outToServer = new PrintStream(clientSocket.getOutputStream());

        /* Create a reading buffer to the socket */
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {

            System.out.println( "Type 'R' to send a time Request to server, or 'Q' to quit: " );

            /* Get user's input */
            userInput = inFromUser.readLine();

            /* Send the message to server */
            outToServer.println( userInput );

            /* Stop infinite loop if user wants to stop getting echos by typing exit */
            if ( userInput.equals( "Q" ) || userInput.equals( "q" ) ){
                System.out.println("Quit. Bye!");
                break;
            }

            /* Stop infinite loop if user wants to stop getting echos by typing exit */
            if ( userInput.equals( "exit" ) )
                break;

            /* Read the server's response */
            serverResponse = inFromServer.readLine();

            /* Display echoed message from server */
            System.out.println("Server returned: " + serverResponse);
        }

        System.out.println("Closing socket.");
        clientSocket.close();
    }
}
