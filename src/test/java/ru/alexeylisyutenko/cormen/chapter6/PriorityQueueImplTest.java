package ru.alexeylisyutenko.cormen.chapter6;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter6.PriorityQueue;
import ru.alexeylisyutenko.cormen.chapter6.PriorityQueueImpl;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueImplTest {

    @Test
    void priorityQueueDemo() {
        PriorityQueue priorityQueue = new PriorityQueueImpl();
        System.out.println(priorityQueue);

        priorityQueue.maxHeapInsert(10);
        priorityQueue.maxHeapInsert(1);
        priorityQueue.maxHeapInsert(11);
        priorityQueue.maxHeapInsert(21);
        priorityQueue.maxHeapInsert(5);

        assertEquals(21, priorityQueue.heapMaximum());
        assertEquals(21, priorityQueue.heapMaximum());

        assertEquals(21, priorityQueue.heapExtractMaximum());
        assertEquals(11, priorityQueue.heapExtractMaximum());
        assertEquals(10, priorityQueue.heapExtractMaximum());
        assertEquals(5, priorityQueue.heapExtractMaximum());
        assertEquals(1, priorityQueue.heapExtractMaximum());

        System.out.println(priorityQueue);

        priorityQueue.maxHeapInsert(7);
        priorityQueue.maxHeapInsert(134);
        priorityQueue.maxHeapInsert(25);
        priorityQueue.maxHeapInsert(17);

        assertEquals(134, priorityQueue.heapMaximum());

        priorityQueue.heapIncreaseKey(2, 200);

        assertEquals(200, priorityQueue.heapMaximum());

        assertEquals(200, priorityQueue.heapExtractMaximum());
        assertEquals(134, priorityQueue.heapExtractMaximum());
        assertEquals(25, priorityQueue.heapExtractMaximum());
        assertEquals(7, priorityQueue.heapExtractMaximum());

        System.out.println(priorityQueue);
    }

}