package ex4_java_client;

import api.EdgeData;
import api.GeoLocation;
import core.Glocation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class Pokemon {
    private double value;
    private int type;
    private GeoLocation location;
    private EdgeData edge;
    private double distance;

    public Pokemon(double value, int type, GeoLocation pos) {
        this.value = value;
        this.type = type;
        this.location = pos;
        this.distance = 0;

    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation Newlocation) {
        this.location = Newlocation;
    }

    public EdgeData getEdge() {
        return edge;
    }

    public void setEdge(EdgeData newEdge) {
        this.edge = newEdge;
    }
    public static ArrayList<Pokemon> loadPokemon(String file){
        ArrayList<Pokemon> pokemons=new ArrayList<>();
        try{
            JSONObject obj=new JSONObject(file);
            JSONArray arr=obj.getJSONArray("Pokemons");
            for(int i=0;i<arr.length();i++){
                JSONObject p=arr.getJSONObject(i).getJSONObject("Pokemon");
                double value=p.getDouble("value");
                int type=p.getInt("type");
                String pos=p.getString("pos");
                String[] loc=pos.split(",");
                GeoLocation Location=new Glocation(Double.parseDouble(loc[0]),Double.parseDouble(loc[1]),Double.parseDouble(loc[2]));
                pokemons.add(new Pokemon(value,type,Location));

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        pokemons.sort(new Pokemon.valueComp());
        return pokemons;
    }
    private static class valueComp implements Comparator<Pokemon> {
        public int compare(Pokemon o1, Pokemon o2) {
            if (o1.getValue()==o2.getValue())
                return 0;
            if (o1.getValue() < o2.getValue())
                return 1;
            return -1;
        }
    }
}
