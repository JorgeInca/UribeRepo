import { echartSetOption } from '../echarts-utils';

const gaugeMultiRingChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-gauge-multiring-chart-example'
  );

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      series: [
        {
          type: 'gauge',
          startAngle: 90,
          endAngle: -270,
          radius: '85%',
          pointer: {
            show: false
          },
          center: ['50%', '50%'],
          progress: {
            show: true,
            overlap: false,
            roundCap: true,
            clip: false,
            itemStyle: {
              color: getColor('info')
            }
          },
          axisLine: {
            lineStyle: {
              width: 8,
              color: [[1, getColor('gray-200')]]
            }
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false
          },
          data: [79],
          detail: {
            show: false
          },
          animationDuration: 2000
        },
        {
          type: 'gauge',
          startAngle: 90,
          endAngle: -270,
          radius: '70%',
          pointer: {
            show: false
          },
          center: ['50%', '50%'],
          progress: {
            show: true,
            overlap: false,
            roundCap: true,
            clip: false,
            itemStyle: {
              color: getColor('primary')
            }
          },
          axisLine: {
            lineStyle: {
              width: 8,
              color: [[1, getColor('gray-200')]]
            }
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false
          },
          data: [85],
          detail: {
            show: false
          },
          animationDuration: 2000
        },
        {
          type: 'gauge',
          startAngle: 90,
          endAngle: -270,
          radius: '55%',
          pointer: {
            show: false
          },
          center: ['50%', '50%'],
          progress: {
            show: true,
            overlap: false,
            roundCap: true,
            clip: false,
            itemStyle: {
              color: getColor('success')
            }
          },
          axisLine: {
            lineStyle: {
              width: 8,
              color: [[1, getColor('gray-200')]]
            }
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false
          },
          data: [70],
          detail: {
            show: false
          },
          animationDuration: 2000
        }
      ]
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default gaugeMultiRingChartInit;
