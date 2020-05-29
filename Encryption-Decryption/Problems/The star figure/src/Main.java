import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        char[][] mat = new char[n][n];
        //initialize
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                mat[i][j] = '.';
            }
        }
        //draw middle row
        for(int i = 0; i < n; i++) {
            mat[n/2][i] = '*';
        }
        // draw middle column
        for(int i = 0; i < n; i++) {
            mat[i][n/2] = '*';
        }
        // draw main diagonal
        for(int i = 0; i < n; i++) {
            mat[i][i] = '*';
        }
        // draw secondary diagonal
        for(int i = 0; i < n; i++) {
            mat[i][n-i-1] = '*';
        }
        printMat(mat);

    }

    public static void printMat(char[][] mat) {
        int rowNum = mat.length;
        int colNum = mat[0].length;

        for(int i =0; i < rowNum; i++) {
            for(int j =0;j<colNum;j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }
}