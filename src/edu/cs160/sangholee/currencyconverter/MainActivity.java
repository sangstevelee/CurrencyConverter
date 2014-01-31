package edu.cs160.sangholee.currencyconverter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView result;
	EditText inputValue;
	private String fromCurrency;
	private String toCurrency;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		setupSpinners();
		result = (TextView) findViewById(R.id.result);
		inputValue = (EditText) findViewById(R.id.currencyInput);
	    inputValue.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	            calculate();
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		calculate();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setupSpinners() {
		Spinner spinner = (Spinner) findViewById(R.id.fromSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.currency_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		Spinner spinner1 = (Spinner) findViewById(R.id.toSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
		        R.array.currency_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner1.setAdapter(adapter1);
		System.out.println("hello");
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	calculate();

            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	calculate();

            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
	}
	
	public void calculate() {
		Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
		Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
		fromCurrency = fromSpinner.getSelectedItem().toString();
		toCurrency = toSpinner.getSelectedItem().toString();
		double input = 0;
		if (!inputValue.getText().toString().trim().equals("")) {
			input = Double.valueOf(inputValue.getText().toString());
		}
		double finalValue = input;
		if (fromCurrency.equals(toCurrency)) {
			finalValue = input;
		} else if (fromCurrency.equals("USD")) {
			if (toCurrency.equals("BTC")) {
				finalValue = input * 0.001217;
			} else if (toCurrency.equals("DOGE")) {
				finalValue = input * 638.17;
			}
		} else if (fromCurrency.equals("BTC")) {
			if (toCurrency.equals("USD")) {
				finalValue = input * 822.4;
			} else if (toCurrency.equals("DOGE")) {
				finalValue = input * 532970.5882352941;
			}
		} else if (fromCurrency.equals("DOGE")) {
			if (toCurrency.equals("USD")) {
				finalValue = input * 0.00156697432620104;
			} else if (toCurrency.equals("BTC")) {
				finalValue = input * 0.0000018762761437006789;
			}
		}
		result.setText(Double.toString(finalValue));
	}
	

}
