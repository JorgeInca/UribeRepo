import { echartSetOption, tooltipFormatter } from '../echarts-utils';

const horizontalBarChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-horizontal-bar-chart-example'
  );
  const months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
  ];
  const data = [
    1020, 1160, 1300, 958, 1240, 1020, 1409, 1200, 1051, 1120, 1240, 1054
  ];

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'axis',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        formatter: tooltipFormatter,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        }
      },
      xAxis: {
        type: 'value',
        boundaryGap: false,
        axisLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-300')
          }
        },
        axisTick: { show: true },
        axisLabel: {
          color: getColor('gray-500')
        },
        splitLine: {
          show: false
        },
        min: 600
      },
      yAxis: {
        type: 'category',
        data: months,
        boundaryGap: true,
        axisLabel: {
          formatter: value => value.substring(0, 3),
          show: true,
          color: getColor('gray-500'),
          margin: 15
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-200')
          }
        },
        axisTick: { show: false },
        axisLine: {
          lineStyle: {
            color: getColor('gray-300')
          }
        }
      },
      series: [
        {
          type: 'bar',
          name: 'Total',
          data,
          lineStyle: { color: getColor('primary') },
          itemStyle: {
            color: getColor('primary'),
            barBorderRadius: [0, 3, 3, 0]
          },
          showSymbol: false,
          symbol: 'circle',
          smooth: false,
          hoverAnimation: true
        }
      ],
      grid: { right: '3%', left: '10%', bottom: '10%', top: '0%' }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default horizontalBarChartInit;
