package creationsofali.expressbooking;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Swai on 9/11/2016.
 */
public class MyCustomAdapter extends BaseAdapter {

    LayoutInflater inflater;
    String[] availabeServices;
    String origin;
    String destination;


    //ctor
    public MyCustomAdapter(Context context, String[] data, String from, String to){
        availabeServices = data;
        Log.d("Passed data count ", "= "+ data.length);
        Log.d("Passed origin ", "= "+ from);
        Log.d("Passed destination ", "= "+ to);
        origin = from;
        destination = to;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Returns the item counts in the array
    @Override
    public int getCount() {
        return availabeServices.length;
    }

    //Returns an item in the specific position
    @Override
    public Object getItem(int position) {
        return availabeServices[position];
    }

    //Returns the item position
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.list_item, null);
        TextView companyName = (TextView) mView.findViewById(R.id.companyName);
        TextView time = (TextView) mView.findViewById(R.id.time);
        TextView from = (TextView) mView.findViewById(R.id.origin);
        TextView to = (TextView) mView.findViewById(R.id.destination);
        TextView busType = (TextView) mView.findViewById(R.id.busType);

        //Extracting data from array of strings
        String correctData = availabeServices[position];
        String[] splitArray = correctData.split(" - ");  // index 0 = companyName, index 1 = time, index 2 = busType
        Log.d("Bus name: ", splitArray[0]);
        Log.d("Time: ", splitArray[1]);

        //Setting values to corresponding widgets in mView
        companyName.setText(splitArray[0]);
        time.setText(splitArray[1]);
        busType.setText(splitArray[2]);
        from.setText(origin);
        to.setText(destination);

        return mView;
    }
}
