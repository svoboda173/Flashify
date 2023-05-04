package com.example.flashify;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {
    TextView catText;
    Button fbtn1, fbtn2, fbtn3, fbtn4 ;
    Switch edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        LinearLayout outerLinearLayout = findViewById(R.id.categoryLinearLayout);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0, convertDptoPx(90));
        buttonParams.weight = 4;

        LinearLayout.LayoutParams editButtonParams = new LinearLayout.LayoutParams(0, convertDptoPx(60));
        editButtonParams.weight = 1;
        editButtonParams.leftMargin = convertDptoPx(10);

        LinearLayout.LayoutParams deleteButtonParams = new LinearLayout.LayoutParams(0, convertDptoPx(60));
        deleteButtonParams.weight = 1;
        deleteButtonParams.rightMargin = convertDptoPx(10);



        LinearLayout.LayoutParams innerLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        innerLayoutParams.setMargins(convertDptoPx(15), 0, convertDptoPx(15), convertDptoPx(20));

        Category c = (Category) getIntent().getParcelableExtra("categoryNumber");


        for (int i = 0; i < c.getFlashcards().size(); i++) {
            // Create a new horizontal LinearLayout to hold the dynamic button and two smaller image buttons
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(innerLayoutParams);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER);


            // Create the dynamic button
            Button button = new Button(this);
            button.setText(c.getFlashcard(i).getFront());
            button.setBackgroundColor(0xFF6200ED);
            button.setLayoutParams(buttonParams);
            button.setTextColor(0xFFFFFFFF);

            // Create the two smaller image buttons
            ImageButton editBtn = new ImageButton(this);
            editBtn.setImageResource(R.drawable.baseline_mode_edit_24);
            editBtn.setBackgroundColor(Color.TRANSPARENT);
            editBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
            editBtn.setVisibility(View.INVISIBLE);
            editBtn.setLayoutParams(editButtonParams);

            ImageButton deleteBtn = new ImageButton(this);
            deleteBtn.setImageResource(R.drawable.icons8_remove_96);
            deleteBtn.setBackgroundColor(Color.TRANSPARENT);
            deleteBtn.setVisibility(View.INVISIBLE);
            deleteBtn.setLayoutParams(deleteButtonParams);

            // Add the dynamic button and two smaller image buttons to the LinearLayout
            layout.addView(deleteBtn);
            layout.addView(button);
            layout.addView(editBtn);

            int ind = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentF = new Intent(CategoryView.this, FlashcardView.class);
                    intentF.putExtra("categoryNumber", c);
                    intentF.putExtra("ind", ind);
                    startActivity(intentF);
                }
            });

            outerLinearLayout.addView(layout);
        }


        // localize the interactive buttons in the screen
        catText = findViewById(R.id.textCategoryView);
        catText.setText(c.getName());

        edit = findViewById(R.id.editBtn2);

        // delete buttons

        //edit category buttons;

        /********* edit toggle ************/

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEditOn = edit.isChecked();
                // toggle on
                    for (int innerLinearLayoutInd = 0; innerLinearLayoutInd < c.getFlashcards().size(); innerLinearLayoutInd++) {
                        LinearLayout innerLinearLayout = (LinearLayout) outerLinearLayout.getChildAt(innerLinearLayoutInd);
                        if (isEditOn) {
                            innerLinearLayout.getChildAt(0).setVisibility(View.VISIBLE);
                            innerLinearLayout.getChildAt(2).setVisibility(View.VISIBLE);
                        } else {
                            innerLinearLayout.getChildAt(0).setVisibility(View.INVISIBLE);
                            innerLinearLayout.getChildAt(2).setVisibility(View.INVISIBLE);
                        }
                    }
            }
        });
    }

/********************STATIC APPROACH TO ADDING BUTTONS************************
        // Flashcard buttons
//        ArrayList<Button> FshButtons = new ArrayList<>();
//        fbtn1 = findViewById(R.id.BtnFlashcard1);
//        fbtn2 = findViewById(R.id.btnFlashcard2);
//        fbtn3 = findViewById(R.id.btnFlashcard3);
//        fbtn4 = findViewById(R.id.btnFlashcard4);
//        FshButtons.add(fbtn1);
//        FshButtons.add(fbtn2);
//        FshButtons.add(fbtn3);
//        FshButtons.add(fbtn4);

/************************* INITIALIZER *****************************

            // retrieve the category object from the previous activity
            catText.setText(c.getName());

            // initialize the fsh buttons
            for (int id = 0; id < c.getFlashcards().size() ; id++){
                FshButtons.get(id).setText(c.getFlashcards().get(id).getFront());
                FshButtons.get(id).setVisibility(View.VISIBLE);
                int finalId = id;
                FshButtons.get(id).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentF = new Intent (CategoryView.this, FlashcardView.class);
                        intentF.putExtra("co",c);
                        int index = finalId;
                        intentF.putExtra("ind",index);
                        startActivity(intentF);
                    }
                });

            }
 */

///******************************************************************/
//
    private int convertDptoPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

/******************************************************************/

}