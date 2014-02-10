package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.ChooseActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseView {

	public View view;
	private DinnerModel model;

	public ChooseView(View view, DinnerModel model, ChooseActivity ca) {

		// store in the class the reference to the Android View
		this.view = view;
		this.model = model;

		//TextView start = (TextView) view.findViewById(R.id.start_page);
		//example.setText("Hello world");

		
		
		
		
		LinearLayout starterList = (LinearLayout) view.findViewById(R.id.starter_list);
		View dishPreview = ca.getLayoutInflater().inflate(R.layout.dish_preview, null);
		starterList.addView(dishPreview);

		// Setup the rest of the view layout
		
	}



}
