import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int serverclient = 0;
        int tx = 0;
        int buffer = 0;

        String ip = "localhost";
        int port = 22222;


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
