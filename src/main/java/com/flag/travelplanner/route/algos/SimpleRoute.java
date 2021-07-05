package com.flag.travelplanner.route.algos;


import java.util.*;

public class SimpleRoute implements RouteAlgorithm{
    private static class Pair {
        int key;
        double value;
        Pair(int key, double value) {
            this.key = key;
            this.value = value;
        }
    }
    private  List<List<Integer>> POIArrangement;
    @Override
    public List<List<Integer>> generateRoute(double[][] timeMatrix, double maxAllowableHours) {
        POIArrangement = new ArrayList<>();
        if (timeMatrix.length == 0) return POIArrangement;
        PriorityQueue<Pair> minTime = new PriorityQueue<>(Comparator.comparingDouble(x -> x.value));
        for (int i = 1; i < timeMatrix.length-1; i++){
            minTime.add(new Pair(i, timeMatrix[0][i]));
        }
        List<Integer> poiList = new ArrayList<>();
        while(!minTime.isEmpty()) {
            poiList.add(minTime.poll().key);
        }
        POIArrangement.add(poiList);
        return POIArrangement;
    }
}
