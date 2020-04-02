package StreamTest.Stream; /**
 * @author lijishu
 * @date 2020/3/4 3:28 下午
 * @content:
 */


import jdk.internal.dynalink.beans.StaticClass;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStudy {



        public static List<Integer>  initData(){
            List<Integer> skuList = new ArrayList<Integer>();
            for (int i = 0; i <100 ; i++) {
                skuList.add(i);
            }
            return  skuList;
        }

       public static List<String> initStringDate(){
        List<String> skuList = new ArrayList<String>();
        for (int i = 0; i <100 ; i++) {
            skuList.add(String.valueOf(i));
        }
        return  skuList;
    }

    public List<String> initStringData(){
        List<String> skuList = new ArrayList<String>();
        for (int i = 0; i <100 ; i++) {
            if (i>10) {
                skuList.add("test" + i);
            }else {
                skuList.add("Qwer"+i);
            }
        }
        return  skuList;
    }

    public static Integer add(Integer q){
            q++;
            return q;
    }

        public Long countSku(){
            Long count=initData().stream().count();
            System.out.println(count);
            return count;
        }

    /**
     * 输出流
     * @param title
     * @param stream
     * @param <T>
     */
        public static <T> void show(String title,Stream<T> stream){
            final int SIZE = 0;
            List<T> firstElements = stream.limit(SIZE+1).collect(Collectors.toList());
            System.out.println(title + ":");
            for (int i = 0; i <firstElements.size() ; i++) {
                if(i<SIZE){
                    System.out.println(firstElements.get(i));
                }else {
                    System.out.println("....");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            StreamTest.Stream.StreamStudy streamTest=new StreamTest.Stream.StreamStudy();
            //streamTest.countSku();
           List<String> initStringData = streamTest.initStringData();
            Optional<String> largest = initStringData.stream().max(String::compareToIgnoreCase);
           // System.out.println(largest);

            Optional<String> startWithQ = initStringData.stream().filter(s -> s.startsWith("Q")).findFirst();

            //Optional<String> startWithQ = initStringData.stream().parallel().filter(s -> s.startsWith("Q")).findAny();

            System.out.println(startWithQ);

            streamTest.countSku();
            Integer[] skuArray = new Integer[]{1,2,3,4,5,6};
            Stream<Integer> skuArrayStream = Stream.of(skuArray);
            show("Array",skuArrayStream);

            Stream<String> lijishu = Stream.generate(()->"lijishu");
            show("lijishu",lijishu);

            Stream<Double> random = Stream.generate(Math::random);
            show("random",random);

           Stream<BigInteger>integerStream = Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.ONE));
            show("integerStream",integerStream);

            Stream<String> mapperTest = initStringDate().stream().map(String::toLowerCase);

            Stream<String> mapperTest2 = initStringDate().stream().map(s -> s+"lijishu");

            Stream<String> mapperTest3 = initStringDate().stream().distinct();


            Object[] powers = Stream.iterate(1.0,p->p*2).peek(e-> System.out.println("Fetching:"+e)).limit(20).toArray();
            for (Object a:powers) {
                System.out.println(a.toString());
            }


        }
    }


