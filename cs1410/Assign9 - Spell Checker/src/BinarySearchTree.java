public class BinarySearchTree<E extends Comparable<E>> {
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }
    }

    private TreeNode root;

    public void display(String message) {
        System.out.println();
        System.out.println(message);
        inOrder(root);
        System.out.println();
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    public boolean remove(E value) {
        TreeNode node = root;
        // If the tree is empty return false as nothing was removed,
        // otherwise enter recursive removal
        if (node == null) {
            return false;
        } else {
            return recurRemove(null, node, value);
        }
    }

    private boolean recurRemove(TreeNode parent, TreeNode node, E value) {
        if (node == null) {
            return false;
        }
        if (value.compareTo(node.value) > 0) {
            return recurRemove(node, node.right, value);
        } else if (value.compareTo(node.value) < 0) {
            return recurRemove(node, node.left, value);
        } else {
            // Found the Node
            if (node.left == null) {
                if (node.right == null) {
                    // No Children (Mountain Goats Reference)
                    if (parent == null) {
                        root = null;/* One-node tree */
                    } else if (parent.left == node) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else {
                    // No Left Child
                    if (parent == null) {
                        root = node.right; // Update upon removal
                    } else if (parent.left == node) {
                        parent.left = node.right;
                    } else {
                        parent.right = node.right;
                    }
                }
            } else {
                // No Right Child
                if (node.right == null) {
                    if (parent == null) {
                        root = node.left; // Update upon removal
                    } else if (parent.left == node) {
                        parent.left = node.left;
                    } else {
                        parent.right = node.left;
                    }
                } else {
                    // Twins! (Two children)
                    TreeNode maxParent = node;
                    TreeNode max = node.left;
                    while (max.right != null) {
                        maxParent = max;
                        max = max.right;
                    }
                    // Removed value becomes max value
                    node.value = max.value;

                    // Remove the max from the left subtree
                    if (maxParent == node) {
                        maxParent.left = max.left;
                    } else {
                        maxParent.right = max.left;
                    }
                }
            }
            return true;
        }
    }

    public boolean insert(E value) {
        if (root == null) {
            // If there is no root, make one :)
            root = new TreeNode(value);
            // If a value was inserted, return true and skip line 21
            return true;
        }
        return recurInsert(root, value);
    }

    private boolean recurInsert(TreeNode node, E value) {
        // Repeat the insert process for if node.value < value
        if (value.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new TreeNode(value);
                return true;
            }
            return recurInsert(node.right, value);
        } else if (value.compareTo(node.value) < 0) {
            // Repeat the insert process for if node.value > value
            if (node.left == null) {
                node.left = new TreeNode(value);
                return true;
            }
            return recurInsert(node.left, value);
        } else return false; /* If value == node.value it's a duplicate */
    }


    public boolean search(E value) {
        return recurSearch(root, value);
    }

    private boolean recurSearch(TreeNode node, E value) {
        if (node == null) {
            return false;
        }

        if (value.compareTo(node.value) < 0) {
            return recurSearch(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            return recurSearch(node.right, value);
        } else {
            return true;
        }
    }

    public int numberNodes() {
        return recurNumNodes(root);
    }

    private int recurNumNodes(TreeNode node) {
        if (node == null) return 0;
        // Add sides together and add one if node isn't null
        return recurNumNodes(node.left) + recurNumNodes(node.right) + 1;
    }

    public int numberLeafNodes() {
        return recurNumLeafNodes(root);
    }

    private int recurNumLeafNodes(TreeNode node) {
        if (node == null) {
            return 0;
        } else if (node.left == null) {
            if (node.right == null) {
                // if node is a leaf, add one
                return 1;
            }
        }
        return recurNumLeafNodes(node.left) + recurNumLeafNodes(node.right);
    }

    public int height() {
        return recurHeight(root);
    }

    private int recurHeight(TreeNode node) {
        // Height is 0 if node is null
        if (node == null) return -1;
        // Figure out which side is higher, then add one if the node isn't null
        return Math.max(recurHeight(node.left), recurHeight(node.right)) + 1;
    }
}