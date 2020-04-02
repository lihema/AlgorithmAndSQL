package StreamTest.Stream;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: LiJiShu
 * @date: 2020/3/27 13:40
 * @content:
 */
public class CollectingResults {


    public static List<String> initString(){
        List<String> result = new ArrayList<>();

        for (int i = 0; i <20 ; i++) {
            result.add("test"+i);
        }

        return  result;
    }

    public static Stream<String> noVowels() throws IOException{

        List<String> wordList = initString();

        Stream<String> words = wordList.stream();

        return words.map(s -> s.replace("test","TEST"));

    }

    public static <T> void show(String label,Set<T> set){

        System.out.println(label + ":" + set.getClass().getName());
        System.out.println("[" + set.stream().limit(10).map(Object::toString).collect(Collectors.joining(","))+"]");

    }

    public static void main(String[] args) throws IOException {

        Iterator<Integer> iter = Stream.iterate(0,n->n+1).limit(10).iterator();

        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        Object[] numbers = Stream.iterate(0,n->n+1).limit(10).toArray();

        System.out.println("Object array" + numbers);

        try{

            Integer number = (Integer)numbers[0];
            System.out.println("number" + number);
            Integer[] numbers2 = (Integer[]) numbers;

        }catch (ClassCastException e){

            e.printStackTrace();

        }

        Integer[] number3 = Stream.iterate(0,n->n+1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array:" + number3);
        try {
            Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
            show("noVowelSet",noVowelSet);

            TreeSet<String>noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
            show("noVowelTreeSet",noVowelTreeSet);


            String result = noVowels().collect(Collectors.joining());
            System.out.println("Joining:" + result);


            result= noVowels().limit(10).collect(Collectors.joining(","));
            System.out.println("Joining with comms" + result);


        }catch (IOException e){
            e.printStackTrace();
        }

        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("averageWordLength:"+averageWordLength);
        System.out.println("maxWordLength:"+maxWordLength);
        System.out.println("foreach");
        noVowels().limit(10).forEach(System.out::println);

    }

}
