import java.util.*;
import java.io.Serializable;

public class Tag implements Serializable{
   public long ID;
   public boolean lost;
   public int x;
   public int y;
   
   public Tag(long num){
      this.ID = num;
      this.lost = false;
      this.x = (int) (Math.random() * ( 100 - (-100) ) + (-100));
      this.y = (int) (Math.random() * ( 100 - (-100) ) + (-100));
   }
   
   public Tag(long num, boolean l, int x1, int y1){
      this.ID = num;
      this.lost = l;
      this.x = x1;
      this.y = y1;
   }

}