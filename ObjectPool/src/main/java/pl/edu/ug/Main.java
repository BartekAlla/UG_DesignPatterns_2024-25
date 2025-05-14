package pl.edu.ug;

import pl.edu.ug.builder.ComputationThreadDirector;
import pl.edu.ug.computation.CylinderVolume;
import pl.edu.ug.computation.SphereSurfaceArea;
import pl.edu.ug.computation.CircleCircumference;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static int THREAD_COUNT = 50000;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting computation...");
    }
}
