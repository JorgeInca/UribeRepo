import { echartSetOption, tooltipFormatter } from '../echarts-utils';

const lineGradientChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-line-gradient-chart-example'
  );

  const data = [
    ['2021-06-05', 116],
    ['2021-06-06', 129],
    ['2021-06-07', 135],
    ['2021-06-08', 86],
    ['2021-06-09', 73],
    ['2021-06-10', 85],
    ['2021-06-11', 73],
    ['2021-06-12', 68],
    ['2021-06-13', 92],
    ['2021-06-14', 130],
    ['2021-06-15', 245],
    ['2021-06-16', 139],
    ['2021-06-17', 115],
    ['2021-06-18', 111],
    ['2021-06-19', 309],
    ['2021-06-20', 206],
    ['2021-06-21', 137],
    ['2021-06-22', 128],
    ['2021-06-23', 85],
    ['2021-06-24', 94],
    ['2021-06-25', 71],
    ['2021-06-26', 106],
    ['2021-06-27', 84],
    ['2021-06-28', 93],
    ['2021-06-29', 85],
    ['2021-06-30', 73],
    ['2021-07-01', 83],
    ['2021-07-02', 125],
    ['2021-07-03', 107],
    ['2021-07-04', 82],
    ['2021-07-05', 44],
    ['2021-07-06', 72],
    ['2021-07-07', 106],
    ['2021-07-08', 107],
    ['2021-07-09', 66],
    ['2021-07-10', 91],
    ['2021-07-11', 92],
    ['2021-07-12', 113],
    ['2021-07-13', 107],
    ['2021-07-14', 250],
    ['2021-07-15', 111],
    ['2021-07-16', 350],
    ['2021-07-17', 150],
    ['2021-07-18', 420],
    ['2021-07-19', 77],
    ['2021-07-20', 83],
    ['2021-07-21', 111],
    ['2021-07-22', 57],
    ['2021-07-23', 55],
    ['2021-07-24', 60]
  ];

  const dateList = data.map(item => {
    return item[0];
  });
  const valueList = data.map(item => {
    return item[1];
  });

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      visualMap: {
        show: false,
        type: 'continuous',
        dimension: 0,
        min: 0,
        max: dateList.length - 1,
        color: [getColor('danger'), getColor('warning')]
      },
      tooltip: {
        trigger: 'axis',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        },
        formatter: params => tooltipFormatter(params)
      },
      xAxis: {
        type: 'category',
        data: dateList,
        axisLabel: {
          formatter: value => window.dayjs(value).format('MMM DD'),
          color: getColor('gray-500'),
          margin: 15
        },
        axisLine: {
          lineStyle: {
            color: getColor('gray-300'),
            type: 'solid'
          }
        },
        axisPointer: {
          lineStyle: {
            color: getColor('gray-300')
          }
        }
      },
      yAxis: {
        type: 'value',
        axisLabel: {
          show: true,
          color: getColor('gray-500'),
          margin: 15
        },
        splitLine: {
          lineStyle: {
            color: getColor('gray-200'),
            type: 'dashed'
          }
        }
      },
      grid: { right: '3%', left: '8%', bottom: '10%', top: '5%' },
      series: {
        name: 'Total',
        type: 'line',
        showSymbol: false,
        symbolSize: 10,
        symbol: 'circle',
        data: valueList,
        itemStyle: {
          color: getColor('white'),
          borderWidth: 2
        }
      }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default lineGradientChartInit;
