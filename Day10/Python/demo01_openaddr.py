class HashTable:
    class Entry:
        def __init__(self, key, value):
            self.key = key
            self.value = value

    NSLOTS = 10

    def __init__(self):
        self.table = [None] * self.NSLOTS

    def put(self, key, value):
        probe = 1
        # calc hash index
        index = self.hash(key)
        while True:
            # if no elem on the index, create new entry on the index
            if self.table[index] is None:
                self.table[index] = self.Entry(key, value)
                return
            # if key is matched on index (key already present), overwrite value
            en = self.table[index]
            if key == en.key:
                en.value = value
                return
            # if key not matched on index, find next index by rehash
            index = self.rehash(key, probe)
            probe += 1

    def get(self, key):
        probe = 1
        # calc hash index
        index = self.hash(key)
        while True:
            # if no elem on the index, return None (no value found)
            if self.table[index] is None:
                return None
            # if key is matched on index, return value
            en = self.table[index]
            if key == en.key:
                return en.value
            # if key not matched on index, find next index by rehash
            index = self.rehash(key, probe)
            probe += 1

    def hash(self, key):
        return key % self.NSLOTS

    def rehash(self, key, probe):
        CONST = 1
        return (self.hash(key) + probe * CONST) % self.NSLOTS


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
