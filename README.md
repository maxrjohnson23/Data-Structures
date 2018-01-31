# Data Structures

## Implementations of common Data Structures in Java

###  Common Big O Notation Complexities
   _Ordered by most to least performant_
   * O(1) - Constant
   * O(log n) - Logarithmic
   * O(n) - Linear
   * O(n^2) - Quadratic
   * O(c^n) - Exponential
   * O(n!) - Factorial

### Data Structures

1. **Stacks**
    1. Attributes
       * Last-in-first-out (LIFO)
       * Accessing an element alters the data
    2. Operations
       * Push - O(1)
       * Pop - O(1)
       * Search - O(n)
       * Access - O(n)
       
2.  **Queues**
    1. Attributes
       * First-in-first-out (FIFO)
    2. Operations
       * Enqueue
       * Dequeue
       * Search
       * Access

2.  **Lists**
    1. Attributes
       * Flexible sizing
       * Insertion/removal is more dynamic
    2. Operations
       * Add
       * Remove
       * Insert
       * Remove at
       * Find
       * Get

### Data Structure Performance (average case scenario)
| Structure   | Access | Search | Insert | Delete |
| ------------| ------ | ------ | ------ | ------ |
|  Stack      | O(n)   | O(n)   | O(1)   | O(1)   |
|  Queue      | O(n)   | O(n)   | O(1)   | O(1)   |
|  Linked List| O(n)   | O(n)   | O(1)   | O(1)   |
|  Hash Table | O(1)   | O(1)   | O(1)   | O(1)   |
|  Binary Tree| O(log n)   | O(log n)   | O(log n)   | O(log n)   |
