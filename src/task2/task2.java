package task2;
import java.util.ArrayList;
import java.lang.Math;
public class task2 {
        public static void main(String[] args) {
            int size = (int)(Math.random()*20 + 10);
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < size; i++){
                arr.add((int)(Math.random()*100));
            }
            System.out.println(arr);
            sort(arr, 0, size-1);
            System.out.println(arr);
        }

        static void sort(ArrayList<Integer> arr, int first,int last){
            int middle;
            if(first<last){
                middle = (first + last)/2;
                sort(arr, first, middle);
                sort(arr, middle + 1, last);
                merge(arr, first, middle, middle + 1, last);
            }
        }

        static void merge(ArrayList<Integer> arr, int n1, int c1, int n2, int c2 ){
            ArrayList<Integer> MArr = new ArrayList<>();
            int x = n1, y = n2;
            while(x <= c1 && y <= c2){
                if(arr.get(x) < arr.get(y)){
                    MArr.add(arr.get(x));
                    x++;
                }else{
                    MArr.add(arr.get(y));
                    y++;
                }
            }
            if(x==c1+1){
                while(y!=c2+1){
                    MArr.add(arr.get(y));
                    y++;
                }
            }else{
                while(x!=c1+1){
                    MArr.add(arr.get(x));
                    x++;
                }
            }
            for(int i = n1; i<=c2; i++){
                arr.set(i, MArr.get(i-n1));
            }
        }

}
