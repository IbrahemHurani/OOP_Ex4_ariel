import math
import unittest
from DiGraph import DiGraph
from GraphAlgo import GraphAlgo
import sys

#I do this Test on file A5.json
def graph_creator():
    g = GraphAlgo()
    File = 'C:\\Users\\HP\\PycharmProjects\\pythonProject7\\data\\A5.json'  # you need to put your path for the file
    g.load_from_json(File)
    return g.get_graph()


class TestGraphAlgo(unittest.TestCase):
    def test_load_from_json(self):
        Graph = GraphAlgo()
        self.assertEqual(True, Graph.load_from_json('C:\\Users\\HP\\PycharmProjects\\pythonProject7\\data\\A5.json'))
        # you need to put your path for the file

    def test_save_to_json(self):
        Graph = GraphAlgo()
        self.assertEqual(True, Graph.load_from_json('C:\\Users\\HP\\PycharmProjects\\pythonProject7\\data\\A5.json'))
        # you need to put your path for the file

    def test_shortest_path(self):
        g = graph_creator()
        graph = GraphAlgo(g)
        self.assertEqual((3.8385038348753886, [1, 9, 11, 13, 5]), graph.shortest_path(1, 5))
        self.assertEqual((5.458538484258919, [10, 11, 13, 14, 15]), graph.shortest_path(10, 15))

    def test_TSP(self):
        g = graph_creator()
        graph = GraphAlgo(g)
        path = [2, 3, 13, 5, 4, 9, 0]
        self.assertEqual((path, 10.146375874326434), graph.TSP([2, 5, 4, 3, 9, 0]))

    def test_centerPoint(self):
        g = graph_creator()
        graph = GraphAlgo(g)
        self.assertEqual((40, 9.291743173960954), graph.centerPoint())


if __name__ == '__main__':
    unittest.main()
