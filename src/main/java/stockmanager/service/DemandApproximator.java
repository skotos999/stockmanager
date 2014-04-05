package stockmanager.service;

public class DemandApproximator {

    public DemandApproximator() {

    }

    public double approximate(int[] demandHistory) {
        double result = 0;
        for (int demand : demandHistory) result += demand;
        return result / demandHistory.length;
    }
}
