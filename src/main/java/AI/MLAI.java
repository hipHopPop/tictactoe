/*
Programmer: Mateo Silver
Program MLAI
Purpose: Class that deals with the Neural Network
*/

//TODO: -make MLAI.output use Sigmoid f(x)
//		-make sortByW sort *done  
//		-computer places already placed numbers *done
//		-computer keeps chooseing the same place after turn 1 *done
//		-Use text file as database to store weights *done
//			-Read and declare using database *done
//			-rewrite database after reweighting (preferably add to MLAI.reweight) *done
//		-Be able to specify what file to reweight (to use for ML v ML) *done
//		-data i/o broken*done
//    -node values being stored, but theyre being stored incorrectly
//    - make it only return the node weights in the correct format
package AI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
TODO: Make reSort() work/ 
*/

public class MLAI {
	static double w0 = 0.50,w1 = 0.50,w2 = 0.50,w3 = 0.50,w4 = 0.50,w5 = 0.50,w6 = 0.50,w7 = 0.50,w8 = 0.50;
	public static double[][] nodeWeights = { {0,w0}, {1,w1}, {2,w2}, {3,w3}, {4,w4}, {5,w5}, {6,w6}, {7,w7}, {8,w8} };
	static double[] weights = {w0, w1, w2, w3, w4, w5, w6, w7, w8};

	public static double[][] nodeWeight2 = { {0,w0}, {1,w1}, {2,w2}, {3,w3}, {4,w4}, {5,w5}, {6,w6}, {7,w7}, {8,w8} };
	static double[] weights2 = {w0, w1, w2, w3, w4, w5, w6, w7, w8};

	//nodeWeights[node][weight]
	
	public static void main(String[] args) {
		int[] usedArr = {3,4,6};
		
		
		System.out.println("\n");
		
		System.out.println("dataInput3()");
		try {
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		printArr(nodeWeights);

		System.out.println("\nReweight");
		reweight(true, usedArr, nodeWeights);
		printArr(nodeWeights);


		System.out.println("\ndataOutput()");
		try {
			dataOutput(nodeWeights, ".\\\\.\\\\WeightsDatabase.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("read new weights");
		//nodeWeights = dataInput3();
		//printArr(nodeWeights);

	}
	
	public static double[][] dataInput(String filePath) {
		double[][] output = { { 0, w0 }, { 1, w1 }, { 2, w2 }, { 3, w3 }, { 4, w4 }, { 5, w5 }, { 6, w6 }, { 7, w7 },
				{ 8, w8 } };
		File file = new File(filePath);
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				for (int i = 0, rep = 0; i < 9; i++) {
					String str = sc.nextLine();
					// System.out.println("str:"+str);
					output[rep][1] = Double.parseDouble(str);
					rep++;
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
   
   public static void dataOutput(double[][] nW, String filePath) throws IOException {
	   PrintWriter pw = new PrintWriter(new FileWriter(filePath));
	 
		for (int i = 0; i < nW.length; i++) {
			pw.write(Double.toString(nW[i][1]));
			pw.write("\n");
		}
		pw.close();

	}
	
   /*
	@SuppressWarnings("unused")
	public static void writeToWeightsDatabase(double[][] nW, String path){
		//replace values in file with weights seperated by a comma
		String eol = System.getProperty("line.separator");
      int count = 0;
		try {
			FileOutputStream file = new FileOutputStream(path);
			DataOutputStream dataOS = new DataOutputStream(file);
			BufferedWriter writeData = new BufferedWriter(new OutputStreamWriter(dataOS));
		
		do{
			//write to file
			for(int i = 0; i < nW.length; i++) {
				//System.out.println(nW[i][1]);
				dataOS.writeDouble(nW[i][1]);
			}
			
			String str = nW[0][1]+"eol"+nW[1][1]+"eol"+nW[2][1]+"eol"+nW[3][1]+"eol"+nW[4][1]+"eol"+nW[5][1]+"eol"+nW[6][1]+"eol"+nW[7][1]+"eol"+nW[8][1]; 					
			String strPrint = str.substring(0,4);
			dataOS.writeUTF(strPrint);
         count++;
		}
		while(count < 9); 
	file.flush();
   file.close();
   
    }catch(Exception e) {
         System.out.println("Exception "+e);
         //return false;
    }//end catch
		
    }//end method
*/
	
	public static double[][] sortByW(double[][] nW){
		//sort by weight [i][w]
		//uses bubble-sort
		boolean isSorted = false;
		@SuppressWarnings("unused")
		int switches = 0;
		
		while(isSorted == false) {
			isSorted = true;
			for(int i = 0; i < nW.length - 1; i++) {
				if(nW[i][1] < nW[i+1][1]) {
					double[] hold = nW[i+1];
					nW[i+1] = nW[i];
					nW[i] = hold;
					//System.out.println("Switched " +nW[i][0]+ " with " +nW[i+1][0]);
					switches++;
					isSorted = false;
				}//end if i > i+1
			}//end for int i...
			}//end while sorted is false

		
		//return sorted array
		return nW;
	}
	
	public static int output(double[][] nW, boolean[] usedArr) {
		int out = 0;
		
		while (true) {
			for (int i = 0; i < nW.length; i++) {
				out = (int) nW[i][0];
				if (usedArr[out] == true) {
					continue;
				} else {
					return out;
				}
			}
			if (out >= 9) {
				System.out.println("java machine broke");
			} else {
				return out;
			}
		}
	}
	
	public static double[][] reweight(boolean win, int[] used, double[][] nW){
		if(win) {
			for(int i = 0; i<nW.length; i++) {
				for(int j = 0; j<used.length; j++) {
					//if the int in the used array matches the int in the nodeWeights[][]
					if(nW[i][0] == used[j]) {
						nW[i][1] = nW[i][1] + 0.05;
						@SuppressWarnings("unused")
						String arrS = Arrays.toString(nW[i]);
						//System.out.println(arrS+" was reweighted");
					}
				}
			}
		}
		else if(win == false) {
			for(int i = 0; i<nW.length; i++) {
				for(int j = 0; j<used.length; j++) {
					//if the int in the used array matches the int in the nodeWeights[][]
					if(nW[i][0] == used[j]) {
						nW[i][1] = nW[i][1] - 0.05;
						@SuppressWarnings("unused")
						String arrS = Arrays.toString(nW[i]);
						//System.out.println(arrS+" was reweighted");
					}
				}
			}
		}
		return nW;
	}
	
	public static void printArr(double[][] arr) {
		System.out.println("Node  Weight");
		for(int i = 0; i<arr.length; i++) {
			System.out.print(arr[i][0]+"   "); 
			System.out.print(arr[i][1]+"\n");
		}
		}//end function
	
	public static double[][] reSort(double[][] nW) {
		boolean isSorted = false;
		@SuppressWarnings("unused")
		int switches = 0;
		
		while(isSorted == false) {
			isSorted = true;
			for(int i = 0; i < nW.length - 1; i++) {
				if(nW[i][0] > nW[i+1][0]) {
					double[] hold = nW[i+1];
					nW[i+1] = nW[i];
					nW[i] = hold;
					//System.out.println("Switched " +nW[i][0]+ " with " +nW[i+1][0]);
					switches++;
					isSorted = false;
				}//end if i > i+1
			}//end for int i...
			}//end while sorted is false

		
		//return sorted array
		return nW;
	}
	
	}//end classz
	


/*
Steps that the alg takes:
1:sort nodeWeights[][] by weight
2.choose one of top 3?
3.output choice
4.after game get data and reweight(+-0.05 win/loss)
5.rinse and repeat

import java.io.*; 

 */





