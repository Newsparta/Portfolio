import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Runs an experiment for an implementation of a hash table
 * 
 * @author Rhett Edwards
 * @version Fall 2024
 */
public class HashtableExperiment {
    /* Instance Variables */
    ExperimentState state;
    static int experimentSize;
    static StringBuilder experimentSummary;
    static int dataSource;
    static int debugLevel = 0;
    static double loadFactor;
    
    /**
     * Class to encapsulate the state of the experiment
     */
    private class ExperimentState {
        int tableElements;
        int Insertions;
        int uniqueInsertions = 0;
        double AverageProbes;
    }

    /**
     * Driver method to initiate experiment
     * @param args
     */
    public static void main(String[] args) {
        /* Initial validation of arguments */
        if (args.length < 2 || args.length > 3) {
            printUsage("Incorrect number of arguments used");
            return;
        } else {
            try {
                dataSource = Integer.parseInt(args[0]);
                loadFactor = Double.parseDouble(args[1]);
                if (args.length == 3) {
                    debugLevel = Integer.parseInt(args[2]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* Setup Experiments */
        HashtableExperiment experiment = new HashtableExperiment();
        TwinPrimeGenerator twinPrimeGenerator = new TwinPrimeGenerator();
        experimentSize = twinPrimeGenerator.generateTwinPrimes(95500, 96000);
        LinearProbing hashtableLinearProbing = new LinearProbing(experimentSize);
        DoubleHashing hashtableDoubleHashing = new DoubleHashing(experimentSize);

        experimentSummary = new StringBuilder();
        initializeSummary(dataSource);

        experiment.run(args, hashtableLinearProbing, "linear-dump.txt", "\tUsing Linear Probing");
        experiment.run(args, hashtableDoubleHashing, "double-dump.txt", "\tUsing Double Hashing");

        System.out.print(experimentSummary.toString());
    }

    /**
     * Method to run the experiment
     * @param args
     */
    public void run(String[] args, Hashtable hashType, String dumpFile, String hashTitle) {
        Random random = new Random();
        state = new ExperimentState();
        state.tableElements = (int)(Math.round(loadFactor * experimentSize));

        /* Random number experiment */
        if (dataSource == 1) {
            while (hashType.size < state.tableElements) {
                insertWithDebug(hashType, random.nextInt());
            }

        /* Date experiment */
        } else if (dataSource == 2) {
            long[] dates = generateDates(state.tableElements);
            for (long date : dates) {
                insertWithDebug(hashType, date);
            }

        /* Word list experiment */
        } else {
            try {
                List<String> words = readWords("word-list.txt");
                for (int i = 0; state.uniqueInsertions < state.tableElements; i++) {
                    insertWithDebug(hashType, words.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // update stats
        state.Insertions = hashType.getTotalProbes();
        state.AverageProbes = ((double)hashType.getTotalProbes() / (double)hashType.getTotalSearches());

        if (debugLevel == 0) {
            appendExperimentResults(hashTitle);
        } else if (debugLevel == 1) {
            appendExperimentResults(hashTitle);
            dumpToFile(dumpFile, hashType);
        }
    }

    /**
     * Helper method to insert with debug output
     * @param hashtable the hashtable to insert into
     * @param key the key to insert
     */
    private void insertWithDebug(Hashtable hashtable, Object key) {
        boolean isDuplicate = hashtable.search(key) != null;
        hashtable.insert(key);
        // update unique insertions
        if (!isDuplicate) {
            state.uniqueInsertions++;
        } 
        // print for debug level 2
        if (debugLevel == 2) {
            if (isDuplicate) {
                System.out.println("Duplicate: " + key);
            } else {
                System.out.println("Inserted: " + key);
            }
        }
    }

    /**
     * Dumps the hash table to a file
     * @param fileName the name of the file to write to
     */
    public void dumpToFile(String fileName, Hashtable hashtable) {
        try (PrintWriter out = new PrintWriter(fileName)) {
            for (int i = 0; i < hashtable.getCapacity(); i++) {
                if (hashtable.table[i] != null) {
                    out.println("table[" + i + "]: " + hashtable.table[i].toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /** 
     * Helper method to generate dates for the second experiment option
     * @param _size number of elements needed
     */
    private long[] generateDates(int _size) {
        long[] dates = new long[_size];
        long current = new Date().getTime();

        for (int i = 0; i < _size; i++) {
            dates[i] = current;
            current += 1000;
        }

        return dates;
    }

    /**
     * Reads words from a file and returns them as a list of strings.
     * @param fileName the name of the file to read from
     * @return a list of words
     * @throws IOException if an I/O error occurs
     */
    public List<String> readWords(String fileName) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        }
        return words;
    }

    /**
     * Initialize the state of the experiment summary
     */
    private static void initializeSummary(int _dataSource) {
        String dataSourceName;
        if (_dataSource == 1) {
            dataSourceName = "random numbers";
        } else if (_dataSource == 2) {
            dataSourceName = "date value as a long";
        } else {
            dataSourceName = "Word-list";
        }
        experimentSummary.append("\nHashtableExperiment: Found a twin prime table capacity: ");
        experimentSummary.append(experimentSize);
        experimentSummary.append("\n");
        experimentSummary.append("HashtableExperiment: Input: ");
        experimentSummary.append(dataSourceName);
        experimentSummary.append(" Loadfactor: ");
        experimentSummary.append(loadFactor);
        experimentSummary.append("\n\n");
    }

    /**
     * 
     */
    private void appendExperimentResults(String _hashTitle) {
        String formattedAverage = String.format("%.2f", state.AverageProbes);
        experimentSummary.append(_hashTitle);
        experimentSummary.append("\n");
        experimentSummary.append("HashtableExperiment: size of hash table is ");
        experimentSummary.append(state.tableElements);
        experimentSummary.append("\n");
        experimentSummary.append("\tInserted ");
        experimentSummary.append(state.Insertions);
        experimentSummary.append(" elements, of which ");
        experimentSummary.append((state.Insertions - state.tableElements));
        experimentSummary.append(" were duplicates\n");
        experimentSummary.append("\tAvg. no. of probes = ");
        experimentSummary.append(formattedAverage);
        experimentSummary.append("\n\n");
    }

    /**
     * Print the default usage after a specific input message.
     * @param _input text to display before input message
     */
    private static void printUsage(String _input) {
        System.out.println(_input + ", please use the following usage.");
        System.out.println("java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]\n");
    }
}
