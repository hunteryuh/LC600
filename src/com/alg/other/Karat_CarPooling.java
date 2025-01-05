package com.alg.other;

import java.util.*;
/*
/*
You and your friends are driving to a Campground to go camping. Only 2 of you have cars, so you will be carpooling.
Routes to the campground are linear, so each location will only lead to 1 location and there will be no loops or detours. Both cars will leave from their starting locations at the same time. The first car to pass someone's location will pick them up. If both cars arrive at the same time, the person can go in either car.
Roads are provided as a directed list of connected locations with the duration (in minutes) it takes to drive between the locations.
[Origin, Destination, Duration it takes to drive]
Given a list of roads, a list of starting locations and a list of people/where they live, return a collection of who will be in each car upon arrival to the Campground.
------------------------------------------------------
Brid‍‍‌‍‍‌‍‌‌‌‌‍‍‍‌‍gewater--(30)-->Caledonia--(15)-->New Grafton--(5)-->Campground
                                       ^
Liverpool---(10)---Milton-----(30)-----^
roads1 = [
    ["Bridgewater", "Caledonia", "30"], <= The road from Bridgewater to Caledonia takes 30 minutes to drive.
    ["Caledonia", "New Grafton", "15"],
    ["New Grafton", "Campground", "5"],
    ["Liverpool", "Milton", "10"],
    ["Milton", "New Grafton", "30"]
]
starts1 = ["Bridgewater", "Liverpool"]
people1 = [
    ["Jessie", "Bridgewater"], ["Travis", "Caledonia"],
    ["Jeremy", "New Grafton"], ["Katie", "Liverpool"]
]
Car1 path: (from Bridgewater): [Bridgewater(0, Jessie)->Caledonia(30, Travis)->New Grafton(45)->Campground(50)]
Car2 path: (from Liverpool): [Liverpool(0, Katie)->Milton(10)->New Grafton(40, Jeremy)->Campground(45)]
Output (In any order/format):
    [Jessie, Travis], [Katie, Jeremy]
--------------------------------------
Riverport->Chester->Campground
             ^
Halifax------^
roads2 = [["Riverport", "Chester", "40"], ["Chester", "Campground", "60"], ["Halifax", "Chester", "40"]]
starts2 = ["Riverport", "Halifax"]
people2 = [["Colin", "Riverport"], ["Sam", "Chester"], ["Alyssa", "Halifax"]]
Riverport->Bridgewater->Liverpool->Campground
Output (In any order/format):
    [Colin, Sam], [Alyssa] OR [Colin], [Alyssa, Sam]
----------------------------------------
Riverport->Bridgewater->Liverpool->Campground
roads3 = [["Riverport", "Bridgewater", "1"], ["Bridgewater", "Liverpool", "1"], ["Liverpool", "Campground", "1"]]
starts3_1 = ["Riverport", "Bridgewater"]
starts3_2 = ["Bridgewater", "Riverport"]
starts3_3 = ["Riverport", "Liverpool"]
people3 = [["Colin", "Riverport"], ["Jessie", "Bridgewater"], ["Sam", "Liverpool"]]
Output (starts3_1/starts3_2):  [Colin], [Jessie, Sam] - (Cars can be in any order)
Output (starts3_3): [Jessie, Colin], [Sam]
----------------------------------------
All Test Cases: (Cars can be in either order)
carpool(roads1, starts1, people1) => [Jessie, Travis], [Katie, Jeremy]
carpool(roads2, starts2, people2) => [Colin, Sam], [Alyssa] OR [Colin], [Alyssa, Sam]
carpool(roads3, starts3_1, people3) => [Colin], [Jessie, Sam]
carpool(roads3, starts3_2, people3) => [Jessie, Sam], [Colin]
carpool(roads3, starts3_3, people3) => [Jessie, Colin], [Sam]
----------------------------------------
Complexity Variable:
n = number of roads
*/
public class Karat_CarPooling {

    class Car {
        List<String> passengers;

        public Car() {
            passengers = new ArrayList<>();
        }

        public void addPassenger(String passenger) {
            passengers.add(passenger);
        }

        public List<String> getPassengers() {
            return passengers;
        }
    }
    public List<List<String>> carpool(List<String[]> roads, List<String> starts, List<String[]> people) {
        Map<String, Map<String, Integer>> graph = buildGraph(roads);
        Map<String, String> personLocation = new HashMap<>();
        for (String[] person : people) {
            personLocation.put(person[1], person[0]);
        }
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < starts.size(); i++) {
            Car car = new Car();
            dfs(starts.get(i), graph, personLocation, car);
            cars.add(car);
        }
        List<List<String>> result = new ArrayList<>();
        for (Car car : cars) {
            result.add(car.getPassengers());
        }
        return result;
    }

    private static Map<String, Map<String, Integer>> buildGraph(List<String[]> roads) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        for (String[] road : roads) {
            String origin = road[0];
            String destination = road[1];
            int duration = Integer.parseInt(road[2]);
            if (!graph.containsKey(origin)) {
                graph.put(origin, new HashMap<>());
            }
            graph.get(origin).put(destination, duration);
        }
        return graph;
    }

    private static void dfs(String location, Map<String, Map<String, Integer>> graph, Map<String, String> personLocation, Car car) {
        car.addPassenger(personLocation.get(location));
        if (location.equals("Campground")) {
            return;
        }
        if (graph.containsKey(location)) {
            Map<String, Integer> neighbors = graph.get(location);
            for (String neighbor : neighbors.keySet()) {
                int duration = neighbors.get(neighbor); //?
                if (isValidPassenger(neighbor, personLocation, car)) {
                    dfs(neighbor, graph, personLocation, car);
                    break;
                }
            }
        }
    }

    private static boolean isValidPassenger(String location, Map<String, String> personLocation, Car car) {
        for (String locationKey : personLocation.keySet()) {
            if (locationKey.equals(location) && !car.getPassengers().contains(personLocation.get((locationKey)))) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        List<String[]> roads1 = Arrays.asList(
                new String[]{"Bridgewater", "Caledonia", "30"},
                new String[]{"Caledonia", "New Grafton", "15"},
                new String[]{"New Grafton", "Campground", "5"},
                new String[]{"Liverpool", "Milton", "10"},
                new String[]{"Milton", "New Grafton", "30"}
        );

        List<String> starts1 = Arrays.asList("Bridgewater", "Liverpool");

        List<String[]> people1 = Arrays.asList(
                new String[]{"Jessie", "Bridgewater"},
                new String[]{"Travis", "Caledonia"},
                new String[]{"Jeremy", "New Grafton"},
                new String[]{"Katie", "Liverpool"}
        );

        Karat_CarPooling solution = new Karat_CarPooling();
        List<List<String>> result1 = solution.carpool(roads1, starts1, people1);
        System.out.println(result1);

        List<String[]> roads2 = Arrays.asList(
                new String[]{"Riverport", "Chester", "40"},
                new String[]{"Chester", "Campground", "60"},
                new String[]{"Halifax", "Chester", "40"}
        );

        List<String> starts2 = Arrays.asList("Riverport", "Halifax");

        List<String[]> people2 = Arrays.asList(
                new String[]{"Colin", "Riverport"},
                new String[]{"Sam", "Chester"},
                new String[]{"Alyssa", "Halifax"}
        );

        Karat_CarPooling solution2 = new Karat_CarPooling();
        List<List<String>> result2 = solution2.carpool(roads2, starts2, people2);
        System.out.println(result2);

        List<String[]> roads3 = Arrays.asList(
                new String[]{"Riverport", "Bridgewater", "1"},
                new String[]{"Bridgewater", "Liverpool", "1"},
                new String[]{"Liverpool", "Campground", "1"}
        );

        List<String> starts3_1 = Arrays.asList("Riverport", "Bridgewater");
        List<String> starts3_2 = Arrays.asList("Bridgewater", "Riverport");
        List<String> starts3_3 = Arrays.asList("Riverport", "Liverpool");

        List<String[]> people3 = Arrays.asList(
                new String[]{"Colin", "Riverport"},
                new String[]{"Jessie", "Bridgewater"},
                new String[]{"Sam", "Liverpool"}
        );
        Karat_CarPooling solution3 = new Karat_CarPooling();
        List<List<String>> result3_1= solution.carpool(roads3, starts3_1, people3);
        System.out.println(result3_1);

        List<List<String>> result3_2 = solution2.carpool(roads3, starts3_2, people3);
        System.out.println(result3_2);

        List<List<String>> result3_3 = solution3.carpool(roads3, starts3_3, people3);
        System.out.println(result3_3);
    }
}
