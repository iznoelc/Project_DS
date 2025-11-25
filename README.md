# Data Structures Project
Final Project for Data Structures

**TEAM :** Izzy Carlson, Landon Chapin, Esperanza Paulino

# Instructions to run code
**IDE:** We used Intellij for our IDE. You should be able to, once this folder is unzipped, select this project folder and open it in Intellij. We are using Open OracleJDK 25.

# Limitations
We used the **BlumBlumShub** algorithm to randomly generate the graph's node's values and the node's weights. 

However, we were unable to determine a way to cap the BlumBlumShub number generation. We needed to be able to force numbers

between a certain range when generating nodes (the graph has a minimum of 3 nodes and a maximum of 15) and edges (each 

vertex can have up to 3 edges). Therefore, we used **java.util.random** to generate these numbers.