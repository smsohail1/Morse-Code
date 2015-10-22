package com.example.appxone.morsecode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by APPXONE on 10/14/2015.
 */
public class american {
    char american_value;
    String get_american_hashvalue = "";
    HashMap<String, String> american_morse = new HashMap<>();
    String set_morse_american = "";
    int cac=0;

    public void american_Morse() {
        american_morse.put("a", ".- ");
        american_morse.put("i", ".. ");

        american_morse.put("s", "... ");


        american_morse.put("r", ". .. ");

        american_morse.put("p", "..... ");

        american_morse.put("e", ". ");

        american_morse.put("t", "- ");
        american_morse.put("b", "-... ");

        american_morse.put("c", ".. . ");

        american_morse.put("d", "-.. ");

        american_morse.put("f", ".-. ");

        american_morse.put("g", "--. ");


        american_morse.put("h", ".... ");
        american_morse.put("j", "-.-. ");

        american_morse.put("k", "-.- ");


        american_morse.put("l", "-- ");


        american_morse.put("m", "-- ");


        american_morse.put("n", "-. ");


        american_morse.put("o", ". . ");
        american_morse.put("p", "..... ");


        american_morse.put("q", "..-. ");

        american_morse.put("u", "..- ");

        american_morse.put("v", "...- ");

        american_morse.put("w", ".-- ");

        american_morse.put("x", ".-.. ");

        american_morse.put("y", ".. .. ");

        american_morse.put("z", "... . ");


        american_morse.put("0", "---- ");


        american_morse.put("1", ".--. ");

        american_morse.put("2", "..-.. ");

        american_morse.put("3", "...-. ");


        american_morse.put("4", "....- ");

        american_morse.put("5", "--- ");

        american_morse.put("6", "...... ");

        american_morse.put("7", "--.. ");

        american_morse.put("8", "-.... ");

        american_morse.put("9", "-..- ");
        american_morse.put(" ", "/ ");


    }


    public void get_American() {
        MainActivity.morse_code.setText("");

        for (cac = 0; cac < MainActivity.t.length(); cac++) {
            american_value = MainActivity.t.getText().charAt(cac);


            for (Map.Entry<String, String> entry1 : american_morse.entrySet()) {

                //     String str = String.valueOf(et.getText().toString().toCharArray()[count1]);
                //  CharSequence char_sequence=(CharSequence) str;
                if (entry1.getKey().equalsIgnoreCase(american_value + "")) {
                    get_american_hashvalue = entry1.getValue().toString();
                    //et.getText().toString();


                    if (american_value == ' ') {
                        if (MainActivity.t.getText().charAt(cac) == ' ' && cac == 0) {
                            //check_inc++;
                            break;
                            //  check_inc++;
                            //MainActivity.play_stop.setImageResource(R.drawable.play_button);
                            //MainActivity.spin.setEnabled(true);

                        } else {
                            if (MainActivity.t.getText().charAt(cac) == ' ' && MainActivity.t.getText().charAt(cac - 1) == ' ') {


                                break;


                            } else if (MainActivity.t.getText().charAt(cac) == ' ') {
                                set_morse_american = "/ ";


                                MainActivity.morse_code.append(set_morse_american);
                                set_morse_american = "";
                                break;


                            }

                        }
                        //   check_inc++;

                    } else {
                        MainActivity.morse_code.append(get_american_hashvalue);

                        get_american_hashvalue = "";
                        break;
                    }
                }

            }

        }
    }


    public void american_morse_play() {

    }
}
