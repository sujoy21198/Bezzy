package com.example.bezzy.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.bezzy.R;

public class ProfileFragment extends Fragment {
    Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        button=view.findViewById(R.id.edit_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),EditprofileActivity.class);
                startActivity(intent);
            }
        });
        /*RecyclerView postRecyclerView= findViewById(R.id.postRecyclerView);
        postRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        List<PostItem> postItems=new ArrayList<>();
        postItems.add(new PostItem(R.drawable.bnm));
        postItems.add(new PostItem(R.drawable.dfg));
        postItems.add(new PostItem(R.drawable.dsz));
        postItems.add(new PostItem(R.drawable.gfd));
        postItems.add(new PostItem(R.drawable.sde));
        postItems.add(new PostItem(R.drawable.xde));
        postItems.add(new PostItem(R.drawable.qwe));
        postItems.add(new PostItem(R.drawable.tyy));
        postItems.add(new PostItem(R.drawable.mju));
        postItems.add(new PostItem(R.drawable.jkl));
        postItems.add(new PostItem(R.drawable.ffew));
        postItems.add(new PostItem(R.drawable.fgh));
        postItems.add(new PostItem(R.drawable.frr));
        postItems.add(new PostItem(R.drawable.vfr));
        postRecyclerView.setAdapter((new PostAdapter(postItems)));
*/





        return view;

    }
}
