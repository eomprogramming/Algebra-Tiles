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
	private TileLayout tileLayout;
	private TableLayout table;
	private RowGroup rowgroup;
	private ScrollView verticalScroll;
	int sRow;
	int sCol;
	Button sPressed;
	
	final CharSequence[] items = {"X^2","-X^2", "X","-X", "1","-1"};
	
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
		
		verticalScroll = new ScrollView(this);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.weight = 1.0f;
		verticalScroll.setLayoutParams(params);
		
		HorizontalScrollView horizontalScroll = new HorizontalScrollView(this);
		horizontalScroll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		table = new TableLayout(this);
		table.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		button = new LinkedList<Button>();
		row = new LinkedList<TableRow>();
								
		row.add(new TableRow(this));
		row.get(0).setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		button.add(getButton(0,0));
		
		row.get(0).addView(button.get(0));
	
		table.addView(row.get(0));
		
		table.setPadding(10, 30, 10, 20);
		layout.addView(table);
		horizontalScroll.addView(layout);
		verticalScroll.addView(horizontalScroll);
		main_layout.addView(verticalScroll);
		
		tileLayout = new TileLayout();
					
		setContentView(main_layout);
		
		Button clearButton = new Button(this);
		clearButton.setText("Restart");
		clearButton.setPadding(10, 10,10, 10);
		clearButton.setTextSize(18);
		clearButton.setTypeface(null,Typeface.BOLD);
		clearButton.setBackgroundColor(Color.rgb(230, 230, 230));
		clearButton.setTextColor(Color.rgb(60, 60, 60));
		clearButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent i = getApplicationContext().getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName() );				
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
				startActivity(i);
				overridePendingTransition(0, 0);
			}			
		});	
		clearButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		
		main_layout.addView(clearButton);
		
		//Create the non-gui stuff
		rowgroup = new RowGroup();
    }
    
    public void onClick(View v) {
    	sPressed = (Button)v;
    	String pos = sPressed.getHint().toString();
    	sRow = Integer.parseInt(pos.substring(0, pos.indexOf(",")));
    	sCol = Integer.parseInt(pos.substring(pos.indexOf(",")+1, pos.length()));
    	if(rowgroup.getRows().get(sRow).getTiles().get(sCol).getType() == Tile.PLUS)
    	{
    		this.showDialog(0);
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
					row.get(row.size()-1).setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
					for(int i = 0; i <= p.col; i++){					
						if(templateRow.getTiles().get(i).getType() == Tile.PLUS){	
							button.add(getButton(p.row,p.col));	
							row.get(p.row).addView(button.get(button.size()-1));
						}else{
							//If it's an empty block, add a place holding view
							View view = new View(this);
							row.get(p.row).addView(view);
						}
					}				
					table.addView(row.get(p.row));
					
				}else{
					while(row.get(p.row).getChildCount() < p.col){
						if(templateRow.getTiles().get(row.get(p.row).getChildCount()).getType() == Tile.EMPTY){
							View view = new View(this);
							row.get(p.row).addView(view);
						}
					}
						
					button.add(getButton(p.row,p.col));	

					if(row.get(p.row).getChildCount() > p.col){
						if(!(row.get(p.row).getChildAt(p.col) instanceof Button)){
							row.get(p.row).removeViewAt(p.col);
							row.get(p.row).addView(button.get(button.size()-1),p.col);
						}
					}else{
						row.get(p.row).addView(button.get(button.size()-1));		
					}
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
		    		rowgroup.addTile(sRow, sCol, new Tile(type, isPositive));
					button.get(sPressed.getId()).setText(Tile.getSymbol(type));
					updateButtons(rowgroup.updatePlusTiles(sRow, sCol),sRow,sCol);
					setButton(sRow, sCol, sPressed, type);
					
					tileLayout.add(sRow, sCol, type, isPositive);
		    	}else{
		    		Toast.makeText(getApplicationContext(), "Can't place selected tile.", Toast.LENGTH_SHORT).show();
		    	}
		    }
		});
		AlertDialog alert = builder.create();
		return alert;
	}
	
}