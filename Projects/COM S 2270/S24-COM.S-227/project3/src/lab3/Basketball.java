package lab3;

public class Basketball {
    private double diameter;
    private boolean inflated;

    public Basketball(double diameter) {
        this.diameter = diameter;
        this.inflated = false;
    }

    public boolean isDribbleable() {
        return inflated;
    }

    public void inflate() {
        inflated = true;
    }

    public double getDiameter() {
        return diameter;
    }
}
