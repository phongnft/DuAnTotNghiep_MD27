package com.example.duantotnghiep_md27.Model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep_md27.ItemTouchHelperAdapter;

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private final ItemTouchHelperAdapter itemTouchHelperAdapter;
    public MyItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.itemTouchHelperAdapter = itemTouchHelperAdapter;
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0; // Không hỗ trợ di chuyển
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.END; // Hỗ trợ trượt từ phải sang trái và ngược lại
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

}
