package com.example.prueba;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView disp, disp2, et1;
    String aux, aux2;
    Button bpres;
    Double memoria;
    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void bot0(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "0");
    }

    public void bot1(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "1");
    }

    public void bot2(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "2");
    }

    public void bot3(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "3");
    }

    public void bot4(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "4");
    }

    public void bot5(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "5");
    }

    public void bot6(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "6");
    }

    public void bot7(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "7");
    }

    public void bot8(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "8");
    }

    public void bot9(View view) {
        disp = findViewById(R.id.disp);
        disp.setText(disp.getText() + "9");
    }

    public void botpunt(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + ".");
        bpres.setEnabled(false);
    }

    public void suma(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "+");
        bpres.setEnabled(true);
    }

    public void resta(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "-");
        bpres.setEnabled(true);
    }

    public void multi(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "*");
        bpres.setEnabled(true);
    }

    public void divi(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "/");
        bpres.setEnabled(true);
    }

    public void limp(View view) {
        bpres = findViewById(R.id.bp);
        disp = findViewById(R.id.disp);
        disp.setText("");
        bpres.setEnabled(true);
    }

    public void borra(View view) {
        disp = findViewById(R.id.disp);
        if (disp.length() > 0) {
            aux = disp.getText().toString();
            aux = aux.substring(0, aux.length() - 1);
            disp.setText(aux);
        } else {
            disp.setText(" ");
        }

    }

    public void igual(View view) {
        disp = findViewById(R.id.disp);
        if (disp.length() > 0) {
            aux = disp.getText().toString();
            disp.setText(eval(aux) + "");
            aux2 = et1.getText().toString();
            et1.setText(aux2 + " , " + aux);
        } else {
            disp.setText("");
        }

    }


    public static double eval(final String str) {

        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void parab(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "(");
        bpres.setEnabled(true);
    }

    public void parcer(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + ")");
        bpres.setEnabled(true);
    }

    public void sen(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "sin(");
        bpres.setEnabled(true);
    }

    public void cos(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "cos(");
        bpres.setEnabled(true);
    }

    public void tan(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "tan(");
        bpres.setEnabled(true);
    }

    public void sqrt(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "sqrt(");
        bpres.setEnabled(true);
    }

    public void porcen(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "%");
        bpres.setEnabled(true);
    }

    public void expo(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "^");
        bpres.setEnabled(true);
    }

    public void inv(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText("1/" + disp.getText());
        bpres.setEnabled(true);
    }

    public void pi(View view) {
        disp = findViewById(R.id.disp);
        bpres = findViewById(R.id.bp);
        disp.setText(disp.getText() + "π");
        bpres.setEnabled(true);
    }

    public void memag(View view) {
        disp = findViewById(R.id.disp);
        disp2 = findViewById(R.id.disp2);
        if (disp.length() == 0) {
            disp2.setText("");
        } else {
            memoria = Double.parseDouble(disp.getText() + "");
            disp2.setText(String.valueOf(memoria));
        }
    }

    //-------------------------------- Prueba de cargado y guardado de datos


    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }
}

