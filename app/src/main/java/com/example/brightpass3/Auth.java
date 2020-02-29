package com.example.brightpass3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import java.util.Scanner;

public class Auth extends AppCompatActivity {

    Randomarr ra=new Randomarr();
    TextView[] rarr;
    Button next;
    int secret;
    int pin=4869;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kim_et_al);

        ra.generate();
        initialise();
        assign_rand();
        secret=calculate_secret();
        System.out.println(secret);
        next.setText(Integer.toString(secret));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("secret", Integer.toString(secret));
                startActivity(intent);
            }
        });



    }

    int calculate_secret()
    {
        int temp,a[];
        a=new int[4];
        temp=pin;
        StringBuilder sb=new StringBuilder();
        for(int i=3;i>=0;i--)
        {
            int t=temp%10;
            a[i]=ra.b[(t-1)/5][(t-1)%5];temp=temp/10;
            System.out.print(a[i]+"    ");
        }
        temp=0;
        int index=ra.start;
        System.out.println(index);
        for(int i=0;i<4;i++)
        {

            switch(a[i])
            {
                case 0:
                {
                    index=(index+5)%25;
                    temp=temp*10+Integer.parseInt(rarr[index].getText().toString());
                    System.out.print(temp+"    ");
                    break;
                }

                case 1:
                {
                    index=(index+20)%25;
                    temp=temp*10+Integer.parseInt(rarr[(index+20)%25].getText().toString());
                    System.out.print(temp+"    ");
                    break;
                }

                case 2:
                {
                    if((index)%5==1)
                        index=index+4;
                    else index=index-1;
                    temp=temp*10+Integer.parseInt(rarr[index].getText().toString());
                    System.out.print(temp+"    ");
                    break;
                }

                case 3:
                {
                    if((index)%5==0)
                        index=index-4;
                    else index=index+1;
                    temp=temp*10+Integer.parseInt(rarr[index].getText().toString());
                    System.out.print(temp+"    ");
                    break;
                }

            }
            System.out.println(index);
        }
        return temp;
    }

    public void initialise()
    {
        rarr = new TextView[35];
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
        next = findViewById(R.id.button);
    }

    public void assign_rand()
    {

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
}

class Randomarr {
        int a[][] = new int[5][5];
        int b[][] = new int[2][5];
        int start;

        public void generate() {
            Random r = new Random();
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    a[i][j] = r.nextInt(10);
                }
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    b[i][j] = r.nextInt(4);
                    System.out.print(b[i][j]);
                }
                System.out.println();
            }
            start = r.nextInt(25);

        }
}
