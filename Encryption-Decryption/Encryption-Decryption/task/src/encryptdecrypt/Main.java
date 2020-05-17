package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String msg = "we found a treasure!";
        StringBuilder sb = new StringBuilder();
        for(char c: msg.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                int distanceFromA = c - 'a';
                //System.out.println(distanceFromA);
                char encryptedChar = (char)('z' - distanceFromA);
                //System.out.println(encryptedChar);
                sb.append(encryptedChar);
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb.toString());
    }
}
