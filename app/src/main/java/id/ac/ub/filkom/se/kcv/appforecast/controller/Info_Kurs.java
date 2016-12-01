package id.ac.ub.filkom.se.kcv.appforecast.controller;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.DTDHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.ac.ub.filkom.se.kcv.appforecast.R;
import id.ac.ub.filkom.se.kcv.appforecast.controller.server.JSONParser;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


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
    Button cek_chart,cek_kurs;

    LineChart chart;
    public               boolean  cek     = true;

    JSONParser jParser = new JSONParser();
    ArrayList<Kurs> data_kurs = new ArrayList<Kurs>();
    JSONArray dt_kurs = null;
    String url_read_data = "http://lide-app.com/appforecast/database/read_real_kurs.php";
    // JSON Node names, ini harus sesuai yang di API
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_NO = "no";
    public static final String TAG_TANGGAL = "tanggal";
    public static final String TAG_KURS = "kurs";
    public static final String TAG_DATA = "data_kurs";

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
    private int index=1;
    View view = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view   = inflater.inflate(R.layout.mainapp_activity_main_fragment_info, container, false);

        tanggal_awal = (TextView) view.findViewById(R.id.input_tanggal_awal);
        tanggal_akhir = (TextView) view.findViewById(R.id.input_tanggal_akhir);

        cek_kurs = (Button) view.findViewById(R.id.buttonLihatKurs);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        cek_kurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadKurs read = (ReadKurs) new ReadKurs().execute();
            }
        });

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
                    dpd.setAccentColor(getResources().getColor(R.color.primary));
                    dpd.setMaxDate(now);
                    dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
                    cek = false;
                    status_tanggal = 1;
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


        //button cek chart
        cek_chart = (Button) view.findViewById(R.id.cek_chart);
        cek_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_box_chart);
//                dialog.setTitle("Chart info chart");
                LineChartView lineChart = (LineChartView) dialog.findViewById(R.id.info_linechart_dialog);

                List<PointValue> values = new ArrayList<PointValue>();
                values.add(new PointValue(0, 13456));
                values.add(new PointValue(1, 4897));
//                values.add(new PointValue(2, 12784));
//                values.add(new PointValue(3, 4));
//                values.add(new PointValue(4, 1));
//                values.add(new PointValue(5, 8));
//                Toast.makeText(view.getContext(), ""+tmp.size(), Toast.LENGTH_SHORT).show();
//                for (int i = 1; i <tmp.size() ; i++) {
//
//                    values.add(new PointValue((float)(i-1),14253));
//                }


                //In most cased you can call data model methods in builder-pattern-like manner.
                Line line = new Line(values).setColor(Color.BLACK).setCubic(true);
                lineChart.setOnValueTouchListener(new LineChartOnValueSelectListener() {
                    @Override
                    public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
                        Toast.makeText(view.getContext(), "Kurs tanggal "+value.getX()+" : Rp. " +pointIndex, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onValueDeselected() {

                    }
                });
                List<Line> lines = new ArrayList<Line>();
                lines.add(line);

                LineChartData data = new LineChartData();
                data.setLines(lines);

                //set axis left n buttom
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                axisX.setName("Tanggal");
                axisY.setName("Kurs (Rp.)");
                axisY.setTextColor(R.color.black);
                axisY.setMaxLabelChars(5);
                axisX.setValues(getXAxisValues());
                axisX.setTextColor(R.color.black);

                data.setAxisXBottom(new Axis(getXAxisValues()).setHasLines(true));
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
                data.setBaseValue(Float.NEGATIVE_INFINITY);

                lineChart.setLineChartData(data);
//                ArrayList<Entry> entries = new ArrayList<>();
//                entries.add(new Entry(4f, 0));
//                entries.add(new Entry(8f, 1));
//                entries.add(new Entry(6f, 2));
//                entries.add(new Entry(2f, 3));
//                entries.add(new Entry(18f, 4));
//                entries.add(new Entry(9f, 5));
//
//                LineDataSet dataset = new LineDataSet(entries, "# of Calls");
//
//                ArrayList<String> labels = new ArrayList<String>();
//                labels.add("January");
//                labels.add("February");
//                labels.add("March");
//                labels.add("April");
//                labels.add("May");
//                labels.add("June");
//
//                LineData data = new LineData(labels, dataset);
//                dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
//                dataset.setDrawCubic(true);
//                dataset.setDrawFilled(true);
//
//                lineChart.setData(data);
//                lineChart.animateY(5000);
//        chart.setDrawGridBackground(false);
//                chart.setBackgroundColor(R.color.white);
//                XAxis x = chart.getXAxis();
//                x.setTextSize(12f);
//                x.setLabelRotationAngle(-90);
//                x.setDrawGridLines(false);
////                x.setAxisLineWidth(12f);
////        chart.getXAxis().setLabelsToSkip(0);
////        chart.getLineData().getDataSets().get(0).lineDataSet.setDrawCubic(true);
//                x.setValueFormatter(new IAxisValueFormatter() {
//
//                    @Override
//                    public String getFormattedValue(float value, AxisBase axis) {
////                System.out.println(index);
//
////                        int dt = Math.round(value);
//                        return ""+(value);  // xVal is a string array
//                    }
//
//                    @Override
//                    public int getDecimalDigits() {
//                        return 0;
//                    }
//
//                });
//
//                index=1;
////axis to the bottom
//                x.setPosition(XAxis.XAxisPosition.BOTTOM);
//
//                YAxis y = chart.getAxisLeft();
//
//                y.setValueFormatter(new IAxisValueFormatter() {
//                    @Override
//                    public String getFormattedValue(float value, AxisBase axis) {
//                        return "Rp. "+(int)value;
//                    }
//
//                    @Override
//                    public int getDecimalDigits() {
//                        return 0;
//                    }
//                });
//                y.setTextSize(12f);
//                y.setSpaceTop(50);
//                y.setSpaceBottom(50);
////                y.setLabelCount(4, true);
////                y.setAxisLineWidth(20f);
//                chart.getAxisRight().setEnabled(false);
//                LineDataSet set1;
//
//                // create a dataset and give it a type
//                set1 = new LineDataSet(getYAxisValues(), "Info Kurs");
//                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//                set1.setColor(ColorTemplate.getHoloBlue());
//                set1.setCircleColor(R.color.primary_dark);
//                set1.setLineWidth(4f);
//                set1.setCircleRadius(5f);
//                set1.setFillAlpha(65);
//                set1.setFillColor(ColorTemplate.getHoloBlue());
//                set1.setHighLightColor(Color.rgb(244, 117, 117));
//                set1.setDrawCircleHole(true);
//                set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
////        set1.setDrawCubic(true)
//
//                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//                dataSets.add(set1); // add the datasets
//
//                // create a data object with the datasets
//                LineData dataChart = new LineData(dataSets);
//
//                // set data
//                chart.setData(dataChart);
                // Setting dialogview
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });

        final String [] xAxis = {"hari ke-1","hari ke-2","hari ke-3","hari ke-4","hari ke-5","hari ke-6","hari ke-7"};
        //set value chart


        return view;
    }
//    public int a=0;
ArrayList<Kurs> tmp=null;
    public ArrayList<Kurs> addData(){
        ArrayList<Kurs> items = new ArrayList<Kurs>();
        items.add(new Kurs("No","Tanggal","Nilai tukar"));
//        items.add(new Kurs("1","15-10-2016","Rp. 13.590"));
//        items.add(new Kurs("2","15-10-2016","Rp. 13.590"));
//        items.add(new Kurs("3","15-10-2016","Rp. 13.590"));
//        items.add(new Kurs("4","15-10-2016","Rp. 13.590"));
//        items.add(new Kurs("5","15-10-2016","Rp. 13.590"));


        List<NameValuePair> parameter = new ArrayList<NameValuePair>();
        try {
            JSONObject json = jParser.makeHttpRequest(url_read_data,"POST", parameter);

            int success = json.getInt(TAG_SUCCESS);
//            Toast.makeText(view.getContext(), success+" : halo", Toast.LENGTH_LONG).show();
            if (success == 1) { //Ada record Data (SUCCESS = 1)

                //Getting Array of daftar_mhs
                dt_kurs = json.getJSONArray(TAG_DATA);
//                Toast.makeText(view.getContext(), dt_kurs.length()+" : length", Toast.LENGTH_LONG).show();
                // looping through All daftar_mhs
                for (int i = 0; i < dt_kurs.length() ; i++){
                    JSONObject c = dt_kurs.getJSONObject(i);
//                    tempMhs = new Mahasiswa();
//                    tempMhs.setMhsId(c.getString(TAG_ID_MHS));
//                    tempMhs.setMhsName(c.getString(TAG_NAMA_MHS));
//                    tempMhs.setMhsNIM(c.getString(TAG_NIM_MHS));
//                    daftar_mhs.add(tempMhs);
                    items.add(new Kurs((i+1)+"",c.getString(TAG_TANGGAL),c.getString(TAG_KURS)));
                }

                Toast.makeText(view.getContext(), "Load data sukses", Toast.LENGTH_LONG).show();
            }
            else {

            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(view.getContext(), "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
        }
        tmp = items;
        return items;
    }

    private ArrayList<AxisValue> getYAxisValues() {

//        ArrayList<Entry> valueSet1 = new ArrayList<>();
//        Entry val1 = new Entry(15-11-2016, 12000); // Jan
//        valueSet1.add(val1);
//        Entry val2 = new Entry(16-11-2016, 13000); // Jan
//        valueSet1.add(val2);
//        Entry val3 = new Entry(17-11-2016, 14000); // Jan
//        valueSet1.add(val3);
//        Entry val4 = new Entry(18-11-2016, 12000); // Jan
//        valueSet1.add(val4);
//        Entry val5 = new Entry(19-11-2016, 12000); // Jan
//        valueSet1.add(val5);
//        Entry val6 = new Entry(20-11-2016, 12200); // Jan
//        valueSet1.add(val6);
        ArrayList<AxisValue> yAxis = new ArrayList<>();
        for (int i = 1; i < tmp.size(); i++) {
            yAxis.add(new AxisValue(i-1,tmp.get(i).getHasil().toCharArray()));
        }
//        yAxis.add(new AxisValue(0,"Rp. 13.500".toCharArray()));
//        yAxis.add(new AxisValue(1,"Rp. 13.500".toCharArray()));
//        yAxis.add(new AxisValue(2,"Rp. 13.500".toCharArray()));
//        yAxis.add(new AxisValue(3,"Rp. 13.500".toCharArray()));
//        yAxis.add(new AxisValue(4,"Rp. 13.500".toCharArray()));
//        yAxis.add(new AxisValue(5,"Rp. 13.500".toCharArray()));

        return yAxis;
    }

    private ArrayList<AxisValue> getXAxisValues() {
        ArrayList<AxisValue> xAxis = new ArrayList<>();
        for (int i = 1; i < tmp.size(); i++) {
            xAxis.add(new AxisValue(i-1,tmp.get(i).getHari().toCharArray()));
        }

//        xAxis.add(new AxisValue(0,"14-11-2016".toCharArray()));
//        xAxis.add(new AxisValue(1,"15-11-2016".toCharArray()));
//        xAxis.add(new AxisValue(2,"16-11-2016".toCharArray()));
//        xAxis.add(new AxisValue(3,"17-11-2016".toCharArray()));
//        xAxis.add(new AxisValue(4,"18-11-2016".toCharArray()));
//        xAxis.add(new AxisValue(5,"19-11-2016".toCharArray()));

        return xAxis;
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

    class ReadKurs extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(view.getContext());
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            String returnResult = "oke"; //memanggil method getMhsList()
            return returnResult;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if(result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(view.getContext(), "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if(result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(getContext(), "Data empty", Toast.LENGTH_LONG).show();
            }
            else
            {
                //list view hasil pencarian Kurs
                ArrayList data = addData();
                MyAdapter myadapter = new MyAdapter(view.getContext(), data);
                ListView listView = (ListView) view.findViewById(R.id.listview_hasil_kurs);
                listView.setAdapter(myadapter);

                listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (data.size()*100)));
            }
        }




    }
}
