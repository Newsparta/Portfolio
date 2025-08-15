****************
* Double-Linked List
* 4/5/2024
* Rhett Edwards
**************** 

OVERVIEW:

 This is a double linked list implementation of IndexedUnsortedList. This also has a fully
 functioning list iterator with all of it's associated methods.


INCLUDED FILES:

 * dllNode.java - node object used by IUDoubleLinkedList
 * IndexedUnsortedList.java - implemented interface
 * IUDoubleLinkedList.java - double linked list implementation
 * ListTester.java - used to test implementation of IndexedUnsortedList
 * README - this file


COMPILING AND RUNNING:
 
 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac ListTester.java

 Run the compiled testing file with the command:
 $ java ListTester

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 For this program there are several required classes. The basic building block for the double linked list is the "dllNode.java".
 This is a specialized version of a node class with respect to a double linked list. In this class it will hold the element of the
 node, the previous attached node, and the next attached node. This class has all the associated getters and setters for accessing and
 mutating this class. The implemented interface is located in "IndexedUnsortedList.java". This is where the required
 methods and their documentation for the implementation of the double linked list resides.The actual implementation of double linked list
 is contained in "IUDoubleLinkedList.java". This is where all of the functionality of the double linked list is. 
 This class utilizes dllNode's to construct and keep track of a list of these dllNode objects. This has a fully functioning list iterator 
 with its associated add(), remove(), and set(). This has allowed for the iterator to be the primary function for mutating the list.
 The last included class is "ListTester.java", this is the primary testing method to see if the implementation of IndexedUnsortedList
 has been done well. This test class is a basic high level black box test that does not test for all of the special cases.

 For this project I had to design the double linked list and node classes. The double linked list design was driven by the requirements
 of the IndexedUnsortedList interface. The only main design choice with this implementation was the decision to utilize the list iterator
 of the double linked list to achieve its mutating functionality to reduce code duplication. I also made a choice to make a specific use
 of the Node object. It is possible to use a single Node object for multiple types of list implementations. However I decided against using
 this because I wanted to keep this implementation compartmentalized. I dont want to have generic classes unless it makes sense and is not
 utilized often. I feel like many classes can have similarities and if you always make a class perform multiple jobs you can easily balloon
 the responsibilities of these classes and make it harder to debug or understand what classes require what elements. I am not sure if this
 is beneficial for this implementation but this was why I decided to specialize a new dllNode class. While designing the IUDoubleLinkedList
 class I was also not yet able to allow the list iterator to start at either the beginning or the end depending on the index that the iterator
 wants to start at. This could be improved by finishing the list iterator constructor to allow for this case.

TESTING:

 To meet all of the functionality requirements of IndexedUnsortedList I have done basic black box testing using an included test class.
 This test class checks 82 change scenarios that tests all of the basic functionality of mutating the double linked list. The primary drawback
 to this testing class is the fact that it lacks specialized results. For some of the test cases, for instance checking if there is a next
 element if the iterator is at the end of the list, this testing strategy works perfectly fine. However there are many change scenarios that
 are only checking to make sure no exception is thrown. This is extremely beneficial but does not tell you if the implementation actually
 achieved the correct mutation of the list, only that it did not have an unexpected exception. I believe this to be the most time efficient
 solution to testing the majority of this implementation. However this has room for error. For example an implementation of an add method that
 does not add the correct element in will cause an undiagnosed problem where the list does not contain what the user wanted to add. I think this
 should be the next step to improving this implementation to make sure more testing is done to ensure the list contains specifically what you
 want it to contain.


DISCUSSION:

 One issue I encountered while programming this is implementing the test scenarios for 2 and 3 element lists with respect to the list
 iterator. I believe I covered all of the scenarios needed but it would be good to double check my implementation to make sure this is
 not misleading the test results. Another challenge was properly implementing the add() method for the list iterator. For this method I
 had 2 instances of a partially working add method that lacked special cases. Both of these cases came to light while testing and were
 easily rectified but I would not have caught these without testing. the first was not recognizing the difference between an iterator starting
 at the beginning of a 2+ element list and a single element list. This was simple to solve by splitting the first check into 2 cases, one for
 each of these. The next problem was accidentally updating the nextNode's previousNode reference before I had updated that previousNode to
 point at the new node to be added. Order is very important in how you achieve this and it is helpful to reference a physical picture for this.

 This part of this project that seemed to click for me was utilizing the iterator to achieve all of the mutation of this list. This clicked so
 well that I am worried that I am missing a piece to that puzzle and it will crash and burn. The functionality of being able to start the iterator
 wherever you want helps tremendously. Once you start the iterator where you need it, you can utilize the add() method directly, or move from the
 start to where you need to add. This also works for remove() and set() where all you need to do is drive left or right over the element of interest.
 This gives the iterator a reference to what you want to remove or set and then you can mutate as needed. Keeping track of where the iterator is at
 and specifically what it will be doing by add(), remove(), or set() at any moment is important. However if you have an image to refence this is
 simple to understand and keeps you aware of exactly how it will work.

 The final challenge to this project that I may not have time to solve is using the list iterator constructor to start at either end of the list.
 This is actually the main reason I am worried my implementation is lacking something important. I have errors that I would not expect when I try
 to implement this. This may be a symptom that the current implementation may only be working for the specific setup that I made, or that my
 logic behind starting at the end is flawed.
