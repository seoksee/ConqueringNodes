Concurrent Programming Assignment

Description
1. You are required to build a simple game using multithreading. 
2. The game (your program) accepts three arguments, n, t and m, where n >> t. 
3. After receiving the arguments, the program will sequentially create n random points. 
4. The points are floating point (floats or doubles) coordinates in a 1000 x 1000 region. 
Note: NO two points should overlap. 
5. Each point is represented as an object containing the coordinates.

6. After that, your program will launch t threads. Each thread will randomly pick a pair of points to add an edge between them. 
7. Use an appropriate data structure to store the pair of connected points. 
8. However, NO point should be connected with more than one other point, i.e. each point could be used to form at most one edge (locking needed when forming an edge).
9. Exit Criteria: The program will run for m seconds, or when any one thread has failed to form a single edge after 20 attempts. 
10. Display the number of edges each thread was successfully created.

