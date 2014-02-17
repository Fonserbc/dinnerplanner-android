package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import se.kth.csc.iprog.dinnerplanner.android.MenuActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
	

public class ChooseViewController implements OnClickListener, OnEditorActionListener{
	DinnerModel model;
	ChooseView view;
	Activity activity;
	private Map<String,Dish> nameToDish = new HashMap<String,Dish>();
	private ArrayList<ImageButton> buttomList = new ArrayList<ImageButton>();
	
	
	public ChooseViewController(Activity activity,DinnerModel model, ChooseView view){
		this.activity = activity;
		this.model = model;
		this.view = view;

		view.createButton.setOnClickListener(this);
		//view.cancelButton.setOnClickListener(this);
		
		view.numberView.setOnEditorActionListener(this);
		
		nameToDish = view.nameToDish;
		buttomList = view.buttomList;
		for (ImageButton i : buttomList){
			i.setOnClickListener
		    (new ImageButton.OnClickListener(){
				@Override
				public void onClick(View vi) {
					dishDialog(nameToDish.get(((TextView)((View) vi.getParent()).findViewById(R.id.dish_name)).getText()));
				}
		    }
		    );						
		}		
	}
	
	@Override	
	public void onClick(View v) {
		//storeDinnerParticipants();
		/*
		switch (v.getId()){
		case R.id.menu_create:
			Intent intent = new Intent(activity.getBaseContext(), MenuActivity.class);
			activity.startActivity(intent);
			break;
			*/
			
		if (v.getId() == R.id.menu_create){
			Intent intent = new Intent(activity.getBaseContext(), MenuActivity.class);
			activity.startActivity(intent);
		}
	}
		/*
		case R.id.dish_info_cancel:
			//close popup
			break;
		}*/
		
	//}
	

	public boolean onEditorAction(TextView totalCost, int arg1, KeyEvent arg2) {
		if(arg1 == EditorInfo.IME_ACTION_DONE) {
			if(view.numberView.getText().length() >0){
				model.setNumberOfGuests(Integer.parseInt(view.numberView.getText().toString()) );
			}
			else{
				view.numberView.setText("1");
				model.setNumberOfGuests(Integer.parseInt(view.numberView.getText().toString()) );
			}

			return false;
		}
		return false;
	}
	
	public void dishDialog(Dish d) {
		// Prepare Layout
		View dishInfo = view.createDishInfo(d);
		
		// Build alert
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setView(dishInfo);
		
		AlertDialog alert = builder.create();
		
		alert.show();
	}
}