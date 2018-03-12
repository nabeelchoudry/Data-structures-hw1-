import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DenseBoard<T> implements ExpandableBoard<T>{
	// Implementation of ExpandableBoard which uses internal 2D
	// arrays/ArrayLists to store elements. Provides undo/redo
	// capabilities and tracks the longest sequence as elements are set.
	// 
	// Space Complexity: O(R*C + H*L)
	//   R: number of rows in the board
	//   C: number of cols in the board
	//   H: combined size of the undo/redo history
	//   L: length of the longest sequence
	private int minRow, maxRow,minCol,maxCol;
	private T fillElem;
	private T oldFillElem;
	private ArrayList<ArrayList<T>> board;
	Stack<RowColElem<T>> moves=new Stack<RowColElem<T>>();
	Stack<RowColElem<T>> undos=moves;
	Stack<RowColElem<T>> Redomoves=new Stack<RowColElem<T>>();
	List< RowColElem<T> > longS= new ArrayList< RowColElem<T> >();;


	public DenseBoard(int minRow, int maxRow, int minCol, int maxCol, T fillElem) {
		this.minRow=minRow;
		this.maxRow=maxRow;
		this.minCol=minCol;
		this.maxCol=maxCol;
		if (fillElem==null)
		{
			throw new RuntimeException("Cannot set fill element to null");
		}
		else {
			this.fillElem=fillElem;
			oldFillElem=fillElem;
		}
		board=new ArrayList<ArrayList<T>>();

		for(int i=0;i<=maxRow-minRow;i++){
			board.add(new ArrayList<T>());
			for(int j=0;j<=maxCol-minCol;j++){
				board.get(i).add(fillElem);
			}

		}
	}

	public DenseBoard(T fillElem) {

		this(0,0,0,0,fillElem);

	}
	public DenseBoard(T[][] x, T fillElem) {
		this.minRow=0;
		this.maxRow=x.length-1;
		this.minCol=0;
		this.maxCol=x[0].length-1;
		if (fillElem==null)
		{
			throw new RuntimeException("Cannot set fill element to null");
		}
		else {

			this.fillElem=fillElem;
			oldFillElem=fillElem;
		}
		board=new ArrayList<ArrayList<T>>();

		for(int i=0;i<=maxRow-minRow;i++){
			board.add(new ArrayList<T>());
			for(int j=0;j<=maxCol-minCol;j++){
				board.get(i).add(fillElem);
				

			}

		}	
		for(int i=0;i<=maxRow-minRow;i++){
			board.add(new ArrayList<T>());
			for(int j=0;j<=maxCol-minCol;j++){

				set(i,j,x[i][j]);
				undos=new Stack<RowColElem<T>>();//undo should return empty 
			}
		} 
	}
	public int getMinRow() {
		return minRow;
	}
	public int getMaxRow() {
		return maxRow;
	}
	public int getMinCol() {
		return minCol;
	}
	public int getMaxCol() {
		return maxCol;
	}
	public T getFillElem() {
		return fillElem;
	}
	public void setFillElem(T f) {
		if (fillElem==null)
		{
			throw new RuntimeException("Cannot set fill element to null");
		}
		else {

			this.fillElem=f;
		}
	}
	public List< RowColElem<T> > getLongestSequence() {
		return longS;
	}

	/*
	 * helper method to find LongestSequnce
	 * use the stack that keep track of undo as reference 
	 * us the top of the stack elem and compare it on 4 side
	 */
	public void helpLongest(){
		int newRow,newCol;
		// if the board started at 0 0 
		if(minRow==0 && minCol==0){
			newRow=moves.peek().getRow();
			newCol=moves.peek().getCol();
		}
		// row and cols from arrayList to number on the board
		else{

			newRow=minRow+moves.peek().getRow();
			newCol=minCol+moves.peek().getCol();

		}
		// temp list to store elem and compare 
		List< RowColElem<T> > ls = new ArrayList< RowColElem<T> >();
		// if there is no sequnce longer the 1 it use the first elem placed on the board
		if(moves.size()==1){
			// loc variable to store where the elem is located
			RowColElem<T> loc = new RowColElem<T>(newRow,newCol,moves.peek().getElem());
			ls.add(loc);
			// to return the longest sequnce the LongS points to temp ls list
			longS=new ArrayList< RowColElem<T>>(ls);
			// temp list cleared to check for other sequences
			ls.clear();
		}
		else if(moves.size()>=2){
			//location of the elem
			RowColElem<T> loc = new RowColElem<T>(newRow,newCol,moves.peek().getElem());
			ls.add(loc);
			// check in if the left side of right side is equal
			if(moves.peek().getCol()-1>=0)
			{
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()).get(moves.peek().getCol()-1)))
				{
					int l=1;// to go left each col
					// loop while board doesnt end or while elem on left is not equal
					while(moves.peek().getCol()-l>=0 && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()).get(moves.peek().getCol()-l))){
						loc = new RowColElem<T>(newRow,newCol-l,moves.peek().getElem());
						ls.add(0, loc);// left most sequence to the front of the list
						l++;
					}
				}
			}
			//check if the elem on the right side is equal
			if(moves.peek().getCol()+1<=maxCol-minCol){
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()).get(moves.peek().getCol()+1))){
					int r=1;// to go right each col
					// loop while board doesnt end or while elem on right is not equal
					while(moves.peek().getCol()+r<=maxCol-minCol && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()).get(moves.peek().getCol()+r))){
						loc = new RowColElem<T>(newRow,newCol+r,moves.peek().getElem());
						ls.add(loc);
						r++;
					}
				}

			}
			// if the size of the temp is long the the earlier sequence change the longest to temp
			if(ls.size()>longS.size()){
				longS=new ArrayList< RowColElem<T>>(ls);
				// clear the ls to check for other side sequnces
				ls.clear();
			}
			
			// check if the elem above is equal
			if(moves.peek().getRow()-1>=0)
			{
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()-1).get(moves.peek().getCol())))
				{
					int t=1;// to go top each row
					// loop while board doesnt end or while elem on top is not equal
					while(moves.peek().getRow()-t>=0 && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()-t).get(moves.peek().getCol()))){
						loc = new RowColElem<T>(newRow-t,newCol,moves.peek().getElem());
						ls.add(0, loc);
						t++;
					}
				}
			}
			// check if the elem below is equal
			if(moves.peek().getRow()+1<=maxRow-minRow){
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()+1).get(moves.peek().getCol())))
				{
					int d=1;// to go down each row
					// loop while board doesnt end or while elem on right is not equal
					while(moves.peek().getRow()+d<=maxRow-minRow && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()+d).get(moves.peek().getCol()))){
						loc = new RowColElem<T>(newRow+d,newCol,moves.peek().getElem());
						ls.add(loc);
						d++;
					}
				}

			}
			// check if the current sequence is the longest
			if(ls.size()>longS.size()){
				longS=new ArrayList< RowColElem<T>>(ls);
				ls.clear();
			}
// check the diagonals 
			if(moves.peek().getRow()-1>=0 && moves.peek().getCol()-1>=0)
			{
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()-1).get(moves.peek().getCol()-1)))
				{
					int t=1;// to go top each row and col
					// loop while board doesnt end or while elem on top is not equal
					while(moves.peek().getRow()-t>=0 && moves.peek().getCol()-t>=0 && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()-t).get(moves.peek().getCol()-t))){
						loc = new RowColElem<T>(newRow-t,newCol-t,moves.peek().getElem());
						ls.add(0, loc);
						t++;
					}
				}
			}
			// check if the elem below is equal
			if(moves.peek().getRow()+1<=maxRow-minRow && moves.peek().getCol()+1<=maxCol-minCol){
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()+1).get(moves.peek().getCol()+1)))
				{
					int d=1;// to go down each row and right col
					// loop while board doesnt end or while elem on right is not equal
					while(moves.peek().getRow()+d<=maxRow-minRow && moves.peek().getCol()+d<=maxCol-minCol && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()+d).get(moves.peek().getCol()+d))){
						loc = new RowColElem<T>(newRow+d,newCol+d,moves.peek().getElem());
						ls.add(loc);
						d++;
					}
				}

			}
			// check if the current sequence is the longest
			if(ls.size()>longS.size()){
				longS=new ArrayList< RowColElem<T>>(ls);
				ls.clear();
			}

			if(moves.peek().getRow()-1>=0 && moves.peek().getCol()+1<=maxCol-minCol)
			{
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()-1).get(moves.peek().getCol()+1)))
				{
					int t=1;// to go top each row and right col
					// loop while board doesnt end or while elem on top is not equal
					while(moves.peek().getRow()-t>=0 && moves.peek().getCol()+t<=maxCol-minCol && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()-t).get(moves.peek().getCol()+t))){
						loc = new RowColElem<T>(newRow-t,newCol+t,moves.peek().getElem());
						ls.add(0, loc);
						t++;
					}
				}
			}
			// check if the elem below is equal
			if(moves.peek().getRow()+1<=maxRow-minRow && moves.peek().getCol()-1>=0){
				if(moves.peek().getElem().equals(board.get(moves.peek().getRow()+1).get(moves.peek().getCol()-1)))
				{
					int d=1;// to go down each row
					// loop while board doesnt end or while elem on right is not equal
					while(moves.peek().getRow()+d<=maxRow-minRow && moves.peek().getCol()-d>=0 && 
							moves.peek().getElem().equals(board.get(moves.peek().getRow()+d).get(moves.peek().getCol()-d))){
						loc = new RowColElem<T>(newRow+d,newCol-d,moves.peek().getElem());
						ls.add(loc);
						d++;
					}
				}

			}
			// check if the current sequence is the longest
			if(ls.size()>longS.size()){
				longS=new ArrayList< RowColElem<T>>(ls);
				ls.clear();
			}

		}

	}



	public T get(int row, int col) {
		// if the request location doesnt exit on the board
		if(row<minRow||row>maxRow||col<minCol||col>maxCol){
			return fillElem;

		}
		else{
			int newRow,newCol;
			// convert the row and cols into location on the arraylist
			if(minRow<=0){

				newRow=row-minRow;
			}
			else{
				newRow=(minRow*-1)+row;
			}
			if(minCol<=0){

				newCol=col-minCol;
			}
			else{
				newCol=(minCol*-1)+col;
			}
			// if the the fillElem was changed this return the changed elem
			if(board.get(newRow).get(newCol).equals(oldFillElem)){
				return fillElem;
			}
			else{
				return board.get(newRow).get(newCol);
			}

		}
	}

	public void addRowBottom() {
		for(int j=0;j<=maxCol-minCol;j++){
			board.get(maxRow+1).add(fillElem);
		}
		maxRow=maxRow+1;// change the max row new value of maxRow

	}

	public void addColRight() {
		for(int i=0;i<=maxRow-minRow;i++){
			board.get(i).add(fillElem);

		}
		maxCol=maxCol+1;
	}

	public void set(int row, int col, T x) {

		if (x==null)
		{
			throw new RuntimeException("Cannot set elements to null");
		}
		else if(x.equals(fillElem)){
			return;
		}

		else{
			int newRow,newCol;// to store the row and col location on the board Arraylist
			// check if board need expanding 
			if(row>maxRow||row<minRow||col>maxCol||col<minCol){
				//update the undos stack with new location if row or col add up or on left
				if(!(moves.isEmpty())){
					if(row<minRow || col<minCol){
						int addedRow=0;
						int addedCol=0;
						if(row<minRow){
							addedRow=minRow-row;
						}
						if(col<minCol){
							addedCol=minCol-col;
						}
						RowColElem<T>newlocation = new RowColElem<T>(moves.peek().getRow()+addedRow,moves.peek().getCol()+addedCol,moves.peek().getElem());
						moves.pop();
						moves.push(newlocation);
					}
				}
				expandToInclude(row,col);
			}
			// Convert the rows and cols location on the arraylist
			if(minRow==0 && minCol==0){
				newRow=row;
				newCol=col;
			}
			// convert the row and cols into location on the arraylist
			else{
				if(minRow<=0){

					newRow=row-minRow;
				}
				else{
					newRow=(minRow*-1)+row;
				}
				if(minCol<=0){

					newCol=col-minCol;
				}
				else{
					newCol=(minCol*-1)+col;
				}
			}
			// check if elem alredy exist at that location
			if(!(board.get(newRow).get(newCol).equals(fillElem)) || !(board.get(newRow).get(newCol).equals(oldFillElem))){
				throw new RuntimeException("Element "+row+" "+col +" already set to "+board.get(newRow).get(newCol));
			}
			else{
				
				board.get(newRow).set(newCol, x);// set the elem
				Redomoves.clear();// no more redos in new elem is set
				RowColElem<T>location = new RowColElem<T>(newRow,newCol,board.get(newRow).get(newCol));
				moves.push(location);
				helpLongest();
			}
		}
	}


	public int getPhysicalRows() {
		return maxRow-minRow+1;
	}


	public int getPhysicalCols() {
		return maxCol-minCol+1;
	}


	public int expandToInclude(int row, int col) {
		int r=this.board.size();
		int c=this.board.get(0).size();

		int oldSize=r*c;
		// add the extra row at bottom
		if(row>maxRow)
		{
			int newRow=row-maxRow;// how many rows need to be add
			for(int i=maxRow-minRow+1;i<=maxRow-minRow+newRow;i++){
				board.add(new ArrayList<T>());
				for(int j=0;j<=maxCol-minCol;j++){
					board.get(i).add(fillElem);
				}	
			}
			maxRow=row;// change the max row new value of maxRow
		}

		if(col>maxCol){
			int newCol=col-maxCol;// how many cols need to be add
			for(int i=0;i<=maxRow-minRow;i++){
				for(int j=maxCol-minCol+1;j<=maxCol-minCol+newCol;j++){
					board.get(i).add(fillElem);
				}
			}
			maxCol=col;
		}
		if(row<minRow)
		{
			int newRow=minRow-row;
			for(int i=maxRow-minRow+1;i<=maxRow-minRow+newRow;i++){
				board.add(new ArrayList<T>());
				for(int j=0;j<=maxCol-minCol;j++){
					board.get(i).add(fillElem);
				}
			}
			minRow=row;// change the minrow new value of minRow
			for(int i=maxRow-minRow;i>=0;i--){
				for(int j=0;j<=maxCol-minCol;j++){
					if((i-newRow)<0){
						board.get(i).set(j, fillElem);
					}
					else{
						board.get(i).set(j, board.get(i-newRow).get(j));
					}	
				}
			}
		}

		if(col<minCol){
			int newCol=minCol-col;
			for(int i=0;i<=maxRow-minRow;i++){
				for(int j=maxCol-minCol+1;j<=maxCol-minCol+newCol;j++){
					board.get(i).add(fillElem);
				}

			}
			minCol=col;// change the minrow new value of minRow
			for(int i=0;i<=maxRow-minRow;i++){
				for(int j=maxCol-minCol;j>=0;j--){
					if((j-newCol)<0){
						board.get(i).set(j, fillElem);
					}
					else{
						board.get(i).set(j, board.get(i).get(j-newCol));
					}	
				}
			}

		}
		int newSize=(board.size()*board.get(0).size());
		int added=newSize-oldSize;

		return added;
	}


	public void undoSet() {
		if(undos.isEmpty()){
			throw new RuntimeException("Undo history is empty");
		}
		else{
			int r=undos.peek().getRow();
			int c=undos.peek().getCol();
			Redomoves.push(undos.peek());
			undos.pop();
			board.get(r).set(c, fillElem);
		}
	}


	public void redoSet() {
		if(Redomoves.isEmpty()){
			throw new RuntimeException("Redo history is empty");
		}
		else{
			board.get(Redomoves.peek().getRow()).set(Redomoves.peek().getCol(),Redomoves.peek().getElem());
			undos.push(Redomoves.peek());
			Redomoves.pop();

		}
	}


	public String toString() {


		StringBuilder table=new StringBuilder();
		table.append("    ");
		int col=minCol;
		for(int j=0;j<maxCol-minCol+1;j++){
			table.append("|");
			table.append(String.format("%3s",col));
			if(j==maxCol-minCol){
				table.append("|\n");
			}
			col++;
		}
		table.append("    ");
		for(int i=0;i<=maxCol-minCol;i++){
			table.append("+---");
			if(i==maxCol-minCol){
				table.append("+\n");
			}
		}
		int j=0;
		int row=minRow;
		while(!(j==maxRow-minRow+1)){
			table.append(String.format("%3s",row));
			table.append(" ");

			for(int k=0;k<=maxCol-minCol;k++){
				table.append("|");

				if(board.get(j).get(k).equals(oldFillElem)){
					table.append(String.format("%3s",fillElem));
				}
				else{
					table.append(String.format("%3s", board.get(j).get(k)));
				}
				if(k==maxCol-minCol){
					table.append("|\n");
				}
			}
			table.append(" ");
			table.append("   ");
			for(int i=0;i<=maxCol-minCol;i++){
				table.append("+---");
				if(i==maxCol-minCol){
					table.append("+\n");
				}
			}

			j++;
			row++;


		}

		return table.toString();
	}


}
