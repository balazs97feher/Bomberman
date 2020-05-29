import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

import java.io.*;



public class RankingListManager {

    Map<String, Integer> unsortedMap = new HashMap<String, Integer>();
    Map<String, Integer> sortedMap = new HashMap<String, Integer>();


    //Listahoz adas
    //visszaadja a mar rendezett listat
    public Map<String, Integer> addToRankingList(String username, int score){
        //Beillesztes a listaba
        unsortedMap.put(username, score);

        //Lista rendezese csokkeno sorba
        sortedMap = this.sortByValue(unsortedMap);

        return sortedMap;
    }

    //rendezett lista lekerdezese
    public Map<String, Integer> getRankingList(){
        return sortedMap;
    }

    //Lista nyomtatasa consolera
    public static <U, S> void printRankingList(Map<U, S> map) {
        System.out.println("Sorted Ranking List:");

        for (Map.Entry<U, S> entry : map.entrySet()) {
            System.out.println("Username : " + entry.getKey()
                    + " Score : " + entry.getValue());
        }
    }

    //lista mentese fileba
    public void serialiseRankingList(){

        try {
            FileOutputStream fos = new FileOutputStream("map.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(sortedMap);
            oos.close();

        }catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    //file beolvasasa
    public void deserialiseRankingList(String file){
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map sortedMap = (Map) ois.readObject();
            ois.close();
        }catch(IOException | ClassNotFoundException e1){
            e1.printStackTrace();
        }


    }


    //Lista rendezese es atrakasa a sortedba
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

    /*
    //classic iterator example
    for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
        Map.Entry<String, Integer> entry = it.next();
        sortedMap.put(entry.getKey(), entry.getValue());
    }*/


        return sortedMap;
    }













}
