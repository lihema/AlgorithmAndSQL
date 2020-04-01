package StreamTest.Stream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author: LiJiShu
 * @date: 2020/3/31 19:57
 * @content:
 */
public class DownstreamCollectors {

    public static class City {

        private String name;

        private String state;

        private int population;

        public City(String s, String s1, int parseInt) {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }
    }

    public static Stream<City> readCities(String filename) throws IOException {

        return Files.lines(Paths.get(filename)).map(l -> l.split(",")).map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));

    }

    public static void main(String[] args) throws IOException {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(Locale::getCountry, toSet()));
        System.out.println("countryToLocaleSet:" + countryToLocaleSet);


        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Long> countryToLocaleCount = locales.collect(groupingBy(Locale::getCountry, counting()));
        System.out.println("countryToLocaleCount:" + countryToLocaleCount);

        Stream<City> cities = readCities("StreamTest/cities.txt");
        Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(City::getState
                , summingInt(City::getPopulation)));
        System.out.println("stateToCityPopulation:" + stateToCityPopulation);

        cities = readCities("StreamTest/cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(groupingBy(
                City::getState
                , mapping(City::getName
                        , maxBy(Comparator.comparing(String::length)))));
        System.out.println("stateToLongestCityName:" + stateToLongestCityName);

        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countoryToLanguages = locales.collect(groupingBy(Locale::getDisplayCountry
                , mapping(Locale::getDisplayLanguage
                        ,toSet())));
        System.out.println("countoryToLanguages:" + countoryToLanguages);

        cities = readCities("StreamTest/cities.txt");
        Map<String,IntSummaryStatistics> stateToCityPopulationSummary = cities
                                                                     .collect(groupingBy(City::getState),ww)



    }

}
