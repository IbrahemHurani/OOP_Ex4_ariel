import math
import unittest
from DiGraph import DiGraph
from GraphAlgo import GraphAlgo
def graph_creator():
    g = DiGraph()
    for i in range(50):
        g.add_node(i)
        g.add_edge(0,i,5)
        g.add_edge(i,0,8)
    for j in range(50):
        g.add_edge(1,j,6)
        g.add_edge(j,3,9)

    return g
class TestGraphAlgo(unittest.TestCase):
    def test_get_graph(self):
        g = graph_creator()
        graph=GraphAlgo(g)
        self.assertEqual(g,graph.get_graph())
    def test_shortest_path(self):
        g=graph_creator()
        graph=GraphAlgo(g)
        self.assertEqual((6,[1,5]),graph.shortest_path(1,5))
        self.assertEqual((13,[10,0,15]),graph.shortest_path(10,15))
    def test_TSP(self):
        g = graph_creator()
        graph=GraphAlgo(g)
        path=[2,0,5,4,3,9]
        self.assertEqual((path,56),graph.TSP([2,5,4,3,9,0]))
    def test_centerPoint(self):
        g=graph_creator()
        graph=GraphAlgo(g)
        self.assertEqual((0,5),graph.centerPoint())
if __name__ == '__main__':
    unittest.main()
