import { echartSetOption } from '../echarts-utils';

const singleAxisScatterChartInit = () => {
  const { getColor, getData, getRandomNumber } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-single-axis-scatter-chart-example'
  );

  const hours = [
    '12am',
    '1am',
    '2am',
    '3am',
    '4am',
    '5am',
    '6am',
    '7am',
    '8am',
    '9am',
    '10am',
    '11am',
    '12pm',
    '1pm',
    '2pm',
    '3pm',
    '4pm',
    '5pm',
    '6pm',
    '7pm',
    '8pm',
    '9pm',
    '10pm',
    '11pm'
  ];

  const days = [
    'Saturday',
    'Friday',
    'Thursday',
    'Wednesday',
    'Tuesday',
    'Monday',
    'Sunday'
  ];

  const data = [];
  for (let i = 0; i < 7; i += 1) {
    for (let j = 0; j < 24; j += 1) {
      data.push([j, i, getRandomNumber(0, 10)]);
    }
  }

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'item',
        axisPointer: {
          type: 'none'
        },
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        position: 'top',
        formatter: params => {
          return `
            ${days[params.value[1]]} <br/>
            ${hours[params.value[0]]} : ${params.value[2]}
          `;
        }
      },
      xAxis: {
        type: 'category',
        data: hours,
        boundaryGap: false,
        splitLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-200')
          }
        },
        axisLine: {
          show: false
        },
        axisTick: {
          lineStyle: {
            color: getColor('gray-600')
          }
        }
      },
      yAxis: {
        type: 'category',
        data: days,
        axisLine: {
          show: false
        },
        axisTick: {
          lineStyle: {
            color: getColor('gray-600')
          }
        },
        axisLabel: {
          margin: 15
        }
      },
      series: [
        {
          name: 'Punch Card',
          type: 'scatter',
          symbolSize: val => val[2] * 2,
          data,
          animationDelay: idx => {
            return idx * 5;
          },
          itemStyle: {
            color: getColor('primary')
          }
        }
      ],
      grid: {
        right: 12,
        left: 5,
        bottom: 5,
        top: 5,
        containLabel: true
      }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default singleAxisScatterChartInit;
