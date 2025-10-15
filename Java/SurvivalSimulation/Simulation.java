import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *  Runs a simulation of the objectives of C1A
 * 
 *  Runs a simulation with a population of 4 people. each person is a unique skill that affects
 *  their ability to survive. This simulation has 2 modes, "Society" and "Anarchy" which also
 *  dictate what skill effects and disaster effects take place. The simulation ends after the
 *  entire population has died or it has reached 365 days. Each day there is a chance for a 
 *  disaster to happen to the population.
 * 
 *  To compile, navigate to the directory where the source files are located and run the following
 *  'javac Simulation.java'
 * 
 *  To run execute the following java command
 *  'java Simulation [Mode] [Simulation Runs]'
 *      Arguments:
 *          Mode (Optional) - 0 for Society mode, 1 for Anarchy mode, defaults to 0.
 *          Simulation Runs (Optional) - 0 for single full output run, any other number 
 *                                       to specify a number of simulations to perform
 *                                       and average the results. defaults to 0.
 * 
 * @author Rhett Edwards
 * @version Spring 2025
 */
public class Simulation {

    enum Disaster {
        HURRICANE, FAMINE, DISEASE, WOLVES
    }
    enum Mode {
        SOCIETY, ANARCHY
    }
    static Mode mode;
    static int simulationRuns;

    static List<Person> people = new ArrayList<>();

    static Random random = new Random();

    public static void main(String[] args) {
        try {   // verify command line arguments
            int _mode = args.length > 0 ? Integer.parseInt(args[0]) : 0;
            simulationRuns = args.length > 1 ? Integer.parseInt(args[1]) : 0;
            if (_mode == 1) {
                mode = Mode.ANARCHY;
            } else {
                mode = Mode.SOCIETY;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            if (simulationRuns == 0) {
                int days = 365; // Number of days to simulate
                runSimulation(mode, days);
            } else {
                int days = 365; // Number of days to simulate
                int _totalSurvivalDays = 0;
                for (int i = 1; i <= simulationRuns; i++) {
                    _totalSurvivalDays += runSimulation(mode, days);
                }
                
                StringBuilder finalResults = new StringBuilder();
                finalResults.append("The population survived an average of ");
                finalResults.append(_totalSurvivalDays / simulationRuns).append(" days");
                finalResults.append(" for ").append(simulationRuns).append(" simulations.").append("\n");
                System.out.println(finalResults.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run simulation logic based on mode and days
     * @param mode anarchy or society
     * @param days to run simulation
     * 
     * @return days survived
     */
    static int runSimulation(Mode mode, int days) throws FileNotFoundException {
        int day;
        boolean isDisaster;
        Disaster disaster = null;
        StringBuilder outputString;

        initializePeople(); // Reset the people list

        try (// Create a PrintStream to write to a file
        PrintStream fileOut = new PrintStream(new FileOutputStream("simulation_output.txt", true))) {
            StringBuilder modeString = new StringBuilder(); // output current running mode
            modeString.append("Running in ").append(mode).append(" mode ...");
            System.out.println(modeString.toString());
            fileOut.println(modeString.toString());

            for (day = 1; day <= days; day++) { // run each day of simulation
                
                isDisaster = false;
                if (random.nextInt(5) < 4) { // 4 in 5 chance of disaster
                    disaster = getRandomDisaster();
                    isDisaster = true;
                }

                outputString = new StringBuilder(); // Initialize the StringBuilder for each day
                outputString.append("Day ").append(day).append(": ");
                if (isDisaster) {
                    outputString.append(disasterToString(disaster)).append("\n\n");
                } else {
                    outputString.append("No disaster").append("\n\n");
                }

                Iterator<Person> iterator = people.iterator();
                while (iterator.hasNext()) {    // loop through population and apply daily effects
                    Person person = iterator.next();
                    person.applySkill();
                    person.consumeFood();
                    if (isDisaster) { // apply disaster or not
                        applyDisaster(disaster, person);
                    }

                    outputString.append(person.getSkill()); // finish output string for each person
                    if (person.getSkill() == "Carpenter") {
                        outputString.append("\t- Health: ").append(person.health);
                    } else {
                        outputString.append("\t\t- Health: ").append(person.health);
                    }
                    outputString.append(", Food: ").append(person.food);
                    outputString.append(", Shelter: ").append(person.shelter).append("\n");

                    if (person.health <= 0 || person.food <= 0) {   // check if person has died
                        outputString.append(person.getSkill());
                        outputString.append(" has died...\n");
                        iterator.remove();
                    }

                }

                if (simulationRuns == 0) {
                    System.out.println(outputString.toString());    // output final string
                    fileOut.println(outputString.toString());
                }

                if (people.isEmpty()) {
                    System.out.println("\nEveryone has died...\n");
                    fileOut.println("\nEveryone has died...\n");
                    break;
                } else if (simulationRuns == 0){
                    System.out.println(people.size() + " People survived.\n");
                    fileOut.println(people.size() + " People survived.\n");
                }
            }

            StringBuilder finalResults = new StringBuilder();
            finalResults.append("The population survived for ");
            finalResults.append(day - 1).append(" days.").append("\n");
            System.out.println(finalResults.toString());
            fileOut.println(finalResults.toString());
        }

        return (day - 1);
    }

    /**
     * Define a random disaster
     * @return disaster to apply
     */
    static Disaster getRandomDisaster() {
        Disaster[] disasters = Disaster.values();
        return disasters[random.nextInt(disasters.length)];
    }

    /**
     * Apply disaster effects
     * @param disaster to be used for effect
     */
    static void applyDisaster(Disaster disaster, Person person) {
        switch (disaster) {
            case HURRICANE: // if no shelter reduce health, otherwise reduce shelter
                if (person.shelter == 0) {
                    person.health -= 5;
                } else {
                    person.shelter -= 3;
                    person.shelter = Math.max(person.shelter, 0);
                }
                break;
            case FAMINE:    // reduce food by 2
                person.food -= 2;  
                person.food = Math.max(person.food, 0);
                break;
            case DISEASE:   // reduce health by 2
                person.health -= 2;  
                person.health = Math.max(person.health, 0);
                break;
            case WOLVES:

                Iterator<Person> iterator = people.iterator();
                while (iterator.hasNext()) {
                    Person _person = iterator.next();
                    if (mode == Mode.ANARCHY) {
                        if (_person instanceof PersonHunter) {
                            _person.health -= 1;
                        } else {
                            _person.health -= 3;
                        }
                    } else { // mode == Mode.SOCIETY
                        if (canFightWolves()) {
                            _person.health -= 1;
                        } else {
                            if (person instanceof PersonHunter) {
                                _person.health -= 1;
                            } else {
                                _person.health -= 3;
                            }
                        }
                    }
                }
                
                break;        
            default:
                break;
        }
    }

    /**
     * Uses any avaliable hunters in the population to attempt to fight off an iteration of
     * the disaster case.
     */
    static boolean canFightWolves() {
        boolean returnValue = false;
        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person instanceof PersonHunter) {   // find first avaliable hunter
                returnValue = true;
            }
        }

        return returnValue;
    }

    /**
     * Initializes the simulation population
     */
    static void initializePeople() {
        people.clear();
        people.add(new PersonDoctor());
        people.add(new PersonFarmer());
        people.add(new PersonCarpenter());
        people.add(new PersonHunter());
    }

    /**
     * To string to return the type of disaster
     */
    static String disasterToString(Disaster disaster) {
        StringBuilder returnValue = new StringBuilder();
        switch (disaster) {
            case HURRICANE:
                returnValue.append("Hurricane");
                break;
            case FAMINE:
            returnValue.append("Famine");
                break;
            case DISEASE:
            returnValue.append("Disease");
                break;
            case WOLVES:
            returnValue.append("Wolves");
                break;
            default:
                break;
        }
        return returnValue.toString();
    }
}