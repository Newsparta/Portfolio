/**
 * Abstract class for creating a hash table of HashObject's
 * 
 * @author Rhett Edwards
 */
public abstract class Hashtable {

    /* Instance Variables */
    protected HashObject[] table;
    protected int size;
    private int totalProbes;
    private int totalSearches;

    /**
     * Default constructor to initialize table
     * @param _capacity of the table
     */
    public Hashtable(int _capacity) {
        this.table = new HashObject[_capacity];
        this.size = 0;
    }

    /**
     * Inserts specified key at the specified index
     * @param key key to add into table
     * @param _index index to add at
     */
    public void insert(Object key) {
        int index = positiveMod(hash(key), table.length);
        int stepSize = positiveMod(secondaryHash(key), table.length);
        int originalIndex = index;
        int searchProbeCount = 1;

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                table[index].incrementFrequencyCount();
                return;
            }
            index = positiveMod(index + stepSize, table.length);
            searchProbeCount++;
            if (index == originalIndex) {
                throw new IllegalStateException("Hashtable is full");
            }
        }

        table[index] = new HashObject(key);
        table[index].setProbeCount(searchProbeCount);
        size++;
        totalSearches++;
        totalProbes += searchProbeCount;
    }

    /**
     * Searches hash table for specified key
     * @param key key to look for
     * @return null for not found or HashObject if found
     */
    public HashObject search(Object key) {
        int index = positiveMod(hash(key), table.length);
        int stepSize = positiveMod(secondaryHash(key), table.length);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index];
            }
            index = positiveMod(index + stepSize, table.length);
            if (index == originalIndex) {
                break;
            }
        }
        return null;
    }

    /**
     * Ensures the result of the mod operation is always positive
     * @param dividend the number to be divided
     * @param divisor the number by which to divide
     * @return positive result of the mod operation
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) {
            quotient += divisor;
        }
        return quotient;
    }

    /**
     * Hash function to determine where to add element to table
     */
    protected abstract int hash(Object _key);

    /**
     * Secondary hash function
     * 
     */
    protected abstract int secondaryHash(Object _key);

    /**
     * Returns the current size of the table
     * @return size of table
     */
    public int getSize() {
        return size;
    }

    /**
     * @return maximum capacity of the table
     */
    public int getCapacity() {
        return table.length;
    }

    /**
     * Returns the total probes
     */
    public int getTotalProbes() {
        return totalProbes;
    }

    /**
     * Returns the total searches
     */
    public int getTotalSearches() {
        return totalSearches;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (HashObject obj : table) {
            if (obj != null) {
                string.append(obj.toString()).append("\n");
            }
        }
        return string.toString();
    }
}
