package data.util;

import java.util.*;

 public final class MapComparator {

    public static Map<Integer, Integer> getMaxValue(final Map<Integer, Integer> inputMap) {
        ArrayList<Map.Entry<Integer, Integer>> sortedArray = new ArrayList<>();
        for (Map.Entry<Integer, Integer> eachINput : inputMap.entrySet()) {
            sortedArray.add(eachINput);
        }

        Comparator<Map.Entry<Integer, Integer>> sortComparator = new Comparator<Map.Entry<Integer, Integer>>() {

            @Override
            public int compare(final Map.Entry<Integer, Integer> e1, final Map.Entry<Integer, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                return v2.compareTo(v1);
            }
        };

        Collections.sort(sortedArray, sortComparator);
        int maxValue = -1;
        Map<Integer, Integer> maxMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> max : sortedArray) {
            if (max.getValue() >= maxValue) {
                maxValue = max.getValue();
                maxMap.put(max.getKey(), max.getValue());
            } else {
                break;
            }
        }
        return maxMap;
    }

    private MapComparator() {
    }
}
