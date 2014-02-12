package se.kth.csc.iprog.dinnerplanner.android.view;

import se.kth.csc.iprog.dinnerplanner.android.R;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TempMenuView {
	private Context context;
	public View view;
	DinnerModel model;
	LayoutInflater inflater;
	private View instruction_view;
	
	public TempMenuView(Context context, View view, DinnerModel model) {
		// store in the class the reference to the Android View
		this.context = context;
		this.view = view;
		this.model = model;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		instruction_view = (View) inflater.inflate(R.layout.dish_instructions, null);
		
		int i = 1;
		FillView(i);
		
		// Setup the rest of the view layout
		((RelativeLayout) view.findViewById(R.id.info_scroll)).addView(instruction_view);
		
	}
	
	public void FillView(int type){
		
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
