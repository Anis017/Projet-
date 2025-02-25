// Climbing.java
import java.util.*;

public class Climbing {
    static class Node {
        int id;

        @Override
        public String toString() {
            return "Node{id=" + id + "}";
        }
        List<Node> children;
        Node(int id) {
            this.id = id;
            this.children = new ArrayList<>();
        }
    }

    /*@ requires t >= 0 && params != null && trees != null && params.length == t * 2 
      @    && trees.length == t;
      @ ensures \result != null && \result.length == t;
      @ ensures (\forall int i; 0 <= i && i < t; \result[i] >= 0);
      @*/
    public static int[] maxDisjointPaths(int t, int[] params, String[] trees) {
        int[] results = new int[t];
        for (int i = 0; i < t; i++) {
            int k = params[2 * i + 1];
            Node root = parseTree(trees[i], new int[]{0});
            results[i] = solve(root, k);
        }
        return results;
    }

    private static Node parseTree(String tree, int[] pos) {
        while (pos[0] < tree.length() && tree.charAt(pos[0]) != '(') pos[0]++;
        pos[0]++; // Skip '('
        int id = 0;
        while (pos[0] < tree.length() && Character.isDigit(tree.charAt(pos[0]))) {
            id = id * 10 + (tree.charAt(pos[0]) - '0');
            pos[0]++;
        }
        Node node = new Node(id);
        if (pos[0] >= tree.length() || tree.charAt(pos[0]) == ')') {
            pos[0]++;
            return node;
        }
        pos[0]++; // Skip '{'
        while (pos[0] < tree.length() && tree.charAt(pos[0]) != '}') {
            node.children.add(parseTree(tree, pos));
            if (pos[0] < tree.length() && tree.charAt(pos[0]) == ',') pos[0]++;
        }
        pos[0] += 2; // Skip '}' and ')'
        return node;
    }

    private static int solve(Node root, int k) {
        if (root == null || k < 0) return 0;
        if (k == 0) return 1;

        int total = 0;
        for (Node child : root.children) {
            total += solve(child, k - 1); // Chemin incluant cet enfant
        }
        return total;
    }
}