package com.example.appxone.morsecode;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static MediaPlayer m, l;
    Button b, translate, stop, download;
    public static EditText t;
    public static ImageView play_stop;
    int inc = 0;
    ArrayList<String> allEds = new ArrayList<String>();
   public static char text_char[] = new char[100];
    public  static String get_text_box;
 public  static   int text_length;
  public static String morse_code_text;
    public static TextView morse_code;
    TextView ll, ss;
    int check = 0;
    int check_continental = 0;
    int check_american = 0;
    char eeeq;
    boolean bool;
    InputMethodManager inputManager;
    continentals c1;
    american a0;
    LinearLayout dropdown;
    String char_seq = "";
    TextWatcher textWatcher;
    public static Spinner spin;
    HashMap<String, String> hashmap = new HashMap<>();
    String item_spinner;
    int positon_spinner = 0;
    int get_share_pref;
    int positon_spinner23;

    SharedPreferences share_pref_get;

    //    public   String item="International(ITU)";
//    public   int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_stop = (ImageView) findViewById(R.id.sss);
        t = (EditText) findViewById(R.id.morsecode_box);
        morse_code = (TextView) findViewById(R.id.morse_code);
        m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
        l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
        dropdown = (LinearLayout) findViewById(R.id.spinner);
        spin = (Spinner) findViewById(R.id.spinner2);

        c1 = new continentals();

        a0 = new american();
        a0.american_Morse();
        c1.Contenental_morse();


        // Typeface type_face=Typeface.createFromAsset(getAssets(), "fonts/helveticaltstd_roman_webfont.woff");
        //t.setTypeface(type_face);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/helveticaltstd_roman_webfont.ttf");

        t.setTypeface(custom_font);

        SharedPreferences share_pref = getApplicationContext().getSharedPreferences("option", MODE_PRIVATE);
        SharedPreferences.Editor edit = share_pref.edit();

        positon_spinner23 = onItemselected.position;
        edit.putInt("morse", positon_spinner23);
        edit.commit();


        share_pref_get = getApplicationContext().getSharedPreferences("option", MODE_PRIVATE);


        get_share_pref = share_pref_get.getInt("morse", 0);




        ArrayAdapter<String> array = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdownselected,
                R.id.tv_dropDownMenu, getResources().getStringArray(R.array.select_language));
        spin.setAdapter(array);
        spin.setSelection(get_share_pref);

        t.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (m.isPlaying() || l.isPlaying()) {
                    //      hideKeyboard(v);
                    inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    bool = true;
                } else {
                    bool = false;
                }

                return bool;
            }
        });


        morse_code_text = "";
        hashmap.put("a", ".- ");
        hashmap.put("i", ".. ");

        hashmap.put("s", "... ");


        hashmap.put("r", ".-. ");


        hashmap.put("p", ".--. ");

        hashmap.put("e", ". ");

        hashmap.put("t", "- ");

        hashmap.put("b", "-... ");


        hashmap.put("c", "-.-. ");


        hashmap.put("d", "-.. ");

        hashmap.put("f", "..-. ");


        hashmap.put("g", "--. ");


        hashmap.put("h", ".... ");


        hashmap.put("j", ".--- ");

        hashmap.put("k", "-.- ");


        hashmap.put("l", ".-.. ");

        hashmap.put("m", "-- ");


        hashmap.put("n", "-. ");


        hashmap.put("o", "--- ");


        hashmap.put("p", ".--. ");


        hashmap.put("q", "--.- ");

        hashmap.put("u", "..- ");


        hashmap.put("v", "...- ");


        hashmap.put("w", ".-- ");


        hashmap.put("x", "-..- ");


        hashmap.put("y", "-.-- ");


        hashmap.put("z", "--.. ");


        hashmap.put("0", "----- ");

        hashmap.put("1", ".---- ");

        hashmap.put("2", "..--- ");

        hashmap.put("3", "...-- ");


        hashmap.put("4", "....- ");

        hashmap.put("5", "..... ");

        hashmap.put("6", "-.... ");

        hashmap.put("7", "--... ");

        hashmap.put("8", "---.. ");

        hashmap.put("9", "----. ");

        hashmap.put(".", ".-.-.- ");


        hashmap.put(",", "--..-- ");

        hashmap.put("?", "..--.. ");


        hashmap.put("=", "-...- ");


        hashmap.put("-", "-....- ");

        hashmap.put("/", "-..-. ");


        hashmap.put("@", ".--.-. ");


        hashmap.put("~", ".-... ");


        hashmap.put("#", "...-.- ");


        hashmap.put("$", "...-..- ");


        hashmap.put("\"", ".-..-. ");


        hashmap.put("&", ".-... ");


        hashmap.put("+", ".-.-. ");


        hashmap.put("!", "-.-.-- ");


        hashmap.put("_", "..--.- ");


        hashmap.put("'", ".----. ");


        hashmap.put(")", "-.--.- ");

        hashmap.put("(", "-.--. ");


        hashmap.put(";", "-.-.-. ");

        hashmap.put(" ", "/ ");

        spin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(m.isPlaying() || l.isPlaying())
                {
                  //  spin.getSelectedView();
                    spin.setEnabled(false);

                }
else {
                    spin.setOnItemSelectedListener(new onItemselected());
                }
//                SharedPreferences share_pref=getApplicationContext().getSharedPreferences("option", MODE_PRIVATE);
//                SharedPreferences.Editor edit=share_pref.edit();
//
//                positon_spinner23 = onItemselected.position;
//                edit.putInt("morse",positon_spinner23);
//                edit.commit();


                return false;
            }
        });


        textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


                item_spinner = onItemselected.item.toString();
                positon_spinner = onItemselected.position;
                SharedPreferences share_pref = getApplicationContext().getSharedPreferences("option", MODE_PRIVATE);
                SharedPreferences.Editor edit = share_pref.edit();

                positon_spinner23 = onItemselected.position;
                edit.putInt("morse", positon_spinner23);
                edit.commit();


                share_pref_get = getApplicationContext().getSharedPreferences("option", MODE_PRIVATE);


                get_share_pref = share_pref_get.getInt("morse", 0);


                int count1 = count - 1;
                if (get_share_pref == 0) {
                    if (t.getText().toString().length() == 0) {
                        morse_code.setText("");

                    } else {
//char aaaa=et.getText().toString().toCharArray()[count1];

                        morse_code.setText("");
                        String o = t.getText().toString();
                        for (int ca = 0; ca < t.length(); ca++) {
                            eeeq = t.getText().charAt(ca);


                            for (Map.Entry<String, String> entry : hashmap.entrySet()) {

                                //     String str = String.valueOf(et.getText().toString().toCharArray()[count1]);
                                //  CharSequence char_sequence=(CharSequence) str;
                                if (entry.getKey().equalsIgnoreCase(eeeq + "")) {
                                    char_seq = entry.getValue().toString();
                                    //et.getText().toString();


                                    morse_code.append(char_seq);

                                    char_seq = "";

                                }

                            }

                        }

                    }
                } else if (get_share_pref == 1) {
                    c1.get_Continental();


                } else if (get_share_pref == 2) {
                    a0.get_American();
                }


                //  String o=et.getText().toString();

                // ccc++;
                //    ccc=1;String o=et.getText().toString();

                // count=0;
//        if (get_text == "") {
//            Toast toast=Toast.makeText(MainActivity.this,"Field cannot be empty",Toast.LENGTH_SHORT);
//            toast.show();
//        }

                // c1.get_Continental();
//a0.get_American();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        };
        t.addTextChangedListener(textWatcher);


        play_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences share_pref = getApplicationContext().getSharedPreferences("option", MODE_PRIVATE);
                SharedPreferences.Editor edit = share_pref.edit();

                positon_spinner23 = onItemselected.position;
                edit.putInt("morse", positon_spinner23);
                edit.commit();


                //   item_spinner = onItemselected.item.toString();
                // positon_spinner = onItemselected.position;
                share_pref_get = getApplicationContext().getSharedPreferences("option", MODE_PRIVATE);


                get_share_pref = share_pref_get.getInt("morse", 0);

//                inputManager = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//
//                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
//                        InputMethodManager.HIDE_NOT_ALWAYS);

                if (get_share_pref == 0) {
                    if (m.isPlaying()) {
                        m.stop();

                        play_stop.setImageResource(R.drawable.stop_pressed_button);

                        spin.setEnabled(true);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                play_stop.setImageResource(R.drawable.play_button);

                            }
                        }, 200);


                        //  play_stop.setImageResource(R.drawable.play_button);

                        //  b.setText("Play");
                        m.release();
                        m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                        check = 1;
                    }

                    if (l.isPlaying()) {
                        l.stop();

                        play_stop.setImageResource(R.drawable.stop_pressed_button);
                        spin.setEnabled(true);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                play_stop.setImageResource(R.drawable.play_button);

                            }
                        }, 200);

                        //     play_stop.setImageResource(R.drawable.play_button);

                        //b.setText("Play");
                        l.release();

                        l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                        check = 1;
                    }

                    if (check == 0) {


                        get_text_box = t.getText().toString();
                        morse_code_text = "";

                        text_length = get_text_box.length();
                        String ss[] = new String[33];
                        inc = 0;

                        text_char = get_text_box.toCharArray();
                        check = 1;
                        if (get_text_box.isEmpty()) {
                            //  stop.setEnabled(false);
                            morse_code_text = "";
                            morse_code.setText(morse_code_text);
                            Toast to = Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT);
                            to.show();

                        } else {

                            play_stop.setImageResource(R.drawable.play_pressed_button);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    play_stop.setImageResource(R.drawable.stop_button);

                                }
                            }, 200);

                            validate(text_char[0]);
                        }
                    }
                    check = 0;
                    //validate(text_char[1]);

                } else if (get_share_pref == 1) {

                    if (m.isPlaying()) {
                        m.stop();
                        play_stop.setImageResource(R.drawable.stop_pressed_button);
                        spin.setEnabled(true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                play_stop.setImageResource(R.drawable.play_button);

                            }
                        }, 200);


                        m.release();
                        m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                        check_continental = 1;
                    }

                    if (l.isPlaying()) {
                        l.stop();

                        play_stop.setImageResource(R.drawable.stop_pressed_button);
                        spin.setEnabled(true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                play_stop.setImageResource(R.drawable.play_button);

                            }
                        }, 200);

                        //     play_stop.setImageResource(R.drawable.play_button);

                        //b.setText("Play");
                        l.release();

                        l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                        check_continental = 1;
                    }

                    if (check_continental == 0) {


                        get_text_box = t.getText().toString();
                        morse_code_text = "";

                        text_length = get_text_box.length();
                        String ss[] = new String[33];
                        inc = 0;

                        text_char = get_text_box.toCharArray();
                        check_continental = 1;
                        if (get_text_box.isEmpty()) {
                            //  stop.setEnabled(false);
                            morse_code_text = "";
                            morse_code.setText(morse_code_text);
                            Toast to = Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT);
                            to.show();

                        } else {

                            play_stop.setImageResource(R.drawable.play_pressed_button);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    play_stop.setImageResource(R.drawable.stop_button);

                                }
                            }, 200);

                            validate_continental(text_char[0]);
                        }
                    }
                    check_continental = 0;

//c1.continental_morse_play();


                } else if (get_share_pref == 2) {

                    if (m.isPlaying()) {
                        m.stop();
                        play_stop.setImageResource(R.drawable.stop_pressed_button);
                        spin.setEnabled(true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                play_stop.setImageResource(R.drawable.play_button);

                            }
                        }, 200);


                        m.release();
                        m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                        check_american = 1;
                    }

                    if (l.isPlaying()) {
                        l.stop();

                        play_stop.setImageResource(R.drawable.stop_pressed_button);
                        spin.setEnabled(true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                play_stop.setImageResource(R.drawable.play_button);

                            }
                        }, 200);

                        //     play_stop.setImageResource(R.drawable.play_button);

                        //b.setText("Play");
                        l.release();

                        l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                        check_american = 1;
                    }

                    if (check_american == 0) {


                        get_text_box = t.getText().toString();
                        morse_code_text = "";

                        text_length = get_text_box.length();
                        String ss[] = new String[33];
                        inc = 0;

                        text_char = get_text_box.toCharArray();
                        check_american = 1;
                        if (get_text_box.isEmpty()) {
                            //  stop.setEnabled(false);
                            morse_code_text = "";
                            morse_code.setText(morse_code_text);
                            Toast to = Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT);
                            to.show();

                        } else {

                            play_stop.setImageResource(R.drawable.play_pressed_button);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    play_stop.setImageResource(R.drawable.stop_button);

                                }
                            }, 200);

                            validate_american(text_char[0]);
                        }
                    }
                    check_american = 0;

                    // a0.american_morse_play();
                }

            }
        });

    }


//    public void hideKeyboard(View view) {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }

    public void validate_continental(char text_continental) {


        if (text_continental == 'A' || text_continental == 'a') {


            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);


            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "- ";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(720);
                            inc++;
                            if (inc < text_length) {
                                validate_continental(text_char[inc]);
                            } else if (inc == text_length) {
                                //  b.setText("Play");
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                            }
                        }
                    });

                }
            });

        } else if (text_continental == 'I' || text_continental == 'i') {


            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);

            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(720);
                            inc++;


                            if (inc < text_length) {
                                validate_continental(text_char[inc]);
                            } else if (inc == text_length) {
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                                // b.setText("Play");
                            }
                        }
                    });

                }
            });

        } else if (text_continental == 'S' || text_continental == 's') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    //  t.setTextColor(Color.parseColor("#000000"));

                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        //   b.setText("Play");
                                    }
                                }
                            });

                        }
                    });

                }
            });

        }




        else if (text_continental == 'R' || text_continental == 'r') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //    t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
                                        //    stop.setEnabled(false);
                                        //  translate.setEnabled(true);
                                        //b.setEnabled(true);
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        // b.setText("Play");
                                    }

                                }
                            });

                        }
                    });

                }
            });

        }

        else if (text_continental == 'P' || text_continental == 'p') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {

                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_continental == 'E' || text_continental == 'e') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ". ";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    //   t.setTextColor(Color.parseColor("#000000"));
                    SystemClock.sleep(720);

                    inc++;

                    if (inc < text_length) {
                        validate_continental(text_char[inc]);
                    } else if (inc == text_length) {
                        //stop.setEnabled(false);
                        //  translate.setEnabled(true);
                        //b.setEnabled(true);
                        //  b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }


                }
            });

        } else if (text_continental == 'T' || text_continental == 't') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "- ";
            morse_code.setText(morse_code_text);
            // t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    //   t.setTextColor(Color.parseColor("#000000"));
                    SystemClock.sleep(720);

                    inc++;

                    if (inc < text_length) {
                        validate_continental(text_char[inc]);
                    } else if (inc == text_length) {
                        //stop.setEnabled(false);
                        //translate.setEnabled(true);
                        // b.setEnabled(true);
                        //    b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }
                }
            });

        } else if (text_continental == 'M' || text_continental == 'm') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "- ";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            //  t.setTextColor(Color.parseColor("#000000"));
                            SystemClock.sleep(720);
                            inc++;

                            if (inc < text_length) {
                                validate_continental(text_char[inc]);
                            } else if (inc == text_length) {
                                //stop.setEnabled(false);
                                //translate.setEnabled(true);
                                //b.setEnabled(true);
                                //    b.setText("Play");
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                            }
                        }
                    });


                }
            });

        } else if (text_continental == 'N' || text_continental == 'n') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            //   t.setTextColor(Color.parseColor("#000000"));
                            SystemClock.sleep(720);

                            inc++;


                            if (inc < text_length) {
                                validate_continental(text_char[inc]);
                            } else if (inc == text_length) {
                                //  stop.setEnabled(false);
                                // translate.setEnabled(true);
                                // b.setEnabled(true);
                                //  b.setText("Play");
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                            }
                        }
                    });


                }
            });

        } else if (text_continental == 'O' || text_continental == 'o') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);

                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                        }
                                    });

                                }
                            });


                        }
                    });


                }
            });

        } else if (text_continental == 'J' || text_continental == 'j') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            SystemClock.sleep(240);

                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //   t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                //   b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });
        }



        else if (text_continental == 'Q' || text_continental == 'q') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //   t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
                                                // stop.setEnabled(false);
                                                // translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //  b.setText("Play");
                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        }




        else if (text_continental == 'K' || text_continental == 'k') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    // t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        //   b.setText("Play");
                                    }
                                }
                            });


                        }
                    });


                }
            });

        } else if (text_continental == 'U' || text_continental == 'u') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));

                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        // b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }
                                }
                            });

                        }
                    });

                }
            });

        } else if (text_continental == 'Z' || text_continental == 'z') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(720);
                                                    inc++;


                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });

                                        }
                                    });


                                }
                            });

                        }
                    });


                }
            });

        } else if (text_continental == 'G' || text_continental == 'g') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //       t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    //         t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;


                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        //   b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }


                                }
                            });

                        }
                    });


                }
            });

        }



        else if (text_continental == 'F' || text_continental == 'f') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //    t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
//            m.reset();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //         t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //   b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        }

        else if (text_continental == 'W' || text_continental == 'w') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    //        t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);


                                        //  b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_continental == 'D' || text_continental == 'd') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //    t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);


                                        //   b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }
                                }
                            });


                        }
                    });


                }
            });

        } else if (text_continental == 'X' || text_continental == 'x') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(240);

                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);

                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                            SystemClock.sleep(720);

                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate_continental(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                                //  b.setText("Play");

                                                            }
                                                        }
                                                    });

                                                }
                                            });


                                        }
                                    });

                                }
                            });


                        }
                    });


                }
            });

        } else if (text_continental == 'B' || text_continental == 'b') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;


                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //     b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        }


        else if (text_continental == 'C' || text_continental == 'c') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //      t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
//
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //    b.setText("Play");

                                            }

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        }


        else if (text_continental == 'Y' || text_continental == 'y') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //    t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);


                                                        //  b.setText("Play");

                                                    }
                                                }
                                            });

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        }



        else if (text_continental == 'L' || text_continental == 'l') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //        t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;
                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //     b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });

                }
            });

        }

        else if (text_continental == 'H' || text_continental == 'h') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);


                                                //  b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_continental == 'V' || text_continental == 'v') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //   b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });
                        }
                    });

                }
            });

        } else if (text_continental == '1') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            SystemClock.sleep(240);

                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //   t.setTextColor(Color.parseColor("#000000"));

                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
                                                //   stop.setEnabled(false);
                                                // translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                // b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });


        } else if (text_continental == '2') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {

                                                        //    b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }


                                                }
                                            });

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });
        } else if (text_continental == '3') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //  stop.setEnabled(false);
                                                        //  translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        // b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                            //    t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_continental == '4') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.start();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //   stop.setEnabled(false);
                                                        // translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }

                                                }
                                            });


                                            //       t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_continental == '5') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
                                        //   stop.setEnabled(false);
                                        // translate.setEnabled(true);
                                        // b.setEnabled(true);
                                        //   b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_continental == '6') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);

                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);


                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(720);

                                                            inc++;


                                                            if (inc < text_length) {
                                                                validate_continental(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                        stop.setEnabled(false);
//                                                        translate.setEnabled(true);
//                                                        b.setEnabled(true);
                                                                // b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }

                                                        }
                                                    });
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_continental == '7') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            SystemClock.sleep(720);
                                            inc++;


                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
                                                //stop.setEnabled(false);
                                                //translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                //   b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }


                                        }
                                    });


                                }
                            });

                        }
                    });


                }
            });

        } else if (text_continental == '8') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);

                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_continental(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        // stop.setEnabled(false);
                                                        //translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        //    b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_continental == '0') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);

                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));

                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate_continental(text_char[inc]);
                                    } else if (inc == text_length) {
                                        // stop.setEnabled(false);
                                        //translate.setEnabled(true);
                                        //b.setEnabled(true);
                                        //  b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_continental == '9') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);

                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_continental(text_char[inc]);
                                            } else if (inc == text_length) {
                                                // stop.setEnabled(false);
                                                //translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                // b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        }


        else if(text_continental==' ') {
            if (inc == text_length) {
                play_stop.setImageResource(R.drawable.play_button);
                spin.setEnabled(true);

            }
           else if (inc > 0)
            {
             if (get_text_box.charAt(inc) == ' ' && get_text_box.charAt(inc - 1) == ' ') {

                 inc++;
                 if (inc < text_length) {
                     validate_continental(text_char[inc]);
                 } else if (inc == text_length) {
                     // stop.setEnabled(false);
                     //translate.setEnabled(true);
                     //b.setEnabled(true);
                     // b.setText("Play");
                     play_stop.setImageResource(R.drawable.play_button);
                     spin.setEnabled(true);

                 }


             }

             else if(get_text_box.charAt(inc)==' ')
             {
                 morse_code_text += "/ ";

                 SystemClock.sleep(1680);


                 inc++;

                 if (inc < text_length) {
                     validate_continental(text_char[inc]);
                 } else if (inc == text_length) {
                     // stop.setEnabled(false);
                     //translate.setEnabled(true);
                     //b.setEnabled(true);
                     // b.setText("Play");
                     play_stop.setImageResource(R.drawable.play_button);
                     spin.setEnabled(true);

                 }
             }
        }

else
            {
                inc++;
                if (inc < text_length) {
                    validate_continental(text_char[inc]);
                } else if (inc == text_length) {
                    // stop.setEnabled(false);
                    //translate.setEnabled(true);
                    //b.setEnabled(true);
                    // b.setText("Play");
                    play_stop.setImageResource(R.drawable.play_button);
                    spin.setEnabled(true);

                }
            }
        }


        else {
            inc++;

            if (inc < text_length) {
                validate_continental(text_char[inc]);
            } else if (inc == text_length) {
                // stop.setEnabled(false);
                //translate.setEnabled(true);
                //b.setEnabled(true);
                // b.setText("Play");
                play_stop.setImageResource(R.drawable.play_button);
                spin.setEnabled(true);

            }
        }

    }


    public void validate_american(char text_american) {
        if (text_american == 'A' || text_american == 'a') {


            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);


            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "- ";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(720);
                            inc++;
                            if (inc < text_length) {
                                validate_american(text_char[inc]);
                            } else if (inc == text_length) {
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                                //   b.setText("Play");
                            }
                        }
                    });

                }
            });

        } else if (text_american == 'I' || text_american == 'i') {


            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);

            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(720);
                            inc++;


                            if (inc < text_length) {
                                validate_american(text_char[inc]);
                            } else if (inc == text_length) {
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                                // b.setText("Play");
                            }
                        }
                    });

                }
            });

        } else if (text_american == 'S' || text_american == 's') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    //  t.setTextColor(Color.parseColor("#000000"));

                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        //  b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }
                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == 'R' || text_american == 'r') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ". ";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //    t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
                                        //    stop.setEnabled(false);
                                        //  translate.setEnabled(true);
                                        //b.setEnabled(true);
                                        //   b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }

                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == 'P' || text_american == 'p') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {

                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    //   t.setTextColor(Color.parseColor("#000000"));
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_american(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == 'E' || text_american == 'e') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ". ";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    //   t.setTextColor(Color.parseColor("#000000"));
                    SystemClock.sleep(720);

                    inc++;

                    if (inc < text_length) {
                        validate_american(text_char[inc]);
                    } else if (inc == text_length) {
                        //stop.setEnabled(false);
                        //  translate.setEnabled(true);
                        //b.setEnabled(true);
                        //  b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }


                }
            });

        } else if (text_american == 'T' || text_american == 't') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "- ";
            morse_code.setText(morse_code_text);
            // t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    //   t.setTextColor(Color.parseColor("#000000"));
                    SystemClock.sleep(720);

                    inc++;

                    if (inc < text_length) {
                        validate_american(text_char[inc]);
                    } else if (inc == text_length) {
                        //stop.setEnabled(false);
                        //translate.setEnabled(true);
                        // b.setEnabled(true);
                        // b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }
                }
            });

        } else if (text_american == 'M' || text_american == 'm') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "- ";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            //  t.setTextColor(Color.parseColor("#000000"));
                            SystemClock.sleep(720);
                            inc++;

                            if (inc < text_length) {
                                validate_american(text_char[inc]);
                            } else if (inc == text_length) {
                                //stop.setEnabled(false);
                                //translate.setEnabled(true);
                                //b.setEnabled(true);
                                //  b.setText("Play");
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                            }
                        }
                    });


                }
            });

        } else if (text_american == 'N' || text_american == 'n') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            //   t.setTextColor(Color.parseColor("#000000"));
                            SystemClock.sleep(720);

                            inc++;


                            if (inc < text_length) {
                                validate_american(text_char[inc]);
                            } else if (inc == text_length) {
                                //  stop.setEnabled(false);
                                // translate.setEnabled(true);
                                // b.setEnabled(true);
                                //  b.setText("Play");
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                            }
                        }
                    });


                }
            });

        } else if (text_american == 'O' || text_american == 'o') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ". ";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            SystemClock.sleep(720);

                            inc++;

                            if (inc < text_length) {
                                validate_american(text_char[inc]);
                            } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                //  b.setText("Play");
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                            }


                        }
                    });


                }
            });

        } else if (text_american == 'J' || text_american == 'j') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            SystemClock.sleep(240);

                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //   t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });
        } else if (text_american == 'Q' || text_american == 'q') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //   t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
                                                // stop.setEnabled(false);
                                                // translate.setEnabled(true);
                                                //b.setEnabled(true);

                                                //b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == 'K' || text_american == 'k') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    // t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);

                                        //  b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }
                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == 'U' || text_american == 'u') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));

                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        //   b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }
                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == 'Z' || text_american == 'z') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //        t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;


                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);


                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });


                }
            });

        } else if (text_american == 'G' || text_american == 'g') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //       t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    //         t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;


                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        // b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);


                                    }


                                }
                            });

                        }
                    });


                }
            });

        } else if (text_american == 'F' || text_american == 'f') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //    t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
//            m.reset();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                        play_stop.setImageResource(R.drawable.play_button);

                                        spin.setEnabled(true);

                                        //   b.setText("Play");

                                    }

                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == 'W' || text_american == 'w') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    //        t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);

                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        // b.setText("Play");

                                    }


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == 'D' || text_american == 'd') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //    t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);

                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        //   b.setText("Play");

                                    }
                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == 'X' || text_american == 'x') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });

                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == 'B' || text_american == 'b') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;


                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                //b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);

                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == 'C' || text_american == 'c') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
//

                                        // b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);


                                    }


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == 'Y' || text_american == 'y') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);


                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);

                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == 'L' || text_american == 'l') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //        t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "- ";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                            SystemClock.sleep(720);
                            inc++;
                            if (inc < text_length) {
                                validate_american(text_char[inc]);
                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                play_stop.setImageResource(R.drawable.play_button);

                                spin.setEnabled(true);

                                //  b.setText("Play");

                            }


                        }
                    });

                }
            });

        } else if (text_american == 'H' || text_american == 'h') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);


                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == 'V' || text_american == 'v') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);


                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });
                        }
                    });

                }
            });

        } else if (text_american == '1') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            SystemClock.sleep(240);

                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //   t.setTextColor(Color.parseColor("#000000"));

                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
                                                //   stop.setEnabled(false);
                                                // translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                // b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });


        } else if (text_american == '2') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_american(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                        //    b.setText("Play");

                                                    }


                                                }
                                            });

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });
        } else if (text_american == '3') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_american(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //  stop.setEnabled(false);
                                                        //  translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        // b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                            //    t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == '4') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.start();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_american(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //   stop.setEnabled(false);
                                                        // translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }

                                                }
                                            });


                                            //       t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == '5') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate_american(text_char[inc]);
                                    } else if (inc == text_length) {
                                        //   stop.setEnabled(false);
                                        // translate.setEnabled(true);
                                        // b.setEnabled(true);
                                        //   b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }


                                }
                            });

                        }
                    });

                }
            });

        } else if (text_american == '6') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);

                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);


                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(720);

                                                            inc++;


                                                            if (inc < text_length) {
                                                                validate_american(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                        stop.setEnabled(false);
//                                                        translate.setEnabled(true);
//                                                        b.setEnabled(true);
                                                                // b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }

                                                        }
                                                    });
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == '7') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            SystemClock.sleep(720);
                                            inc++;


                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
                                                //stop.setEnabled(false);
                                                //translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                //   b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }


                                        }
                                    });


                                }
                            });

                        }
                    });


                }
            });

        } else if (text_american == '8') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);

                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate_american(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        // stop.setEnabled(false);
                                                        //translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        //    b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == '0') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);

                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
                                                // stop.setEnabled(false);
                                                //translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text_american == '9') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);

                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate_american(text_char[inc]);
                                            } else if (inc == text_length) {
                                                // stop.setEnabled(false);
                                                //translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                // b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        }





        else if(text_american==' ') {
            if (inc == text_length) {
                play_stop.setImageResource(R.drawable.play_button);
                spin.setEnabled(true);

            }
            else if (inc > 0)
            {
                if (get_text_box.charAt(inc) == ' ' && get_text_box.charAt(inc - 1) == ' ') {

                    inc++;
                    if (inc < text_length) {
                        validate_american(text_char[inc]);
                    } else if (inc == text_length) {
                        // stop.setEnabled(false);
                        //translate.setEnabled(true);
                        //b.setEnabled(true);
                        // b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }


                }

                else if(get_text_box.charAt(inc)==' ')
                {
                    morse_code_text += "/ ";

                    SystemClock.sleep(1680);


                    inc++;

                    if (inc < text_length) {
                        validate_american(text_char[inc]);
                    } else if (inc == text_length) {
                        // stop.setEnabled(false);
                        //translate.setEnabled(true);
                        //b.setEnabled(true);
                        // b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }
                }
            }

            else
            {
                inc++;
                if (inc < text_length) {
                    validate_american(text_char[inc]);
                } else if (inc == text_length) {
                    // stop.setEnabled(false);
                    //translate.setEnabled(true);
                    //b.setEnabled(true);
                    // b.setText("Play");
                    play_stop.setImageResource(R.drawable.play_button);
                    spin.setEnabled(true);

                }
            }
        }



















        else {
            inc++;

            if (inc < text_length) {
                validate_american(text_char[inc]);
            } else if (inc == text_length) {
                // stop.setEnabled(false);
                //translate.setEnabled(true);
                //b.setEnabled(true);
                // b.setText("Play");
                play_stop.setImageResource(R.drawable.play_button);
                spin.setEnabled(true);

            }
        }


    }

    public void validate(final char text) {


        if (text == 'A' || text == 'a') {


            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);


            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "- ";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(720);
                            inc++;
                            if (inc < text_length) {
                                validate(text_char[inc]);
                            } else if (inc == text_length) {

                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                                // b.setText("Play");
                            }
                        }
                    });

                }
            });

        } else if (text == 'I' || text == 'i') {


            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);

            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(720);
                            inc++;


                            if (inc < text_length) {
                                validate(text_char[inc]);
                            } else if (inc == text_length) {
                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                                //   b.setText("Play");
                            }
                        }
                    });

                }
            });

        } else if (text == 'S' || text == 's') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    //  t.setTextColor(Color.parseColor("#000000"));

                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        //   b.setText("Play");
                                    }
                                }
                            });

                        }
                    });

                }
            });

        } else if (text == 'R' || text == 'r') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //    t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
                                        //    stop.setEnabled(false);
                                        //  translate.setEnabled(true);
                                        //b.setEnabled(true);
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        // b.setText("Play");
                                    }

                                }
                            });

                        }
                    });

                }
            });

        } else if (text == 'P' || text == 'p') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {

                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //   t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //   b.setText("Play");
                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == 'E' || text == 'e') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ". ";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    //   t.setTextColor(Color.parseColor("#000000"));
                    SystemClock.sleep(720);

                    inc++;

                    if (inc < text_length) {
                        validate(text_char[inc]);
                    } else if (inc == text_length) {
                        //stop.setEnabled(false);
                        //  translate.setEnabled(true);
                        //b.setEnabled(true);

                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                        //b.setText("Play");
                    }


                }
            });

        } else if (text == 'T' || text == 't') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "- ";
            morse_code.setText(morse_code_text);
            // t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    //   t.setTextColor(Color.parseColor("#000000"));
                    SystemClock.sleep(720);

                    inc++;

                    if (inc < text_length) {
                        validate(text_char[inc]);
                    } else if (inc == text_length) {
                        //stop.setEnabled(false);
                        //translate.setEnabled(true);
                        // b.setEnabled(true);

                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                        //  b.setText("Play");
                    }
                }
            });

        } else if (text == 'M' || text == 'm') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "- ";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            //  t.setTextColor(Color.parseColor("#000000"));
                            SystemClock.sleep(720);
                            inc++;

                            if (inc < text_length) {
                                validate(text_char[inc]);
                            } else if (inc == text_length) {
                                //stop.setEnabled(false);
                                //translate.setEnabled(true);
                                //b.setEnabled(true);

                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                                //  b.setText("Play");
                            }
                        }
                    });


                }
            });

        } else if (text == 'N' || text == 'n') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ". ";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            //   t.setTextColor(Color.parseColor("#000000"));
                            SystemClock.sleep(720);

                            inc++;


                            if (inc < text_length) {
                                validate(text_char[inc]);
                            } else if (inc == text_length) {
                                //  stop.setEnabled(false);
                                // translate.setEnabled(true);
                                // b.setEnabled(true);

                                play_stop.setImageResource(R.drawable.play_button);
                                spin.setEnabled(true);

                                //   b.setText("Play");
                            }
                        }
                    });


                }
            });

        } else if (text == 'O' || text == 'o') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);

                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        // b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'J' || text == 'j') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            SystemClock.sleep(240);

                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //   t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                //     b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });
        } else if (text == 'Q' || text == 'q') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //   t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
                                                // stop.setEnabled(false);
                                                // translate.setEnabled(true);
                                                //b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //  b.setText("Play");
                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'K' || text == 'k') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    // t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);

                                        // b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }
                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'U' || text == 'u') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));

                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        //b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);


                                    }
                                }
                            });

                        }
                    });

                }
            });

        } else if (text == 'Z' || text == 'z') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //        t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;


                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                //  b.setText("Play");
                                                play_stop.setImageResource(R.drawable.play_button);

                                                spin.setEnabled(true);

                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });


                }
            });

        } else if (text == 'G' || text == 'g') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //       t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    //         t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;


                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);
                                        //  b.setText("Play");
                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                    }


                                }
                            });

                        }
                    });


                }
            });

        } else if (text == 'F' || text == 'f') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //    t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
//            m.reset();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //         t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //   b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == 'W' || text == 'w') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "- ";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    //        t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);
                                    inc++;

                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);

                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        //      b.setText("Play");

                                    }


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == 'D' || text == 'd') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ". ";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    //    t.setTextColor(Color.parseColor("#000000"));
                                    SystemClock.sleep(720);

                                    inc++;

                                    if (inc < text_length) {
                                        validate(text_char[inc]);
                                    } else if (inc == text_length) {
//                                        stop.setEnabled(false);
//                                        translate.setEnabled(true);
//                                        b.setEnabled(true);

                                        play_stop.setImageResource(R.drawable.play_button);
                                        spin.setEnabled(true);

                                        //    b.setText("Play");

                                    }
                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'X' || text == 'x') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //     b.setText("Play");

                                            }
                                        }
                                    });

                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'B' || text == 'b') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;


                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                // b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'C' || text == 'c') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //      t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
//
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //    b.setText("Play");

                                            }

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'Y' || text == 'y') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);

                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);

                                                spin.setEnabled(true);

                                                //    b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == 'L' || text == 'l') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //        t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;
                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //     b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });


                        }
                    });

                }
            });

        } else if (text == 'H' || text == 'h') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ". ";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);
                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);


                                                //    b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == 'V' || text == 'v') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "- ";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(720);
                                            inc++;

                                            if (inc < text_length) {
                                                validate(text_char[inc]);
                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);

                                                play_stop.setImageResource(R.drawable.play_button);
                                                spin.setEnabled(true);

                                                //  b.setText("Play");

                                            }
                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == '.') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    l.start();

                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();


                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);


                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);

                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                                    l.start();
                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);
                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                            SystemClock.sleep(720);
                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                                stop.setEnabled(false);
//                                                                translate.setEnabled(true);
//                                                                b.setEnabled(true);
//
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                                //       b.setText("Play");

                                                            }


                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });


                                    //    t.setTextColor(Color.parseColor("#000000"));


                                }
                            });

                        }
                    });

                }
            });
        } else if (text == ',') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();

                                            morse_code_text += "-";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                    SystemClock.sleep(240);


                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    l.start();

                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);
                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                                            SystemClock.sleep(720);
                                                            inc++;


                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                                stop.setEnabled(false);
//                                                                translate.setEnabled(true);
//                                                                b.setEnabled(true);
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                                //   b.setText("Play");

                                                            }

                                                        }
                                                    });


                                                }
                                            });


                                            //        t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });


                }
            });


        } else if (text == '?') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);


                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);
                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(720);
                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                                stop.setEnabled(false);
//                                                                translate.setEnabled(true);
//                                                                b.setEnabled(true);
                                                                //         b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);


                                                            }
                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });
        } else if (text == '=') {

            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);

                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    SystemClock.sleep(720);

                                                    inc++;


                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                        stop.setEnabled(false);
//                                                        translate.setEnabled(true);
//                                                        b.setEnabled(true);

                                                        //      b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });
        } else if (text == '-') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //       t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(240);

                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    l.start();
                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);

                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                            SystemClock.sleep(720);

                                                            inc++;


                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                                stop.setEnabled(false);
//                                                                translate.setEnabled(true);
//                                                                b.setEnabled(true);
                                                                //      b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });

                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });
        } else if (text == '@') {


            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {

                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);

                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            l.start();
                                            morse_code_text += "-";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                    SystemClock.sleep(240);

                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);

                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(720);
                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                                stop.setEnabled(false);
//                                                                translate.setEnabled(true);
//                                                                b.setEnabled(true);
                                                                // b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });
                                                }
                                            });


                                            //   t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == '\'') {

            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            SystemClock.sleep(240);

                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //   t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "-";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                    SystemClock.sleep(240);


                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);

                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(720);

                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                                stop.setEnabled(false);
//                                                                translate.setEnabled(true);
//                                                                b.setEnabled(true);
                                                                //   b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });


                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });
        } else if (text == '(' || text == ')') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //    t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);


                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);

                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    l.start();
                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);
                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                                            SystemClock.sleep(720);

                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                                stop.setEnabled(false);
//                                                                translate.setEnabled(true);
//                                                                b.setEnabled(true);


                                                                //   b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });


                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });


        } else if (text == ':') {

            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);

                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {

                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(240);


                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);
                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(720);

                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//            stop.setEnabled(false);
//            translate.setEnabled(true);
//            b.setEnabled(true);
                                                                //  b.setText("Play");

                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });


                                                }
                                            });
                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });


        } else if (text == '1') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            SystemClock.sleep(240);

                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {


                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //   t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);


                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {

                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //   stop.setEnabled(false);
                                                        // translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                        //  b.setText("Play");
                                                    }
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });


        } else if (text == '2') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {

                                                        //     b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }


                                                }
                                            });

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });
        } else if (text == '3') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);

                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //  stop.setEnabled(false);
                                                        //  translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        // b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                            //    t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == '4') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.start();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //   stop.setEnabled(false);
                                                        // translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }

                                                }
                                            });


                                            //       t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == '5') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.start();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //   stop.setEnabled(false);
                                                        // translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        // b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }

                                                }
                                            });


                                            //       t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == '6') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);

                                                    inc++;


                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                        stop.setEnabled(false);
//                                                        translate.setEnabled(true);
//                                                        b.setEnabled(true);
                                                        // b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == '7') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //        t.setTextColor(Color.parseColor("#000000"));

                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);
                                                    inc++;


                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //stop.setEnabled(false);
                                                        //translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        // b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                        }
                                    });


                                }
                            });

                        }
                    });


                }
            });

        } else if (text == '8') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);

                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);

                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        // stop.setEnabled(false);
                                                        //translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == '0') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);

                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);


                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "- ";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        // stop.setEnabled(false);
                                                        //translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == '9') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);

                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {


                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        // stop.setEnabled(false);
                                                        //translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });

                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == ')') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //    t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);


                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);

                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    l.start();
                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);
                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                                            SystemClock.sleep(720);

                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
                                                                // stop.setEnabled(false);
                                                                //   translate.setEnabled(true);
                                                                //     b.setEnabled(true);
                                                                //  b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });


                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });


        } else if (text == '(') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();
                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            //    t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);


                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                        stop.setEnabled(false);
//                                                        translate.setEnabled(true);
//                                                        b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }


                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });


        } else if (text == '/') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    SystemClock.sleep(720);

                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        // stop.setEnabled(false);
                                                        // translate.setEnabled(true);
                                                        //b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                            //       t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });

                                }
                            });


                        }
                    });


                }
            });

        } else if (text == '&') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //        t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(720);
                                                    inc++;
                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //stop.setEnabled(false);
                                                        //  translate.setEnabled(true);
                                                        //   b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                            //    t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });


                        }
                    });

                }
            });

        } else if (text == '!') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //      t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "-";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                    SystemClock.sleep(240);

                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    l.start();
                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);
                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                                            SystemClock.sleep(720);

                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
                                                                //  stop.setEnabled(false);
                                                                // translate.setEnabled(true);
                                                                //b.setEnabled(true);
                                                                // b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == ';') {
            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
            l.start();
            morse_code_text += "-";
            morse_code.setText(morse_code_text);
            //     t.setTextColor(Color.parseColor("#FF0000"));
            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    l.stop();
                    l.release();

                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    SystemClock.sleep(240);

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //      t.setTextColor(Color.parseColor("#000000"));


                                            SystemClock.sleep(240);

                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "-";
                                            morse_code.setText(morse_code_text);
                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                    SystemClock.sleep(240);

                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);
                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                            SystemClock.sleep(720);

                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
                                                                //  stop.setEnabled(false);
                                                                // translate.setEnabled(true);
                                                                //b.setEnabled(true);
                                                                //  b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });


                }
            });

        } else if (text == '+') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //   t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                    SystemClock.sleep(240);

                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    l.start();

                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();


                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);


                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(720);
                                                    inc++;

                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
                                                        //  stop.setEnabled(false);
                                                        //    translate.setEnabled(true);
                                                        // b.setEnabled(true);
                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);
                                                        spin.setEnabled(true);

                                                    }


                                                }
                                            });

                                        }
                                    });


                                    //    t.setTextColor(Color.parseColor("#000000"));


                                }
                            });

                        }
                    });

                }
            });
        } else if (text == '_') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //      t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                            l.start();
                            morse_code_text += "-";
                            morse_code.setText(morse_code_text);
                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    l.stop();
                                    l.release();
                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    //   t.setTextColor(Color.parseColor("#000000"));


                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);

                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);
                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);


                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                                    l.start();
                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);
                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                            SystemClock.sleep(720);
                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
                                                                //stop.setEnabled(false);
                                                                //translate.setEnabled(true);
                                                                //b.setEnabled(true);
                                                                // b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });
        } else if (text == '~') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //        t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            //    t.setTextColor(Color.parseColor("#000000"));
                                            SystemClock.sleep(240);

                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ". ";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                                                    SystemClock.sleep(720);
                                                    inc++;
                                                    if (inc < text_length) {
                                                        validate(text_char[inc]);
                                                    } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);


                                                        //  b.setText("Play");
                                                        play_stop.setImageResource(R.drawable.play_button);

                                                        spin.setEnabled(true);

                                                    }
                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });

                }
            });

        } else if (text == '#') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);


                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                    l.start();
                                                    morse_code_text += "- ";
                                                    morse_code.setText(morse_code_text);
                                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            l.stop();
                                                            l.release();
                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                            SystemClock.sleep(720);
                                                            inc++;

                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
                                                                //   stop.setEnabled(false);
                                                                //  translate.setEnabled(true);
                                                                // b.setEnabled(true);
                                                                //  b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);
                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });


                                                }
                                            });


                                            //    t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == '$') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //  t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();
                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    m.start();
                    morse_code_text += ".";
                    morse_code.setText(morse_code_text);
                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            m.stop();
                            m.release();
                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            SystemClock.sleep(240);


                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);


                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                    l.start();
                                    morse_code_text += "-";
                                    morse_code.setText(morse_code_text);
                                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            l.stop();
                                            l.release();
                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                            SystemClock.sleep(240);


                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                            m.start();
                                            morse_code_text += ".";
                                            morse_code.setText(morse_code_text);

                                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    m.stop();
                                                    m.release();
                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                    SystemClock.sleep(240);


                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ".";
                                                    morse_code.setText(morse_code_text);

                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(240);


                                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                                            l.start();
                                                            morse_code_text += "- ";
                                                            morse_code.setText(morse_code_text);
                                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                                @Override
                                                                public void onCompletion(MediaPlayer mp) {
                                                                    l.stop();
                                                                    l.release();
                                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);


                                                                    SystemClock.sleep(720);
                                                                    inc++;

                                                                    if (inc < text_length) {
                                                                        validate(text_char[inc]);
                                                                    } else if (inc == text_length) {
                                                                        //   stop.setEnabled(false);
                                                                        //  translate.setEnabled(true);
                                                                        // b.setEnabled(true);
                                                                        //   b.setText("Play");
                                                                        play_stop.setImageResource(R.drawable.play_button);
                                                                        spin.setEnabled(true);

                                                                    }
                                                                }
                                                            });


                                                        }
                                                    });


                                                }
                                            });


                                            //    t.setTextColor(Color.parseColor("#000000"));

                                        }
                                    });


                                }
                            });

                        }
                    });

                }
            });

        } else if (text == '"') {
            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
            m.start();
            morse_code_text += ".";
            morse_code.setText(morse_code_text);
            //        t.setTextColor(Color.parseColor("#FF0000"));
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    m.stop();
                    m.release();

                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);

                    SystemClock.sleep(240);


                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                    l.start();
                    morse_code_text += "-";
                    morse_code.setText(morse_code_text);
                    l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            l.stop();
                            l.release();
                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                            SystemClock.sleep(240);

                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                            m.start();
                            morse_code_text += ".";
                            morse_code.setText(morse_code_text);
                            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    m.stop();
                                    m.release();
                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    SystemClock.sleep(240);

                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                    m.start();
                                    morse_code_text += ".";
                                    morse_code.setText(morse_code_text);
                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                            m.stop();
                                            m.release();
                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                            SystemClock.sleep(240);

                                            l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);
                                            l.start();
                                            morse_code_text += "-";
                                            morse_code.setText(morse_code_text);


                                            l.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                @Override
                                                public void onCompletion(MediaPlayer mp) {
                                                    l.stop();
                                                    l.release();
                                                    l = MediaPlayer.create(MainActivity.this, R.raw.morse_long);

                                                    SystemClock.sleep(240);

                                                    m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);
                                                    m.start();
                                                    morse_code_text += ". ";
                                                    morse_code.setText(morse_code_text);
                                                    m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mp) {
                                                            m.stop();
                                                            m.release();
                                                            m = MediaPlayer.create(MainActivity.this, R.raw.morse_short);


                                                            SystemClock.sleep(720);
                                                            inc++;
                                                            if (inc < text_length) {
                                                                validate(text_char[inc]);
                                                            } else if (inc == text_length) {
//                                                stop.setEnabled(false);
//                                                translate.setEnabled(true);
//                                                b.setEnabled(true);


                                                                //  b.setText("Play");
                                                                play_stop.setImageResource(R.drawable.play_button);

                                                                spin.setEnabled(true);

                                                            }
                                                        }
                                                    });

                                                }
                                            });


                                        }
                                    });


                                }
                            });


                        }
                    });

                }
            });

        }







        else if(text==' ') {
            if (inc == text_length) {
                play_stop.setImageResource(R.drawable.play_button);
                spin.setEnabled(true);

            }
            else if (inc > 0)
            {
                if (get_text_box.charAt(inc) == ' ' && get_text_box.charAt(inc - 1) == ' ') {

                    inc++;
                    if (inc < text_length) {
                        validate(text_char[inc]);
                    } else if (inc == text_length) {
                        // stop.setEnabled(false);
                        //translate.setEnabled(true);
                        //b.setEnabled(true);
                        // b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }


                }

                else if(get_text_box.charAt(inc)==' ')
                {
                    morse_code_text += "/ ";

                    SystemClock.sleep(1680);


                    inc++;

                    if (inc < text_length) {
                        validate(text_char[inc]);
                    } else if (inc == text_length) {
                        // stop.setEnabled(false);
                        //translate.setEnabled(true);
                        //b.setEnabled(true);
                        // b.setText("Play");
                        play_stop.setImageResource(R.drawable.play_button);
                        spin.setEnabled(true);

                    }
                }
            }

            else
            {
                inc++;
                if (inc < text_length) {
                    validate(text_char[inc]);
                } else if (inc == text_length) {
                    // stop.setEnabled(false);
                    //translate.setEnabled(true);
                    //b.setEnabled(true);
                    // b.setText("Play");
                    play_stop.setImageResource(R.drawable.play_button);
                    spin.setEnabled(true);

                }
            }
        }









        else {
            inc++;

            if (inc < text_length) {
                validate(text_char[inc]);
            } else if (inc == text_length) {
                // stop.setEnabled(false);
                //translate.setEnabled(true);
                //b.setEnabled(true);
                // b.setText("Play");
                play_stop.setImageResource(R.drawable.play_button);
                spin.setEnabled(true);

            }
        }
    }


    @Override
    public void onBackPressed() {

        if (m.isPlaying()) {
            m.stop();

            m.release();
        }

        if (l.isPlaying()) {
            l.stop();

            l.release();
        }
        MainActivity.this.finish();

        super.onBackPressed();
    }

    @Override
    protected void onUserLeaveHint() {

        if (m.isPlaying()) {
            m.stop();
            m.release();
        }

        if (l.isPlaying()) {
            l.stop();

            l.release();
        }
        MainActivity.this.finish();
        super.onUserLeaveHint();
    }
}
