package com.example.calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    StringBuilder help = new StringBuilder();
    StringBuilder useHelp = new StringBuilder();
    ArrayList<String> use = new ArrayList<>();
    TextView Shows;
    boolean flag = false;
    boolean flagKuohao = false;
    int NumKuohao = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button plus = (Button) findViewById(R.id.plusButton);
        Button minus = (Button) findViewById(R.id.minusButton);
        Button times = (Button) findViewById(R.id.timesButton);
        Button divide = (Button) findViewById(R.id.divideButton);
        Button C = (Button) findViewById(R.id.ButtonC);
        Button equals = (Button) findViewById(R.id.ButtonEquals);
        Button left = (Button) findViewById(R.id.ButtonLeft);
        Button right = (Button) findViewById(R.id.ButtonRight);
        Button Back = (Button) findViewById(R.id.ButtonBack);
        TextView textView = (TextView) findViewById(R.id.ShowingText);
        Button b0 = (Button) findViewById(R.id.Button0);
        Button b1 = (Button) findViewById(R.id.Button1);
        Button b2 = (Button) findViewById(R.id.Button2);
        Button b3 = (Button) findViewById(R.id.Button3);
        Button b4 = (Button) findViewById(R.id.Button4);
        Button b5 = (Button) findViewById(R.id.Button5);
        Button b6 = (Button) findViewById(R.id.Button6);
        Button b7 = (Button) findViewById(R.id.Button7);
        Button b8 = (Button) findViewById(R.id.Button8);
        Button b9 = (Button) findViewById(R.id.Button9);
        Button point = (Button) findViewById(R.id.ButtonPoint);

        point.setOnClickListener(this);
        Back.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        times.setOnClickListener(this);
        divide.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        C.setOnClickListener(this);
        equals.setOnClickListener(this);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);

        Shows = textView;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v){
        if(flag)
        {
            help.delete(0,help.length());
            Shows.setText("");
            flag = false;
            return;
        }
        switch (v.getId())
        {
            case R.id.Button0:
                help.append('0');
                useHelp.append('0');
                //Toast.makeText(MainActivity.this,"0",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button1:
                help.append('1');
                useHelp.append('1');
                break;
            case R.id.Button2:
                help.append('2');
                useHelp.append('2');
                break;
            case R.id.Button3:
                help.append('3');
                useHelp.append('3');
                break;
            case R.id.Button4:
                help.append('4');
                useHelp.append('4');
                break;
            case R.id.Button5:
                help.append('5');
                useHelp.append('5');
                break;
            case R.id.Button6:
                help.append('6');
                useHelp.append('6');
                break;
            case R.id.Button7:
                help.append('7');
                useHelp.append('7');
                break;
            case R.id.Button8:
                help.append('8');
                useHelp.append('8');
                break;
            case R.id.Button9:
                help.append('9');
                useHelp.append('9');
                break;
            case R.id.ButtonBack:
                if(help.length() > 0)
                {  help.deleteCharAt(help.length()-1);
                    if(useHelp.length() > 0)
                    {
                        useHelp.deleteCharAt(useHelp.length()-1);
                    }
                    else{
                        String temp = use.get(use.size()-1);
                        if(temp.equals("+") || temp.equals("-") ||
                                temp.equals("*") || temp.equals("/"))
                        {
                            use.remove(use.size()-1);
                        }else{
                        useHelp.append(use.get(use.size()-1));
                            use.remove(use.size()-1);
                        }

                    }
                }
                break;
            case R.id.ButtonC:
                help.delete(0,help.length());
                useHelp.delete(0,useHelp.length());
                use.clear();
                break;
            case R.id.ButtonLeft:
                help.append('(');
                use.add("(");
                NumKuohao++;
                flagKuohao = true;
                break;
            case R.id.ButtonRight:
                if(use.size() == 0 || use.get(use.size()-1).equals("("))
                {
                    Toast.makeText(MainActivity.this,"计算式有误",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(flagKuohao == false)
                {
                    Toast.makeText(MainActivity.this,"没有相应的左括号！",Toast.LENGTH_SHORT).show();
                    return;
                }
                help.append(')');
                if(useHelp.length() != 0)
                    use.add(useHelp.toString());

                use.add(")");
                NumKuohao--;
                if(NumKuohao == 0)
                    flagKuohao = false;
                useHelp.delete(0,useHelp.length());

                break;
            case  R.id.plusButton:
                if(useHelp.length() > 0)
                {use.add(useHelp.toString());
                 useHelp.delete(0,useHelp.length());
                }
                if(use.size()==0 || use.get(use.size()-1).equals("+") ||use.get(use.size()-1).equals("-")
                ||use.get(use.size()-1).equals("*")||use.get(use.size()-1).equals("/"))
                {
                    Toast.makeText(MainActivity.this,"计算式有误！",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    help.append('+');
                    use.add("+");
                }
                break;
            case R.id.minusButton:

                if(useHelp.length() > 0)
                    use.add(useHelp.toString());
                if(use.size()==0 || use.get(use.size()-1).equals("+") ||use.get(use.size()-1).equals("-")
                        ||use.get(use.size()-1).equals("*")||use.get(use.size()-1).equals("/"))
                {
                    Toast.makeText(MainActivity.this,"计算式有误！",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    help.append('-');
                }
                use.add("-");
                useHelp.delete(0,useHelp.length());
                break;
            case R.id.timesButton:

                if(useHelp.length() > 0)
                    use.add(useHelp.toString());
                if(use.size()==0 || use.get(use.size()-1).equals("+") ||use.get(use.size()-1).equals("-")
                        ||use.get(use.size()-1).equals("*")||use.get(use.size()-1).equals("/"))
                {
                    Toast.makeText(MainActivity.this,"计算式有误！",Toast.LENGTH_SHORT).show();
                    return;
                }
                help.append('*');
                use.add("*");
                useHelp.delete(0,useHelp.length());
                break;
            case R.id.divideButton:

                if(useHelp.length() > 0)
                    use.add(useHelp.toString());
                if(use.size()==0 || use.get(use.size()-1).equals("+") ||use.get(use.size()-1).equals("-")
                        ||use.get(use.size()-1).equals("*")||use.get(use.size()-1).equals("/"))
                {
                    Toast.makeText(MainActivity.this,"计算式有误！",Toast.LENGTH_SHORT).show();
                    return;
                }
                help.append('/');
                use.add("/");
                useHelp.delete(0,useHelp.length());
                break;
            case R.id.ButtonPoint:

                if(useHelp.length() == 0)
                {
                    Toast.makeText(MainActivity.this,"小数点前必须有整数！！",Toast.LENGTH_SHORT).show();
                }else{
                    if(useHelp.charAt(useHelp.length()-1)!='.'){
                        help.append('.');
                        useHelp.append('.');}
                    else{
                        Toast.makeText(MainActivity.this,"计算式有误",Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.ButtonEquals:
                if(use.size() == 0)
                {
                    Toast.makeText(MainActivity.this,"请输入计算公式",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(useHelp.length() != 0)
                    use.add(useHelp.toString());
                else
                {
                    if(!use.get(use.size()-1).equals(")"))
                    {
                        Toast.makeText(MainActivity.this,"计算式有误！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(flagKuohao)
                {
                    while(NumKuohao > 0)
                        {
                            use.add(")");
                            NumKuohao--;
                        }
                    flagKuohao = false;
                }

                useHelp.delete(0,useHelp.length());
                String[] fin = new String[1];
                fin = CalCul.midToend(use.toArray(use.toArray(fin)));
                float shows;
                if(fin[0] != null)
                {
                    shows = CalCul.evalRPN(fin);
                    String helpfina = CalCul.fmt_prt_double(shows);
                    use.clear();
                    help.delete(0,help.length());
                    help.append(helpfina);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"计算式有误",Toast.LENGTH_SHORT).show();
                    return;
                }

                flag = true;

                //Toast.makeText(MainActivity.this,""+shows,Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        Shows.setText(help.toString());
    }
}