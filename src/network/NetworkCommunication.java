package network;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NetworkCommunication {

    private Scanner scanner = new Scanner(System.in);

    private DataOutputStream dos;
    private DataInputStream dis;




    private Socket socket;
    private ServerSocket serverSocket;

    //0 client, 1 server
    private int SocketMode = 0;

    private boolean accepted = false;
    private int errors = 0;

    //konstruktor
    //csak h latszodjon h lefut
    NetworkCommunication() {

        System.out.println("CONSTRUCTOR: Network Communication ");
    }

    //server mode valasztasa
    public void servermode(String ip, int port){
        initializeServer(ip, port);
        if (!accepted) listenForServerRequest();
    }

    //client mod valasztasa
    //meg kell adni h mi a server ip es port
    public void clientmode(String ip, int port){
        connect(ip, port);
    }


    //uzenet kuldese
    //objektum uzenet kuldese parameterkent
    public void send(TestPlayerMoved message){
        try {

            ObjectOutputStream oos = new ObjectOutputStream(dos);


            oos.writeObject(message);
            oos.flush();
            oos.close();

            return;

            //string kuldese
            /*
            dos.writeUTF(message);
            dos.flush();
            message = "";
            return;

             */
        } catch (IOException e1) {
            errors++;
            e1.printStackTrace();
        }
    }

    //uzenet fogadasa
    //fogadott objektumot ad vissza
    public TestPlayerMoved receive(){
        try{
            ObjectInputStream ois = new ObjectInputStream(dis);

            return (TestPlayerMoved) ois.readObject();

        }catch(IOException | ClassNotFoundException e2){
            errors++;
            e2.printStackTrace();
            TestPlayerMoved error = new TestPlayerMoved();
            return error;
        }
    }

    //kapcsolat lezarasa
    //socket close
    public void socketclose(){
        if (SocketMode == 1) {
            try {
                serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(SocketMode == 0)
            try {
                socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }


    //serverhez csatlakozas
    //visszaadja h sikeres e vagy nem
    private boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
            SocketMode = 0;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
            return false;
        }
        System.out.println("Successfully connected to the server.");
        return true;
    }


    //server inicializalasa
    //ip es portot kell megadni
    private void initializeServer(String ip, int port) {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            SocketMode = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //cliens fogadasa a szerveren
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

