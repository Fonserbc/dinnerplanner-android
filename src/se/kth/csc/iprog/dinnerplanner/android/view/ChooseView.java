package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.List;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.ChooseActivity;
import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.content.Context;
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
	private LinearLayout starterList;

	public ChooseView(View view, DinnerModel model, ChooseActivity ca, Context context) {

		// store in the class the reference to the Android View
		this.context = context;
		this.view = view;
		this.model = model;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		starterList = (LinearLayout) view.findViewById(R.id.starter_list);

		fillChooseList();

		

		//View dishPreview = inflater.inflate(R.layout.dish_preview, null);
		//starterList.addView(dishPreview);

		// Setup the rest of the view layout
		
	}
	
	
	private void fillChooseList (){
		Dish[] dishes = ((Dish[]) model.getDishesOfType(1).toArray());
		
		Dish dish = dishes[0];
		final View dishPreview = inflater.inflate(R.layout.dish_preview, null);
		TextView dishName = (TextView) dishPreview.findViewById(R.id.dish_name);
		dishName.setText(dish.getName());
		
		starterList.post(new Runnable(){
			public void run() {
				starterList.addView(dishPreview);
			}
		});
	}



}
