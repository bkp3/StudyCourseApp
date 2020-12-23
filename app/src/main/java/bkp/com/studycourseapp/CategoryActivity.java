package bkp.com.studycourseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);

        String[] title = new String[]{
                "1. Introduction to Java",
                "2. Java Fundamental",
                "3. Control Statement",
                "4. Java Array",
                "5. Java String",
                "6. oops concept",
                "7. Constructors",
                "8. Static Members",
                "9. Instance Members",
                "10. Package",
                "11. Access Modifiers",
                "12. Inheritance",
                "13. Polymorphism",
                "14. Encapsulation",
                "15. Abstraction",
                "16. this & super keyword",
                "17. final keyword",
                "18. Exception Handling",
                "19. Multithreading",
                "20. JDBC",
                "21. Collection"};
        String[] htmlFile = new String[]{ "part1", "part2", "part3", "part4", "part5", "part6", "part7", "part8", "part9", "part10", "part11", "part12", "part13", "part14", "part15", "part16", "part17", "part18", "part19", "part20", "part21"};

        customAdapter = new CustomAdapter(this,title,htmlFile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CategoryActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(CategoryActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}