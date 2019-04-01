
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class Rextester
{  
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static int V;
    private static ArrayList<Integer> U = new ArrayList<>();
    private static int a;
    private static int[] d;
    private static ArrayList<ArrayList<Integer>> p = new ArrayList<>();
    public static void main(String args[]){
        inputGraph();        
        d = new int[V];
        d[a] = 0;
        for(int i=0; i<V; i++){
            p.add(new ArrayList<>());
            if(i!=a)
                d[i] = Integer.MAX_VALUE;
        }
        p.get(a).add(0);
        
        while(U.size()!=V){
            int v = getMin(d);
            U.add(v);
            for(int i=0; i<V; i++){
                if(!U.contains(i) && graph.get(i).get(v)!=0){
                    if(d[i]>d[v]+graph.get(i).get(v)){
                        d[i] = d[v]+graph.get(i).get(v);
                        p.set(i, new ArrayList<>());
                        for(Integer x : p.get(v))
                            p.get(i).add(x);
                        p.get(i).add(i);
                    }
                }
            }
        }
        for(Integer i : d)
            System.out.println(i);
        System.out.println(p);
    }
    
    private static int getMin(int d[]){
        int min = 0;
        for(int i=0; i<d.length; i++)
            if(!U.contains(i))
                min = i;
        for(int i=0; i<d.length; i++){
            
            if(d[i] < d[min] && !U.contains(i))
                min = i;
        }
        return min;
    }
    
    private static void inputGraph() {
        try {
            Scanner sc = new Scanner(System.in);
            V = sc.nextInt();
            a = sc.nextInt();
            graph = new ArrayList<>();
            for(int i = 0; i < V; i++){
                graph.add(new ArrayList<>());
                for(int j = 0; j < V; j++){
                    graph.get(i).add(sc.nextInt());
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}