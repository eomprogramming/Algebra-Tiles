package com.eomprogramming.algebratiles;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class EquationInputActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.equation_input);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			
		Log.d("a-t", "Equation Input Page Loaded.");
	}
}

