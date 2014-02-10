package se.kth.csc.iprog.dinnerplanner.android;


import se.kth.csc.iprog.dinnerplanner.android.view.ChooseView;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends Activity {

	private DinnerModel model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		model = ((DinnerPlannerApplication) this.getApplication()).getModel();
		
		setContentView(R.layout.activity_choose);
		

		//ChooseView chooseView = new ChooseView(findViewById(R.layout.activity_choose), model, this);
		
		Button createButton = (Button) findViewById(R.id.menu_create);
	    createButton.setOnClickListener
	    (new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getBaseContext(),MenuActivity.class);
				startActivity(intent);
			}
	    }
	    );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose, menu);
		return true;
	}
}
