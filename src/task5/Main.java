import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> edgesOfTrees = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<Integer>>> trees = new ArrayList<>();
    private static int e;
    private static int v;
    enum GraphChar{
        HasLoop,
        NotConnected,
        IsTree,
    }
    public static void main(String args[]){

        inputGraph();
        findAllFrames();
        if(!edgesOfTrees.isEmpty()) {
            System.out.println(edgesOfTrees);
            System.out.println(edgesOfTrees.size());
        }else System.out.println("Graph is not connected.");
        //printTrees();
    }

    private static void findAllFrames() {
        ArrayList<Integer> allEdges = new ArrayList<>();
        for (int i=0; i<e; i++){
            allEdges.add(i);
        }
        if(isTree(allEdges)==GraphChar.IsTree){
            edgesOfTrees.add(allEdges);
            return;
        }else if(isTree(allEdges)==GraphChar.NotConnected){
            return;
        }

        getCombs();
        for(ArrayList<Integer> tr : comb){
            if(isTree(tr)==GraphChar.IsTree){
                edgesOfTrees.add(tr);
            }
        }
    }

    private static GraphChar isTree(ArrayList<Integer> edgesList) {
        ArrayList<ArrayList<Integer>> adjacencyMatrix = toAdjacencyMatrix(edgesList);
        Stack<Integer> deepSearchStack = new Stack<>();
        int Vertexes[] = new int[v];
        int vert = 0;

        int i;
        deepSearchStack.push(vert);
        Vertexes[vert] = 1;
        while (!deepSearchStack.empty()){
            vert = deepSearchStack.pop();
            Vertexes[vert] = -1;
            for (i=0; i<v; i++){
                if(adjacencyMatrix.get(vert).get(i)==1){
                    if(Vertexes[i]==0) {
                        deepSearchStack.push(i);
                        Vertexes[i] = 1;
                    }else if(Vertexes[i]==1) return GraphChar.HasLoop;
                }
            }
        }
        for(i=0; i<v && Vertexes[i]!=0; i++);
        return i==v ? GraphChar.IsTree : GraphChar.NotConnected;
    }

    private static ArrayList<ArrayList<Integer>> toAdjacencyMatrix(ArrayList<Integer> eList) {
        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
        for(int i=0; i<v; i++){
            adjacencyMatrix.add(new ArrayList<>());
            for(int j=0; j<v; j++){
                adjacencyMatrix.get(i).add(0);
            }
        }
        for(Integer i : eList){
            int vertex = -1;
            for(int j = 0; j<v; j++){
                if(graph.get(j).get(i)==1 && vertex!=-1){
                    adjacencyMatrix.get(vertex).set(j, 1);
                    adjacencyMatrix.get(j).set(vertex, 1);
                    break;
                }
                if(graph.get(j).get(i)==1 && vertex==-1){
                    vertex = j;
                }
            }
        }
        return adjacencyMatrix;
    }

    private static void inputGraph() {
        try {
            File file = new File("C:\\Users\\Maxim\\IdeaProjects\\Frames\\src\\matrix.txt");
            Scanner sc = new Scanner(file);
            v = sc.nextInt();
            e = sc.nextInt();
            graph = new ArrayList<>();
            for(int i = 0; i < v; i++){
                graph.add(new ArrayList<>());
                for(int j = 0; j < e; j++){
                    graph.get(i).add(sc.nextInt());
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void getCombs(){
        int n = e;
        int k = v - 1;
        long count = fact(n)/fact(k)/fact(n-k);
        comb.add(new ArrayList<>());

        for(int i = 0; i < k; i++){
            comb.get(0).add(i);
        }

        for(int i = 1; i < count; i++){
            for(int j = 0; j < k; j++){
                int el = comb.get(i-1).get(k-j-1);
                if(el < n - j - 1){
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
    }

    private static long fact(int n){
        if (n <= 1){
            return 1;
        }else {
            return n * fact(n-1);
        }
    }

    private static void printTrees() {
        for (ArrayList<ArrayList<Integer>> it : trees) {
            for (ArrayList<Integer> i : it){
                for (Integer j : i){
                    System.out.printf("%5d", j);
                }
                System.out.println();
            }
        }
    }
}
