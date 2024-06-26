package strategies.giftsDistribution;

import entities.Child;
import entities.Santa;
import interfaces.DistributeGiftsStrategy;
import simulation.Actions.DistributeGifts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class NiceScoreCityDistributionStrategy implements DistributeGiftsStrategy {
    private List<Child> currentRoundChildren;
    private final Santa santa;
    private Double averageCity = 0.0;
    /* Stores the averageScore for every city */
    private final Map<String, Double> averageScoreMap = new HashMap<>();

    public NiceScoreCityDistributionStrategy(final List<Child> currentRoundChildren,
                                             final Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    @Override
    public void distributeGifts() {
        /*
        * Map that stores the name of the city
        * and the niceScores of the children from that city
        */
        Map<String, List<Double>> citiesScores = new HashMap<>();
        for (Child child : currentRoundChildren) {
            if (!citiesScores.containsKey(child.getCity().toString())) {
                citiesScores.put(child.getCity().toString(), new LinkedList<>());
            }
            citiesScores.get(child.getCity().toString()).add(child.getAverageScore());
        }

        /* Compute average score for each city */
        citiesScores.forEach((cities, doubles) -> {
            doubles.forEach(score -> averageCity += score);

            averageScoreMap.put(cities, averageCity / doubles.size());
            averageCity = 0.0;
        });

        /* Sort the cities by the average score and in case of equality sort them by the name */
        List<String> sortedCitiesList = averageScoreMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()
                        .thenComparing(Map.Entry.<String, Double>comparingByKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        /*
        * Create a list in which children are added in the descending
        * order of the average score of the city they live in
        */
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

        /* Sort children by the id */
        currentRoundChildren = children.stream()
                .sorted(Comparator.comparing(Child::getId))
                .collect(Collectors.toList());
    }
}
