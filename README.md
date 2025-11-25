# Data Structures Project
Final Project for Data Structures

**TEAM :** Izzy Carlson, Landon Chapin, Esperanza Paulino

# Instructions to run code
**IDE:** We used Intellij for our IDE. You should be able to, once this folder is unzipped, select this project folder and open it in Intellij. We are using Open OracleJDK 25.

# Limitations
1. We used the **BlumBlumShub** algorithm to randomly generate the graph's node's values and the node's weights. However, we were unable to determine a way to cap the BlumBlumShub number generation. We needed to be able to force numbers between a certain range when generating nodes (the graph has a minimum of 3 nodes and a maximum of 15) and edges (each vertex can have up to 3 edges). Therefore, we used **java.util.random** to generate these numbers.
2. Not necessarily a limitation, but a *notice*, when randomly generating graphs, we don't let nodes have paths to themselves. In reality, this could be the case, but in order to better display the cycle functionality, we do not allow it. This is because if a node has a path to itself, it is considered a cycle, and therefore, the findCycle method is prioritizing nodes with paths to themselves.

