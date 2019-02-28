package com.example.mycontactlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class ContactSettingsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);


        initChangeBackgroundColor();
        initListButton();
        initSettingsButton();
        initMapButton();
        initSettings();
        initSortByClick();
        initSortOrderClick();
        initChangeBackgroundColorOnClick();
        getBackgroundColor();

    }

    private void initListButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initMapButton() {
        ImageButton ibList = (ImageButton) findViewById(R.id.imageButtonMap);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibSettings = (ImageButton) findViewById(R.id.imageButtonSettings);
        ibSettings.setEnabled(false);
    }

    private void initSettings() {
        String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield","contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder","ASC");

        RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
        RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
        RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);
        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked(true);
        }
        else {
            rbBirthDay.setChecked(true);
        }

        RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
        RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }
        else {
            rbDescending.setChecked(true);
        }
    }

    private void initSortByClick() {
        RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
                RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
                if (rbName.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit() .putString("sortfield", "contactname").commit();
                }
                else if (rbCity.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "city").commit();
                }
                else {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "birthday").commit();
                }
            }
        });
    }

    private void initSortOrderClick() {
        RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroupSortOrder);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
                if (rbAscending.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").commit();
                }
                else {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").commit();
                }
            }
        });
    }
    private void initChangeBackgroundColor() {
        String sortColor = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortcolor", "regular");

        RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
        RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed);
        RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
        RadioButton rbRegular = (RadioButton) findViewById(R.id.radioRegular);

        if (sortColor.equalsIgnoreCase("green")) {
            rbGreen.setChecked(true);
        } else if (sortColor.equalsIgnoreCase("blue")) {
            rbBlue.setChecked(true);
        } else if (sortColor.equalsIgnoreCase("red")) {
            rbRed.setChecked(true);
        } else {
            rbRegular.setChecked(true);
        }

    }

    private void initChangeBackgroundColorOnClick() {
        final RelativeLayout r = (RelativeLayout)findViewById(R.id.activity_contact_settings);
        RadioGroup rgSortByColor = (RadioGroup) findViewById(R.id.radioGroupSortByColor);
        rgSortByColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
                RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed);
                RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);

                if (rbBlue.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit() .putString("sortcolor", "blue").commit();
                    //int blue = getResources().getColor(R.color.colorBlue);
                    r.setBackgroundResource(R.color.colorBlue);
                }
                else if (rbRed.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortcolor", "red").commit();
                    //int red = getResources().getColor(R.color.colorRed);
                    //r.setBackgroundColor(red);
                    r.setBackgroundResource(R.color.colorRed);
                }
                else if (rbGreen.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortcolor", "green").commit();
                    //int green = getResources().getColor(R.color.colorGreen);
                    //r.setBackgroundColor(green);
                    r.setBackgroundResource(R.color.colorGreen);
                }
                else {
                    //int regular = getResources().getColor(R.color.system_transparent);
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortcolor", "regular").commit();
                    //r.setBackgroundColor(regular);
                    r.setBackgroundResource(R.color.system_transparent);

                }
            }
        });
    }

    private void getBackgroundColor(){
        final RelativeLayout r = (RelativeLayout)findViewById(R.id.activity_contact_settings);
        RadioButton rbBlue = (RadioButton) findViewById(R.id.radioBlue);
        RadioButton rbRed = (RadioButton) findViewById(R.id.radioRed);
        RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
        if (rbBlue.isChecked()) {
            r.setBackgroundResource(R.color.colorBlue);
        }
        else if (rbRed.isChecked()) {
            //int red = getResources().getColor(R.color.colorRed);
            r.setBackgroundResource(R.color.colorRed);
        }
        else if (rbGreen.isChecked()) {
            //int green = getResources().getColor(R.color.colorGreen);
            r.setBackgroundResource(R.color.colorGreen);
        }
        else {
            //int regular = getResources().getColor(R.color.system_transparent);
            r.setBackgroundResource(R.color.system_transparent);
        }

    }


}
