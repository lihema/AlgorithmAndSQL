/**
 * @author lijishu
 * @date 2020/3/4 3:28 下午
 * @content:
 */


import java.util.ArrayList;
import java.util.List;

public class StreamStudy {



        public List<Integer> initDate(){
            List<Integer> skuList = new ArrayList<Integer>();
            for (int i = 0; i <100 ; i++) {
                skuList.add(i);
            }
            return  skuList;
        }

        public Long countSku(){
            Long count=initDate().stream().count();
            System.out.println(count);
            return count;
        }

        public static void main(String[] args) {
            StreamStudy streamTest=new StreamStudy();
            streamTest.countSku();
        }
    }


