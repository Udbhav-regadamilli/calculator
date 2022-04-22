package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;

    private String input, output, newOutput;

    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, badd, bsub, bmul, bdiv, bpoint, bpercentage, bpower, bequal, bclear, bback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);
        b0 = findViewById(R.id.b0);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        badd = findViewById(R.id.addition);
        bsub = findViewById(R.id.subtraction);
        bmul = findViewById(R.id.multiplication);
        bdiv = findViewById(R.id.division);
        bpoint = findViewById(R.id.point);
        bequal = findViewById(R.id.equal);
        bpower = findViewById(R.id.pwr_btn);
        bpercentage = findViewById(R.id.percent);
        bclear = findViewById(R.id.clear_btn);
        bback = findViewById(R.id.back);
        bback.setText("\u232b");
    }

    public void onButtonClicked(View view){
        try{
            Button button = (Button) view;
            String data = button.getText().toString();
            switch (data){
                case "C":
                    input = null;
                    output = null;
                    outputText.setText("");
                    break;

                case "=":
                    solve();
                    break;

                case "%":
                    solve();
                    inputText.setText(input);
                    double d = Double.parseDouble(inputText.getText().toString())/100;
                    outputText.setText(String.valueOf(d));
                    break;

                case "\u232b":
                    if(input.length() == 0 && outputText.length() != 0){
                        outputText.setText("");
                        break;
                    }

                    if(input.length() != 0){
                        input=input.substring(0, input.length()-1);
                    }
                    break;

                default:
                    if(input == null){
                        input = "";
                    }

                    if(data.equals("+") || data.equals("-") || data.equals("/") || data.equals("*") || data.equals("^")){
                        solve();
                    }

                    input+=data;
            }
            inputText.setText(input);
        }
        catch (Exception e){
            msg(e);
        }
    }

    private void solve(){
        if(input.split("\\+").length == 2){
            String numbers[] = input.split("\\+");
            try{
                double d = Double.parseDouble(numbers[0]) +  Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = Decimal(output);
                outputText.setText(newOutput);
                input = newOutput + "";
            }catch (Exception e){
                //outputText.setError(e.getMessage().toString());
                msg(e);
            }
        }

        if(input.split("\\*").length == 2){
            String numbers[] = input.split("\\*");
            try{
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = Decimal(output);
                outputText.setText(newOutput);
                input = newOutput + "";
            }catch (Exception e){
                //outputText.setError(e.getMessage().toString());
                msg(e);
            }
        }

        if(input.split("\\/").length == 2){
            String numbers[] = input.split("\\/");
            try{
                double d = Double.parseDouble(numbers[0]) /  Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = Decimal(output);
                outputText.setText(newOutput);
                input = newOutput + "";
            }catch (Exception e){
                //outputText.setError(e.getMessage().toString());
                msg(e);
            }
        }

        if(input.split("\\-").length == 2){
            String numbers[] = input.split("\\-");
            try{
                if(Double.parseDouble(numbers[0]) >= Double.parseDouble(numbers[1])){
                    double d = Double.parseDouble(numbers[0]) -  Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = Decimal(output);
                    outputText.setText(newOutput);
                    input = newOutput + "";
                }else{
                    double d = Double.parseDouble(numbers[1]) -  Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = Decimal(output);
                    outputText.setText("-"+newOutput);
                    input = "-"+newOutput + "";
                }
            }catch (Exception e){
                //outputText.setError(e.getMessage().toString());
                msg(e);
            }
        }

        if(input.split("\\^").length == 2){
            String numbers[] = input.split("\\^");
            try{
                double d = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = Decimal(output);
                outputText.setText(newOutput);
                input = newOutput + "";
            }catch (Exception e){
                //outputText.setError(e.getMessage().toString());
                msg(e);
            }
        }
    }

    private String Decimal(String n){
        String num[] = n.split("\\.");
        if(num.length > 1){
            if(num[1].equals("0")){
                n = num[0];
            }
        }
        return n;
    }

    private void msg(Exception e){
        Context context = getApplicationContext();
        CharSequence text = e.toString();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}