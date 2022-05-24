package com.samsung.childrensdevelopmentcentermobile;

import static com.samsung.childrensdevelopmentcentermobile.nodb.ChildrensDevelopmentCenterSimpleDB.ELECTIVE_LIST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.samsung.childrensdevelopmentcentermobile.adapter.ElectiveAdapter;
import com.samsung.childrensdevelopmentcentermobile.fragment.AddElectiveFragment;
import com.samsung.childrensdevelopmentcentermobile.model.Elective;
import com.samsung.childrensdevelopmentcentermobile.rest.ChildrensDevelopmentCenterApi;
import com.samsung.childrensdevelopmentcentermobile.rest.impl.ChildrensDevelopmentCenterApiVolleyImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MSG_NAME = "electiveFromListByPos";

    private AppCompatButton btnAdd;

    private FragmentTransaction transaction;

    private ElectiveAdapter electiveAdapter;

    private RecyclerView rvElectives;

    private ItemTouchHelper.SimpleCallback simpleItemTouchCallback;

    private final ChildrensDevelopmentCenterApi childrensDevelopmentCenterApi = new ChildrensDevelopmentCenterApiVolleyImpl(this);

    @Override
    protected void onResume() {
        super.onResume();

        childrensDevelopmentCenterApi.fillElectiveList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        childrensDevelopmentCenterApi.fillDirectionList();
        childrensDevelopmentCenterApi.fillTutorList();

        rvElectives = findViewById(R.id.rv_electives);
        electiveAdapter = new ElectiveAdapter(this, ELECTIVE_LIST);
        rvElectives.setAdapter(electiveAdapter);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(view -> {

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_main, new AddElectiveFragment());
            transaction.commit();
        });

        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
        ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                Elective elective = ELECTIVE_LIST.get(position);

                if (swipeDir == ItemTouchHelper.LEFT) {
                    Toast.makeText(MainActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                    childrensDevelopmentCenterApi.deleteElective(elective.getId());

                }

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvElectives);
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        int size = fragments.size();
        if (size > 0)
            getSupportFragmentManager().beginTransaction().remove(fragments.get(size-1)).commit();
        else
            finish();
    }

    public void update() {

        electiveAdapter.notifyDataSetChanged();
    }

}