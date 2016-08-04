package com.geziwulian.geziandroid.fragment.order;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.order.adapter.OrderAdapter;
import com.geziwulian.geziandroid.utils.DividerDecoration;
import com.geziwulian.netlibrary.model.dinner.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 志浩 on 2016/8/2.
 *
 * layout 使用的homeFragment
 */
public class OrderFragment extends BaseFragment {

    @BindView(R.id.home_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.home_swipe)
    SwipeRefreshLayout mSwipe;

    private OrderAdapter adapter;
    private Unbinder unbinder;

    private List<String> testList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        unbinder = ButterKnife.bind(this,view);
        initView();
        initDemo();

    }

    private  void initDemo(){
        for (int i=0;i<20;i++){
            testList.add("item"+i);
        }
        adapter.addItem(testList);
    }

    private void initView() {
        adapter = new OrderAdapter(getActivity());
        mRecycler.setHasFixedSize(true);
        mRecycler.addItemDecoration(new DividerDecoration(getActivity()));
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(adapter);
        addItemSwipeHelper(mRecycler);
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showToast("刷新动作");
            }
        });
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingTolast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastItem == (totalItemCount - 1) && isSlidingTolast) {
                        showToast("到底部操作");
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isSlidingTolast = true;
                } else {
                    isSlidingTolast = false;
                }
            }

        });


    }

    private void addItemSwipeHelper(RecyclerView recyclerView) {
        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; //no drag-n-drop
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                // TODO: 2016/8/3


                adapter.notifyItemRemoved(index);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                getDefaultUIUtil().clearView(((OrderAdapter.ViewHolder) viewHolder).vItem);
                ((OrderAdapter.ViewHolder) viewHolder).vBackground.setBackgroundColor(Color.TRANSPARENT);

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (viewHolder != null) {
                    getDefaultUIUtil().onSelected(((OrderAdapter.ViewHolder) viewHolder).vItem);
                }
            }

            @Override
            public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                getDefaultUIUtil().onDraw(canvas, recyclerView, ((OrderAdapter.ViewHolder) viewHolder).vItem, dX, dY, actionState, isCurrentlyActive);
                if (dX < 0) { // 向左滑动是的提示
                    ((OrderAdapter.ViewHolder) viewHolder).vBackground.setBackgroundResource(R.color.red);
                }
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
