package battleship;

import java.util.*;

class Ship {
    int size;
    String title;
    int[] x;
    int[] y;
    int[] coordinates;
    boolean horizontalLocation = true;

    Ship(int size, String title) {
        this.size = size;
        this.title = title;
        coordinates = new int[size];
    }

    Ship(int size, String title, int[] x, int[] y) {
        this.size = size;
        this.title = title;
        this.x = x;
        this.y = y;
        coordinates = new int[size];
    }

    public void printMenu() {
        System.out.println("Enter the coordinates of the " + title + " (" + size + " cells):");
    }

    public void setCoordinates(int[] x, int[] y) {
        if (x[1] == y[1]) {
            horizontalLocation = false;
        }
        if (horizontalLocation) {
            for (int i = 0; i < size; i++) {
                coordinates[i] = (Math.min(x[1], y[1]) + i) + x[0] * 10;
            }
        } else {
            for (int i = 0; i < size; i++) {
                coordinates[i] = x[1] + (Math.min(x[0], y[0]) + i) * 10;
            }
        }
    }
}

public class Main {
    public static void showField(String[][] array) {
        int numberCol = 0;
        String alphabet = "ABCDEFGHIJ";
        int numberline = 0;

        for (int i = 0; i < 11; i++) {

            for (int j = 0; j < 11; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(numberCol + 1 + " ");
                    numberCol++;
                } else if (j == 0) {
                    System.out.print(alphabet.charAt(numberline) + " ");
                    numberline++;
                } else {
                    System.out.print(array[i - 1][j - 1] + " ");
                }


            }
            System.out.println();

        }
    }

    public static void changeField(String[][] array, Ship ship, int[] x, int[] y) {
        boolean change = false;
        boolean busy;
        Scanner scanner = new Scanner(System.in);
        while (!change) {
            busy = false;
            if (x[0] == y[0]) {
                int start = x[1];
                if (Math.abs(x[1] - y[1]) == ship.size - 1) {
                    if (x[1] > y[1]) {
                        start = y[1];
                    }
                    for (int j = start - 1; j <= start + ship.size; j++) {
                        if (j < 0) {
                            j = 0;
                        }
                        if (j >= 10) {
                            break;
                        }
                        if (array[x[0]][j].equals("O")) {
                            busy = true;
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            x = getCoordinate(scanner.next());
                            y = getCoordinate(scanner.next());
                            break;
                        }
                        if (x[0] + 1 >= array.length) {
                            if (array[x[0] - 1][j].equals("O")) {
                                busy = true;
                                System.out.println("Error! You placed it too close to another one. Try again:");
                                x = getCoordinate(scanner.next());
                                y = getCoordinate(scanner.next());
                                break;
                            }
                        }
                        if (x[0] - 1 < 0) {
                            if (array[x[0] + 1][j].equals("O")) {
                                busy = true;
                                System.out.println("Error! You placed it too close to another one. Try again:");
                                x = getCoordinate(scanner.next());
                                y = getCoordinate(scanner.next());
                                break;
                            }
                        }
                        if ((x[0] - 1 >= 0) && x[0] + 1 < array.length) {
                            if (array[x[0] + 1][j].equals("O") || array[x[0] - 1][j].equals("O")) {
                                busy = true;
                                System.out.println("Error! You placed it too close to another one. Try again:");
                                x = getCoordinate(scanner.next());
                                y = getCoordinate(scanner.next());
                                break;
                            }
                        }

                    }

                    if (!busy) {
                        for (int j = start; j < start + ship.size; j++) {
                            array[x[0]][j] = "O";
                            change = true;

                        }
                    }


                } else {
                    System.out.println("Error! Wrong length of the " + ship.title + "! Try again:");
                    x = getCoordinate(scanner.next());
                    y = getCoordinate(scanner.next());
                }
            }
            if (x[1] == y[1]) {
                if (Math.abs(x[0] - y[0]) == ship.size - 1) {
                    if (x[0] > y[0]) {
                        x[0] = y[0];
                    }
                    for (int j = x[0] - 1; j <= x[0] + ship.size; j++) {
                        if (j < 0) {
                            j = 0;
                        }
                        if (j >= 10) {
                            break;
                        }
                        if (array[j][x[1]].equals("O")) {
                            busy = true;
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            x = getCoordinate(scanner.next());
                            y = getCoordinate(scanner.next());
                            break;
                        }
                        if (x[1] + 1 >= array.length) {
                            if (array[j][x[1] - 1].equals("O")) {
                                busy = true;
                                System.out.println("Error! You placed it too close to another one. Try again:");
                                x = getCoordinate(scanner.next());
                                y = getCoordinate(scanner.next());
                                break;
                            }
                        }
                        if (x[1] - 1 < 0) {
                            if (array[j][x[1] + 1].equals("O")) {
                                busy = true;
                                System.out.println("Error! You placed it too close to another one. Try again:");
                                x = getCoordinate(scanner.next());
                                y = getCoordinate(scanner.next());
                                break;
                            }
                        }
                        if ((x[1] - 1 >= 0) && x[1] + 1 < array.length) {
                            if (array[j][x[1] + 1].equals("O") || array[j][x[1] - 1].equals("O")) {
                                busy = true;
                                System.out.println("Error! You placed it too close to another one. Try again:");
                                x = getCoordinate(scanner.next());
                                y = getCoordinate(scanner.next());
                                break;
                            }
                        }
                    }
                    if (!busy) {
                        for (int j = x[0]; j < x[0] + ship.size; j++) {
                            array[j][x[1]] = "O";
                            change = true;
                        }
                    }
                } else {
                    System.out.println("Error! Wrong length of the " + ship.title + "! Try again:");
                    x = getCoordinate(scanner.next());
                    y = getCoordinate(scanner.next());
                }
            }
            if (x[1] != y[1] && x[0] != y[0]) {
                System.out.println("Error! Wrong ship location! Try again:");
                x = getCoordinate(scanner.next());
                y = getCoordinate(scanner.next());
            }
        }
        ship.setCoordinates(x, y);


    }

    public static void changeFieldByShot(String[][] array, String[][] fogArray, int[] shootCoordinates, ArrayList<Ship> ships) {
        Scanner scanner = new Scanner(System.in);
        if (array[shootCoordinates[0]][shootCoordinates[1]].equals("O") || array[shootCoordinates[0]][shootCoordinates[1]].equals("X")) {
            array[shootCoordinates[0]][shootCoordinates[1]] = "X";
            fogArray[shootCoordinates[0]][shootCoordinates[1]] = "X";
            int counter = 0;


            for (Ship ship : ships) {
                for (int i = 0; i < ship.coordinates.length; i++) {
                    if (ship.coordinates[i] == shootCoordinates[0] * 10 + shootCoordinates[1]) {
                        // System.out.println("You hit a ship!999" + element.title);
                        for (int y = 0; y < ship.coordinates.length; y++) {
                            if (array[ship.coordinates[y] / 10][ship.coordinates[y] % 10].equals("O")) {
                                System.out.println("You hit a ship!");
                                System.out.println("Press Enter and pass the move to another player");
                                scanner.nextLine();
                                break;
                            }
                            if (array[ship.coordinates[y] / 10][ship.coordinates[y] % 10].equals("X")) {
                                counter++;
                            }
                            if (counter == ship.coordinates.length) {
                                if (Arrays.deepEquals(array, fogArray)) {
                                    System.out.println("You sank the last ship. You won. Congratulations!");
                                    System.exit(0);
                                    break;
                                } else {
                                    System.out.println("You sank a ship! Specify a new target:");
                                    System.out.println("Press Enter and pass the move to another player");
                                    scanner.nextLine();
                                    counter = 0;
                                }


                            }
                        }
                    }

                }
            }


        } else {
            array[shootCoordinates[0]][shootCoordinates[1]] = "M";
            fogArray[shootCoordinates[0]][shootCoordinates[1]] = "M";
            System.out.println("You missed!");
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
        }
    }


    public static int[] getCoordinate(String string) {
        int array[] = new int[2];
        String alphabet = "ABCDEFGHIJ";
        Scanner scanner = new Scanner(System.in);
        int x1 = 0, x2 = 0;
        boolean x1Valid = false;
        while (!x1Valid || (x2 < 0 || x2 > 9)) {
            for (int i = 0; i < alphabet.length(); i++) {
                if (alphabet.charAt(i) == string.toUpperCase(Locale.ROOT).substring(0, 1).charAt(0)) {
                    x1 = i;
                    x1Valid = true;
                    break;
                }
            }
            try {
                if (string.length() == 2) {
                    x2 = Integer.parseInt(string.toUpperCase(Locale.ROOT).substring(1, 2)) - 1;
                } else {
                    x2 = Integer.parseInt(string.toUpperCase(Locale.ROOT).substring(1, 3)) - 1;
                }

            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                string = scanner.next();
            }
            if (!x1Valid || x2 < 0 || x2 > 9) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                string = scanner.next();
            }
        }
        array[0] = x1;
        array[1] = x2;
        return array;
    }

    public static ArrayList<Ship> setShip(String[][] array) {
        Scanner scanner = new Scanner(System.in);
        Ship aircraftCarrier = new Ship(5, "Aircraft Carrier");
        aircraftCarrier.printMenu();

        int x[] = getCoordinate(scanner.next());
        int y[] = getCoordinate(scanner.next());
        aircraftCarrier = new Ship(5, "Aircraft Carrier", x, y);
        changeField(array, aircraftCarrier, x, y);
        showField(array);

        Ship Battleship = new Ship(4, "Battleship");
        Battleship.printMenu();
        x = getCoordinate(scanner.next());
        y = getCoordinate(scanner.next());
        Battleship = new Ship(4, "Battleship", x, y);
        changeField(array, Battleship, x, y);
        showField(array);

        Ship Submarine = new Ship(3, "Submarine");
        Submarine.printMenu();
        x = getCoordinate(scanner.next());
        y = getCoordinate(scanner.next());
        Submarine = new Ship(3, "Submarine", x, y);
        changeField(array, Submarine, x, y);
        showField(array);

        Ship Cruiser = new Ship(3, "Cruiser");
        Cruiser.printMenu();
        x = getCoordinate(scanner.next());
        y = getCoordinate(scanner.next());
        Cruiser = new Ship(3, "Cruiser", x, y);

        changeField(array, Cruiser, x, y);
        showField(array);

        Ship Destroyer = new Ship(2, "Destroyer");
        Destroyer.printMenu();
        x = getCoordinate(scanner.next());
        y = getCoordinate(scanner.next());
        Destroyer = new Ship(2, "Destroyer", x, y);
        changeField(array, Destroyer, x, y);
        showField(array);

        ArrayList<Ship> ships = new ArrayList<>(Arrays.asList(aircraftCarrier, Battleship, Submarine, Cruiser, Destroyer));
        return ships;
    }

    public static void playGame(String[][] arrayPlayer1, String[][] arrayPlayer2, String[][] fogArray1, String[][] fogArray2, ArrayList<Ship> ships1, ArrayList<Ship> ships2) {
        int n = 1;
        Scanner scanner = new Scanner(System.in);
        while (true) {

            if (n % 2 != 0) {

                showField(fogArray2);
                System.out.println("---------------------");
                showField(arrayPlayer1);
                System.out.println("Player 1, it's your turn:");
                int[] shotCoordinate = getCoordinate(scanner.next());
                changeFieldByShot(arrayPlayer2, fogArray2, shotCoordinate, ships2);
            } else {

                showField(fogArray1);
                System.out.println("---------------------");
                showField(arrayPlayer2);
                System.out.println("Player 2, it's your turn:");
                int[] shotCoordinate2 = getCoordinate(scanner.next());
                changeFieldByShot(arrayPlayer1, fogArray1, shotCoordinate2, ships1);
            }
            n++;
        }
    }


    public static void main(String[] args) {
        String[][] arrayPlayer1 = new String[10][10];
        String[][] arrayPlayer2 = new String[10][10];
        String[][] fogArray1 = new String[10][10];
        String[][] fogArray2 = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                arrayPlayer1[i][j] = "~";
                arrayPlayer2[i][j] = "~";
                fogArray1[i][j] = "~";
                fogArray2[i][j] = "~";
            }
        }

        System.out.println("Player 1, place your ships on the game field");
        showField(arrayPlayer1);
        ArrayList<Ship> ships1 = setShip(arrayPlayer1);
        System.out.println("Press Enter and pass the move to another player");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Player 2, place your ships on the game field");
        showField(arrayPlayer2);
        ArrayList<Ship> ships2 = setShip(arrayPlayer2);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        playGame(arrayPlayer1, arrayPlayer2, fogArray1, fogArray2, ships1, ships2);

    }
}
