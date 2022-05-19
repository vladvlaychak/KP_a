package com.example.pslogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

public class text_content_activity extends AppCompatActivity {
    private ActionBar actionBar;
    private TextView text_content;
    private TextView header;
    private Typeface face1;
    private ImageView iContent;
    private SharedPreferences def_pref;
    private AppBarConfiguration mAppBarConfiguration;
    private ArrayAdapter<String> mAdapter;
    private ListView List;
    private  String[] array;
    private Button result_buton;
    private ArrayAdapter<String> adapter;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private int rezult_of_tests;
    private int category = 0;
    private int position = 0;
    public SharedPreferences sPref;
    public EditText etText;
    public Button btnSave;
    public int cli;
    public String s;
    private View footer;
    private String[] rezult_array= new String[10];
    public String res;
    private static String[] rezult_s_d_b = {"отсутствие депрессивных симптомов","легкая депрессия, астено-субдепрессивная симптоматика, м.б. у соматических","умеренная депрессия, критический уровень.","явно выраженная депрессивная симптоматика, не исключена эндогенност"};
    public int d;
    final String SAVED_TEXT = "saved_text";
    private int[] test_S_d_b_result = {0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3,0,0,1,2,3};
    private int[] array_helper = {R.string.Central_nervous_system, R.string.Frustration, R.string.Phobia,R.string.Pheromones, R.string.Conditioned_reflex, R.string.Tolerance,R.string.Temperament, R.string.Structuralism, R.string.Stereotype, R.string.Consciousness, R.string.Free_associations, R.string.Multiple_personality_disorder, R.string.Psychology,R.string.Projection, R.string.Placebo,R.string.Panic_attack,R.string.Memory,R.string.Neuron,R.string.Heredity,R.string.Mnemonics,R.string.Motivation,R.string.Narcolepsy,R.string.Personality,R.string.Libido,R.string.Intelligence_quotient,R.string.Cognitive_dissonance,R.string.Catharsis,R.string.Illusion,R.string.Perfect,R.string.Dissociation,R.string.Hallucination,R.string.Displacement,R.string.Unconditional_reflex,R.string.Affect,R.string.Aphasia,R.string.Amnesia,R.string.Aggression,R.string.Agoraphobia,R.string.Agnosia,R.string.Abstraction};
    private int[] array_tests = {R.array.test_t_c, R.array.test_S_d_b, R.array.test_s_z, R.array.test_s_b, R.array.test_c_t_l, R.array.test_p_t_s_c, R.array.test_l_n_A, R.array.test_r_t_l, R.array.test_m_e_L_n, R.array.test_t_e_b_h};
    private View ad;
    public int a = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);
        List = findViewById(R.id.info_content_test);
        array = getResources().getStringArray(R.array.test_S_d_b);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,new ArrayList<String>(Arrays.asList(array)) );
        List.setAdapter(adapter);
        text_content = findViewById(R.id.text_main_content);
        result_buton = (Button) findViewById(R.id.button_result);
        footer = createFooter("Подвал");
        List.addFooterView(footer);
        List.setAdapter(adapter);
        header = findViewById(R.id.text_header);

        reciveIntent();
    }
    public class S_D_B_REZULT {
        public int c;
        public int s_d_b_ol() {
            text_content.setVisibility(View.VISIBLE);
            header.setText("Результат шкалы депрессии Бека");

            if(rezult_of_tests <= 9){
                this.c = 0;
                s = rezult_s_d_b[c];
                text_content_activity.this.a = 0;}
            else if (rezult_of_tests >= 10 &&rezult_of_tests<=18){
                this.c = 1;
                s = rezult_s_d_b[c];
                text_content_activity.this.a = 1;
            }
            else if (rezult_of_tests >= 19 &&rezult_of_tests<=29){
                this.c = 2;
                s = rezult_s_d_b[c];
                text_content_activity.this.a = 2;
            }
            else if (rezult_of_tests >= 30) {
                this.c = 3;
                s = rezult_s_d_b[c];
                text_content_activity.this.a = 3;
            }
            System.out.println(rezult_of_tests);
            System.out.println(s);
            System.out.println(a);

            a = text_content_activity.this.a;
            System.out.println(a);
            rezult_of_tests = 0;
            text_content.setText(s);
            return c;
        }
        public String getName(){
            return s;
        }
        public int getC(){
            return c;
        }
    }

    public void button_result_show(View v) {
        new S_D_B_REZULT().s_d_b_ol();
    }

    // создание подвала
    View createFooter(String text) {
        View view = getLayoutInflater().inflate(R.layout.list_footer, null);
        return view;
    }
    private void reciveIntent() {
        Intent i = getIntent();
        if (i != null) {
            category = i.getIntExtra("category", 0);
            position = i.getIntExtra("position", 0);
            S_D_B_REZULT S_D_B_REZULT = new S_D_B_REZULT();
            cli =  S_D_B_REZULT.getC();
        }
        switch (category) {
            case 0:
//                iContent.setImageResource(array_image_fish[position]);
                text_content.setText(array_helper[position]);
                ad = findViewById(R.id.info_content_test);
                ad.setVisibility(View.GONE);

                break;
            case 1:
//                text_content.setText(array_tests[position]);
//                iContent.setImageResource(array_image_fish[position]);
                if (position == 0) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println(rezult_of_tests);
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];

                        }
                    });
                }
                else if (position == 1) {
                    text_content.setText(R.string.S_d_b);
                    header.setText(R.string.S_d_b_result);
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            List.setChoiceMode(List.CHOICE_MODE_MULTIPLE);
                            text_content.setVisibility(View.GONE);
//                            result_buton.setVisibility(View.VISIBLE);
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position];
                            System.out.println(rezult_of_tests);
                        }
                    });

//
                }
                else if (position == 2) {
                    text_content.setText(R.string.S_d_b);
                    header.setText(R.string.s_z);
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            text_content.setVisibility(View.GONE);
                            List.setChoiceMode(List.CHOICE_MODE_MULTIPLE);
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 3) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 4) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 5) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 6) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 7) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 8) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            rezult_of_tests = rezult_of_tests + test_S_d_b_result[position + 1];
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                ad = findViewById(R.id.info_content_test);
                ad.setVisibility(View.VISIBLE);
                array = getResources().getStringArray(array_tests[position]);
                adapter.clear();
                adapter.addAll(array);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                if (position == 0) {

                }
                else if (position == 1) {
                    System.out.println(rezult_array);

                    System.out.println(rezult_s_d_b[position]);
                    System.out.println(cli);
                    System.out.println(a);
                    int b = rezult_of_tests;
                    System.out.println(b);
                text_content.setText(rezult_s_d_b[position]);
//
                }
                else if (position == 2) {

                }
                else if (position == 3) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 4) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 5) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 6) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 7) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            System.out.println(rezult_of_tests);
                        }
                    });
                }
                else if (position == 8) {
                    List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            System.out.println(rezult_of_tests);
                        }
                    });
                }

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;

        }
    }
    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}

//       result_buton = (Button) findViewById(R.id.button_result);
//               result_buton.setOnClickListener(new View.OnClickListener() {
//@Override
//public Integer onClick(View view) {
//        text_content.setVisibility(View.VISIBLE);
//        header.setText("Результат шкалы депрессии Бека");
//
//        if(rezult_of_tests <= 9){
//        rezult_array[2] = ("отсутствие депрессивных симптомов");
//        s = 4;
//        c1 = rezult_s_d_b[2];
//        System.out.println(c1);
//        }
//        else if (rezult_of_tests >= 10 &&rezult_of_tests<=18){
//        rezult_array[2] = "легкая депрессия, астено-субдепрессивная симптоматика, м.б. у соматических";
//        System.out.println(rezult_array[2]);
//        }
//        else if (rezult_of_tests >= 19 &&rezult_of_tests<=29){
//        rezult_array[2] ="умеренная депрессия, критический уровень.";
//        System.out.println(rezult_array[2]);
//        }
//        else if (rezult_of_tests >= 30) {
//        rezult_array[2] = "явно выраженная депрессивная симптоматика, не исключен эндогенност";
//        System.out.println(rezult_array[2]);
//        }
//        rezult_of_tests = 0;
//        text_content.setText(rezult_array[2]);
//
//        s = 4;
//        System.out.println(s);
//        }
//        });