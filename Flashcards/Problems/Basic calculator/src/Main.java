class Problem {

    public static void main(String[] args) {
        Integer lhs = Integer.parseInt(args[1]);
        Integer rhs = Integer.parseInt(args[2]);
        if(args[0].contentEquals("+")) {
            System.out.println(lhs + rhs);
        } else if (args[0].contentEquals("-")) {

            System.out.println(lhs - rhs);
        } else if (args[0].contentEquals("*")) {
            System.out.println(lhs*rhs);
        } else {
            System.out.println("Unknown operator");
        }



    }
}