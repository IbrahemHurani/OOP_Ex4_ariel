# OOP assignment 4:

![image](https://user-images.githubusercontent.com/86603326/148660545-c8aa9df8-93d7-41b1-a01c-50e61cfac18a.png)

# Explain about assignment:
This assignment about directed weighted graph and on the edges there is a pokemon and agents and we bulid algorthim for agent to Catch the pokemon fast.
# Algorithm:
we take the classes for bulid the graph and all the algorithm for the graph from assignment 2 you can found more information  about the graph in my [Readme](https://github.com/IbrahemHurani/OOP_Ex2_ariel) there .
and this assignment we added 4 classes:

1.Class pokemon:
 this class have this variables:
 ```java
 private double value;
 private int type;
 private GeoLocation location;
 private EdgeData edge;
 private double distance;
  ```
Method for this class:
```java
 public double getValue()//this function return the value for pokemon
 public void setValue(double value)//this fucntion to change the value for the pokemon
 public void setDistance(double distance)//this function to  change the distance for the pokemon
 public double getDistance()//this function to return the distance for the pokemon
 public int getType()//this fucntion to return the type
 public void setType(int type)//this function to change the type
 public GeoLocation getLocation()//this function to return the location
 public void setLocation(GeoLocation Newlocation)//this function to change the location
 public EdgeData getEdge()//this to return which edge the pokemon is.
 public void setEdge(EdgeData newEdge)//to change the edge.
 public static ArrayList<Pokemon> loadPokemon(String file)//this fucntion to read for json and input in Arraylist.
```
2.class Agents:
this class have this variables:
```java
private int id;
private double value;
private int src;
private int dest;
private double speed;
private GeoLocation pos;
private boolean done;
```
the methods for this class:
```java
 public int getId()//this function return the id for agent
 public void setId(int id)//this function to change the id
 public boolean getdone()//this fucntion return if the agent move or stop.
 public void setDone(boolean set)//this fucntion to change if the agent moved to change or if the agent is stoped.
 public double getValue()//this function to return the value
 public void setValue(double value)////this function to change the value
 public int getSrc()//this function to return the source for the agent
 public void setSrc(int Newsrc)//this function to change the source
 public int getDest()//this function to return the destination (where the agent go).
 public void setDest(int dest)///this function to to change the destination
 public double getSpeed()//this function to return the speed for agent
 public void setSpeed(double speed)//this function to change the speed
 public GeoLocation getPos()//this function to return the location for agent
 public void setPos(Glocation pos)//this function to change the location
 public  static ArrayList<Agent> loadAgent(String file)//this function to read form json(load) and input to Arraylist.
```
3.class Client:

this class for do connect with the server and for the run file Ex4_Server_v0.0.jar.

4.class StudentCode:

this class work like a main is run the code and in this class have the algorthim for agent to catch the pokemon.

# How Run the code:
1.install all the file form my github.

2.open the intellij and put all the file in intellij.

3.add all files jar to library.

4.open Terminal in the intellij and write in the terminal ->java -jar Ex4_Server_v0.0.jar 0-15(you need to choose just one number).

![image](https://user-images.githubusercontent.com/86603326/148661381-ab93e3f6-4367-4d18-a069-794251830cc1.png)


5.run the class StudentCode.


# Screen Shot:
![image](https://user-images.githubusercontent.com/86603326/148659216-6039e13c-eed4-4bec-bbbc-b503e9524852.png)



# Diagram:
![image](https://user-images.githubusercontent.com/86603326/148658859-704dd20d-bb12-45b3-91a7-f2bc2d94f223.png)
