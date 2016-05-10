import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import com.Algos.TwoFour.TwoFourTree;
import com.Algos.TwoFour.ta;


public class TwoFourMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int flag = 1;
		while (flag == 1) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			Random random = new Random();
			int n = 0;
			System.out.println("Enter the value of n: ");
			try {
				n = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TwoFourTree tree = new TwoFourTree();
			for (int a = 0; a < n; a++) {
				tree.add(new ta(n));
			}
			long startTime,endTime;
			int operation = 0;
			int searchCount = 0;
			int insertCount = 0;
			int deleteCount = 0;
			startTime = System.nanoTime();
			while (operation < (2 * n)) {
				int rand = random.nextInt(10);
				if (rand < 3) {
					// Making sure insert operation runs at 0.3
					// probability
					tree.add(new ta(random.nextInt(n)));
					operation++;
					insertCount++;
					/*if (operation == (n / 2) || operation == n || operation == (3 * n / 2))
						System.out.println("Height at " + operation
								+ "th operation is " + tree.height()
								+ "; No. of nodes at this point: "
								+ functions.countNodes());*/
				}
				rand = random.nextInt(10);
				if (rand < 3) { // Making sure delete operation runs at 0.2 //
								// probability
								tree.remove(new ta(random.nextInt(n)));
								operation++;
								deleteCount++; 
								/*if (operation == (n / 2) || operation == n ||operation == (3 * n / 2))
								System.out.println("Height at " + operation +"th operation is " + functions.height() +"; No. of nodes at this point: " +
								functions.countNodes()); */
					}
				rand = random.nextInt(10);
				if (rand < 4) { // Making sure search operation runs at 0.5 //
								// probability
								tree.belongsTo(new ta(random.nextInt(n)));
								operation++;
								searchCount++; 
								/*if (operation == (n / 2) || operation == n || operation == (3 * n / 2)) 
									System.out.println("Height at " + operation + "th operation is " + functions.height() 
											+"; No. of nodes at this point: " +functions.countNodes()); */
					}
				}
			endTime = System.nanoTime() - startTime;
			/*System.out.println("Height at " + operation + "th operation is "
					+ functions.height() + "; No. of nodes at this point: "
					+ functions.countNodes());*/
			System.out.println("Total No. of search operations performed :"
					+ searchCount);
			System.out.println("Total No. of delete operations performed :"
					+ deleteCount);
			System.out.println("Total No. of insert operations performed :"
					+ insertCount);
			System.out.println("Total Time Taken:" + endTime + " nanoseconds");
			}
		}
}
