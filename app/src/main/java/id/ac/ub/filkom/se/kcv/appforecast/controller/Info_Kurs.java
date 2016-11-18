package id.ac.ub.filkom.se.kcv.appforecast.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.ub.filkom.se.kcv.appforecast.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Info_Kurs.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Info_Kurs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Info_Kurs extends Fragment implements DatePickerDialog.OnDateSetListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public               Calendar tanggal = Calendar.getInstance();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tanggal_awal;
    TextView tanggal_akhir;
    int status_tanggal;
    public               boolean  cek     = true;

    private OnFragmentInteractionListener mListener;

    public Info_Kurs()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Info_Kurs newInstance(String param1, String param2)
    {
        Info_Kurs fragment = new Info_Kurs();
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
        View   view   = inflater.inflate(R.layout.mainapp_activity_main_fragment_info, container, false);

        tanggal_awal = (TextView) view.findViewById(R.id.input_tanggal_awal);
        tanggal_akhir = (TextView) view.findViewById(R.id.input_tanggal_akhir);

        tanggal_awal.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(cek)
                {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            Info_Kurs.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setAccentColor(getResources().getColor(R.color.primary));
                    dpd.setMaxDate(now);
                    dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
                    cek = false;
                    status_tanggal = 0;
                }
                else
                {
                    cek = true;
                }
            }
        });

        tanggal_awal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Info_Kurs.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                status_tanggal = 0;
                dpd.setAccentColor(getResources().getColor(R.color.primary));
                dpd.setMaxDate(now);
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        tanggal_akhir.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(cek)
                {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            Info_Kurs.this,
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    status_tanggal = 1;
                    dpd.setAccentColor(getResources().getColor(R.color.primary));
                    dpd.setMaxDate(now);
                    dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
                    cek = false;
                }
                else
                {
                    cek = true;
                }
            }
        });

        tanggal_akhir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Info_Kurs.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                status_tanggal = 1;
                dpd.setAccentColor(getResources().getColor(R.color.primary));
                dpd.setMaxDate(now);
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        return view;
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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        tanggal.set(year, monthOfYear, dayOfMonth);
        if(status_tanggal==0){
            tanggal_awal.setText(date);
        }else{
            tanggal_akhir.setText(date);
        }

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
