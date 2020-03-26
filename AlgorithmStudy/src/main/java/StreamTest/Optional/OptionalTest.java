package StreamTest.Optional;

import java.util.*;

/**
 * @author: LiJiShu
 * @date: 2020/3/26 19:02
 * @content:
 */
public class OptionalTest {

    public List<String> initString(){
        List<String> result = new ArrayList<>();

        for (int i = 0; i <20 ; i++) {
            result.add("test"+i);
        }

        return  result;
    }

    public static Optional<Double> invert(Double x){

        return x == 0 ? Optional.empty() : Optional.of(1/x);

    }

    public static  Optional<Double> squareRoot(Double x){
        return  x<0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

    public static void main(String[] args) {
        OptionalTest optionalTest = new OptionalTest();

        List<String> wordList = optionalTest.initString();

        Optional<String> optionalStringValue = wordList.stream().filter(s->s.contains("te")).findFirst();

        System.out.println(optionalStringValue.orElse("No Word!" + "Contain  Words"));

        Optional<String> optionalString = Optional.empty();

        String result = optionalString.orElse("N/A");

        System.out.println("result:"+ result);

        result = optionalString.orElseGet(()-> Locale.getDefault().getDisplayName());

        System.out.println("result:"+result);

        try {

            result = optionalString.orElseThrow(IllegalAccessError::new);

            System.out.println("result:"+result);

        }catch (Throwable e){

            e.printStackTrace();

        }

        optionalStringValue = optionalTest.initString().stream()
                .filter(s->s.contains("te")).findFirst();

        optionalStringValue.ifPresent(s -> System.out.println("ifPresent result:"+s));

        Set<String> resultSet = new HashSet<>();

        optionalStringValue.ifPresent(resultSet::add);

        Optional<Boolean> added = optionalStringValue.map(resultSet::add);

        System.out.println(added);

        System.out.println(invert(4.0).flatMap(OptionalTest::squareRoot));

        System.out.println(invert(-4.0).flatMap(OptionalTest::squareRoot));

        System.out.println(invert(0.0).flatMap(OptionalTest::squareRoot));



    }

}
