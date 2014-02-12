package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.android.view.ChooseView;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

	private DinnerModel model;
	
	private MenuView menuView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		
		setContentView(R.layout.activity_menu);
		
		menuView = new MenuView(getBaseContext(), findViewById(R.layout.activity_menu), model);
		
		
		Button backButton = (Button)menuView.view.findViewById(R.id.button_back);
		backButton.setOnClickListener
		(new Button.OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  finish();
			  }
		});
		
		Button ingredientsButton = (Button)menuView.view.findViewById(R.id.ingredients);
		ingredientsButton.setOnClickListener
		(new Button.OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  menuView.changeView(0);
			  }
		});
		
		Button starterButton = (Button)menuView.view.findViewById(R.id.starterdish);
		starterButton.setOnClickListener
		(new Button.OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  menuView.changeView(1);
			  }
		});
		
		Button mainButton = (Button)menuView.view.findViewById(R.id.maindish);
		mainButton.setOnClickListener
		(new Button.OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  menuView.changeView(2);
			  }
		});
		
		Button dessertButton = (Button)menuView.view.findViewById(R.id.dessertdish);
		dessertButton.setOnClickListener
		(new Button.OnClickListener() {
			  @Override
			  public void onClick(View v) {
				  menuView.changeView(2);
			  }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
