package id.ac.ub.filkom.se.kcv.astechlauncher.controller.mainapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

import id.ac.ub.filkom.se.kcv.astechlauncher.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Prediksi.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Prediksi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Prediksi extends Fragment implements AdapterView.OnItemSelectedListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] mata_uang = {"Dolar-US", "Ringgit-Malaysia", "Yen-Japan", "Yuan-China","Euro-Eropa"};
    String[] range_hari = {"1 hari (hari ini)", "2 hari", "3 hari", "4 hari","5 hari","6 hari","7 hari"};
    String[] listArray = {"Android ListView Example","ListVIew Android","Simple Android ListView","ListView in Android","Android ListView Example","ListVIew Android","Simple Android ListView","ListView in Android","Android ListView Example","ListVIew Android","Simple Android ListView","ListView in Android"};

    private OnFragmentInteractionListener mListener;

    public Prediksi()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Prediksi.
     */
    // TODO: Rename and change types and number of parameters
    public static Prediksi newInstance(String param1, String param2)
    {
        Prediksi fragment = new Prediksi();
        Bundle args     = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mainapp_activity_main_fragment_prediksi, container, false);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.textview_spinner_setting, mata_uang);

        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner) view.findViewById(R.id.spinner_mata_uang);
        materialDesignSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this.getActivity(),R.layout.textview_spinner_setting, range_hari);

        MaterialBetterSpinner materialDesignSpinner2 = (MaterialBetterSpinner) view.findViewById(R.id.spinner_range_hari);
        materialDesignSpinner2.setAdapter(arrayAdapter2);

        MyAdapter adapter = new MyAdapter(this.getContext(), addData());
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        return view;
    }



    public ArrayList<Item> addData(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("No","Tanggal","Nilai tukar"));
        items.add(new Item("1","15-10-2016","Rp. 13.590"));
        items.add(new Item("2","15-10-2016","Rp. 13.590"));
        items.add(new Item("3","15-10-2016","Rp. 13.590"));

        return items;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if(mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
