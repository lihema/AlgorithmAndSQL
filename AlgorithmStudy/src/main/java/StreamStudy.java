/**
 * @author lijishu
 * @date 2020/3/4 3:28 下午
 * @content:
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamStudy {



        public List<Integer> initData(){
            List<Integer> skuList = new ArrayList<Integer>();
            for (int i = 0; i <100 ; i++) {
                skuList.add(i);
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

        public Long countSku(){
            Long count=initData().stream().count();
            System.out.println(count);
            return count;
        }

        public static void main(String[] args) {
            StreamStudy streamTest=new StreamStudy();
            //streamTest.countSku();
           List<String> initStringData = streamTest.initStringData();
            Optional<String> largest = initStringData.stream().max(String::compareToIgnoreCase);
           // System.out.println(largest);

           // Optional<String> startWithQ = initStringData.stream().filter(s -> s.startsWith("Q")).findFirst();

            Optional<String> startWithQ = initStringData.stream().parallel().filter(s -> s.startsWith("Q")).findAny();

            System.out.println(startWithQ);
        }
    }


