
from core.GraphAlgo import GraphAlgo
import sys
if __name__ == '__main__':
    graph=GraphAlgo()
    if(not graph.load_from_json(sys.argv[1])):
        print("Error check your file")
    else:
        graph.plot_graph()