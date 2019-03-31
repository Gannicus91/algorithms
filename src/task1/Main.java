package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("n = ");
        int n = Integer.parseInt(reader.readLine()), k = n + 1;

        while (k > n){
            System.out.print("k = ");
            k = Integer.parseInt(reader.readLine());
        }

        long count = fact(n)/fact(k)/fact(n-k);
        ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
        comb.add(new ArrayList<>());

        for(int i = 1; i <= k; i++){
            comb.get(0).add(i);
        }

        for(int i = 1; i < count; i++){
            for(int j = 0; j < k; j++){
                int el = comb.get(i-1).get(k-j-1);
                if(el < n - j){
                    comb.add(new ArrayList<>());
                    for (int p = 0; p < k; p++){
                        if (p < k - j - 1){
                            comb.get(i).add(comb.get(i - 1).get(p));
                        }else{
                            if(p == k - j - 1)
                                comb.get(i).add(comb.get(i-1).get(p) + 1);
                            else
                                comb.get(i).add(comb.get(i).get(p-1) + 1);
                        }
                    }
                    break;
                }
            }
        }

        for (ArrayList<Integer> a : comb){
            System.out.println(a);
        }
    }

    static long fact(int n){
        if (n <= 1){
            return 1;
        }else {
            return n * fact(n-1);
        }
    }
}
