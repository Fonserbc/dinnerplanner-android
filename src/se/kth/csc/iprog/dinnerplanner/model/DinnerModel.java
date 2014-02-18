package se.kth.csc.iprog.dinnerplanner.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class DinnerModel extends Observable implements IDinnerModel  {

	Set<Dish> dishes = new HashSet<Dish>();
	Set<Dish> selectedDishes = new HashSet<Dish>();
	int numberOfguests;	
	
	/**
	 * The constructor of the overall model. Set the default values here
	 */
	public DinnerModel(){
		
		//Adding some example data, you can add more
		Dish dish1 = new Dish("French toast",Dish.STARTER,"toast","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
		dish1.addIngredient(new Ingredient("eggs",0.5,"",1));
		dish1.addIngredient(new Ingredient("milk",30,"ml",6));
		dish1.addIngredient(new Ingredient("brown sugar",7,"g",1));
		dish1.addIngredient(new Ingredient("ground nutmeg",0.5,"g",12));
		dish1.addIngredient(new Ingredient("white bread",2,"slices",2));
		dishes.add(dish1);
		
		Dish dish2 = new Dish("Meat balls",Dish.MAIN,"meatballs","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		dish2.addIngredient(new Ingredient("extra lean ground beef",115,"g",20));
		dish2.addIngredient(new Ingredient("sea salt",0.7,"g",3));
		dish2.addIngredient(new Ingredient("small onion, diced",0.25,"",2));
		dish2.addIngredient(new Ingredient("garlic salt",0.6,"g",3));
		dish2.addIngredient(new Ingredient("Italian seasoning",0.3,"g",3));
		dish2.addIngredient(new Ingredient("dried oregano",0.3,"g",3));
		dish2.addIngredient(new Ingredient("crushed red pepper flakes",0.6,"g",3));
		dish2.addIngredient(new Ingredient("Worcestershire sauce",16,"ml",7));
		dish2.addIngredient(new Ingredient("milk",20,"ml",4));
		dish2.addIngredient(new Ingredient("grated Parmesan cheese",5,"g",8));
		dish2.addIngredient(new Ingredient("seasoned bread crumbs",115,"g",4));
		dishes.add(dish2);
		
		Dish dish3 = new Dish("Ice creame",Dish.DESERT,"icecream","Mix all ingredients in a bowl and put it in the freezer.");
		dish3.addIngredient(new Ingredient("eggs",0.5,"",1));
		dish3.addIngredient(new Ingredient("cream",60,"ml",3));
		dish3.addIngredient(new Ingredient("chocolate",25,"g",5));
		dish3.addIngredient(new Ingredient("sugar",6,"ml",1));
		dishes.add(dish3);
		
		Dish dish4 = new Dish("Baked brie",Dish.STARTER,"bakedbrie","Put the nuts on the brie and pour honey on top. Bake it in the oven for 7 minutes.");
		dish4.addIngredient(new Ingredient("brie",0.2,"",10));
		dish4.addIngredient(new Ingredient("nuts",15,"g",2));
		dish4.addIngredient(new Ingredient("honey",10,"ml",3));
		dishes.add(dish4);
		
		Dish dish5 = new Dish("Fried halloumi",Dish.STARTER,"halloumi","Slice the halloumi and fry it in a pan.");
		dish5.addIngredient(new Ingredient("halloumi cheese",0.25,"",5));
		dish5.addIngredient(new Ingredient("oil",0.5,"tbs",1));
		dishes.add(dish5);
		
		Dish dish6 = new Dish("Green Pea Soup",Dish.STARTER,"green","Put everything in at pot and cook for five minutes. Use a blender to make it into a pure.");
		dish6.addIngredient(new Ingredient("Frozen peas",100,"g",3));
		dish6.addIngredient(new Ingredient("Broth",100,"ml",1));
		dishes.add(dish6);
		
		Dish dish7 = new Dish("Paella",Dish.MAIN,"paella","Get a spanish guy, preferably from Catalonia, and get him prepare it, its not simple to explain, trust me.");
		dish7.addIngredient(new Ingredient("Frozen peas",30,"g",3));
		dish7.addIngredient(new Ingredient("Rice",100,"g",1));
		dish7.addIngredient(new Ingredient("Chicken",100,"g",2));
		dish7.addIngredient(new Ingredient("Saffron",2,"g",1));
		dishes.add(dish7);

	}
	
	/**
	 * Returns all of the dishes (from menu, that the user can select from)
	 */
	public Set<Dish> getDishes(){
		return dishes;
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishesOfType(int type){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type){
				result.add(d);
			}
		}
		return result;
	}
	
	/**
	 * Returns the set of dishes of specific type, that contain filter in their name
	 * or name of any ingredient. 
	 */
	public Set<Dish> filterDishesOfType(int type, String filter){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type && d.contains(filter)){
				result.add(d);
			}
		}
		return result;
	} 
	
	//return number of guests
	@Override
	public int getNumberOfGuests() {		
		return numberOfguests;
	}

	//set number of guests
	@Override
	public void setNumberOfGuests(int numberOfGuests) {
		numberOfguests = numberOfGuests;
		
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns the dish that is on the menu for selected type (1 = starter, 2 = main, 3 = desert).
	 */
	@Override
	public Dish getSelectedDish(int type) {
		//look through the set and find the dish with the selected type
		for(Dish d : selectedDishes){
			if(d.getType() == type){
				return d;
			}
		}
		//error could not find a dish with the selected type
		return null;
	}

	/**
	 * Returns all the dishes on the menu (that the user have selected).
	 */
	@Override
	public Set<Dish> getFullMenu() {
		return selectedDishes;
	}

	/**
	 * Returns all ingredients for all the dishes on the menu.
	 */
	@Override
	public Set<Ingredient> getAllIngredients() {
		Set<Ingredient> all_ingrediens = new HashSet<Ingredient>();
		for(Dish d : selectedDishes){
			Set<Ingredient> ingredient = d.getIngredients();
			for(Ingredient i : ingredient){
				all_ingrediens.add(i);
			}
		}
		return all_ingrediens;
	}

	/**
	 * Returns the total price of the menu (all the ingredients multiplied by number of guests).
	 */
	@Override
	public float getTotalMenuPrice() {
		double total = 0;
		for(Dish d : selectedDishes){
			Set<Ingredient> ingredient = d.getIngredients();
			for(Ingredient i : ingredient){
				total = total + (i.getPrice() * numberOfguests);
			}
		}
		return (float) total;
	}
	
	/**
	 * Returns the total price of the dish (all the ingredients multiplied by number of guests).
	 */
	public float getDishPrice(Dish d) {
		double total = 0;
		Set<Ingredient> ingredient = d.getIngredients();
		for(Ingredient i : ingredient){
			total = total + (i.getPrice() * numberOfguests);
		}
		return (float) total;
	}
	
	//add dish from menu
	public void addDish(String name){
		Dish selected = null;
		for(Dish d : dishes){
			if(d.getName().equals(name)){
				selected = d;
				break;
			}
		}
		
		if (selected == null) return; //Dish does not exist
		
		for (Dish d : selectedDishes) {
			if (d.getName() == selected.getName()) return; // Dish already selected, nothing to do
			if (d.getType() == selected.getType()) {
				selectedDishes.remove(d);
				break;
			}
		}
		
		selectedDishes.add(selected);
		
		setChanged();
		notifyObservers();
	}

}
