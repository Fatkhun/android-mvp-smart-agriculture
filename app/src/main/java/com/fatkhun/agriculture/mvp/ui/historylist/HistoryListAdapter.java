package com.fatkhun.agriculture.mvp.ui.historylist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.ui.base.BaseViewHolder;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;


    HistoryListAdapter.Callback mCallback;
    private List<DataResponse> mData;
    List<DataResponse> mDataDefault;
    private String mType;
    Context context;

    public HistoryListAdapter(List<DataResponse> dataResponseList, Context context) {
        mData = dataResponseList;
        this.context = context;
    }

    public void setCallback(HistoryListAdapter.Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new HistoryListAdapter.ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_view, parent, false));
            case VIEW_TYPE_EMPTY:
                return new HistoryListAdapter.EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
            default:
                return new HistoryListAdapter.EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null && mData.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<DataResponse> dataResponses) {
        mData.clear();
        mData.addAll(dataResponses);
        mDataDefault = dataResponses;
        notifyDataSetChanged();
    }

//    public void filterByNameLocation(String query) {
//        List<PerformanceSummary> filteredSummaryPerformancePoiOsk = new ArrayList<>();
//        for (PerformanceSummary performancePoiOsk : mSummaryPerformancePoiOsksDefault){
//            if (performancePoiOsk.getTitlePoiOsk().toLowerCase().contains(query.toLowerCase()) ){
//                filteredSummaryPerformancePoiOsk.add(performancePoiOsk);
//            }
//        }
//        mSummaryPerformancePoiOsks.clear();
//        mSummaryPerformancePoiOsks.addAll(filteredSummaryPerformancePoiOsk);
//        notifyDataSetChanged();
//    }

    public interface Callback {
        void onBlogEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {

        TextView humidity;
        TextView soilMoisture;
        TextView temperature;
        TextView water;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);

            humidity = itemView.findViewById(R.id.tv_data_humidity);
            soilMoisture = itemView.findViewById(R.id.tv_data_soil_moisture);
            temperature = itemView.findViewById(R.id.tv_data_temperature);
            water = itemView.findViewById(R.id.tv_data_water);
            time = itemView.findViewById(R.id.tv_data_duration);


        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            DataResponse item = mData.get(position);
            Log.d("DEBUG", mData.toString());


            humidity.setText(String.valueOf(item.getHumidity()));
            soilMoisture.setText(String.valueOf(item.getSoilMoisture()));
            temperature.setText(String.valueOf(item.getTemp()));
            water.setText(String.valueOf(item.getWaterVolume() + " ml"));
            time.setText(String.valueOf(dateConverter(item.getTime())));

            itemView.setOnClickListener(v->{

            });

        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button btnRetry;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            btnRetry.setOnClickListener(v -> {
                if (mCallback != null)
                    mCallback.onBlogEmptyViewRetryClick();
            });
        }

        @Override
        protected void clear() {

        }

    }


    private void setTextOrnull(TextView textView, String text, String option) {
        if (option.equals(",")){
            DecimalFormat decim = new DecimalFormat("#,###,###");
            textView.setText(decim.format(Long.valueOf(text)));
        }else {
            if (text == null)
                textView.setText("0" + option);
            else {
                textView.setText(text + option);
            }
        }
    }

    private String dateConverter(String dateInput){
        try {
            SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            spf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date newDate = null;
            newDate = spf.parse(dateInput);
            spf= new SimpleDateFormat("dd MM yyyy HH:mm:ss");
            String returnDate = spf.format(newDate);
            return returnDate;

        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
