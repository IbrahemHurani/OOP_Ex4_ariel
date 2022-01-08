package ex4_java_client;

import api.GeoLocation;

import core.Glocation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;
import java.util.ArrayList;

public class Agent {
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private GeoLocation pos;
    private boolean done;

    public Agent(int id, double value, int source, int dest, double speed, Glocation pos) {
        this.id = id;
        this.value = value;
        this.src = source;
        this.dest = dest;
        this.speed = speed;
        this.pos = pos;
        this.done=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean getdone(){
        return this.done;
    }
    public void setDone(boolean set){
        this.done=set;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getSrc() {
        return this.src;
    }

    public void setSrc(int Newsrc) {
        this.src= Newsrc;
    }

    public int getDest() {
        return this.dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GeoLocation getPos() {
        return this.pos;
    }

    public void setPos(Glocation pos) {
        this.pos = pos;
    }

    public  static ArrayList<Agent> loadAgent(String file) {
        ArrayList<Agent> a = new ArrayList<>();
        try {
            JSONObject obj=new JSONObject(file);
            JSONArray arr=obj.getJSONArray("Agents");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject agent=arr.getJSONObject(i).getJSONObject("Agent");
                int id=agent.getInt("id");
                double value=agent.getDouble("value");
                int src=agent.getInt("src");
                int dest=agent.getInt("dest");
                double speed=agent.getDouble("speed");
                String pos=agent.get("pos").toString();
                String[] location=pos.split(",");
                Glocation l=new Glocation(Double.parseDouble(location[0]),Double.parseDouble(location[1]),Double.parseDouble(location[2]));
                a.add(new Agent(id,value,src,dest,speed,l));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return a;
    }



}
