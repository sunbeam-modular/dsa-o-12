def max_repeated_char(s):
    # count num of occurrences for each char
    char_count = {}
    for ch in s:
        char_count[ch] = char_count.get(ch, 0) + 1
    print(char_count)
    # find char with max occurrences
    max_count = 0
    max_char = '\0'
    for ch, count in char_count.items():
        if count > max_count:
            max_count = count
            max_char = ch
    return max_char


if __name__ == "__main__":
    s = "ABCDZBXPYDS"
    ch = max_repeated_char(s)
    print("Max Repeated Char:", ch)
