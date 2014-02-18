package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class MenuViewController implements OnClickListener {
	
	private Activity activity;
	private DinnerModel model;
	private MenuView view;

	public MenuViewController (Activity activity, DinnerModel model, MenuView view) {
		this.activity = activity;
		this.model = model;
		this.view = view;
		
		this.view.backButton.setOnClickListener(this);		
		this.view.ingredientsButton.setOnClickListener(this);
		
		//checking if dishes are selected, you can only click on seleced dishes
		if(view.starterButton != null){
			view.starterButton.setOnClickListener(this);	
		}
		
		if(view.mainButton != null){
			view.mainButton.setOnClickListener(this);
		}
		
		if(view.dessertButton != null){
			view.dessertButton.setOnClickListener(this);
		}
	}
	
	@Override
	public void onClick(View v) {
		if (v == view.backButton) {
			activity.finish();
		}
		else if (v == view.ingredientsButton){
			view.changeView(0);
		}
		else if (v == view.starterButton){
			view.changeView(1);
		}
		else if (v == view.mainButton){
			view.changeView(2);
		}
		else if (v == view.dessertButton){
			view.changeView(3);
		}
	}

}
