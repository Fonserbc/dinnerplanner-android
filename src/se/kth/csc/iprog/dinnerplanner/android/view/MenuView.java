package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.view.View;
import android.widget.TextView;

public class MenuView {

	public View view;
	private DinnerModel model;

	public MenuView(View view, DinnerModel model) {

		// store in the class the reference to the Android View
		this.view = view;
		this.model = model;

		//TextView start = (TextView) view.findViewById(R.id.start_page);
		//example.setText("Hello world");

		// Setup the rest of the view layout
		
	}

}
