import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NetworkCommunication {

    private String ip = "localhost";
    private int port = 22222;
    private int buffer = 0;
    private int tx = 0;

    private Scanner scanner = new Scanner(System.in);

    private DataOutputStream dos;
    private DataInputStream dis;

    private Socket socket;
    private ServerSocket serverSocket;

    private boolean accepted = false;
    private int errors = 0;




    NetworkCommunication() {
        System.out.println("Network Communication");

        System.out.println("Please input the IP: ");
        ip = scanner.nextLine();
        System.out.println("Please input the port: ");
        port = scanner.nextInt();



        while (port < 1 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = scanner.nextInt();
        }



        if (!connect()) initializeServer();

    }

    public void run(){
        while (true) {

            System.out.println("1 For SEND, 0 for RECEIVE ");
            tx = scanner.nextInt();

            if (!accepted) listenForServerRequest();

            if (tx == 1){
                System.out.println("Write the message: ");
                buffer = scanner.nextInt();

                try {
                    dos.writeInt(buffer);
                    dos.flush();
                    buffer = 0;
                } catch (IOException e1) {
                    errors++;
                    e1.printStackTrace();
                }
                System.out.println("DATA WAS SENT");
                tx = 0;
            }

            else{
                System.out.println("Received data:");

                try{
                    buffer = dis.readInt();
                    System.out.println(buffer);
                    buffer = 0;

                }catch(IOException e2){
                    errors++;
                    e2.printStackTrace();
                }

            }





        }
    }

    private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
            return false;
        }
        System.out.println("Successfully connected to the server.");
        return true;
    }

    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //yourTurn = true;
        //circle = false;
    }

    private void listenForServerRequest() {
        System.out.println("Waiting for client...");
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
            System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}

