package strategies.giftsDistribution;

import entities.Child;
import entities.Santa;
import enums.Cities;
import interfaces.DistributeGiftsStrategy;
import simulation.Actions.DistributeGifts;

import java.util.*;
import java.util.stream.Collectors;

public final class NiceScoreCityDistributionStrategy implements DistributeGiftsStrategy {
    private List<Child> currentRoundChildren;
    private Santa santa;
    private Double averageCity = 0.0;
    private Map<String, Double> averageScoreMap = new HashMap<>();

    public NiceScoreCityDistributionStrategy(List<Child> currentRoundChildren, Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    @Override
    public void distributeGifts() {
        /*
        * Map that stores the name of the city
        * and the niceScores of the children from that city
        */
        Map<String, List<Double>> citiesScores= new HashMap<>();
        for (Child child : currentRoundChildren) {
            if (!citiesScores.containsKey(child.getCity().toString())) {
                citiesScores.put(child.getCity().toString(), new LinkedList<>());
            }
            citiesScores.get(child.getCity().toString()).add(child.getAverageScore());
        }

        citiesScores.forEach((cities, doubles) -> {
            doubles.forEach(score -> averageCity += score);

            averageScoreMap.put(cities, averageCity / doubles.size());
            averageCity = 0.0;
        });

        List<String> sortedCitiesList = averageScoreMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()
                        .thenComparing(Map.Entry.<String, Double>comparingByKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Child> children = new ArrayList<>();
        for (String cities : sortedCitiesList) {
            for (Child child : currentRoundChildren) {
                if (child.getCity().toString().equals(cities)) {
                    children.add(child);
                }
            }
        }

        /* Distribute gifts for each child */
        DistributeGifts updateReceivedGifts =
                new DistributeGifts(children, santa);
        updateReceivedGifts.update();

        currentRoundChildren = children.stream()
                .sorted(Comparator.comparing(Child::getId))
                .collect(Collectors.toList());
    }
}
