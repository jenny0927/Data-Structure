import java.io.BufferedReader;

import java.io.FileReader;
/**
 * This readFromFile class read the text file
 * @author jenny
 *
 */

public class readFromFile {
	String line;
	BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	
	public readFromFile(String filename)
	{			
		try
		{			
			BufferedReader b= new BufferedReader(new FileReader(filename));
			
			while(true)
			{
				line= b.readLine();
				
				if(line!=null)
				{
					String[] parameters = line.split(" ");
					
					if(line.equalsIgnoreCase("print_tree"))
					{
						tree.printTree();
					}
					else if(parameters.length >= 2)
					{
						String command = parameters[0];
						String v = parameters[1];
						
						if(command.equalsIgnoreCase("insert"))
						{
							Integer value = new Integer(v);
							tree.insert(new Integer(value));
						}
						else if(command.equalsIgnoreCase("remove")){
							
							Integer value = new Integer(v);
							tree.remove(new Integer(value));
						}
					}
					else if(line.equalsIgnoreCase("inorder_list")){
						
						tree.iterator();
					}
				}			
				else 
				{
					break;
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());			
		}
	}
}
