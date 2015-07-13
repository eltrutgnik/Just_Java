package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        int price = calculatePrice(hasWhippedCream, hasChocolateChip);

        String message = createOrderSummary(price, hasWhippedCream, hasChocolateChip, value);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + value);
        intent.putExtra(Intent.EXTRA_TEXT, message );

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the increment button is clicked./
     */
    public void increment(View view) {
        if (quantity > 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }


        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

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


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }
     */


    /**
         * Calculates the price of the order based on the current quantity.
         *
         * @return the price
         */

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolateChip) {
        int basePrice = 5;
        if(hasWhippedCream){
            basePrice = basePrice + 1;
        }

        if(hasChocolateChip){
            basePrice = basePrice + 2;
        }


        int price = quantity * basePrice;
        return price;
    }

    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolateChip, String value){



        String summaryMessage = "Name: " + value ;
        summaryMessage += "\nAdd Whipped Cream? "+ hasWhippedCream;
        summaryMessage += "\nAdd Chocolate Chip? "+ hasChocolateChip;
        summaryMessage += "\nQuantity: " + quantity;
        summaryMessage += "\nTotal: $" + price ;
        summaryMessage += "\nThank you! ";
        return summaryMessage;

    }
}


