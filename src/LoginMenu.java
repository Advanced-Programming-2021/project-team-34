import java.util.regex.*
public class LoginMenu {
    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
    public createUser(String username , String password , String nickname){
        if(!Player.getPlayerByUsername(username).equals(null)){
            System.out.println("user with username "+username+" already exists");
        }else if(!Player.getPlayerByNicknaPlayer(username , password , nickname).equals(null)){
            System.out.println("user with nickname "+nickname+" already exists");
        }else{
            new Player(username,password,nickname);
            System.out.println("user created successfully!");
        }
    }
    public void login(String username , String password) {
        Player player = Player.getPlayerByUsername(username , password);
        if(player.equals(null)){
            System.out.println("Username and password didn't match!");
        }else if(!player.getPassword().equals(password)){
            System.out.println("Username and password didn't match!");
        }else{
            System.out.println("user logged in successfully!");
        }
    }
    public menuExit(){
        ;
    }
    public showCurrentMenu() {
        ;
    }

}
