package Model

import java.util.regex.*;

1;

class LoginMenu
{
    
    
    
    public final Matcher getCommandMatcher(String input, String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }    
    
    
    public final void createUser(Matcher matcher)
    {
        
    }    
    
    
    public final void login(Matcher matcher)
    {
        
    }    
    
    
    public final void menuExit()
    {
        
    }    
    
    
    public final void showCurrentMenu()
    {
        
    }    
}
