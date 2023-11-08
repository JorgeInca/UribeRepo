import { echartSetOption, tooltipFormatter } from '../echarts-utils';

const seriesBarChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-series-bar-chart-example');

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      color: [getColor('primary'), getColor('info')],
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        },
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        formatter: params => tooltipFormatter(params)
      },
      xAxis: {
        type: 'value',
        axisLabel: {
          formatter: value => `${value / 1000}k`,
          color: getColor('gray-500')
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-300'),
            type: 'solid'
          }
        },
        splitLine: {
          lineStyle: {
            type: 'dashed',
            color: getColor('gray-200')
          }
        }
      },
      yAxis: {
        type: 'category',
        axisLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-300'),
            type: 'solid'
          }
        },
        axisLabel: {
          color: getColor('gray-500')
        },
        axisTick: { show: false },
        splitLine: { show: false },
        data: ['Brazil', 'Indonesia', 'USA', 'India', 'China']
      },
      series: [
        {
          name: '2011',
          type: 'bar',
          data: [131744, 104970, 29034, 235481, 132541],
          itemStyle: {
            barBorderRadius: [0, 3, 3, 0]
          }
        },
        {
          name: '2012',
          type: 'bar',
          data: [134141, 121594, 31000, 141201, 124115],
          itemStyle: {
            barBorderRadius: [0, 3, 3, 0]
          }
        }
      ],
      grid: { right: 15, left: '12%', bottom: '10%', top: 5 }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default seriesBarChartInit;
