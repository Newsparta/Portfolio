import java.util.Iterator;

class PersonDoctor extends Person {

    PersonDoctor() {
        super("Doctor");
    }

    @Override
    void applySkill() {
        if (Simulation.mode == Simulation.Mode.ANARCHY) {
            if (food <= 1) {
                food++;
            } else {
                health += 2;
            }
        } else {
            if (food <= 1) {
                food++;
            } else {
                Iterator<Person> iterator = Simulation.people.iterator();
                while (iterator.hasNext()) {
                    Person person = iterator.next();
                    person.health += 2;
                }
            }
        }
        constrainResources();
        skillTimer++;
    }
}
