import java.util.Arrays;

public class homeworkNotepad {
    public class TreeNode {
        public int x;
        public TreeNode  left;
        public TreeNode  right;
    }

    int count(TreeNode t, int low, int high ) {
        if (t == null) {return 0;}
        if (t.x < low) {
            return count(t.right, low, high);
        }

        if (t.x > high) {
            return count(t.left, low, high);
        }

        return count(t.left, low, high) + count(t.right, low, high);
    }

    public static void main(String[] args) {
        doIt(8);
    }
}
