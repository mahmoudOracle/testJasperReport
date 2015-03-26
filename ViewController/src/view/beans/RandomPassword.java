package view.beans;

public class RandomPassword {
    public static void main(String[] args) {
        String password = "";
        for (int i = 1; i <= 15; i++) {
            int rand =(int) (26*Math.random())+96;
            char c1 = (char)rand;
            password += c1;
           System.out.println(c1);
       }
        
        System.out.println(password);
    }
}
