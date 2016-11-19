package com.example.alfonso.pregoneame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import com.example.alfonso.pregoneame.ToDoItem.Tema;
public class ToDoManagerActivityCliente extends AppCompatActivity   {

    // Add a ToDoItem Request Code
    private static final int ADD_TODO_ITEM_REQUEST = 0;
    public static String nuevalinea = System.getProperty("line.separator");
    private static final String FILE_NAME = "TodoManagerActivityData6.txt";
    private static final String TAG = "Lab-UserInterface";

    // IDs for menu items
    private static final int MENU_DELETE = Menu.FIRST;
    private static final int MENU_DUMP = Menu.FIRST + 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ToDoAdapter mAdapter;
    private Button buttonTodo;
    private Button buttonDeporte;
    private Button buttonCultura;
    private Button buttonFestejo;
    private Button buttonOtros;
    private Button buttonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_manager_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //TODO - Get a reference to the RecyclerView
        mRecyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //TODO - Set a Linear Layout Manager to the RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //TODO - Create a new Adapter for the RecyclerView
        // specify an adapter (see also next example)
        mAdapter = new ToDoAdapter(new ToDoAdapter.OnItemClickListener(){
            @Override public void onItemClick(ToDoItem item){
               // Snackbar.make(ToDoManagerActivityCliente.this.getCurrentFocus(), "Titulo: " + item.getTitle() + nuevalinea + "Tema: " + item.getTema()+ nuevalinea +"Descripcion: " + nuevalinea + item.getDescripcion() +  nuevalinea + "Fecha: " + item.getDate(), Snackbar.LENGTH_LONG).show();


                final String titulo = item.getTitle().toString();
                System.out.println(" VALOR DE TITULO "+ titulo);
                AlertDialog.Builder builder = new AlertDialog.Builder(ToDoManagerActivityCliente.this);
                builder.setMessage("Tema: " + item.getTema()+ nuevalinea +"Descripcion: " + nuevalinea + item.getDescripcion() +  nuevalinea + "Fecha: " + item.getDate())
                        .setTitle("Titulo: "+ item.getTitle())
                        .setCancelable(false)
                        .setPositiveButton("Cerrar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // metodo que se debe implementar
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();









            }

        });
        //TODO - Attach the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        buttonSalir = (Button) findViewById(R.id.buttonSalir);

        buttonSalir.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent act3= new Intent(ToDoManagerActivityCliente.this, MainActivity.class);
                startActivity(act3);
            }
        });




        buttonFestejo = (Button) findViewById(R.id.buttonFestejo);

        buttonFestejo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                loadItemsFestejo();

            }
        });

        buttonTodo = (Button) findViewById(R.id.buttonTodos);

        buttonTodo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                loadItems();
            }
        });
        buttonDeporte = (Button) findViewById(R.id.buttonDeporte);

        buttonDeporte.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                loadItemsDeporte();
            }
        });
        buttonCultura = (Button) findViewById(R.id.buttonCultura);

        buttonCultura.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                loadItemsCultura();
            }
        });
        buttonOtros = (Button) findViewById(R.id.buttonOtros);

        buttonOtros.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                loadItemsOtros();
            }
        });








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        log("Entered onActivityResult()");

        // TODO - Check result code and request code.
        if(requestCode==ADD_TODO_ITEM_REQUEST){
            if(resultCode==RESULT_OK){
                //TODO - Create a TodoItem from data and add it to the adapter
                ToDoItem item= new ToDoItem(data);
                mAdapter.add(item);
            }
        }



    }

    @Override
    public void onResume() {
        super.onResume();

        // Load saved ToDoItems, if necessary
        if (mAdapter.getItemCount() == 0)

            loadItems();
            saveItems();
    }

    @Override
    protected void onPause() {
        super.onPause();


        mAdapter.clear();
        loadItems();
        saveItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

//        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all");
 //       menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log");
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_DELETE:
                mAdapter.clear();
                return true;
            case MENU_DUMP:
                dump();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/
    private void dump() {

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            String data = ((ToDoItem) mAdapter.getItem(i)).toLog();
            log("Item " + i + ": " + data.replace(ToDoItem.ITEM_SEP, ","));
        }

    }

    // Load stored ToDoItems
    private void loadItems() {
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String descri= null;
            String tema = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                descri = reader.readLine();
                tema = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                mAdapter.add(new ToDoItem(title,descri,Tema.valueOf(tema), date));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /*-------------------------------*/

    private void loadItemsDeporte() {
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String descri= null;
            String tema = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                descri = reader.readLine();
                tema = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                if(tema.toString().equals("Deporte")) {
                    mAdapter.add(new ToDoItem(title, descri, Tema.valueOf(tema), date));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Load stored ToDoItems
    private void loadItemsCultura() {
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String descri= null;
            String tema = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                descri = reader.readLine();
                tema = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                if(tema.toString().equals("Cultura")) {
                    mAdapter.add(new ToDoItem(title, descri, Tema.valueOf(tema), date));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Load stored ToDoItems
    private void loadItemsFestejo() {
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String descri= null;
            String tema = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                descri = reader.readLine();
                tema = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                if(tema.toString().equals("Festejo")) {
                    mAdapter.add(new ToDoItem(title, descri, Tema.valueOf(tema), date));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Load stored ToDoItems
    private void loadItemsOtros() {
        BufferedReader reader = null;
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));

            String title = null;
            String descri= null;
            String tema = null;
            Date date = null;

            while (null != (title = reader.readLine())) {
                descri = reader.readLine();
                tema = reader.readLine();
                date = ToDoItem.FORMAT.parse(reader.readLine());
                if(tema.toString().equals("Otros")) {
                    mAdapter.add(new ToDoItem(title, descri, Tema.valueOf(tema), date));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Save ToDoItems to file
    private void saveItems() {
        PrintWriter writer = null;
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    fos)));

            for (int idx = 0; idx < mAdapter.getItemCount(); idx++) {

                writer.println(mAdapter.getItem(idx));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }



    private void log(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, msg);
    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.log){
            Intent acti = new Intent(ToDoManagerActivity.this, Login.class);
            startActivity(acti);
        }
        else if (id == R.id.nav_deporte) {
            // Handle the camera action
        } else if (id == R.id.nav_cultura) {

        } else if (id == R.id.nav_festejos) {

        } else if (id == R.id.nav_otros) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/

}
