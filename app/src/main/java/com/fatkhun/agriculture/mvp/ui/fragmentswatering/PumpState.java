package com.fatkhun.agriculture.mvp.ui.fragmentswatering;

import com.fatkhun.agriculture.mvp.R;

public enum PumpState {
        PUMP_ON(R.drawable.ic_alarm_on_white_24dp,"ON"),
        PUMP_OFF(R.drawable.ic_alarm_off_white_24dp,"OFF"),
        AUTO_ON(R.drawable.ic_flash_on_white_24dp,"ON"),
        AUTO_OFF(R.drawable.ic_notifications_off_white_24dp,"OFF");

        private final int mType;
        private final String mText;

        PumpState(int type,String text) {
            mType = type;
            mText = text;
        }

        public int getType() {
            return mType;
        }

        public String getText() {
            return mText;
        }
}
