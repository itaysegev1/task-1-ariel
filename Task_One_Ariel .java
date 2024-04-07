import java.util.Arrays;

public class Task_One_Ariel {
    static double sum = 0;

    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 315112482;             // Your ID should be written here
        int numOfDigits = 2;                 // Number of digits [2,6]
        // Starting a game
        // System.out.println(Title + " with code of " + numOfDigits + " digits");
        // manualEx1Game(game);
        int times = 0;
        for (int i = 0; i < 1; i++) { //loop of 100 games
            game.startGame(myID, numOfDigits); // Starting a game
            autoEx1Game1(game);// you should implement this function )and any additional required functions).
            times++;//counting how much times in row i played the game

        }
        System.out.println("the avarage guess tries of " + times + " games with num of digits: " + numOfDigits + " is: " + sum / times); //printing the avarage of 100 trys


    }

    //function that run the automatic game
    public static void autoEx1Game1(BP_Server game) {
        /**function that run the automatic game*/
        boolean[] boolarr = boolArray(game.getNumOfDigits()); //creating array of booleans
        int[] guess = random(game.getNumOfDigits()); //first guess is random
        while (game.isRunning()) { //run while the game is running
            sum += 1;
            int[] res = game.play(guess); //activate the guess and gets the bulls and cows
            //  System.out.println(Arrays.toString(guess)+" bull "+res[0]+" cow "+res[1]); //debugging step
            boolarr = changebool(boolarr, res[0], res[1], guess); //change the booleans array in according to my guess
            guess = randomnumarr(boolarr, game.getNumOfDigits()); //gets new guess
           /*System.out.println(); //debugging step
            for (int i = 0; i < guess.length; i++) {
                System.out.print(guess[i]);
            }*/

        }
        System.out.println(game.getStatus()); //printing game status
    }

    public static int[] random(int numofdigits) {
        /**function that return a random number in array according to the size of num of digits*/
        int[] a = new int[numofdigits];
        for (int i = 0; i < numofdigits; i++) {
            double r = Math.random() * 10;
            int rand = (int) r;
            a[i] = rand;
        }
        return a;
    }


    public static boolean[] boolArray(int numofdigits) {
        /**creating a boolean array in size of pow of 10 with num of digits (all the options)*/
        boolean[] a = new boolean[(int) Math.pow(10, numofdigits)];
        for (int i = 0; i < a.length; i++) {
            a[i] = true;
        }
        return a;
    }

    public static int[] toArray2(int a, int size) {
        /**function that get a number and his size and return that number in array*/
        int[] c = new int[size];
        for (int j = c.length - 1; j >= 0; j -= 1) {
            c[j] = a % 10;
            a = a / 10;
        }
        return c;
    }

    public static int[] bullpgia(int[] guess, int[] index) {
        /**function that gets two int arrays and return how much bull and cow was there*/
        boolean[] bull = new boolean[guess.length];//new boolean array that mark (for safety)
        int bul = 0;
        int pgia = 0;
        int[] a = Arrays.copyOf(guess, guess.length); //copping guess into new array to not change the original
        int[] b = Arrays.copyOf(index, index.length);//copping index into new array to not change the original

        for (int i = 0; i < guess.length; i++) { //checking how much bulls i have between the two arrays and mark them
            if (a[i] == b[i]) {
                bull[i] = true;
                bul = bul + 1;
                a[i] = -1;// Mark the matched position in the guess array
                b[i] = -2; // Mark the matched position in the index array
            }
        }

        for (int i = 0; i < guess.length; i++) { //checking how much cows i have between the two arrays and mark them
            for (int j = 0; j < guess.length; j++) {
                if ((i != j) && (a[i] == b[j]) && (!bull[i])) {
                    a[i] = -1;// Mark the matched position in the guess array
                    b[j] = -2; // Mark the matched position in the index array
                    pgia = pgia + 1;
                }
            }
        }
       System.out.println("Guess: " + Arrays.toString(guess) + ", Index: " + Arrays.toString(index) + ", Bulls: " + bul + ", Cows: " + pgia);
        // the line above is debugging line
        int[] bulpgia = new int[]{bul, pgia}; //creating new array the first spot is bull the second is cow
        return bulpgia;
    }


    public static boolean[] changebool(boolean[] boolarr, int bull, int cow, int[] guess) {
        /** function that changing the original boolean array and update in according to the bull and cow function*/
        for (int i = 0; i < boolarr.length; i++) {
            if (boolarr[i]) {
                int[] ind = toArray2(i, guess.length);
                int[] bac = bullpgia(guess, ind);
                if (bull != bac[0] || cow != bac[1]) { //if the bull and cow from he original game is not equal to the
                    //bools and cows in the local function of the game between index and guess, the index cant be the number
                    boolarr[i] = false;
                }
            }

        }

        return boolarr;
    }

    public static int[] randomnumarr(boolean[] boolarr, int size) {
        /** function that gets me new random guess in according to the possible indexes of the boolean array*/
        int count = 0;
        int index = 0;
        for (int i = 0; i < boolarr.length; i++) { //counting how much numbers there are in the array that they are true
            if (boolarr[i]) {
                count++;
                index = i; //saving the index of one of them
            }
        }
        if (count == 1) { //if there are only one index that he is true in fact that this is the number and it will return it
            return toArray2(index, size);
        }
        int[] trueindexes = new int[count];//creating new array of all the new possible indexes
        int r = (int) (Math.random() * count); // getting new random num between [0,count)
        index = 0;
        for (int i = 0; i < boolarr.length; i++) { // filling the array with all the possible numbers
            if (boolarr[i]) {
                trueindexes[index] = i;
                index++;
            }
        }
        return toArray2(trueindexes[r], size); //returning one random number from the possible numbers
    }
}
