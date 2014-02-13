package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class DishInfoViewController implements OnClickListener {
	
	private Activity activity;
	private View view;
	private DinnerModel model;
	
	public DishInfoViewController (Activity activity, View view, DinnerModel model) {
		this.activity = activity;
		this.view = view;
		this.model = model;
		
		
	}

	@Override
	public void onClick(View v) {
		
	}

}
