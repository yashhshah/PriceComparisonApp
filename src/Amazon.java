
import java.io.IOException;


public class Amazon extends Website {
    
    public Amazon(boolean q, String p, String url) throws IOException{
        
        //super amazon
        super(true, p, url,"aria-label=\"$", 
                "h2 data-attribute=", "\" data-max-rows", "\"",19,13, "Amazon");  
    }    
}
