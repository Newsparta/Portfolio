# Project #: Project 2 - Stardew Valley Simulation

* Author: Rhett Edwards & CS instructors
* Class: CS321 Section #001
* Semester: Fall 2024

## Overview

This is an experiment for testing linear and double hashing functions for a hash table.

## Reflection

Overall i enjoyed this project. Having no code to work from I think was a good immersion into making sure you find all the
information you need to complete the project. In some instances, like when I was trying to write the experiment class, this
was a bit overwhelming. However taking small steps towards the goal helps to get the momentum going, and once that happens
the overwhelming nature of that aspect is less important. I think this also helps to emphasize the need to learn exactly what
is required from the project as nothing is provided to you other than some expected test cases.

There were several areas that i struggled with during this project. One of the biggest areas was the fact that I used my own
hashing implementation that was seperate from the book. This was for a couple reasons. One big reason was that I simply do not
understand what the book pseudocode is showing. I don't think the book has good pseudocode for learning. In terms of implemenetation
I think it is excellent because it shows you exactly what you need. However for learning I think it is terrible beacuse I end
up not understanding what the letters and operators are doing. This could all be solved if it were just written in plain english.
I think i would rather have a more vague pseudocode that clearly explains what it needs to do and lets me try and figure out what
the implementation exactly is, rather than showing me exactly what it needs but I cant read it anyhow. Another struggle with the
implementation was simply making sure the hash functions were plotting to the correct points in the table. The first part of this
had an issue where I simply was not writing the entire table into the dump file so i spent a long time trying to figure out why
it was producing correctly, exactly half of the hash table. Then the second aspect was that my double hashing implementation did
not place the items in the correct spot. It wasn't very difficult to fix but I had to move puzzle pieces around multiple times before
it came out correctly.

## Compiling and Using

From the command line, navigate to the folder containing HashtableExperiment.java.
compile with 'javac HashtableExperiment.java'. Run the program with the following syntax.
'java HashtableExperiment \<dataSource\> \<loadFactor\> \[\<debugLevel\>\]'.
The first 2 arguments are required and the last is optional.

## Results 

run-tests.sh runs all tests with matching output and empty .diff files for all but 0.95 and 0.99 load factor

////////////////////
// RANDOM RESULTS //
////////////////////

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: random numbers Loadfactor: 0.5

	Using Linear Probing
HashtableExperiment: size of hash table is 47896
	Inserted 72106 elements, of which 24210 were duplicates
	Avg. no. of probes = 1.51

	Using Double Hashing
HashtableExperiment: size of hash table is 47896
	Inserted 66481 elements, of which 18585 were duplicates
	Avg. no. of probes = 1.39

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: random numbers Loadfactor: 0.6

	Using Linear Probing
HashtableExperiment: size of hash table is 57475
	Inserted 100365 elements, of which 42890 were duplicates
	Avg. no. of probes = 1.75

	Using Double Hashing
HashtableExperiment: size of hash table is 57475
	Inserted 87472 elements, of which 29997 were duplicates
	Avg. no. of probes = 1.52

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: random numbers Loadfactor: 0.7

	Using Linear Probing
HashtableExperiment: size of hash table is 67054
	Inserted 144605 elements, of which 77551 were duplicates
	Avg. no. of probes = 2.16

	Using Double Hashing
HashtableExperiment: size of hash table is 67054
	Inserted 115647 elements, of which 48593 were duplicates
	Avg. no. of probes = 1.72

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: random numbers Loadfactor: 0.8

	Using Linear Probing
HashtableExperiment: size of hash table is 76633
	Inserted 222881 elements, of which 146248 were duplicates
	Avg. no. of probes = 2.91

	Using Double Hashing
HashtableExperiment: size of hash table is 76633
	Inserted 154502 elements, of which 77869 were duplicates
	Avg. no. of probes = 2.02

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: random numbers Loadfactor: 0.9

	Using Linear Probing
HashtableExperiment: size of hash table is 86212
	Inserted 469359 elements, of which 383147 were duplicates
	Avg. no. of probes = 5.44

	Using Double Hashing
HashtableExperiment: size of hash table is 86212
	Inserted 221587 elements, of which 135375 were duplicates
	Avg. no. of probes = 2.57

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: random numbers Loadfactor: 0.95

	Using Linear Probing
HashtableExperiment: size of hash table is 91001
	Inserted 959537 elements, of which 868536 were duplicates
	Avg. no. of probes = 10.54

	Using Double Hashing
HashtableExperiment: size of hash table is 91001
	Inserted 286355 elements, of which 195354 were duplicates
	Avg. no. of probes = 3.15

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: random numbers Loadfactor: 0.99

	Using Linear Probing
HashtableExperiment: size of hash table is 94833
	Inserted 4273393 elements, of which 4178560 were duplicates
	Avg. no. of probes = 45.06

	Using Double Hashing
HashtableExperiment: size of hash table is 94833
	Inserted 442864 elements, of which 348031 were duplicates
	Avg. no. of probes = 4.67

--------------------------------------------------------------------------------



//////////////////
// DATE RESULTS //
//////////////////

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: date value as a long Loadfactor: 0.5

	Using Linear Probing
HashtableExperiment: size of hash table is 47896
	Inserted 61379 elements, of which 13483 were duplicates
	Avg. no. of probes = 1.28

	Using Double Hashing
HashtableExperiment: size of hash table is 47896
	Inserted 65402 elements, of which 17506 were duplicates
	Avg. no. of probes = 1.37

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: date value as a long Loadfactor: 0.6

	Using Linear Probing
HashtableExperiment: size of hash table is 57475
	Inserted 82808 elements, of which 25333 were duplicates
	Avg. no. of probes = 1.44

	Using Double Hashing
HashtableExperiment: size of hash table is 57475
	Inserted 97260 elements, of which 39785 were duplicates
	Avg. no. of probes = 1.69

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: date value as a long Loadfactor: 0.7

	Using Linear Probing
HashtableExperiment: size of hash table is 67054
	Inserted 107042 elements, of which 39988 were duplicates
	Avg. no. of probes = 1.60

	Using Double Hashing
HashtableExperiment: size of hash table is 67054
	Inserted 135341 elements, of which 68287 were duplicates
	Avg. no. of probes = 2.02

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: date value as a long Loadfactor: 0.8

	Using Linear Probing
HashtableExperiment: size of hash table is 76633
	Inserted 139208 elements, of which 62575 were duplicates
	Avg. no. of probes = 1.82

	Using Double Hashing
HashtableExperiment: size of hash table is 76633
	Inserted 189617 elements, of which 112984 were duplicates
	Avg. no. of probes = 2.47

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: date value as a long Loadfactor: 0.9

	Using Linear Probing
HashtableExperiment: size of hash table is 86212
	Inserted 188137 elements, of which 101925 were duplicates
	Avg. no. of probes = 2.18

	Using Double Hashing
HashtableExperiment: size of hash table is 86212
	Inserted 272981 elements, of which 186769 were duplicates
	Avg. no. of probes = 3.17

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: date value as a long Loadfactor: 0.95

	Using Linear Probing
HashtableExperiment: size of hash table is 91001
	Inserted 245896 elements, of which 154895 were duplicates
	Avg. no. of probes = 2.70

	Using Double Hashing
HashtableExperiment: size of hash table is 91001
	Inserted 341716 elements, of which 250715 were duplicates
	Avg. no. of probes = 3.76

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: date value as a long Loadfactor: 0.99

	Using Linear Probing
HashtableExperiment: size of hash table is 94833
	Inserted 512598 elements, of which 417765 were duplicates
	Avg. no. of probes = 5.41

	Using Double Hashing
HashtableExperiment: size of hash table is 94833
	Inserted 511302 elements, of which 416469 were duplicates
	Avg. no. of probes = 5.39

--------------------------------------------------------------------------------

///////////////////////
// WORD LIST RESULTS //
///////////////////////


--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-list Loadfactor: 0.5

	Using Linear Probing
HashtableExperiment: size of hash table is 47896
	Inserted 76486 elements, of which 28590 were duplicates
	Avg. no. of probes = 1.60

	Using Double Hashing
HashtableExperiment: size of hash table is 47896
	Inserted 66599 elements, of which 18703 were duplicates
	Avg. no. of probes = 1.39

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-list Loadfactor: 0.6

	Using Linear Probing
HashtableExperiment: size of hash table is 57475
	Inserted 123536 elements, of which 66061 were duplicates
	Avg. no. of probes = 2.15

	Using Double Hashing
HashtableExperiment: size of hash table is 57475
	Inserted 88172 elements, of which 30697 were duplicates
	Avg. no. of probes = 1.53

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-list Loadfactor: 0.7

	Using Linear Probing
HashtableExperiment: size of hash table is 67054
	Inserted 241654 elements, of which 174600 were duplicates
	Avg. no. of probes = 3.60

	Using Double Hashing
HashtableExperiment: size of hash table is 67054
	Inserted 115421 elements, of which 48367 were duplicates
	Avg. no. of probes = 1.72

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-list Loadfactor: 0.8

	Using Linear Probing
HashtableExperiment: size of hash table is 76633
	Inserted 514087 elements, of which 437454 were duplicates
	Avg. no. of probes = 6.71

	Using Double Hashing
HashtableExperiment: size of hash table is 76633
	Inserted 154494 elements, of which 77861 were duplicates
	Avg. no. of probes = 2.02

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-list Loadfactor: 0.9

	Using Linear Probing
HashtableExperiment: size of hash table is 86212
	Inserted 1708282 elements, of which 1622070 were duplicates
	Avg. no. of probes = 19.81

	Using Double Hashing
HashtableExperiment: size of hash table is 86212
	Inserted 221498 elements, of which 135286 were duplicates
	Avg. no. of probes = 2.57

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-list Loadfactor: 0.95

	Using Linear Probing
HashtableExperiment: size of hash table is 91001
	Inserted 10064258 elements, of which 9973257 were duplicates
	Avg. no. of probes = 110.60

	Using Double Hashing
HashtableExperiment: size of hash table is 91001
	Inserted 289931 elements, of which 198930 were duplicates
	Avg. no. of probes = 3.19

--------------------------------------------------------------------------------

--------------------------------------------------------------------------------

HashtableExperiment: Found a twin prime table capacity: 95791
HashtableExperiment: Input: Word-list Loadfactor: 0.99

	Using Linear Probing
HashtableExperiment: size of hash table is 94833
	Inserted 44728738 elements, of which 44633905 were duplicates
	Avg. no. of probes = 471.66

	Using Double Hashing
HashtableExperiment: size of hash table is 94833
	Inserted 445294 elements, of which 350461 were duplicates
	Avg. no. of probes = 4.70

--------------------------------------------------------------------------------



## Sources used

- CS321 Data Structures Project #3 document
- Project 3 Plan

----------

## Notes

* none
