package com.example.Pinv4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Auth extends AppCompatActivity {

    Sequencearr ra=new Sequencearr();
    TextView pop1,pop2;
    Button closePopupBtn;
    PopupWindow popupWindow;
    ConstraintLayout linearLayout1;
    Context context;
    TextView[] rarr;
    LieSequence l;
    ArrayList<Integer> i1;
    Iterator itr;
    int secretKeyCount = 0;
    int pin_directions[] = new int[4];
    int pin = 7890;
    int previous_key;
    int present_position = 0;
    int success = 1;
    int z;
    String evalue = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_interface);
        ra.generate();
        initialise();
        assign_rand();
        previous_key = ra.start + 1;
        context = getApplicationContext();
    }

    public void perform_action(View v) {
        rarr[ra.start].setBackgroundResource(0);
        if(secretKeyCount < 7) {
            TextView tv = (TextView) findViewById(v.getId());
            check_key(secretKeyCount, (String) tv.getText());
            tv.setTextColor(Color.GREEN);
            secretKeyCount++;
        }
    }

    public void check_key(int position, String key) {
        int t1 = -1;
        if(z == position) {
            int direction = pin_directions[present_position++];
            if (direction == 0) {
                t1 = (previous_key + 5) % 25;
            }

            if (direction == 1) {
                t1 = (previous_key + 20) % 25;
            }

            if (direction == 2) {
                if (previous_key % 5 == 1) {
                    t1 = (previous_key + 4);
                } else {
                    t1 = (previous_key - 1);
                }

            }

            if (direction == 3) {
                if (previous_key % 5 == 0) {
                    t1 = (previous_key - 4);
                } else {
                    t1 = (previous_key + 1);
                }
            }

            if(t1 != Integer.parseInt(key)) {
                success = 0;
            }
            if(itr.hasNext()) {
                z = (int) itr.next();
            }
        }

        if(position == 6) {
            popup();
        }
        previous_key = Integer.parseInt(key);
        checkbrigthness(position+1);
    }

    public void assign_rand() {

        for(int i=0;i<25;i++)
        {
            rarr[i].setText(Integer.toString(ra.a[i/5][i%5]));
        }

        for(int i=25;i<35;i++)
        {
            if(ra.b[(i/5)-5][i%5]==0)
            {
                rarr[i].setBackgroundResource(R.drawable.down);
            }

            if(ra.b[(i/5)-5][i%5]==1)
            {
                rarr[i].setBackgroundResource(R.drawable.up);
            }

            if(ra.b[(i/5)-5][i%5]==2)
            {
                rarr[i].setBackgroundResource(R.drawable.left);
            }

            if(ra.b[(i/5)-5][i%5]==3)
            {
                rarr[i].setBackgroundResource(R.drawable.right);
            }
            rarr[ra.start].setBackgroundResource(R.drawable.start);
        }
    }

    public void check_key_directions() {
        int temp_pin = pin;
        int array[] = new int[]{0 , 0 , 0 ,0};
        int count = 3;
        while(true) {
            if(temp_pin > 0) {
                array[count--] = temp_pin % 10;
                temp_pin = temp_pin/10;
            }
            else {
                break;
            }
        }
        for (int i = 0 ; i < 4; i++) {
            int temp = array[i] - 1;
            if(temp < 0) {
                temp +=10;
            }
            int x = temp / 5;
            int y = temp % 5;
            pin_directions[i] = ra.b[x][y];
        }
    }

    public void checkbrigthness(int k)
    {
        if(i1.contains(k))
            changeScreenBrightness(context, 240);
        else
            changeScreenBrightness(context, 5);
    }

    private void changeScreenBrightness(Context context, int screenBrightnessValue)
    {
        // Change the screen brightness change mode to manual.
        // Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        // Apply the screen brightness value to the system, this will change the value in Settings ---> Display ---> Brightness level.
        // It will also change the screen brightness for the device.
        // Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, screenBrightnessValue);

        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = screenBrightnessValue / 255f;
        window.setAttributes(layoutParams);

    }

    public void popup () {
        LayoutInflater layoutInflater = (LayoutInflater) Auth.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup, null);
        closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
        pop2=(TextView)customView.findViewById(R.id.textView9);
        pop1=(TextView)customView.findViewById(R.id.textView50);

        if(success == 1) {
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

    public void initialise() {
        rarr = new TextView[35];
        linearLayout1 = (ConstraintLayout) findViewById(R.id.linearLayout1);
        rarr[0] = findViewById(R.id.textView11);
        rarr[1] = findViewById(R.id.textView12);
        rarr[2] = findViewById(R.id.textView13);
        rarr[3] = findViewById(R.id.textView14);
        rarr[4] = findViewById(R.id.textView15);
        rarr[5] = findViewById(R.id.textView21);
        rarr[6] = findViewById(R.id.textView22);
        rarr[7] = findViewById(R.id.textView23);
        rarr[8] = findViewById(R.id.textView24);
        rarr[9] = findViewById(R.id.textView25);
        rarr[10] = findViewById(R.id.textView31);
        rarr[11] = findViewById(R.id.textView32);
        rarr[12] = findViewById(R.id.textView33);
        rarr[13] = findViewById(R.id.textView34);
        rarr[14] = findViewById(R.id.textView35);
        rarr[15] = findViewById(R.id.textView41);
        rarr[16] = findViewById(R.id.textView42);
        rarr[17] = findViewById(R.id.textView43);
        rarr[18] = findViewById(R.id.textView44);
        rarr[19] = findViewById(R.id.textView45);
        rarr[20] = findViewById(R.id.textView51);
        rarr[21] = findViewById(R.id.textView52);
        rarr[22] = findViewById(R.id.textView53);
        rarr[23] = findViewById(R.id.textView54);
        rarr[24] = findViewById(R.id.textView55);
        rarr[25] = findViewById(R.id.textView61);
        rarr[26] = findViewById(R.id.textView62);
        rarr[27] = findViewById(R.id.textView63);
        rarr[28] = findViewById(R.id.textView64);
        rarr[29] = findViewById(R.id.textView65);
        rarr[30] = findViewById(R.id.textView71);
        rarr[31] = findViewById(R.id.textView72);
        rarr[32] = findViewById(R.id.textView73);
        rarr[33] = findViewById(R.id.textView74);
        rarr[34] = findViewById(R.id.textView75);
        l = new LieSequence();
        l.generate();
        i1 = l.i1;
        itr = i1.iterator();
        z = (int) itr.next();
        check_key_directions();
        checkbrigthness(present_position);
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
        checkbrigthness(Integer.parseInt(evalue));
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkbrigthness(Integer.parseInt(evalue));
    }
}

class Sequencearr {
    int a[][] = new int[5][5];
    int b[][] = new int[2][5];
    int start;

    public void generate() {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int count = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                a[i][j] = count++;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                b[i][j] = r.nextInt(4);
            }
        }
        start = r.nextInt(25);

    }
}

class LieSequence {
    String Lie;
    ArrayList<Integer> i1;
    LieSequence()
    {
        Lie=new String("0000000");
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
