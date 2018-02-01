package hashtable;

public class BasicHashTable<X, Y> {

    private HashEntry[] data;
    private int capacity;
    private int size;

    public BasicHashTable(int capacity) {
        this.capacity = capacity;
        this.data = new HashEntry[this.capacity];
        this.size = 0;
    }

    public Y get(X key) {
        int hash = calculateHash(key);

        // No data found for the key
        if (data[hash] == null) {
            return null;
        } else {
            // return the value for the entry
            return (Y) data[hash].getValue();
        }

    }

    public void put(X key, Y value) {
        int hash = calculateHash(key);
        data[hash] = new HashEntry(key, value);
        size++;
    }

    public Y delete(X key) {
        // Get value for key to be returned
        Y value = get(key);

        // Clear the hashtable slot
        if (value != null) {
            int hash = calculateHash(key);
            data[hash] = null;
            size--;
            hash = (hash + 1) % this.capacity;

            // If the next hash slot isn't empty, rehash the entry to keep the table organized
            while (data[hash] != null) {
                HashEntry he = data[hash];
                data[hash] = null;
                System.out.printf("Rehashing: %s - %s%n", he.getKey(), he.getValue());
                put((X) he.getKey(), (Y) he.getValue());
                // reposition the hash value, but didn't add an entry - undo the add from put()
                size--;
                // move on to next hash
                hash = (hash + 1) % this.capacity;
            }
        }
        return value;
    }

    public boolean hasKey(X key) {
        int hash = calculateHash(key);
        return data[hash] != null && data[hash].getKey().equals(key);
    }

    public boolean hasValue(Y value) {
        for (HashEntry he : data) {
            if (he != null && he.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    private int calculateHash(X key) {
        // depends on X to provide the hashCode implementation
        int hash = (key.hashCode() % this.capacity);
        // necessary to deal with collisions
        while (data[hash] != null && !data[hash].getKey().equals(key)) {
            hash = (hash + 1) % this.capacity;
        }
        return hash;
    }

    private class HashEntry<X, Y> {
        private X key;
        private Y value;

        public HashEntry(X key, Y value) {
            this.key = key;
            this.value = value;
        }

        public X getKey() {
            return key;
        }

        public void setKey(X key) {
            this.key = key;
        }

        public Y getValue() {
            return value;
        }

        public void setValue(Y value) {
            this.value = value;
        }
    }

}
