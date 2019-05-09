import java.util.*;
import java.io.*;

public class TagMenu{
   
   public static void main(String[] args)throws FileNotFoundException, IOException, ClassNotFoundException{

      File file = new File("TagData.txt");
      file.createNewFile();
      
      FileInputStream fi  = new FileInputStream(file);
         
      ArrayList<Tag> list = new ArrayList<Tag>();
      try{
         ObjectInputStream in = new ObjectInputStream(fi);
         while(true){
            Tag t = (Tag) in.readObject();
            list.add(t);
         }
      }catch(EOFException e){
         // do nothing, end of file
      }
      
      /* FOR DEVELOPERS ONLY  TO VIEW TAGS ON FILE */
      /* UNCOMMENT LINE BELOW TO VIEW SERVER FILE */
      //viewList(list);
         
      fi.close();
      
      boolean exit = false;
      int choice = 0;
      
      while( !exit ){
         openMenu();
         Scanner input = new Scanner(System.in);
         if( !input.hasNextInt() ){
            // do nothing
         }else if(input.hasNextInt()){ // user entered an int
            choice = input.nextInt();
            if(choice <= 5 && choice >= 1 ){
               if(choice == 5){
                  exit = true;
               }else if(choice == 1){
                  long id = 0;
                  boolean keepGoing = true;
                  int newID_Flag = 1;
                  while(keepGoing){
                     System.out.println("Enter the Tag ID, or type 0 to cancel registration.");
                     input = new Scanner(System.in);
                     if( input.hasNext("0") ){
                        keepGoing = false;
                     }else if( input.hasNextLong() ){ // if user enters a long
                        id = input.nextLong(); // store the ID1
                        if( !list.isEmpty() ){
                           for( Tag tag : list ){ // loop through entire arraylist
                              if( id == tag.ID ){   // If a match is found, stop searching
                                 System.out.println("ID HAS ALREADY BEEN REGISTERED");
                                 newID_Flag = 0;
                                 break;
                              }else{
                                 newID_Flag = 1;
                              }
                           }
                           if( newID_Flag == 1 ){ // matching ID not found, create a new tag w ID and add to list of tags
                               System.out.println("TAG ID [" + id  + "] HAS BEEN REGISTERED.");
                               list.add( new Tag(id) ); //create tag and add to list
                               
                               FileOutputStream fo = new FileOutputStream(file); //open file
                               ObjectOutputStream out = new ObjectOutputStream(fo);
                               
                               for(Tag t : list ){ //copy each object in list to file
                                 out.writeObject(t);
                               }
                               out.close(); 
                               fo.close();
                               newID_Flag = 0;
                               keepGoing = false;
                           }
                        }else{ //empty list
                           System.out.println("TAG ID [" + id  + "] HAS BEEN REGISTERED.");
                           list.add( new Tag(id) );
                           FileOutputStream fo = new FileOutputStream(file); //open file
                           ObjectOutputStream out = new ObjectOutputStream(fo);
                               
                           for(Tag t : list ){ //copy each object in list to file
                              out.writeObject(t);
                           }
                           out.close(); 
                           fo.close();
                           newID_Flag = 0;
                           keepGoing = false;
                        }
                     }
                  }
               }else if(choice == 2){
                  long id = 0;
                  boolean keepGoing = true;
                  int flag = 1;
                  while(keepGoing){
                     System.out.println("Enter the Tag ID, or type 0 to cancel viewing status.");
                     input = new Scanner(System.in);
                     if( input.hasNext("0") ){
                        keepGoing = false;
                     }else if( input.hasNextLong() ){ // if user enters a long
                        id = input.nextLong(); // store the ID
                        if( !list.isEmpty() ){
                           for( Tag tag : list ){ // loop through entire arraylist
                              if( id == tag.ID ){   // If a match is found, stop searching
                                 System.out.println("TAG ID: ["+tag.ID+"]");
                                 if(tag.lost == true){
                                    System.out.println("STATUS: Lost");
                                    System.out.println("COORDINATES: x(" + tag.x + ")  y(" + tag.y + ")\n");
                                 }else{
                                    System.out.println("STATUS: Not Lost\n");
                                 }
                                 flag =1;
                                 keepGoing = false;
                                 break;
                              }else{
                                 flag = 0;
                              }
                           }
                           if(flag == 0){//didn't find ID in list
                              System.out.println("TAG ID [" + id  + "] IS NOT REGISTERED.");
                           }
                        }else{ //empty list
                           System.out.println("TAG ID [" + id  + "] IS NOT REGISTERED.");
                        }
                     }
                  }
               }else if(choice == 3){
                  long id = 0;
                  boolean keepGoing = true;
                  int flag = 1;
                  while(keepGoing){
                     System.out.println("Enter the Tag ID, or type 0 to cancel marking a tag lost.");
                     input = new Scanner(System.in);
                     if( input.hasNext("0") ){
                        keepGoing = false;
                     }else if( input.hasNextLong() ){ // if user enters a long
                        id = input.nextLong(); // store the ID
                        if( !list.isEmpty() ){
                           for( Tag tag : list ){ // loop through entire arraylist
                              if( id == tag.ID ){   // If a match is found, stop searching
                                 keepGoing = false;
                                 flag =1;
                                 if(tag.lost == true){
                                    System.out.println("TAG ID [" + id +"] IS ALREADY LOST.");
                                 }else{
                                    tag.lost = true;
                                    keepGoing = false;
                                    System.out.println("TAG ID [" + id  + "] IS NOW LOST.");
                                 }
                                 break;
                              }else{
                                 flag = 0;
                              }
                           }
                           if(flag == 1){ //ID found and changed status to lost, update file data.
                              FileOutputStream fo = new FileOutputStream(file); //open file
                              ObjectOutputStream out = new ObjectOutputStream(fo);
                              
                              for(Tag t : list ){ //copy each object in list to file
                                out.writeObject(t);
                              }
                              out.close(); 
                              fo.close();
                           }
                           if(flag == 0){//didn't find ID in list
                              System.out.println("TAG ID [" + id  + "] IS NOT REGISTERED.");
                           }
                        }else{ //empty list
                           System.out.println("TAG ID [" + id  + "] IS NOT REGISTERED.");
                        }
                     }
                  } 
               }else if(choice == 4){
                  long id = 0;
                  boolean keepGoing = true;
                  int flag = 1;
                  while(keepGoing){
                     System.out.println("Enter the Tag ID, or type 0 to cancel marking a tag found.");
                     input = new Scanner(System.in);
                     if( input.hasNext("0") ){
                        keepGoing = false;
                     }else if( input.hasNextLong() ){ // if user enters a long
                        id = input.nextLong(); // store the ID
                        if( !list.isEmpty() ){
                           for( Tag tag : list ){ // loop through entire arraylist
                              if( id == tag.ID ){   // If a match is found, stop searching
                                 flag = 1;
                                 keepGoing = false;
                                 if(tag.lost == false){
                                    System.out.println("TAG ID [" + id  + "] IS ALREADY FOUND.");
                                 }else{
                                    tag.lost = false;
                                    System.out.println("TAG ID [" + id  + "] IS NOW FOUND.");
                                 }
                                 break;
                              }else{
                                 flag = 0;
                              }
                           }
                           if(flag == 1){ //ID found and changed status to found, update file data.
                              FileOutputStream fo = new FileOutputStream(file); //open file
                              ObjectOutputStream out = new ObjectOutputStream(fo);
                              
                              for(Tag t : list ){ //copy each object in list to file
                                out.writeObject(t);
                              }
                              out.close(); 
                              fo.close();
                           }
                           if(flag == 0){//didn't find ID in list
                              System.out.println("TAG ID [" + id  + "] IS NOT REGISTERED.");
                           }
                        }else{ //empty list
                           System.out.println("TAG ID [" + id  + "] IS NOT REGISTERED.");
                        }
                     }
                  }
               }
            }
         } 
      }
      
   }
   
   public static void openMenu(){
      System.out.println("1. Register Tag");
      System.out.println("2. View Tag Status");
      System.out.println("3. Report Tag Lost");
      System.out.println("4. Report Tag Found");
      System.out.println("5. Exit");
      System.out.println("Enter a Menu Choice 1 - 5: ");
   }
   
   /*public static void viewList(ArrayList<Tag> list){
      for(Tag t : list ){
         System.out.println("ID: " + t.ID);
         System.out.println("Lost Status: " + t.lost);
         System.out.println("Coords: x("+t.x+") y(" + t.y + ")");
         System.out.println("-------------------------------------------------");
      }
   }*/
}