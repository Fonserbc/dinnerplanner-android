package se.kth.csc.iprog.dinnerplanner.android;


import java.util.HashMap;
import java.util.Map;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class ChooseActivity extends Activity {

	private DinnerModel model;
	private ChooseView chooseView;
	private Map<String,Dish> nameToDish = new HashMap<String,Dish>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		
		setContentView(R.layout.activity_choose);

		chooseView = new ChooseView(getBaseContext(), findViewById(R.layout.activity_choose), model, this);

		Button createButton = (Button) findViewById(R.id.menu_create);
	    createButton.setOnClickListener
	    (new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//storeDinnerParticipants();
				
				Intent intent = new Intent(getBaseContext(),MenuActivity.class);
				startActivity(intent);
			}
	    }
	    );
		EditText numberView = (EditText) findViewById(R.id.number_participants);
		numberView.setOnEditorActionListener
		(new OnEditorActionListener(){
			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				if(arg1 == EditorInfo.IME_ACTION_DONE) {
					storeDinnerParticipants();
					//this should be in view in the future
					TextView totalCost = (TextView) findViewById(R.id.text_cost_amount);
					totalCost.setText(Float.toString(model.getTotalMenuPrice()) + " SEK");
					return false;
				}
				return false;
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose, menu);
		return true;
	}
	
	public void storeDinnerParticipants() {
		EditText numberView = (EditText) findViewById(R.id.number_participants);
		if (numberView.getText().length() > 0){
			model.setNumberOfGuests(Integer.parseInt(numberView.getText().toString()));
		}
		else{
			numberView.setText("1");
			model.setNumberOfGuests(Integer.parseInt(numberView.getText().toString()));
		}
	}
	
	public void registerDishToButton(Dish d, ImageButton b) {
		nameToDish.put(d.getName(), d);
		
		b.setOnClickListener
	    (new ImageButton.OnClickListener(){
			@Override
			public void onClick(View vi) {
				dishDialog(nameToDish.get(((TextView)((View) vi.getParent()).findViewById(R.id.dish_name)).getText()));
			}
	    }
	    );
	}
	
	public void dishDialog(Dish d) {
		// Prepare Layout
		View dishInfo = chooseView.createDishInfo(d);
		
		// Build alert
		
		AlertDialog.Builder builder = new AlertDialog.Builder(ChooseActivity.this);
		builder.setView(dishInfo);
		
		AlertDialog alert = builder.create();
		
		alert.show();
	}
}
