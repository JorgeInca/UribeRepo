import { echartSetOption } from '../echarts-utils';

const barWaterFallChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-bar-waterfall-chart-example'
  );
  const days = [
    '2021-06-05',
    '2021-06-06',
    '2021-06-07',
    '2021-06-08',
    '2021-06-09',
    '2021-06-10',
    '2021-06-11',
    '2021-06-12',
    '2021-06-13',
    '2021-06-14',
    '2021-06-15'
  ];

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      legend: {
        data: ['Expenditure', 'Income'],
        textStyle: {
          color: getColor('gray-600')
        }
      },
      tooltip: {
        trigger: 'axis',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        /* eslint-disable prefer-destructuring */
        formatter: params => {
          let tar;
          if (params[1].value !== '-') {
            tar = params[1];
          } else {
            tar = params[2];
          }
          return `${window.dayjs(tar.name).format('MMM DD')}<br/>${
            tar.seriesName
          } : ${tar.value}`;
        },
        transitionDuration: 0,
        axisPointer: {
          type: 'shadow'
        }
      },
      xAxis: {
        type: 'category',
        data: days,
        axisLine: {
          lineStyle: {
            color: getColor('gray-300'),
            type: 'solid'
          }
        },
        axisTick: { show: false },
        axisLabel: {
          color: getColor('gray-400'),
          formatter: value => window.dayjs(value).format('MMM DD'),
          margin: 15
        },
        splitLine: {
          show: false
        }
      },
      yAxis: {
        type: 'value',
        boundaryGap: true,
        axisLabel: {
          show: true,
          color: getColor('gray-400'),
          margin: 15
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-200')
          }
        },
        axisTick: { show: false },
        axisLine: { show: false },
        min: 600
      },
      series: [
        {
          name: 'Assist',
          type: 'bar',
          stack: 'Total',
          itemStyle: {
            barBorderColor: 'transparent',
            color: 'transparent'
          },
          emphasis: {
            itemStyle: {
              barBorderColor: 'transparent',
              color: 'transparent'
            }
          },
          data: [0, 900, 1245, 1530, 1376, 1376, 1511, 1689, 1856, 1495, 1292]
        },
        {
          name: 'Income',
          type: 'bar',
          stack: 'Total',
          label: {
            show: true,
            position: 'top',
            color: getColor('gray-600')
          },
          data: [1000, 400, 350, '-', '-', 320, 180, 190, '-', '-', '-'],
          itemStyle: {
            color: getColor('primary'),
            barBorderRadius: [3, 3, 0, 0]
          }
        },
        {
          name: 'Expenditure',
          type: 'bar',
          stack: 'Total',
          label: {
            show: true,
            position: 'bottom',
            color: getColor('gray-600')
          },
          data: ['-', '-', '-', 100, 140, '-', '-', '-', 120, 345, 190],
          itemStyle: {
            color: getColor('success'),
            barBorderRadius: [3, 3, 0, 0]
          }
        }
      ],
      grid: { right: '3%', left: '10%', bottom: '10%', top: '10%' }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default barWaterFallChartInit;
