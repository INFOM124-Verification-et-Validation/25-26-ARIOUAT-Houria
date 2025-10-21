# Specification-based Testing

## 1. Goal, inputs and outputs
- Goal: Vérifier que le comportement de Clyde est correct en fonction de sa distance avec PacMan
- Input domain: Distance entre Clyde et PacMan
- Output domain: Clyde fuit si il est a moins de 8 cases de PacMan. Clyde se rapproche si il est a plus de 8 cases.

## 2. Explore the program (if needed)

## 3. Identify input and output partitions

### Input partitions
input:
Partition 1 : Distance <= 8 : Clyde est proche
Partition 2 : Distance > 8 : Clyde est loin

#### Individual inputs

#### Combinations of input values

### Output partitions
Output :
Partition 1 : Distance <= 8 : Clyde fuit
Partition 2 : Distance > 8 : Clyde se rapproche

## 4. Identify boundaries
Distance = 8 : Clyde fuit
Distance = 7 : Clyde fuit
Distance = 9 ; Clyde se rapproche


## 5. Select test cases
Test distance = 8
Test distance = 9
test distance = 7