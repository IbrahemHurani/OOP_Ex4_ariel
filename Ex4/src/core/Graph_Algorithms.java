package core;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Graph_Algorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    final int INFINITE = Integer.MAX_VALUE;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        if (graph == null)
            return null;
        DirectedWeightedGraph NewCopy = new Graph();
        Iterator<NodeData> node = this.graph.nodeIter();
        while (node.hasNext()) {
            NodeData n = node.next();
            NewCopy.addNode(n);
        }
        while (node.hasNext()) {
            Iterator<EdgeData> edge = this.graph.edgeIter(node.next().getKey());
            if (edge != null) {
                while (edge.hasNext()) {
                    NewCopy.connect(edge.next().getSrc(), edge.next().getDest(), edge.next().getWeight());
                }
            }
        }


        return NewCopy;
    }


    @Override
    public boolean isConnected() {
        Iterator<NodeData> vertex = this.graph.nodeIter();
        while (vertex.hasNext()) {
            int src = vertex.next().getKey();
            while (vertex.hasNext()) {
                int dest = vertex.next().getKey();
                if (shortestPathDist(src, dest) == INFINITE) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        Iterator<NodeData> vertex = this.graph.nodeIter();
        if (this.graph.getNode(src) == null || this.graph.getNode(dest) == null) {
            return -1;
        }
        while (vertex.hasNext()) {
            NodeData v = vertex.next();
            v.setTag(0);
            v.setInfo("");
            v.setWeight(INFINITE);
        }
        this.graph.getNode(src).setWeight(0);
        for (int i = 1; i <= this.graph.nodeSize(); ) {
            int min = findMinNode();
            Iterator<EdgeData> edge = this.graph.edgeIter(this.graph.getNode(min).getKey());
            if (edge != null) {
                while (edge.hasNext()) {
                    EdgeData e = edge.next();
                    double currentWeight = this.graph.getNode(e.getDest()).getWeight();
                    double srcPlusEdge = this.graph.getNode(min).getWeight() + e.getWeight();
                    if (srcPlusEdge < currentWeight) {
                        this.graph.getNode(e.getDest()).setWeight(srcPlusEdge);
                        this.graph.getNode(e.getDest()).setInfo(this.graph.getNode(min).getInfo() + min + ",");
                    }
                }
            }
            this.graph.getNode(min).setTag(1);
            i++;
        }
        return this.graph.getNode(dest).getWeight();

    }

    private int findMinNode() {
        Iterator<NodeData> vertex = this.graph.nodeIter();
        double minWeight = INFINITE;
        int id = 1;
        while (vertex.hasNext()) {
            NodeData v = vertex.next();
            if (v.getWeight() <= minWeight && (v.getTag() == 0)) {
                minWeight = v.getWeight();
                id = v.getKey();
            }
        }
        return id;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> sPath = new ArrayList<NodeData>();
        double path = shortestPathDist(src, dest);
        if (path == -1) {
            return null;
        }
        String ans = this.graph.getNode(dest).getInfo();
        ans += this.graph.getNode(dest).getKey();
        String[] node = ans.split(",");
        for (int i = 0; i < node.length; i++) {
            int key = Integer.parseInt(node[i]);
            sPath.add(this.graph.getNode(key));
        }
        return sPath;
    }

    @Override
    public NodeData center() {
        if (isConnected()) {
            HashMap<Integer, Double> CenterG = new HashMap<>();
            for (int i = 0; i < this.graph.nodeSize(); i++) {
                CenterG.put(i, 0.0);
                for (int j = 0; j < this.graph.nodeSize(); j++) {
                    double d = shortestPathDist(i, j);
                    if (CenterG.get(i) < d) {
                        CenterG.put(i, d);

                    }

                }

            }
            int key=0;
            double min = Integer.MAX_VALUE;
            for (int i = 0; i < this.graph.nodeSize(); i++) {
                if(CenterG.get(i)<min){
                    min=CenterG.get(i);
                    key=i;
                }
            }
            return this.graph.getNode(key);
        }

        return null;
    }



    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(cities.isEmpty())
            return null;
        int indexForList=0;
        List<NodeData> t=new ArrayList<>();
        NodeData src=cities.get(indexForList++);
        cities.remove(0);
        while(indexForList<cities.size()){
            NodeData dest=cities.get(indexForList++);
            if(shortestPath(src.getKey(),dest.getKey())==null){return null;}
            List<NodeData> path=shortestPath(src.getKey(),dest.getKey());
            if(indexForList!=2)
                path.remove(path.get(0));
            t.addAll(path);
            src=dest;
        }
        return t;
    }


    @Override
    public boolean save(String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(graph);
        try {
            PrintWriter w = new PrintWriter(new File(file));
            w.write(json);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean load(String file) {
        try {
            DirectedWeightedGraph newGraph = Ex2.getGrapg(file);
            init(newGraph);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
