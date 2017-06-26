
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author youtube
 */
public class Costco extends Website {
    
    public Costco(boolean q, String p, String url) throws IOException{
        //super the website class
        super(false, p, "https://www.costco.com/CatalogSearch?keyword="+p+"&pageSize=96",
                "<div class=\"price\" data-regionNav=\"DEFAULT\">$",
                "class=\"description\">", "</p>", "</div>", 20, 45, "Costco");
        
        
        
        
    
    }
    
}
