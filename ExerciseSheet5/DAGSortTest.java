import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

/* EXERCISE: Write a JUnit 5 test class to test DAGSort.java. Your test class should thoroughly exercise the code,
   including how it deals with invalid input. You should consider that a DAG may have multiple valid topological orderings
   and your tests should be robust to this.

   You can assume that the test class returns some output (so you do not need to test for infinite loops or memory issues),
   and you can assume that the code will never need to run on graphs larger than 1000 (so you do not need to test how the
   code behaves on graphs larger than this).
*/

public class DAGSortTest {

    DAGSort dagSort;

    @BeforeEach
    public void setUp() {
        dagSort = new DAGSort();
    }

    @AfterEach
    public void tearDown() {
        dagSort = null;
    }

    // Checks if, provided a null graph, the NullPointerException is thrown.
    @Test
    public void testNullGraph() {
        assertThrows(NullPointerException.class, () -> dagSort.sortDAG(null));
    }

    // Checks if, provided an invalid node 0, the InvalidNodeException is thrown.
    @Test
    public void testNegativeNode() {
        int[][] graph = new int[3][];
        graph[0] = new int[]{1, 2};
        graph[1] = new int[]{2};
        graph[2] = new int[]{Integer.MIN_VALUE};

        assertThrows(InvalidNodeException.class, () -> dagSort.sortDAG(graph),
                "Nodes are assumed to be labelled from 0 to (number of nodes) - 1");
    }

    // Checks if, provided an invalid node n > (num. of edges), the InvalidNodeException is thrown.
    @Test
    public void testOutOfBoundsNode() {
        int[][] graph = new int[3][];
        graph[0] = new int[]{1, 2};
        graph[1] = new int[]{2};
        graph[2] = new int[]{Integer.MAX_VALUE};

        assertThrows(InvalidNodeException.class, () -> dagSort.sortDAG(graph),
                "Nodes are assumed to be labelled from 0 to (number of nodes) - 1");
    }

    // Checks if, provided a Cyclic Graph, the CycleDetectedException is thrown.
    @Test
    public void testCyclicGraph() {
        int[][] cyclicGraph = new int[3][];
        cyclicGraph[0] = new int[]{1};
        cyclicGraph[1] = new int[]{2};
        cyclicGraph[2] = new int[]{0};

        assertThrows(CycleDetectedException.class, () -> dagSort.sortDAG(cyclicGraph),
                "Cyclic graphs are not allowed.");
    }

    // Checks if, provided a Cyclic Graph, the CycleDetectedException is thrown.
    @Test
    public void testSingletonCyclicGraph() {
        int[][] cyclicGraph = new int[1][];
        cyclicGraph[0] = new int[]{0};

        assertThrows(CycleDetectedException.class, () -> dagSort.sortDAG(cyclicGraph),
                "Cyclic graphs are not allowed.");
    }

    // Checks if, provided a valid Directed Acyclic Graph, a valid topological order is output.
    @Test
    public void testOutput() {
        int[][] graph = new int[6][];
        graph[0] = new int[]{};
        graph[1] = new int[]{};
        graph[2] = new int[]{3};
        graph[3] = new int[]{1};
        graph[4] = new int[]{0,1};
        graph[5] = new int[]{0,2};

        int[][] expectedResults = new int[13][];

        expectedResults[0] = new int[]{4,5,0,2,3,1};
        expectedResults[1] = new int[]{4,5,2,0,3,1};
        expectedResults[2] = new int[]{4,5,2,3,0,1};
        expectedResults[3] = new int[]{4,5,2,3,1,0};
        expectedResults[4] = new int[]{5,2,3,4,0,1};
        expectedResults[5] = new int[]{5,2,3,4,1,0};
        expectedResults[6] = new int[]{5,2,4,0,3,1};
        expectedResults[7] = new int[]{5,2,4,3,0,1};
        expectedResults[8] = new int[]{5,2,4,3,1,0};
        expectedResults[9] = new int[]{5,4,0,2,3,1};
        expectedResults[10] = new int[]{5,4,2,0,3,1};
        expectedResults[11] = new int[]{5,4,2,3,0,1};
        expectedResults[12] = new int[]{5,4,2,3,1,0};

        boolean correctOutput = false;
        int[] actualResult = null;

        try {
            actualResult = dagSort.sortDAG(graph);
        } catch (Exception ignored) { }

        for (int[] expectedResult : expectedResults) {
            if (Arrays.toString(expectedResult).equals(Arrays.toString(actualResult))) {
                correctOutput = true;
                break;
            }
        }

        assertTrue(correctOutput);
    }
}