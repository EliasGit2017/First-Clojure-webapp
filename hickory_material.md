# Some Bootiful code examples I keep:

These Codes are small pieces of what I find elegant and precise examples of some of my favorites languages.

 

```python
#  Memoization through functions
def memoize(f):
    memo = dict()
    def helper(x):
        if x not in memo:
            memo[x] = f(x)
        return memo[x]
    return helper

@memoize
def fib(x):
    if x == 0:
        return 0
    elif x == 1:
        return  1
    else:
        return fib(x - 1) + fib(x - 2)

# Could have also used memoization through fib = memoize(fib)

#%
# Memoization through a callable class

class Memoize:

    def __init__(self, fn):
        self.fn = fn
        self.memo = {}
    
    def __call__(self, *args):
        if args not in self.memo:
            self.memo[args] = self.fn(*args)
        return self.memo[args]

@Memoize
def fib(x):
    if x == 0:
        return 0
    elif x == 1:
        return  1
    else:
        return fib(x - 1) + fib(x - 2)
    
print(fib(200))
```

##### Quines:

```python
_='_=%r;print _%%_';print _%_
```

