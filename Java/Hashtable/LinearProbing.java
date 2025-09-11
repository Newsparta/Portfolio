/**
 * Hash function for linear probing of an object into the hash table
 * 
 * @author Rhett Edwards
 */
public class LinearProbing extends Hashtable {

    /**
     * Super constructor from Hashtable
     * @param _capacity capacity of hash table
     */
    public LinearProbing(int _capacity) {
        super(_capacity);
    }

    @Override
    protected int hash(Object _key) {
        return positiveMod(_key.hashCode(), table.length);
    }

    @Override
    protected int secondaryHash(Object _obj) {
        return 1; // step size of 1
    }
}
