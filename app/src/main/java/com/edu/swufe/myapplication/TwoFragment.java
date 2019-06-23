package com.edu.swufe.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    double[] values = {0.00, 0.00, 0.00, 0.00};
    double sumVal = values[0] + values[1] + values[2] + values[3];
    int[] colors = {Color.BLUE, Color.GRAY, Color.MAGENTA, Color.CYAN};

    private LinearLayout ll_expense_piechart;
    private GraphicalView graphicalView;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);

        initPieChart(v);

        return v;
    }

    private void initPieChart(View v) {
        CategorySeries dataset = bulidCategoryDataset("图文报表", values);
        DefaultRenderer renderer = buildCategoryRenderer(colors);

        ll_expense_piechart = (LinearLayout) v.findViewById(R.id.ll_expense_piechart);
        ll_expense_piechart.removeAllViews();

        graphicalView = ChartFactory.getPieChartView(getContext()
                ,dataset, renderer);//饼状图
        graphicalView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        ll_expense_piechart.addView(graphicalView);
    }

    private CategorySeries bulidCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("房租:"+values[0], values[0]/sumVal);
        series.add("伙食费:"+values[1], values[1]/sumVal);
        series.add("生活费:"+values[2], values[2]/sumVal);
        series.add("其它:"+values[3], values[3]/sumVal);
        return series;
    }

    private DefaultRenderer getPieRenderer(){
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setZoomButtonsVisible(true);//设置显示放大放小缩小按钮
        renderer.setZoomEnabled(true);//设置允许放大放小
        //设置各个类别分别对应的颜色
        SimpleSeriesRenderer yellowRenderer = new SimpleSeriesRenderer();
        yellowRenderer.setColor(Color.YELLOW);
        SimpleSeriesRenderer blueRenderer = new SimpleSeriesRenderer();
        blueRenderer.setColor(Color.BLUE);
        SimpleSeriesRenderer redRenderer = new SimpleSeriesRenderer();
        redRenderer.setColor(Color.RED);
        renderer.addSeriesRenderer(yellowRenderer);
        renderer.addSeriesRenderer(blueRenderer);
        renderer.addSeriesRenderer(redRenderer);

        renderer.setLabelsTextSize(30);//设置坐标字号
        renderer.setLegendTextSize(50);//设置图例字号
        renderer.setApplyBackgroundColor(true);//设置是否应用背景色
        renderer.setBackgroundColor(Color.BLACK);

        return renderer;
    }
    protected DefaultRenderer buildCategoryRenderer(int[] colors){
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLegendTextSize(35);//设置左下角标注文字的大小
        renderer.setLabelsTextSize(25);//饼图上标记文字的字体大小
        renderer.setLabelsColor(Color.BLACK);//饼图上标记文字的颜色
        renderer.setPanEnabled(false);
        //renderer.setDisplayValues(true);//显示数据


        for(int color : colors){
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            //设置百分比
            //r.setChartValuesFormat(NumberFormat.getPercentInstance());
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
}
