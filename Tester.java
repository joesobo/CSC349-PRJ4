public class Tester {
    public static void main(String[] args) {
        int[] set1 = { 100, 50, 25, 10, 5, 1 };
        int[] set2 = { 100, 50, 20, 15, 10, 5, 3, 2, 1 };
        int[] set3 = { 64, 32, 16, 8, 4, 2, 1 };
        int[] set4 = { 100, 50, 25, 10, 1 };
        int[] set5 = { 66, 35, 27, 18, 10, 1 };

        test(1, set1);
        test(2, set2);
        test(3, set3);
        test(4, set4);
        test(5, set5);
    }

    private static void test(int setNum, int[] d){
        int matches = 0;
        for(int n = 1; n <= 200; n++){
            int[] DPresults = ChangeMaker.change_DP(n, d);
            int DPcount = ChangeMaker.coinCount(DPresults);

            int[] Greedyresults = ChangeMaker.change_greedy(n, d);
            int Greedycount = ChangeMaker.coinCount(Greedyresults);

            if(DPcount == Greedycount){
                matches++;
            }
        }

        System.out.printf("Testing set %d: x%d matches in 200 tests\n", setNum, matches);
    }
}