// Main.java
public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    private static void problem1() {
        System.out.println("Problème 1: Registre National Belge");
        int n = 4;
        int[] sizes = {7, 5, 3, 1};
        String[][] testCases = {
            {"Leclereq", "Peeters", "Martin", "Peeters", "Peeters", "Dubois", "Peeters"},
            {"Peeters", "Goossens", "Laurent", "Goossens", "Goossens"},
            {"Peeters", "Laurent", "Wouters"},
            {"Leclereq"}
        };
        String[] results = Register.findMostCommonNames(n, sizes, testCases);
        for (String result : results) {
            System.out.println(result == null ? "null" : result);
        }
    }

    private static void problem2() {
        System.out.println("\nProblème 2: Le Pompier Namurois");
        int t = 4;
        int[] dimensions = {5, 5, 1, 1, 3, 3, 1, 1};
        int[][][] grids = {
            {{0, 1, 2, 0, 0}, {1, -1, 1, -1, 0}, {-1, 1, 1, -1, 0}, {0, 0, 0, 1, 0}, {2, 0, -1, 1, 0}},
            {{1}},
            {{1, -1, 1}, {2, 3, 1}, {1, 1, 2}},
            {{-1}}
        };
        int[] results = Firefighter.maxPeopleSaved(t, dimensions, grids);
        for (int result : results) {
            System.out.println(result);
        }
    }

    private static void problem3() {
        System.out.println("\nProblème 3: L'escalade à Freÿr");
        int t = 4;
        int[] params = {9, 2, 8, 3, 6, 3, 1, 5};
        String[] trees = {
            "(1,{(2,{(5),(6)}),(3,{(7),(8),(9)}),(4)})",
            "(5,{(1,{(8),(6)}),(4,{(9),(2),(3)})})",
            "(1,{(2,{(3),(6)}),(4,{(9)})})",
            "(1)"
        };
        int[] results = Climbing.maxDisjointPaths(t, params, trees);
        for (int result : results) {
            System.out.println(result);
        }
    }
}