package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.android.view.MenuView;
import se.kth.csc.iprog.dinnerplanner.android.view.MenuViewController;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MenuActivity extends Activity {

	private DinnerModel model;
	
	private MenuView menuView;
	private MenuViewController menuViewController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		
		setContentView(R.layout.activity_menu);
		
		menuView = new MenuView(getBaseContext(), findViewById(R.layout.activity_menu), model);
		
		menuViewController = new MenuViewController(this, model, menuView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
