package HW1;

public class LinkedListTest {

    // Counter for the number of test failures.
    private static int failures = 0;

    public static void main(String[] args) {
        testEmptyList();
        testAddFirstAndAddLast();
        testRemoveFirst();

        System.out.println("\nLinkedList Test Summary:");
        if (failures == 0) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("Total failures: " + failures);
        }
        // Optionally exit with a non-zero status if tests failed.
        System.exit(failures);
    }

    // Test that a new LinkedList is empty.
    private static void testEmptyList() {
        System.out.println("Running testEmptyList...");
        LinkedList<Integer> list = new LinkedList<>();
        if (list.getSize() != 0) {
            System.err.println("testEmptyList failed: List should be empty upon initialization.");
            failures++;
        } else {
            System.out.println("testEmptyList passed.");
        }
    }

    // Test adding elements using addFirst() and addLast().
    private static void testAddFirstAndAddLast() {
        System.out.println("\nRunning testAddFirstAndAddLast...");
        LinkedList<Integer> list = new LinkedList<>();

        // Insert initial element using addFirst.
        list.addFirst(10);
        if (list.getSize() != 1) {
            System.err.println("testAddFirstAndAddLast failed: After addFirst, size should be 1.");
            failures++;
        }

        // Now that the list is non-empty, add more elements using addLast.
        list.addLast(20);
        list.addLast(30);

        if (list.getSize() != 3) {
            System.err.println("testAddFirstAndAddLast failed: Expected list size of 3 after insertions, got " + list.getSize());
            failures++;
        } else {
            System.out.println("testAddFirstAndAddLast passed.");
        }
    }

    // Test that removing elements using removeFirst() returns them all in correct order.
    private static void testRemoveFirst() {
        System.out.println("\nRunning testRemoveFirst...");
        LinkedList<Integer> list = new LinkedList<>();

        // Prepare list: start with addFirst then addLast.
        list.addFirst(10);   // list: [10]
        list.addLast(20);    // list: [10, 20]
        list.addLast(30);    // list: [10, 20, 30]

        // Remove elements in order.
        Integer first = list.removeFirst();
        Integer second = list.removeFirst();
        Integer third = list.removeFirst();

        if (first == null || second == null || third == null) {
            System.err.println("testRemoveFirst failed: A removal returned null unexpectedly.");
                failures++;
        } else {
            if (first != 10) {
                System.err.println("testRemoveFirst failed: Expected first removal to be 10 but got " + first);
            failures++;
        }
            if (second != 20) {
                System.err.println("testRemoveFirst failed: Expected second removal to be 20 but got " + second);
                failures++;
    }
            if (third != 30) {
                System.err.println("testRemoveFirst failed: Expected third removal to be 30 but got " + third);
                failures++;
}
        }

        if (list.getSize() != 0) {
            System.err.println("testRemoveFirst failed: List should be empty after all removals, but size is " + list.getSize());
            failures++;
        } else {
            System.out.println("testRemoveFirst passed.");
        }
    }
}
