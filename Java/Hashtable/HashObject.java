/**
 * General hash object to be used in hash table
 * 
 * @author Rhett Edwards
 */
public class HashObject {

    /* Instance Variables */
    private Object key;
    private int frequencyCount;
    private int probeCount;

    /**
     * Default constructor uses given key
     * @param _key to identify the object
     */
    public HashObject(Object _key) {
        this.key = _key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    /**
     * Gets the key of this hash object
     * @return key object
     */
    public Object getKey() {
        return key;
    }

    /**
     * Returns the current frequency count of hash object
     * @return frequency count
     */
    public int getFrequencyCount() {
        return frequencyCount;
    }

    /**
     * Gets the current probe count of the hash object
     * @return probe count
     */
    public int getProbeCount() {
        return probeCount;
    }

    /**
     * Increments the frequency count of this hash object
     */
    public void incrementFrequencyCount() {
        frequencyCount++;
    }

    /**
     * Increments the probe count of this hash object
     */
    public void setProbeCount(int _probeCount) {
        probeCount = _probeCount;
    }

    /**
     * @return hash code of the given object
     */
    public int getHashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object _obj) {
        // check if _obj is in reference to the same thing
        if (this == _obj) {
            return true;
        }
        // return false if null or not the same class
        if (_obj == null || getClass() != _obj.getClass()) {
            return false;
        }
        // do logical key comparison if it has made it this far
        HashObject that = (HashObject) _obj;
        return key.equals(that.key);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(key);
        string.append(" ");
        string.append(frequencyCount);
        string.append(" ");
        string.append(probeCount);
        return string.toString();
    }
}

