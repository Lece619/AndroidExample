package com.lece.ex_0728;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Page extends Fragment {

    View layout = null;
    int position;

    public void setPosition(int position){
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(position == 3){
            layout = (FrameLayout)inflater.inflate(R.layout.fragment_page2, container, false);
        }else{
            layout = (LinearLayout)inflater.inflate(R.layout.fragment_page, container, false);
            TextView text = layout.findViewById(R.id.text);
            Button btn = layout.findViewById(R.id.btn);
            text.setText("page"+ (position+1));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "page"+(position+1) + "버튼 클릭", Toast.LENGTH_SHORT).show();
                }
            });
        }
        // Inflate the layout for this fragment
        return layout;
    }
}