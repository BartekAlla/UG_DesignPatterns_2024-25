package pl.edu.ug.thread_builder.computation;

public class CylinderVolume extends ComputationObject<CylinderVolume> {
    private final double r, h;

    public CylinderVolume(double r, double h) {
        this.r = r;
        this.h = h;
    }
    public CylinderVolume(CylinderVolume target) {
        this.r = target.r;
        this.h = target.h;
    }
    @Override
    public CylinderVolume clone() {
        return new CylinderVolume(this);
    }

    @Override
    public double compute() {
        return Math.PI * r * r * h;
    }
}
