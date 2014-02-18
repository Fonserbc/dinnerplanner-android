package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseView;
import se.kth.csc.iprog.dinnerplanner.android.view.ChooseViewController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;

public class ChooseActivity extends Activity {

	private DinnerModel model;
	private ChooseView chooseView;
	private ChooseViewController chooseCtrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		
		setContentView(R.layout.activity_choose);

		chooseView = new ChooseView(getBaseContext(), findViewById(R.layout.activity_choose), model);
		
		chooseCtrl = new ChooseViewController(this,model,chooseView);
	}
}
