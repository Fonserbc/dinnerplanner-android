package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.view.View;
import android.widget.TextView;

public class StartPageView {

	public View view;
	private DinnerModel model;

	public StartPageView(View view, DinnerModel model) {

		this.view = view;
		this.model = model;

		TextView start = (TextView) view.findViewById(R.id.start_page);
	}

}
