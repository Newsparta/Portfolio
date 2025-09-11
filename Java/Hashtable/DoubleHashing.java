/**
 * Hash function for double hashing an object into the hash table
 * 
 * @author Rhett Edwards
 */
public class DoubleHashing extends Hashtable {

    /**
     * Super constructor from Hashtable
     * @param _capacity capacity of hash table
     */
    public DoubleHashing(int _capacity) {
        super(_capacity);
    }

    @Override
    protected int hash(Object _key) {
        return positiveMod(_key.hashCode(), table.length);
    }

    @Override
    protected int secondaryHash(Object _key) {
        return 1 + positiveMod(_key.hashCode(), table.length - 2);
    }
}