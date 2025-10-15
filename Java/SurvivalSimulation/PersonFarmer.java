import java.util.Iterator;

public class PersonFarmer extends Person {

    PersonFarmer() {
        super("Farmer");
    }

    @Override
    void applySkill() {
        if (Simulation.mode == Simulation.Mode.ANARCHY) {
            if (skillTimer % 3 == 0) {
                food += 3;
            }
        } else {
            if (skillTimer % 3 == 0) {
                Iterator<Person> iterator = Simulation.people.iterator();
                while (iterator.hasNext()) {
                    Person person = iterator.next();
                    person.food += 3;
                }
            }
        }
        constrainResources();
        skillTimer++;
    }
}
