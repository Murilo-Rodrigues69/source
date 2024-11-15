package Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.product;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		List<product> list = new ArrayList<>();
		System.out.println("Enter file path: ");
		String source = sc.nextLine();
		
		File sourceFile = new File(source);
		String sourcertv = sourceFile.getParent();
		

		boolean sucess = new File(sourcertv + "\\out").mkdir();
		
		String target = sourcertv + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(source))){
			
			String item = br.readLine();
			
			while (item != null) {
				String [] filds = item.split(",");
				String name = filds[0];
				Double price = Double.parseDouble(filds[1]);
				int quantity = Integer.parseInt(filds[2]);
				list.add(new product(name,price,quantity));
				item = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(target))){
				
				for(product iteme : list) {
					bw.write(iteme.getName()+";"+ String.format("%.2f", iteme.totalPrice()));
					bw.newLine();
				}
				System.out.println(target + "CREATED");
			}
			catch (IOException e){
				System.out.println("Error file:"+e.getMessage());
			}
		}
		catch(IOException e) {
			System.out.println("Error file:"+e.getMessage());
		}
		
	}

}
