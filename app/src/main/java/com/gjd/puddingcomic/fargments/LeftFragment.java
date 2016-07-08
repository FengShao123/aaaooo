package com.gjd.puddingcomic.fargments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gjd.puddingcomic.EventBusJavaBean.LeftClickBackBean;
import com.gjd.puddingcomic.R;
import com.gjd.puddingcomic.activitys.MainActivity;

import org.simple.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeftFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftFragment extends Fragment
    implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView login;
    private Button register;
    private ImageView logo;

    public LeftFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftFragment.
     */
    public static LeftFragment newInstance(String param1, String param2) {
        LeftFragment fragment = new LeftFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_left, container, false);

        findView(v);

        return v;
    }

    /**
     * 找到每个view 给其添加监听
     * @param v
     */
    private void findView(View v) {
        //找到textview给其监听
        TextView upDate = (TextView) v.findViewById(R.id.update_fragment_left);
        TextView rankLink = (TextView) v.findViewById(R.id.rankLink_fragment_left);
        TextView end = (TextView) v.findViewById(R.id.end_fragment_left);
        TextView classify = (TextView) v.findViewById(R.id.classify_fragment_left);
        TextView image = (TextView) v.findViewById(R.id.image_fragment_left);
        TextView history = (TextView) v.findViewById(R.id.history_fragment_left);
        TextView setting = (TextView) v.findViewById(R.id.setting_fragment_left);
        TextView homePage = (TextView) v.findViewById(R.id.homePage_fragment_left);
        TextView random = (TextView) v.findViewById(R.id.random_fragment_left);

        random.setOnClickListener(this);
        homePage.setOnClickListener(this);
        upDate.setOnClickListener(this);
        rankLink.setOnClickListener(this);
        end.setOnClickListener(this);
        classify.setOnClickListener(this);
        image.setOnClickListener(this);
        history.setOnClickListener(this);
        setting.setOnClickListener(this);
        logo = ((ImageView) v.findViewById(R.id.logo_fragment_left));
        login = ((TextView) v.findViewById(R.id.login_fragment_left));
        register = (Button)v.findViewById(R.id.register_fragment_left);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        logo.setOnClickListener(this);
//System.out.println("---------->登录");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_fragment_left:
                System.out.println("---------->注册");
                break;
            case R.id.logo_fragment_left:
                System.out.println("---------->图片");
                break;
            case R.id.login_fragment_left:
                System.out.println("---------->登录");
                break;
        }
        EventBus.getDefault().post(new LeftClickBackBean(v));
    }
        /*public void textclick(View view){
            EventBus.getDefault().post(view);
        }*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
