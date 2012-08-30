package com.eomprogramming.algebratiles;

import java.util.LinkedList;

import com.eomprogramming.algebratiles.model.*;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AlgebraTilesActivity extends Activity implements OnClickListener {

	private LinkedList<Button> button;
	LinkedList<TableRow> row;
	private TableLayout table;
	private RowGroup rowgroup;
	private ScrollView verticalScroll;
	private SlidingDrawer slidingDrawer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
				
		LinearLayout main_layout = new LinearLayout(this);
		main_layout.setOrientation(LinearLayout.VERTICAL);
		
		verticalScroll = new ScrollView(this);
		verticalScroll.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
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
		

		// add sliding Drawer
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		slidingDrawer = (SlidingDrawer)inflater.inflate(R.layout.sliding_drawer, main_layout, false);
		main_layout.addView(slidingDrawer);

		// get Layout for place your content in sliding drawer
		LinearLayout slideContent = (LinearLayout)slidingDrawer.findViewById(R.id.content);
		TextView instText = new TextView(this);
		instText.setText("Pick a Tile: ");
		slideContent.addView(instText);
		
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
    	
    	if(rowgroup.getRows().get(x).getTiles().get(y).getType() == Tile.PLUS){

   //     	slidingDrawer.animateOpen();    		
  //  		verticalScroll.setVisibility(ScrollView.GONE);    		
	    	
	    	rowgroup.addTile(x, y, new Tile(Tile.X,true));
			
			button.get(v.getId()).setText("x^2");
			updateButtons(rowgroup.updatePlusTiles(x, y),x,y);
			setButton(pressed, Tile.X_SQUARED);
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
		b.setBackgroundColor(Color.rgb(200,0,0));		
		b.setId(button.size());
		b.setOnClickListener(this);	
		b.setGravity(Gravity.CENTER);
		
		TableRow.LayoutParams bparams = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT);
		bparams.setMargins(5, 5, 5, 5);		
		b.setLayoutParams(bparams);
				
		return b;
	}
	
	public final int SIZE = 60;
	
	public void setButton(Button b, int type){
		TableRow.LayoutParams bparams;
		switch(type){
			case Tile.ONE:
				bparams = new TableRow.LayoutParams(SIZE,SIZE);
				b.setLayoutParams(bparams);
				break;
			case Tile.X_SQUARED:
				bparams = new TableRow.LayoutParams(SIZE*2,SIZE*2);
				b.setLayoutParams(bparams);
				break;
		}
		
	}
}