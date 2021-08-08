package com.example.coffeeorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.text.NumberFormat;

import org.w3c.dom.Text;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle t;
    DrawerLayout d;
    TextView tvSummary;
    Button btn;
    TabLayout tabo;
    TabItem a,b,c,dr,e;
    ViewPager v;
    fragment f;
    ViewFlipper view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("COFFEE ORDER APP");

        Toolbar tool = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(tool);

        nav = (NavigationView) findViewById(R.id.nav);
        d = (DrawerLayout) findViewById(R.id.drawer);

        tabo = (TabLayout) findViewById(R.id.tab1);
        a = (TabItem) findViewById(R.id.tab2);
        b = (TabItem) findViewById(R.id.tab3);
        c = (TabItem) findViewById(R.id.tab4);
        dr = (TabItem) findViewById(R.id.tab5);
        e = (TabItem) findViewById(R.id.tab6);
        v = (ViewPager) findViewById(R.id.pageholder);

        t = new ActionBarDrawerToggle(this, d, tool, R.string.low, R.string.high);
        d.addDrawerListener(t);
        t.syncState();

        f = new fragment(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabo.getTabCount());
        v.setAdapter(f);
        tabo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                v.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
        v.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabo));


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getItemId() == R.id.home) {
                    Toast.makeText(getApplicationContext(), "Home panel is open", Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.about) {
                    Toast.makeText(getApplicationContext(), "about is clicked", Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.menu) {
                    Toast.makeText(getApplicationContext(), "menu is open", Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.order) {
                    Toast.makeText(getApplicationContext(), "pay order is clicked", Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.call) {
                    Toast.makeText(getApplicationContext(), "call panel is open", Toast.LENGTH_LONG).show();
                }
                d.closeDrawer(GravityCompat.START);
                return false;


            }
        });

        /*int arr[]={};
        view=(ViewFlipper)findViewById(R.id.flipper);
        for (int i=0;i<arr.length;i++)
        {
            showimage(arr[i]);
        }*/

    }
    /*public void showimage(int img)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(img);

        view.addView(imageView);
        view.setFlipInterval(3000);
        view.setAutoStart(true);

        view.setInAnimation(this,android.R.anim.slide_in_left);
        view.setOutAnimation(this,android.R.anim.slide_out_right);
    }*/
    /*btn.setOnClickListener(new View.OnClickListener) {
        public void onClick (View v)
        {
            String s = tvSummary.getText().toString();
            sendEmail(s);
        }
    }
    private void sendEmail(String emailText)
    {
        String email[]={"pinkiakter195@gmail.com"};
        Intent i=new Intent(Intent.ACTION_SENDTO);
        i.putExtra(Intent.EXTRA_TEXT,emailText);
        i.putExtra(Intent.EXTRA_SUBJECT,"here is new Coffee Order");
        i.putExtra(Intent.EXTRA_EMAIL,email);
        i.setData(Uri.parse("mailto :"));
        startActivity(i);
    }
*/

    int numofcoffee=0;
    public void submitOrder(View view)
    {
        EditText n=(EditText) findViewById(R.id.text);
        String name=n.getText().toString();
        EditText add=(EditText) findViewById(R.id.add);
        String adderss=add.getText().toString();
        EditText item=(EditText) findViewById(R.id.item);
        String Item=item.getText().toString();

        CheckBox cream=(CheckBox)findViewById(R.id.whip);
        boolean c=cream.isChecked();

        CheckBox cr=(CheckBox)findViewById(R.id.cho);
        boolean l=cr.isChecked();


        int d=calc(c,l);




        displaypay(create(name,adderss,Item,d,c,l));
    }
    public void displaypay(String pay)
    {
        TextView price=(TextView) findViewById(R.id.price);
        price.setText(pay);
    }
    private int calc(boolean k,boolean o)
    {
        int base=180;
        if(k)
        {
            base+=2;
        }
        if(o)
        {
            base+=5;
        }
        return numofcoffee*base;
    }
    public void increment(View view)
    {
        if(numofcoffee>=2000)
        {
            Toast.makeText(this,"You Cannot order greater than 2000 cofee",Toast.LENGTH_SHORT).show();
            return;
        }
        numofcoffee++;
        display(numofcoffee);
    }
    public void decrement(View view)
    {
        if(numofcoffee==0){
            Toast.makeText(this,"You Cannot order less than 1 cofee",Toast.LENGTH_SHORT).show();
            return;
        }
        numofcoffee--;
        display(numofcoffee);
    }
    private String create(String name,String add,String item, int pr, boolean a, boolean b)
    {
        String p="Name :"+ name;
        p+="\nAddress : "+add;
        p+="\nItem Type :  "+item;
        p+="\nAdd WhipperCream ? "+a;
        p+="\nAdd ChocolateCream ? "+b;
        p+="\nPlease pay $"+ pr +"Taka";
        p+="\nThanks for ordering";
        return p;
    }
    private void display(int x)
    {
        TextView q=(TextView) findViewById(R.id.quantity);
        q.setText(""+x);
    }
    private void displayprice(int p)
    {
        TextView price=(TextView) findViewById(R.id.price);
        price.setText(NumberFormat.getCurrencyInstance().format(p));
    }
   public void browser(View view)
   {
       Intent browserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http:://pinkiakter195@gmail.com"));
       startActivity(browserIntent);
   }

    public void git(View view)
    {
        Intent browserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/pinkfloyed"));
        startActivity(browserIntent);
    }
    public void submit(View view)
    {

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://accounts.google.com/servicelogin/signinchooser?flowName=GlifWebSignIn&flowEntry=ServiceLogin"));
        startActivity(intent);

    }

}
