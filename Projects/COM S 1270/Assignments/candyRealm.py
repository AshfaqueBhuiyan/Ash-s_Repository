# Muhammad Ashfaque Bhuiyan                                                                 11-21-2023
# Assignment-6
# Description: "Candy Realm" is a candy-themed board game with player vs player and player vs AI modes.

# CITATION:https://www.geekyhobbies.com/candy-land-board-game-rules-and-instructions-for-how-to-play/
#          https://instructions.hasbro.com/en-us/instruction/Candy-Land-Game
#          https://www.wikihow.com/Play-Candy-Land

# CITATION: ACCESSED: 11-17-2023

import random
import colorama

colorama.init()

board = {
    "Red": "Cherry Popper",
    "Orange": "Tangy Orange",
    "Yellow": "Lemonade",
    "Green": "Sour Apple",
    "Blue": "Blue Raspberry",
    "Purple": "Sweet Grape"
}

players = {
    "Player 1": 0,
    "Player 2": 0,
    "Player 3": 0,
    "Player 4": 0,
    "AI": 0
}

def draw_card():
    return random.randint(1, 6)

def print_board():
    print("\nCandy Realm Board:")
    for color, candy in board.items():
        print(f"{color}: {candy}")

def player_vs_player():
    print("Player vs Player mode selected!")

    while True:
        num_players = input("Enter the number of players (2 to 4): ")

        if num_players.isdigit():
            num_players = int(num_players)
            if 2 <= num_players <= 4:
                for i in range(1, num_players + 1):
                    players[f"Player {i}"] = 0

                while True:
                    for i in range(1, num_players + 1):
                        player_name = f"Player {i}"
                        if move_player(player_name):
                            print(f"{player_name} wins!")
                            return
            else:
                print("Invalid number of players. Please enter a number between 2 and 4.")
        else:
            print("Invalid input. Please enter a valid number.")

def player_vs_ai():
    print("Player vs AI mode selected!")
    while True:
        for player in players:
            if player == "Player 1":
                if move_player(player):
                    print(f"{player} wins!")
                    return
            elif player == "AI":
                if move_player(player):
                    print(f"{player} wins!")
                    return

def move_player(player):
    current_position = players[player]
    print(f"{player} is at {list(board.keys())[current_position]}")

    if player == "AI":
        steps = draw_card()
        print(f"AI drew a {steps}!")
        new_position = current_position + steps
        if new_position >= len(board):
            print(f"AI reached the end!")
            return True
        players[player] = new_position
        print(f"AI moves to {list(board.keys())[new_position]}")
        return False

    user_input = input(f"{player}, type 'roll' to draw a card and move, or 'quit' to end the game: ")
    if user_input.lower() == "roll":
        steps = draw_card()
        print(f"{player} drew a {steps}!")
        new_position = current_position + steps
        if new_position >= len(board):
            print(f"{player} reached the end!")
            return True
        players[player] = new_position
        print(f"{player} moves to {list(board.keys())[new_position]}")
        return False
    elif user_input.lower() == "quit":
        print("Game ended.")
        exit()
    else:
        print("Invalid input. Please type 'roll' to continue or 'quit' to end the game.")
        return move_player(player)


def printTitleMaterial():
    """Prints the title material for the game, including the student's name, class, and section number.
    """
    print("Candy Realm!")
    print()
    print("Created By: Ashfaque Bhuiyan")
    print("[COM S 127 G]")
    print()
    print("[p]lay, [i]nformation, [q]uit")

def main():
    while True:
        printTitleMaterial()
        choice = input("Enter your choice: ")

        if choice.lower() == "p":
            print("You selected Play!")
            print("Select Game Mode:")
            print("[p]layer vs player")
            print("[a]i vs player")
            mode_choice = input("Enter your choice: ")

            players["Player 1"] = 0
            players["Player 2"] = 0
            players["Player 3"] = 0
            players["Player 4"] = 0
            players["AI"] = 0

            if mode_choice.lower() == "p":
                player_vs_player()
            elif mode_choice.lower() == "a":
                player_vs_ai()
            else:
                print("Invalid choice. Please select again.")
        elif choice.lower() == "i":
            print("'Candy Realm' is a game where players navigate through a board of candy-themed spaces.")
            print("You can choose to play against another player or against the AI. Enjoy Playing in the Candy Realm!")
        elif choice.lower() == "q":
            print("Farewell! Thanks for for visiting the Candy Realm! If you ever crave more sweet adventures, we'll be here ;).")
            break
        else:
            print("Invalid choice. Please select again.")

if __name__ == "__main__":
    main()