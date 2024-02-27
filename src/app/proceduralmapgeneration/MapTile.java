package app.proceduralmapgeneration;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLabel;

/**
 *
 * @author anagy
 */
public class MapTile {
    JLabel label;
    ArrayList<Integer> arDown,arLeft,arUp,arRight;
    int id;
    int down,left,up,right;
    int chance;

    public MapTile() {
        this.id=0;
        this.down = 0;
        this.left = 0;
        this.up = 0;
        this.right = 0;
        this.label = new JLabel();
        this.arDown=new ArrayList();
        this.arLeft=new ArrayList();
        this.arUp=new ArrayList();
        this.arRight=new ArrayList(); 
        this.chance=1;
    }

    
    public MapTile(String id, String down, String left, String up, String right,String chance) {
        
        this.id=Integer.valueOf(id);
        this.down = Integer.valueOf(down);
        this.left = Integer.valueOf(left);
        this.up = Integer.valueOf(up);
        this.right = Integer.valueOf(right);
        this.label = new JLabel();
        this.arDown=new ArrayList();
        this.arLeft=new ArrayList();
        this.arUp=new ArrayList();
        this.arRight=new ArrayList();
        this.chance=Integer.valueOf(chance);
        
    }
    
    

    @Override
    public String toString() {
        return "MapTile{" + "label=" + label + ", arDown=" + arDown + ", arLeft=" + arLeft + ", arUp=" + arUp + ", arRight=" + arRight + ", id=" + id + ", down=" + down + ", left=" + left + ", up=" + up + ", right=" + right + '}';
    }
    
}
