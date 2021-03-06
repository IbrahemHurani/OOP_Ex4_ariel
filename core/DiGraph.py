from src.GraphInterface import GraphInterface
from core.Node import Node


class DiGraph(GraphInterface):

    def __init__(self):
        self.Nodes = {}
        self.Edges = {}
        self.in_edges = {}
        self.mc = 0
        self.size_of_nodes = 0
        self.size_of_edges = 0

    def v_size(self) -> int:
        return self.size_of_nodes

    def e_size(self) -> int:
        return self.size_of_edges

    def get_mc(self) -> int:
        return self.mc

    def get_all_v(self) -> dict:
        return self.Nodes

    def all_in_edges_of_node(self, id1: int) -> dict:
        return self.in_edges.get(id1)

    def all_out_edges_of_node(self, id1: int) -> dict:
        return self.Edges.get(id1)

    def add_edge(self, id1: int, id2: int, weight: float) -> bool:
        if self.Nodes.__contains__(id1) and self.Nodes.__contains__(id2) and id1 != id2 and not self.has_edge(id1, id2):
            self.Edges[id1][id2] = weight
            self.in_edges[id2][id1] = weight
            self.size_of_edges += 1
            self.mc += 1
            return True
        return False

    def add_node(self, node_id: int, pos: tuple = None) -> bool:
        if self.Nodes.__contains__(node_id):
            return False
        self.Nodes[node_id] = Node(node_id, pos=pos)
        self.in_edges[node_id] = {}
        self.Edges[node_id] = {}
        self.size_of_nodes += 1
        self.mc += 1

        return True

    def remove_node(self, node_id: int) -> bool:
        if self.Nodes.__contains__(node_id):
            out = self.all_out_edges_of_node(node_id)
            for i in out:
                self.in_edges[i].pop(node_id)
            self.Edges.pop(node_id)
            self.size_of_edges -= out.__len__()
            self.mc += out.__len__()

            inside = self.all_in_edges_of_node(node_id)
            for i in inside:
                self.Edges[i].pop(node_id)
                self.size_of_edges -= 1
                self.mc += 1

            self.in_edges.pop(node_id)
            self.Nodes.pop(node_id)
            self.size_of_nodes -= 1
            self.mc += 1
            return True

        return False

    def remove_edge(self, node_id1: int, node_id2: int) -> bool:
        if self.has_edge(node_id1, node_id2):
            self.Edges[node_id1].pop(node_id2)
            self.in_edges[node_id2].pop(node_id1)
            self.size_of_edges -= 1
            self.mc += 1
            return True
        return False

    def has_edge(self, id1, id2):
        if self.Edges[id1].__contains__(id2):
            return True
        else:
            return False

    def get_node(self, id1):
        if self.Nodes.__contains__(id1):
            return self.Nodes[id1]
        else:
            return None
