import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        // Ranking List demo
        //
        //
        //
        /*
        RankingListManager rlm = new RankingListManager();

        rlm.addToRankingList("Valter", 100);
        rlm.addToRankingList("Jani", 50);
        rlm.addToRankingList("Lali", 40);
        rlm.addToRankingList("Józsi", 60);
        rlm.addToRankingList("Sanyi", 20);


        rlm.printRankingList(rlm.getRankingList());
        rlm.serialiseRankingList();

        rlm.deserialiseRankingList("map.txt");
        rlm.printRankingList(rlm.getRankingList());
        //********************************************************************
        //
        //
        //

         */

        // Network Demo
        //
        //
        TestPlayerMoved proba = new TestPlayerMoved();
        TestPlayerMoved received = new TestPlayerMoved();

        proba.PlayerMovedEvent(1, 4, 6);

        Scanner scanner = new Scanner(System.in);

        int serverclient = 0;
        int tx = 0;
        String buffer = "";

        String ip = "localhost";
        int port = 22222;


        //ip es port bekerese
        System.out.println("Please input the IP: ");
        ip = scanner.nextLine();
        System.out.println("Please input the port: ");
        port = scanner.nextInt();

        //port ellenorzese
        while (port < 1024 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = scanner.nextInt();
        }


        NetworkCommunication NC = new NetworkCommunication();


        //server es client mod kivalasztas
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

        //ki kell valasztani h kuldeni vagy fogadni akarunk
        //
        //mindegy h mikor valasztjuk ki a fogadast
        //az uzenet elkuldese elott es utan is kivalaszthatojuk a masik gepen fogadast

        while(true) {
            System.out.println("1 For SEND, 0 for RECEIVE ");
            tx = scanner.nextInt();

            if (tx == 1) {
                /*
                System.out.println("Write the message: ");
                //buffer = scanner.nextLine();
                buffer = System.console().readLine();
                System.out.println(buffer);

                 */


                NC.send(proba);
                System.out.println("Message sent!");


                tx = 0;
            } else {
                System.out.println("Received data:");
                received = NC.receive();
                System.out.println(received.getPlayerId());
                System.out.println(received.getPreviousPosition());
                System.out.println(received.getNewPosition());


            }
        }

        //socket lezarasa
        //NC.socketclose();

        //*****************************************************************************



    }
}
