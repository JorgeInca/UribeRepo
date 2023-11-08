import { echartSetOption } from '../echarts-utils';

const barRaceChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-bar-race-chart-example');

  let data = Array.from(Array(7).keys()).map(() =>
    Math.round(Math.random() * 200)
  );

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      xAxis: {
        max: 'dataMax',
        splitLine: {
          lineStyle: {
            color: getColor('gray-200')
          }
        },
        axisLabel: {
          color: getColor('gray-500')
        }
      },
      yAxis: {
        type: 'category',
        data: ['A', 'B', 'C', 'D', 'E', 'F', 'G'],
        inverse: true,
        axisLabel: {
          color: getColor('gray-500')
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-300')
          }
        },
        axisTick: {
          show: false
        },
        animationDuration: 300,
        animationDurationUpdate: 300,
        max: 4 // only the largest 5 bars will be displayed
      },
      series: [
        {
          realtimeSort: true,
          name: 'X',
          type: 'bar',
          data,
          label: {
            show: true,
            position: 'right',
            color: getColor('gray-700'),
            fontWeight: 500,
            valueAnimation: true
          },
          itemStyle: {
            color: getColor('primary'),
            barBorderRadius: [0, 3, 3, 0]
          }
        }
      ],
      animationDuration: 0,
      animationDurationUpdate: 3000,
      animationEasing: 'linear',
      animationEasingUpdate: 'linear',
      grid: {
        right: '10%',
        left: 5,
        bottom: 5,
        top: 5,
        containLabel: true
      }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);

    const run = () => {
      data = data.map(item =>
        Math.random() > 0.9
          ? item + Math.round(Math.random() * 2000)
          : item + Math.round(Math.random() * 200)
      );

      chart.setOption({
        series: [
          {
            data
          }
        ]
      });
    };

    setTimeout(() => {
      run();
    }, 0);
    setInterval(() => {
      run();
    }, 3000);
  }
};

export default barRaceChartInit;
