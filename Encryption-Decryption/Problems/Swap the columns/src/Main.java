import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        int rowNum = Integer.parseInt(sc.next());
        int colNum = Integer.parseInt(sc.next());
        int[][] matrix = new int[rowNum][colNum];
        for(int i = 0; i < rowNum; i++) {
            for(int j = 0; j < colNum; j++) {
                matrix[i][j] = Integer.parseInt(sc.next());
            }
        }
        int swapCol1 = Integer.parseInt(sc.next());
        int swapCol2 = Integer.parseInt(sc.next());
        if(swapCol1 != swapCol2) {
            int[] tmp = new int[rowNum];
            for(int i = 0; i < rowNum; i++) {
                tmp[i] = matrix[i][swapCol1];
            }
            for(int i = 0; i < rowNum; i++) {
                matrix[i][swapCol1] = matrix[i][swapCol2];
            }
            for(int i = 0; i < rowNum; i++) {
                matrix[i][swapCol2] = tmp[i];
            }

        }
        printMatrix(matrix);

    }
    public static void printMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = 0; i < row;i ++) {
            for(int j =0;j<col;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}