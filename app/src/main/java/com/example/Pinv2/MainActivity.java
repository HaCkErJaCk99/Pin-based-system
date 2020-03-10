package com.example.Pinv2;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Randomarr ra = new Randomarr();
    TextView[] rarr;
    TextView[] e;
    TextView pop1,pop2;
    TextView time;
    String pin;
    Button closePopupBtn;
    PopupWindow popupWindow;
    ConstraintLayout linearLayout1;
    String evalue="1";
    LieSequence l;
    ArrayList<Integer> i1;
    Context context;
    Calendar c;
    String finalpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR);
        int mMinute = c.get(Calendar.MINUTE);
        time = findViewById(R.id.editText20);
        if (mMinute < 10 && mHour < 10)
        {
            time.setText("0" + mHour + ":0" + mMinute);
        }
        else if (mMinute < 10)
        {
            time.setText(mHour + ":0" + mMinute);
        }
        else if (mHour < 10)
        {
            time.setText("0" + mHour + ":" + mMinute);
        }
        else {
            time.setText(mHour + ":" + mMinute);
        }
        initialise();
        assign_rand();
        finalpin = calculate_secret();

        for (int i = 0; i < 7; i++)
            e[i].setInputType(InputType.TYPE_NULL);
        e[0].requestFocus();
        e[0].setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                evalue = "1";
                return false;
            }
        });

        e[1].setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                evalue = "2";
                return false;
            }
        });

        e[2].setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                evalue = "3";
                return false;
            }
        });

        e[3].setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                evalue = "4";
                return false;
            }
        });

        e[4].setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                evalue = "5";
                return false;
            }
        });

        e[5].setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                evalue = "6";
                return false;
            }
        });

        e[6].setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                evalue = "7";
                return false;
            }
        });

    }

    public void initialise() {
        rarr = new TextView[10];
        pin = "1234";
        l = new LieSequence();
        l.generate();
        i1 = l.i1;
        e = new TextView[7];
        linearLayout1 = findViewById(R.id.linearLayout1);
        e[0] = findViewById(R.id.textView2);
        e[1] = findViewById(R.id.textView3);
        e[2] = findViewById(R.id.textView7);
        e[3] = findViewById(R.id.textView6);
        e[4] = findViewById(R.id.textView5);
        e[5] = findViewById(R.id.textView8);
        e[6] = findViewById(R.id.textView4);
        rarr[0] = findViewById(R.id.textView);
        rarr[1] = findViewById(R.id.textView10);
        rarr[2] = findViewById(R.id.textView12);
        rarr[3] = findViewById(R.id.textView11);
        rarr[4] = findViewById(R.id.textView13);
        rarr[5] = findViewById(R.id.textView17);
        rarr[6] = findViewById(R.id.textView16);
        rarr[7] = findViewById(R.id.textView18);
        rarr[8] = findViewById(R.id.textView15);
        rarr[9] = findViewById(R.id.textView14);
        ra.generate();
    }

    public void assign_rand() {
        for (int i = 0; i < 10; i++) {
            rarr[i].setText(Integer.toString(ra.b[i / 5][i % 5]));
        }
    }

    String calculate_secret() {
        int temp = Integer.parseInt(pin);
        int[] aa;
        String secretpin = "";
        aa = new int[4];
        for (int i = 0; i < 4; i++) {
            aa[3 - i] = temp % 10 + Integer.parseInt(rarr[temp % 10 - 1].getText().toString());
            aa[3 - i] = aa[3 - i] % 10;
            temp = temp / 10;
        }
        for (int i = 0; i < 4; i++) {
            temp = temp * 10 + aa[i];
        }
        secretpin = Integer.toString(temp);
        if (secretpin.length() != 4)
            secretpin = "0" + secretpin;
        return secretpin;

    }
    private void changeScreenBrightness(Context context, int screenBrightnessValue) {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = screenBrightnessValue / 255f;
        window.setAttributes(layoutParams);
    }

    public void enterpin(View view) {
        switch (view.getId()) {
            case R.id.button26: entertext("0",evalue);
                break;
            case R.id.button4: entertext("1",evalue);
                break;
            case R.id.button16:entertext("2",evalue);
                break;
            case R.id.button17:entertext("3",evalue);
                break;
            case R.id.button18:entertext("4",evalue);
                break;
            case R.id.button20:entertext("5",evalue);
                break;
            case R.id.button19:entertext("6",evalue);
                break;
            case R.id.button21:entertext("7",evalue);
                break;
            case R.id.button22:entertext("8",evalue);
                break;
            case R.id.button23:entertext("9",evalue);
                break;
            case R.id.button24:entertext("Ok",evalue);
                break;
            case R.id.button25:entertext("Del",evalue);
                break;

        }
    }

    public boolean checkpin() {

        StringBuilder sb=new StringBuilder();
        Iterator i = i1.iterator();
        for(int k=0;k<4;k++)
        {
            sb.append(e[(int)i.next()].getText());
        }
        return sb.toString().equals(finalpin);
    }

    public void checkbrigthness(int k) {
        if(i1.contains(k))
            changeScreenBrightness(context, 240);
        else
            changeScreenBrightness(context, 5);
    }

    public void entertext(String k,String t) {
            if(!k.equals("Del") && !k.equals("Ok"))
            {
                switch (t) {
                    case "1": e[0].setText(k);checkbrigthness(1);e[1].requestFocus();evalue="2";
                        break;
                    case "2": e[1].setText(k);checkbrigthness(2);e[2].requestFocus();evalue="3";
                        break;
                    case "3": e[2].setText(k);checkbrigthness(3);e[3].requestFocus();evalue="4";
                        break;
                    case "4": e[3].setText(k);checkbrigthness(4);e[4].requestFocus();evalue="5";
                        break;
                    case "5": e[4].setText(k);checkbrigthness(5);e[5].requestFocus();evalue="6";
                        break;
                    case "6": e[5].setText(k);checkbrigthness(6);e[6].requestFocus();evalue="7";
                        break;
                    case "7": e[6].setText(k);evalue="8";
                        break;

                        default:evalue="8";break;
                }
            }

            else if(k.equals("Del"))
            {
                switch (t) {
                    case "1":e[0].setText("");checkbrigthness(0);
                        break;
                    case "2":e[0].setText("");evalue="1";checkbrigthness(0);e[0].requestFocus();
                        break;
                    case "3":e[1].setText("");evalue="2";checkbrigthness(1);e[1].requestFocus();
                        break;
                    case "4":e[2].setText("");evalue="3";checkbrigthness(2);e[2].requestFocus();
                        break;
                    case "5":e[3].setText("");evalue="4";checkbrigthness(3);e[3].requestFocus();
                        break;
                    case "6":e[4].setText("");evalue="5";checkbrigthness(4);e[4].requestFocus();
                        break;
                    case "7":e[5].setText("");evalue="6";checkbrigthness(5);e[5].requestFocus();
                        break;
                    case "8":e[6].setText("");evalue="7";checkbrigthness(6);e[6].requestFocus();
                }
            }

            else
            {
                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup, null);
                closePopupBtn = customView.findViewById(R.id.closePopupBtn);
                pop2 = customView.findViewById(R.id.textView9);
                pop1 = customView.findViewById(R.id.textView50);
                boolean valid=checkpin();

              if(valid) {

                  popupWindow = new PopupWindow(customView, 700, 500);
                  popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
                  pop1.setVisibility(View.VISIBLE);
                  closePopupBtn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          popupWindow.dismiss();
                      }
                  });
              }
              else {

                  popupWindow = new PopupWindow(customView, 800, 500);
                  popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
                  pop2.setVisibility(View.VISIBLE);
                  closePopupBtn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          popupWindow.dismiss();
                      }
                  });
              }


            }

        }

    @Override
    protected void onPause() {
        super.onPause();
        changeScreenBrightness(context, 50);
    }

    @Override
    protected void onStop() {
        super.onStop();
        changeScreenBrightness(context, 50);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkbrigthness(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

class LieSequence {
    String Lie;
    ArrayList<Integer> i1;
    LieSequence()
    {
        Lie = "0000000";
    }

    void generate()
    {
        Random rand = new Random();
        i1=new ArrayList<Integer>();
        while(i1.size()!=4)
        {
            i1.add(rand.nextInt(7));
            i1=removeDuplicates(i1);
        }
        i1.sort(null);
        createSeq(i1);
    }

    void createSeq(ArrayList<Integer> list)
    {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<7;i++)
        {
            if(list.contains(i))
                sb.append("1");
            else sb.append("0");
        }
        Lie=sb.toString();

    }

    public <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list)
        {
            if (!newList.contains(element))
            {
                newList.add(element);
            }
        }
        return newList;
    }
}

class Randomarr {
    int[][] b = new int[2][5];
    public void generate() {
        Random r = new Random();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++)
            {
                b[i][j] = r.nextInt(10);
            }
        }
    }
}