package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.ChooseActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChooseView {

	public View view;
	private DinnerModel model;
	private Context context;
	private LayoutInflater inflater;

	public ChooseView(Context context, View view, DinnerModel model) {

		// store in the class the reference to the Android View
		this.context = context;
		this.view = view;
		this.model = model;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		fillChooseList();
		

		//View dishPreview = inflater.inflate(R.layout.dish_preview, null);
		//starterList.addView(dishPreview);

		// Setup the rest of the view layout
		
	}
	
	
	private void fillChooseList (){
		LinearLayout starterList = (LinearLayout) view.findViewById(R.id.starter_list);
		
		Set<Dish> dishes = model.getDishesOfType(1);
		
		for (Iterator<Dish> it = dishes.iterator(); it.hasNext(); ) {
			Dish dish = it.next();
			
			View dishPreview = inflater.inflate(R.layout.dish_preview, null);
			TextView dishName = (TextView) dishPreview.findViewById(R.id.dish_name);
			dishName.setText(dish.getName());
		
			starterList.addView(dishPreview);
		}
	}



}
