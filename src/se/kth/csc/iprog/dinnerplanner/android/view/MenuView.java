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
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MenuView {

	public View view;
	protected DinnerModel model;

	public MenuView(View view, DinnerModel model) {

		// store in the class the reference to the Android View
		this.view = view;
		this.model = model;

		//TextView start = (TextView) view.findViewById(R.id.start_page);
		//example.setText("Hello world");
		
		fillInstructionsList();

		// Setup the rest of the view layout
		
	}

	private void fillInstructionsList() {
		ListView list = (ListView) view.findViewById(R.layout.ingredients_list);
		
		List<Ingredient> ingredients = retrieveIngredients(model.getFullMenu());
		
		for (Ingredient i : ingredients) {
			
		}
	}

	/**
	 * Returns all ingredients of the current menu, one of each type
	 */
	private List<Ingredient> retrieveIngredients(Set<Dish> dishes) {
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Map<String,Integer> ingNames = new HashMap<String,Integer>();
		
		Set<Ingredient> ing = model.getAllIngredients();
		
		for (Ingredient i : ing) {
			if (ingNames.containsKey(i.getName())) { // If ingredient is in set
				Ingredient current = ingredients.get(ingNames.get(i.getName()));
				
				current.setQuantity(current.getQuantity() + i.getQuantity());
			}
			else {
				
			}
		}
		
		return ingredients;
	}
}
