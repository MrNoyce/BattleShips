
import java.util.Random;
        import java.util.Scanner;

public class BattleShips {

    private static Scanner userInput = new Scanner(System.in);
    private static String errorResponse = "Please enter a number between 0 and 9";
    private static String errorResponseLetter = "Please enter a number as you have entered a letter";
    private static String[][] oceanMap = new String[10][10]; //Ocean Map >> 2d Array 10 x 10
    private static String[][] oceanMapNoShips = new String[10][10]; //Ocean Map >> 2d Array 10 x 10

    private static void mapMaker() {
        //Create Numbers on the map
        System.out.println("  0123456789");
        for (int row = 0; row < oceanMap.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < oceanMap[row].length; col++) {
                if (oceanMap[row][col] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(oceanMap[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789");
        System.out.println();
    }
    private static void mapMakerNoShips() {
        //Create Numbers on the map
        System.out.println("  0123456789");
        for (int row = 0; row < oceanMapNoShips.length; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < oceanMapNoShips[row].length; col++) {
                if (oceanMapNoShips[row][col] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(oceanMapNoShips[row][col]);
                }
            }
            System.out.println("|" + row);
        }
        System.out.println("  0123456789");
    }

    private static void userDeployment() {
        /////////////////////////////////////////////
        //Get User input Start
        /////////////////////////////////////////////
        boolean checkerX = false;
        boolean checkerY = false;
        int checkerXVal = 0;
        int checkerYval = 0;
        int count = 0;

        System.out.println("Where do you want to deploy your ships?");
        System.out.println();

        while ( count < 5) {
            while (!checkerX) {
                System.out.print("Please enter your X Co-Ordinate: ");
                int userValX = userInput.nextInt();
                if (userValX <= 9 && userValX >= 0) {
                    checkerX = true;
                    checkerXVal = userValX;
                } else {
                    System.out.println(errorResponse);
                }
            }

            while (!checkerY) {
                System.out.print("Please enter your Y Co-Ordinate: ");
                int userValY = userInput.nextInt();
                if (userValY <= 9 && userValY >= 0) {
                    checkerY = true;
                    checkerYval = userValY;
                } else {
                    System.out.println(errorResponse);
                }
            }
            if (oceanMap[checkerXVal][checkerYval] != null) {
                System.out.println("This position has already been taken, please choose another place for your ship");
                count--;
            }else{
                oceanMap[checkerXVal][checkerYval] = "@";
            }
            checkerX = false;
            checkerY = false;
            count++;
            System.out.println("number of ships used = " + count);
        }
        /////////////////////////////////////////////
        //Get User input End
        /////////////////////////////////////////////
    }

    private static void  compDeployment() {
        /////////////////////////////////////////////
        //Get Computer input Start
        /////////////////////////////////////////////


        boolean checkerXComp = false;
        boolean checkerYComp = false;
        int checkerXValComp = 0;
        int checkerYvalComp = 0;
        int countComp = 0;
        Random compNumber = new Random();

        System.out.println("The computer is deploy ships");
        while ( countComp < 5) {
            while (!checkerXComp) {
                int compValX = (compNumber.nextInt(9));
                //System.out.println(compValX);
                if (compValX <= 9 && compValX >= 0) {
                    checkerXComp = true;
                    checkerXValComp = compValX;
                } else {
                    System.out.println(errorResponse);
                }
            }

            while (!checkerYComp) {
                int compValY =(compNumber.nextInt(9));
                //System.out.println(compValY);
                if (compValY <= 9 && compValY >= 0) {
                    checkerYComp = true;
                    checkerYvalComp = compValY;
                } else {
                    System.out.println(errorResponse);
                }
            }
            if (oceanMap[checkerXValComp][checkerYvalComp] != null) {
                System.out.println("This position has already been taken, please choose another place for your ship");
                countComp--;
            }else{
                oceanMap[checkerXValComp][checkerYvalComp] = "#";
            }
            checkerXComp = false;
            checkerYComp = false;
            countComp++;
            System.out.println(countComp + ". ship Deployed");
            System.out.println();
        }
        /////////////////////////////////////////////
        //Get Computer input End
        /////////////////////////////////////////////

    }

    private static void  startGame() {

        boolean gameOver = false;
        boolean stateCheckerUser = false;
        boolean stateCheckerComp = false;
        int compShips = 5;
        int userShips = 5;
        int userStrike = 0;
        int compStrike = 0;

        while (!gameOver) {
            while (!stateCheckerUser) {
                if (gameOver && stateCheckerComp) {
                    System.out.println("Game Over The Computer Wins");
                    stateCheckerUser = true;
                } else {
                    userStrike += userGuess();
                    mapMakerNoShips();
                    if (userStrike == compShips) {
                        stateCheckerUser = true;
                        gameOver = true;
                        System.out.println("Game Over, Player 1 Wins");
                    } else {
                        stateCheckerUser = true;
                        stateCheckerComp = false;
                    }
                }

            }

            while (!stateCheckerComp) {
                if (gameOver && stateCheckerUser) {
                    System.out.println("Game Over Player 1 Wins");
                    stateCheckerComp = true;
                } else {
                    compStrike += compGuess();
                    mapMakerNoShips();
                    if (compStrike == userShips) {
                        stateCheckerComp = true;
                        gameOver = true;
                        System.out.println("Game Over, The Computer Wins");
                    } else {
                        stateCheckerComp = true;
                        stateCheckerUser = false;
                    }
                }

            }
        }
    }

    private static int userGuess (){

        boolean userGuessChecker = false;
        boolean userGuessCheckerX = false;
        boolean userGuessCheckerY = false;
        int userGuessX = 0;
        int userGuessY = 0;
        int hitValue = 0;


        System.out.println("Enter your X and Y Co-Ordinates to lunch your attack! ");
        System.out.println();


        while (!userGuessChecker && !userGuessCheckerX && !userGuessCheckerY) {
            while (!userGuessCheckerX) {
                System.out.print("Enter your X value: ");
                if (userInput.hasNextInt()) {
                    userGuessX = userInput.nextInt();
                    if (userGuessX < 10 && userGuessX >= 0) {
                        userGuessCheckerX = true;
                    } else {
                        System.out.println(errorResponse);
                    }
                }else {
                    System.out.println(errorResponseLetter);
                    userInput.next(); /// God Send :)
                }
            }

            while (!userGuessCheckerY) {
                System.out.print("Enter your Y value: ");
                if (userInput.hasNextInt()) {
                    userGuessY = userInput.nextInt();
                    if (userGuessY < 10 && userGuessY >= 0) {
                        userGuessCheckerY = true;
                    } else {
                        System.out.println(errorResponse);
                    }
                }else {
                    System.out.println(errorResponseLetter);
                    userInput.next();
                }
            }

            if (oceanMap[userGuessX][userGuessY] != null) {
                // if cell == 1
                if( oceanMap[userGuessX][userGuessY] == "1" ){
                    System.out.println("You have already selected this position, please choose again");
                    userGuessCheckerX = false;
                    userGuessCheckerY = false;
                }
                // if cell == 2
                else if( oceanMap[userGuessX][userGuessY] == "2" ){
                    System.out.println("The Computer has already chosen this position, please choose again");
                    userGuessCheckerX = false;
                    userGuessCheckerY = false;
                }
                else if( oceanMap[userGuessX][userGuessY] == "@" ) {
                    System.out.println("You cant fire on your own position, please choose again");
                    userGuessCheckerX = false;
                    userGuessCheckerY = false;
                }
                else if( oceanMap[userGuessX][userGuessY] == "*" ) {
                    System.out.println("The computer has already sunk your ship at this position, please choose again");
                    userGuessCheckerX = false;
                    userGuessCheckerY = false;
                }
                else if( oceanMap[userGuessX][userGuessY] == "^" ) {
                    System.out.println("You have already sunk this ship, please choose again");
                    userGuessCheckerX = false;
                    userGuessCheckerY = false;
                } else {
                    // else update the cell
                    System.out.println("HIT");
                    oceanMap[userGuessX][userGuessY] = "^";
                    oceanMapNoShips[userGuessX][userGuessY] = "^";
                    hitValue ++;
                    userGuessChecker = true;
                }

            }else{
                System.out.println("MISS");
                oceanMap[userGuessX][userGuessY] = "1";
                oceanMapNoShips[userGuessX][userGuessY] = "1";
                userGuessChecker = true;
            }

        }
        return hitValue;
    }

    private static int compGuess (){

        boolean compGuessChecker = false;
        boolean compGuessCheckerX = false;
        boolean compGuessCheckerY = false;
        int compGuessX = 0;
        int compGuessY = 0;
        int compHitValue = 0;
        Random compGuessNumber = new Random();

        System.out.println("The computer is now attacking");
        System.out.println();

        while (!compGuessChecker && !compGuessCheckerX && !compGuessCheckerY) {
            while (!compGuessCheckerX) {
                compGuessX = (compGuessNumber.nextInt(9));
                if (compGuessX <= 9 && compGuessX >= 0) {
                    compGuessCheckerX = true;
                    System.out.println(compGuessX);
                } else {
                    System.out.println(errorResponse);
                }
            }


            while (!compGuessCheckerY) {
                compGuessY = (compGuessNumber.nextInt(9));
                if (compGuessY <= 9 && compGuessY >= 0) {
                    compGuessCheckerY = true;
                    System.out.println(compGuessY);
                } else {
                    System.out.println(errorResponse);
                }

            }
            if (oceanMap[compGuessX][compGuessY] != null) {
                // if cell == 1
                if( oceanMap[compGuessX][compGuessY] == "1" ){
                    System.out.println("Player1 has already selected this position, please choose again");
                    compGuessCheckerX = false;
                    compGuessCheckerY = false;
                }
                // if cell == 2
                else if( oceanMap[compGuessX][compGuessY] == "2" ){
                    System.out.println("You have already chosen this position, please choose again");
                    compGuessCheckerX = false;
                    compGuessCheckerY = false;
                }
                else if( oceanMap[compGuessX][compGuessY] == "#" ) {
                    System.out.println("You cant fire on your own position, please choose again");
                    compGuessCheckerX = false;
                    compGuessCheckerY = false;
                }
                else if( oceanMap[compGuessX][compGuessY] == "^" ) {
                    System.out.println("You have already sunk this ship, please choose again");
                    compGuessCheckerX = false;
                    compGuessCheckerY = false;
                }
                else if( oceanMap[compGuessX][compGuessY] == "*" ) {
                    System.out.println("Player has sunk your ship at this position, please choose again");
                    compGuessCheckerX = false;
                    compGuessCheckerY = false;
                }else {
                    // else update the cell
                    System.out.println("HIT");
                    oceanMap[compGuessX][compGuessY] = "*";
                    oceanMapNoShips[compGuessX][compGuessY] = "*";
                    compHitValue ++;
                    compGuessChecker = true;
                }

            }else{
                System.out.println("MISS");
                oceanMap[compGuessX][compGuessY] = "2";
                oceanMapNoShips[compGuessX][compGuessY] = "2";
                compGuessChecker = true;
            }
        }
        return compHitValue;
    }


    public static void main (String[] args) {

        System.out.println("----- Welcome to the Battle Ships Game -----");
        System.out.println();

        mapMaker();
        userDeployment();
        compDeployment();
        mapMakerNoShips();
        startGame();


            }

}
