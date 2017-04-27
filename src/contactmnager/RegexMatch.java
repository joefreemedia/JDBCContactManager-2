/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmnager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author phil
 */
public class RegexMatch {
    private String regex;
    public RegexMatch(String regex){
        this.regex = regex;
    }
    
    public void SetRegex(String regex){
        this.regex = regex;
    }
    //for checking matches after you hit the insert button
    public boolean CheckExp(String str){
        Pattern pattern = Pattern.compile(this.regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
}
