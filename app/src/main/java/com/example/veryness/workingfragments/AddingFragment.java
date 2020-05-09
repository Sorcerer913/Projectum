package com.example.veryness.workingfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veryness.R;
import com.example.veryness.main.Actor;
import com.example.veryness.main.MainActivity;
import com.example.veryness.main.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddingFragment extends Fragment {
    Button button;
    private List<Actor> items = new ArrayList<>();
    public List<ViewHolder> mPoints = new ArrayList<>();
    private RecyclerView recyclerView;
    private AddingFragment fragment;
    private MainFragment mainFragment;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewObjectListAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert container != null;
        // button=container.findViewById(R.id.button);
        return inflater.inflate(R.layout.funfragment, container, false);

    }

    public void setItems(List<Actor> items) {
        this.items = items;
    }

    public static AddingFragment newInstance() {
        AddingFragment fragment = new AddingFragment();
        fragment.setFragment(fragment);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private void setFragment(AddingFragment fragment) {
        this.fragment = fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.actors_list);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewObjectListAdapter(items);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        int j = 0;
        for (int i = 0; i < mPoints.size(); i++) {
            if (i < items.size()) {
                if(items.get(i)!=null){
                items.get(i).setName(mPoints.get(i).name.getText().toString());}
            }
        }

    }

    public List<Actor> getItems() {
        return items;
    }

    public void setMainFragment(MainFragment fragment) {
        this.mainFragment = fragment;
    }

    public class RecyclerViewObjectListAdapter extends RecyclerView.Adapter<ViewHolder> {

        public int getItemViewType(int position) {
            return position % 4;
        }

        private List<Actor> mItems;

        public List<Actor> getmItems() {
            return mItems;
        }

        public RecyclerViewObjectListAdapter(List<Actor> mItems) {
            this.mItems = mItems;
        }

        View veiwer;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("gsononviewtype", viewType + " ");
            if (viewType == 0 || viewType == 1)
                return new ViewHolder(getLayoutInflater().inflate(R.layout.fun_item, parent, false));
            else
                return new ViewHolder(getLayoutInflater().inflate(R.layout.fun_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            if (items.get(position) != null) {
                holder.onbindmodel(items.get(position));
                Log.v("ITEMS_SIZE_BEFORECLICK", String.valueOf(items.size()));
                mPoints.add(position, holder);
                mPoints.set(position, holder);
                holder.delobj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        items.remove(position);
                        mainFragment.getMySurfaceView().getSprite().remove(position);
                        mPoints.remove(holder);
                        mainFragment.getItems().clear();
                        for(int i=0;i<items.size();i++){
                            mainFragment.addItem(items.get(i));
                        }
                        ((MainActivity) Objects.requireNonNull(getActivity())).setMainFragment(mainFragment);
                        mAdapter=new RecyclerViewObjectListAdapter(items);
                        Log.v("ITEMS_SIZE_AFTER", String.valueOf(items.size()));
                        recyclerView.setAdapter(mAdapter);

                        }
                });
            }
        }


        @Override
        public int getItemCount() {
            if (items.size() == 0) {
                return 0;
            } else {
                return items.size();
            }

        }


    }
}

