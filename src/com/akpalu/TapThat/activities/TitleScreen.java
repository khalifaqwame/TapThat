package com.akpalu.TapThat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.akpalu.TapThat.R;
import com.akpalu.TapThat.levels.LevelSelect;

/**
 * Shows the title screen
 * @author Qwame
 *
 */
public class TitleScreen extends VolumeControlActivity {
	TitleScreen reference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		reference = this;
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		setContentView(R.layout.activity_start_screen);
		
		ImageView goButton = (ImageView) findViewById(R.id.go_button);
        ImageView howtobtn = (ImageView) findViewById(R.id.howtoBtn);

        goButton.setOnClickListener(goButtonListener);
        howtobtn.setOnClickListener(howtobtnButtonListener);

    }

    private View.OnClickListener howtobtnButtonListener = new View.OnClickListener() {

        public void onClick(View v) {
            startActivity(new Intent(TitleScreen.this, TutorialActivity.class));
        }

    };




    private View.OnClickListener goButtonListener = new View.OnClickListener() {

        public void onClick(View v) {
            startActivity(new Intent(TitleScreen.this, LevelSelect.class));
        }



    };;


    /**
	 * Disables the back button
	 */
	@Override
	public void onBackPressed() {
	}




    }