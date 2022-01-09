import heapq
import json
import math
import random
from typing import List
from src.GraphAlgoInterface import GraphInterface
from src.GraphAlgoInterface import GraphAlgoInterface
from core.DiGraph import DiGraph
from core.Node import Node
import matplotlib.pyplot as plt


class GraphAlgo(GraphAlgoInterface):

    def __init__(self, g: GraphInterface = None):
        self.graph = g

    # this function return the graph
    def get_graph(self) -> GraphInterface:
        return self.graph

    # this function read the file json and input to class
    def load_from_json(self, file_name: str) -> bool:
        load_graph = DiGraph()
        try:
            with open(file_name, 'r') as f:
                dict_algo = json.load(f)
                nodes = dict_algo["Nodes"]

                if isinstance(nodes, list):
                    for i in nodes:
                        n = Node(**i)
                        load_graph.add_node(n.id, n.pos)

                    edges = dict_algo["Edges"]
                    for i in edges:
                        load_graph.add_edge(id1=i['src'], id2=i['dest'], weight=i['w'])

                elif isinstance(nodes, dict):
                    for k, v in nodes.items():
                        n = Node(**v)
                        load_graph.add_node(n.id, n.pos)
                    edges = dict_algo["Edges"]
                    for k, v in edges.items():
                        for i in v.keys():
                            load_graph.add_edge(int(k), int(i), float(v.get(i)))

            self.graph = load_graph
        except IOError as e:
            print(e)
            return False
        return True

    # this function to save the file json
    def save_to_json(self, file_name: str) -> bool:
        try:
            with open(file_name, 'w') as fp:
                json.dump(self.graph, default=lambda m: m.__dict__, fp=fp, indent=4)
        except IOError as e:
            print(e)
            return False
        return True

    # this function return the short path in the graph and the dest for the this path
    def shortest_path(self, id1: int, id2: int) -> (float, list):
        unseen_nodes = self.graph.get_all_v()
        if id1 not in unseen_nodes or id2 not in unseen_nodes:
            return math.inf, []

        track_predecessor = {id1: -1}
        shortest_distance = {}

        # Setting the Dist to infinity
        for node in unseen_nodes.keys():
            shortest_distance[node] = math.inf
        shortest_distance[id1] = 0

        q = []
        heapq.heappush(q, (0, id1))
        while q:
            v = heapq.heappop(q)[1]
            for u, w in self.graph.all_out_edges_of_node(v).items():
                if shortest_distance[u] > shortest_distance[v] + w:
                    shortest_distance[u] = shortest_distance[v] + w
                    track_predecessor[u] = v
                    heapq.heappush(q, (shortest_distance[u], u))
            if v == id2:
                break
        path = []
        if shortest_distance[id2] == math.inf:
            return shortest_distance[id2], path

        p = id2
        while p != -1:
            path.insert(0, p)
            p = track_predecessor[p]
        return shortest_distance[id2], path

    # this function return the graph center in the graph
    def centerPoint(self) -> (int, float):
        center = -1
        max = math.inf
        d = 0

        for i in self.graph.get_all_v().keys():
            check = 0

            for j in self.graph.get_all_v().keys():

                dist, list = self.shortest_path(i, j)

                if dist > check:
                    check = dist

            if check < max:
                max = check
                d = check
                center = i

        return center, d

    # this function for travelling salesman problem
    def TSP(self, node_lst: List[int]) -> (List[int], float):
        if node_lst is None:
            return None, -1
        path = []
        List = node_lst
        indexForlist = 0
        DestThePath = 0
        src = List[indexForlist]
        indexForlist += 1
        while indexForlist < len(node_lst):
            dest = List[indexForlist]
            list_shortPath = self.shortest_path(src, dest)[1]  # this the path from src to dest
            DestThePath = DestThePath + self.shortest_path(src, dest)[0]  # the for the dest for the path from src to dest
            src = dest
            indexForlist += 1
            for i in list_shortPath:
                if i not in path:
                    path.append(i)
        return path, DestThePath

    # this function to draw all the graph
    def plot_graph(self) -> None:
        if self.graph.v_size() > 9:
            head_p = 0.00025
            wid_p = 0.00001
        else:
            head_p = 0.2
            wid_p = 0.001

        all_nodes = self.graph.get_all_v()
        for k, v in all_nodes.items():
            if v.pos is None:
                x_ran = random.uniform(0.5, self.graph.v_size())
                y_ran = random.uniform(0.5, self.graph.v_size())
                v.pos = (x_ran, y_ran)

        x_val = []
        y_val = []
        n = list(all_nodes.keys())
        for k, v in all_nodes.items():  # draw nodes
            x_val.append(v.pos[0])
            y_val.append(v.pos[1])

        fig, ax = plt.subplots()  # draw id nodes
        ax.scatter(x_val, y_val)
        for i, txt in enumerate(n):
            ax.annotate(txt, (x_val[i] + 0.00003, y_val[i] + 0.0003))

        for n in all_nodes.values():
            x = n.pos[0]
            y = n.pos[1]
            for k in self.graph.all_out_edges_of_node(n.id).keys():  # draw edges
                dx = self.graph.get_node(k).pos[0]
                dy = self.graph.get_node(k).pos[1]
                plt.arrow(x, y, dx - x, dy - y, head_width=head_p, length_includes_head=True, width=wid_p)
        plt.plot(x_val, y_val, 'or')
        plt.show()
