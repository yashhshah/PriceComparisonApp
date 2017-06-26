/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * @author youtube
 */

public class Website{
    
    //declare variables
    String phrase;
    String web;
    String product; 
    String price;
    String foodParseSearch; 
    String priceParseSearch; 
    int foodParseSubstring; 
    int priceParseSubstring;
    static String source;
    int x = 0;
    boolean sA = false;
    static searchBar search;
    
   
    
    //website class that takes care of parsing from the actual website    
    public Website(boolean w, String t, String u, String pricePhrase, String productPhrase,
            String f, String p, int food, int pr, String source) throws IOException{
        
        //set the variables to the parameters
        phrase = t;
        web = u;
        price = pricePhrase; 
        product = productPhrase;
        foodParseSearch = f; 
        priceParseSearch = p; 
        foodParseSubstring = food;
        priceParseSubstring = pr;
        URL webpage = new URL(web);
        this.source = source;
        
        //if the product is amazon the parsing is different
        if(w==false){
            sA = true;
        }
        
        //Url Parser
        UrlParser(sA,webpage, price, product,foodParseSearch, 
                priceParseSearch, foodParseSubstring, priceParseSubstring);  
    }
    
    public static void UrlParser(boolean skipAhead,URL url, String priceString, String productString, String f, String p, int fo, int pr) throws IOException{
        
        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String g = "";
        String s = null;
        String t = null;
        String html ="";
        double vb = 0;
        boolean success = true;
        int x = 0;
        
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            
            //html parser
            String a = line;
            html+=a;
            s = html; 
            t = html;
        }
            //set indices 
            int i = s.indexOf(priceString); 
            int v = t.indexOf(productString);
            
            //amazon has a slightly different parsing rule
            if(skipAhead){
                 t = t.substring(v+1);    
                 v = t.indexOf(productString);            
            }
            
            //checks until you reach the end of the html webpage
            while(i!=-1 && v!=-1){
                
                //get the required food and price titles
                String potentialFood = t.substring(v);
                String potentialPrice = s.substring(i);              
                String foodParser = potentialFood.substring(fo);
                String priceParser = potentialPrice.substring(pr);
                int slashIndex = foodParser.indexOf(f);
                int commaIndex = priceParser.indexOf(p);
                String foodFound = foodParser.substring(0,slashIndex); 
                String priceFound = priceParser.substring(0,commaIndex);
                
                //some prices have a range for eg 13-15. These prices are discarded
                try{
                    vb = Double.parseDouble(priceFound);
                }
                catch(Exception e){
                    success = false;                
                }
                
                //if its not a range
                if(vb>1 && success){
                    search.productName.add(foodFound);
                    search.priceValue.add(priceFound);   
                    search.sourceName.add(source);
                    search.showUp.add(true);
                }
                
                //move on to the next html tag
                t = t.substring(v+1);           
                v = t.indexOf(productString);   
                s = s.substring(i+1); 
                i = s.indexOf(priceString);
                  
        }   
    }    
}
        
        
        
    
    
   
    

