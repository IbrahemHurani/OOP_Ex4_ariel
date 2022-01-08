package core;

import api.GeoLocation;

public class Glocation implements GeoLocation {
    private double x;
    private double y;
    private double z;
    public Glocation(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public Glocation(GeoLocation geo) {
        this.x=geo.x();
        this.y=geo.y();
        this.z= geo.z();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        double sum=Math.pow(g.x()-this.x,2)+Math.pow(g.y()-this.y,2)+Math.pow(g.z(),2);
        return Math.sqrt(sum);
    }
}
