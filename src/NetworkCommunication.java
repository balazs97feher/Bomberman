import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NetworkCommunication {

    private Scanner scanner = new Scanner(System.in);

    private DataOutputStream dos;
    private DataInputStream dis;

    private Socket socket;
    private ServerSocket serverSocket;

    private boolean accepted = false;
    private int errors = 0;


    NetworkCommunication() {
        System.out.println("CONSTRUCTOR: Network Communication ");
    }

    public void servermode(String ip, int port){
        initializeServer(ip, port);
        if (!accepted) listenForServerRequest();
    }

    public void clientmode(String ip, int port){
        connect(ip, port);
    }

    public void send(int message){
        try {
            dos.writeInt(message);
            dos.flush();
            message = 0;
            return;
        } catch (IOException e1) {
            errors++;
            e1.printStackTrace();
        }
    }

    public int receive(){
        try{
            return dis.readInt();

        }catch(IOException e2){
            errors++;
            e2.printStackTrace();
            return -666;
        }
    }


    private boolean connect(String ip, int port) {
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

    private void initializeServer(String ip, int port) {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

