package com.eomprogramming.algebratiles;

import java.util.LinkedList;

import com.eomprogramming.algebratiles.model.Pos;
import com.eomprogramming.algebratiles.model.Row;
import com.eomprogramming.algebratiles.model.RowGroup;
import com.eomprogramming.algebratiles.model.Tile;

import android.app.Activity;
import android.content.pm.ActivityInfo;
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

public class AlgebraTilesActivity extends Activity implements OnClickListener {

	private LinkedList<Button> button;
	LinkedList<TableRow> row;
	private TableLayout table;
	private RowGroup rowgroup;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
				
		LinearLayout main_layout = new LinearLayout(this);
		main_layout.setOrientation(LinearLayout.VERTICAL);
		
		ScrollView v_sv = new ScrollView(this);
		v_sv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		HorizontalScrollView sv = new HorizontalScrollView(this);
		sv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		table = new TableLayout(this);		
		
		button = new LinkedList<Button>();
		row = new LinkedList<TableRow>();
					
		TableRow.LayoutParams bparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
		bparams.setMargins(5, 5, 5, 5);
		
		
		row.add(new TableRow(this));
//		row.get(0).setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
		
		button.add(new Button(this));
		button.get(0).setText("+");
		button.get(0).setPadding(10, 10,10, 10);
		button.get(0).setId(0);
//		button.get(0).setTextSize(32);	
		button.get(0).setHint("0,0");
		button.get(0).setOnClickListener(this);
		
		row.get(0).addView(button.get(0));
	
		table.addView(row.get(0));
		
		table.setPadding(10, 30, 10, 20);
		layout.addView(table);
		sv.addView(layout);
		v_sv.addView(sv);
		main_layout.addView(v_sv);
		
		setContentView(main_layout);
		
		//Create the non-gui stuff
		rowgroup = new RowGroup();
    }
    
    @Override
	public void onClick(View v) {
    	Button pressed = (Button)v;
    	String pos = pressed.getHint().toString();
    	int x = Integer.parseInt(pos.substring(0, pos.indexOf(",")));
    	int y = Integer.parseInt(pos.substring(pos.indexOf(",")+1, pos.length()));
    	rowgroup.addTile(x, y, new Tile(Tile.X,true));
		
		button.get(v.getId()).setText("x");
		
		updateButtons(rowgroup.updatePlusTiles(x, y),x,y);
		/*
		for(Row r : rowgroup.getRows()){
						
			if(r.getPosition() < row.size() ){
				
				int toAdd = r.getTiles().size() - row.get(r.getPosition()).getChildCount();
				
				Log.d("a-t", "Adding "+toAdd+" buttons to "+r.getPosition());
				
				for(int i = 0; i < toAdd; i++){
																
					button.add(new Button(this));
					button.get(button.size()-1).setText("+");
					button.get(button.size()-1).setPadding(10, 10,10, 10);
					button.get(button.size()-1).setHint(r.getPosition()+","+row.get(r.getPosition()).getChildCount());
					button.get(button.size()-1).setId(button.size()-1);
					button.get(button.size()-1).setOnClickListener(this);	
					row.get(r.getPosition()).addView(button.get(button.size()-1));					
			
				}	
				
			}else{
				
				row.add(new TableRow(this));
		//		row.get(row.size()-1).setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
				
				for(int i = 0; i < r.getTiles().size(); i++){
					
					if(r.getTiles().get(i).getType() == Tile.PLUS){					
						button.add(new Button(this));
						button.get(button.size()-1).setText("+");
						button.get(button.size()-1).setPadding(10, 10,10, 10);
						button.get(button.size()-1).setHint((row.size()-1)+","+(row.get(row.size()-1).getChildCount()));
						button.get(button.size()-1).setId(button.size()-1);
						button.get(button.size()-1).setOnClickListener(this);	
						row.get(row.size()-1).addView(button.get(button.size()-1));
					}else{
						View view = new View(this);
						row.get(row.size()-1).addView(view);
					}
				}
			
				table.addView(row.get(row.size()-1));				
			}
			table.invalidate();
			
		}
		*/
	}

	private void updateButtons(LinkedList<Pos> pos, int in_row, int in_col) {
		for(Pos p : pos){
			
			Row templateRow = rowgroup.getRows().get(p.getRow());
			
			//CHECK RIGHT
			if(p.row == in_row && p.col > in_col){
				
			}
			
			//CHECK LEFT
			if(p.row == in_row && p.col < in_col){
				
			}
			
			//CHECK BOTTOM
			if(p.row > in_row && p.col < in_col){
				
			}
			//Check if we have to add a row
			if(p.row > row.size()){
				row.add(new TableRow(this));
				
				for(int i = 0; i < p.col; i++){					
					if(templateRow.getTiles().get(i).getType() == Tile.PLUS){					
						button.add(new Button(this));
						button.get(button.size()-1).setText("+");
						button.get(button.size()-1).setPadding(10, 10,10, 10);
						button.get(button.size()-1).setHint(p.row+","+p.col);
						button.get(button.size()-1).setId(button.size()-1);
						button.get(button.size()-1).setOnClickListener(this);	
						row.get(row.size()-1).addView(button.get(button.size()-1));
					}else{
						//If it's an empty block, add a place holding view
						View view = new View(this);
						row.get(row.size()-1).addView(view);
					}
				}				
				table.addView(row.get(row.size()-1));
				
			}else{
				
				
				
			}
		}
		
		if(pos.size() != 0)
			table.invalidate();
	}
}