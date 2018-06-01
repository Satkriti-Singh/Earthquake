package com.example.prateek.earthquake;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EarthAdapter extends ArrayAdapter<Earthquake> {

    public EarthAdapter(Activity context, ArrayList<Earthquake> earthquakes)
    {
        super(context,0,earthquakes);
    }


   private String formatDate(Date dateObj) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
       // Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(dateObj);
        return dateFormat.format(dateObj);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    private static final String LOCATION_SEPARATOR = " of ";


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemview=convertView;
        if(listitemview==null)
        {
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }


        Earthquake currentearthquake=getItem(position);

        String originalLocation = currentearthquake.getMplace();
        String primaryLocation="india";
        String locationOffset="30km";

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentearthquake.getMmag());


        TextView magTextView=listitemview.findViewById(R.id.magnitude);
        magTextView.setText(output);

        GradientDrawable magnitudecircle=(GradientDrawable) magTextView.getBackground();
        int magnitudecolor=getMagnitudeColor(currentearthquake.getMmag());
        magnitudecircle.setColor(magnitudecolor);

        TextView placeTextView=listitemview.findViewById(R.id.location_offset);
        placeTextView.setText(locationOffset);

        TextView offTextView=listitemview.findViewById(R.id.primary_location);
        offTextView.setText(primaryLocation);

       Date dateObject=new Date(currentearthquake.getMmillisecond());

        TextView dateTextView=listitemview.findViewById(R.id.date);
        String formatteddate= formatDate(dateObject);
        dateTextView.setText(formatteddate);

        TextView timeView = listitemview.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView

        timeView.setText(formattedTime);

        return listitemview;

        /*
         word currentAndroidword = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.number);
        nameTextView.setText(currentAndroidword.getmDefaultTranslation());
        return super.getView(position, convertView, parent);
         */
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
