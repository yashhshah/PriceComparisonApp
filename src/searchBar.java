
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class searchBar extends javax.swing.JFrame {

    //searchBar class instance
    static searchBar sb;
    
    //pass booleans
    boolean walmart;
    boolean amazon;
    boolean costco;
    
    //create arraylists for the product, price and where i got it from
    static ArrayList<String> productName = new ArrayList<String>(); 
    static ArrayList<String> priceValue = new ArrayList<String>();
    static ArrayList<String> sourceName = new ArrayList<String>(); 
    static ArrayList<Boolean> showUp = new ArrayList<Boolean>();
    static ArrayList<String> fullProductName = new ArrayList<String>();
    boolean hitOnce = false;
    
    //search bar constructor
    public searchBar(boolean a, boolean b, boolean c) {
        initComponents();
        walmart = a;
        amazon = b;
        costco = c;
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jLabel1.setText("Enter a product to compare!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       
    }//GEN-LAST:event_jTextField1ActionPerformed
    //check if enter is pressed on the searchbar
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        //if enter is pressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER && hitOnce==false){           
            hitOnce = true;
            String searchField = jTextField1.getText();
            String aAndW = searchField.replaceAll("\\s+", "");
            String cost = searchField.replaceAll(" ", "+");
            
            //if walmart is clicked pass it to that class
            if(walmart){
                try {
                    Walmart waProduct = new Walmart(true, searchField,
                            "https://www.walmart.com/search/?query="+aAndW);//pg 1
                    Walmart waProduct2 = new Walmart(true, searchField,"https://www.walmart.com/se"
                            + "arch/?cat_id=0&page=2&query="+aAndW+"#searchProductResult");//pg 2
                    
                } catch (IOException ex) {
                }            
            }
            
            //if amazon is clicked
            if(amazon){
                try {
                    
                    Amazon aProduct = new Amazon(true, aAndW,
                            "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&"
                            + "field-keywords="+aAndW+"&rh=i%3Aaps%2Ck%3A"+aAndW);              
                } catch (IOException ex) {                
                }    
            }
            
            //if costco is clicked
            if(costco){
                try {
                    Costco cProduct = new Costco(true, searchField, "https://www.costco.com/CatalogSearch?keyword="
                            +cost+"&pageSize=96");
                    
                } catch (IOException ex) {
                    
                }            
            }
            
            //sorts prices from lowest to highest
            priceSorter();
            
            //passes all this info to the final output class
            FinalOutput fa = new FinalOutput(productName,priceValue, sourceName,showUp,fullProductName);
            fa.setVisible(true);    
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    //uses the selection sort algorithm 
    public static void priceSorter(){
        
        //for loop that goes through all the products and prices
        for(int i = 0; i<productName.size(); i++){            
            int index = i;             
            for(int k = index; k<productName.size(); k++){
                if(Double.parseDouble(priceValue.get(index))>Double.parseDouble(priceValue.get(k))){
                    index = k;            
                }
            }
            
            //do some parsing stuff. The end goal is to get rid of spaces using characters
            double c = Double.parseDouble(priceValue.get(index));             
            String t = productName.get(index).replace("-" , " ").replace(",", " ");
            String price = "";
            
            int indexOfNumber = t.length()-1;
            for(int q = 0; q<t.length(); q++){
                char cq = t.charAt(q); 
                if(Character.isDigit(cq)){
                    indexOfNumber = t.indexOf(cq);
                    price+=Character.valueOf(cq);
                   break;
                    
                }
                
            }
            
            //reset values based on the sorting
            fullProductName.add(t);
            t = t.substring(0,indexOfNumber);
            String s = sourceName.get(index);
            priceValue.set(index, priceValue.get(i));
            priceValue.set(i, String.valueOf(c));
            productName.set(index, productName.get(i));
            productName.set(i, t);
            sourceName.set(index, sourceName.get(i)); 
            sourceName.set(i, s);  
        }
    }
    
    
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(searchBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //invokes runnable
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sb.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
