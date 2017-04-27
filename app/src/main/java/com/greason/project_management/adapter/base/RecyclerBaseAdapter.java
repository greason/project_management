package com.greason.project_management.adapter.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.greason.project_management.adapter.holder.ABRecyclerViewHolder;

import java.util.List;

/**
 * RecyclerBaseAdapter
 * Created by Greason on 16/6/7.
 */
public abstract class RecyclerBaseAdapter<T> extends RecyclerView.Adapter<ABRecyclerViewHolder> {

    protected int lastClickPosition = 0;
    protected boolean isShowChoose = false;
    protected boolean isChooseAll = false;

    public interface Item {
        int TYPE_HEADER = 0;
        int TYPE_FOOTER = 1;
        int TYPE_NORMAL = 2;
        int TYPE_HEADER_ONLY = 3;
        int TYPE_FOOTER_ONLY = 4;
        int TYPE_ACTIVITY = 5;
    }

    private Activity mActivity;
    protected List<T> list = null;

    protected View headerView;
    protected boolean hasHeader = false;
    protected boolean hasFooter = false;
    protected OnItemClickListener onItemClickListener;

    public RecyclerBaseAdapter(Activity mActivity, List<T> list) {
        this.mActivity = mActivity;
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isHeader(int position) {
        return hasHeader() && position == 0;
    }

    public boolean isFooter(int position) {
        if (hasHeader()) {
            return hasFooter() && position == list.size() + 1;
        } else {
            return hasFooter() && position == list.size();
        }
    }

    public View getHeaderView() {
        return headerView;
    }

    public boolean hasHeader() {
        return hasHeader;
    }

    public boolean hasFooter() {
        return hasFooter;
    }

    public int getHeaderCount() {
        return hasHeader() ? 1 : 0;
    }

    public int getFooterCount() {
        return hasFooter() ? 1 : 0;
    }

    public void setShowChoose(boolean showChoose) {
        isShowChoose = showChoose;
    }

    public boolean isShowChoose() {
        return isShowChoose;
    }

    public void setChooseAll(boolean chooseAll) {
        isChooseAll = chooseAll;
    }

    public boolean isChooseAll() {
        return isChooseAll;
    }

    @Override
    public ABRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*if (hasHeader() && viewType == Item.TYPE_HEADER) {
            return new HeaderViewHolder(getHeaderView());
        } else if (hasFooter() && viewType == Item.TYPE_FOOTER) {
            return new FooterViewHolder(getFooterView());
        } else*/ {
            return onCreateHolder(parent, viewType);
        }
    }

    public abstract ABRecyclerViewHolder onCreateHolder(ViewGroup parent, int viewType);

    protected void onBindItemView(ABRecyclerViewHolder holder, T item, int position){}

    @Override
    public void onBindViewHolder(ABRecyclerViewHolder holder, int position) {
        onBindItemView(holder, getItemByPosition(position), getPosition(position));
    }

    protected T getItemByPosition(int position) {
        return list.get(getPosition(position));
    }

    protected int getPosition(int position) {
        if (hasHeader()) {
            return position - 1;
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        count += (hasHeader() ? 1 : 0);
        count += (hasFooter() ? 1 : 0);
        count += list.size();
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int size = getItemCount();
        if (hasHeader() && position == 0) {
            return Item.TYPE_HEADER;
        } else if (hasFooter() && position == size - 1) {
            return Item.TYPE_FOOTER;
        }
        return Item.TYPE_NORMAL;
    }

    public void removeAll() {
        if (getList() != null) {
            getList().removeAll(getList());
            notifyDataSetChanged();
        }
    }

    public void addAll(List<T> list) {
        if (getList() != null) {
            getList().addAll(list);
            notifyDataSetChanged();
        }
    }

    public void removeItem(T item) {
        if (getList() != null) {
            getList().remove(item);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        if (getList() != null) {
            if (getHeaderCount() <= position && position < getItemCount()) {
                getList().remove(position);
                notifyDataSetChanged();
            }
        }
    }

    public Activity getActivity() {
        return mActivity;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    public void setLastClickPosition(int lastClickPosition) {
        this.lastClickPosition = lastClickPosition;
    }
}
