import java.util.Iterator;
import java.util.Random;

class PersonHunter extends Person {

    PersonHunter() {
        super("Hunter");
    }

    @Override
    void applySkill() {

        Random random = new Random();
        if (Simulation.mode == Simulation.Mode.ANARCHY) {
            if (random.nextInt(5) < 1) { // 1 in 5 chance of finding food
                food += 2;
            }
        } else {
            if (random.nextInt(5) < 1) { // 1 in 5 chance of finding food
                Iterator<Person> iterator = Simulation.people.iterator();
                while (iterator.hasNext()) {
                    Person person = iterator.next();
                    person.food += 2;
                }
            }
        }
        constrainResources();
        skillTimer++;
    }
}
