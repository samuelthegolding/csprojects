import numpy as np

# Ask for input from user here
N = int(input("Enter how many rows or columns the square matrix (N x N) will have: "))

print("Enter the matrix row by row, separate each spot with a space:")
A = []
for i in range(N):
    row = list(map(float, input(f"Row {i+1}: ").split()))
    A.append(row)

# Set up L and U for Crout's Method
L=[]
for i in range(N):
    Lrow = []
    for j in range(N):
        Lrow.append(0.0)
    L.append(Lrow)
# Put 1's on the diagonal for U for Crout's method
U = []
for i in range(N):
    Urow = []
    for j in range(N):
        if i == j: 
            Urow.append(1.0) 
        else: 
            Urow.append (0.0)
    U.append(Urow)


# Crout's LU Decomposition
for j in range(N):
    for i in range(j, N):
        total = 0.0
        for k in range(j):
            total += L[i][k] * U[k][j]
        L[i][j] = A[i][j] - total

    for i in range(j + 1, N):
        total = 0.0
        for k in range(j):
            total += L[j][k] * U[k][i]
        U[j][i] = (A[j][i] - total) / L[j][j]

# Forward and backward substitution to find the inverse
inv_matrix = []

for col in range(N):
    # Made a unit vector "e" to find the inverse
    e = [0.0 for _ in range(N)]
    e[col] = 1.0

    # Forward solve: L * y = e
    y = [0.0 for _ in range(N)]
    for i in range(N):
        total = 0.0
        for j in range(i):
            total += L[i][j] * y[j]
            if L[i][i] == 0:
                print("Please do a dependent matrix")
        y[i] = (e[i] - total) / L[i][i]

    # Backward solve: U * x = y
    x = [0.0 for _ in range(N)]
    for i in range(N-1, -1, -1):
        total = 0.0
        for j in range(i+1, N):
            total += U[i][j] * x[j]
        x[i] = y[i] - total

    # Append the column to the inverse matrix
    inv_matrix.append(x)

# Transpose the result because I built it column by column due to forward and backward sub
inv_matrix = list(map(list, zip(*inv_matrix)))

# Display results
print("\nOriginal Matrix:")
for row in A:
    print(row)

print("\nL matrix:")
for row in L:
    print(row)

print("\nU matrix:")
for row in U:
    print(row)

print("\nInverse matrix using LU decomposition:")
for row in inv_matrix:
    print(row)

# Compare answer with NumPy
np_inv = np.linalg.inv(np.array(A))

print("\nInverse matrix using NumPy (for verification):")
print(np_inv)

# Accuracy check
error = np.max(np.abs(np.array(inv_matrix) - np_inv))
print(f"\nMaximum absolute difference with NumPy inverse: {error:.2e}")