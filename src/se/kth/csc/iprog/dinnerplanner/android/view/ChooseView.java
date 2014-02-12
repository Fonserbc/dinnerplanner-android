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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChooseView {

	public View view;
	private DinnerModel model;
	private Context context;
	private ChooseActivity activity;
	private LayoutInflater inflater;

	public ChooseView(Context context, View view, DinnerModel model, ChooseActivity activity) {

		// store in the class the reference to the Android View
		this.context = context;
		this.view = view;
		this.model = model;
		this.activity = activity;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		
		getDishList();
		
		//TextView totalCost = (TextView) view.findViewById(R.id.text_cost_amount);
		//totalCost.setText(Float.toString(model.getTotalMenuPrice()) + " SEK");
		
	}
	
	private void getDishList(){
		LinearLayout dishList = (LinearLayout) view.findViewById(R.id.starter_list);
		fillDishList(dishList, 1);
		
		LinearLayout dishList2 = (LinearLayout) view.findViewById(R.id.main_list);
		fillDishList(dishList2, 2);

		LinearLayout dishList3 = (LinearLayout) view.findViewById(R.id.dessert_list);
		fillDishList(dishList3, 3);
	}
	
	private void fillDishList (LinearLayout dishList, int dishType){
		Set<Dish> dishes = model.getDishesOfType(dishType);
		
		for (Iterator<Dish> it = dishes.iterator(); it.hasNext(); ) {
			Dish dish = it.next();
			
			View dishPreview = inflater.inflate(R.layout.dish_preview, null);
			TextView dishName = (TextView) dishPreview.findViewById(R.id.dish_name);
			dishName.setText(dish.getName());
			
			ImageButton dishImg = (ImageButton) dishPreview.findViewById(R.id.dish_photo);
			//Log.v("hej", R.drawable.toast+" and get "+context.getResources().getIdentifier(dish.getImage(), "drawable", context.getPackageName()));
			dishImg.setImageResource(context.getResources().getIdentifier(dish.getImage(), "drawable", context.getPackageName()));
		
			activity.registerDishToButton(dish, dishImg);
			
			dishList.addView(dishPreview);			
		}
	}
	
	public View createDishInfo (Dish d) {
		View layout = inflater.inflate(R.layout.dish_info, null);
		
		((TextView) layout.findViewById(R.id.dish_info_name)).setText(d.getName());
		((ImageView) layout.findViewById(R.id.dish_info_image)).setImageResource(context.getResources().getIdentifier(d.getImage(), "drawable", context.getPackageName()));
		float dishPrice = model.getDishPrice(d);
		((TextView) layout.findViewById(R.id.dish_info_cost_total)).setText(String.format("%.1f", dishPrice) + " SEK");
		((TextView) layout.findViewById(R.id.dish_info_cost_person)).setText("("+String.format("%.1f", dishPrice/(float)model.getNumberOfGuests()) + " SEK / Person)");
		
		return layout;
	}
}
