
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author youtube
 */
public class FinalOutput extends javax.swing.JFrame {

    //declare all variables. A ton are used because half of them are used to 
    //set it equal to the parameters that come in and half to get the info i want
    static FinalOutput f;
    ArrayList <String> productList;
    ArrayList <String> priceList;
    ArrayList <String> sourceList;
    ArrayList <Boolean> add = new ArrayList<Boolean>();
    ArrayList <String> finalPriceList = new ArrayList<String>(); 
    ArrayList <String> finalSourceList = new ArrayList<String>(); 
    ArrayList <String> finalProductList= new ArrayList<String>();
    ArrayList <Boolean> finalAdd = new ArrayList<Boolean>();
    ArrayList <Boolean> checkDouble = new ArrayList<Boolean>();
    ArrayList<String> outputPrice = new ArrayList<String>();
    ArrayList<String> outputSource = new ArrayList<String>();
    static boolean addToList = true;
    ArrayList <String> doubleClick = new ArrayList<String>();
    ArrayList <String> fullProduct = new ArrayList<String>();
    ArrayList <String> fullP = new ArrayList<String>();
    int lastIndex = 0;
    
    //final output constructor
    public FinalOutput(ArrayList <String> product, ArrayList <String> price, ArrayList <String> s,
            ArrayList <Boolean> a, ArrayList <String> t) {
        
        //get the info from other sources
        productList = product;
        priceList = price;
        sourceList =s;
        add = a;
        fullProduct = t;   
        addToList = true;//basically, a boolean that checks if I should add things to the list 
        
        
        for(int i = 0; i<productList.size(); i++){
            addToList = true;//value changes so set it equal to true one more time   
            
            //can only compare to previous items if i>0
            if(i>0){
                int k = i-1;
                String productName = productList.get(i);
                String pricep = priceList.get(i); 
                String source = sourceList.get(i);
                
                //runs a while loop to check all the items previously appended
                while(k!=0){
                
                    //replaces all the white spaces and ignores case differences
                    if(productName.replaceAll("\\s+","").
                        equalsIgnoreCase(productList.get(k).replaceAll("\\s+",""))){
                    
                        //if the products are from a different source
                        if(sourceList.get(k).contains(source)==false){    
                            int g = finalProductList.indexOf(productList.get(k));
                            try{
                            finalPriceList.set(g, priceList.get(k)+" "+pricep); 
                            finalSourceList.set(g, sourceList.get(k)+" "+source); 
                            finalAdd.set(g, false);   
                            fullP.set(g,fullProduct.get(k)+"*"+fullProduct.get(i));            
                            } 
                            catch(Exception e){
                            }            
                        }
                    
                 
                        //this is where i dont add the item to the list because it was being repeated
                        addToList=false;            
                    }               
                
                    k--;         
                }
            }
            
            //some listings were also blank when parsed
            if(productList.get(i).equals("")){                
                addToList = false;                
            }
            
            //if i can actually add it to the list
            if(addToList){
                
                //set variables required for the final outputs
                String text = productList.get(i);
                finalProductList.add(text);                
                finalPriceList.add(priceList.get(i)); 
                finalSourceList.add(sourceList.get(i)); 
                finalAdd.add(add.get(i));
                checkDouble.add(true);
                doubleClick.add("");
                fullP.add(fullProduct.get(i));   
            }   
        }
        
        //create the list after the array has its values so that it can display it
        initComponents();
        
        //the beauty of my code. JLists dont let you change around colours 
        //unless you make a cell renderer. 
        MyCellRenderer m = new MyCellRenderer();
        jList1.setCellRenderer(m);
        
        
        
    }

   //the cell renderer used to change colours of the text
    class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
     public MyCellRenderer() {
         setOpaque(true);
     }

     public Component getListCellRendererComponent(JList<?> list,
                                                   Object value,
                                                   int index,
                                                   boolean isSelected,
                                                   boolean cellHasFocus) {

         setText(value.toString());
         Color background;
         Color foreground;
         
         // check if this cell represents the current DnD drop location
         JList.DropLocation dropLocation = list.getDropLocation();
         if (dropLocation != null
                 && !dropLocation.isInsert()
                 && dropLocation.getIndex() == index) {
             background = Color.BLUE;
             foreground = Color.WHITE;

         // check if this cell is selected and if it repeats
         } else if (finalAdd.get(index)) {
             background = Color.white;
             foreground = Color.BLACK;

         // if it actually repeats
         } else {
             background = Color.yellow;
             foreground = Color.RED;
         };

         setBackground(background);
         setForeground(foreground);
         return this;
     }
 }
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = finalProductList.toArray(new String[finalProductList.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //checks if a specific item is clicked
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        
        //if the item is single clicked. Didnt really need this but easy to change
        //to a double click command if needed
        if(evt.getClickCount()>=1){
            //in built command that gets the index of the item selected
            int index = jList1.locationToIndex(evt.getPoint());
            textArea1.setText("");
            boolean containsSpace = true;
            int endPrice = 0;
            int endSource = 0;           
            int endProd = 0;
            //clone the arrays so they do not mess with the original ones
            outputPrice = (ArrayList<String>) finalPriceList.clone(); 
            outputSource = (ArrayList<String>) finalSourceList.clone();
            ArrayList <String> outputProd = (ArrayList<String>) fullP.clone();
            
            //if the products are repeated, I append the values with a white space
            //this code takes care of removing that space
            while(containsSpace){
                if(outputPrice.get(index).contains(" ")){                        
                    lastIndex = index;
                    endPrice = outputPrice.get(index).indexOf(" ");
                    endSource = outputSource.get(index).indexOf(" ");
                    endProd = outputProd.get(index).indexOf("*");
                    textArea1.append("Product Name: "+outputProd.get(index).substring(0,endProd) + "\n");
                    textArea1.append(outputSource.get(index).substring(0,endSource)+ ": ");
                    textArea1.append("$"+outputPrice.get(index).substring(0,endPrice)+ "\n");
                    textArea1.append("*****************************************************"+"\n");
                    outputPrice.set(index, outputPrice.get(index).substring(endPrice+1)); 
                    outputSource.set(index, outputSource.get(index).substring(endSource+1));
                    outputProd.set(index,outputProd.get(index).substring(endProd+1));               
                }
                //if no white space is detected
                else{                
                    containsSpace = false;
                }   
            }
            
            //append things if they do not have a white space or the second
            //item with a product that does
            textArea1.append("Product Name: "+outputProd.get(index)+"\n");
            textArea1.append(outputSource.get(index) + ": ");
            textArea1.append("$"+outputPrice.get(index)+ "\n"); 
        }
    }//GEN-LAST:event_jList1MouseClicked

   
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
            java.util.logging.Logger.getLogger(FinalOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinalOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinalOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinalOutput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       //set visible
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                f.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
