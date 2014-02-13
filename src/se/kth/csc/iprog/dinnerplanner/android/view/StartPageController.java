package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.ChooseActivity;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class StartPageController implements OnClickListener {
	Activity activity;
	private StartPageView view;

public StartPageController(Activity activity, StartPageView mainView, DinnerModel model){
	this.activity = activity;
	this.view = mainView;
	
	view.startButton.setOnClickListener(this);
  }

@Override
public void onClick(View v) {
	Intent intent = new Intent(activity.getBaseContext(),ChooseActivity.class);
	activity.startActivity(intent);	
	}
}