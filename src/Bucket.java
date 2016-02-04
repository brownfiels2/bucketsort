/**  
 *  Nick Wade, Steve Brownfield, Ryan Guard, Kurt Herschede, Alex Altevers
 *  Group Project 3
 *  CSC 364, Spring 2016
 *  02-04-2016
 *  Description: Bucket sort algorithm will sort a set of integers
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A version of a bucket sort which puts values into buckets based on their 
 * range and then use insertion sort to sort each bucket. 
 */
public class Bucket{
    /**
     * Entry point
     * @param args the command line arguments
     */	
    public static void main(String[] args){
        //create the test data
        int[] set1 = createRandomSet(100,100);
        int[] set2 = createRandomSet(1000,100);
        int[] set3 = createRandomSet(10000,100);
        ArrayList<int[]> testData = new ArrayList<>();
        testData.add(set1);
        testData.add(set2);
        testData.add(set3);     
        
        //Sort test data and print results - Change numberOfBuckets as needed.
        long start, end;
        int numberOfBuckets = 10;
        for(int i = 0; i < testData.size(); i++){
            //print array before sorted for comparision
            System.out.println("Before: " + Arrays.toString(testData.get(i)));  
            
            //Send set to bucketSort() and track the elapsed time
            start = System.nanoTime();
            bucketSort(testData.get(i), numberOfBuckets);
            end = System.nanoTime();
            
            //print sorted results for comparision
            System.out.println("After " + (end - start) + " nano seconds " + 
                    Arrays.toString(testData.get(i)));   
            System.out.println();
        }
    }
    /**
     * Generates a set of random integers
     * 
     * @param n The number of integers to generate
     * @param range The max value of any randomly generated integer
     * @return set An array of randomly generated integers
     */
    public static int[] createRandomSet(int n, int range){
        int[] set = new int[n];
        int temp;
        for(int i = 0; i < set.length; i++){
            temp = (int)((range+1) * Math.random());
            set[i] = temp;
        }		
        return set;
    }
    /**
     * Sorts an array of integers using the Insertion Sort algorithm
     * This is used to sort each bucket of integers and at the same time
     * it inserts them into the given set
     * 
     * @param set The set to insert the sorted integers into
     * @param numbers This is the numbers to sorted and added to parameter set
     */
    private static void insertionSort(int[] set, Integer... numbers){
        for(int i = 0; i < numbers.length; i++){
            int currentElement = i;
            int k;
            for(k = i-1; k >= 0 && set[k] > currentElement; k--){
                    set[k+1] = numbers[k];
            }
            set[k+1] = currentElement;
        }        
    }        
    /**
     * Sorts an array of integers using the Bucket Sort algorithm
     * 
     * @param set The set of integers to sort
     * @param numBuckets The number of buckets to sort into
     * @return set The set of sorted integers
     */
    public static int[]  bucketSort(int[] set, int numBuckets){
        //create an array of buckets 
        ArrayList<Integer>[] buckets = new ArrayList[numBuckets];
        for(int i = 0; i < numBuckets; i++){
                buckets[i] = new ArrayList<>();
        }

        //insert integers from the orginal set into the correct bucket 
        int key;
        for(int i = 0; i < set.length; i++){                    
            key = set[i] / (numBuckets + 1);
            buckets[key].add(set[i]);
        } 

        //sort each bucket and insert into the set during the insertion sort
        for(int i = 0; i < buckets.length; i++){   
            insertionSort(set, buckets[i].toArray(new Integer[buckets[i].size()]));
        }

        return set;
    }
}
