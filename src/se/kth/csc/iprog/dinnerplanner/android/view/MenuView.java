package se.kth.csc.iprog.dinnerplanner.android.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuView {
	
	private Context context;
	
	public class IngredientsArrayAdapter extends ArrayAdapter<Ingredient> {
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
	DinnerModel model;
	LayoutInflater inflater;
	
	private ListView ingredientsListView;
	private View instruction_view;
	private View currentview;

	public MenuView(Context context, View view, DinnerModel model) {

		// store in the class the reference to the Android View
		this.context = context;
		this.view = view;
		this.model = model;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		instruction_view = (View) inflater.inflate(R.layout.dish_instructions, null);
		ingredientsListView = (ListView) inflater.inflate(R.layout.ingredients_list, null);
		TextView totalCost = (TextView) view.findViewById(R.id.text_menu_price);
		
		totalCost.setText(Float.toString(model.getTotalMenuPrice()) + " SEK");
		
		fillIngredientsList();

		changeView(0);
	}
	
	public void changeView(int i){
		if(currentview != null){
			((ViewGroup) view.findViewById(R.id.info_scroll)).removeView(currentview);
		}
		
		if(i == 0){
			currentview = ingredientsListView;
			
		}
		else{
			currentview = instruction_view;
			fillInstructionView(i);
			
		}
		// Setup the rest of the view layout
		((RelativeLayout) view.findViewById(R.id.info_scroll)).addView(currentview);
	}

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
			else {
				ingNames.put(i.getName(), ingredients.size());
				Ingredient newI = new Ingredient(i);
				ingredients.add(newI);
			}
		}
		
		return ingredients;
	}
	
	public void fillInstructionView(int type){
		Dish dish = model.getSelectedDish(type);
		
		if(dish!= null){
			TextView dishtype = (TextView) instruction_view.findViewById(R.id.text_type_of_dish);
			dishtype.setText(typeToString(dish.getType()));
	
			TextView dishname = (TextView) instruction_view.findViewById(R.id.text_name_of_dish);
			dishname.setText(dish.getName());
		
			TextView dishin = (TextView) instruction_view.findViewById(R.id.text_dish_instruction);
			dishin.setText(dish.getDescription().replaceAll("\\. ", "\\.\n"));
			dishin.setMovementMethod(new ScrollingMovementMethod());
		}
	}
	
	private String typeToString(int i){
		if (i == 1)
			return "Starter";
		else if (i == 2)
			return "Main";
		else
			return "Dessert";
	}
}
