package umn.ac.id.assignment01_42219;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.*;

public class MainActivity extends AppCompatActivity {

    TextView hasil;

    Button nol, satu, dua, tiga, empat, lima, enam, tujuh, delapan, sembilan;
    Button tambah, kali, bagi, kurang;
    Button c, ce, back, posneg, koma, samadengan;

    boolean isNegative = false;

    StringBuilder tempValue = new StringBuilder("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initiate buttons
        nol = this.findViewById(R.id.kosong);
        satu = this.findViewById(R.id.satu);
        dua = this.findViewById(R.id.dua);
        tiga = this.findViewById(R.id.tiga);
        empat = this.findViewById(R.id.empat);
        lima = this.findViewById(R.id.lima);
        enam = this.findViewById(R.id.enam);
        tujuh = this.findViewById(R.id.tujuh);
        delapan = this.findViewById(R.id.delapan);
        sembilan = this.findViewById(R.id.sembilan);
        //Operators
        tambah = this.findViewById(R.id.tambah);
        bagi = this.findViewById(R.id.bagi);
        kali = this.findViewById(R.id.kali);
        kurang = this.findViewById(R.id.kurang);
        //etc
        c = this.findViewById(R.id.c);
        ce = this.findViewById(R.id.ce);
        back = this.findViewById(R.id.back);
        posneg = this.findViewById(R.id.posneg);
        koma = this.findViewById(R.id.koma);
        samadengan = this.findViewById(R.id.samadengan);

        //TextView
        hasil = this.findViewById(R.id.result);

        //Listeners
        nol.setOnClickListener(v -> {
            tempValue.append("0");
            refreshView();
        });
        satu.setOnClickListener(view -> {
            tempValue.append("1");
            refreshView();
        });
        dua.setOnClickListener(view -> {
            tempValue.append("2");
            refreshView();
        });
        tiga.setOnClickListener(view -> {
            tempValue.append("3");
            refreshView();
        });
        empat.setOnClickListener(view -> {
            tempValue.append("4");
            refreshView();
        });
        lima.setOnClickListener(view -> {
            tempValue.append("5");
            refreshView();
        });
        enam.setOnClickListener(view -> {
            tempValue.append("6");
            refreshView();
        });
        tujuh.setOnClickListener(view -> {
            tempValue.append("7");
            refreshView();
        });
        delapan.setOnClickListener(view -> {
            tempValue.append("8");
            refreshView();
        });
        sembilan.setOnClickListener(view -> {
            tempValue.append("9");
            refreshView();
        });
        //Operators
        tambah.setOnClickListener(view -> {
            tempValue.append("+");
            refreshView();
        });
        kali.setOnClickListener(view -> {
            tempValue.append("*");
            refreshView();
        });
        bagi.setOnClickListener(view -> {
            tempValue.append("/");
            refreshView();
        });
        kurang.setOnClickListener(view -> {
            tempValue.append("-");
            refreshView();
        });
        //etc
        c.setOnClickListener(view -> {
            tempValue.setLength(0);
            tempValue.append("0");
            refreshView();
        });
        ce.setOnClickListener(view -> {
            tempValue.setLength(0);
            tempValue.append("0");
            refreshView();
        });
        koma.setOnClickListener(view -> {
            if(tempValue.charAt(tempValue.length()-1) != '.' && illegalCommaCheck(tempValue)) {
                tempValue.append(".");
            }
            refreshView();
        });
        posneg.setOnClickListener(view -> {
            Log.v("Before click : ", String.valueOf(isNegative));
            if(tempValue.equals("0")) return;
            if(isNegative){
                tempValue.deleteCharAt(0);
            }
            else{
                tempValue.insert(0, "-");
            }
            isNegative = !isNegative;
            Log.v("After click : ", String.valueOf(isNegative));
            refreshView();
        });
        back.setOnClickListener(view -> {
            if(tempValue.length() > 1){
                tempValue.setLength(tempValue.length()-1);
                refreshView();
                return;
            }
            if(tempValue.length() == 1 && tempValue.charAt(0) != '0'){
                tempValue.setCharAt(0, '0');
            }
            refreshView();
        });
        samadengan.setOnClickListener( view -> calculate());
    }

    boolean illegalCommaCheck(StringBuilder value){
        //for allowing 0.smth to be written in start
        if(value.charAt(0) == '0' && value.length()==1) return true;
        //return true if comma is allowed
        int lastCommaLocation = -1;
        //find the comma
        for(int i = 0;i<value.length();i++){
            if(value.charAt(i) == '.'){
                lastCommaLocation = i;
            }
        }
        //if no comma located return true now
        if(lastCommaLocation == -1){
            return true;
        }

        //from the comma location to the end of string find any operator

        for(int i = lastCommaLocation ; i<value.length() ; i++){
            if(value.charAt(i) == '+' || value.charAt(i) == '-' || value.charAt(i) == '*' || value.charAt(i) == '/'){
                return true;
            }
        }

        return false;
    }

    void calculate(){
        try{
            //Clean the leading 0 if exist
            if(tempValue.length()>1 && tempValue.charAt(0) == '0'){
                tempValue.deleteCharAt(0);
            }
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Log.v("Evaluating ", tempValue.toString());
            Object result = engine.eval(tempValue.toString());
            hasil.setText(result.toString());
        }
        catch (Exception e){
            hasil.setText(e.getMessage());
        }
    }

    void refreshView(){
        try{
            Log.v("Char length:", String.valueOf(tempValue.length()));
            if(tempValue.length()>1 && tempValue.charAt(0) == '0'){
                tempValue.deleteCharAt(0);
            }
            Log.v("tempValue : ", tempValue.toString());
            if(tempValue.charAt(0) == '-' && tempValue.charAt(1) == '0'){
                tempValue.deleteCharAt(0);
            }
            String display = tempValue.toString();
            hasil.setText(display);
            tempValue.setLength(0);
            tempValue.append(display);
        }
        catch (Exception e){
            Toast toast=Toast.makeText(getApplicationContext(),"Idk what was happened but it is bad, really bad, i restarted the app so things go normal again", Toast.LENGTH_SHORT);
            toast.show();
            hasil.setText("0");
        }
    }
}