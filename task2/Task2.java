package com.task2;

import com.task2.City;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        List<City> cities = new LinkedList<>();

        System.out.println("Please enter the number of tests:");
        int numberTests = getScunner().nextInt();

        if (numberTests >= 10) {
            throw new RuntimeException("limit is exceeded");
        }

        while (numberTests > 0) {
            System.out.println("Please enter the number of cities:");
            int numberCities = getScunner().nextInt();

            if (numberCities > 10000) {
                System.out.println("limit is exceeded");
            }

            int id = 1;
            while (numberCities > 0) {
                System.out.println("Enter please name of city:");
                City city = new City(id++, getScunner().nextLine());
                System.out.println("Enter please number of neighbors:");
                int numberNeihbors = getScunner().nextInt();
                while (numberNeihbors > 0) {
                    System.out.println("Enter index of neighbor:");
                    int index = getScunner().nextInt();
                    System.out.println("Enter cost of neighbor:");
                    int cost = getScunner().nextInt();
                    city.addNeighbor(index, cost);
                    numberNeihbors--;
                }
                cities.add(city);
                numberCities--;
            }
            numberTests--;
        }

        System.out.println("Please enter the number of searches:");
        int numberFindPathQuestion = getScunner().nextInt();
        while (numberFindPathQuestion > 0) {
            System.out.println("Enter name 1 :");
            String name1 = getScunner().nextLine();
            System.out.println("Enter name 2:");
            String name2 = getScunner().nextLine();
            System.out.println("Answer = " + findMinimumPath(cities, name1, name2));
            numberFindPathQuestion--;
        }
    }

    static Scanner getScunner() {
        return new Scanner(System.in);
    }

    static City[] getNeighborArrayCity(City city, List<City> cities) {
        City[] arrayCity = new City[city.getNeighbors().size()];
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : city.getNeighbors().entrySet()) {
            arrayCity[count] = cities.get(entry.getKey() - 1);
            count++;
        }
        return arrayCity;
    }

    static int findMinimumPath(List<City> cities, String name1, String name2) {
        List<Integer> answers = new ArrayList<>();
        City city1 = null;
        City city2 = null;
        //Find the city1 and the city2
        for (City s : cities) {
            if (s.getName().equals(name1)) {
                city1 = s;
            } else if (s.getName().equals(name2)) {
                city2 = s;
            }
        }

        System.out.println(city1);
        System.out.println(city2);
        //==================================================
        //find city2 in the neighbors of all cities and write them and them cost in Map
        Map<City, Integer> tempCities = new HashMap<>();
        for (City c : cities) {
            for (City c2 : getNeighborArrayCity(c, cities)) {
                if (c2.getId() == city2.getId()) {
                    tempCities.put(c, c.getCost(c2.getId()));
                }
            }
        }
        //find city1 in the neighbors from cities of map
        int count = cities.size() * cities.size();
        while (count > 0) {
            Map<City, Integer> newCities = new HashMap<>();
            for (Map.Entry<City, Integer> entry : tempCities.entrySet()) {
                for (int c : city1.getArrayNighborsId()) {
                    //if we find city1 in the neighbors from cities of map:
                    //write theem cost and cost from the Map in list of answers
                    if (c == entry.getKey().getId()) {
                        answers.add(city1.getCost(entry.getKey().getId()) + entry.getValue());
                        //if we not find city1 in the neighbors from cities of map:
                        //we write in new Map neighbors from cities of last map
                    } else {
                        for (City a : cities) {
                            for (City a2 : getNeighborArrayCity(a, cities)) {
                                if (a2.getId() == entry.getKey().getId()) {
                                    newCities.put(a, a.getCost(a2.getId()) + entry.getValue());
                                }
                            }
                        }
                    }
                }
                //overwrite cities of Map and repeat the cycle
                tempCities = newCities;
                count--;
            }
        }
        //we find the minimum cost in all variants
        if (answers.size() > 0) {
            return Collections.min(answers);
        } else {
            return 0;
        }
    }
}
