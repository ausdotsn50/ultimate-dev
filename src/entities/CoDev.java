package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import layout.design.LoadDev;

public class CoDev {

    private int coDevNum = 7;
    private Random random = new Random();

    public double[] accuracy = {0.40, 0.50, 0.55, 0.60, 0.65, 0.70, 0.75};

    public List<LoadDev> devList = new ArrayList<>();

    public CoDev() {
        loadDevs();
    }

    public void loadDevs() {
        for (int i = 0; i < coDevNum; i++) {
            String path = "/image/codev/" + (i + 1) + ".png";
            LoadDev dev = new LoadDev(path, accuracy[i]);
            devList.add(dev); 
        }
    }

    public void createDevs(double[] accuracy) {
        devList.clear();  

        for (int i = 0; i < coDevNum; i++) {
            double acc = accuracy[i];
            String path = "/image/codev/" + (i + 1) + ".png";
            LoadDev dev = new LoadDev(path, acc);
            devList.add(dev);
        }
    }

    public LoadDev getRandomDev() {
        return devList.get(random.nextInt(devList.size()));
    }
}
