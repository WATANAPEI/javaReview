class Problem {
    public static void main(String[] args) {
        for(int i = 0; i < args.length; i++) {
            if(args[i].contentEquals("test")) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);

    }
}