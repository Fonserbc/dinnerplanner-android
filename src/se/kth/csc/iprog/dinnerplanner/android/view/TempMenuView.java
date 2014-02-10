package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.view.View;
import android.widget.TextView;

public class TempMenuView {
	public View view;
	protected DinnerModel model;
	
	public TempMenuView(View view, DinnerModel model) {
		// store in the class the reference to the Android View
		this.view = view;
		this.model = model;
		
		System.out.println("hej");
		System.out.println("hej");
		System.out.println("hej");
		int i = 1;
		FillView(i);
	}
	
	public void FillView(int type){
		Dish dish = model.getSelectedDish(type);
		System.out.println(dish);
		//TextView dishname = (TextView) view.findViewById(R.id.text_name_of_dish);
		//dishname.setText(dish.getName());
		
		//fillInstructionsList();

		// Setup the rest of the view layout
		
	}
}
