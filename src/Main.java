import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("Welcome to the Bluejack Game.");
        System.out.println();

        String[] gameDeckColor = {"blue", "yellow", "red", "green"};
        int[] gameDeck = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Card[] Deck = new Card[40];

        Card[] computerDeck = new Card[10];
        Card[] userDeck = new Card[10];

        Card[] computerHand = new Card[4];
        Card[] userHand = new Card[4];

        int l = 0;
        for (int i = 0; i < gameDeckColor.length; i++) {
            for (int j = 0; j < gameDeck.length; j++) {
                Deck[l++] = new Card(gameDeckColor[i], gameDeck[j], 1);
            }
        }
        System.out.println("**********  The deck is shuffling... **********");
        System.out.println();

        Shuffle(Deck);
        for (int c = 0; c < Deck.length; c++) {
            System.out.println(Deck[c]);
        }

        System.out.println("********************");

        Dealing(Deck, computerDeck, userDeck);

        int[] remainingCard = {1, 2, 3, 4, 5, 6};
        int[] sign = {-1, +1};

        System.out.println();

        //Player's remain cards (with sign)
        System.out.println("Player's Remain Cards are ");
        for (int i = 0; i < 3; i++) {
            userDeck[i + 5] = remainRandomCard(gameDeckColor, remainingCard, sign);
        }

        System.out.println();

        //Computer's remain cards (with sign)
        System.out.println("Computer's Remain Cards are ");
        for (int i = 0; i < 3; i++) {
            computerDeck[i + 5] = remainRandomCard(gameDeckColor, remainingCard, sign);
        }

        System.out.println("--------------------");

        Random r = new Random();
        // for user
        for (int i = 0; i < 2; i++) {
            int a = r.nextInt(10);
            System.out.println();
            System.out.println("* Randomly selected card is " + a + " *");

            if (a == 0) {
                System.out.println("Player's Remaining Card is flip card! ");
                userDeck[i + 8] = new Card("flip");

            } else if (a == 1) {
                System.out.println("Player's Remaining Card is double card! ");
                userDeck[i + 8] = new Card("double");

            } else {
                System.out.println("Player's Remain Card is ");
                userDeck[i + 8] = remainRandomCard(gameDeckColor, remainingCard, sign);
            }
        }
        System.out.println("--------------------");

        // for computer
        for (int i = 0; i < 2; i++) {
            int b = r.nextInt(10);
            System.out.println();
            System.out.println("* Randomly selected card is " + b + " *");

            if (b == 0) {
                System.out.println("Computer's Remaining Card is flip card! ");
                computerDeck[i + 8] = new Card("flip");
            } else if (b == 1) {
                System.out.println("Computer's Remaining Card is double card! ");
                computerDeck[i + 8] = new Card("double");
            } else {
                System.out.println("Computer's Remain Card is ");
                computerDeck[i + 8] = remainRandomCard(gameDeckColor, remainingCard, sign);
            }
        }
        System.out.println("--------------------");
        System.out.println();
        fourCards(computerDeck, userDeck, computerHand, userHand);
        System.out.println();

        System.out.println("*** After the player and the computer are given 5 cards, the remaining game cards are as follows: ***");
        for (int i = Deck.length - 35; i < Deck.length - 5; i++) {
            System.out.println(Deck[i]);
        }
        System.out.println();
        System.out.println("---------- THE GAME BEGINS ----------");
        System.out.println();

        int userPoint = 0;
        int computerPoint = 0;
        Card[] userBoard = new Card[9];
        Card[] computerBoard = new Card[9];

        System.out.println("First, it is the player's turn and after it is the computer's turn.");
        System.out.println("If you want to draw a card from the game deck, enter 1");
        System.out.println("If you want to stand, enter 2 (if you stand, the turn passes to the other player)");

        for (int i = 0; i < userHand.length; i++) {
            System.out.println("Player's hand is: " + userHand[i]);
        }
        System.out.println();
        for (int i = 0; i < computerHand.length; i++) {
            System.out.println("Computer's hand is: X");  // If we write computerHand [i], we can see the cards in the computer's hand.
        }
        Scanner sc = new Scanner(System.in);
            while (userPoint < 3 && computerPoint < 3) {
                while (true) {
                    System.out.println();
                    System.out.print("Please enter your choice: ");
                    int choice = sc.nextInt();

                    if (choice == 1) {
                        drawTopCard(Deck, userBoard);
                        System.out.println("Sum = " + sum(userBoard));

                        if (sum(userBoard) > 20) {
                            System.out.println("Your score is = " + sum(userBoard) + ". You busted, computer won. ");
                            computerPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            break;
                        } else if (sum(userBoard) == 20) {
                            System.out.println("You won this set! ");
                            userPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            break;
                        }
                        System.out.println("If you want to draw a card from the Player Hand in your hand, enter 3");
                        System.out.println("If you want to continue drawing cards from the Game Deck, enter any number except 3");
                        int a = sc.nextInt();
                        if (a == 3) {
                            for (int i = 0; i < userHand.length; i++) {
                                System.out.println("Player's hand is: " + userHand[i]);
                            }
                            System.out.println();
                            System.out.println("If you want the 1st number, enter 1\n" +
                                    "If you want the 2nd number, enter 2\n" +
                                    "If you want the 3rd number, enter 3\n" +
                                    "If you want the 4th number, enter 4");

                            int enter = sc.nextInt();
                            if (enter == 1) {
                                for (int i = 0; i < userBoard.length; i++) {
                                    if (userBoard[i] == null) {
                                        userBoard[i] = userHand[0];
                                        break;
                                    }
                                }
                                System.out.println("The card you choose: " + userHand[0]);
                                System.out.println("Sum is = " + sum(userBoard));

                                if (userHand[0].getSpecial().equals("double")) {
                                    int dbl = sum(userBoard) * 2;
                                    System.out.println("New value is = " + dbl);
                                    int sum = dbl + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                } else if (userHand[0].getSpecial().equals("flip")) {
                                    int flip = sum(userBoard) * -1;
                                    System.out.println("New value is = " + flip);
                                    int sum = flip + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                }
                                if (sum(userBoard) > 20) {
                                    System.out.println("Your score is = " + sum(userBoard) + ". You busted, computer won. ");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                } else if (sum(userBoard) == 20) {
                                    System.out.println("You won this set! ");
                                    userPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                }
                                userHand[0] = null;

                            } else if (enter == 2) {
                                for (int i = 0; i < userBoard.length; i++) {
                                    if (userBoard[i] == null) {
                                        userBoard[i] = userHand[1];
                                        break;
                                    }
                                }
                                System.out.println("The card you choose: " + userHand[1]);
                                System.out.println("Sum is = " + sum(userBoard));

                                if (userHand[1].getSpecial().equals("double")) {
                                   int dbl = sum(userBoard) * 2;
                                    System.out.println("New value is = " + dbl);
                                    int sum = dbl + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                } else if (userHand[1].getSpecial().equals("flip")) {
                                    int flip = sum(userBoard) * -1;
                                    System.out.println("New value is = " + flip);
                                    int sum = flip + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                }
                                if (sum(userBoard) > 20) {
                                    System.out.println("Your score is = " + sum(userBoard) + ". You busted, computer won. ");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                } else if (sum(userBoard) == 20) {
                                    System.out.println("You won this set! ");
                                    userPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                }
                                userHand[1] = null;

                            } else if (enter == 3) {
                                for (int i = 0; i < userBoard.length; i++) {
                                    if (userBoard[i] == null) {
                                        userBoard[i] = userHand[2];
                                        break;
                                    }
                                }
                                System.out.println("The card you choose: " + userHand[2]);
                                System.out.println("Sum is = " + sum(userBoard));

                                if (userHand[2].getSpecial().equals("double")) {
                                    int dbl = sum(userBoard) * 2;
                                    System.out.println("New value is = " + dbl);
                                    int sum = dbl + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                } else if (userHand[2].getSpecial().equals("flip")) {
                                    int flip = sum(userBoard) * -1;
                                    System.out.println("New value is = " + flip);
                                    int sum = flip + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                }
                                if (sum(userBoard) > 20) {
                                    System.out.println("Your score is = " + sum(userBoard) + ". You busted, computer won. ");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                } else if (sum(userBoard) == 20) {
                                    System.out.println("You won this set! ");
                                    userPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                }
                                userHand[2] = null;

                            } else if (enter == 4) {
                                for (int i = 0; i < userBoard.length; i++) {
                                    if (userBoard[i] == null) {
                                        userBoard[i] = userHand[3];
                                        break;
                                    }
                                }
                                System.out.println("The card you choose: " + userHand[3]);
                                System.out.println("Sum is = " + sum(userBoard));

                                if (userHand[3].getSpecial().equals("double")) {
                                    int dbl = sum(userBoard) * 2;
                                    System.out.println("New value is = " + dbl);
                                    int sum = dbl + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                } else if (userHand[3].getSpecial().equals("flip")) {
                                    int flip = sum(userBoard) * -1;
                                    System.out.println("New value is = " + flip);
                                    int sum = flip + sum(userBoard);
                                    System.out.println("Sum = " + sum);
                                }
                                if (sum(userBoard) > 20) {
                                    System.out.println("Your score is = " + sum(userBoard) + ". You busted, computer won. ");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                } else if (sum(userBoard) == 20) {
                                    System.out.println("You won this set! ");
                                    userPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                }
                                userHand[3] = null;
                            }
                            if (sum(userBoard) > 20) {
                                System.out.println("Your score is = " + sum(userBoard) + ". You busted, computer won. ");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            } else if (sum(userBoard) == 20) {
                                System.out.println("You won this set! ");
                                userPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            }
                        }
                        System.out.println();
                        System.out.println("----- It is computer's turn. -----");
                        System.out.println();
                        if (sum1(computerBoard) <= 15) {
                            drawTopCard2(Deck, computerBoard);
                            System.out.println("Computer sum = " + sum1(computerBoard));
                            if (sum1(computerBoard) > 20) {
                                System.out.println("Computer's score is = " + sum1(computerBoard) + ". Computer busted, player won.");
                                userPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            } else if (sum1(computerBoard) == 20) {
                                System.out.println("Computer won this set!");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            }
                        } else if (sum1(computerBoard) > 15 && sum1(computerBoard) < 17) {
                            System.out.println("Computer sum = " + sum1(computerBoard));

                            if (computerHand[0].getValue() + sum1(computerBoard) == 20) {
                                System.out.println("Computer draws a card from the Computer Hand");
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                    computerBoard[i] = computerHand[0];
                                    break;
                                }
                            }
                            System.out.println("The card taken by computer: " + computerHand[0]);
                            System.out.println("Sum is = " + sum1(computerBoard));
                            System.out.println("Computer won this set!");
                            computerPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            computerHand[0] = null;
                            break;
                        } else if (computerHand[1].getValue() + sum1(computerBoard) == 20) {
                            System.out.println("Computer draws a card from the Computer Hand");
                            for (int i = 0; i < computerBoard.length; i++) {
                                if (computerBoard[i] == null) {
                                    computerBoard[i] = computerHand[1];
                                    break;
                                }
                            }
                            System.out.println("The card taken by computer: " + computerHand[1]);
                            System.out.println("Sum is = " + sum1(computerBoard));
                            System.out.println("Computer won this set!");
                            computerPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            computerHand[1] = null;
                            break;
                        } else if (computerHand[2].getValue() + sum1(computerBoard) == 20) {
                            System.out.println("Computer draws a card from the Computer Hand");
                            for (int i = 0; i < computerBoard.length; i++) {
                                if (computerBoard[i] == null) {
                                    computerBoard[i] = computerHand[2];
                                    break;
                                }
                            }
                            System.out.println("The card taken by computer: " + computerHand[2]);
                            System.out.println("Sum is = " + sum1(computerBoard));
                            System.out.println("Computer won this set!");
                            computerPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            computerHand[2] = null;
                            break;
                        } else if (computerHand[3].getValue() + sum1(computerBoard) == 20) {
                            System.out.println("Computer draws a card from the Computer Hand");
                            for (int i = 0; i < computerBoard.length; i++) {
                                if (computerBoard[i] == null) {
                                    computerBoard[i] = computerHand[3];
                                    break;
                                }
                            }
                            System.out.println("The card taken by computer: " + computerHand[3]);
                            System.out.println("Sum is = " + sum1(computerBoard));
                            System.out.println("Computer won this set!");
                            computerPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            computerHand[3] = null;
                            break;
                            } else {
                                System.out.println("Computer chose to stand.");

                            }
                        } else if (sum1(computerBoard) > 20) {
                            System.out.println("Computer's score is = " + sum1(computerBoard) + ". Computer busted, player won.");
                            userPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            break;
                        } else if (sum1(computerBoard) == 20) {
                            System.out.println("Computer won this set!");
                            computerPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            break;
                        } else {
                            System.out.println("Computer chose to stand.");
                            break;
                        }
                        System.out.println();
                        System.out.println("----- It is player's turn. -----");

                    } else if (choice == 2) {
                        System.out.println("----- Your turn is over, it is computer's turn. -----");
                        if (sum1(computerBoard) <= 15) {
                            drawTopCard2(Deck, computerBoard);
                            System.out.println("Computer sum = " + sum1(computerBoard));
                            if (sum1(computerBoard) > 20) {
                                System.out.println("Computer's score is = " + sum1(computerBoard) + ". Computer busted, player won.");
                                userPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            } else if (sum1(computerBoard) == 20) {
                                System.out.println("Computer won this set!");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            } else if (sum1(computerBoard) < 20) {
                                if (computerHand[0].getValue() + sum1(computerBoard) == 20) {
                                    System.out.println("Computer draws a card from the Computer Hand");
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[0];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[0]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    System.out.println("Computer won this set!");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    computerHand[0] = null;
                                    break;

                                } else if (computerHand[1].getValue() + sum1(computerBoard) == 20) {
                                    System.out.println("Computer draws a card from the Computer Hand");
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[1];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[1]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    System.out.println("Computer won this set!");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    computerHand[1] = null;
                                    break;
                                } else if (computerHand[2].getValue() + sum1(computerBoard) == 20) {
                                    System.out.println("Computer draws a card from the Computer Hand");
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[2];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[2]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    System.out.println("Computer won this set!");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    computerHand[2] = null;
                                    break;
                                } else if (computerHand[3].getValue() + sum1(computerBoard) == 20) {
                                    System.out.println("Computer draws a card from the Computer Hand");
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[3];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[3]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    System.out.println("Computer won this set!");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    computerHand[3] = null;
                                    break;
                                } else if (computerHand[0].getValue() + sum1(computerBoard) < 20) {
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[0];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[0]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    computerHand[0] = null;
                                    break;
                                } else if (computerHand[1].getValue() + sum1(computerBoard) < 20) {
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[1];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[1]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    computerHand[1] = null;
                                    break;
                                } else if (computerHand[2].getValue() + sum1(computerBoard) < 20) {
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[2];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[2]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    computerHand[2] = null;
                                    break;
                                } else if (computerHand[3].getValue() + sum1(computerBoard) < 20) {
                                    for (int i = 0; i < computerBoard.length; i++) {
                                        if (computerBoard[i] == null) {
                                            computerBoard[i] = computerHand[3];
                                            break;
                                        }
                                    }
                                    System.out.println("The card taken by computer: " + computerHand[3]);
                                    System.out.println("Sum is = " + sum1(computerBoard));
                                    computerHand[3] = null;
                                    break;
                                } else {
                                    System.out.println("Computer chose to stand.");
                                    if (sum(userBoard) > sum1(computerBoard)) {
                                        System.out.println("The player's score is greater than the computer");
                                        userPoint++;
                                        System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                        for (int i = 0; i < userBoard.length; i++) {
                                            userBoard[i] = null;
                                        }
                                        for (int j = 0; j < computerBoard.length; j++) {
                                            computerBoard[j] = null;
                                        }
                                        break;
                                    } else if (sum(userBoard) < sum1(computerBoard)) {
                                        System.out.println("The computer's score is greater than the player");
                                        computerPoint++;
                                        System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                        for (int i = 0; i < userBoard.length; i++) {
                                            userBoard[i] = null;
                                        }
                                        for (int j = 0; j < computerBoard.length; j++) {
                                            computerBoard[j] = null;
                                        }
                                        break;
                                    } else {
                                        System.out.println("The game ended in a draw.");
                                        System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                        for (int i = 0; i < userBoard.length; i++) {
                                            userBoard[i] = null;
                                        }
                                        for (int j = 0; j < computerBoard.length; j++) {
                                            computerBoard[j] = null;
                                        }
                                        break;
                                    }
                                }
                            }
                        } else if (sum1(computerBoard) > 15 && sum1(computerBoard) < 17) {
                            System.out.println("Computer sum = " + sum1(computerBoard));

                            if (computerHand[0].getValue() + sum1(computerBoard) == 20) {
                                System.out.println("Computer draws a card from the Computer Hand");
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[0];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[0]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                System.out.println("Computer won this set!");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                computerHand[0] = null;
                                break;

                            } else if (computerHand[1].getValue() + sum1(computerBoard) == 20) {
                                System.out.println("Computer draws a card from the Computer Hand");
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[1];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[1]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                System.out.println("Computer won this set!");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                computerHand[1] = null;
                                break;
                            } else if (computerHand[2].getValue() + sum1(computerBoard) == 20) {
                                System.out.println("Computer draws a card from the Computer Hand");
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[2];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[2]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                System.out.println("Computer won this set!");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                computerHand[2] = null;
                                break;
                            } else if (computerHand[3].getValue() + sum1(computerBoard) == 20) {
                                System.out.println("Computer draws a card from the Computer Hand");
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[3];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[3]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                System.out.println("Computer won this set!");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                computerHand[3] = null;
                                break;
                            } else if (computerHand[0].getValue() + sum1(computerBoard) < 20) {
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[0];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[0]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                computerHand[0] = null;
                                break;
                            } else if (computerHand[1].getValue() + sum1(computerBoard) < 20) {
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[1];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[1]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                computerHand[1] = null;
                                break;
                            } else if (computerHand[2].getValue() + sum1(computerBoard) < 20) {
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[2];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[2]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                computerHand[2] = null;
                                break;
                            } else if (computerHand[3].getValue() + sum1(computerBoard) < 20) {
                                for (int i = 0; i < computerBoard.length; i++) {
                                    if (computerBoard[i] == null) {
                                        computerBoard[i] = computerHand[3];
                                        break;
                                    }
                                }
                                System.out.println("The card taken by computer: " + computerHand[3]);
                                System.out.println("Sum is = " + sum1(computerBoard));
                                computerHand[3] = null;
                                break;
                            } else {
                                System.out.println("Computer chose to stand.");
                                if (sum(userBoard) > sum1(computerBoard)) {
                                    System.out.println("The player's score is greater than the computer");
                                    userPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                } else {
                                    System.out.println("The computer's score is greater than the player");
                                    computerPoint++;
                                    System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                    for (int i = 0; i < userBoard.length; i++) {
                                        userBoard[i] = null;
                                    }
                                    for (int j = 0; j < computerBoard.length; j++) {
                                        computerBoard[j] = null;
                                    }
                                    break;
                                }
                            }
                        } else if (sum1(computerBoard) > 20) {
                            System.out.println("Computer's score is = " + sum1(computerBoard) + ". Computer busted, player won.");
                            userPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            break;
                        } else if (sum1(computerBoard) == 20) {
                            System.out.println("Computer won this set!");
                            computerPoint++;
                            System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                            for (int i = 0; i < userBoard.length; i++) {
                                userBoard[i] = null;
                            }
                            for (int j = 0; j < computerBoard.length; j++) {
                                computerBoard[j] = null;
                            }
                            break;
                        } else {
                            System.out.println("Computer chose to stand.");
                            if (sum(userBoard) > sum1(computerBoard)) {
                                System.out.println("The player's score is greater than the computer");
                                userPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            } else if (sum(userBoard) < sum1(computerBoard)) {
                                System.out.println("The computer's score is greater than the player");
                                computerPoint++;
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            } else {
                                System.out.println("The game ended in a draw.");
                                System.out.println("Player's Score = " + userPoint + " , Computer's Score = " + computerPoint);
                                for (int i = 0; i < userBoard.length; i++) {
                                    userBoard[i] = null;
                                }
                                for (int j = 0; j < computerBoard.length; j++) {
                                    computerBoard[j] = null;
                                }
                                break;
                            }
                        }
                    }
                }
            } System.out.println("----------- Game Over ----------");

    }
    public static void Shuffle(Card[] Deck) {
        Random r = new Random();
        for (int i = 0; i < Deck.length; i++) {
            int a = r.nextInt(Deck.length);
            int b = r.nextInt(Deck.length);
            Swap(Deck, a, b);
        }
    }
    public static void Swap(Card[] Deck, int i, int j) {
        Card temp = Deck[i];
        Deck[i] = Deck[j];
        Deck[j] = temp;
    }
    public static void Dealing(Card [] Deck, Card [] computerDeck, Card [] userDeck){
        System.out.println();
        System.out.println("**********  Cards are dealing...  **********");
        System.out.println();

        for (int j=0; j<5; j++){
            // The card at the top is given to the computer.
            computerDeck[j] = Deck[j];
            System.out.println("Computer Cards are = " + computerDeck[j]);
        }
        System.out.println("********************");
        for (int k=Deck.length-1; k>=Deck.length-5; k--){
            // The card at the bottom is given to the user.
            userDeck[39-k] = Deck [k];
            System.out.println("Player Cards are = " + userDeck[39-k]);
        }
    }
    public static Card remainRandomCard(String[] colors, int[] values, int [] signs) {
        Random r = new Random();

        String color = colors[r.nextInt(colors.length)];
        int value = values[r.nextInt(values.length)];
        int sign = signs[r.nextInt(signs.length)];
        Card card1 = new Card(color, value, sign);
        System.out.println(card1);
        return card1;

    }
    public static void fourCards(Card[] computerDeck,Card[] userDeck, Card[] computerHand,Card[] userHand ){
        System.out.println("Total 10 arrays for computer : ");
        for (int i=0; i< computerDeck.length; i++){
            System.out.println(computerDeck[i]);
        }

        shuffleTenCards(computerDeck,userDeck);  //To prevent the same two numbers from coming randomly, we mix the numbers and print the first four
        System.out.println();
        System.out.println("Shuffling the computer deck: ");
        for (int c = 0; c < computerDeck.length; c++) {     // Checking to see if it is shuffled
            System.out.println(computerDeck[c]);
        } System.out.println();

        System.out.println("The four cards chosen for the computer are: ");
        for (int a=0; a<computerHand.length; a++){
            System.out.println(computerDeck[a]);
            computerHand[0] = computerDeck[0];
            computerHand[1] = computerDeck[1];
            computerHand[2] = computerDeck[2];
            computerHand[3] = computerDeck[3];
        }

        System.out.println();
        System.out.println("********************");
        System.out.println();

        System.out.println("Total 10 arrays for player : ");
        for (int i=0; i< userDeck.length; i++){
            System.out.println(userDeck[i]);
        }

        shuffleTenCards(computerDeck,userDeck); //To prevent the same two numbers from coming randomly, we mix the numbers and print the first four
        System.out.println();
        System.out.println("Shuffling the player deck: ");
        for (int c = 0; c < userDeck.length; c++) {     // Checking to see if it is shuffled
            System.out.println(userDeck[c]);
        } System.out.println();

        System.out.println("The four cards chosen for the player are: ");
        for (int a=0; a<userHand.length; a++) {
            System.out.println(userDeck[a]);
            userHand [0] = userDeck[0];
            userHand [1] = userDeck[1];
            userHand [2] = userDeck[2];
            userHand [3] = userDeck[3];
        }
        System.out.println("----------------------------");
        System.out.println();
    }

    public static void shuffleTenCards(Card [] computerDeck,Card [] userDeck){
        Random r = new Random();
        for (int i = 0; i < computerDeck.length; i++) {
            int a = r.nextInt(computerDeck.length);
            int b = r.nextInt(computerDeck.length);
            Card temp = computerDeck[a];
            computerDeck[a] = computerDeck[b];
            computerDeck[b] = temp;
        }

        for (int i = 0; i < userDeck.length; i++) {
            int c = r.nextInt(userDeck.length);
            int d = r.nextInt(userDeck.length);
            Card temp = userDeck[c];
            userDeck[c] = userDeck[d];
            userDeck[d] = temp;
        }
    }
    public static int gameDeckTop=3;
    public static void drawTopCard(Card [] Deck, Card[] userBoard) {
        if (gameDeckTop< Deck.length-5){
            gameDeckTop += 2;
            Card drawnCard = Deck[gameDeckTop];
            for (int i=0; i<userBoard.length; i++){
                if (userBoard[i] == null){
                    userBoard[i] = drawnCard;
                    break;
                }
            }
            System.out.println(drawnCard);
        } else {
            System.out.println("Cards sold out.");
        }
    }
    public static int gameDeckTop2=4;
    public static void drawTopCard2(Card [] Deck, Card[] computerBoard) {
        if (gameDeckTop2 < Deck.length - 5) {
            gameDeckTop2 += 2;
            Card drawnCard = Deck[gameDeckTop2];
            for (int i = 0; i < computerBoard.length; i++) {
                if (computerBoard[i] == null) {
                    computerBoard[i] = drawnCard;
                    break;
                }
            }
            System.out.println(drawnCard);
        } else {
            System.out.println("Cards sold out.");
        }
    }
    public static int sum (Card [] userBoard){
        int sum = 0;
        for (Card c: userBoard){
            if (c != null){
                sum += c.getValue();
            }
        } return sum;
    }
    public static int sum1 (Card [] computerBoard){
        int sum = 0;
        for (Card c: computerBoard){
            if (c != null){
                sum += c.getValue();
            }
        } return sum;
    }
}