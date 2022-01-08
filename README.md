Name: State Andreea-Rebecca

Group: 321CA



# Project - Santa Claus is coming to ACS students



<div align="center"><img src="https://tenor.com/view/santa-claus-is-coming-to-town-santa-claus-christmas-tree-merry-christmas-flying-gif-15754914.gif" width="300px"></div>



## Project Structure

### Packages content

* checker/ - checker files
* common/
    * Constants - constants used throughout the program
* database/
    * Database - class that stores all the data given as input
* entities/
    * Child - contains all the particularities of a child and a method
              that determines the age category of the child
    * Gift  - contains all the particularities of a gift
    * Santa - contains all the particularities of santa and has methods
              that are used in the gift's distribution action
* enums/
    * Cities   - enum with all the cities visited by santa of a producer
    * Category - enum with all the gift's categories
* factories/
    * ScoreStrategyFactory - class that implements Factory design pattern and
                            contains a method that creates custom strategies
* fileio/
    * input/  - contains classes used to read data from the json files
    * output/ - contains classes used to write data in json files
* interfaces/
    * ScoreStrategy - Interface for the Strategy Design Pattern
* main/
    * Main - Starting point of the program
* simulation/
    * actions/
        * CalculateBudget - class which contains method that computes
                            the budget for every child
        * UpdateChildrenInfo - class which contains method that updates the
                               information of each child
        * UpdateSantaInfo - class which contains method that updates
                            the information of santa
        * DistributeGifts - class which contains method that distributes gifts
                            to each child according to their gifts preferences
    * Simulation - implements methods for the initial and basic rounds
* strategies/ - Concrete classes that implement the Strategy interface and
                override method that computes the average score
  * BabyScoreStrategy
  * KidScoreStrategy 
  * TeenScoreStrategy

## Design Patterns

The input data is stored in the Database class on which I chose
to apply **the Singleton design pattern**, so that the class would
be instantiated only one time.

Because the average score of each child is calculated based on the age
category I chose to use **the Strategy design pattern** and created
3 different strategies (baby, kid and teen).
Each class implements the interface ScoreStrategy and overrides
the method computeAverageScore.

Because I have 3 different strategies, I created the ScoreStrategyFactory
class in which I used **the Factory design pattern** in order to instantiate
the different strategies.

## Simulation steps

### Initial Round (Round 0)
1. Determine the age category of each child
   * Baby: < 5 years old
   * Kid: 5 - 12 years old
   * Teen: 12 - 18 years old
   * Young Adult: > 18 years old
2. For each child store in a list the nice score for each child
3. Compute average score based on the age category of each child
    * Baby: average score is always  10
    * Kid:  average score is the average of all the nice scores from the
            child's nice scores list
    * Teen: average score is the weighted average of all the nice scores
            from the child's nice scores list
    * Young Adult: they don't receive gifts, so we won't calculate an 
                   average score for them anymore
4. Calculate the budget of every child

   budgetUnit = santaBudget / sum of all the children's average scores

   childBudget = averageScore * budgetUnit
5. Distribute gifts as follows
   * Santa goes through every child's wishlist and checks if he
     has a gift from that category in his gifts list
   * If the gift exists, santa will assign that gift only if the price
     of the gift is within the child's budget
   * If there are several gifts in the same category, Santa decides
     to give the child the gift with the lowest price

### Basic Round
1. Increment the age of every child and remove the children
   that now are over 18 years old
2. Santa reads the updates list from the current round:
   * adds the new children to the children list only
     if they are not Young Adults (< 18y)
   * update the children information using the childrenUpdates list
     * check if the id of the children is still in the santa's children list
       (if not, then that means the child became a Young Adult)
     * add the new nice score to the child's nice scores list only
       if the nice score is not null
     * add to the begging of the child's wishlist the new list of gifts
      categories he wishes to receive
   * add the new gifts to santa's gifts list
   * update santa's budget with the one given in the updates list
3. Repeat the steps from the initial round


<div align="center"><img src="https://tenor.com/view/macaulay-culkin-merry-christmas-ya-filthy-animal-gif-13110096.gif" width="300px"></div>
