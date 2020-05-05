import java.util.Scanner;

enum Dir {
    RIGHT,
    LEFT,
    UP,
    DOWN;
}

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] result = new int[size+2][size+2];
        Pos pos = new Pos(1, 1, size );
        for(int i =1; i <= size*size; i++) {
            result[pos.getRow()][pos.getCol()] = i;
            pos.move();
        }
        for (int i=1; i < size+1; ++i) {
            for (int j=1; j < size+1; ++j) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
class Pos{
    int row;
    int col;
    int size;
    Dir currentDir;
    boolean[][] reached;
    public Pos(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.size = size;
        this.currentDir = Dir.RIGHT;
        reached = new boolean[size+2][size+2];
        for(int i = 0; i < size+2; i++) {
            for(int j = 0; j < size+2; j++) {
                if(i == 0 || i == size+1 || j == 0 || j == size+1) {
                    reached[i][j] = true;
                    continue;
                }
                reached[i][j] = false;
            }
        }
        reached[1][1] = true;
    }
    public void move() {
        reached[this.row][this.col] = true;
        switch(this.currentDir) {
            case RIGHT:
                this.col += 1;
                if(reached[this.row][this.col+1] == true) {
                    currentDir = Dir.DOWN;
                }
                break;
            case DOWN:
                this.row += 1;
                if(reached[this.row+1][this.col] == true) {
                    currentDir = Dir.LEFT;
                }
                break;
            case LEFT:
                this.col -= 1;
                if(reached[this.row][this.col-1] == true) {
                    currentDir = Dir.UP;
                }
                break;
            case UP:
                this.row -= 1;
                if(reached[this.row-1][this.col] == true) {
                    currentDir = Dir.RIGHT;
                }
                break;
        }

    }
    int getRow() {
        return this.row;
    }
    int getCol() {
        return this.col;
    }
}


