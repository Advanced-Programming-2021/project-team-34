import java.util.regex.*;
import java.util.Scanner;
public class Controller {
    public static Matcher matcher;
    public static Matcher matcher1;
    public static Matcher matcher2;
    public static Matcher matcher3;
    public static Matcher matcher4;
    public static Matcher matcher5;
    public static Matcher matcher6;
    Scanner scanner = new Scanner(System.in);
    String input;
    public static void runLoginMenu() {
        LoginMenu menu = new LoginMenu();
        while(ture){
            input = scanner.nextLine();
            matcher = getMatch(input,"\\s*user\\s+create\\s+username\\s+(\\w+)\\s+password\\s+\\w+\\s+nickname\\s+\\w+\\s*");
            mathcer1=getMatch(input,"\\s*user\\s+create\\s+username\\s+(\\w+)\\s+password\\s+\\w+\\s+nickname\\s+\\w+\\s*");
            mathcer2=getMatch(input,"\\s*user\\s+create\\s+username\\s+(\\w+)\\s+password\\s+\\w+\\s+nickname\\s+\\w+\\s*");
            matcher3=getMatch(input,"\\s*user\\s+create\\s+username\\s+(\\w+)\\s+password\\s+\\w+\\s+nickname\\s+\\w+\\s*");
            matcher4=getMatch(i)
            if(mathcer.matches()&&matcher1.find()&&matcher2.find()&&mathcer3.find()){

            }
        }
    }



    public Matcher getMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
