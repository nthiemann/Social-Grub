package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Predicate;

public class Search extends AppCompatActivity {
    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference recipeRef = db.getReference("Image Dish");
    DatabaseReference tagRef = db.getReference().child("1jtXaB0m7-Hd5RcigSl1XRFoJCt5DNDMfgagA8tMOWxo").child("Sheet1");


    Button searchButton;
    EditText recipeNameField;
    EditText userField;
    SearchView tagSearch;
    ListView listView;

    // Searchable lists
    ArrayList<String> recipeNameList = new ArrayList<>();
    ArrayList<String> userList = new ArrayList<>();

    // Tag Search
    ArrayList<String> tagList = new ArrayList<>();
    HashSet<String> tagsSelected = new HashSet<>();


    // A list of posts that meet criteria (by post ID)
    ArrayList<String> listOfPosts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        listView = findViewById(R.id.list_view);
        searchButton = findViewById(R.id.button);
        recipeNameField = findViewById(R.id.editTextRecipeName);
        userField = findViewById(R.id.editTextPersonName);
        tagSearch = findViewById(R.id.searchView2);

        populateTagsSearchList();

        /*stringArrayList.add("Vegan");
        stringArrayList.add("Vegitarian");
        stringArrayList.add("Gluten-free");
        stringArrayList.add("Kosher");
        stringArrayList.add("Entrees");
        stringArrayList.add("Appetizers");
        stringArrayList.add("Full-course Dish");
        stringArrayList.add("Soups");
        stringArrayList.add("Salads");
        stringArrayList.add("Desserts");*/

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                search();
                Intent returnToExplore = new Intent(Search.this,ExploreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("filteredPosts", Parcels.wrap(listOfPosts));
                returnToExplore.putExtras(bundle);

                startActivity(returnToExplore);
            }
        });


        tagSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        adapter = new ArrayAdapter<>(Search.this, android.R.layout.simple_list_item_1,
                tagList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position), Toast.LENGTH_SHORT).show();
                tagsSelected.add(adapter.getItem(position));

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.searchView2);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter array list
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/


    private void populateTagsSearchList()
    {
        tagRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Looping through all the recipes:
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String tagName = dataSnapshot1.child("TagString").getValue().toString();
                    tagList.add(tagName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Search.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Create the list of posts given criteria
    private void search()
    {

        String recipeNameToSearch = recipeNameField.getText().toString().trim();
        String userNameToSearch = userField.getText().toString().trim();

        // Loop through all recipes in the Image Dish. If they meet all criteria, add them to the list
        recipeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String recipeName = dataSnapshot1.child("recipeTitle").getValue().toString();
                    String userName = dataSnapshot1.child("Username").getValue().toString();
                    ArrayList<String> recipeTagsList = new ArrayList<>();
                    for (DataSnapshot thisId : dataSnapshot1.child("recipeTags").getChildren())
                    {
                        recipeTagsList.add((String) thisId.child("tagName").getValue());
                    }


                    boolean recipeNameMatch =  (!(recipeNameToSearch.length() > 0) || (recipeNameToSearch.equals(recipeName)));
                    boolean userNameMatch = (!(userNameToSearch.length() > 0) || (userNameToSearch.equals(userName)));
                    boolean tagsMatch = (!(tagsSelected.size() > 0) || recipeTagsList.containsAll(tagsSelected));
                    if (recipeNameMatch && userNameMatch && tagsMatch)
                        listOfPosts.add(dataSnapshot1.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Search.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}