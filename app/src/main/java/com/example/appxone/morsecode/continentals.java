package com.example.appxone.morsecode;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by APPXONE on 10/14/2015.
 */
public class continentals {
    char value, value_spin;
    String get_hashvalue = "";
    String get_hashvalue1 = "";
    String spin_hashvalue = "";
    String american_text = "";
    int increment = 0;
    char get_morse_codes;
    int check11;
    int inc = 0, counter = 0;
    char get_words;
    int rrr = 0;
    String op;
    MainActivity main;
    int ca = 0;
    int check_inc=0;
String set_morse="";
    HashMap<String, String> continental_morse = new HashMap<>();

    public void Contenental_morse() {

        main = new MainActivity();
        continental_morse.put("a", ".- ");
        continental_morse.put("i", ".. ");

        continental_morse.put("s", "... ");


        continental_morse.put("r", ".-. ");

        continental_morse.put("p", "..... ");

        continental_morse.put("e", ". ");

        continental_morse.put("t", "- ");
        continental_morse.put("b", "-... ");

        continental_morse.put("c", "-.-. ");

        continental_morse.put("d", "-.. ");

        continental_morse.put("f", "..-. ");

        continental_morse.put("g", "--. ");


        continental_morse.put("h", ".... ");
        continental_morse.put("j", ".--- ");

        continental_morse.put("k", "-.- ");


        continental_morse.put("l", ".-.. ");


        continental_morse.put("m", "-- ");


        continental_morse.put("n", "-. ");


        continental_morse.put("o", ".-... ");
        continental_morse.put("p", "..... ");


        continental_morse.put("q", "--.- ");

        continental_morse.put("u", "..- ");

        continental_morse.put("v", "...- ");

        continental_morse.put("w", ".-- ");

        continental_morse.put("x", "..-... ");

        continental_morse.put("y", "--... ");

        continental_morse.put("z", ".--.. ");


        continental_morse.put("0", "--- ");


        continental_morse.put("1", ".--. ");

        continental_morse.put("2", "..-.. ");

        continental_morse.put("3", "...-. ");


        continental_morse.put("4", "....- ");

        continental_morse.put("5", "--- ");

        continental_morse.put("6", "...... ");

        continental_morse.put("7", "--.. ");

        continental_morse.put("8", "-.... ");

        continental_morse.put("9", "-..- ");
        continental_morse.put(" ", "/ ");

    }

    public void get_Continental() {
        MainActivity.morse_code.setText("");
        //   op = MainActivity.t.getText().toString();

       // check_inc=0;
        for ( ca = 0; ca < MainActivity.t.length(); ca++) {
            value = MainActivity.t.getText().charAt(ca);


            for (Map.Entry<String, String> entry2 : continental_morse.entrySet()) {

                //     String str = String.valueOf(et.getText().toString().toCharArray()[count1]);
                //  CharSequence char_sequence=(CharSequence) str;
                if (entry2.getKey().equalsIgnoreCase(value + "")) {
                    get_hashvalue = entry2.getValue().toString();


                    if (value == ' ') {
                        if (MainActivity.t.getText().charAt(ca)==' ' && ca==0) {
                            //check_inc++;
                            break;
                          //  check_inc++;
                            //MainActivity.play_stop.setImageResource(R.drawable.play_button);
                            //MainActivity.spin.setEnabled(true);

                        }


                        else  {
                            if (MainActivity.t.getText().charAt(ca) == ' ' && MainActivity.t.getText().charAt(ca-1)==' ') {


                                break;



                            }
                           else if (MainActivity.t.getText().charAt(ca) == ' ') {
                                set_morse=   "/ ";


                                MainActivity.morse_code.append(set_morse);
                                set_morse="";
                                break;



                            }

                        }
                         //   check_inc++;

                    }


                    //et.getText().toString();

                    else {
                        MainActivity.morse_code.append(get_hashvalue);
                       // check_inc++;
                        get_hashvalue = "";
                        break;

                    }
                }

            }

        }

    }


//    public void spin_Continental() {
//       // MainActivity.morse_code.setText("");
//       // String oi = MainActivity.t.getText().toString();
//        for (int cac = 0; cac < MainActivity.t.length(); cac++) {
//            value_spin = MainActivity.t.getText().charAt(cac);
//
//
//            for (Map.Entry<String, String> entry3 : continental_morse.entrySet()) {
//
//                //     String str = String.valueOf(et.getText().toString().toCharArray()[count1]);
//                //  CharSequence char_sequence=(CharSequence) str;
//                if (entry3.getKey().equalsIgnoreCase(value_spin + "")) {
//                    spin_hashvalue = entry3.getValue().toString();
//
//
//                    //et.getText().toString();
//
//
//                    MainActivity.morse_code.append(spin_hashvalue);
//
//                    spin_hashvalue = "";
//
//                }
//
//            }
//
//        }
//    }


    public void continental_morse_play() {


        check11 = 0;


//MainActivity.morse_code.setText("sdsdssdd");
        if (MainActivity.m.isPlaying()) {
            MainActivity.m.stop();
            MainActivity.play_stop.setImageResource(R.drawable.stop_pressed_button);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.play_stop.setImageResource(R.drawable.play_button);

                }
            }, 200);


            //  play_stop.setImageResource(R.drawable.play_button);

            //  b.setText("Play");
            // MainActivity.m.release();
            //MainActivity.m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            check11 = 1;
        }

        if (MainActivity.l.isPlaying()) {
            MainActivity.l.stop();

            MainActivity.play_stop.setImageResource(R.drawable.stop_pressed_button);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    MainActivity.play_stop.setImageResource(R.drawable.play_button);

                }
            }, 200);

            //     play_stop.setImageResource(R.drawable.play_button);

            //b.setText("Play");
            //              MainActivity.l.release();

//                l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            check11 = 1;
        }


        if (check11 == 0) {

//    MainActivity.morse_code.append("sdsdssdd");

            american_text = MainActivity.t.getText().toString();


            if (american_text.equalsIgnoreCase("")) {
                //  Toast toast1 = Toast.makeText(continentals.this, "Field cannot be empty", Toast.LENGTH_SHORT);
                //toast1.show();
            } else {
                for (int ii = 0; ii < american_text.length(); ii++) {
                    get_words = american_text.charAt(ii);
                    inc++;
                    // value = MainActivity.t.getText().charAt(cac);
                    //  if (counter == american_text.length()) {
                    //    MainActivity.play_stop.setImageResource(R.drawable.play_button);
                    // }

                    if (get_words == ' ') {

                        if (inc == american_text.length()) {

                        } else if (ii > 0) {
                            if (american_text.charAt(ii) == ' ' && american_text.charAt(ii - 1) == ' ') {

                            }
                        } else if (american_text.charAt(ii) == ' ') {
                            MainActivity.morse_code.append("/ ");

                        }
                    } else {

                        for (Map.Entry<String, String> entry22 : continental_morse.entrySet()) {

                            get_morse_codes = american_text.charAt(ii);
                            //     String str = String.valueOf(et.getText().toString().toCharArray()[count1]);
                            //  CharSequence char_sequence=(CharSequence) str;


                            if (entry22.getKey().equalsIgnoreCase(get_morse_codes + "")) {
                                get_hashvalue1 = entry22.getValue().toString();
                                //   MainActivity.morse_code.append("abce");

                                //et.getText().toString();
                                for (int loop = 0; loop < get_hashvalue.length(); loop++) {
                                    increment++;


                                    if (increment == get_hashvalue1.length() && get_hashvalue1.charAt(loop) == '.') {


                                        MainActivity.morse_code.append(String.valueOf(get_hashvalue1.charAt(loop)));
                                        MainActivity.m.start();
                                        MainActivity.m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                MainActivity.m.stop();
                                                // MainActivity.m.release();
                                                //   MainActivity.m = MediaPlayer.create(continentals.this, R.raw.morse_short);


                                                SystemClock.sleep(720);

                                            }
                                        });


                                    } else if (get_hashvalue1.charAt(loop) == '.') {

                                        MainActivity.morse_code.append(String.valueOf(get_hashvalue1.charAt(loop)));
                                        MainActivity.m.start();
                                        MainActivity.m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                MainActivity.m.stop();
                                                // MainActivity.m.release();
                                                //   MainActivity.m = MediaPlayer.create(continentals.this, R.raw.morse_short);

                                                SystemClock.sleep(240);

                                            }
                                        });


                                    } else if (increment == get_hashvalue1.length() && get_hashvalue1.charAt(loop) == '-') {
                                        MainActivity.morse_code.append(String.valueOf(get_hashvalue1.charAt(loop)));
                                        MainActivity.l.start();
                                        MainActivity.l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                MainActivity.l.stop();
                                                // MainActivity.m.release();
                                                //   MainActivity.m = MediaPlayer.create(continentals.this, R.raw.morse_short);

                                                SystemClock.sleep(720);

                                            }
                                        });
                                    } else if (get_hashvalue1.charAt(loop) == '-') {
                                        MainActivity.morse_code.append(String.valueOf(get_hashvalue1.charAt(loop)));
                                        MainActivity.l.start();
                                        MainActivity.l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mp) {
                                                MainActivity.l.stop();
                                                // MainActivity.m.release();
                                                //   MainActivity.m = MediaPlayer.create(continentals.this, R.raw.morse_short);

                                                SystemClock.sleep(240);

                                            }
                                        });
                                    } else if (get_hashvalue1.charAt(loop) == ' ') {
                                        MainActivity.morse_code.append(String.valueOf(get_hashvalue1.charAt(loop)));

                                    }


                                }

                                //  MainActivity.morse_code.append(get_hashvalue1);

                                // get_hashvalue1 = "";

                            }

                        }
                    }
                    counter++;

                }


            }

        }
        check11 = 0;

    }

}
