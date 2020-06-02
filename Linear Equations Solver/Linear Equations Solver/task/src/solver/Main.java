package solver;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inFilePath = args[0];
        System.out.println(inFilePath);
        String outFilePath = args[1];
        System.out.println(outFilePath);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inFilePath))) {
            int varNum = Integer.parseInt(bufferedReader.readLine());
            int[][] matrix = new int[varNum][varNum+1];

            for(int i = 0; i < varNum; i++){
                String[] numbersInRow = bufferedReader.readLine().split("\\s+");
                for(int j = 0; j < numbersInRow.length; j++) {
                    matrix[i][j] = Integer.parseInt(numbersInRow[j]);
                }
            }
            printMatrix(matrix);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
