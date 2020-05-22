import java.util.Scanner;




public class Main {


    private static Scanner scanner = new Scanner(System.in);

    private static int serverclient = 0;
    private static int tx = 0;
    private static int buffer = 0;

    private static String ip = "localhost";
    private static int port = 22222;

    public static void main(String[] args) {

        System.out.println("Please input the IP: ");
        ip = scanner.nextLine();
        System.out.println("Please input the port: ");
        port = scanner.nextInt();

        while (port < 1024 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = scanner.nextInt();
        }

        NetworkCommunication NC = new NetworkCommunication();

        System.out.println("1 - SERVER, 0 - CLIENT ");
        serverclient = scanner.nextInt();


        if(serverclient == 1){
            System.out.println("SERVER mode selected");
            NC.servermode(ip, port);
            System.out.println("SERVER mode activated: " + ip + ":" + port + " | Starting a server");
        }
        else if(serverclient == 0){
            System.out.println("CLIENT mode selected");
            NC.clientmode(ip, port);
            System.out.println("CLIENT mode activated: " + ip + ":" + port + " | Starting a server");

        }

        while(true) {
            System.out.println("1 For SEND, 0 for RECEIVE ");
            tx = scanner.nextInt();

            if (tx == 1) {
                System.out.println("Write the message: ");
                buffer = scanner.nextInt();

                NC.send(buffer);

                tx = 0;
            } else {
                System.out.println("Received data:");
                System.out.println(NC.receive());
            }
        }
    }
}
