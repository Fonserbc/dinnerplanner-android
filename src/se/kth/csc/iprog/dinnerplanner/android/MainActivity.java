package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.android.view.StartPageController;
import se.kth.csc.iprog.dinnerplanner.android.view.StartPageView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	
	private DinnerModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
    	super.onCreate(savedInstanceState);
    	
    	model = ((DinnerPlannerApplication) this.getApplication()).getModel();
    	
    	// Set the view for the main activity screen
    	// it must come before any call to findViewById method
        setContentView(R.layout.activity_main);
        
    	// Creating the view class instance
        StartPageView mainView = new StartPageView(findViewById(R.layout.start_page), model);
        StartPageController mainController = new StartPageController(this, mainView, model);
        
    }

}
