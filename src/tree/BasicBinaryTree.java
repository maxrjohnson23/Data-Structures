package tree;

// Tree elements must implement Comparable
public class BasicBinaryTree<X extends Comparable<X>> {

    private Node root;
    private int size;

    public BasicBinaryTree() {
        this.root = null;
    }

    public void add(X item) {
        Node node = new Node(item);
        if (root == null) {
            // first item added to the tree
            this.root = node;
            System.out.printf("Set root: %s%n", node.getItem());
            this.size++;
        } else {
            insert(this.root, node);
        }
    }

    private void insert(Node parent, Node child) {
        // If child is less than parent, it goes on the left
        if (child.getItem().compareTo(parent.getItem()) < 0) {
            // Child is less than parent. If left node is null, it goes here
            if (parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;
            } else {
                // Recursively search for insert location
                insert(parent.getLeft(), child);
            }
        } else if (child.getItem().compareTo(parent.getItem()) > 0) {
            // Child is greater than parent. If right node is null, it goes here
            if (parent.getRight() == null) {
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
            } else {
                // Recursively search for insert location
                insert(parent.getRight(), child);
            }
        }

        // If parent and child values are equal, do nothing.
        // It is assumed that values are unique in the tree, so it is already existing
    }

    public boolean contains(X item) {
        Node foundNode = getNode(item);

        return foundNode != null;
    }

    // Recursive search
    private boolean search(X item, Node node) {
        // If child is less than node, search left
        if (item.compareTo(node.getItem()) < 0) {
            if (node.getLeft() == null) {
                return false;
            } else {
                // Recursively search for item on the left
                return search(item, node.getLeft());
            }
        } else if (item.compareTo(node.getItem()) > 0) {
            if (node.getRight() == null) {
                return false;
            } else {
                // Recursively search for item on the right
                return search(item, node.getRight());
            }
        } else {
            // Item equal to the current node, we found it
            return true;
        }
    }

    // Non-recursive retrieval or search
    public Node getNode(X item) {
        Node currentNode = root;

        while (currentNode != null) {
            int val = item.compareTo(currentNode.getItem());

            // check if node is a match
            if (val == 0) {
                return currentNode;
            } else if (val < 0) {
                // If less than 0, check the left side
                currentNode = currentNode.getLeft();
            } else {
                // If greater than 0, check the right
                currentNode = currentNode.getRight();
            }
        }
        // Not found
        return null;
    }

    public boolean delete(X item) {
        boolean deleted = false;

        // determine if tree is empty
        if (root == null) {
            return false;
        }

        // find the node to delete
        Node currentNode = getNode(item);

        if (currentNode != null) {
            // if node doesn't have children, remove it
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                unlink(currentNode, null);
                deleted = true;
            } else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                // unlink and reattach right subtree
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            } else if (currentNode.getRight() == null && currentNode.getLeft() != null) {
                // unlink and reattach left subtree
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            } else {
                // both children present
                // swap out the node with the right-most leaf node on the left
                Node child = currentNode.getLeft();
                while (child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }

                // with the right-most leaf node, we can replace with this node
                child.getParent().setRight(null);

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted = true;
            }
        }
        if (deleted) {
            this.size--;
        }
        return deleted;
    }

    private void unlink(Node currentNode, Node newNode) {
        // if this is the root node, we have a different replacement
        if (currentNode == root) {
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            this.root = newNode;
        } else if (currentNode.getParent().getRight() == currentNode) {
            currentNode.getParent().setRight(newNode);
        } else {
            currentNode.getParent().setLeft(newNode);
        }
    }


    public int size() {
        return size;
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private X item;

        public Node(X item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(X item) {
            this.item = item;
        }
    }

}
