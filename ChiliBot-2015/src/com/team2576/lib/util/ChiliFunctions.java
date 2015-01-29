package com.team2576.lib.util;

import java.util.Vector;

/**
*
* @author Lucas
*/

public class ChiliFunctions {
   
   /**
    * Function to obtain absolute value of a number.
    * <p>
    * 
    * @param val value to obtain absolute value
    * @return Absolute value of 'val'
    */
   public static double abs(double val) {
       if (val < 0){
           return val * -1;
       }
       return val;
   }
   
   /**
    * Clamps value between two values.
    * <p>
    * 
    * @param val Value to clamp
    * @param min Minimum value for val
    * @param max Maximum value for val
    * @return val Clamped value between two values
    */
   public static double clamp(double val, double min, double max){
       if(val > max){
           return max;
       } else if(val < min) {
           return min;
       } else {
       return val;
       }
   }
   
   public static double overFlow(double val, double max) {
	   if(val > max){
		   return max;
	   }
	   return val;
   }
   
   public static double overFlowToZero(double val, double max) {
	   if(val > max) {
		   return 0;
	   }
	   return val;
   }
   
   public static double clamp_output(double val) {
      return clamp_output(val,1.0);
   }
   
   public static double clamp_output(double val,double max) {
	   if(val > max){
           return max;
       } else if(val < -max){
           return -max;
       } else {
           return val;
       }
   }
   
   public static double[] normalize(double[] values) {
       double max_val = 0;
       double[] return_vals = new double[values.length];
       for(int i = 0; i < values.length; i++){
           if(values[i] > max_val){
               max_val = Math.abs(values[i]);
           }
       }
       if(max_val <= 1){
           return values;
       } else {
           for(int j = 0; j < values.length; j++){
               return_vals[j] = values[j] / max_val;
           }
       }
       return return_vals;
   }
   
   public static void print(int x){
	   System.out.println(x);
   }
   
   /**
    * 
    * @param outer_index
    * @param inner_index
    * @param vector
    * @return inner value
    */
   @SuppressWarnings("rawtypes")
   public static Object doubleDimensionVectorValue(byte outer_index, byte inner_index, Vector vector) {
	   Object temp = vector.elementAt(outer_index);
	   Vector temp2 = (Vector) temp;
	   return temp2.elementAt(inner_index); 
   }

   
   public ChiliFunctions() {}
}