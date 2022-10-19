# Motion Blur

This project calculates a multithread (and a singlethread) `Motion Blur`.

Let M1 be a matrix with pixels:

```
M1 = 1 2 3 4 5
     2 3 4 5 6
     3 4 5 6 7
     4 5 6 7 8
```

The formula to calculate the motion blur is:
```
result(x, y) = (M1(x, y)+M1(x-1, y)+M1(x, y-1)+M1(x, y+1)) / 4
```

Meaning that each entry of the matrix is an average of itself with the up, down and left neighbor pixels.

If these neighbor pixels are not defined, they shouldn't enter the calculus, for example:
```
result(0, 0) = (M1(0, 0)+M1(0, 1)) / 2
```

## Run

Run the `MotionBlurTest`.

