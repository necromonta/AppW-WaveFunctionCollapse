package app.proceduralmapgeneration;

import static app.proceduralmapgeneration.AppProceduralMapGeneration.drawsTiles;
import static app.proceduralmapgeneration.AppProceduralMapGeneration.map;
import static app.proceduralmapgeneration.AppProceduralMapGeneration.vulcano;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author anagy
 */
class Helper extends TimerTask{
   int time=0;
   boolean yes=false;
   boolean yes2=false;
    @Override
    public void run() {
        if (time==2) {
           map[vulcano.xCord][vulcano.yCord].id=33;
            yes=true;
        }
        
        try{
        if (yes2) {
              map[vulcano.xCord-1][vulcano.yCord-1].id=34;
                 map[vulcano.xCord+1][vulcano.yCord-1].id=34;
                    map[vulcano.xCord][vulcano.yCord-2].id=34;
                    
                    map[vulcano.xCord-1][vulcano.yCord+1].id=34;
                 map[vulcano.xCord+1][vulcano.yCord+1].id=34;
                    map[vulcano.xCord][vulcano.yCord+2].id=34;
                    
                    map[vulcano.xCord-2][vulcano.yCord].id=34;
                 map[vulcano.xCord-1][vulcano.yCord-1].id=34;
                    map[vulcano.xCord-1][vulcano.yCord+1].id=34;
                    
                    map[vulcano.xCord+2][vulcano.yCord].id=34;
                 map[vulcano.xCord+1][vulcano.yCord-1].id=34;
                    map[vulcano.xCord+1][vulcano.yCord+1].id=34;
                    yes=false;
                    yes2=false;
                    drawsTiles();
        }
        if (yes) {
           map[vulcano.xCord][vulcano.yCord-1].id=34;
              map[vulcano.xCord][vulcano.yCord+1].id=34;
                 map[vulcano.xCord+1][vulcano.yCord].id=34;
                    map[vulcano.xCord-1][vulcano.yCord].id=34;
                    yes=false;
                    yes2=true;
                    drawsTiles();
        }
        }
        catch(ArrayIndexOutOfBoundsException a){
           map[vulcano.xCord][vulcano.yCord-1].id=34;
              map[vulcano.xCord][vulcano.yCord+1].id=34;
                 map[vulcano.xCord+1][vulcano.yCord].id=34;
                    map[vulcano.xCord-1][vulcano.yCord].id=34;
        }
        
        time++;
    }
    
}
    
public class AppProceduralMapGeneration {

    public static class Cords {

        int xCord;
        int yCord;

        public Cords(int xCord, int yCord) {
            this.xCord = xCord;
            this.yCord = yCord;
        }

    }
    public static JFrame frame = new JFrame();
    public static int width;
    public static int height;
    public static int firstTileX;
    public static int firstTileY;
    public static MapTile map[][];
    public static int delay;
    public static JLabel character;
    public static ArrayList<MapTile> mapTiles = new ArrayList();
      public static JLabel player=new JLabel();
      public static Cords vulcano;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException {
        
        initsAll();

       loadsMap();
       
          initsFrame();
       
        timer();
        
        frame.repaint();
        
        savesMap();
    }
    
            
    //Complex
    public static void initsAll() throws FileNotFoundException{
        loadsData();
        initsMap();
        fillsUpArrayLists(mapTiles);
    }
    
     public static void loadsMap() throws FileNotFoundException, InterruptedException{
         randomFirst();
         width=35;
         height=20;
        map = new MapTile[width][height];
         initsMap();
        Scanner sc=new Scanner(System.in);
//         System.out.println("Wanna load prev map?");
//         System.out.println("yes/no");
//         String input=sc.nextLine();
//        if (input.equals("yes")) {
//            FileReader fr=new FileReader("map.txt");
//        Scanner sc2=new Scanner(fr);
//       
//        int asd=0;
//        while(sc2.hasNextLine()){
//           String line=sc2.nextLine();
//          
//            for (int i = 0; i < width; i++) {
//             map[i][asd].id=Integer.valueOf(line.split(";")[i]);  
//            }
//            asd++;
//        }
//         generatesMap();
//
//        }else{
//         System.out.println("Map width:");
//         width=Integer.valueOf(sc.nextLine());
//         System.out.println("Map height:");
//          height=Integer.valueOf(sc.nextLine());
//         System.out.println("Delay");
//         delay=Integer.valueOf(sc.nextLine());
//          
//        }
          generatesMap();
           addsStructures(); 
        
                drawsTiles();
    }
     
     public static void generatesMap() throws InterruptedException {
        ArrayList<Cords> current = new ArrayList();
        ArrayList<Cords> next = new ArrayList();
        current.add(new Cords(firstTileX, firstTileY));
        for (int i = 0; i < 5000; i++) {
            //System.out.println("current:"+current.size());


            for (int j = 0; j < current.size(); j++) {
                int x = current.get(j).xCord;
                int y = current.get(j).yCord;
                //down
           
                if (y + 1 < height && map[x][y + 1].id == 0) {
                    if (x - 1 > -1 && map[x - 1][y + 1].id != 0) {
                        
                        map[x][y + 1].id = findsSpecificDown(returnsField(map[x - 1][y + 1].id).right, returnsField(map[x][y].id).down);
                    } else {
                        try{
                            map[x][y + 1].id = returnsField(map[x][y].id).arDown.get(random(returnsField(map[x][y].id).arDown.size()));
                        }catch(IndexOutOfBoundsException | NullPointerException e){
                            
                        }
                        

                    }

                    drawsTiles();
                    frame.repaint();
                    Thread.sleep(delay);
                    next.add(new Cords(x, y + 1));
                }

                //left
                if (x - 1 > -1 && map[x - 1][y].id == 0) {
                    if (y - 1 > -1 && map[x - 1][y - 1].id != 0) {
                        map[x - 1][y].id = findsSpecificLeft(returnsField(map[x][y].id).left, returnsField(map[x - 1][y - 1].id).down);
                    } else {
                         try{
                             map[x - 1][y].id = returnsField(map[x][y].id).arLeft.get(random(returnsField(map[x][y].id).arLeft.size()));
                         }catch(IndexOutOfBoundsException e){
                            
                        }
                        
                    }
                    drawsTiles();
                    frame.repaint();
                    Thread.sleep(delay);
                    next.add(new Cords(x - 1, y));
                }

                //up 
                if (y - 1 > -1 && map[x][y - 1].id == 0) {

                    if (x + 1 < width && map[x + 1][y - 1].id != 0) {
                        map[x][y - 1].id = findsSpecificUp(returnsField(map[x][y].id).up, returnsField(map[x + 1][y - 1].id).left);
                    } else {
                        try{
                             map[x][y - 1].id = returnsField(map[x][y].id).arUp.get(random(returnsField(map[x][y].id).arUp.size()));
                         }catch(IndexOutOfBoundsException e){
                            
                        }
                        
                    }
                    drawsTiles();
                    frame.repaint();
                    Thread.sleep(delay);
                    next.add(new Cords(x, y - 1));
                }

                //right
                if (x + 1 < width && map[x + 1][y].id == 0) {
                    if (y + 1 < height && map[x + 1][y + 1].id != 0) {
                        map[x + 1][y].id = findsSpecificRight(returnsField(map[x][y].id).right, returnsField(map[x + 1][y + 1].id).up);
                    } else if (y - 1 > -1 && map[x + 1][y - 1].id != 0) {
                        map[x + 1][y].id = findsSpecificDown(returnsField(map[x][y].id).right, returnsField(map[x + 1][y - 1].id).down);
                    } else {
                         try{
                              map[x + 1][y].id = returnsField(map[x][y].id).arRight.get(random(returnsField(map[x][y].id).arRight.size()));
                         }catch(IndexOutOfBoundsException e){
                            
                        }
                      
                    }
                  drawsTiles();
                    frame.repaint();
                    Thread.sleep(delay);
                    next.add(new Cords(x + 1, y));
                }

            }

            current.clear();
            current.addAll(next);
            next.clear();
            if (current.isEmpty()) {
                break;
            }
        }
    }
    
    //Simple 
     
    public static void savesMap() throws IOException{
          Scanner sc=new Scanner(System.in);
         System.out.println("Wanna save?");
         System.out.println("yes/no");
         String input=sc.nextLine();
         if (input.equals("yes")) {
            FileWriter wr=new FileWriter("map.txt");
             for (int i = 0; i < width; i++) {
                 for (int j = 0; j < height; j++) {
                     wr.write(map[i][j].id+";");
                 }
                 wr.write("\n");
             }
            wr.close();
        }
         System.out.println("Saved");
    }
    
    public static void timer(){
        Timer timer=new Timer();
        TimerTask task=new Helper();
        timer.schedule(task, 0,5000);
        
    }
       
    public static void randomFirst() {      
        try{
            firstTileX = (int) (Math.random() * (width));
        firstTileY = (int) (Math.random() * (height));
        map[firstTileX][firstTileY].id = (int) (Math.random() * (mapTiles.size() - 1));
        }
        catch(java.lang.NullPointerException e){
            
        }
    }
    
    public static void generatesRandomMap(){
      
      for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
               map[i][j].id=(int) (Math.random() * (mapTiles.size())+1);
            }
        }  
        drawsTiles();
    }

    public static int random(int a) {
        return (int) (Math.random() * a);
    }

    public static void loadsData() throws FileNotFoundException {
        FileReader fr = new FileReader("data.txt");
        Scanner sc = new Scanner(fr);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            MapTile temp = new MapTile(line.split(",")[0], line.split(",")[1], line.split(",")[2], line.split(",")[3], line.split(",")[4], line.split(",")[5]);
            mapTiles.add(temp);
        }
    }

    public static void fillsUpArrayLists(ArrayList<MapTile> mapTiles) {
        for (int i = 0; i < mapTiles.size(); i++) {
            for (int j = 0; j < mapTiles.size(); j++) {
                if (i != j) {
                    if (mapTiles.get(i).right == mapTiles.get(j).left) {
                        mapTiles.get(i).arRight.add(mapTiles.get(j).id);
                    }
                    if (mapTiles.get(i).left == mapTiles.get(j).right) {
                        mapTiles.get(i).arLeft.add(mapTiles.get(j).id);
                    }
                    if (mapTiles.get(i).up == mapTiles.get(j).down) {
                        mapTiles.get(i).arUp.add(mapTiles.get(j).id);
                    }
                    if (mapTiles.get(i).down == mapTiles.get(j).up) {
                        mapTiles.get(i).arDown.add(mapTiles.get(j).id);
                    }
                }
            }

        }
    }

    public static MapTile returnsField(int number) {
        if (number==0) {
            number=14;
        }
        MapTile temp = new MapTile();
        
        for (int i = 0; i < mapTiles.size(); i++) {
            if (mapTiles.get(i).id == number) {
                return mapTiles.get(i);
            }
        }
        System.out.println("Invalid id: "+number);
//        System.out.println(temp.id);
        return temp;
    }

    public static int findsSpecificDown(int r, int d) {
        ArrayList<MapTile> good = new ArrayList();
        for (int i = 0; i < mapTiles.size(); i++) {
            if (mapTiles.get(i).left == r && mapTiles.get(i).up == d) {
                for (int j = 0; j < mapTiles.get(i).chance; j++) {
                    good.add(mapTiles.get(i)); 
                }
               
            }
        }
        int rng = (int) (Math.random() * (good.size()));
        if (good.isEmpty()) {
            System.out.println("Down");
            System.out.println("Up: " + d + " Left: " + r);
            System.out.println("No tile such this was found");
            return 15;
        }
        return good.get(rng).id;
    }

    public static int findsSpecificLeft(int l, int d) {
        ArrayList<MapTile> good = new ArrayList();
        for (int i = 0; i < mapTiles.size(); i++) {
            if (mapTiles.get(i).right == l && mapTiles.get(i).up == d) {
                good.add(mapTiles.get(i));
            }
        }
        int rng = (int) (Math.random() * (good.size()));

        if (good.isEmpty()) {
            System.out.println("Left");
            System.out.println("Up: " + d + " Right: " + l);
            System.out.println("No tile such this was found");
            return 15;
        }
        return good.get(rng).id;
    }

    public static int findsSpecificUp(int u, int l) {
        ArrayList<MapTile> good = new ArrayList();
        for (int i = 0; i < mapTiles.size(); i++) {
            if (mapTiles.get(i).down == u && mapTiles.get(i).right == l) {
                good.add(mapTiles.get(i));
            }
        }
        int rng = (int) (Math.random() * (good.size()));
        if (good.isEmpty()) {
            System.out.println("Up");
            System.out.println("Down: " + u + " Right: " + l);
            System.out.println("No tile such this was found");
            return 15;
        }
        return good.get(rng).id;
    }

    public static int findsSpecificRight(int r, int u) {

        ArrayList<MapTile> good = new ArrayList();
        for (int i = 0; i < mapTiles.size(); i++) {
            if (mapTiles.get(i).left == r && mapTiles.get(i).down == u) {
                good.add(mapTiles.get(i));
            }
        }
        int rng = (int) (Math.random() * (good.size()));
        if (good.isEmpty()) {
            System.out.println("Right");
            System.out.println("Down: " + u + " Left: " + r);
            System.out.println("No tile such this was found");
            return 15;
        }

        return good.get(rng).id;
    }

    public static void drawsTiles() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                drawPic(map[i][j]);
            }
        }
        frame.repaint();
    }

    public static void soutsValidIds() {
        System.out.println("Down:");

        for (int j = 0; j < mapTiles.get(0).arDown.size(); j++) {
            System.out.print(mapTiles.get(0).arDown.get(j) + ",");
        }

        System.out.println("");
        System.out.println("Up");

        for (int j = 0; j < mapTiles.get(0).arUp.size(); j++) {
            System.out.print(mapTiles.get(0).arUp.get(j) + ",");
        }

        System.out.println("");
        System.out.println("Right");

        for (int j = 0; j < mapTiles.get(0).arRight.size(); j++) {
            System.out.print(mapTiles.get(0).arRight.get(j) + ",");
        }

        System.out.println("");
        System.out.println("Left");

        for (int j = 0; j < mapTiles.get(0).arLeft.size(); j++) {
            System.out.print(mapTiles.get(0).arLeft.get(j) + ",");
        }
        System.out.println("");
    }

    public static void initsMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new MapTile();
                map[i][j].label.setBounds(i * 50, j * 50, 50, 50);
                //  map[i][j].id=(int) (Math.random() * (mapTiles.size()-1) + 1);
            }
        }
    }

    public static void initsFrame() {
        frame.setSize(width * 50 + 16, height * 50 + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setTitle("Procedural Map Generator 3000");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                frame.add(map[i][j].label);
            }

        }
    }
    
    public static void drawPic(MapTile tile) {
        ImageIcon icon = new ImageIcon("mapTiles\\"+tile.id + ".png");
        tile.label.setIcon(icon);
    }
    
    public static void addsStructures(){
        ArrayList<Cords> temp=new ArrayList();
          ArrayList<Cords> temp2=new ArrayList();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[i][j].id==14) {
                    temp.add(new Cords(i,j));
                }
                if (map[i][j].id==13) {
                    temp2.add(new Cords(i,j));
                }
            }
        }
        
        //haz
        for (int i = 0; i < 20; i++) {
             int rng=  (int) (Math.random() * (temp.size() - 1));
        map[temp.get(rng).xCord][temp.get(rng).yCord].id=18; 
        }
        
        //fa
        for (int i = 0; i < 10; i++) {
             int rng=  (int) (Math.random() * (temp.size() - 1));
        map[temp.get(rng).xCord][temp.get(rng).yCord].id=36; 
        }
        
        //hajo
        for (int i = 0; i < 20; i++) {
             int rng=  (int) (Math.random() * (temp2.size() - 1));
        map[temp2.get(rng).xCord][temp2.get(rng).yCord].id=27; 
        }
      
        //vulkan
        int rng=  (int) (Math.random() * (temp2.size() - 1));
        vulcano=new Cords(temp2.get(rng).xCord,temp2.get(rng).yCord);
        map[temp2.get(rng).xCord][temp2.get(rng).yCord].id=32; 
        
        
        // haz --> haz
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i+1!=width) {
                   if (map[i][j].id==18&&map[i+1][j].id==18) {
                    map[i][j].id=20;
                     map[i+1][j].id=21;
                } 
                }
                
            }
        }
        
        //hajo--> hajo
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i+1!=width) {
                   if (map[i][j].id==27&&map[i+1][j].id==27) {
                    map[i][j].id=28;
                     map[i+1][j].id=29;
                }  
                }
               
            }
        }
        
        //haz up haz
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (j+1!=height) {
                    if (map[i][j].id==18&&map[i][j+1].id==18) {
                    map[i][j].id=23;
                     map[i][j+1].id=22;
                }
                }
                
            }
        }
        
         //hajo up hajo
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (j+1!=height) {
                   if (map[i][j].id==27&&map[i][j+1].id==27) {
                    map[i][j].id=30;
                     map[i][j+1].id=31;
                }  
                }
                
            }
        }
        
        // haz --> haz
        //   haz --> haz
         for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[i][j].id==20&&map[i+1][j].id==21) {
                    if (map[i][j+1].id==20&&map[i+1][j+1].id==21) {
                        map[i][j+1].id=26;
                     map[i+1][j+1].id=26; 
                    }
                   
                }
            }
        }
        
    }
    
  
    
}

