/* CHANGELOG: 
   Initial release: 200 tests

   FINAL TESTS
   These tests must be passed by the final deadline for the project in
   order to get credit for the Automated Testing portion of the
   project. 

   To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   UNIX/MAX OS X: in the terminal do
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar HW1FinalTests   # run tests
 
   WINDOWS: in cmd.exe, same commands but replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar HW1FinalTests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import org.junit.Rule;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import java.util.*;
import java.io.*;

public class HW1FinalTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("HW1FinalTests");
  }
  
  // Global timeout for all tests: use argument to Timeout.millis( __ );
  //@Rule public Timeout globalTimeout = Timeout.millis(1000); 

  // // Alternative test declaration; includes both explicit timeout
  // // and case where an exception is expected
  // @Test(timeout=1000,expected=SomeException.class)


  // ADHERENCE TO EXPANDABLEBOARD INTERFACE
  @Test public void expandableBoard1(){
    ExpandableBoard<String> b;
    b = new DenseBoard<String>(+0, +2, +0, +4, "");
  }

  // CONSTRUCTOR EXISTENCE TESTS
  @Test public void constructor1(){
    DenseBoard<String> b = new DenseBoard<String>(+0, +2, +0, +4, "");
  }
  @Test public void constructor2(){
    DenseBoard<String> b = new DenseBoard<String>(-4, +2, -5, -2, "ok");
  }
  @Test public void constructor3(){
    DenseBoard<Integer> b = new DenseBoard<Integer>(-3, +8, -3, 0, 0);
  }
  @Test public void constructor4(){
    Double fill = new Double(0.5);
    DenseBoard<Double> b = new DenseBoard<Double>(fill);
  }
  @Test public void constructor5(){
    Object fill = new Object();
    DenseBoard<Object> b = new DenseBoard<Object>(fill);
  }
  @Test public void constructor6(){
    Integer mat [][] = {
      { 1, 2, 3, 0},
      { 0, 0, 0, 0},
      { 4, 0, 5, 6},
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
  }

  // BOARD EXTENT TESTS
  @Test public void extents1(){
    int rowMin=0, rowMax=2, colMin=0, colMax=4;
    String fill="";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
    assertEquals(rowMax-rowMin+1, b.getPhysicalRows());
    assertEquals(colMax-colMin+1, b.getPhysicalCols());
  }
  @Test public void extents2(){
    int rowMin=-1, rowMax=-1, colMin=1, colMax=1;
    String fill="??";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
    assertEquals(rowMax-rowMin+1, b.getPhysicalRows());
    assertEquals(colMax-colMin+1, b.getPhysicalCols());
  }
  @Test public void extents3(){
    int rowMin=-5, rowMax=+10, colMin=-10, colMax=-2;
    Integer fill=0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
    assertEquals(rowMax-rowMin+1, b.getPhysicalRows());
    assertEquals(colMax-colMin+1, b.getPhysicalCols());
  }
  @Test public void extents4(){
    int rowMin=-5, rowMax=+10, colMin=-3, colMax=8;
    Integer fill=0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
    assertEquals(rowMax-rowMin+1, b.getPhysicalRows());
    assertEquals(colMax-colMin+1, b.getPhysicalCols());
  }
  @Test public void extents5(){
    int rowMin=0, rowMax=0, colMin=0, colMax=0;
    Double fill = new Double(0.5);
    DenseBoard<Double> b = new DenseBoard<Double>(fill);
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
    assertEquals(rowMax-rowMin+1, b.getPhysicalRows());
    assertEquals(colMax-colMin+1, b.getPhysicalCols());
  }
  @Test public void extents6(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      { 1, 2, 3, 0, 0, 3, 8, },
      { 4, 0, 5, 6, 0, 3, 8, },
      { 0, 0, 0, 0, 0, 3, 8, },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
    assertEquals(rowMax-rowMin+1, b.getPhysicalRows());
    assertEquals(colMax-colMin+1, b.getPhysicalCols());
  }

  // GET(i,j) TESTS WITH VARIOUS CONSTRUCTORS

  // In bounds only
  @Test public void get1(){
    DenseBoard<String> b = new DenseBoard<String>(+0, +2, +0, +4, "");
    assertEquals("", b.get(0,0));
    assertEquals("", b.get(0,4));
    assertEquals(new String(""), b.get(1,1));
    assertEquals("", b.get(2,0));
    assertEquals("", b.get(2,4));
  }
  // With out of bounds
  @Test public void get2(){
    DenseBoard<String> b = new DenseBoard<String>(-4, +2, -5, -2, "ok");
    assertEquals("ok", b.get(0,0));
    assertEquals(new String("ok"), b.get(1,1));
    assertEquals("ok", b.get(-4,-2));
    assertEquals("ok", b.get(-4,-5));
    assertEquals("ok", b.get(-4,-1));
    assertEquals("ok", b.get(+2,-3));
    assertEquals("ok", b.get(+3,-8));
    assertEquals("ok", b.get(-8,-8));
  }
  @Test public void get3(){
    DenseBoard<Integer> b = new DenseBoard<Integer>(-3, +8, -3, 0, 0);
    Integer fill = new Integer(0);
    assertEquals(fill, b.get(0,0));
    assertEquals(new Integer(0), b.get(0,0));
    assertEquals(fill, b.get(-3,-3));
    assertEquals(fill, b.get(+8,0));
    assertEquals(fill, b.get(-5,0));
    assertEquals(fill, b.get(+10,+2));
  }
  @Test public void get4(){
    Double fill = new Double(0.5);
    DenseBoard<Double> b = new DenseBoard<Double>(fill);
    assertEquals(new Double(0.5), b.get(0,0));
    assertEquals(fill, b.get(0,0));
    assertEquals(fill, b.get(1,0));
    assertEquals(fill, b.get(0,-1));
    assertEquals(fill, b.get(+1,-1));
    assertEquals(fill, b.get(-4,0));

  }
  @Test public void get5(){
    Object fill = new Object();
    DenseBoard<Object> b = new DenseBoard<Object>(fill);
    assertEquals(fill, b.get(0,0));
    assertEquals(fill, b.get(+1,+0));
    assertEquals(fill, b.get(+0,+1));
    assertEquals(fill, b.get(+1,+1));
    assertEquals(fill, b.get(-3,-2));
  }
  @Test public void get6(){
    Integer mat [][] = {
      { 1, 2, 3, 0},
      { 0, 0, 0, 0},
      { 4, 0, 5, 6},
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    for(int row=0; row<mat.length; row++){
      for(int col=0; col<mat.length; col++){
        assertEquals(mat[row][col], b.get(row,col));
      }
    }
    assertEquals(fill, b.get(-1,+0));
    assertEquals(fill, b.get(+0,-1));
    assertEquals(fill, b.get(+3,+4));
  }
  // Prove independence of the board from given 2d array
  @Test public void get7(){
    Integer mat [][] = {
      { 1, 2, 3, 0},
      { 0, 0, 0, 0},
      { 4, 0, 5, 6},
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    mat[0][0] = new Integer(10);
    mat[1][2] = new Integer(11);
    
    assertEquals(new Integer(1), b.get(+0,+0));
    assertEquals(new Integer(0), b.get(+1,+2));
  }

  // SIMPLE GET AND SET FILL ELEMENT TESTS
  @Test public void get_setFillElem1(){
    DenseBoard<String> b = new DenseBoard<String>(+0, +2, +0, +4, "");
    assertEquals("", b.getFillElem());
  }
  @Test public void get_setFillElem2(){
    DenseBoard<String> b = new DenseBoard<String>(-4, +2, -5, -2, "ok");
    assertEquals("ok", b.getFillElem());
    String newFill = "ACK";
    b.setFillElem(newFill);
    assertEquals(newFill, b.getFillElem());
    assertEquals(newFill, b.get(-4,-2));
    assertEquals(newFill, b.get(-4,-5));
    assertEquals(newFill, b.get(-4,-1));
    assertEquals(newFill, b.get(+2,-3));
    assertEquals(newFill, b.get(+3,-8));
    assertEquals(newFill, b.get(-8,-8));
  }
  @Test public void get_setFillElem3(){
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(-3, +8, -3, 0, fill);
    assertEquals(fill, b.getFillElem());
    Integer newFill = 10;
    b.setFillElem(newFill);
    assertEquals(newFill, b.getFillElem());
    assertEquals(newFill, b.get(0,0));
    assertEquals(newFill, b.get(-3,-3));
    assertEquals(newFill, b.get(+8,0));
    assertEquals(newFill, b.get(-5,0));
    assertEquals(newFill, b.get(+10,+2));
  }
  @Test public void get_setFillElem4(){
    Double fill = new Double(0.5);
    DenseBoard<Double> b = new DenseBoard<Double>(fill);
    assertEquals(fill, b.getFillElem());
    assertEquals(new Double(fill), b.getFillElem());
    Double newFill = 1.5;
    b.setFillElem(newFill);
    assertEquals(newFill, b.getFillElem());
    assertEquals(new Double(newFill), b.getFillElem());

    assertEquals(newFill, b.get(0,0));
    assertEquals(newFill, b.get(+1,+0));
    assertEquals(newFill, b.get(+0,+1));
    assertEquals(newFill, b.get(+1,+1));
    assertEquals(newFill, b.get(-3,-2));

    // Second change to fill element
    newFill = -1.5;
    b.setFillElem(newFill);
    assertEquals(newFill, b.getFillElem());
    assertEquals(new Double(newFill), b.getFillElem());

    assertEquals(newFill, b.get(0,0));
    assertEquals(newFill, b.get(+1,+0));
    assertEquals(newFill, b.get(+0,+1));
    assertEquals(newFill, b.get(+1,+1));
    assertEquals(newFill, b.get(-3,-2));
  }
  @Test public void get_setFillElem5(){
    Object fill = new Object();
    DenseBoard<Object> b = new DenseBoard<Object>(fill);
    assertEquals(fill, b.getFillElem());
    Object newFill = new Object();
    b.setFillElem(newFill);
    assertEquals(newFill, b.getFillElem());

    assertEquals(newFill, b.get(0,0));
    assertEquals(newFill, b.get(+1,+0));
    assertEquals(newFill, b.get(+0,+1));
    assertEquals(newFill, b.get(+1,+1));
    assertEquals(newFill, b.get(-3,-2));

  }

  // Trickier test: requires that during the 2D array constructor,
  // fill elements present in the provided 2D array are propery
  // "converted" into whatever internal fill element representation is
  // used so that changing the fill element later alters what is
  // returned for the fill positions
  @Test public void get_setFillElem6(){
    Integer mat [][] = {
      { 1, 2, 3, 0},
      { 0, 0, 0, 0},
      { 4, 0, 5, 6},
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    assertEquals(fill, b.getFillElem());

    Integer newFill = -1;
    b.setFillElem(newFill);    
    assertEquals(newFill, b.getFillElem());

    for(int row=0; row<mat.length; row++){
      for(int col=0; col<mat.length; col++){
        if(mat[row][col].equals(fill)){
          assertEquals(newFill, b.get(row,col));
        }
        else{
          assertEquals(mat[row][col], b.get(row,col));
        }
      }
    }
    assertEquals(newFill, b.get(-1,+0));
    assertEquals(newFill, b.get(+0,-1));
    assertEquals(newFill, b.get(+3,+4));
    
    // Second change
    newFill = +22;
    b.setFillElem(newFill);    
    assertEquals(newFill, b.getFillElem());

    for(int row=0; row<mat.length; row++){
      for(int col=0; col<mat.length; col++){
        if(mat[row][col].equals(fill)){
          assertEquals(newFill, b.get(row,col));
        }
        else{
          assertEquals(mat[row][col], b.get(row,col));
        }
      }
    }
    assertEquals(newFill, b.get(-1,+0));
    assertEquals(newFill, b.get(+0,-1));
    assertEquals(newFill, b.get(+3,+4));
  }

  // TOSTRING TESTS
  @Test public void toString1(){
    int rowMin=1, rowMax=3, colMin=1, colMax=3;
    String fill="";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    String expectS =
      "    |  1|  2|  3|\n"+
      "    +---+---+---+\n"+
      "  1 |   |   |   |\n"+
      "    +---+---+---+\n"+
      "  2 |   |   |   |\n"+
      "    +---+---+---+\n"+
      "  3 |   |   |   |\n"+
      "    +---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void toString2(){
    int rowMin=-1, rowMax=-1, colMin=1, colMax=1;
    String fill="??";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    String expectS =
      "    |  1|\n"+
      "    +---+\n"+
      " -1 | ??|\n"+
      "    +---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void toString3(){
    int rowMin=-5, rowMax=+10, colMin=-10, colMax=-2;
    Integer fill=0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    String expectS =
      "    |-10| -9| -8| -7| -6| -5| -4| -3| -2|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      " -5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      " -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      " -3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      " -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      " -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  7 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  8 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "  9 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      " 10 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void toString4(){
    int rowMin=-5, rowMax=+10, colMin=-3, colMax=8;
    Integer fill=0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    String expectS =
      "    | -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|  8|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      " -5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      " -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      " -3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      " -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      " -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  7 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  8 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "  9 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      " 10 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
      "    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void toString5(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      { 1, 2, 3, 0, 0, 3, 8, },
      { 4, 0, 5, 6, 0, 3, 8, },
      { 0, 0, 0, 0, 0, 3, 8, },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    String expectS =
      "    |  0|  1|  2|  3|  4|  5|  6|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  0 |  1|  2|  3|  0|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  1 |  4|  0|  5|  6|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  2 |  0|  0|  0|  0|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void toString6(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      {100,  2,  3,  0,  0,  3,  8,  },
      {  4,  0,  5,  6,  0,  3,  8,  },
      {  0,  0,  0,999,  0,-13,108,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    String expectS =
      "    |  0|  1|  2|  3|  4|  5|  6|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  0 |100|  2|  3|  0|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  1 |  4|  0|  5|  6|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  2 |  0|  0|  0|999|  0|-13|108|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }

  // Changing fill works with 2D constructor and toString
  @Test public void toString_change_fill1(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      {100,  2,  3,  0,  0,  3,  8,  },
      {  4,  0,  5,  6,  0,  3,  8,  },
      {  0,  0,  0,999,  0,-13,108,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    fill = -99;
    b.setFillElem(fill);
    String expectS =
      "    |  0|  1|  2|  3|  4|  5|  6|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  0 |100|  2|  3|-99|-99|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  1 |  4|-99|  5|  6|-99|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  2 |-99|-99|-99|999|-99|-13|108|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }

  // IN BOUNDS SET TESTS
  @Test public void set_inbounds1(){
    int rowMin=0, rowMax=2, colMin=0, colMax=4;
    String fill = "";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    int row,col; String elem;
    row=0; col=0; elem="A";
    b.set(row,col,elem);
    assertEquals(elem, b.get(row,col));
    row=2; col=4; elem="B";
    b.set(row,col,elem);
    assertEquals(elem, b.get(row,col));
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
  }
  @Test public void set_inbounds2(){
    int rowMin=-3, rowMax=2, colMin=0, colMax=4;
    String fill = "??";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    int row,col; String elem;
    row=1; col=0; elem="A";
    b.set(row,col,elem);
    assertEquals(elem, b.get(row,col));
    row=2; col=4; elem="B";
    b.set(row,col,elem);
    assertEquals(elem, b.get(row,col));
    assertEquals(rowMin,b.getMinRow());
    assertEquals(rowMax,b.getMaxRow());
    assertEquals(colMin,b.getMinCol());
    assertEquals(colMax,b.getMaxCol());
  }
  @Test public void set_inbounds3(){
    int rowMin=-3, rowMax=2, colMin=0, colMax=4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,0,1},
      {2,4,2},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  1|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  2|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void set_inbounds4(){
    int rowMin=-3, rowMax=2, colMin=0, colMax=4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-3,+2,+1},
      {-2,+4,+2},
      {+2,+0,+3},
      {+1,+3,+4},
      {-3,+0,+5},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+\n"+
" -3 |  5|  0|  1|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  2|\n"+
"    +---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  4|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  3|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void set_inbounds5(){
    int rowMin=-7, rowMax=-2, colMin=-3, colMax=+7;
    int fill = 999;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4,+3,+10},
      {-2,+4,-20},
      {-6,+7,+30},
      {-4,+0,-40},
      {-7,+0,+50},
      {-7,-2,+60},
      {-6,-1,-70},
    };
    applySets(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 |999| 60|999| 50|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 |999|999|-70|999|999|999|999|999|999|999| 30|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |999|999|999|999|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |999|999|999|-40|999|999| 10|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |999|999|999|999|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |999|999|999|999|999|999|999|-20|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }

  // Cannot set elements twice, raises an exception
  @Test public void set_inbounds_exception1(){
    int rowMin=-3, rowMax=2, colMin=0, colMax=4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,0,1},
      {2,4,2},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  1|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  2|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    boolean thrown = false;
    String expectMsg = "Element 1 0 already set to 1"; 
    try {
      b.set(1,0,5);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }
  @Test public void set_inbounds_exception2(){
    int rowMin=-7, rowMax=-2, colMin=-3, colMax=+7;
    int fill = 999;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4,+3,+10},
      {-2,+4,-20},
      {-6,+7,+30},
      {-4,+0,-40},
      {-7,+0,+50},
      {-7,-2,+60},
      {-6,-1,-70},
    };
    applySets(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 |999| 60|999| 50|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 |999|999|-70|999|999|999|999|999|999|999| 30|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |999|999|999|999|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |999|999|999|-40|999|999| 10|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |999|999|999|999|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |999|999|999|999|999|999|999|-20|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    boolean thrown = false;
    String expectMsg = "Element -7 -2 already set to 60";
    try {
      b.set(-7,-2,+65);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }
  // In bounds set tests 2D array constructor
  @Test public void set_inbounds_2D_constructor1(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      { 10,  2,  3,  0,  0,  3,  8,  },
      {  4,  0,  5,  6,  0,  3,  8,  },
      {  0,  0,  0,  9,  0, 13, 12,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {+2,+1,-1},
      {+2,+4,-2},
      {+1,+4,-3},
      {+2,+2,-4},
    };
    applySets(sets,b);
    String expectS =
      "    |  0|  1|  2|  3|  4|  5|  6|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  0 | 10|  2|  3|  0|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  1 |  4|  0|  5|  6| -3|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  2 |  0| -1| -4|  9| -2| 13| 12|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }  
  @Test public void set_inbounds_2D_constructor2(){
    Integer mat [][] = {
      { 10,  2,  3,  0,  0,  },
      {  4,  0,  5,  6,  0,  },
      {  0,  4,  5,  0,  6,  },
      {  4,  0,  6,  0,  0,  },
      {  0,  0,  0,  9,  0,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {+0,+4,-1},
      {+4,+0,-2},
      {+4,+1,-3},
      {+2,+3,-4},
      {+5,+4,-5},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+\n"+
"  0 | 10|  2|  3|  0| -1|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  4|  0|  5|  6|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  4|  5| -4|  6|\n"+
"    +---+---+---+---+---+\n"+
"  3 |  4|  0|  6|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  4 | -2| -3|  0|  9|  0|\n"+
"    +---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0| -5|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }  

  // Should raise exceptions by trying to set elements with a non-fill
  // element even if created with the 2D array constructor
  @Test public void set_inbounds_2D_constructor_exception1(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      { 10,  2,  3,  0,  0,  3,  8,  },
      {  4,  0,  5,  6,  0,  3,  8,  },
      {  0,  0,  0,  9,  0, 13, 12,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
    };
    applySets(sets,b);
    String expectS =
      "    |  0|  1|  2|  3|  4|  5|  6|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  0 | 10|  2|  3|  0|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  1 |  4|  0|  5|  6|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  2 |  0|  0|  0|  9|  0| 13| 12|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    boolean thrown = false;
    String expectMsg = "Element 1 2 already set to 5";
    try {
      b.set(+1,+2,-100);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }  
  @Test public void set_inbounds_2D_constructor_exception2(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      { 10,  2,  3,  0,  0,  3,  8,  },
      {  4,  0,  5,  6,  0,  3,  8,  },
      {  0,  0,  0,  9,  0, 13, 12,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {+2,+1,-1},
      {+2,+4,-2},
      {+1,+4,-3},
      {+2,+2,-4},
    };
    applySets(sets,b);
    String expectS =
      "    |  0|  1|  2|  3|  4|  5|  6|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  0 | 10|  2|  3|  0|  0|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  1 |  4|  0|  5|  6| -3|  3|  8|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "  2 |  0| -1| -4|  9| -2| 13| 12|\n"+
      "    +---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    boolean thrown = false;
    String expectMsg = "Element 1 4 already set to -3";
    try {
      b.set(+1,+4,-100);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }  

  // Set to null throws exception
  @Test public void set_null1(){
    int rowMin=-7, rowMax=-2, colMin=-3, colMax=+7;
    int fill = 999;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4,+3,+10},
      {-2,+4,-20},
      {-6,+7,+30},
      {-4,+0,-40},
      {-7,+0,+50},
      {-7,-2,+60},
      {-6,-1,-70},
    };
    applySets(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 |999| 60|999| 50|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 |999|999|-70|999|999|999|999|999|999|999| 30|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |999|999|999|999|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |999|999|999|-40|999|999| 10|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |999|999|999|999|999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |999|999|999|999|999|999|999|-20|999|999|999|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    boolean thrown = false;
    String expectMsg = "Cannot set elements to null";
    try {
      b.set(-2,-3,null);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }
  @Test public void set_null2(){
    int fill = -1;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  0 | -1|\n"+
"    +---+\n"+
      "";
    checkBoard(b,expectS,fill);
    boolean thrown = false;
    String expectMsg = "Cannot set elements to null";
    try {
      b.set(0,0,null);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }

  // Setting in simple constructed board is the same as previous test
  @Test public void set_inbounds_simpleConstructor1(){
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][] = {
      {0,0,10},
    };
    applySets(sets,b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  0 | 10|\n"+
"    +---+\n"+
      "";
    checkBoard(b,expectS,fill);

    boolean thrown = false;
    String expectMsg = "Element 0 0 already set to 10";
    try {
      b.set(0,0,20);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }

  // Set to fill element does not change elements
  @Test public void set_inbounds_fill1(){
    int rowMin=-3, rowMax=2, colMin=0, colMax=4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-3,+2,fill},
      {-2,+4,fill},
      {+2,+0,fill},
      {+1,+3,fill},
      {-3,+0,fill},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void set_inbounds_fill2(){
    int rowMin=+3, rowMax=+9, colMin=+2, colMax=6;
    String fill = "??";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    b.set(4,4,fill);
    b.set(3,5,fill);
    b.set(9,6,fill);
    String expectS =
"    |  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+\n"+
"  3 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  4 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  5 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  6 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  7 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  8 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  9 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void set_inbounds_fill3(){
    int rowMin=+3, rowMax=+9, colMin=+2, colMax=6;
    String fill = "??";
    DenseBoard<String> b = new DenseBoard<String>(rowMin,rowMax,colMin,colMax,fill);
    b.set(4,4,fill);
    b.set(3,5,fill);
    b.set(9,6,fill);
    String expectS =
"    |  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+\n"+
"  3 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  4 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  5 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  6 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  7 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  8 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
"  9 | ??| ??| ??| ??| ??|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    fill = ".";
    b.setFillElem(fill);
    expectS = 
"    |  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+\n"+
"  3 |  .|  .|  .|  .|  .|\n"+
"    +---+---+---+---+---+\n"+
"  4 |  .|  .|  .|  .|  .|\n"+
"    +---+---+---+---+---+\n"+
"  5 |  .|  .|  .|  .|  .|\n"+
"    +---+---+---+---+---+\n"+
"  6 |  .|  .|  .|  .|  .|\n"+
"    +---+---+---+---+---+\n"+
"  7 |  .|  .|  .|  .|  .|\n"+
"    +---+---+---+---+---+\n"+
"  8 |  .|  .|  .|  .|  .|\n"+
"    +---+---+---+---+---+\n"+
"  9 |  .|  .|  .|  .|  .|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }

  // Test to ensure actual deep equality is used
  @Test public void set_inbounds_deep_equals(){
    int rowMin=+3, rowMax=+9, colMin=+2, colMax=6;
    String fill = new String("0");
    DenseBoard<Object> b = new DenseBoard<Object>(rowMin,rowMax,colMin,colMax,fill);
    Integer zero = new Integer(0);
    b.set(4,4,zero);
    String expectS =
"    |  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  6 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  7 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  8 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  9 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    Integer elem = (Integer) b.get(4,4);
    assertEquals("Retrieved element does not match expected",zero,elem);
    fill = "~~";
    b.setFillElem(fill);
    expectS =
"    |  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+\n"+
"  3 | ~~| ~~| ~~| ~~| ~~|\n"+
"    +---+---+---+---+---+\n"+
"  4 | ~~| ~~|  0| ~~| ~~|\n"+
"    +---+---+---+---+---+\n"+
"  5 | ~~| ~~| ~~| ~~| ~~|\n"+
"    +---+---+---+---+---+\n"+
"  6 | ~~| ~~| ~~| ~~| ~~|\n"+
"    +---+---+---+---+---+\n"+
"  7 | ~~| ~~| ~~| ~~| ~~|\n"+
"    +---+---+---+---+---+\n"+
"  8 | ~~| ~~| ~~| ~~| ~~|\n"+
"    +---+---+---+---+---+\n"+
"  9 | ~~| ~~| ~~| ~~| ~~|\n"+
"    +---+---+---+---+---+\n"+
      "";  
    checkBoard(b,expectS,fill);
  }

  // SET TESTS WHICH EXPAND

  // 1 directional expansions
  @Test public void set_expand_one_direction1(){
    Integer fill = -11;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][] = {
      {+1,+0,100},
    };
    applySets(sets,b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  0 |-11|\n"+
"    +---+\n"+
"  1 |100|\n"+
"    +---+\n"+
      "";
    int eRowMin=0, eRowMax=1, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction2(){
    Integer fill = -11;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][] = {
      {+4,+0,100},
    };
    applySets(sets,b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  0 |-11|\n"+
"    +---+\n"+
"  1 |-11|\n"+
"    +---+\n"+
"  2 |-11|\n"+
"    +---+\n"+
"  3 |-11|\n"+
"    +---+\n"+
"  4 |100|\n"+
"    +---+\n"+
      "";
    int eRowMin=0, eRowMax=4, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction3(){
    Integer fill = -11;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][] = {
      {+0,+1,100},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|\n"+
"    +---+---+\n"+
"  0 |-11|100|\n"+
"    +---+---+\n"+
      "";
    int eRowMin=0, eRowMax=0, eColMin=0, eColMax=1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction4(){
    Integer fill = -11;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][] = {
      {+0,+8,100},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|  5|  6|  7|  8|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  0 |-11|-11|-11|-11|-11|-11|-11|-11|100|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=0, eRowMax=0, eColMin=0, eColMax=8;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction5(){
    int rowMin=-3, rowMax=-3, colMin=+2, colMax=+2;
    Integer fill = -11;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-2,+2,100},
    };
    applySets(sets,b);
    String expectS =
"    |  2|\n"+
"    +---+\n"+
" -3 |-11|\n"+
"    +---+\n"+
" -2 |100|\n"+
"    +---+\n"+
      "";
    int eRowMin=-3, eRowMax=-2, eColMin=2, eColMax=2;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction6(){
    int rowMin=4, rowMax=4, colMin=-5, colMax=-5;
    Integer fill = -11;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {10,-5,100},
    };
    applySets(sets,b);
    String expectS =
"    | -5|\n"+
"    +---+\n"+
"  4 |-11|\n"+
"    +---+\n"+
"  5 |-11|\n"+
"    +---+\n"+
"  6 |-11|\n"+
"    +---+\n"+
"  7 |-11|\n"+
"    +---+\n"+
"  8 |-11|\n"+
"    +---+\n"+
"  9 |-11|\n"+
"    +---+\n"+
" 10 |100|\n"+
"    +---+\n"+
      "";
    int eRowMin=4, eRowMax=10, eColMin=-5, eColMax=-5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction7(){
    int rowMin=4, rowMax=7, colMin=-5, colMax=-4;
    Integer fill = -11;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,-3,100},
    };
    applySets(sets,b);
    String expectS =
"    | -5| -4| -3|\n"+
"    +---+---+---+\n"+
"  4 |-11|-11|100|\n"+
"    +---+---+---+\n"+
"  5 |-11|-11|-11|\n"+
"    +---+---+---+\n"+
"  6 |-11|-11|-11|\n"+
"    +---+---+---+\n"+
"  7 |-11|-11|-11|\n"+
"    +---+---+---+\n"+
      "";
    int eRowMin=4, eRowMax=7, eColMin=-5, eColMax=-3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction8(){
    int rowMin=-1, rowMax=1, colMin=-2, colMax=-1;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+1,+4,111},
    };
    applySets(sets,b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|111|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=1, eColMin=-2, eColMax=+4;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction9(){
    int rowMin=-2, rowMax=1, colMin=-2, colMax=3;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+5,+2,111},
    };
    applySets(sets,b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|111|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-2, eRowMax=5, eColMin=-2, eColMax=+3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction10(){
    int rowMin=-2, rowMax=1, colMin=-2, colMax=0;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+3,+0,111},
      {+2,+5,222},
      {+3,+8,333},
      {+6,+0,444},
    };
    applySets(sets,b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|  8|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|222|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|111|  0|  0|  0|  0|  0|  0|  0|333|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  6 |  0|  0|444|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-2, eRowMax=6, eColMin=-2, eColMax=8;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction11(){
    int rowMin=0, rowMax=0, colMin=0, colMax=0;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,0,111},
    };
    applySets(sets,b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
" -1 |111|\n"+
"    +---+\n"+
"  0 |  0|\n"+
"    +---+\n"+
      "";
    int eRowMin=-1, eRowMax=0, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction12(){
    int rowMin=+2, rowMax=+2, colMin=+5, colMax=+5;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,5,111},
    };
    applySets(sets,b);
    String expectS =
"    |  5|\n"+
"    +---+\n"+
" -1 |111|\n"+
"    +---+\n"+
"  0 |  0|\n"+
"    +---+\n"+
"  1 |  0|\n"+
"    +---+\n"+
"  2 |  0|\n"+
"    +---+\n"+
      "";
    int eRowMin=-1, eRowMax=2, eColMin=5, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction13(){
    int rowMin=-3, rowMax=+1, colMin=0, colMax=+4;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-5,2,111},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+\n"+
" -5 |  0|  0|111|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    int eRowMin=-5, eRowMax=1, eColMin=0, eColMax=4;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction14(){
    int rowMin=+1, rowMax=+1, colMin=+2, colMax=+3;
    Integer fill = -1;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+1,+1,11},
    };
    applySets(sets,b);
    String expectS =
"    |  1|  2|  3|\n"+
"    +---+---+---+\n"+
"  1 | 11| -1| -1|\n"+
"    +---+---+---+\n"+
      "";
    int eRowMin=1, eRowMax=1, eColMin=1, eColMax=3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction15(){
    int rowMin=-1, rowMax=+1, colMin=+2, colMax=+3;
    Integer fill = -1;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,-4,11},
    };
    applySets(sets,b);
    String expectS =
"    | -4| -3| -2| -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 | -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 | 11| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 | -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=1, eColMin=-4, eColMax=3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_one_direction16(){
    int rowMin=-1, rowMax=+1, colMin=+2, colMax=+3;
    Integer fill = -1;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0,11},
      {-3,+0,22},
      {+1,-4,33},
      {+0,+1,44},
      {-5,+0,55},
      {+1,+1,66},
    };
    applySets(sets,b);
    String expectS =
"    | -4| -3| -2| -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -5 | -1| -1| -1| -1| 55| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -4 | -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -3 | -1| -1| -1| -1| 22| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -2 | -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 | -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 | -1| -1| -1| -1| 11| 44| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 | 33| -1| -1| -1| -1| 66| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-5, eRowMax=1, eColMin=-4, eColMax=3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  // Mixture of expansion in all 4 directions, one direction at a time
  @Test public void set_expand_one_direction17(){
    int rowMin=-1, rowMax=+1, colMin=+2, colMax=+3;
    Integer fill = -1;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0,11},
      {-3,+0,22},
      {+1,-4,33},
      {+1,+5,44},
      {+5,+1,55},
      {+0,+1,66},
      {-5,+0,77},
      {+1,-5,88},
      {-7,+4,99},
    };
    applySets(sets,b);
    String expectS =
"    | -5| -4| -3| -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 | -1| -1| -1| -1| -1| -1| -1| -1| -1| 99| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 | -1| -1| -1| -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 | -1| -1| -1| -1| -1| 77| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 | -1| -1| -1| -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 | -1| -1| -1| -1| -1| 22| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 | -1| -1| -1| -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 | -1| -1| -1| -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 | -1| -1| -1| -1| -1| 11| 66| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 | 88| 33| -1| -1| -1| -1| -1| -1| -1| -1| 44|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 | -1| -1| -1| -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 | -1| -1| -1| -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 | -1| -1| -1| -1| -1| -1| -1| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  5 | -1| -1| -1| -1| -1| -1| 55| -1| -1| -1| -1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-7, eRowMax=5, eColMin=-5, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }

  // Expand in two directions with one move

  // Down/right
  @Test public void set_expand_two_directions1(){
    // int rowMin=-1, rowMax=+1, colMin=+2, colMax=+3;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][] = {
      {+1,+1,11},
    };
    applySets(sets,b);
    String expectS =
"    |  0|  1|\n"+
"    +---+---+\n"+
"  0 |  0|  0|\n"+
"    +---+---+\n"+
"  1 |  0| 11|\n"+
"    +---+---+\n"+
      "";
    int eRowMin=0, eRowMax=1, eColMin=0, eColMax=1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions2(){
    int rowMin=+1, rowMax=+1, colMin=-2, colMax=-2;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,0,11},
    };
    applySets(sets,b);
    String expectS =
"    | -2| -1|  0|\n"+
"    +---+---+---+\n"+
"  1 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  2 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  3 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  4 |  0|  0| 11|\n"+
"    +---+---+---+\n"+
      "";
    int eRowMin=1, eRowMax=4, eColMin=-2, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions3(){
    int rowMin=-2, rowMax=+1, colMin=-2, colMax=+2;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+8,11},
    };
    applySets(sets,b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|  8|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0| 11|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-2, eRowMax=4, eColMin=-2, eColMax=8;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  // Up/left
  @Test public void set_expand_two_directions4(){
    int rowMin=-2, rowMax=+1, colMin=-2, colMax=+2;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4,-3,11},
    };
    applySets(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|\n"+
"    +---+---+---+---+---+---+\n"+
" -4 | 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-4, eRowMax=1, eColMin=-3, eColMax=2;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions5(){
    int rowMin=+2, rowMax=+5, colMin=-2, colMax=-1;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4,-6,11},
    };
    applySets(sets,b);
    String expectS =
"    | -6| -5| -4| -3| -2| -1|\n"+
"    +---+---+---+---+---+---+\n"+
" -4 | 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-4, eRowMax=5, eColMin=-6, eColMax=-1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  // Down/left
  @Test public void set_expand_two_directions6(){
    int rowMin=+2, rowMax=+5, colMin=-2, colMax=-1;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+7,-3,11},
    };
    applySets(sets,b);
    String expectS =
"    | -3| -2| -1|\n"+
"    +---+---+---+\n"+
"  2 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  3 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  4 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  5 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  6 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  7 | 11|  0|  0|\n"+
"    +---+---+---+\n"+
      "";
    int eRowMin=+2, eRowMax=+7, eColMin=-3, eColMax=-1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions7(){
    int rowMin=-2, rowMax=+3, colMin=-1, colMax=+1;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+7,-4,11},
    };
    applySets(sets,b);
    String expectS =
"    | -4| -3| -2| -1|  0|  1|\n"+
"    +---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  6 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  7 | 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-2, eRowMax=+7, eColMin=-4, eColMax=+1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  // Up/right
  @Test public void set_expand_two_directions8(){
    int rowMin=-1, rowMax=0, colMin=+4, colMax=+6;
    Integer fill = 1;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4,+7,22},
    };
    applySets(sets,b);
    String expectS =
"    |  4|  5|  6|  7|\n"+
"    +---+---+---+---+\n"+
" -4 |  1|  1|  1| 22|\n"+
"    +---+---+---+---+\n"+
" -3 |  1|  1|  1|  1|\n"+
"    +---+---+---+---+\n"+
" -2 |  1|  1|  1|  1|\n"+
"    +---+---+---+---+\n"+
" -1 |  1|  1|  1|  1|\n"+
"    +---+---+---+---+\n"+
"  0 |  1|  1|  1|  1|\n"+
"    +---+---+---+---+\n"+
      "";
    int eRowMin=-4, eRowMax=0, eColMin=4, eColMax=7;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions9(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    Integer fill = 1;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-8,+8,22},
    };
    applySets(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|  8|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -8 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1| 22|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|  1|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-8, eRowMax=+2, eColMin=-3, eColMax=8;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  // Several set/expands in a row expanding in one and two directions
  @Test public void set_expand_two_directions10(){
    int rowMin=0, rowMax=0, colMin=0, colMax=0;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-2,+3,11},
      {+2,+5,22},
      {+4,-2,33},
      {-5,+0,44},
      {+4,+7,55},
    };
    applySets(sets,b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |  0|  0| 44|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0| 11|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0| 22|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  4 | 33|  0|  0|  0|  0|  0|  0|  0|  0| 55|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-5, eRowMax=+4, eColMin=-2, eColMax=7;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions11(){
    int rowMin=-1, rowMax=0, colMin=+3, colMax=+5;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,+3,11},
      {-4,+4,22},
      {+3,-0,33},
      {+0,+0,44},
      {+0,-2,55},
      {+5,-4,66},
      {-6,+7,77},
      {-8,-8,88},
    };
    applySets(sets,b);
    String expectS =
"    | -8| -7| -6| -5| -4| -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -8 | 88|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0| 77|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0| 22|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0| 11|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0| 55|  0| 44|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0| 33|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0| 66|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-8, eRowMax=+5, eColMin=-8, eColMax=7;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions12(){
    int rowMin=-1, rowMax=0, colMin=+3, colMax=+5;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-2,+6,11},
      {-4,+8,22},
      {-5,+2,33},
      {+1,+10,44},
      {+3,+11,55},
      {+5,+0,66},
      {+7,-2,77},
    };
    applySets(sets,b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|  8|  9| 10| 11|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |  0|  0|  0|  0| 33|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0| 22|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0| 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0| 44|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0| 55|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0| 66|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  7 | 77|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-5, eRowMax=+7, eColMin=-2, eColMax=11;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_expand_two_directions13(){
    // int rowMin=-1, rowMax=0, colMin=+3, colMax=+5;
    Integer mat [][] = {
      {  1,  2, },
      {  0,  0, },
      {  0,  3, },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {-2,+2,11},
      {+4,-1,22},
      {+5,+4,33},
      {-3,-4,44},
      {-1,-1,55},
      {+2,+0,66},
    };
    applySets(sets,b);
    String expectS =
"    | -4| -3| -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
" -3 | 44|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0| 11|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0| 55|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  1|  2|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0| 66|  3|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0| 22|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|  0|  0| 33|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=+5, eColMin=-4, eColMax=4;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }

  // Set to fill element does not change size
  @Test public void set_no_expand1(){
    int rowMin=0, rowMax=0, colMin=0, colMax=0;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-8,+8, fill},
    };
    applySets(sets,b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  0 |  0|\n"+
"    +---+\n"+
      "";
    int eRowMin=0, eRowMax=0, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_no_expand2(){
    int rowMin=-3, rowMax=+2, colMin=+3, colMax=+5;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+3,+6, fill},
    };
    applySets(sets,b);
    String expectS =
"    |  3|  4|  5|\n"+
"    +---+---+---+\n"+
" -3 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
" -2 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
" -1 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  0 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  1 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  2 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=2, eColMin=3, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void set_no_expand3(){
    int rowMin=-3, rowMax=+2, colMin=+3, colMax=+5;
    Integer fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+3,+6, fill},
      {-2,+1, fill},
      {-8,+8, fill},
      {-0,+0, fill},
    };
    applySets(sets,b);
    String expectS =
"    |  3|  4|  5|\n"+
"    +---+---+---+\n"+
" -3 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
" -2 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
" -1 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  0 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  1 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  2 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=2, eColMin=3, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }


  // Tests of board independence: multiple can exist at once
  @Test public void board_independence1(){
    int fill = 0;
    String expectB;
    DenseBoard<Integer> b1 = new DenseBoard<Integer>(fill);
    DenseBoard<Integer> b2 = new DenseBoard<Integer>(fill);
    int sets[][];

    sets = new int[][]{
      {2,2, 11},
      {-1,0,22},
      {-1,2,33},
      {1,0,44},
    };
    applySets(sets, b1);
    applySets(sets, b2);

    expectB =
"    |  0|  1|  2|\n"+
"    +---+---+---+\n"+
" -1 | 22|  0| 33|\n"+
"    +---+---+---+\n"+
"  0 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  1 | 44|  0|  0|\n"+
"    +---+---+---+\n"+
"  2 |  0|  0| 11|\n"+
"    +---+---+---+\n"+
      "";
    checkBoard(b1,expectB,fill);
    checkBoard(b2,expectB,fill);

    sets = new int[][]{
      {3,5, 55},
    };
    applySets(sets, b1);
    checkBoard(b2,expectB,fill);

    expectB =
"    |  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+\n"+
" -1 | 22|  0| 33|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 | 44|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0| 11|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0| 55|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    checkBoard(b1,expectB,fill);
  }
  @Test public void board_independence2(){
    int fill = 0;
    String expectB;
    DenseBoard<Integer> b1 = new DenseBoard<Integer>(fill);
    DenseBoard<Integer> b2 = new DenseBoard<Integer>(fill);
    int sets[][];

    sets = new int[][]{
      {2,2, 11},
      {-1,0,22},
      {-1,2,33},
      {1,0,44},
    };
    applySets(sets, b1);
    applySets(sets, b2);

    expectB =
"    |  0|  1|  2|\n"+
"    +---+---+---+\n"+
" -1 | 22|  0| 33|\n"+
"    +---+---+---+\n"+
"  0 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  1 | 44|  0|  0|\n"+
"    +---+---+---+\n"+
"  2 |  0|  0| 11|\n"+
"    +---+---+---+\n"+
      "";
    checkBoard(b1,expectB,fill);
    checkBoard(b2,expectB,fill);

    b1.setFillElem(999);
    checkBoard(b2,expectB,fill);
    fill = 999;

    expectB =
"    |  0|  1|  2|\n"+
"    +---+---+---+\n"+
" -1 | 22|999| 33|\n"+
"    +---+---+---+\n"+
"  0 |999|999|999|\n"+
"    +---+---+---+\n"+
"  1 | 44|999|999|\n"+
"    +---+---+---+\n"+
"  2 |999|999| 11|\n"+
"    +---+---+---+\n"+
      "";
    checkBoard(b1,expectB,fill);
  }
    


  // EXPAND TO INCLUDE TESTS
  // No expansion
  @Test public void expand_none1(){
    int rowMin=-3, rowMax=2, colMin=0, colMax=4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+1,+0,0},
      {-2,+2,0},
    };
    checkExpands(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=+2, eColMin=0, eColMax=4;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void expand_none2(){
    int rowMin=0, rowMax=2, colMin=0, colMax=1;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0,0},
      {2,1,0},
    };
    checkExpands(sets,b);
    String expectS =
"    |  0|  1|\n"+
"    +---+---+\n"+
"  0 |  0|  0|\n"+
"    +---+---+\n"+
"  1 |  0|  0|\n"+
"    +---+---+\n"+
"  2 |  0|  0|\n"+
"    +---+---+\n"+
      "";
    int eRowMin=0, eRowMax=+2, eColMin=0, eColMax=1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void expand_none3(){
    int rowMin=-1, rowMax=4, colMin=-3, colMax=+3;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0,0},
      {2,1,0},
      {-1,+3,0},
      {4,3,0},
      {4,-3,0},
      {-1,-3,0},
    };
    checkExpands(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=+4, eColMin=-3, eColMax=3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void expand_none4(){
    int rowMin=0, rowMax=2, colMin=0, colMax=6;
    Integer mat [][] = {
      { 10,  2,  3,  0,  0,  3,  8,  },
      {  4,  0,  5,  6,  0,  3,  8,  },
      {  0,  0,  0,  9,  0, 13, 12,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {0,0,0},
      {2,1,0},
      {+1,+6,0},
    };
    checkExpands(sets,b);
    String expectS =
"    |  0|  1|  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 | 10|  2|  3|  0|  0|  3|  8|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  4|  0|  5|  6|  0|  3|  8|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  9|  0| 13| 12|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=0, eRowMax=+2, eColMin=0, eColMax=6;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }

  // Expand some
  @Test public void expand_some1(){
    int rowMin=0, rowMax=0, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,0,1},
      {0,1,2},
    };
    checkExpands(sets,b);
    String expectS =
"    |  0|  1|\n"+
"    +---+---+\n"+
"  0 |  0|  0|\n"+
"    +---+---+\n"+
"  1 |  0|  0|\n"+
"    +---+---+\n"+
      "";
    int eRowMin=0, eRowMax=+1, eColMin=-0, eColMax=1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void expand_some2(){
    int rowMin=-1, rowMax=0, colMin=-2, colMax=+1;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,0,4},
      {-2,-3,8},
    };
    checkExpands(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|\n"+
"    +---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    int eRowMin=-2, eRowMax=+1, eColMin=-3, eColMax=1;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void expand_some3(){
    int rowMin=-2, rowMax=+1, colMin=-1, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,0,0},
      {0,-3,8},
      {+2,+7,23},
    };
    checkExpands(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|  7|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-2, eRowMax=+2, eColMin=-3, eColMax=7;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void expand_some4(){
    int rowMin=-1, rowMax=+1, colMin=-1, colMax=+1;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+2,+3,11},
      {-3,-3,22},
      {+3,-5,21},
      {-5,+6,45},
    };
    checkExpands(sets,b);
    String expectS =
"    | -5| -4| -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-5, eRowMax=+3, eColMin=-5, eColMax=6;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void expand_some5(){
    Integer mat [][] = {
      { 10,  2,  3,  0,  0,  3,  8,  },
      {  4,  0,  5,  6,  0,  3,  8,  },
      {  0,  0,  0,  9,  0, 13, 12,  },
    };
    Integer fill = new Integer(0);
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {-1,+0,7},
      {-2,-1,12},
      {+5,-3,40},
    };
    checkExpands(sets,b);
    String expectS =
"    | -3| -2| -1|  0|  1|  2|  3|  4|  5|  6|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0| 10|  2|  3|  0|  0|  3|  8|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  4|  0|  5|  6|  0|  3|  8|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  9|  0| 13| 12|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-2, eRowMax=+5, eColMin=-3, eColMax=6;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }

  // LONGEST SEQUENCE Tests
  // Comparator to sort elements of longest sequence lists
  class RowMajor<T> implements Comparator<RowColElem<T>>{
    public int compare(RowColElem<T> x, RowColElem<T> y){
      int diff = x.getRow()-y.getRow();
      if(diff != 0){ return diff; }
      return x.getCol()-y.getCol();
    }
  }
  Comparator<RowColElem<Integer>> cmpI = new RowMajor<Integer>();

  // Test whether 2D array constructor produces proper longest sequence
  @Test public void longest_seq_2d_constructor1(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      {  0,  2,  2,  0,  0,  3,  8,  },
      {  4,  0,  5,  5,  5,  3,  8,  },
      {  0,  0,  0,  9,  0, 13, 12,  },
    };
    String expect = "[(1,2,5), (1,3,5), (1,4,5)]";
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    checkLongestSequence(b,expect);
  }
  @Test public void longest_seq_2d_constructor2(){
    Integer fill = new Integer(-1);
    Integer mat [][] = {
      { -1,  2,  2,  9, -1,  3,  8,  },
      {  4, -1,  5,  9,  5, 13,  8,  },
      { -1, -1, -1,  9, -1, 13, 12,  },
      { -1, -1, -1,  9, -1, 13, 12,  },
    };
    String expect = "[(0,3,9), (1,3,9), (2,3,9), (3,3,9)]";
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    checkLongestSequence(b,expect);
  }
  @Test public void longest_seq_2d_constructor4(){
    Integer fill = new Integer(-1);
    Integer mat [][] = {
      { -1,  2,  2,  7, -1,  3,  8,  },
      {  4, -1,  7,  9,  5, 13,  8,  },
      { -1,  7, -1,  9, -1, 13, 12,  },
      {  7, -1, -1,  9, -1, 13, 12,  },
    };
    String expect = "[(0,3,7), (1,2,7), (2,1,7), (3,0,7)]";
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    checkLongestSequence(b,expect);
  }
  @Test public void longest_seq_2d_constructor5(){
    Integer fill = new Integer(-1);
    Integer mat [][] = {
      { -1,  2,  2,  7, -1,  3,  8,  },
      {  4, -1,  7,  2,  5, 13,  8,  },
      { -1,  7, -1,  9,  2, 13, 12,  },
      { -1, -1, -1,  9, -1,  2, 12,  },
      { -1, -1, -1,  9, -1, 13,  2,  },
    };
    String expect = "[(0,2,2), (1,3,2), (2,4,2), (3,5,2), (4,6,2)]";
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    checkLongestSequence(b,expect);
  }

  // Fill-only board should produce empty list for longest sequence
  @Test public void longest_empty1(){
    Integer fill = new Integer(-1);
    Integer mat [][] = {
      { -1, -1, -1, },
      { -1, -1, -1, },
      { -1, -1, -1, },
      { -1, -1, -1, },
      { -1, -1, -1, },
    };
    String expect = "[]";
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    checkLongestSequence(b,expect);
  }
  @Test public void longest_empty2(){
    Integer fill = new Integer(-1);
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    String expect = "[]";
    checkLongestSequence(b,expect);
  }
  @Test public void longest_empty3(){
    int rowMin=-1, rowMax=+1, colMin=+1, colMax=+5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    String expect = "[]";
    checkLongestSequence(b,expect);
  }

  // Set + longest sequence determination

  // Single element longest sequences
  @Test public void set_longest_sequence_single_01(){
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][] = {
      {-0,+0, 1},
    };
    applySets(sets,b);
    String expect = "[(0,0,1)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_single_02(){
    int rowMin=0, rowMax=0, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-0,+0, 1},
    };
    applySets(sets,b);
    String expect = "[(0,0,1)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_single_03(){
    int rowMin=-1, rowMax=-1, colMin=-1, colMax=-1;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-0,+0, 11},
    };
    applySets(sets,b);
    String expect = "[(0,0,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_single_04(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,+1, 11},
    };
    applySets(sets,b);
    String expect = "[(-1,1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_single_05(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,1, 11},
    };
    applySets(sets,b);
    String expect = "[(-1,1,11)]";
    checkLongestSequence(b,expect);
  }
  // Setting another element should not change
  @Test public void set_longest_sequence_single_06(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,1, 11},
      {+1,2, 11},
    };
    applySets(sets,b);
    String expect = "[(-1,1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_single_07(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,1, 11},
      {+1,2, 11},
      {-1,-2, 11},
    };
    applySets(sets,b);
    String expect = "[(-1,1,11)]";
    checkLongestSequence(b,expect);
  }

  // North-South Sequences
  @Test public void set_longest_sequence_NS_01(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+1,+0, 11},
    };
    applySets(sets,b);
    String expect = "[(0,0,11), (1,0,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NS_02(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+1,+0, 11},
      {+2,+0, 11},
    };
    applySets(sets,b);
    String expect = "[(0,0,11), (1,0,11), (2,0,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NS_03(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+1,+0, 11},
      {+2,+0, 11},
      {-1,+0, 11},
    };applySets(sets,b);
    String expect = "[(-1,0,11), (0,0,11), (1,0,11), (2,0,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NS_04(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+2,+0, 11},
      {-1,+0, 11},
      {+1,+0, 11},
    };
    applySets(sets,b);
    String expect = "[(-1,0,11), (0,0,11), (1,0,11), (2,0,11)]";
    checkLongestSequence(b,expect);
  }

  // West-East Horizontal sequences
  @Test public void set_longest_sequence_WE_01(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+0,+1, 11},
    };
    applySets(sets,b);
    String expect = "[(0,0,11), (0,1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_WE_02(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+0,-1, 11},
    };
    applySets(sets,b);
    String expect = "[(0,-1,11), (0,0,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_WE_03(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,-1, 11},
      {+0,+0, 11},
      {+0,+1, 11},
      {+0,+2, 11},
    };
    applySets(sets,b);
    String expect = "[(0,-1,11), (0,0,11), (0,1,11), (0,2,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_WE_04(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+0,+2, 11},
      {+0,-1, 11},
      {+0,+1, 11},
    };
    applySets(sets,b);
    String expect = "[(0,-1,11), (0,0,11), (0,1,11), (0,2,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_WE_05(){
    int rowMin=-1, rowMax=+1, colMin=+3, colMax=+4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {+0,+2, 11},
      {+0,-1, 11},
      {+0,-2, 11},
      {+0,+1, 11},
    };
    applySets(sets,b);
    String expect = "[(0,-2,11), (0,-1,11), (0,0,11), (0,1,11), (0,2,11)]";
    checkLongestSequence(b,expect);
  }
  
  // South-West/North-East sequences
  @Test public void set_longest_sequence_SWNE_01(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+8,+4, 11},
      {+7,+5, 11},
    };
    applySets(sets,b);
    String expect = "[(7,5,11), (8,4,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_SWNE_02(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+8,+4, 11},
      {+7,+5, 11},
      {+6,+6, 11},
    };
    applySets(sets,b);
    String expect = "[(6,6,11), (7,5,11), (8,4,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_SWNE_03(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+8,+4, 11},
      {+6,+6, 11},
      {+7,+5, 11},
    };
    applySets(sets,b);
    String expect = "[(6,6,11), (7,5,11), (8,4,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_SWNE_04(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+7,+5, 11},
      {+8,+4, 11},
      {+6,+6, 11},
      {+9,+3, 11},
    };
    applySets(sets,b);
    String expect = "[(6,6,11), (7,5,11), (8,4,11), (9,3,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_SWNE_05(){
    int rowMin=-3, rowMax=-3, colMin=-6, colMax=-4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-5,-7, 11},
      {-4,-8, 11},
      {-6,-6, 11},
      {-3,-9, 11},
    };
    applySets(sets,b);
    String expect = "[(-6,-6,11), (-5,-7,11), (-4,-8,11), (-3,-9,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_SWNE_09(){
    int rowMin=-3, rowMax=-3, colMin=-6, colMax=-4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4,-8, 11},
      {-6,-6, 11},
      {-3,-9, 11},
      {-5,-7, 11},
    };
    applySets(sets,b);
    String expect = "[(-6,-6,11), (-5,-7,11), (-4,-8,11), (-3,-9,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_SWNE_10(){
    int rowMin=-3, rowMax=-3, colMin=-6, colMax=-4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-4+5,-8+8, 11},
      {-6+5,-6+8, 11},
      {-3+5,-9+8, 11},
      {-5+5,-7+8, 11},
    };
    applySets(sets,b);
    String expect = "[(-1,2,11), (0,1,11), (1,0,11), (2,-1,11)]";
    checkLongestSequence(b,expect);
  }

  // North-West/South-East sequences
  @Test public void set_longest_sequence_NWSE_01(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+4, 11},
      {+5,+5, 11},
    };
    applySets(sets,b);
    String expect = "[(4,4,11), (5,5,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NWSE_02(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+4, 11},
      {+3,+3, 11},
      {+6,+6, 11},
      {+5,+5, 11},
    };
    applySets(sets,b);
    String expect = "[(3,3,11), (4,4,11), (5,5,11), (6,6,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NWSE_03(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+4, 11},
      {+3,+3, 11},
      {+6,+6, 11},
      {+5,+5, 11},
    };
    applySets(sets,b);
    String expect = "[(3,3,11), (4,4,11), (5,5,11), (6,6,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NWSE_04(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+4, 11},
      {+3,+3, 11},
      {+6,+6, 11},
      {+5,+5, 11},
      {+2,+2, 11},
    };
    applySets(sets,b);
    String expect = "[(2,2,11), (3,3,11), (4,4,11), (5,5,11), (6,6,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NWSE_05(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4+4,+4, 11},
      {+3+4,+3, 11},
      {+6+4,+6, 11},
      {+5+4,+5, 11},
    };
    applySets(sets,b);
    String expect = "[(7,3,11), (8,4,11), (9,5,11), (10,6,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NWSE_06(){
    int rowMin=+3, rowMax=+8, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4+2,+4-9, 11},
      {+3+2,+3-9, 11},
      {+6+2,+6-9, 11},
      {+5+2,+5-9, 11},
    };
    applySets(sets,b);
    String expect = "[(5,-6,11), (6,-5,11), (7,-4,11), (8,-3,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NWSE_07(){
    int rowMin=+3-5, rowMax=+8-5, colMin=3, colMax=8;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4-10,+4, 11},
      {+3-10,+3, 11},
      {+6-10,+6, 11},
      {+5-10,+5, 11},
      {+7-10,+7, 11},
    };
    applySets(sets,b);
    String expect = "[(-7,3,11), (-6,4,11), (-5,5,11), (-4,6,11), (-3,7,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_NWSE_08(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {1,1, 11},
      {3,3, 11},
      {2,2, 11},
    };
    applySets(sets,b);
    String expect = "[(0,0,11), (1,1,11), (2,2,11), (3,3,11)]";
    checkLongestSequence(b,expect);
  }
  
  // Multiple sets, longest sequence changes
  @Test public void set_longest_sequence_changes_01(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,0, 22},
      {-1,1, 22},
      {-1,2, 22},
      {0,0, 11},
      {1,1, 11},
      {3,3, 11},
      {2,2, 11},
    };
    applySets(sets,b);
    String expect = "[(0,0,11), (1,1,11), (2,2,11), (3,3,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_changes_02(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,+0, 22},
      {-1,+1, 22},
      {+1,+1, 11},
      {+3,+3, 11},
      {-1,+2, 22},
      {+2,+2, 11},
      {+4,+4, 11},
      {-1,-1, 22},
      {+0,+0, 11},
    };
    applySets(sets,b);
    String expect = "[(0,0,11), (1,1,11), (2,2,11), (3,3,11), (4,4,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_changes_03(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
    };
    applySets(sets,b);
    String expect = "[(4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_changes_04(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {2,1, 22},
      {0,3, 11},
      {1,1, 22},
      {0,-1,11},
      {3,1, 22},
    };
    applySets(sets,b);
    String expect = "[(1,1,22), (2,1,22), (3,1,22), (4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_changes_05(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {0,2, 22},
      {3,1, 11},
    };
    applySets(sets,b);
    String expect = "[(4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_changes_06(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {3,2, 33},
      {4,3, 33},
      {5,4, 33},
    };
    applySets(sets,b);
    String expect = "[(3,2,33), (4,3,33), (5,4,33)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_changes_07(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-2,-2, 33},
      {-3,-3, 33},
      {0,2, 11},
      {-4,-4, 33},
      {3,1,22},
    };
    applySets(sets,b);
    String expect = "[(0,0,11), (0,1,11), (0,2,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_changes_08(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    String expect = "[(-1,2,11), (0,1,11), (1,0,11), (2,-1,11)]";
    checkLongestSequence(b,expect);
  }

  // Dummy class; toString() returns Dum when displayCompareField is
  // false, returns an initialization number when true; uses deep
  // equals comparison on the comparison field to determine equality
  static class Dummy {
    public static boolean displayCompareField = false;
    int compareField;
    public Dummy(int cf){
      this.compareField = cf;
    }
    public boolean equals(Object obj){
      if(obj == null || !(obj instanceof Dummy)){
        return false;
      }
      Dummy that = (Dummy) obj;
      return this.compareField == that.compareField;
    }
    public String toString(){
      if(displayCompareField){
        return ""+this.compareField;
      }
      else{
        return "Dum";
      }
    }
  }
  
  // Ensure .equals() method is used for sequence detection, not
  // toString() methods, not shallow equals, uses Dummy class
  @Test public void set_longest_sequence_use_equals_01(){
    int rowMin=0, rowMax=+4, colMin=-1, colMax=4;
    Dummy fill = new Dummy(0);
    DenseBoard<Dummy> b = new DenseBoard<Dummy>(rowMin,rowMax,colMin,colMax,fill);

    Dummy.displayCompareField = false;
    b.set(0,0, new Dummy(11));
    b.set(0,1, new Dummy(22));
    b.set(1,0, new Dummy(11));
    Dummy.displayCompareField = true;
    String expect = "[(0,0,11), (1,0,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void set_longest_sequence_use_equals_02(){
    int rowMin=0, rowMax=+4, colMin=-1, colMax=4;
    Dummy fill = new Dummy(0);
    DenseBoard<Dummy> b = new DenseBoard<Dummy>(rowMin,rowMax,colMin,colMax,fill);

    Dummy.displayCompareField = false;
    b.set(+0,+0, new Dummy(11));
    b.set(+0,+1, new Dummy(22));
    b.set(+1,+0, new Dummy(11));
    b.set(-1,+1, new Dummy(22));
    b.set(-2,+1, new Dummy(22));
    Dummy.displayCompareField = true;
    String expect = "[(-2,1,22), (-1,1,22), (0,1,22)]";
     checkLongestSequence(b,expect);
  }

  // 2D array constructor + set() produces proper longest sequence
  @Test public void longest_seq_sets_2dconstr_01(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      {  0,  0,  2,  2,  0,  0,  3,  8,  },
      {  0,  4,  0,  2,  5,  5,  3,  8,  },
      {  4,  0,  0,  0,  9,  0, 13, 12,  },
    };
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {+0,+1, 2},
    };
    applySets(sets,b);
    String expect = "[(0,1,2), (0,2,2), (0,3,2)]";
    checkLongestSequence(b,expect);
  }
  @Test public void longest_seq_sets_2dconstr_02(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      {  0,  0,  2,  2,  0,  0,  3,  8,  },
      {  0,  4,  0,  2,  5,  5,  3,  8,  },
      {  4,  0,  0,  0,  9,  0, 13, 12,  },
    };
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {-1,+1, 2},
    };
    applySets(sets,b);
    String expect = "[(-1,1,2), (0,2,2), (1,3,2)]";
    checkLongestSequence(b,expect);
  }
  @Test public void longest_seq_sets_2dconstr_03(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      {  0,  0,  2,  2,  0,  0,  3,  8,  },
      {  0,  4,  0,  2,  5,  5,  3,  8,  },
      {  4,  0,  0,  0,  9,  0, 13, 12,  },
    };
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {+3,-1, 4},
    };
    applySets(sets,b);
    String expect = "[(1,1,4), (2,0,4), (3,-1,4)]";
    checkLongestSequence(b,expect);
  }
  @Test public void longest_seq_sets_2dconstr_04(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      {  0,  0,  2,  2,  0,  0,  3,  8,  },
      {  0,  4,  0,  2,  5,  5,  3,  8,  },
      {  4,  0,  0,  0,  9,  0, 13, 12,  },
    };
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {-1,+6, 3},
      {+3,-1, 4},
      {+2,+3, 2},
    };
    applySets(sets,b);
    String expect = "[(-1,6,3), (0,6,3), (1,6,3)]";
    checkLongestSequence(b,expect);
  }
  @Test public void longest_seq_sets_2dconstr_05(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      {  0,  0,  2,  2,  0,  0,  3,  8,  },
      {  0,  4,  0,  2,  5,  5,  3,  8,  },
      {  4,  0,  0,  0,  9,  0, 13, 12,  },
    };
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    int sets[][] = {
      {+3,-1, 4},
      {+2,+3, 2},
      {-1,+6, 3},
      {-1,+3, 2},
    };
    applySets(sets,b);
    String expect = "[(-1,3,2), (0,3,2), (1,3,2), (2,3,2)]";
    checkLongestSequence(b,expect);
  }

  // Check whether the longest sequence returned by the
  // getLongestSequence() is a copy or a shallow reference to an
  // internal field; make changes to the list and see if the board
  // returns the actual longest sequence
  @Test public void getLongestSequence_independent1(){
    int rowMin=-3, rowMax=2, colMin=0, colMax=4;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,0,1},
      {1,1,1},
      {2,4,2},
      {1,3,2},
      {3,5,2},
      {4,6,2},
    };
    applySets(sets,b);
    String expect = "[(1,3,2), (2,4,2), (3,5,2), (4,6,2)]";
    checkLongestSequence(b,expect);
    List<RowColElem<Integer>> copy = b.getLongestSequence();
    Collections.sort(copy,new RowMajor<Integer>());
    copy.remove(0);
    assertEquals("Something is seriously screwy","[(2,4,2), (3,5,2), (4,6,2)]",copy.toString());
    List<RowColElem<Integer>> actual = b.getLongestSequence();
    Collections.sort(actual,new RowMajor<Integer>());
    String actualS = actual.toString();
    if(!actualS.equals(expect)){
      failFmt("Longest sequence is not making deep copies\n"+
              "Expect: "+expect +"\n"+
              "Actual: "+actualS+"\n"+
              "Board:\n"+b.toString()+
              "");
    }
  }
  @Test public void getLongestSequence_independent2(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      { 1, 0, 3, 8, },
      { 4, 0, 3, 8, },
      { 0, 0, 3, 0, },
    };
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    String expect = "[(0,2,3), (1,2,3), (2,2,3)]";
    checkLongestSequence(b,expect);
    List<RowColElem<Integer>> copy = b.getLongestSequence();
    copy.clear();
    assertEquals("Somethign is seriously screwy","[]",copy.toString());
    List<RowColElem<Integer>> actual = b.getLongestSequence();
    Collections.sort(actual,new RowMajor<Integer>());
    String actualS = actual.toString();
    if(!actualS.equals(expect)){
      failFmt("Longest sequence is not making deep copies\n"+
              "Expect: "+expect +"\n"+
              "Actual: "+actualS+"\n"+
              "Board:\n"+b.toString()+
              "");
    }
  }

  // UNDO ONLY TESTS

  // Ensure undo history is empty after 2D constructor
  @Test public void no_undo_2D_constructor(){
    Integer fill = new Integer(0);
    Integer mat [][] = {
      { 1, 0, 3, 8, },
      { 4, 0, 3, 8, },
      { 0, 0, 3, 0, },
    };
    DenseBoard<Integer> b = new DenseBoard<Integer>(mat, fill);
    String expectS =
"    |  0|  1|  2|  3|\n"+
"    +---+---+---+---+\n"+
"  0 |  1|  0|  3|  8|\n"+
"    +---+---+---+---+\n"+
"  1 |  4|  0|  3|  8|\n"+
"    +---+---+---+---+\n"+
"  2 |  0|  0|  3|  0|\n"+
"    +---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
    boolean thrown = false;
    String expectMsg = "Undo history is empty";
    try {
      b.undoSet();
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nundoSet() on fresh board should throw exception with message:\n%s",expectMsg);
    }
    checkBoard(b,expectS,fill);
  }


  // Undo changes elements of board, doesn't change size after expands
  @Test public void undo_sets_01(){
    int rowMin=4, rowMax=4, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+0, 11},
    };
    applySets(sets,b);
    applyUndos(1, b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  4 |  0|\n"+
"    +---+\n"+
      "";
    int eRowMin=4, eRowMax=4, eColMin=-0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_02(){
    int rowMin=-1, rowMax=+3, colMin=0, colMax=3;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
    };
    applySets(sets,b);
    applyUndos(1, b);
    String expectS =
"    |  0|  1|  2|  3|\n"+
"    +---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=3, eColMin=-0, eColMax=3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_03(){
    int rowMin=4, rowMax=4, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+0, 11},
      {+3,+0, 22},
    };
    applySets(sets,b);
    applyUndos(1, b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  3 |  0|\n"+
"    +---+\n"+
"  4 | 11|\n"+
"    +---+\n"+
      "";
    int eRowMin=3, eRowMax=4, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_041(){
    int rowMin=4, rowMax=4, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+0, 11},
      {+3,+0, 22},
    };
    applySets(sets,b);
    applyUndos(2, b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  3 |  0|\n"+
"    +---+\n"+
"  4 |  0|\n"+
"    +---+\n"+
      "";
    int eRowMin=3, eRowMax=4, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_042(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(1, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0| 22|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_05(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(2, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_06(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(3, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_07(){
    int rowMin=-1, rowMax=1, colMin=-2, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-2,-2, 11},
      {-3,-2, 22},
      {-3,-4, 33},
    };
    applySets(sets,b);
    applyUndos(2, b);
    String expectS =
"    | -4| -3| -2| -1|  0|  1|  2|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -2 |  0|  0| 11|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=1, eColMin=-4, eColMax=2;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_08(){
    int rowMin=-1, rowMax=1, colMin=-2, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-2,-6, 11},
      {-3,-2, 22},
      {-3,+3, 33},
      {+4,+5, 44},
      {+0,+0, 55},
    };
    applySets(sets,b);
    applyUndos(3, b);
    String expectS =
"    | -6| -5| -4| -3| -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0| 22|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 | 11|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=4, eColMin=-6, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_sets_09(){
    int rowMin=-1, rowMax=1, colMin=-2, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {-3,+3, 22},
      {-3,-2, 33},
      {+4,+5, 44},
      {-2,-6, 55},
      {-9,-9, 66},
      {+2,+3, 77},
      {+2,+1, 88},
      {+8,+2, 99},
    };
    applySets(sets,b);
    applyUndos(8, b);
    String expectS =
"    | -9| -8| -7| -6| -5| -4| -3| -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -9 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -8 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0| 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  7 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  8 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-9, eRowMax=8, eColMin=-9, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  
  // Undo adjusts the longest sequence
  @Test public void undo_longest_sequence_changes_01(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(1, b);
    String expect = "[(3,1,22), (4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_longest_sequence_changes_02(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(4, b);
    String expect = "[(4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_longest_sequence_changes_03(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(sets.length, b);
    String expect = "[]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_longest_sequence_changes_04(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {5,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {1,0, 11},
      {3,1, 22},
      {2,-1, 11},
      {4,1, 22},
    };
    applySets(sets,b);
    applyUndos(sets.length-2, b);
    String expect = "[(5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_longest_sequence_changes_05(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {3,1, 33},
      {4,1, 33},
      {5,1, 33},
      {1,0, 11},
      {-1,1, 22},
      {2,-1, 11},
      {3,-1, 22},
    };
    applySets(sets,b);
    applyUndos(1, b);
    String expect = "[(-1,2,11), (0,1,11), (1,0,11), (2,-1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_longest_sequence_changes_06(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {3,1, 33},
      {4,1, 33},
      {5,1, 33},
      {1,0, 11},
      {-1,1, 22},
      {2,-1, 11},
      {3,-1, 22},
    };
    applySets(sets,b);
    applyUndos(5, b);
    String expect = "[(-1,2,11), (0,1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_longest_sequence_changes_07(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {3,1, 33},
      {4,1, 33},
      {5,1, 33},
      {1,0, 11},
      {-1,1, 22},
      {2,-1, 11},
      {3,-1, 22},
    };
    applySets(sets,b);
    applyUndos(3, b);
    String expect = "[(3,1,33), (4,1,33), (5,1,33)]";
    checkLongestSequence(b,expect);
  }

  // UNDO+REDO TESTS

  // Undo/redo properly change board state, elements only, min/max row/col stay the same
  @Test public void undo_redo_sets_01(){
    int rowMin=4, rowMax=4, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+0, 11},
    };
    applySets(sets,b);
    applyUndos(1, b);
    applyRedos(1, b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  4 | 11|\n"+
"    +---+\n"+
      "";
    int eRowMin=4, eRowMax=4, eColMin=-0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_02(){
    int rowMin=-1, rowMax=+3, colMin=0, colMax=3;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
    };
    applySets(sets,b);
    applyUndos(1, b);
    applyRedos(1, b);
    String expectS =
"    |  0|  1|  2|  3|\n"+
"    +---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  0 | 11|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|\n"+
"    +---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=3, eColMin=-0, eColMax=3;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_03(){
    int rowMin=4, rowMax=4, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+0, 11},
      {+3,+0, 22},
    };
    applySets(sets,b);
    applyUndos(1, b);
    applyRedos(1, b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  3 | 22|\n"+
"    +---+\n"+
"  4 | 11|\n"+
"    +---+\n"+
      "";
    int eRowMin=3, eRowMax=4, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_040(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(1, b);
    applyRedos(1, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0| 22|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0| 33|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_041(){
    int rowMin=4, rowMax=4, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+0, 11},
      {+3,+0, 22},
    };
    applySets(sets,b);
    applyUndos(2, b);
    applyRedos(2, b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  3 | 22|\n"+
"    +---+\n"+
"  4 | 11|\n"+
"    +---+\n"+
      "";
    int eRowMin=3, eRowMax=4, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_042(){
    int rowMin=4, rowMax=4, colMin=0, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+4,+0, 11},
      {+3,+0, 22},
    };
    applySets(sets,b);
    applyUndos(2, b);
    applyRedos(1, b);
    String expectS =
"    |  0|\n"+
"    +---+\n"+
"  3 |  0|\n"+
"    +---+\n"+
"  4 | 11|\n"+
"    +---+\n"+
      "";
    int eRowMin=3, eRowMax=4, eColMin=0, eColMax=0;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_05(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(2, b);
    applyRedos(1, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0| 22|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_061(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(3, b);
    applyRedos(1, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_062(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(3, b);
    applyRedos(2, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0| 22|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_063(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(3, b);
    applyRedos(3, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0| 22|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0| 33|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_064(){
    int rowMin=-1, rowMax=4, colMin=-2, colMax=5;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-1,-1, 11},
      {+1,+1, 22},
      {+4,+0, 33},
    };
    applySets(sets,b);
    applyUndos(3, b);
    applyRedos(3, b);
    applyUndos(2, b);
    String expectS =
"    | -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-1, eRowMax=4, eColMin=-2, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_07(){
    int rowMin=-1, rowMax=1, colMin=-2, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-2,-2, 11},
      {-3,-2, 22},
      {-3,-4, 33},
    };
    applySets(sets,b);
    applyUndos(2, b);
    applyRedos(1, b);
    String expectS =
"    | -4| -3| -2| -1|  0|  1|  2|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -3 |  0|  0| 22|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -2 |  0|  0| 11|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=1, eColMin=-4, eColMax=2;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_08(){
    int rowMin=-1, rowMax=1, colMin=-2, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {-2,-6, 11},
      {-3,-2, 22},
      {-3,+3, 33},
      {+4,+5, 44},
      {+0,+0, 55},
    };
    applySets(sets,b);
    applyUndos(3, b);
    applyRedos(2, b);
    applyUndos(3, b);
    applyRedos(2, b);
    String expectS =
"    | -6| -5| -4| -3| -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0| 22|  0|  0|  0|  0| 33|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 | 11|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-3, eRowMax=4, eColMin=-6, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }
  @Test public void undo_redo_sets_09(){
    int rowMin=-1, rowMax=1, colMin=-2, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {+0,+0, 11},
      {-3,+3, 22},
      {-3,-2, 33},
      {+4,+5, 44},
      {-2,-6, 55},
      {-9,-9, 66},
      {+2,+3, 77},
      {+2,+1, 88},
      {+8,+2, 99},
    };
    applySets(sets,b);
    applyUndos(8, b);
    applyRedos(4, b);
    applyUndos(2, b);
    String expectS =
"    | -9| -8| -7| -6| -5| -4| -3| -2| -1|  0|  1|  2|  3|  4|  5|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -9 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -8 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -7 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0| 33|  0|  0|  0|  0| 22|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|  0| 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  6 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  7 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
"  8 |  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"+
      "";
    int eRowMin=-9, eRowMax=8, eColMin=-9, eColMax=5;
    checkBoard(b,expectS,fill,eRowMin,eRowMax,eColMin,eColMax);
  }

  // Check to see if undo/redo and setFillElem play nice together
  @Test public void undo_redo_setFill1(){
    int fill = 0;
    String expectB;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][];

    sets = new int[][]{
      {0,0, 11},
    };
    applySets(sets, b);

    expectB =
"    |  0|\n"+
"    +---+\n"+
"  0 | 11|\n"+
"    +---+\n"+
      "";

    checkBoard(b,expectB,fill);

    fill = 999;
    b.setFillElem(fill);
    b.undoSet();

    expectB =
"    |  0|\n"+
"    +---+\n"+
"  0 |999|\n"+
"    +---+\n"+
      "";

    checkBoard(b,expectB,fill);
  }
  @Test public void undo_redo_setFill2(){
    int fill = 0;
    String expectB;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][];

    sets = new int[][]{
      {2,2, 11},
      {-1,0,22},
      {-1,2,33},
      {1,0,44},
    };
    applySets(sets, b);

    expectB =
"    |  0|  1|  2|\n"+
"    +---+---+---+\n"+
" -1 | 22|  0| 33|\n"+
"    +---+---+---+\n"+
"  0 |  0|  0|  0|\n"+
"    +---+---+---+\n"+
"  1 | 44|  0|  0|\n"+
"    +---+---+---+\n"+
"  2 |  0|  0| 11|\n"+
"    +---+---+---+\n"+
      "";
    checkBoard(b,expectB,fill);

    b.undoSet();
    b.undoSet();
    fill = 999;
    b.setFillElem(fill);

    expectB =
"    |  0|  1|  2|\n"+
"    +---+---+---+\n"+
" -1 | 22|999|999|\n"+
"    +---+---+---+\n"+
"  0 |999|999|999|\n"+
"    +---+---+---+\n"+
"  1 |999|999|999|\n"+
"    +---+---+---+\n"+
"  2 |999|999| 11|\n"+
"    +---+---+---+\n"+
      "";
    checkBoard(b,expectB,fill);

    b.redoSet();
    
    expectB =
"    |  0|  1|  2|\n"+
"    +---+---+---+\n"+
" -1 | 22|999| 33|\n"+
"    +---+---+---+\n"+
"  0 |999|999|999|\n"+
"    +---+---+---+\n"+
"  1 |999|999|999|\n"+
"    +---+---+---+\n"+
"  2 |999|999| 11|\n"+
"    +---+---+---+\n"+
      "";
    checkBoard(b,expectB,fill);

    fill = 666;
    b.setFillElem(fill);
    b.redoSet();
    
    expectB =
"    |  0|  1|  2|\n"+
"    +---+---+---+\n"+
" -1 | 22|666| 33|\n"+
"    +---+---+---+\n"+
"  0 |666|666|666|\n"+
"    +---+---+---+\n"+
"  1 | 44|666|666|\n"+
"    +---+---+---+\n"+
"  2 |666|666| 11|\n"+
"    +---+---+---+\n"+
      "";
    checkBoard(b,expectB,fill);
  }


  // Undo/redo adjust longest sequence
  @Test public void undo_redo_longest_sequence_changes_01(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(1, b);
    applyRedos(1, b);
    String expect = "[(-1,2,11), (0,1,11), (1,0,11), (2,-1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_021(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(4, b);
    applyRedos(1, b);
    String expect = "[(4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_022(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(4, b);
    applyRedos(3, b);
    String expect = "[(3,1,22), (4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_023(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(4, b);
    applyRedos(4, b);
    String expect = "[(-1,2,11), (0,1,11), (1,0,11), (2,-1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_03(){
    int rowMin=-1, rowMax=+4, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {0,0, 11},
      {5,1, 22},
      {4,1, 22},
      {0,1, 11},
      {-1,2,11},
      {3,1, 22},
      {1,0, 11},
      {2,-1, 11},
    };
    applySets(sets,b);
    applyUndos(sets.length, b);
    applyRedos(4, b);
    String expect = "[(4,1,22), (5,1,22)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_04(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {5,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {1,0, 11},
      {3,1, 22},
      {2,-1, 11},
      {4,1, 22},
    };
    applySets(sets,b);
    applyUndos(sets.length-2, b);
    applyRedos(4, b);
    applyUndos(1, b);
    String expect = "[(-1,2,11), (0,1,11), (1,0,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_05(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {3,1, 33},
      {4,1, 33},
      {5,1, 33},
      {1,0, 11},
      {-1,1, 22},
      {2,-1, 11},
      {3,-1, 22},
    };
    applySets(sets,b);
    applyUndos(1, b);
    applyRedos(1, b);
    String expect = "[(-1,2,11), (0,1,11), (1,0,11), (2,-1,11)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_06(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {3,1, 33},
      {4,1, 33},
      {5,1, 33},
      {1,0, 11},
      {-1,1, 22},
      {2,-1, 11},
      {3,-1, 22},
    };
    applySets(sets,b);
    applyUndos(5, b);
    applyRedos(3, b);
    String expect = "[(3,1,33), (4,1,33), (5,1,33)]";
    checkLongestSequence(b,expect);
  }
  @Test public void undo_redo_longest_sequence_changes_07(){
    int rowMin=-3, rowMax=+2, colMin=-3, colMax=+2;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 22},
      {-1,2,11},
      {0,1, 11},
      {0,0, 11},
      {3,1, 33},
      {4,1, 33},
      {5,1, 33},
      {1,0, 11},
      {-1,1, 22},
      {2,-1, 11},
      {3,-1, 22},
    };
    applySets(sets,b);
    applyUndos(6, b);
    applyRedos(2, b);
    applyUndos(3, b);
    applyRedos(1, b);
    applyUndos(2, b);
    String expect = "[(-1,2,11), (0,1,11)]";
    checkLongestSequence(b,expect);
  }

  // Undo/redo exceptions
  @Test public void undo_redo_exceptions_01(){
    int rowMin=1, rowMax=+3, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
    };
    applySets(sets, b);
    boolean thrown = false;
    String expectMsg = "Undo history is empty";
    try {
      applyUndos(1, b);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    String expectS =
"    | -1|  0|\n"+
"    +---+---+\n"+
"  1 |  0|  0|\n"+
"    +---+---+\n"+
"  2 |  0|  0|\n"+
"    +---+---+\n"+
"  3 |  0|  0|\n"+
"    +---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void undo_redo_exceptions_02(){
    int rowMin=1, rowMax=+3, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 11},
      {1,2, 22},
      {1,3, 33},
      {1,0, 44},
    };
    applySets(sets, b);
    boolean thrown = false;
    String expectMsg = "Undo history is empty";
    try {
      applyUndos(5, b);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    String expectS =
"    | -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void undo_redo_exceptions_03(){
    int rowMin=1, rowMax=+3, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
    };
    applySets(sets, b);
    boolean thrown = false;
    String expectMsg = "Redo history is empty";
    try {
      applyRedos(1, b);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    String expectS =
"    | -1|  0|\n"+
"    +---+---+\n"+
"  1 |  0|  0|\n"+
"    +---+---+\n"+
"  2 |  0|  0|\n"+
"    +---+---+\n"+
"  3 |  0|  0|\n"+
"    +---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void undo_redo_exceptions_04(){
    int rowMin=1, rowMax=+3, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 11},
      {1,2, 22},
      {1,3, 33},
      {1,0, 44},
    };
    applySets(sets, b);
    boolean thrown = false;
    String expectMsg = "Redo history is empty";
    try {
      applyUndos(3, b);
      applyRedos(4, b);
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    String expectS =
"    | -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0| 44| 11| 22| 33|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }
  @Test public void undo_redo_exceptions_05(){
    int rowMin=1, rowMax=+3, colMin=-1, colMax=0;
    int fill = 0;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][] = {
      {1,1, 11},
      {1,2, 22},
      {1,3, 33},
      {1,0, 44},
    };
    applySets(sets, b);
    boolean thrown = false;
    String expectMsg = "Redo history is empty";
    try {
      applyUndos(3, b);
      applyRedos(1, b);
      b.set(0,-1,55);
      b.redoSet();
    }
    catch(RuntimeException e){
      thrown = true;
      String actualMsg = e.getMessage();
      if(!actualMsg.equals(expectMsg)){
        failFmt("\nset() exception message does not match expected.\nExpect: %s\nActual: %s\n",
                expectMsg,actualMsg);
      }
    }
    if(thrown == false){
      failFmt("\nset() should throw an exception with message:\n%s",expectMsg);
    }
    String expectS =
"    | -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+\n"+
"  0 | 55|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0|  0| 11| 22|  0|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    checkBoard(b,expectS,fill);
  }

  // STRESS TESTS

  // Large sequence of moves
  @Test public void stress_01(){
    int rowMin=1, rowMax=+3, colMin=-1, colMax=0;
    int fill = 0;
    String expectB, expectLS;
    DenseBoard<Integer> b = new DenseBoard<Integer>(rowMin,rowMax,colMin,colMax,fill);
    int sets[][];
    sets = new int[][]{
      {1,1, 11},
      {1,2, 22},
      {1,3, 33},
      {1,0, 44},
    };
    applySets(sets, b);
    expectB =
"    | -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+\n"+
"  1 |  0| 44| 11| 22| 33|\n"+
"    +---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,1,11)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    sets = new int[][]{
      {+0,-1, 11},
      {+2,+2, 22},
      {+3,+3, 33},
      {+1,-1, 44},
      {+0,+0, 11},
      {+3,+2, 22},
      {+2,+4, 33},
    };
    applySets(sets, b);

    expectB =
"    | -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 | 11| 11|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 | 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0| 22|  0| 33|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,2,22), (2,2,22), (3,2,22)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    applyUndos(3, b);

    expectB =
"    | -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 | 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 | 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0| 22|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,2,22), (2,2,22)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    applyRedos(2, b);

    expectB =
"    | -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 | 11| 11|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 | 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0| 22|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,2,22), (2,2,22), (3,2,22)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    sets = new int[][]{
      {+0,+1, 44},
      {-1,-1, 11},
      {+0,+3, 22},
      {+2,+3, 33},
    };
    applySets(sets, b);

    expectB =
"    | -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+\n"+
" -1 | 11|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 | 11| 11| 44|  0| 22|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 | 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,2,22), (2,2,22), (3,2,22)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    sets = new int[][]{
      {-1,+2, 44},
      {-2,-2, 11},
      {+2,+1, 22},
      {+4,+3, 33},
      {+3,-2, 44},
      {-1,-0, 11},
    };
    applySets(sets, b);

    expectB =
"    | -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -2 | 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |  0| 11| 11|  0| 44|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0| 11| 11| 44|  0| 22|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0| 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0| 22| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  3 | 44|  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(-2,-2,11), (-1,-1,11), (0,0,11), (1,1,11)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    applyUndos(4, b);
    applyRedos(4, b);
    applyUndos(4, b);
    applyRedos(2, b);


    expectB =
"    | -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -2 | 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0| 44|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0| 11| 11| 44|  0| 22|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0| 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0| 22| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(-2,-2,11), (-1,-1,11), (0,0,11), (1,1,11)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    applyUndos(2, b);


    expectB =
"    | -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -2 | 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |  0| 11|  0|  0| 44|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0| 11| 11| 44|  0| 22|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0| 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(-2,-2,11), (-1,-1,11), (0,0,11), (1,1,11)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    sets = new int[][]{
      {+2,-1, 44},
      {-1,-2, 11},
      {+0,+2, 22},
      {+5,+3, 33},
      {+3,-2, 44},
      
    };
    applySets(sets, b);

    expectB =
"    | -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -2 | 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 | 11| 11|  0|  0| 44|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |  0| 11| 11| 44| 22| 22|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 |  0| 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  2 |  0| 44|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  3 | 44|  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0| 33|  0|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(-1,2,44), (0,1,44), (1,0,44), (2,-1,44), (3,-2,44)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    sets = new int[][]{
      {+4,-3, 44},
      {+5,-4, 44},
    };
    applySets(sets, b);


    expectB =
"    | -4| -3| -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0| 11|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0| 11| 11|  0|  0| 44|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0| 11| 11| 44| 22| 22|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0| 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0| 44|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0| 44|  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0| 44|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  5 | 44|  0|  0|  0|  0|  0|  0| 33|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(-1,2,44), (0,1,44), (1,0,44), (2,-1,44), (3,-2,44), (4,-3,44), (5,-4,44)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    applyUndos(13, b);
    applyRedos(1, b);

    expectB =
"    | -4| -3| -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0| 11| 11| 44|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  1 |  0|  0|  0| 44| 44| 11| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  2 |  0|  0|  0|  0|  0|  0| 22|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0| 22| 33|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  4 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
"  5 |  0|  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,2,22), (2,2,22), (3,2,22)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

  }    

  @Test public void stress_02(){
    int fill = 0;
    String expectB, expectLS;
    DenseBoard<Integer> b = new DenseBoard<Integer>(fill);
    int sets[][];

    sets = new int[][]{
      {-1,1, 11},
      {1,2,  22},
      {1,-3, 11},
      {1,0,  22},
    };
    applySets(sets, b);

    expectB =
"    | -3| -2| -1|  0|  1|  2|\n"+
"    +---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0| 11|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+\n"+
"  1 | 11|  0|  0| 22|  0| 22|\n"+
"    +---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(-1,1,11)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    fill = 999;
    b.addRowBottom();
    b.setFillElem(fill);
    b.addColRight();

    expectB =
"    | -3| -2| -1|  0|  1|  2|  3|\n"+
"    +---+---+---+---+---+---+---+\n"+
" -1 |999|999|999|999| 11|999|999|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  0 |999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  1 | 11|999|999| 22|999| 22|999|\n"+
"    +---+---+---+---+---+---+---+\n"+
"  2 |999|999|999|999|999|999|999|\n"+
"    +---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(-1,1,11)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);
    
    b.expandToInclude(-4,2);
    fill = -99;
    b.setFillElem(fill);
    sets = new int[][]{
      {2,2, 11},
      {-2,4,  22},
      {2,-3, 11},
      {3,3,  22},
    };
    applySets(sets, b);
    
    expectB =
"    | -3| -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -4 |-99|-99|-99|-99|-99|-99|-99|-99|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -3 |-99|-99|-99|-99|-99|-99|-99|-99|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -2 |-99|-99|-99|-99|-99|-99|-99| 22|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |-99|-99|-99|-99| 11|-99|-99|-99|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |-99|-99|-99|-99|-99|-99|-99|-99|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 | 11|-99|-99| 22|-99| 22|-99|-99|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 | 11|-99|-99|-99|-99| 11|-99|-99|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |-99|-99|-99|-99|-99|-99| 22|-99|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,-3,11), (2,-3,11)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);

    sets = new int[][]{
      {1,1,  22},
      {3,-3,  11},
      {3,4,  22},
    };
    applySets(sets, b);
    fill = 0;
    b.setFillElem(fill);
    applyUndos(4, b);
    applyRedos(2, b);

    expectB =
"    | -3| -2| -1|  0|  1|  2|  3|  4|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -4 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -3 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -2 |  0|  0|  0|  0|  0|  0|  0| 22|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
" -1 |  0|  0|  0|  0| 11|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  0 |  0|  0|  0|  0|  0|  0|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  1 | 11|  0|  0| 22| 22| 22|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  2 | 11|  0|  0|  0|  0| 11|  0|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
"  3 |  0|  0|  0|  0|  0|  0| 22|  0|\n"+
"    +---+---+---+---+---+---+---+---+\n"+
      "";
    expectLS = "[(1,0,22), (1,1,22), (1,2,22)]";
    checkBoard(b,expectB,fill);    
    checkLongestSequence(b,expectLS);
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Utilities to check boards
  ////////////////////////////////////////////////////////////////////////////////

  // Utility to fail with given format string and arguments
  public static void failFmt(String fmt, Object... args){
    fail(String.format(fmt,args));
  }

  public void applySets(int rowColElems[][], ExpandableBoard<Integer> board){
    for(int rce[] : rowColElems){
      int row=rce[0], col=rce[1], elem=rce[2];
      board.set(row,col,elem);
    }
  }

  public void applyUndos(int nUndos, ExpandableBoard<Integer> board){
    for(int i=0; i<nUndos; i++){
      board.undoSet();
    }
  }
  public void applyRedos(int nRedos, ExpandableBoard<Integer> board){
    for(int i=0; i<nRedos; i++){
      board.redoSet();
    }
  }

  // Utility to check expandtoInclude() method
  public void checkExpands(int rowColElems[][], DenseBoard<Integer> board){
    for(int rce[] : rowColElems){
      int row=rce[0], col=rce[1], expect=rce[2];
      String before = board.toString();
      int actual = board.expandToInclude(row,col);
      String after = board.toString();
      if(actual != expect){
        String msg =
          "expandToInclude() returned wrong number of new elements\n"+
          "Expect: "+expect+"\n"+
          "Actual: "+actual+"\n"+
          String.format("Call: expandToInclude(%d,%d)\n",row,col)+
          "Before call:\n"+before+
          "After call:\n"+after;
        failFmt(msg);
      }
    }
  }

  // Check that the string representation is correct and that the fill
  // element is correct
  public <T> void checkBoard(ExpandableBoard<T> board, String expectS, T expectFillElem){
    T actualFillElem = board.getFillElem();
    assertEquals("Fill elements don't match",expectFillElem,actualFillElem);

    String actualS = board.toString();
    if(!actualS.equals(expectS)){
      String diff = simpleDiff2("EXPECT:\n"+expectS,"ACTUAL:\n"+actualS);
      failFmt("\nExpected and actual board strings do not match. Differences marked by ****\n"+diff);
    }
  }

  public <T> void checkBoard(ExpandableBoard<T> board, String expectS, T expectFillElem,
                             int eRowMin, int eRowMax, int eColMin, int eColMax){
    String actualS = board.toString();
    if(!actualS.equals(expectS)){
      String diff = simpleDiff2("EXPECT:\n"+expectS,"ACTUAL:\n"+actualS);
      failFmt("\nExpected and actual board strings do not match. Differences marked by ****\n"+diff);
    }

    T actualFillElem = board.getFillElem();
    assertEquals("Fill elements don't match",expectFillElem,actualFillElem);

    assertEquals("getRowMin() returned wrong number",eRowMin,board.getMinRow());
    assertEquals("getRowMax() returned wrong number",eRowMax,board.getMaxRow());
    assertEquals("getColMin() returned wrong number",eColMin,board.getMinCol());
    assertEquals("getColMax() returned wrong number",eColMax,board.getMaxCol());

    if(board instanceof DenseBoard){
      DenseBoard<T> db = (DenseBoard<T>) board;
      assertEquals("getPhysicalRows() returned wrong number",db.getMaxRow()-db.getMinRow()+1,db.getPhysicalRows());
      assertEquals("getPhysicalCols() returned wrong number",db.getMaxCol()-db.getMinCol()+1,db.getPhysicalCols());
    }
  }

  // // Comparator to sort elements of longest sequence lists
  // class RowMajor<T> implements Comparator<RowColElem<T>>{
  //   public int compare(RowColElem<T> x, RowColElem<T> y){
  //     int diff = x.getRow()-y.getRow();
  //     if(diff != 0){ return diff; }
  //     return x.getCol()-y.getCol();
  //   }
  // }
  // Comparator<RowColElem<Integer>> cmpI = new RowMajor<Integer>();
  // Collections.sort(actual,new RowMajor<T>());

  // Utility to check the longest sequence matches the expected; does
  // sorting on RowColElem items to ensure that there is a canonical order
  public <T> void checkLongestSequence(ExpandableBoard<T> b, String expect){
    List<RowColElem<T>> actual = b.getLongestSequence();
    Collections.sort(actual);   // Assumes RowColElem is Comparable
    String actualS = actual.toString();
    if(!actualS.equals(expect)){
      failFmt("Longest sequence doesn't match\n"+
              "Expect: "+expect +"\n"+
              "Actual: "+actualS+"\n"+
              "Board:\n"+b.toString()+
              "");
    }
  }

  ////////////////////////////////////////////////////////////////////////////////
  // Utilities to append columns of strings
  ////////////////////////////////////////////////////////////////////////////////
  
  // Append strings as columns using space as the divider
  public static String appendColumns2(String all[]){
    return appendColumns2(all, " ");
  }
  
  // Create a side-by-side diff of two strings compared line by line
  public static String simpleDiff2(String x, String y){
    String xs[] = x.split("\n");
    String ys[] = y.split("\n");
    String sep = "      ";
    String dif = " **** ";
    StringBuilder sb = new StringBuilder();
    
    int maxWidth = 0;
    for(String s : xs){
      maxWidth = s.length() > maxWidth ? s.length() : maxWidth;
    }
    for(String s : ys){
      maxWidth = s.length() > maxWidth ? s.length() : maxWidth;
    }
    // Max width format
    String fmt = String.format("%%-%ds",maxWidth);
    
    // Construct the side-by-side diff
    for(int i = 0; i<xs.length || i<ys.length; i++){
      if(i<xs.length && i<ys.length){ // both exist, compare
        sb.append(String.format(fmt,xs[i]));
        String mid = xs[i].equals(ys[i]) ? sep : dif;
        sb.append(mid);
        sb.append(String.format(fmt,ys[i]));
        sb.append("\n");
      }
      else if(i<xs.length){     // only x left
        sb.append(String.format(fmt,xs[i]));
        sb.append(dif);
        sb.append(String.format(fmt,""));
        sb.append("\n");
      }
      else if(i<ys.length){     // only y left
        sb.append(String.format(fmt,""));
        sb.append(dif);
        sb.append(String.format(fmt,ys[i]));
        sb.append("\n");
      }
      else{
        throw new RuntimeException("Something fishy's going on here...");
      }
    }
    return sb.toString();
  }
  
  // Append string as columns using the provided divider between lines
  public static String appendColumns2(String all[], String divider){
    String allCols[][] = new String[all.length][];
    int widths[] = new int[all.length];
    int rowCounts[] = new int[all.length];
    for(int col=0; col<all.length; col++){
      widths[col]=1;            // Can't have %0s formats
      allCols[col] = all[col].split("\n");
      for(int row=0; row<allCols[col].length; row++){
        int len = allCols[col][row].length();
        widths[col] = len > widths[col] ? len : widths[col];
      }
    }
    String formats[] = new String[all.length];
    int maxRow = 0;
    for(int col=0; col<all.length; col++){
      String div = col < all.length-1 ? divider : "\n";
      formats[col] = String.format("%%-%ds%s",widths[col],div);
      maxRow = maxRow < allCols[col].length ? allCols[col].length : maxRow;
    }
    StringBuilder sb = new StringBuilder();
    for(int row=0; row<maxRow; row++){
      for(int col=0; col<all.length; col++){
        String fill = "";
        if(row < allCols[col].length){
          fill = allCols[col][row];
        }
        sb.append(String.format(formats[col],fill));
      }
    }
    return sb.toString();
  }

}
