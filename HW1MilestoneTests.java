/* CHANGELOG: 
   Initial release: 100 tests

   MILESTONE TESTS
   These tests must be passed by the early deadline for the project in
   order to get credit for the Milestone portion of the project. They
   are a subset of the final tests for the project

   To run them on the command line, make sure that the junit-310.jar
   is in the project directory.
 
   UNIX/MAX OS X: in the terminal do
   demo$ javac -cp .:junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .:junit-cs310.jar HW1MilestoneTests   # run tests
 
   WINDOWS: in cmd.exe, same commands but replace : with ; (colon with semicolon)
   demo$ javac -cp .;junit-cs310.jar *.java     # compile everything
   demo$ java  -cp .;junit-cs310.jar HW1MilestoneTests   # run tests
*/

import org.junit.*;
import org.junit.Test; // fixes some compile problems with annotations
import org.junit.Rule;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import java.util.*;
import java.io.*;

public class HW1MilestoneTests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("HW1MilestoneTests");
  }
  
  // Global timeout for all tests: use argument to Timeout.millis( __ );
 // @Rule public Timeout globalTimeout = Timeout.millis(1000); 

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
