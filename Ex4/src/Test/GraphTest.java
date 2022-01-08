package Test;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;
import core.Glocation;
import core.Graph;
import core.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
   DirectedWeightedGraph g=new Graph();
   int sizeForItrNode;
    @BeforeEach
    void testBuild() {
        g =new Graph();
        for (int i=0;i<10;i++){
            GeoLocation geo=new Glocation(i,i*2,i*3);
            NodeData n = new Node(i,geo);
            g.addNode(n);
        }
        g.connect(0,1,3.5);
        g.connect(0,8,4.4);
        g.connect(9,0,6.1111);
        g.connect(1,5,2.5);
        g.connect(1,4,7.11155);
        g.connect(4,3,1.2545);
        g.connect(2,4,14.222);
        g.connect(3,2,2.125);
        g.connect(7,6,4.225);
        g.connect(6,7,7.36544);
        g.connect(5,7,1.0001);
        g.connect(8,5,10.2);

    }

    @Test
    void testGetNode() {
        int key=(int)(Math.random()*10);
        NodeData n = g.getNode(key);
        assertEquals(n.getKey(), key);
        assertNull(g.getNode(-1));
    }

    @Test
    void testGetEdge() {
        EdgeData e = g.getEdge(2,4);
        assertEquals(14.222,e.getWeight());
        EdgeData e1 = g.getEdge(4,2);
        assertNull(e1);
        EdgeData e3 = g.getEdge(4,13);
        assertNull(e3);
    }

    @Test
    void addNode() {
        NodeData node1 = g.getNode(5);
        NodeData node2 = new Node(10,new Glocation(5,1,2));
        NodeData node3 = new Node(12,new Glocation(4,2,7));
        NodeData node4=g.getNode(4);
        NodeData node5=new Node(11,new Glocation(3,2,7));
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node2);
        g.addNode(node3);
        g.addNode(node4);
        g.addNode(node5);
        assertEquals(13, g.nodeSize());
    }

    @Test
    void connect() {
        g.connect(1,2,5.122);
        g.connect(8,5,7.212225);
        assertEquals(13, g.edgeSize());
        assertEquals(5.122, g.getEdge(1,2).getWeight());
        assertNotEquals(42.2,g.getEdge(5,7).getWeight());
    }

    @Test
    void removeNode() {
        g.removeNode(7);
        assertEquals(null,g.getNode(7));
        NodeData n=g.getNode(4);
        assertEquals(n,g.removeNode(4));
        n=g.getNode(9);
        assertEquals(n,g.removeNode(9));
        assertEquals(null,g.getNode(17));
        assertEquals(7,g.nodeSize());
    }

    @Test
    void removeEdge() {
        g.removeEdge(0,1);
        g.removeEdge(0,0);
        g.removeEdge(0,8);
        g.removeEdge(7,6);
        assertEquals(9, g.edgeSize());
        EdgeData e=g.getEdge(5,7);
        assertEquals(e,g.removeEdge(5,7));
        e=g.getEdge(8,5);
        assertEquals(e,g.removeEdge(8,5));

    }

}