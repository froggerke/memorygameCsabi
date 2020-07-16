package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random randomBase;
    int randomNumbTemp;
    String randomNumbersString;

    int[] randomNumbers; // to get picture position
    int[] cardStates; //if flipped
    int[] cardPictures; //pictures on cards
    int[] flippedCards; //how many cards flipped and which
    int[] flippedPictures; //to check flipped cards
    int justFlippedCard, flippedCardsDown;
    boolean flipAble;

    public ImageView imageTemp, imageTempSave1, imageTempSave2;
    public ImageView imageView11, imageView12, imageView13, imageView14;
    public ImageView imageView21, imageView22, imageView23, imageView24;
    public int[] imageArray;
    public ImageView[] imageViewArray;



    public void clickOnCard(View view) {

        if (flipAble) {

            flipTheCardUp(view);

        }
        else {

            flipTheCardDown(view);

        }

         printLog();

    }

    private void flipTheCardDown(View view) {

        imageTemp = (ImageView) view;
        int cardTag = Integer.parseInt(imageTemp.getTag().toString());

        if (cardStates[cardTag - 1] == 1) {

            cardStates[cardTag - 1] = 0;

            flippedCardsDown++;
            imageTemp.setImageResource(imageArray[0]);

            if (flippedCardsDown == flippedCards[0]) {

                reSetVariablesBase();

            }
        }
    }

    private void flipTheCardUp(View view) {

        imageTemp = (ImageView) view;
            int cardTag = Integer.parseInt(imageTemp.getTag().toString());
            int cardNumber = cardPictures[cardTag - 1];

        if (cardStates[cardTag - 1] == 0) {

                cardStates[cardTag - 1] = 1;

                switch (cardNumber) {
                    case 1:
                        imageTemp.setImageResource(imageArray[1]);
                        break;
                    case 2:
                        imageTemp.setImageResource(imageArray[2]);
                        break;
                    case 3:
                        imageTemp.setImageResource(imageArray[3]);
                        break;
                    case 4:
                        imageTemp.setImageResource(imageArray[4]);
                        break;
                }

                justFlippedCard++;

                switch (justFlippedCard) {
                    case 1:
                        imageTempSave1 = imageTemp;
                        flippedPictures[0] = cardNumber;
                        break;
                    case 2:
                        imageTempSave2 = imageTemp;
                        flippedPictures[1] = cardNumber;
                        break;

                }

                flippedCards[0]++;
                flippedCards[justFlippedCard] = cardTag;

                if (flippedCards[0]==2) {

                    flipAble = false;

                }

            }

        if (flippedPictures[0] == flippedPictures[1]) {

            imageTempSave1.animate().alpha(0).setDuration(1000);
            imageTempSave2.animate().alpha(0).setDuration(1000);

            reSetVariablesBase();
        }

    }

    private void reSetVariablesBase() {

        flippedCardsDown = 0;
        flippedCards[0] = 0;
        justFlippedCard = 0;
        flipAble = true;

        flippedPictures[0] = 0;
        flippedPictures[1] = 0;

    }

    private void initialize() {

        flippedPictures = new int[2];
        justFlippedCard=0;
        flippedCardsDown=0;
        flipAble = true;
        flippedCards = new int[3];
        cardPictures = new int[8];
        cardStates = new int[8];
        randomNumbers= new int[8];
        imageArray = new int[5];
        imageViewArray = new ImageView[8];

        imageArray[0] = R.drawable.balna;
        imageArray[1] = R.drawable.hulye;
        imageArray[2] = R.drawable.japan;
        imageArray[3] = R.drawable.vazil;
        imageArray[4] = R.drawable.mufasza;

        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView23 = findViewById(R.id.imageView23);
        imageView24 = findViewById(R.id.imageView24);

        imageViewArray[0] = imageView11;
        imageViewArray[1] = imageView12;
        imageViewArray[2] = imageView13;
        imageViewArray[3] = imageView14;
        imageViewArray[4] = imageView21;
        imageViewArray[5] = imageView22;
        imageViewArray[6] = imageView23;
        imageViewArray[7] = imageView24;

        for (int i = 0; i<8;i++) {
            imageViewArray[i].setImageResource(R.drawable.balna);
        }
    }


    public void picToCardHandler() {

        randomNumbersString = "";
        randomBase = new Random();

        int j = 1;
        int jSwitcher = 0;
        for (int i = 0; i < 8; i++) {
            do {
                randomNumbTemp = randomBase.nextInt(8) + 1;

            }while(randomNumbersString.contains("t"+ randomNumbTemp + "t"));

            randomNumbersString += "t"+ randomNumbTemp + "t";
            randomNumbers[i] = randomNumbTemp;
            cardPictures[randomNumbTemp-1] = j;
            jSwitcher++;

            if (jSwitcher > 1){
                jSwitcher = 0;
                j++;
            }
        }

        for (int i = 0; i < 8; i++) {
            Log.i("RandomNumbs", String.valueOf(cardPictures[i]));
        }
    }

    private void printLog() {

        Log.i("Check", "\n" + "flipped cards " +flippedCards[0] + " " + flippedCards[1] + " "
                + flippedCards[2] + "\njustFlippedCard " + justFlippedCard + "\ncardStates:\n\t"
                + cardStates[0]+ " " + cardStates[1]+ " "+ cardStates[2]+ " "+ cardStates[3]+ " "
                + cardStates[4]+ " "+ cardStates[5]+ " "+ cardStates[6] + " "+ cardStates[7]
                + "\nif flipable "+ flipAble+ "\nflippedDown " + flippedCardsDown);
        Log.i("check2" , ""+ flippedPictures[0]+ " " + flippedPictures[1]);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //                          WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initialize();
        picToCardHandler();

        //setUpPicturesToCards();


    }
}