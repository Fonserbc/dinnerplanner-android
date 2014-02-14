package se.kth.csc.iprog.dinnerplanner.android.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import se.kth.csc.iprog.dinnerplanner.android.MenuActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
	

public class ChooseViewController implements OnClickListener {
	DinnerModel model;
	ChooseView view;
	Activity activity;
	
	
	
	public ChooseViewController(Activity activity,DinnerModel model, ChooseView view){
		this.activity = activity;
		this.model = model;
		this.view = view;
		
		view.createButton.setOnClickListener(this);
		//view.cancelButton.setOnClickListener(this);

	}
	
	@Override	
	public void onClick(View v) {
		//storeDinnerParticipants();
		//hej
		/*switch (v.getId()){
		case R.id.menu_create:*/
			Intent intent = new Intent(activity.getBaseContext(), MenuActivity.class);
			activity.startActivity(intent);
			/*break;
		case R.id.dish_info_cancel:
			//close popup
			break;
		}*/
	}
	
	

		
		

}