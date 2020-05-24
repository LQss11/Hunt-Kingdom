/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 *
 * @author ghassen
 */
public class TextFieldvalidation {
    
    public static boolean isTextFieldNoEmpty( JFXTextField tf){
    boolean b=false;
    
    if(tf.getText().length() !=0 || !tf.getText().isEmpty())
        {
        b= true;
        }
    return b;
    }
    
    public static boolean isTextFieldNoEmpty( DatePicker tf){
    boolean b=false;
    
    if(tf.getValue() !=null)
        {
        b= true;
        }
    return b;
    }
       public static boolean isTextFieldNoEmpty( JFXTextField tf,Label lb,String errorMessage){
    boolean b=true;
     String msg=null;
     tf.getStyleClass().remove("error");
    if(!isTextFieldNoEmpty(tf)){
      
      b=false;  
      msg = errorMessage;
      tf.getStyleClass().add("error");
    }
    lb.setText(msg);
      return b;
    }
        public static boolean isTextFieldNoEmpty( DatePicker tf,Label lb,String errorMessage){
    boolean b=true;
     String msg=null;
     
    if(!isTextFieldNoEmpty(tf)){
      
      b=false;  
      msg = errorMessage;
    }
    lb.setText(msg);
      return b;
    }
       
       public static boolean istextFieldTypeNumber(JFXTextField tf)
       {
           boolean b=false;
           if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))
              b=true;
           return b;
           
       }
       public static boolean istextFieldTypeNumber(JFXTextField tf,Label lb,String errorMessage)
       {
           boolean b=true;
           String msg = null;
           tf.getStyleClass().remove("error");
           if(!istextFieldTypeNumber(tf)){
              b=true;
              msg= errorMessage;
              tf.getStyleClass().add("error");
           }
           lb.setText(msg);
           return b;
           
       }
        public static boolean isTextFieldNoEmpty(TextField tf){
    boolean b =false; 
    if(tf.getText().length() != 0 || !tf.getText().isEmpty())
        b = true; 
        
    return b;
    }
     public static boolean isTextFieldNoEmpty(TextField tf, Label lb , String errorMessage){
    boolean b =true; 
    String msg = null;
    if(!isTextFieldNoEmpty(tf)){
        b = false; 
        msg = errorMessage;
    }
    lb.setText(msg);
    return b;
    }
     
     
   public static boolean textFieldNumber(TextField tf)
   {
       boolean b=false; 
       if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"));
        b= true;
        
        return b;
   
   
   
   }    
    public static boolean textFieldNumber(TextField tf,Label lb,String errorMessage)
   {
       boolean b=true; 
       String msg=null;
         if(!textFieldNumber(tf)){
         b = false; 
        msg = errorMessage;
    }
    lb.setText(msg);
    return b;
   }    
    
    
     public static boolean TextFieldNom(TextField tf, Label lb , String errorMessage){
    boolean b =true; 
    String msg = null;
    if(!isTextFieldNoEmpty(tf)){
        b = false; 
        msg = errorMessage;
    }
    if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+")){
        b = false; 
        msg = "can't contain numbers";
    }
    lb.setText(msg);
    return b;
    }
   public static boolean TextFieldNumber(TextField tf, Label lb , String errorMessage){
    boolean b =true; 
    String msg = null;
    if(!(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))){
        b = false; 
        msg = "can only contain numbers";
    }
    if(!isTextFieldNoEmpty(tf)){
        b = false; 
        msg = errorMessage;
    }
    
    lb.setText(msg);
    return b;
    }
}
