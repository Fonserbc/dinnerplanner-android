package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseView implements Observer{

	public View view;
	private DinnerModel model;
	private Context context;
	private LayoutInflater inflater;
	public Button createButton;
	ImageButton cancelButton;
	Button chooseButton;
	public EditText numberView;
	Map<String,Dish> nameToDish = new HashMap<String,Dish>();
    ArrayList<ImageButton> buttomList = new ArrayList<ImageButton>();
    Drawable defaultDishBackground;
	

	public ChooseView(Context context, View view, DinnerModel model) {

		// store in the class the reference to the Android View
		this.context = context;
		this.view = view;
		this.model = model;
		//this.activity = activity;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		model.addObserver(this);

		createButton = (Button) view.findViewById(R.id.menu_create);
		
		getDishList();
		
		numberView = (EditText) view.findViewById(R.id.number_participants);
		
	}
	
	private void setTotalCost(){
		TextView totalCost = (TextView) view.findViewById(R.id.text_cost_amount);
		totalCost.setText(Float.toString(model.getTotalMenuPrice()) + " SEK");
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
			if (defaultDishBackground == null) defaultDishBackground = dishPreview.getBackground();
			TextView dishName = (TextView) dishPreview.findViewById(R.id.dish_name);
			dishName.setText(dish.getName());
			
			ImageButton dishImg = (ImageButton) dishPreview.findViewById(R.id.dish_photo);
			//Log.v("hej", R.drawable.toast+" and get "+context.getResources().getIdentifier(dish.getImage(), "drawable", context.getPackageName()));
			dishImg.setImageResource(context.getResources().getIdentifier(dish.getImage(), "drawable", context.getPackageName()));
		    
			//adding dish and buttons that the controller will get
			buttomList.add(dishImg);
			nameToDish.put(dish.getName(), dish);
			//activity.registerDishToButton(dish, dishImg);
			
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
		cancelButton = (ImageButton) layout.findViewById(R.id.dish_info_cancel);
		chooseButton = (Button) layout.findViewById(R.id.dish_choose);
		
		return layout;
	}
	
	private void selectedDishBorder(){
		//set border on selected dishes
		for (int type = 1; type <= 3; ++type) {
			Dish d = model.getSelectedDish(type);
			if (d == null) continue;
			
			String dishName = d.getName();
			LinearLayout parent = null;
			switch (d.getType()) {
				case 1:
					parent = (LinearLayout) view.findViewById(R.id.starter_list);
					break;
				case 2:
					parent = (LinearLayout) view.findViewById(R.id.main_list);
					break;
				case 3:
					parent = (LinearLayout) view.findViewById(R.id.dessert_list);
					break;
			}

			for (int i = 0; i < parent.getChildCount(); ++i) {
				TextView childName = (TextView) parent.getChildAt(i).findViewById(R.id.dish_name);
				
				if (childName.getText() == dishName) {
					((LinearLayout) parent.getChildAt(i)).setBackgroundColor(R.drawable.custom_border);
				}
				else {
					((LinearLayout) parent.getChildAt(i)).setBackgroundColor(R.style.AppBaseTheme);
				}
			}
			
			//view.dish_preview.setBackground(R.drawable.custom_border);

			//view.setBackground(R.drawable.custom_border);
						//view.setBackgroundColor(R.drawable.custom_border);
			
		} 
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setTotalCost();
		selectedDishBorder();
	}
}
