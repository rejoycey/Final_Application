package com.edu.swufe.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class KeyPadActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnDisplay = null;

    private Button btnOne = null;

    private Button btnTwo = null;

    private Button btnThree = null;

    private Button btnFour = null;

    private Button btnFive = null;

    private Button btnSix = null;

    private Button btnSeven = null;

    private Button btnEight = null;

    private Button btnNine = null;

    private Button btnZero = null;

    private Button btnDot = null;

    private Button btnDelete = null;

    private Button btnCancel = null;

    private Button btnClean = null;

    private Button btnDone = null;

    private String value = "0";

    private boolean isValueEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_key_pad );
        btnDisplay = (Button) findViewById(R.id.display);
        btnOne = (Button) findViewById(R.id.one);
        btnTwo = (Button) findViewById(R.id.two);
        btnThree = (Button) findViewById(R.id.three);
        btnFour = (Button) findViewById(R.id.four);
        btnFive = (Button) findViewById(R.id.five);
        btnSix = (Button) findViewById(R.id.six);
        btnSeven = (Button) findViewById(R.id.seven);
        btnEight = (Button) findViewById(R.id.eight);
        btnNine = (Button) findViewById(R.id.nine);
        btnZero = (Button) findViewById(R.id.zero);
        btnDot = (Button) findViewById(R.id.dot);
        btnDelete = (Button) findViewById(R.id.delete);
        btnCancel = (Button) findViewById(R.id.cancel);
        btnClean = (Button) findViewById(R.id.clean);
        btnDone = (Button) findViewById(R.id.done);
        btnDisplay.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnClean.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        if(this.getIntent().hasExtra("value"))
        {
            value=this.getIntent().getStringExtra("value");
        }
        if(value==null || value.equals(""))
        {
            value="0";
            isValueEmpty = true;
        }
        btnDisplay.setText(value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.display:
                break;
            case R.id.delete:
                if (value.length() > 1) {
                    value = value.substring(0, value.length() - 1);
                } else {
                    value = "0";
                }
                break;
            case R.id.cancel:
                this.setResult(RESULT_CANCELED);
                this.finish();
                break;
            case R.id.clean:
                value = "0";
                break;
            case R.id.done:

                this.setResult(RESULT_OK, this.getIntent().putExtra("value", value));
                this.finish();
                break;
            default:
                int i = 0;
                if(!isValueEmpty){
                    value = "0";
                    isValueEmpty = true;
                }
                if (v.getId() == R.id.dot) {
                    if (value.indexOf(".") > -1) {
                        break;
                    }
                    i = 1;
                } else {
                    if (value.equals("0")) {
                        value = "";
                    }
                }
                if(value.indexOf(".")>-1 || i==1){
                    if (value.length() < 9) {
                        value = value + ((Button) v).getText();
                    }
                }else{
                    if (value.length() < 6) {
                        value = value + ((Button) v).getText();
                    }
                }

                break;
        }

        if(value.indexOf(".")>-1 && value.indexOf(".")<value.length()-3)
        {
            value=value.substring(0,value.indexOf(".")+3);
        }

        btnDisplay.setText(value);//₩

    }
    }

