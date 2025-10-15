import java.util.Iterator;

class PersonCarpenter extends Person {

    PersonCarpenter() {
        super("Carpenter");
    }

    @Override
    void applySkill() {
        if (Simulation.mode == Simulation.Mode.ANARCHY) {
            if (food <= 1) {
                food++;
            } else {
                shelter += 2;
            }
        } else {
            if (food <= 1) {
                food++;
            } else {
                Iterator<Person> iterator = Simulation.people.iterator();
                while (iterator.hasNext()) {
                    Person person = iterator.next();
                    person.shelter++;
                }
            }
        }
        constrainResources();
        skillTimer++;
    }
}
