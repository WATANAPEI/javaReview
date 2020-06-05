package solver;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String inFilePath = args[1];
        //System.out.println(inFilePath);
        String outFilePath = args[3];
        //System.out.println(outFilePath);

        //double x = 0;
        //double y = 0;
        //double z = 0;
        int varNum = 0;
        double[] result = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inFilePath))) {
            varNum = Integer.parseInt(bufferedReader.readLine());
            double[][] matrix = new double[varNum][varNum+1];

            // parse input matrix
            for(int i = 0; i < varNum; i++){
                String[] numbersInRow = bufferedReader.readLine().split("\\s+");
                for(int j = 0; j < numbersInRow.length; j++) {
                    matrix[i][j] = Double.parseDouble(numbersInRow[j]);
                }
            }
            //printMatrix(matrix);

            /*
            for(int i = 0; i < varNum - 1; i++) {
                double r = matrix[i+1][0] / matrix[0][0];
                for(int j = 0; j < matrix[i].length; j++) {
                    matrix[i+1][j] -= r * matrix[0][j];
                }
            }
            if(matrix[1][1] != 1 ) {
                double r = matrix[1][1];
                for(int i = 0; i < matrix[1].length; i++) {
                    matrix[1][i] /= r;
                }
            }
            //printMatrix(matrix);
            for(int i = 1; i < varNum - 1; i++) {
                double r = matrix[i+1][1] / matrix[1][1];
                for(int j = 1; j < matrix[i].length; j++) {
                    matrix[i+1][j] -= r * matrix[1][j];
                }
            }
            if(matrix[2][2] != 1 ) {
                double r = matrix[2][2];
                for(int i = 0; i < matrix[2].length; i++) {
                    matrix[2][i] /= r;
                }
            }

             */
            double c = matrix[0][0];
            for(int i = 0; i <= varNum; i++) {
                matrix[0][i] /= c;
            }
            for(int k = 0; k < varNum - 1; k++) {
                for(int i = k; i < varNum - 1; i++) {
                    double r = matrix[i+1][k] / matrix[k][k];
                    for(int j = k; j < matrix[i].length; j++) {
                        matrix[i+1][j] -= r * matrix[k][j];
                    }
                }
                if(matrix[k+1][k+1] != 1 ) {
                    double r = matrix[k+1][k+1];
                    for(int i = 0; i < matrix[k+1].length; i++) {
                        matrix[k+1][i] /= r;
                    }
                }

            }
            //printMatrix(matrix);

            // solve reversely
            //z = matrix[2][3];
            //y = matrix[1][3] - matrix[1][2] * z;
            //x = matrix[0][3] - matrix[0][2] * z - matrix[0][1] * y;
            result = new double[varNum];
            for(int i = 0; i < varNum; i++) {
                result[varNum-i-1] = matrix[varNum-i-1][varNum];
                for(int j = 0 ; j < i ;j++) {
                    result[varNum-i-1] -= matrix[varNum-i-1][varNum-j-1] * result[varNum-j-1];
                }
            }
            //System.out.println(x);
            //System.out.println(y);
            //System.out.println(z);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFilePath))){
            //bufferedWriter.write(String.valueOf(x) + "\n");
            //bufferedWriter.write(String.valueOf(y) + "\n");
            //bufferedWriter.write(String.valueOf(z) + "\n");
            for(int i = 0 ; i < varNum; i++) {
                bufferedWriter.write(String.valueOf(result[i]) + "\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void printMatrix(double[][] matrix) {
        System.out.println("*******************************");
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("*******************************");
    }
}
