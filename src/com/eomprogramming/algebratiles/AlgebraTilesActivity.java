package com.eomprogramming.algebratiles;

import java.util.LinkedList;

import com.eomprogramming.algebratiles.math.QEquationGenerator;
import com.eomprogramming.algebratiles.model.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class AlgebraTilesActivity extends Activity implements OnClickListener {

	private LinkedList<Button> button;
	private LinkedList<TableRow> row;
	private GameState tileLayout;
	private TableLayout table, leftTable;
	private RowGroup rowgroup;
	private int sRow;
	private int sCol;
	private Button sPressed;
	
	private TableRow topRow;
	private LinkedList<Button> topButtons;
	private LinkedList<TableRow> leftSideRows;
	
	private final int SUBMIT = -1, CLEAR = -2, UNDO = -3; //button IDs; they are negative for a reason, keep them that way
	
	private final CharSequence[] items = {"X^2","-X^2", "X","-X", "1","-1"};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
				
		LinearLayout main_layout = new LinearLayout(this);
		main_layout.setOrientation(LinearLayout.VERTICAL);
		main_layout.setBackgroundColor(Color.rgb(60, 60, 60));
		
		TextView equationText = new TextView(this);
		equationText.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		equationText.setText(QEquationGenerator.generateRandom().toString());
		equationText.setBackgroundColor(Color.rgb(230, 230, 230));
		equationText.setTextColor(Color.rgb(60, 60, 60));
		equationText.setGravity(Gravity.CENTER);
		equationText.setTypeface(null,Typeface.BOLD);
		equationText.setTextSize(28);
		equationText.setPadding(0, 15, 0, 15);
		main_layout.addView(equationText);
		
		ScrollView verticalScroll = new ScrollView(this);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.weight = 1.0f;
		verticalScroll.setLayoutParams(params);
		
		HorizontalScrollView horizontalScroll = new HorizontalScrollView(this);
		horizontalScroll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		leftTable = new TableLayout(this);
		leftTable.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		leftTable.setPadding(5, 10, 0, 5);
		leftSideRows= new LinkedList<TableRow>();
		
		leftSideRows.add(new TableRow(this));
		leftSideRows.get(0).setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		leftSideRows.get(0).addView(getDisplayButton());		
		leftTable.addView(leftSideRows.get(0));
		
		leftSideRows.add(new TableRow(this));
		leftSideRows.getLast().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		leftSideRows.getLast().addView(getDisplayButton());
		leftTable.addView(leftSideRows.getLast());
		
		layout.addView(leftTable);
		
		table = new TableLayout(this);
		table.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		button = new LinkedList<Button>();
		row = new LinkedList<TableRow>();
								
		row.add(new TableRow(this));
		row.get(0).setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		button.add(getButton(0,0));		
		row.get(0).addView(button.get(0));
	
		topRow = new TableRow(this);
		topRow.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		topButtons = new LinkedList<Button>();
		topButtons.add(getDisplayButton());
		topRow.addView(topButtons.get(0));
		
		table.addView(topRow);
		table.addView(row.get(0));
		
		table.setPadding(0, 10, 10, 10);
		layout.addView(table);
		horizontalScroll.addView(layout);
		verticalScroll.addView(horizontalScroll);
		main_layout.addView(verticalScroll);
		
		tileLayout = new GameState();
					
		setContentView(main_layout);
		
		LinearLayout buttongroup_layout = new LinearLayout(this);
		buttongroup_layout.setBackgroundColor(Color.rgb(230, 230, 230));
		buttongroup_layout.setOrientation(LinearLayout.HORIZONTAL);
		buttongroup_layout.setGravity(Gravity.CENTER);
		buttongroup_layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				
		Button submitButton = new Button(this);
		submitButton.setText("Submit");
		submitButton.setPadding(10, 0,10, 10);
		submitButton.setTypeface(null,Typeface.BOLD);
		submitButton.setBackgroundColor(Color.rgb(230, 230, 230));
		submitButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.submit, 0, 0);
		submitButton.setTextColor(Color.rgb(60, 60, 60));
		submitButton.setOnClickListener(this);	
		submitButton.setId(SUBMIT);
		submitButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		buttongroup_layout.addView(submitButton);
		
		Button clearButton = new Button(this);
		clearButton.setText("Restart");
		clearButton.setPadding(10, 0,10, 10);
		clearButton.setTypeface(null,Typeface.BOLD);
		clearButton.setBackgroundColor(Color.rgb(230, 230, 230));
		clearButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.restart, 0, 0);
		clearButton.setTextColor(Color.rgb(60, 60, 60));
		clearButton.setOnClickListener(this);
		clearButton.setId(CLEAR);
		clearButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		buttongroup_layout.addView(clearButton);
		
		Button undoButton = new Button(this);
		undoButton.setText("Undo");
		undoButton.setPadding(10, 0,10, 10);
		undoButton.setTypeface(null,Typeface.BOLD);
		undoButton.setBackgroundColor(Color.rgb(230, 230, 230));
		undoButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.undo, 0, 0);
		undoButton.setTextColor(Color.rgb(60, 60, 60));
		undoButton.setOnClickListener(this);
		undoButton.setId(UNDO);
		undoButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		buttongroup_layout.addView(undoButton);
		
		main_layout.addView(buttongroup_layout);
		
		//Create the non-gui stuff
		rowgroup = new RowGroup();
    }
    
	public void onClick(View v) {
		if(v.getId() >= 0){
	    	sPressed = (Button)v;
	    	String pos = sPressed.getHint().toString();
	    	sRow = Integer.parseInt(pos.substring(0, pos.indexOf(",")));
	    	sCol = Integer.parseInt(pos.substring(pos.indexOf(",")+1, pos.length()));
	    	if(rowgroup.getRows().get(sRow).getTiles().get(sCol).getType() == Tile.PLUS)
	    	{
	    		this.showDialog(0);
	    	}
	    	else if(rowgroup.getRows().get(sRow).getTiles().get(sCol).getType() != Tile.EMPTY)
	    	{
	    		//remove button
	    	}
		}else if(v.getId() == SUBMIT){
			Toast.makeText(this, "Feature not available yet", Toast.LENGTH_SHORT).show();
		}else if(v.getId() == CLEAR){
			Intent i = getApplicationContext().getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName() );				
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
			startActivity(i);
			overridePendingTransition(0, 0);
		}else if(v.getId() == UNDO){
			Toast.makeText(this, "Feature not available yet", Toast.LENGTH_SHORT).show();
		}
	}

	private void updateButtons(LinkedList<Pos> pos, int in_row, int in_col) {
		for(Pos p : pos){
			
			Row templateRow = rowgroup.getRows().get(p.row);
			
			//CHECK RIGHT
			if(p.row == in_row && p.col > in_col){
				if(p.col < row.get(p.row).getChildCount()){
					button.add(getButton(p.row,p.col));
					row.get(p.row).removeViewAt(p.col);
					row.get(p.row).addView(button.get(button.size()-1),p.col);
				}else{
					button.add(getButton(p.row,p.col));
					row.get(p.row).addView(button.get(button.size()-1));
				}
				if(p.col == tileLayout.getPrevNumCols()+1){
					//Update helper text at top
					topButtons.add(getDisplayButton());
					topRow.addView(topButtons.getLast());
					updateTopDisplayText(p.col-1);
				}
			}
			
			//CHECK LEFT
			if(p.row == in_row && p.col < in_col){
				button.add(getButton(p.row,p.col));	
				row.get(p.row).removeViewAt(p.col);
				row.get(p.row).addView(button.get(button.size()-1),p.col);		
			}
			
			//CHECK BOTTOM
			if(p.row > in_row && p.col == in_col){
				//Check if we have to add a row
				if(p.row >= row.size()){
					row.add(new TableRow(this));
					row.getLast().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					for(int i = 0; i <= p.col; i++){					
						if(templateRow.getTiles().get(i).getType() == Tile.PLUS){	
							button.add(getButton(p.row,p.col));	
							row.getLast().addView(button.getLast());
						}else{
							//If it's an empty block, add a place holding view
							row.getLast().addView(getDisplayButton());
						}
					}				
					table.addView(row.getLast());
					
				}else{
					while(row.get(p.row).getChildCount() < p.col){
						if(templateRow.getTiles().get(row.get(p.row).getChildCount()).getType() == Tile.EMPTY){
							row.get(p.row).addView(getDisplayButton());
						}
					}
						
					button.add(getButton(p.row,p.col));	

					if(row.get(p.row).getChildCount() > p.col){
						if((row.get(p.row).getChildAt(p.col).getId() == View.NO_ID)){
							row.get(p.row).removeViewAt(p.col);
							row.get(p.row).addView(button.get(button.size()-1),p.col);
						}
					}else{
						row.get(p.row).addView(button.get(button.size()-1));		
					}
				}
				if(p.row == tileLayout.getPrevNumRows()+1){
					//update factors on the left
					leftSideRows.add(new TableRow(this));
					leftSideRows.getLast().setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					leftSideRows.getLast().addView(getDisplayButton());
					leftTable.addView(leftSideRows.getLast());
					this.updateLeftDisplayText(p.row-1);
				}
			}
			
			//CHECK TOP
			if(p.row < in_row && p.col == in_col){
				if(p.col >= row.get(p.row).getChildCount()){
					for(int i=row.get(p.row).getChildCount();i<p.col;i++){
						View view = new View(this);
						row.get(p.row).addView(view);
					}						
					button.add(getButton(p.row,p.col));	
					row.get(p.row).addView(button.get(button.size()-1));					
				}else{
					button.add(getButton(p.row,p.col));
					row.get(p.row).removeViewAt(p.col);
					row.get(p.row).addView(button.get(button.size()-1),p.col);		
				}
				
			}
			
		}		
		if(pos.size() != 0)
			table.invalidate();
	}
	
	private Button getButton(int r, int c){
		Button b = new Button(this);
		b.setText("+");
		b.setPadding(10, 10,10, 10);
		b.setHint(r+","+c);		
		b.setTextSize(18);
		b.setTypeface(null,Typeface.BOLD);
		b.setBackgroundColor(Color.rgb(181,230,21));		
		b.setId(button.size());
		b.setOnClickListener(this);	
		b.setGravity(Gravity.CENTER);
		TableRow.LayoutParams bparams = new TableRow.LayoutParams(SIZE,LayoutParams.FILL_PARENT);
		bparams.setMargins(5, 5, 5, 5);		
		b.setLayoutParams(bparams);
				
		return b;
	}
	

    private Button getDisplayButton() {
    	Button b = new Button(this);
		b.setText(" ");
		b.setPadding(10, 10,10, 10);
		b.setTextSize(18);
		b.setTypeface(null,Typeface.BOLD);
		b.setBackgroundColor(Color.rgb(60, 60, 60));		
		b.setGravity(Gravity.CENTER);
		TableRow.LayoutParams bparams = new TableRow.LayoutParams(SIZE,LayoutParams.FILL_PARENT);
		bparams.setMargins(5, 5, 5, 5);		
		b.setLayoutParams(bparams);
				
		return b;
	}
	
    private void updateTopDisplayText(int i){
    		topButtons.get(i).setBackgroundColor(Color.rgb(100, 100, 100));	
			String text = "";
			
			if(!tileLayout.getColSign().get(i))
				text+="-";
			
    		if(tileLayout.getColType().get(i)==Tile.X)
    			text+="X";
    		else
    			text+="1";
    			
    		topButtons.get(i).setText(text);
    }
    
    private void updateLeftDisplayText(int i){
    		Button sideButton = (Button) leftSideRows.get(i+1).getChildAt(0);
			sideButton.setBackgroundColor(Color.rgb(100, 100, 100));	
			String text = "";
			TableRow.LayoutParams bparams;
			
			if(!tileLayout.getRowSign().get(i))
				text+="-";
			
    		if(tileLayout.getRowType().get(i)==Tile.X){
    			text+="X";
    			bparams = new TableRow.LayoutParams(SIZE,SIZE*2);
	    		bparams.setMargins(5, 5, 5, 5);	
	    		sideButton.setLayoutParams(bparams);	    			
    		}else{
    			text+="1";
    			bparams = new TableRow.LayoutParams(SIZE,SIZE);
	    		bparams.setMargins(5, 5, 5, 5);	
	    		sideButton.setLayoutParams(bparams);	    		    			
    		}
    		
    		sideButton.setText(text);
    }
    
	public final int SIZE = 60;
	
	public void setButton(int r, int c, Button b, int type){
		TableRow.LayoutParams bparams;
		if(rowgroup.getRows().get(r).getTiles().get(c).isPositive())
			b.setBackgroundColor(Color.rgb(237,28,36));
		else
			b.setBackgroundColor(Color.rgb(0,162,232));
		
		switch(type){
			case Tile.ONE:
				bparams = new TableRow.LayoutParams(SIZE,SIZE);
				bparams.setMargins(5, 5, 5, 5);		
				b.setLayoutParams(bparams);
				break;
			case Tile.X:
				if(tileLayout.isHorizontal(r, c))
					bparams = new TableRow.LayoutParams(SIZE*2,SIZE);
				else
					bparams = new TableRow.LayoutParams(SIZE,SIZE*2);
				bparams.setMargins(5, 5, 5, 5);		
				b.setLayoutParams(bparams);
				break;
			case Tile.X_SQUARED:
				bparams = new TableRow.LayoutParams(SIZE*2,SIZE*2);
				bparams.setMargins(5, 5, 5, 5);		
				b.setLayoutParams(bparams);
				break;
		}
		
	}
	
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick a tile");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		    	int type = item/2; //get information from user
		    	boolean isPositive = item%2==0; //get information from user
		    	Log.d("a-t", sRow+","+sCol);
		    	if(tileLayout.isValid(sRow, sCol, type, isPositive))
		    	{
		    		tileLayout.add(sRow, sCol, type, isPositive);
		    		rowgroup.addTile(sRow, sCol, new Tile(type, isPositive));
					button.get(sPressed.getId()).setText(Tile.getSymbol(type));
					updateButtons(rowgroup.updatePlusTiles(sRow, sCol),sRow,sCol);
					setButton(sRow, sCol, sPressed, type);					
		    	}else{
		    		Toast.makeText(getApplicationContext(), "Can't place selected tile.", Toast.LENGTH_LONG).show();
		    	}
		    }
		});
		AlertDialog alert = builder.create();
		return alert;
	}
	
}