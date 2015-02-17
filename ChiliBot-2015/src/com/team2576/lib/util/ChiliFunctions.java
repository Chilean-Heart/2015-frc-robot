package com.team2576.lib.util;

import java.util.ArrayList;
import java.util.Vector;

/**
*
* @author Lucas
*/

public class ChiliFunctions {
   
	/**
	* Function to obtain absolute value of a number.
	* 
	* @param val value to obtain absolute value
	* @return Absolute value of 'val'
	*/
   public static double abs(double val) {
       if(val < 0) {
           return val * -1;
       }
       return val;
   }
   
   /**
    * Dead band.
    *
    * @param val the val
    * @param threshold the threshold
    * @return the double
    */
   public static double deadBand(double val, double threshold) {
	   if(Math.abs(val) > threshold) {
		   return val;
	   }
	   return 0;
   }
   
   /**
    * Fill array with zeros.
    *
    * @param array the array
    * @return the double[]
    */
   public static double[] fillArrayWithZeros(double[] array) {
	   for (int i = 0; i < array.length; i++) {
		   array[i] = 0;
	   }
	   return array;
   }
   
   /**
    * Fill array with value.
    *
    * @param array the array
    * @param value the value
    * @return the double[]
    */
   public static double[] fillArrayWithValue(double[] array, double value) {
	   for (int i = 0; i < array.length; i++) {
		   array[i] = value;
	   }
	   return array;
   }
   
   /**
    * Checks if array is filled with zeros.
    *
    * @param array the array
    * @return true, if array is with zeros
    */
   public static boolean isArrayWithZeros(double[] array){
	   for (int i = 0; i < array.length; i++) {
		   if (array[i] != 0) {
			   return false;
		   }
	   }
	   return true;
   }
   
   /**   
    * Clamps value between two values.
    * 
    * @param val Value to clamp
    * @param min Minimum value for val
    * @param max Maximum value for val
    * @return val Clamped value between two values
    */   
   public static double clamp(double val, double min, double max){
       if(val > max) {
           return max;
       } else if(val < min) {
           return min;
       } else {
       return val;
       }
   }
   

   /**
    * Over flow.
    *
    * @param val the val
    * @param max the max
    * @return the double
    */
   public static double topLimit(double val, double max) {
	   if(val > max) {
		   return max;
	   }
	   return val;
   }
   
   /**
    * Over flow to zero.
    *
    * @param val the val
    * @param max the max
    * @return the double
    */
   public static double overFlowToZero(double val, double max) {
	   if(val > max) {
		   return 0;
	   }
	   return val;
   }
   
   /**
    * Clamp output.
    *
    * @param val the val
    * @return the double
    */
   public static double clamp_output(double val) {
      return clamp_output(val,1.0);
   }
   
   /**
    * Clamp output.
    *
    * @param val the val
    * @param max the max
    * @return the double
    */
   public static double clamp_output(double val, double max) {
	   
	   if(Math.abs(val) > max) {
		   if(val < 0){
			   return -max;
		   }
		   return max;
	   }
	   return val;	
   }
   
   /**
    * Normalize wheel speeds.
    *
    * @param values the values
    * @return the double[]
    */
   public static double[] normalize(double[] values) {
       double max_val = 0;
       double[] return_vals = new double[values.length];
       for(int i = 0; i < values.length; i++){
           if(Math.abs(values[i]) > max_val){
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
   
   public static double[] rotateVector(double x, double y, double angle) {
       double cosA = Math.cos(angle * (3.14159 / 180.0));
       double sinA = Math.sin(angle * (3.14159 / 180.0));
       double out[] = new double[2];
       out[0] = x * cosA - y * sinA;
       out[1] = x * sinA + y * cosA;
       return out;
   }

   /**
    * Double dimension vector value.
    *
    * @param outer_index the outer_index
    * @param inner_index the inner_index
    * @param vector the vector
    * @return the object
    */
   @SuppressWarnings("rawtypes")
   public static Object doubleDimensionVectorValue(byte outer_index, byte inner_index, Vector vector) {
	   Object temp = vector.elementAt(outer_index);
	   Vector temp2 = (Vector) temp;
	   return temp2.elementAt(inner_index); 
   }
   
   /**
    * Transpose.
    *
    * @param array the array
    * @return the double[][]
    */
   public static double[][] transpose(ArrayList<double[]> array) {
	   if (array == null || array.size() == 0) return null;
   
	   int width = array.size();
	   int height = array.get(0).length;

	   double[][] array_new = new double[height][width];

	   for (int x = 0; x < width; x++) {
		   for (int y = 0; y < height; y++) {
			   array_new[y][x] = array.get(x)[y];
		   }
	   }
	   return array_new;
   }
   
   public ChiliFunctions() {}
}
