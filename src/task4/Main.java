import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static int rows, cols;
    static double matrix[][];
    public static void main(String[] args){
        inputMatrix();
        gauss();
        normMatrix();
        printMatrix();
        getAnswer();
    }

    private static void normMatrix() {
       //ArrayList<Integer> zeroRows = new ArrayList<>();
       //ArrayList<Integer> zeroCols = new ArrayList<>();
        double sum;
        for (int i=0; i<rows; i++){//rows
            sum=0;
            for (int j=0; j<cols; j++){
                sum+=matrix[i][j];
            }
            if (sum==0){
                deleteRow(i);
                i--;
            }
        }
        for (int i=0; i<cols; i++){//cols
            sum=0;
            for (int j=0; j<rows; j++){
                sum+=matrix[j][i];
            }
            if (sum==0){
                deleteCol(i);
            }
        }
        /**
        while (cols-1!=rows){
            if(cols-1>rows) {
                deleteCol(zeroCols.get(0));
                zeroCols.remove(0);
            }else {
                deleteRow(zeroRows.get(0));
                zeroCols.remove(0);
            }
        }**/
    }

    private static void deleteRow(int row) {
        double Nmatrix[][] = new double[rows-1][cols];
        int k = 0;
        for (int i=0; i <rows; i++){
            if (i==row){
                continue;
            }
            for (int j=0; j<cols; j++){
                Nmatrix[k][j]=matrix[i][j];
            }
            k++;
        }
        rows--;
        matrix = Nmatrix;
    }

    private static void deleteCol(int col) {

        double Nmatrix[][] = new double[rows][cols - 1];
        for (int i=0; i <rows; i++){
            int k = 0;
            for (int j=0; j<cols; j++){
                if(j==col)
                    continue;
                Nmatrix[i][k]=matrix[i][j];
                k++;
            }
        }
        cols--;
        matrix = Nmatrix;
    }
    private static void getAnswer() {
        for (int i=0; i<rows; i++){//rows
            if(matrix[i][i]==1 && i<cols-1);
            else {
                double sum = 0;
                for (int j=0; j<cols-1; j++){
                    sum+=matrix[i][j];
                }
                if (sum==0){
                    System.out.println("No solutions");
                    return;
                }
            }
        }
        if(cols-1==rows){
            for(int i=0; i<rows; i++){
                System.out.printf("x%d = %.2f\n", i+1, matrix[i][cols-1]);
            }
        }else {
            for(int i=0; i<rows; i++){
                System.out.printf("x%d = %.2f", i+1, matrix[i][cols-1]);
                for (int j = rows; j < cols-1; j++)
                    System.out.printf("%+.2fx%d", -matrix[i][j], j+1);
                System.out.println();
            }
            for (int j=rows; j<cols-1; j++){
                System.out.printf("x%d = x%d", j+1, j+1);
            }
        }
    }

    public static void inputMatrix(){
        try {
            File file = new File("C:\\Users\\Maxim\\IdeaProjects\\lab4alg\\src\\matrix.txt");
            Scanner sc = new Scanner(file);
            rows = sc.nextInt();
            cols = sc.nextInt();
            matrix = new double[rows][cols];
            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    matrix[i][j] = sc.nextDouble();
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    public static void gauss(){
        for (int i = 0; i<cols - 1; i++){
            if(i<rows)
                zeroCol(i);
        }
        for (int i = 0; i<rows && i<cols; i++){
            if (matrix[i][i]!=0)
                normStr(i , matrix[i][i]);
        }
    }

    private static void normStr(int r, double lam) {
        for (int j = 0; j < cols; j++){
            matrix[r][j]/= lam;
        }
    }

    private static void zeroCol(int nr) {
        double lam;
        int nrt;
        if(matrix[nr][nr]==0){
            nrt = firstNonZero(nr);
            if(nrt == rows){
                return;
            }
            replaceRow(nr, nrt);
        }
        for (int i=0; i < rows; i++){
            if(i!=nr){
                lam = matrix[i][nr]/matrix[nr][nr];
                subtrRow(i, nr, lam);
            }
        }
    }

    private static void subtrRow(int i, int nr, double lam) {
        for (int j = 0; j < cols; j++){
            matrix[i][j]-=matrix[nr][j]*lam;
        }
    }

    private static void replaceRow(int r1, int r2) {
        double tmp;
        for (int j = 0; j < cols;  j++){
            tmp = matrix[r1][j];
            matrix[r1][j] = matrix[r2][j];
            matrix[r2][j] = tmp;
        }
    }

    private static int firstNonZero(int nr) {
        int i = 0;
        for (i = nr+1; i < rows && matrix[i][nr]==0; i++)
            ;
        return i;
    }

    public static void printMatrix(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.printf("%10.2f", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
