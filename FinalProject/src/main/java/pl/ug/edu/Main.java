package pl.ug.edu;

import pl.ug.edu.mission.Mission;
import pl.ug.edu.traits.TraitType;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<TraitType, Double> req = Map.of(
                TraitType.POWER, 400.0,
                TraitType.ENERGY, 600.0,
                TraitType.INTELLIGENCE, 300.0
        );

        Mission destroyReactor = new Mission("Destroy Reactor", req);

        System.out.println(destroyReactor);
    }
}
