package com.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City {

    int id;
    private String name;
    private Map<Integer, Integer> neighbors = new HashMap<>();

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addNeighbor(int index, int cost) {
        neighbors.put(index, cost);
    }

    public Map<Integer, Integer> getNeighbors() {
        return neighbors;
    }

    public int[] getArrayNighborsId() {
        int[] array = new int[this.getNeighbors().size()];
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : this.getNeighbors().entrySet()) {
            array[count] = entry.getKey();
            count++;
        }
        return array;
    }

    public int getCost(int index) {
        if (this.getNeighbors().get(index) == null) {
            return -1;
        }
        return this.getNeighbors().get(index);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (this.getName().equals(other.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "City{" + "name=" + name + ", neighbors=" + neighbors + '}';
    }
}
