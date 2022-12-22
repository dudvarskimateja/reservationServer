import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Server {
    public static <reservedSeat> void main(String args[] ) throws Exception {
        String inputFromClient;
        String output2client;
        int port;
        Socket connectionSocket = null;
        BufferedReader inFromClient;
        PrintStream outToClient;

        /* port is the argument passed to program */
        port = Integer.parseInt( args[0] );

        /* Create socket for port */
        ServerSocket welcomeSocket = new ServerSocket( port );

        System.out.println("Server waiting at port: " + welcomeSocket.getLocalPort());

        /* Wait endlessly for connections */
        while(true) {

            /* Accept the connection */
            connectionSocket = welcomeSocket.accept();
            System.out.println("Accepted connection from: " + connectionSocket.getInetAddress() );

            /* Create a reading stream to the socket */
            inFromClient = new BufferedReader( new InputStreamReader( connectionSocket.getInputStream() ) );

            /* Create a writing stream to the socket */
            outToClient = new PrintStream( connectionSocket.getOutputStream() );

            /* Wait endlessly for specific client to type messages */
            while ( true ) {
                inputFromClient = null;
                try {
                    /* Read client's message through the socket's input buffer */
                    inputFromClient = inFromClient.readLine();

                    if (inputFromClient.equalsIgnoreCase("R")) {
                        String reservedSeat = null;
                        output2client = reservedSeat;

                        //Create a new datagram data buffer (byte array) for echoing the reserved seat

                    } else {
                        //Create a new datagram data buffer (byte array) for echoing the according response

                        output2client = "Did not receive a reserve seat request";
                    }

                }
                catch (IOException e) {
                    System.out.println( connectionSocket.getInetAddress() + " broke the connection." );
                    break;
                }

                /* Output to screen the message received by the client */
                System.out.println( "Seat reserved: " + inputFromClient );

                /* If message is exit then terminate specific connection - exit the loop */
                if ( inputFromClient.equalsIgnoreCase("Q") ) {
                    System.out.println( "Closing connection with " + connectionSocket.getInetAddress() + "." );
                    break;
                }

                /* Send it back through socket's output buffer */
                outToClient.println( output2client );
            }
            /* Close input stream */
            inFromClient.close();

            /* Close output stream */
            outToClient.close();

            /* Close TCP connection with client on specific port */
            connectionSocket.close();

            /* Wait for more connections */
            System.out.println( "Server waiting at port: " + welcomeSocket.getLocalPort() );
        }
    }
}

