
import java.io.IOException;
import java.net.URL;


public class Walmart extends Website{
    
    public Walmart(boolean q,String p, String url) throws IOException{
        
        //super walmart
        super(true, p, url,"offerPrice","/ip/", "/", ",", 4, 12, "Walmart");  
    }    
}
