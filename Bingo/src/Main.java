import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    private static final String [] [] board01 = new String[5][5];
    private static final String [] [] board02 = new String[5][5];
    private static int bundleOfNumbers [] = new int[100];
    public static int timesYouFoundTheNumber = 0;

    private static int rowPositionOfTheFoundValue []= new int[25];

    private static int columnaPositionOfTheFoundValue [] = new int[25];

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu(){
        timesYouFoundTheNumber = 0;
        fillBoard(board01);
        fillBoard(board02);
        Arrays.fill(bundleOfNumbers, 0);
        Arrays.fill(rowPositionOfTheFoundValue, 0);
        Arrays.fill(columnaPositionOfTheFoundValue, 0);

        System.out.println();
        System.out.println("* * * * * * * * * * * * * * *");
        System.out.println("          BINGO MENU         ");
        System.out.println("* * * * * * * * * * * * * * *");
        System.out.println();
        System.out.println("1. Show Boards");
        System.out.println("2. Exit");
        System.out.print("> ");
        chooseOptionFromTheMainMenu();
    }

    public static void chooseOptionFromTheMainMenu(){
        Scanner inPut = new Scanner(System.in);
        String optionMenu;
        do {
            optionMenu = inPut.nextLine();
            switch (optionMenu){
                case "1":
                    showSubMenu();
                    break;
                case "2":
                    System.out.println("See you later...");
                    break;
                default:
                    System.out.println("Invalid option");
                    showMainMenu();
            }
        }while (!optionMenu.equals("2"));
    }

    public static void showSubMenu(){
        System.out.println();
        System.out.println("* * * * * * * * * * * * * * *");
        System.out.println("            BOARDS           ");
        System.out.println("* * * * * * * * * * * * * * *");
        System.out.println();
        System.out.println("1. Option One");
        System.out.println("2. Option Two");
        System.out.print("> ");
        chooseOptionFromTheSubMenu();
    }

    public static void chooseOptionFromTheSubMenu(){
        boolean defaultOption = false;
        Scanner inPut = new Scanner(System.in);
        String optionSubMenu;

        while (defaultOption == false){
            optionSubMenu = inPut.nextLine();
            switch (optionSubMenu){
                case "1":
                    showBoard(board01);
                    optionsPossibleSubMenu();
                    choosePossibleSubMenuOptions(board01);
                    break;
                case "2":
                    showBoard(board02);
                    optionsPossibleSubMenu();
                    choosePossibleSubMenuOptions(board02);
                    break;
                default:
                    System.out.println("Invalid option");
                    defaultOption = true;
                    showSubMenu();
            }
        }
    }

    public static void optionsPossibleSubMenu(){
        System.out.println("* * * * * * * * * * * * * * *");
        System.out.println(" Press  P  to play!");
        System.out.println(" Press X to go home");
        System.out.println(" Press B to go back!");
        System.out.println("* * * * * * * * * * * * * * *");
        System.out.print("> ");
    }

    public static void choosePossibleSubMenuOptions(String[][] board){
        Scanner inPut = new Scanner(System.in);
        String subOptionMenu;
        do {
            subOptionMenu = inPut.nextLine();
            switch (subOptionMenu){
                case "P":
                    game(board);
                    break;
                case "X":
                    showMainMenu();
                    break;
                case "B":
                    showSubMenu();
                    break;
                default:
                    System.out.println("Invalid option");
                    showSubMenu();
            }
        }while (!subOptionMenu.equals("X"));
    }

    public static void game(String[][] board){
        int timesToBeRepeated = 0;
        Scanner inPut = new Scanner(System.in);
        String option = null;

        do{
            System.out.println();
            System.out.println("* * * * * * * * * * * * * * *");
            System.out.println("            BINGO            ");
            System.out.println("* * * * * * * * * * * * * * *");
            System.out.println();

            int numberCurrent = numberRandom();

            if(numberCurrent == 0){
                System.out.println("There are no longer any numbers to display");
                System.out.println("- - - GAME OVER - - -");
                showMainMenu();
            }else{
                System.out.println(" ---- " + numberCurrent +" ---- ");
                System.out.println();
            }

            System.out.println("Press C to continue\n");
            showBoard(board);

            System.out.println();

            searchNumber(board,numberCurrent);

            if(searchNumber(board,numberCurrent)){
                System.out.println("Match number pick a position to put on your token");
                String repeatedNumber= String.valueOf(numberCurrent);
                searchAndFind(0,0,board,repeatedNumber);
            }else{
                System.out.println("Not matching number on your current card");
            }

            if(BINGO(board)){
                System.out.println("* * * * * * * * * * * * * * *");
                System.out.println("            BINGO            ");
                System.out.println("* * * * * * * * * * * * * * *");
                showBoard(board);
            }else {
                System.out.println("Continue playing press C");
                System.out.println("Press X to exit the game!\n");
                System.out.print(">");
                option = inPut.nextLine();
            }
            timesToBeRepeated += 1;

        }while (timesToBeRepeated<= 100 && !option.equals("X"));
        showMainMenu();
    }


    public static boolean BINGO(String [][] board){
        if(searchRowFull(board) || searchColumnFull(board)){
            return true;
        }else{
            return false;
        }
    }


    public static boolean searchRowFull(String[][] board) {
        for (int x = 0; x < board.length; x++) {
            int full = 0;
            for (int y = 0; y < board[x].length; y++) {
                if(board[x][y].equals("**")){
                    full += 1;

                    if(full == 5){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean searchColumnFull(String[][] board) {
        int row = 0;
        int full = 0;
        while (row < 5){
            for (int y = 0; y < 5; y++) {
                if(board[y][row].equals("**")){
                    full += 1;
                    if(full == 5){
                        System.out.println("Entre");
                        return true;
                    }
                }
            }
            row += 1;
        }
        System.out.println("No Entre");
        return false;
    }

    public static void searchAndFind(int row, int colum, String[][] board, String numerSearch){
        for (int x=row; x < board.length; x++) {
            for (int y=colum; y < board[x].length; y++) {
                if(board[x][y].equals(numerSearch)){
                    System.out.print("\n" + (timesYouFoundTheNumber+1));
                    System.out.print(". Cell [" + (x+1) + "]"+ "[" + (y+1) + "]" + ": " + board[x][y] + "\n");
                    if(rowPositionOfTheFoundValue[timesYouFoundTheNumber] ==  0 && columnaPositionOfTheFoundValue[timesYouFoundTheNumber] == 0) {
                        rowPositionOfTheFoundValue[timesYouFoundTheNumber] = y;
                        columnaPositionOfTheFoundValue[timesYouFoundTheNumber] = x;
                    }
                    timesYouFoundTheNumber += 1;
                    searchAndFind(x+1, y+1, board, numerSearch);
                }
            }
        }

        if (timesYouFoundTheNumber>= 1){
            System.out.println("Choose only one option");
            System.out.print(">");
            int optionCell = chooseWhichCellToUse();
            if(optionCell != 0){
                addXX(board,rowPositionOfTheFoundValue[optionCell-1],columnaPositionOfTheFoundValue[optionCell-1]);
            }
        }
    }

    public static void addXX(String [][] board, int row, int columna){
        for(int i=0; i<= board.length; i++){
            for(int j= 0; j<board[i].length; j++){
                if(board[i][j] == board[columna][row]){
                    board[i][j] = "**";
                    return;
                }
            }
        }
    }

    public static int chooseWhichCellToUse(){
        Scanner inPut = new Scanner(System.in);

        boolean correctValue = false;
        String optionTheCell;
        int tour = timesYouFoundTheNumber + 1;

        while(correctValue == false){
            optionTheCell = inPut.nextLine();

            int chooseValue = Integer.parseInt(optionTheCell);

            if(chooseValue > 0 && chooseValue<=tour){
                correctValue = true;
                return chooseValue ;

            }else{
                System.out.println("Option Invalid");
                correctValue = false;
            }
        }
        return 0;
    }

    public static boolean searchNumber(String [][] board, int numberToSearch){
        String searchNumber= String.valueOf(numberToSearch);
        for (int x=0; x < board.length; x++) {
            for (int y=0; y < board[x].length; y++) {
                if(board[x][y].equals(searchNumber)){
                    return true;
                }
            }
        }
        return false;
    }

    public static int numberRandom(){
        int maximumNumberOfAttempts = 1;
        while (maximumNumberOfAttempts<= 100){
            int numberRandom = (int) (Math.random()*100+1);
            for (int i = 0; i < bundleOfNumbers.length; i++) {
                if (bundleOfNumbers[i] == numberRandom) {
                    maximumNumberOfAttempts += 1;
                } else if (bundleOfNumbers[i] == 0) {
                    bundleOfNumbers[i] = numberRandom;
                    return numberRandom;
                }
            }
        }
        return 0;
    }

    public static void showBoard(String [][] board) {
        for (int x=0; x < board.length; x++) {
            System.out.print("|");
            for (int y=0; y < board[x].length; y++) {
                System.out.print (board[x][y]);
                if (y!=board[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }
    }

    public static void fillBoard(String [][] board){
        for (int row=0; row < board.length; row++) {
            for (int spine=0; spine < board[row].length; spine++) {
                if(row == 2 && spine==2){
                    board[row][spine] = "XX";
                }else{
                    int numberRandom = (int) (Math.random()*100+1);
                    board[row][spine] = String.valueOf(numberRandom);
                }
            }
        }
    }

} 