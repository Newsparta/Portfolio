This is a simple simulation for the survival of members of a small society. The intention is to determine what the value of working
together may be. Each person has unique attributes that contributes to their own personal ability and contribution to the whole. 
This simulation is intened to be run in anarchy or cooperative mode to compare results.

To run execute the following java command
 *  'java Simulation [Mode] [Simulation Runs]'
 *      Arguments:
 *          Mode (Optional) - 0 for Society mode, 1 for Anarchy mode, defaults to 0.
 *          Simulation Runs (Optional) - 0 for single full output run, any other number 
 *                                       to specify a number of simulations to perform
 *                                       and average the results. defaults to 0.


    There are four people in the simulation
    Each person has three attributes: health, food, and shelter
    Each attribute begins with a value of 10
    No attribute can go below 0 and only food can go above 10
    Each round of the simulation is called a day
    Each day every person consumes 1 unit of food
    Each day there is a 4 in 5 chance of a disaster
    There are four disasters: hurricanes, famine, disease, and wolves
    Each disaster negatively affects one or more of the attributes of each person
    Each person has a skill which positively affects one of the attributes