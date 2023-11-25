package com.example.duantotnghiep_md27.permission;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class PinEntryEdittex extends AppCompatEditText {

    protected OnPinEnteredListener mOnPinEnteredListener = null;

    public PinEntryEdittex(@NonNull Context context) {
        super(context);
    }

    public PinEntryEdittex(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PinEntryEdittex(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnPinEnteredListener(OnPinEnteredListener l) {
        mOnPinEnteredListener = l;
    }

    public interface OnPinEnteredListener {
        void onPinEntered(CharSequence str);
    }
}
