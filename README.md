# Heuristic algorithms for travelling salesman's problem
### Genetic Algorithms
- Generate random population of chromosomes
- Calculate fitness for each chromosome
- Repeat steps
  * Select pair of parents using a selection method
  * Generate a child via crossover on the parents with probability Pc
  * Mutate the child via swapping genes with probability Pm
- Replace current population with the new using elitism

#### Representation - TSP
- Each chromosome is a solution to the problem and is composed by a set of genes
- Each gene represent a city
- Each chromosome contains a gene exactly one time

#### Crossover
- Choose to parents from the old poluplation
  * Tournament selection (alt. roulette wheel, reward based)
- Randomly select part to be inherited from the first parent and complete the missing parts from the second parent

#### Mutation
- Depending on the mutation rate, there is a probability to swap two genes in every chromosome of the genetation
- Alternative use k-opt swap

### Ant Colony Optimization
- Generate a population of ant at starting point
- Move each ant until all reach targer point and return to starting point
- Ant dispose pheromone in there movement between to points
- Pheromone evaporates at each point. On the longer paths Pheromone is faster evaporated
- Generate new population and repeat

#### Representation - TSP
- Generate a population of ants setting as starting point a random city
- Move each ant on graph in order to include each city exactly, The selection of the city to visit is influenced by the pheromone on the connection and the distance between the two cities
- Update pheromone consist of two procedures
  * evaporate using a constant rate
  * desposit using each ant's total path distance as coefficient
- Generate new population and repeat

#### Next city selection
- Each ant calculated the probability to visit another (allowed) city at each step
  * based on pheromone already disposed
  * based on distance between the two cities
- At each selection there is small probability to bypass rules and choose randomly

#### Pheromone update
- At each edge 
  * Pheromone evaporates in constant rate
  * Pheromone being disposed is analogous to it's path distance (elitism is use here)
  
