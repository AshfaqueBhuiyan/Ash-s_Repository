# Ash Bhuiyan                                                                       09-15-2023
# Assignment-2
# This assignment consists of codes that upon execution requires input from the user to practice working with mathematical operators, functions, and the random module.

#Introduction
print("Lucky Calculator!")
print()

print("By: Ash Bhuiyan")
print("[COM S 1270 A]")
print()

import random

# Function to get an integer input from the user
def get_integer_input(prompt):
    while True:
        try:
            value = int(input(prompt))
            return value
        except ValueError:
            print("ERROR: Please enter a valid integer.")

# Function for Addition operation
def add(a, b):
    return a + b

# Function for Subtraction operation
def subtract(a, b):
    return a - b

# Function for Multiplication operation
def multiply(a, b):
    return a * b

# Function for Division operation
def divide(a, b):
    if b == 0:
        print("ERROR: Division by zero. Changing the denominator to 1.")
        b = 1
    return a / b

# Function for Integer Division operation
def integer_divide(a, b):
    if b == 0:
        print("ERROR: Division by zero. Changing the denominator to 1.")
        b = 1
    return a // b

# Function for Modulus Operation
def modulus(a, b):
    if b == 0:
        print("ERROR: Division by zero. Changing the denominator to 1.")
        b = 1
    return a % b

# Function for Exponentiation operation
def exponentiate(a, b):
    return a ** b

# Function to calculate a Lucky number
def lucky_number(a, b):
    return random.randint(min(a, b), max(a, b) + 1)

# Main program - Determine initial player choice:
while True:
    print("Welcome to the Lucky Calculator!"
          " What would you like to do?")
    print("Choose an option:")
    print("[c]alculator, [l]ucky number, [q]uit")
    
    choice = input("Enter your choice: ").strip().lower()
    
    if choice == 'c':
        operation = input("Enter the operation (+, -, *, /, //, %, **): ").strip()
        
        if operation not in ('+', '-', '*', '/', '//', '%', '**'):
            print("ERROR: You must enter either '+', '-', '*', '/', '//', '%', or '**'")
            continue
        
        left_operand = get_integer_input("Enter the left operand: ")
        right_operand = get_integer_input("Enter the right operand: ")
        
        if operation == '+':
            answer = add(left_operand, right_operand)
        elif operation == '-':
            answer = subtract(left_operand, right_operand)
        elif operation == '*':
            answer = multiply(left_operand, right_operand)
        elif operation == '/':
            answer = divide(left_operand, right_operand)
        elif operation == '//':
            answer = integer_divide(left_operand, right_operand)
        elif operation == '%':
            answer = modulus(left_operand, right_operand)
        elif operation == '**':
            answer = exponentiate(left_operand, right_operand)
        
        print("The result of your calculation is: {0}".format(answer))
    
    elif choice == 'l':
        a = get_integer_input("Enter the first number: ")
        b = get_integer_input("Enter the second number: ")
        answer = lucky_number(a, b)
        print("Your lucky number is: {0}".format(answer))
    
    elif choice == 'q':
        print("Sorry to see you go...Until next time...Goodbye!")
        break
    
    else:
        print("ERROR: I did not understand your input. Please try again.")
