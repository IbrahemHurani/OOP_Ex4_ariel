package GUI;



import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import ex4_java_client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUI_graph extends JFrame implements ActionListener {
    private Panel panel;
    private DirectedWeightedGraph graph;
    private DirectedWeightedGraphAlgorithms graphAlgo;
    private JMenuBar menu;
    private JMenu g;
    private JMenuItem connect;
    private JMenuItem removeNode;
    private JMenuItem removeEdge;
    private JMenuItem nodeSize;
    private JMenuItem edgeSize;

    private JMenu gAlgo;
    private JMenuItem isConnected;
    private JMenuItem shortestPathDist;
    private JMenuItem shortestPath;
    private JMenuItem center;
    private JMenuItem tsp;
    Client client;
    private JMenu ToFile;
    private JMenuItem save;
    private JMenuItem load;
    public GUI_graph(DirectedWeightedGraphAlgorithms ans,Client c) throws IOException {
        super();
        this.client=c;
        this.panel=new Panel(ans.getGraph(),c);
        this.graph=ans.getGraph();
        this.graphAlgo=ans;
        build();
        this.setResizable(true);
        this.setVisible(true);

    }
    private void build() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        this.g = new JMenu("Graph");
        this.connect = new JMenuItem("Connect Nodes in Graph");
        this.connect.addActionListener(this);
        this.removeNode = new JMenuItem("Remove Node in Graph");
        this.removeNode.addActionListener(this);
        this.removeEdge = new JMenuItem("Remove Edge in Graph");
        this.removeEdge.addActionListener(this);
        this.nodeSize = new JMenuItem("Size Of Nodes");
        this.nodeSize.addActionListener(this);
        this.edgeSize = new JMenuItem("Size Of Edges");
        this.edgeSize.addActionListener(this);

        this.gAlgo = new JMenu("Algorithm Graph");
        this.isConnected = new JMenuItem("Is Connected");
        this.isConnected.addActionListener(this);
        this.shortestPathDist = new JMenuItem("Shortest Path Dist");
        this.shortestPathDist.addActionListener(this);
        this.shortestPath = new JMenuItem("Shortest Path");
        this.shortestPath.addActionListener(this);
        this.center = new JMenuItem("Center");
        this.center.addActionListener(this);
        this.tsp = new JMenuItem("TSP");
        this.tsp.addActionListener(this);

        this.ToFile=new JMenu("File");
        this.save = new JMenuItem("Save with json");
        this.save.addActionListener(this);
        this.load = new JMenuItem("Load with json");
        this.load.addActionListener(this);

        this.gAlgo.add(isConnected);
        this.gAlgo.add(shortestPathDist);
        this.gAlgo.add(shortestPath);
        this.gAlgo.add(center);
        this.gAlgo.add(tsp);

        this.g.add(connect);
        this.g.add(removeNode);
        this.g.add(removeEdge);
        this.g.add(nodeSize);
        this.g.add(edgeSize);

        this.ToFile.add(save);
        this.ToFile.add(load);



        menu = new JMenuBar();
        menu.add(g);
        menu.add(gAlgo);
        menu.add(this.ToFile);
        setJMenuBar(menu);

        this.add(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

         if(e.getSource() == connect) {
             Connect();
        }
        else if(e.getSource() == removeNode) {
            RemoveNode();
        }
        else if(e.getSource() == removeEdge) {
             RemoveEdge();
        }
        else if (e.getSource() == nodeSize) {
            GetSizeNode();
         }
        else if(e.getSource() == edgeSize) {
           GetSizeEdge();
        }
        else if(e.getSource() == isConnected) {
                isconnected();
        }
        else if(e.getSource() == shortestPathDist) {
             ShortestPathDist();
        }
        else if(e.getSource() == shortestPath) {
             ShortestPath();
        }
        else if(e.getSource() == center) {
            Center();
        }
        else if(e.getSource() == tsp) {
            TSP();
        }
        else if(e.getSource() == save) {
             Save();
        }
        else if(e.getSource() == load) {
             Load();
        }
    }

    private void TSP() {
        JFrame frame=new JFrame();
        List<NodeData> t=new ArrayList<>();
        int Stop=0;
        try {
            while (Stop != 10) {
                String message = JOptionPane.showInputDialog(frame, "Enter the number for list");
                int key = Integer.parseInt(message);
                NodeData n = this.graph.getNode(key);
                t.add(n);
                Stop=Integer.parseInt(JOptionPane.showInputDialog(frame,"Enter 10 if you finish and 0 to continue"));
            }
            t=this.graphAlgo.tsp(t);
            int[] Nodekey=new int[t.size()];
            for(int i=0;i<t.size();i++){
                Nodekey[i]=t.get(i).getKey();
            }
            JOptionPane.showMessageDialog(frame, Arrays.toString(Nodekey));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ShortestPath() {
        JFrame frame=new JFrame();
        List<NodeData> l=null;
       String source=JOptionPane.showInputDialog(frame,"Enter source Node");
       String d=JOptionPane.showInputDialog(frame,"Enter Destination Node");
        try {
            int src=Integer.parseInt(source);
            int dest=Integer.parseInt(d);
            l=this.graphAlgo.shortestPath(src,dest);
            int[] Nodekey=new int[l.size()];
            for(int i=0;i<l.size();i++){
                Nodekey[i]=l.get(i).getKey();
            }
            JOptionPane.showMessageDialog(frame,"the path is"+Arrays.toString(Nodekey));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Load() {
        JFrame frame=new JFrame();
        String FileName=JOptionPane.showInputDialog(frame,"Enter Name for File");
        try {
            this.graphAlgo.load(FileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Save() {
        JFrame frame=new JFrame();
        String FileName=JOptionPane.showInputDialog(frame,"Enter Name for File");
        try {
            this.graphAlgo.save(FileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void ShortestPathDist() {
        JFrame frame=new JFrame();
        String source = JOptionPane.showInputDialog(frame,"Source Node");
        String dest = JOptionPane.showInputDialog(frame,"Destination Node");
        try{
            int src=Integer.parseInt(source);
            int d=Integer.parseInt(dest);
            JOptionPane.showMessageDialog(frame,"the short path dist is :"+this.graphAlgo.shortestPathDist(src,d));
            repaint();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    private void Center() {
        JOptionPane.showMessageDialog(new JFrame(), "The Center Node In The Graph is: " + graphAlgo.center().getKey(), "Center In Graph", JOptionPane.DEFAULT_OPTION);
    }

    private void GetSizeEdge() {
        JFrame  frame=new JFrame();
        JOptionPane.showMessageDialog(frame,"the size Edges in this graph is:"+graph.nodeSize(),"size Edges",JOptionPane.DEFAULT_OPTION);
    }

    private void GetSizeNode() {
        JFrame frame=new JFrame();
        JOptionPane.showMessageDialog(frame,"the size Node in this graph is:"+graph.nodeSize(),"size Node",JOptionPane.DEFAULT_OPTION);
    }

    private void isconnected() {
        JFrame frame=new JFrame();
        if(this.graphAlgo.isConnected())
        {
            String message="The graph is connected";
            JOptionPane.showMessageDialog(frame,message);
        }else{
            String message="The graph is not connected";
             JOptionPane.showMessageDialog(frame,message);
        }

    }

    private void RemoveEdge() {
        JFrame frame = new JFrame();
        String source = JOptionPane.showInputDialog(frame,"Source Node");
        String dest = JOptionPane.showInputDialog(frame,"Destination Node");

        try
        {
            int src = Integer.parseInt(source);
            int des = Integer.parseInt(dest);
            graph.removeEdge(src, des);
            this.graphAlgo.init(graph);
            repaint();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void RemoveNode() {
        JFrame frame=new JFrame();
        String key=JOptionPane.showInputDialog(frame,"Enter the key of Node you want to remove");
        try{
            int KeyNode=Integer.parseInt(key);
            this.graph.removeNode(KeyNode);
            this.graphAlgo.init(graph);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Connect() {
        JFrame frame = new JFrame();
        String source = JOptionPane.showInputDialog(frame,"Source Node");
        String dest = JOptionPane.showInputDialog(frame,"Destination Node");
        String weight = JOptionPane.showInputDialog(frame , "Weight of the Edge");
        try{
            int src = Integer.parseInt(source);
            int des = Integer.parseInt(dest);
            double wet = Double.parseDouble(weight);
            graph.connect(src , des, wet);
            this.graphAlgo.init(this.graph);
            repaint();
            this.add(panel);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



}

