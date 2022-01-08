package core;

import api.GeoLocation;
import api.NodeData;

public class Node implements NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;

    public Node(int id,GeoLocation geo){
        this.key=id;
        this.location=new Glocation(geo);
        this.tag=0;
        this.weight=0;
        this.info="white";

    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
            this.location=p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
            this.info=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
            this.tag=t;
    }
}

