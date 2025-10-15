/**
 *  Defines the people to be used in the simulation
 */
abstract class Person {
    int health;    // Basic attributes for a person
    int food;
    int shelter;
    int skillTimer;
    String skill;

    /**
     * Default constructor
     * @param health initial health
     * @param food initial food
     * @param shelter initial shelter
     * @param skillTimer track how many times a skill use has been called
     * @param skill occupation
     */
    Person(String skill) {
        this.health = 10;
        this.food = 10;
        this.shelter = 10;
        this.skillTimer = 0;
        this.skill = skill;
    }

    /**
     * Apply the skill effect based on occupation
     */
    abstract void applySkill();

    /**
     * Decrement food for this individual by 1
     */
    void consumeFood() {
        if (food > 0) {
            food--;
        }
    }

    /**
     * Get the skill of this person
     */
    String getSkill() {
        return this.skill;
    }

    /**
     * Checks if health or shelter are above 10 and reset if needed
     */
    void constrainResources() {
        if (health >= 10) {
            health = 10;
        }
        if (shelter >= 10) {
            shelter = 10;
        }
    }
}
