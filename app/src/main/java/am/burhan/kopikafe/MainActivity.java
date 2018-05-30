package am.burhan.kopikafe;

import android.support.v7.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {
    TextView txtQty;
    TextView txtBill;
    TextView txtOrderSummary;
    TextView txtPrice;
    TextView txtOrderTitle;
    TextView txtTagihan1;
    TextView txtTagihan2;

    RadioButton radioBtnGula;
    RadioButton radioBtnMadu;
    RadioButton radioBtnKrimer;

    CheckBox checkBoxEsKrim;
    CheckBox checkBoxCeri;
    CheckBox checkBoxFoam;
    CheckBox checkBoxKakao;

    EditText txtPembeli;

    String sweetener;
    String topping;
    int qty;
    double bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtQty = (TextView) findViewById(R.id.txtQty);
        txtBill = (TextView) findViewById(R.id.txtBill);
        txtOrderSummary = (TextView) findViewById(R.id.detail);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtOrderTitle = (TextView) findViewById(R.id.orderTitle1);
        txtTagihan1 = (TextView) findViewById(R.id.tagihan1);
        txtTagihan2 = (TextView) findViewById(R.id.tagihan2);
        radioBtnGula = (RadioButton) findViewById(R.id.radioBtnGula);
        radioBtnMadu = (RadioButton) findViewById(R.id.radioBtnMadu);
        radioBtnKrimer = (RadioButton) findViewById(R.id.radioBtnKrimer);
        checkBoxEsKrim = (CheckBox) findViewById(R.id.checkBoxEsKrim);
        checkBoxCeri = (CheckBox) findViewById(R.id.checkBoxCeri);
        checkBoxFoam = (CheckBox) findViewById(R.id.checkBoxFoam);
        checkBoxKakao = (CheckBox) findViewById(R.id.checkBoxKakao);
        txtPembeli = (EditText) findViewById(R.id.pembeli);
        sweetener = "Tidak";
        topping = "Tidak";
        qty = 0;
        bill = 0;
    }

    public void increment(View view) {
        qty += 1;
        display(qty);
    }

    public void decrement(View view) {
        qty -= 1;
        display(qty);
    }

    public void submitOrder(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        topping = "Tidak";
        if ((qty == 0) || (txtPembeli.getText().toString().equals(""))) {
            builder.setMessage("Data invalid. Ada yang belum terisi.");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            bill = calculate();
            if (radioBtnGula.isChecked()) {
                if ((radioBtnMadu.isChecked()) || (radioBtnKrimer.isChecked())) {
                    radioBtnMadu.setChecked(false);
                    radioBtnKrimer.setChecked(false);
                } sweetener = radioBtnGula.getText().toString();
            }
            if (radioBtnMadu.isChecked()) {
                if ((radioBtnGula.isChecked()) || (radioBtnKrimer.isChecked())) {
                    radioBtnGula.setChecked(false);
                    radioBtnKrimer.setChecked(false);
                } sweetener = radioBtnMadu.getText().toString();
            }
            if (radioBtnKrimer.isChecked()) {
                if ((radioBtnMadu.isChecked()) || (radioBtnGula.isChecked())) {
                    radioBtnMadu.setChecked(false);
                    radioBtnGula.setChecked(false);
                } sweetener = radioBtnKrimer.getText().toString();
            }
            if (checkBoxEsKrim.isChecked()) {
                if (topping.equals("Tidak")) {
                    topping = checkBoxEsKrim.getText().toString();
                }
            }
            if (checkBoxCeri.isChecked()) {
                if (topping.equals("Tidak")) {
                    topping = checkBoxCeri.getText().toString();
                } else {
                    topping += ", " + checkBoxCeri.getText().toString();
                }
            }
            if (checkBoxFoam.isChecked()) {
                if (topping.equals("Tidak")) {
                    topping = checkBoxFoam.getText().toString();
                } else {
                    topping += ", " + checkBoxFoam.getText().toString();
                }
            }
            if (checkBoxKakao.isChecked()) {
                if (topping.equals("Tidak")) {
                    topping = checkBoxKakao.getText().toString();
                } else {
                    topping += ", " + checkBoxKakao.getText().toString();
                }
            }
            display(createOrderSummary(sweetener, topping), bill);
        }
    }

    public void display(int qty) {
        txtQty.setText(valueOf(qty));
    }

    public void display(String message, double bill) {
        txtOrderTitle.setText(getString(R.string.judulDetail));
        txtOrderSummary.setText(message);
        txtTagihan1.setText(getString(R.string.judulTotal));
        txtTagihan2.setText("$");
        txtBill.setText(valueOf(bill));

    }

    public double calculate() {
        double price = Double.valueOf(txtPrice.getText().toString());

        return (double) qty * price;
    }

    public String createOrderSummary (String sweetener, String topping) {
        String displayDetail = "Nama : " + txtPembeli.getText().toString();

        displayDetail += "\nPemanis : " + sweetener;
        displayDetail += "\nTopping : " + topping;
        displayDetail += "\nJumlah  : " + qty;

        return displayDetail;
    }

    public void clear(View view) {
        qty = 0;
        bill = 0;
        txtQty.setText("0");
        txtPembeli.setText("");
        txtOrderTitle.setText("");
        txtOrderSummary.setText("");
        txtTagihan1.setText("");
        txtTagihan2.setText("");
        txtBill.setText("");
        radioBtnGula.setChecked(false);
        radioBtnMadu.setChecked(false);
        radioBtnKrimer.setChecked(false);
        checkBoxEsKrim.setChecked(false);
        checkBoxCeri.setChecked(false);
        checkBoxFoam.setChecked(false);
        checkBoxKakao.setChecked(false);
    }
}
