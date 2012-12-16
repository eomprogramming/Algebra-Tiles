package com.eomprogramming.algebratiles;

import java.util.InputMismatchException;

import com.eomprogramming.algebratiles.math.QEquation;
import com.eomprogramming.algebratiles.math.QEquationGenerator;
import com.eomprogramming.algebratiles.model.GameState;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EquationInputActivity extends Activity implements OnClickListener {
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.equation_input);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			
		Log.d("a-t", "Equation Input Page Loaded.");
		
		
		Button b = (Button)findViewById(R.id.button1);
		b.setId(1);
		b.setOnClickListener(this);		
	}

	public void onClick(View v) {
		if(v.getId() == 1)
		{
			EditText ea = (EditText)findViewById(R.id.edit_a);
			EditText eb = (EditText)findViewById(R.id.edit_b);
			EditText ec = (EditText)findViewById(R.id.edit_c);
			
			try
			{
				int a = Integer.parseInt(ea.getText().toString());
				int b = Integer.parseInt(eb.getText().toString());
				int c = Integer.parseInt(ec.getText().toString());
				if(QEquation.factor(a, b, c) != null)
				{
					GameState.q = QEquation.factor(a, b, c);
					Intent i = getApplicationContext().getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName() );				
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
					startActivity(i);
					overridePendingTransition(0, 0);
				}
				else
				{
					Toast.makeText(this, "Invalid Equation. Check to see if it has integer factors.", Toast.LENGTH_SHORT).show();
				}
			}catch (Exception e)
			{
				if(ea.getText().toString().length() + eb.getText().toString().length() + ec.getText().toString().length() == 0)
				{
					GameState.q = QEquationGenerator.generateRandom();
					Intent i = getApplicationContext().getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName() );				
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
					startActivity(i);
					overridePendingTransition(0, 0);
				}
				else
					Toast.makeText(this, "Please fill all the boxes.", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
}

