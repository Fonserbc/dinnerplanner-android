package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuView {
	
	/**
	 * Class for displaying the ingredients on the ingredients list properly
	 */
	private class IngredientsArrayAdapter extends ArrayAdapter<Ingredient> {
		private Context context;
		private ArrayList<Ingredient> ingredients;
		private int numberOfGuests;

		public IngredientsArrayAdapter(Context context, int resource, ArrayList<Ingredient> objects, int numberOfGuests) {
			super(context, resource, objects);
			this.context = context;
			this.ingredients = objects;
			this.numberOfGuests = numberOfGuests;
		}
		
		@Override
		public View getView(int pos, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View rowView = inflater.inflate(R.layout.ingredient, parent, false);
			
			TextView name = (TextView) rowView.findViewById(R.id.ingredient_name);
			TextView quantity = (TextView) rowView.findViewById(R.id.ingredient_quantity);
			TextView price = (TextView) rowView.findViewById(R.id.ingredient_price);
			
			Ingredient i = ingredients.get(pos);
			name.setText(i.getName());
			quantity.setText(String.format("%.1f", i.getQuantity()*numberOfGuests) + " " + i.getUnit());
			price.setText(Double.toString(i.getPrice()*numberOfGuests) + " SEK");
			
			return rowView;
		}
	}

	public View view;
	
	public Button backButton;
	public ImageButton ingredientsButton;
	public ImageButton starterButton;
	public ImageButton mainButton;
	public ImageButton dessertButton;
	
	
	private Context context;
	private DinnerModel model;
	private LayoutInflater inflater;
	
	private ListView ingredientsListView;
	private View instructionsView;
	private View currentView;
	private int currentViewId = -1;

	public MenuView(Context context, View view, DinnerModel model) {

		this.context = context;
		this.view = view;
		this.model = model;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// Find important views
		backButton = (Button)view.findViewById(R.id.button_back);
		ingredientsButton = (ImageButton)view.findViewById(R.id.ingredients);
		
		starterButton = (ImageButton)view.findViewById(R.id.starterdish);
		if(model.getSelectedDish(1) != null){
			starterButton.setImageResource(context.getResources().getIdentifier(model.getSelectedDish(1).getImage(), "drawable", context.getPackageName()));
		}
		
		mainButton = (ImageButton)view.findViewById(R.id.maindish);
		if(model.getSelectedDish(2) != null){
			mainButton.setImageResource(context.getResources().getIdentifier(model.getSelectedDish(2).getImage(), "drawable", context.getPackageName()));
		}
		
		dessertButton = (ImageButton)view.findViewById(R.id.dessertdish);
		if(model.getSelectedDish(3) != null){
			dessertButton.setImageResource(context.getResources().getIdentifier(model.getSelectedDish(3).getImage(), "drawable", context.getPackageName()));
		}
		
		// Inflate views
		instructionsView = (View) inflater.inflate(R.layout.dish_instructions, null);
		ingredientsListView = (ListView) inflater.inflate(R.layout.ingredients_list, null);
		
		// Update info of views based on the model
		TextView totalCost = (TextView) view.findViewById(R.id.text_menu_price);
		totalCost.setText(Float.toString(model.getTotalMenuPrice()) + " SEK");
		
		fillIngredientsList();

		// Set the current view to the ingredients List
		changeView(0); 
	}
	
	public void changeView(int i){
		if (i == currentViewId) return; // Nothing to change

		if(i > 0){ // Dish Instructions
			fillInstructionView(i);
		}
		
		// If we come or go to the ingredients list we need to change the view
		if (currentViewId == 0 || i == 0) {
			if(currentView != null){
				((ViewGroup) view.findViewById(R.id.info_scroll)).removeView(currentView);
			}
			
			if (i == 0) currentView = ingredientsListView;
			else currentView = instructionsView;
			
			((RelativeLayout) view.findViewById(R.id.info_scroll)).addView(currentView);
		}
		
		currentViewId = i;
		
		for (int j = 1; j <= 3; ++j) {
			switch (j) {
				case 0:
					if (j == currentViewId) ingredientsButton.setBackgroundResource(R.drawable.custom_border);
					else ingredientsButton.setBackgroundResource(R.drawable.custom_border_off);
					break;
				case 1:
					if (j == currentViewId) starterButton.setBackgroundResource(R.drawable.custom_border);
					else starterButton.setBackgroundResource(R.drawable.custom_border_off);
					break;
				case 2:
					if (j == currentViewId) mainButton.setBackgroundResource(R.drawable.custom_border);
					else mainButton.setBackgroundResource(R.drawable.custom_border_off);
					break;
				case 3:
					if (j == currentViewId) dessertButton.setBackgroundResource(R.drawable.custom_border);
					else dessertButton.setBackgroundResource(R.drawable.custom_border_off);
					break;
			}
		}
	}

	/**
	 * Fills the ListView of the ingredients list with the current menu ingredients
	 */
	private void fillIngredientsList() {		
		ArrayList<Ingredient> ingredients = retrieveIngredients(model.getFullMenu());
		
		IngredientsArrayAdapter adapter = new IngredientsArrayAdapter(context, R.layout.ingredients_list, ingredients, model.getNumberOfGuests());
		ingredientsListView.setAdapter(adapter);
	}

	/**
	 * Returns all ingredients of the current menu, one of each type
	 */
	private ArrayList<Ingredient> retrieveIngredients(Set<Dish> dishes) {
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Map<String,Integer> ingNames = new HashMap<String,Integer>();
		
		Set<Ingredient> ing = model.getAllIngredients();
		
		for (Ingredient i : ing) {
			if (ingNames.containsKey(i.getName())) { // If ingredient is in set
				int it = ingNames.get(i.getName());
				Ingredient current = ingredients.get(it);
				ingredients.remove(it);
				
				current.setQuantity(current.getQuantity() + i.getQuantity());
				
				ingredients.add(current);				
			}
			else { // New ingredient
				ingNames.put(i.getName(), ingredients.size());
				Ingredient newI = new Ingredient(i);
				ingredients.add(newI);
			}
		}
		
		return ingredients;
	}
	
	/**
	 * Fills the instruction view with the specific dish to display
	 */
	public void fillInstructionView(int type){
		Dish dish = model.getSelectedDish(type);
		
		if(dish!= null){
			TextView dishtype = (TextView) instructionsView.findViewById(R.id.text_type_of_dish);
			dishtype.setText(typeToString(dish.getType()));
	
			TextView dishname = (TextView) instructionsView.findViewById(R.id.text_name_of_dish);
			dishname.setText(dish.getName());
		
			TextView dishin = (TextView) instructionsView.findViewById(R.id.text_dish_instruction);
			dishin.setText(dish.getDescription().replaceAll("\\. ", "\\.\n"));
			dishin.setMovementMethod(new ScrollingMovementMethod());
		}
	}
	
	/**
	 * Auxiliary function of fillInstructionView function
	 */
	private String typeToString(int i){
		if (i == 1)
			return "Starter";
		else if (i == 2)
			return "Main";
		else
			return "Dessert";
	}
}
