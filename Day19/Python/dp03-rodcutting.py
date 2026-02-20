# https://www.geeksforgeeks.org/problems/rod-cutting0840/1

def rec_rodcut(length, price):
    max_profit = 0
    for cut in range(1, length+1):
        profit_right = rec_rodcut(length-cut, price)
        if price[cut] + profit_right > max_profit:
            max_profit = price[cut] + profit_right
    return max_profit


def mem_rodcut(length, price, mem):
    if length == 0:
        mem[0] = 0
        return 0
    if mem[length] != 0:
        return mem[length]
    max_profit = 0
    for cut in range(1, length+1):
        profit_right = mem_rodcut(length-cut, price, mem)
        if price[cut] + profit_right > max_profit:
            max_profit = price[cut] + profit_right
    mem[length] = max_profit
    return mem[length]


def memo_rodcut(length, price):
    mem = [0] * len(price)
    return mem_rodcut(length, price, mem)


rodLen = 6
price = [0, 1, 5, 8, 9, 10, 14, 17, 20, 24, 30]

res1 = rec_rodcut(rodLen, price)
print(f"recursive cut profit: {res1}")

res2 = memo_rodcut(rodLen, price)
print(f"memoized cut profit: {res2}")
