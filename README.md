# OOP assignment 4:

![image](https://user-images.githubusercontent.com/86603326/148660545-c8aa9df8-93d7-41b1-a01c-50e61cfac18a.png)

# Explain about assignment:
This assignment about directed weighted graph and on the edges there is a pokemon and agents and we bulid algorthim for agent to Catch the pokemon fast.
# Algorithm:
we take the classes for bulid the graph and all the algorithm for the graph from assignment 2 you can found more information  about the graph in my [Readme](https://github.com/IbrahemHurani/OOP_Ex3_ariel) there .
and this assignment we added two classes:

1.class Client:

this class for do connect with the server and for the run file Ex4_Server_v0.0.jar.

2.class StudentCode:

this class work like a main is run the code and in this class have the algorthim for agent to catch the pokemon.

# How to catch the pokemon:
At first we will look for where the pokemon on which edges the pokemon is with this funcution :
```python
#this function return which node need the to choose to catch the pokemon through the postion of the pokemon.
def chooseNode(onePokemon):
     EPS = 0.000001
        for e in graph.Edges:
            # find the edge nodes
            src = next(n for n in graph.Nodes if n.id == e.src)
            dest = next(n for n in graph.Nodes if n.id == e.dest)

            src_x = my_scale(src.pos.x, x=True)
            src_y = my_scale(src.pos.y, y=True)
            dest_x = my_scale(dest.pos.x, x=True)
            dest_y = my_scale(dest.pos.y, y=True)

            d1 = (((((src_x - dest_x) ** 2) + ((src_y - dest_y) ** 2)) ** 0.5))
            d2 = (((((src_x - onePokemon.x) ** 2) + ((src_y - onePokemon.y) ** 2)) ** 0.5)) + (((((dest_x - onePokemon.x) ** 2) + ((dest_y - onePokemon.y) ** 2)) ** 0.5))
            dist=abs(d1-d2)/agent.speed
            if dist< EPS:
                return dest.id, src.id
```
and the brain function for agents to choose the path is :
```python
for agent in agents:
        if agent.dest == -1:
            min=100000000
            path = []
            path.append(agent.src)
            for p in pokemons:
                target = chooseNode(p.pos)
                dist,listPath = gAlgo.shortest_path(path[len(path) - 1], target[0])
                listPath.append(target[1])
                if dist<min:
                    min=dist
                    for k in listPath:
                        path.append(k)

            for i in path:
                next_node = i
                client.choose_next_edge(
                    '{"agent_id":' + str(agent.id) + ', "next_node_id":' + str(next_node) + '}')
            ttl = client.time_to_end()
            print(ttl, client.get_info())

    client.move()
 ```

# How Run the code:
1.install all the file form my github.

2.open the  Pycharm and put all the file in Pycharm

3.add all files jar to library.

4.open Terminal in the PycharmP and write in the terminal ->java -jar Ex4_Server_v0.0.jar [0-15] (you need to choose just one number).

![image](https://user-images.githubusercontent.com/86603326/148668312-d9793a00-af4b-406c-858d-3dd653b058e6.png)
 


5.run the class StudentCode.


# Screen Shot:
![image](https://user-images.githubusercontent.com/86603326/148668256-4ab72816-e779-43e8-935a-e7f655e17164.png)

# Video :
![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/86603326/148697508-d7824ea5-cdf8-4494-8acf-45e0d17e6701.gif)


# Diagram:
![image](https://user-images.githubusercontent.com/86603326/148668210-b0d4142b-75b6-48e1-ad42-ecc08db6c1dd.png)

# Result:

|case |  moves |  grade  |
|-----|--------|---------|
| 0   | 90     |   52    |
| 1   | 299    |   100   |
| 2   |  149   |   79    |
| 3   | 299    |   115   |
| 4   |  149   |    94   |
| 5   |  299   |  164    |
|  6  |  299   |  79     |
| 7   |   597  | 214     |
| 8   |  239   |   50    |
|9    |   350  |  214    |
| 10  |   230  |  100    |
| 11  |   220  |   150   |
|12   |  239   |   100   |
| 13  |  200   |  165    |
|14   |  150   |    120  |
|15   |  478   |  152    |

