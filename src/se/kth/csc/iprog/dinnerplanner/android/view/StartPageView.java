package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartPageView {

	public View view;
	private DinnerModel model;
	Button startButton;

	public StartPageView(View view, DinnerModel model) {

		this.view = view;
		this.model = model;
		
		startButton = (Button) view.findViewById(R.id.button_start);
	}

}
