import java.io.*;
import java.util.*;

/*
 * Query Selector, A program implementing the query selector features of the SQL.
 * @author Harshit Agarwal
 * @version 1.0
 */

public class SelectProject {

	public static void main(String[] args) {
		FileReader fr1;
		List<String> conditions = new ArrayList<String>();
		List<String> displayColumns = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		List<String> columnNames;

		try {

			//Create the filereader and buffer reader
			fr1 = new FileReader(args[0]);
			BufferedReader br1 = new BufferedReader(fr1);

			//Read the columns of the table and output them.
			String s1 = br1.readLine(); 

			String column_names[] = s1.split("\t");
			columnNames = Arrays.asList(column_names);

			// divide the column tables and the conditions from the where clause.
			List<String> l1 = Arrays.asList(args);
			int ind = l1.indexOf("WHERE");

			//Add all the conditions to a list  so that they can be checked orderlly.
			for(int i=++ind ; i< args.length ; i++) {
				conditions.add(args[i]);
			}

			//Add all the display columns to the map.
			for(int i=1; i< ind-1; i++) {
				displayColumns.add(args[i]);
			}

			//Iterate over each String and check if the condition is true, if one condition fails move to
			// the new row. If all conditions pass add it to the result list.
			for (String line1 = br1.readLine(); line1 != null; line1 = br1.readLine()) {
				String values[] = line1.split("\t");
				boolean flag=  true;
				for(int i=0 ; flag && i< conditions.size(); i++) {
					String cond = conditions.get(i);
					String a[] = cond.split("\\.");
					int c = columnNames.indexOf(a[0]);
					switch(a[1]) {
					case "lt" : {
						if(flag && Integer.parseInt(values[c]) < Integer.parseInt(a[2])) {
							if(!results.contains(line1)) {results.add(line1);}
						}else {
							if(results.contains(line1)) {results.remove(line1);
							flag= false;
							break;
							}
						}
					}
					break;
					case "eq" : {
						if(flag && values[c].equalsIgnoreCase(a[2])) {
							if(!results.contains(line1)) {results.add(line1);}
						}else {
							if(results.contains(line1)) {results.remove(line1);
							flag=false;
							break;
							}
						}

					}
					break;
					case "gt" : {
						if(flag && Integer.parseInt(values[c]) > Integer.parseInt(a[2])) {
							if(!results.contains(line1)) {results.add(line1);}
						}else {
							if(results.contains(line1)) {results.remove(line1);
							flag= false;
							break;
							}
						}
					}
					break;
					case "le" : {
						if(flag && Integer.parseInt(values[c]) <= Integer.parseInt(a[2])) {
							if(!results.contains(line1)) {results.add(line1);}
						}else {
							if(results.contains(line1)) {results.remove(line1);
							flag =false;
							break;

							}
						}

					}
					case "ge" : {
						if(flag && Integer.parseInt(values[c]) >= Integer.parseInt(a[2])) {
							if(!results.contains(line1)) {results.add(line1);}
						}else {
							if(results.contains(line1)) {results.remove(line1);
							flag = false;
							break;
							}
						}

					}
					}

				}
			}

			//Displaying the column Headers	
			for(String a : displayColumns) {
				System.out.print(a+"\t");
			}
			System.out.println("");

			// Displaying the  data for the column Header
			for(int i =0 ; i< results.size() ; i++) {
				String line = results.get(i);
				for(int k=0;k< displayColumns.size(); k++) {
					int c = columnNames.indexOf(displayColumns.get(k));
					String arr[] = line.split("\t");
					System.out.print(arr[c] + "\t");
				}
				System.out.println("");

			}

			// Closing file and buffered reader
			fr1.close();
			br1.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR!");	
		}

	}
}
