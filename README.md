# Metal-Sheet-Heated-In-PC-Cluster


Project from a bit ago. Uses a metal sheet consisting of 3 different base metals.
When a corner of the sheet is heated, the surrounding parts of the sheet are also heated at various speeds depending on each sections metal composition.
Early versions of this worked by computing the heating of each section in "waves" using threads.
The current implementation works by running the program as a cluster on multiple computers. 
Firewall settings/permissions and router security needs to be set up correctly (full permissions) or it won't run.

S: initial temperature of top left corner
T: initial temperature of bottom right corner
A: each of the 3 base metals
C: thermal constant for metal A
N: the set of neighbouring regions
tempN:temperature of the neighbouring region
pmn: percentage of metal A in neighbor n
|N| number of neighboring regions

C1: .75
C2: 1.0
C3: 1.25


Client & Server: I was trying to get the server socket to work but I was having some issues. My thought process was to use
    serialization for the Model and its subclass and pass serialized objects of Model from the server to the socket class which
    would then perform the compute() method to calculate the temperature change for that sub-section. The socket would then pass
    the temperatures back to the server which would then be stored in the appropriate master Model index.
