class HashTable:
    class Entry:
        def __init__(self, key, value):
            self.key = key
            self.value = value

    NSLOTS = 10

    def __init__(self):
        self.table = [[] for _ in range(self.NSLOTS)]

    def put(self, key, value):
        # find slot of the table where key is to be added
        index = self.hash(key)
        # get the bucket in the slot and search key in all entries in that bucket
        bucket = self.table[index]
        for en in bucket:
            # if key is found, overwrite the value
            if en.key == key:
                en.value = value
                return
        # if key not found (in bucket), create a new entry and add in that bucket
        self.table[index].append(self.Entry(key, value))

    def get(self, key):
        # find slot of the table where key might be present
        index = self.hash(key)
        # get the bucket in the slot and search key in all entries in that bucket
        bucket = self.table[index]
        for en in bucket:
            # if key is found, return the value
            if en.key == key:
                return en.value
        # if key not found (in bucket), return None
        return None

    def hash(self, key):
        return key % self.NSLOTS


if __name__ == "__main__":
    ht = HashTable()
    ht.put(89, "v1")
    ht.put(18, "v2")
    ht.put(49, "v3")
    ht.put(58, "v4")
    ht.put(29, "v5")
    ht.put(36, "v6")
    ht.put(58, "item4")  # if key duplicates, the value should overwrite

    key = int(input("Enter key to find: "))
    value = ht.get(key)
    if value is None:
        print("Not Found")
    else:
        print("Found:", value)
