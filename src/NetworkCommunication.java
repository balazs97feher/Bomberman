import java.io.*;
import java.net.*;

public class NetworkCommunication {
    int ServerPort = 666;
    String ServerIP = "localhost";


    NetworkCommunication(){
        System.out.println( "Network Communication");
    }

    void ClientMode(){

        System.out.println( "Client Mode");

        try (Socket ClientSocket = new Socket(ServerIP, ServerPort)) {

                    InputStream input = ClientSocket.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input);

                    int character;
                    StringBuilder data = new StringBuilder();

                    while ((character = reader.read()) != -1) {
                        data.append((char) character);
                    }

                    System.out.println(data);


                } catch (UnknownHostException ex) {

                    System.out.println("Server not found: " + ex.getMessage());

                } catch (IOException ex) {

                    System.out.println("I/O error: " + ex.getMessage());
                }



    }
    void ServerMode(){
        System.out.println( "Server Mode");


        try (ServerSocket serverSocket = new ServerSocket(ServerPort)) {

            System.out.println("Server is listening on port " + ServerPort);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                new ServerThread(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }



    }


}

