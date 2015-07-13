package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateChipCheckBox = (CheckBox) findViewById(R.id.chocolate_chip_checkbox);
        boolean hasChocolateChip = chocolateChipCheckBox.isChecked();

        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();

        int price = calculatePrice();
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolateChip, value));
    }

    /**
     * This method is called when the increment button is clicked./
     */
    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.

    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    */


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }

        /**
         * Calculates the price of the order based on the current quantity.
         *
         * @return the price
         */

    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolateChip, String value){
        //String cname = "James";
        //int price = calculatePrice();

        String summaryMessage = "Name: " + value ;
        summaryMessage += "\nAdd Whipped Cream? "+ hasWhippedCream;
        summaryMessage += "\nAdd Chocolate Chip? "+ hasChocolateChip;
        summaryMessage += "\nQuantity: " + quantity;
        summaryMessage += "\nTotal: $" + price;
        summaryMessage += "\nThank you! ";
        return summaryMessage;

    }
}


