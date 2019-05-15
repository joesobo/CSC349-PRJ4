//Change Maker
//By Joseph Soboleski (jsoboles@calpoly.edu) and Salman Wajahat (swajahat@calpoly.edu)
//5/15/19

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ChangeMaker {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of coin-denominations and the set of coin values: ");
        //number of coin denominations
        int k = scan.nextInt();

        ArrayList<Integer> denominations = new ArrayList<Integer>();
        for(int i = 0; i < k; i++){
            denominations.add(scan.nextInt());
        }
        int[] d = convertInt(denominations);

        System.out.print("Enter a positive amount to be changed (enter 0 to quit): ");
        //change amount
        int n = scan.nextInt();

        if(n <= 0){
            System.out.println("Thanks for playing. Good Bye");
            System.exit(0);
        }else{
            int[] DP_result = change_DP(n, d);
            System.out.println("\nDP algorithm results");
            printResult(n, d, DP_result);

            int[] greedy_result = change_greedy(n, d);
            System.out.println("\nGreedy algorithm results");
            printResult(n, d, greedy_result);
        }
    }

    static int[] c;
    public static int[] change_DP(int n, int[] d){
        //stores min # of coins
        int[] C = new int[n + 1];
        Arrays.fill(C, Integer.MAX_VALUE);
        C[0] = 0;

        //stores index of denomination
        int[] A = new int[n + 1];
        Arrays.fill(A, -1);

        //stores count of coins
        int[] B = new int[d.length + 1];
        Arrays.fill(B, 0);

        //while more change to be given
        int j = 1;
        while(j <= n){
            int min = Integer.MAX_VALUE;
            int denomUsed = -1;

            for(int i = 0; i < d.length; i++){
                if(j >= d[i]){
                    int current = C[j-d[i]];
                    if(current < min){
                        min = current;
                        denomUsed = i;
                    }
                }else{
                    continue;
                }
            }

            A[j] = denomUsed;
            C[j] = min + 1;
            j++;
        }

        while(n > 0){
            int dIndex = A[n];
            n -= d[dIndex];
            B[dIndex]++;
        }

        return B;
    }

    public static int[] change_greedy(int n, int[] d){
        int[] change = new int[d.length];
        int remaining = n;
        int i = 0;
        while(remaining > 0){
            while(d[i] <= remaining){
                change[i]++;
                remaining -= d[i];
            }
            i++;
        }
        return change;
    }

    public static void printResult(int n, int[] d, int[] result){
        //Amount: 87
        System.out.println("Amount: " + n);

        //Optimal distribution: 3*25c + 1*10c + 2*1c
        System.out.print("Optimal distribution: ");
        for(int i = 0; i < d.length; i++){
            if(result[i] > 0){
                System.out.printf("%d*%dc ", result[i], d[i]);

                if(i != d.length-1){
                    System.out.printf("+ ");
                }
            }
        }
        System.out.print("\n");

        //Optimal coin count: 6
        int total = coinCount(result);
        System.out.println("Optimal coin count: " + total);
    }

    public static int coinCount(int[] results){
        int total = 0;
        for(int i = 0; i < results.length; i++){
            total += results[i];
        }
        return total;
    }

    private static int[] convertInt(ArrayList<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = list.get(i).intValue();
        }
        return ret;
    } 
}