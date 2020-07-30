package calculator;

import java.util.Scanner;

class PeekableScanner {
    private Scanner sc;
    private String next;

    public PeekableScanner(String line) {
        this.sc = new Scanner(line);
        this.next = (sc.hasNext()? sc.next(): null);
    }

    public boolean hasNext() {
        return next != null;
    }

    public String next() {
        String result = next;
        this.next = (sc.hasNext()? sc.next(): null);
        return result;
    }

    public String peek() {
        return next;
    }
}
