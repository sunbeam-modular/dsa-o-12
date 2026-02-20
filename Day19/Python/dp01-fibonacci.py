from time import time


rec_count = 0
mem_count = 0
dp_count = 0


def rec_fibo(n):
    global rec_count
    rec_count = rec_count + 1
    if n == 1 or n == 2:
        return 1
    nterm = rec_fibo(n-1) + rec_fibo(n-2)
    return nterm


def mem_fibo(mem, n):
    global mem_count
    mem_count = mem_count + 1
    if mem[n] != -1:
        return mem[n]
    if n == 1 or n == 2:
        mem[n] = 1
        return 1
    mem[n] = mem_fibo(mem, n-1) + mem_fibo(mem, n-2)
    return mem[n]


def memo_fibo(n):
    mem = [-1] * (n+1)
    return mem_fibo(mem, n)


def tbl_fibo(mem, n):
    global dp_count
    dp_count = dp_count + 1
    mem[1] = mem[2] = 1
    for i in range(3, n+1):
        mem[i] = mem[i-1] + mem[i-2]
    return mem[n]


def dp_fibo(n):
    mem = [-1] * (n+1)
    return tbl_fibo(mem, n)


n = 40

t1 = time()
res1 = rec_fibo(n)
t2 = time()
print(f"recursive fibo term: {res1} in {t2 - t1} ms with {rec_count} fn calls")

t1 = time()
res2 = memo_fibo(n)
t2 = time()
print(f"memoized fibo term: {res2} in {t2 - t1} ms with {mem_count} fn calls")

t1 = time()
res3 = dp_fibo(n)
t2 = time()
print(f"dp fibo term: {res3} in {t2 - t1} ms with {dp_count} fn calls")
