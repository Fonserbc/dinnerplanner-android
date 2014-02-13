package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.ChooseActivity;
import se.kth.csc.iprog.dinnerplanner.android.MainActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class StartPageController {
	final MainActivity activity;

public StartPageController(final MainActivity activity, View mainView, DinnerModel model){
	this.activity = activity;
	Button startButton = (Button) mainView.findViewById(R.id.button_start);
    startButton.setOnClickListener
    (new Button.OnClickListener(){
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity.getBaseContext(),ChooseActivity.class);
			activity.startActivity(intent);
		}
    }
    );
  }
}