import { echartSetOption } from '../echarts-utils';

const gaugeGradeChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-gauge-grade-chart-example');

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      series: [
        {
          radius: '100%',
          type: 'gauge',
          center: ['50%', '70%'],
          startAngle: 180,
          endAngle: 0,
          min: 0,
          max: 1,
          splitNumber: 8,
          axisLine: {
            lineStyle: {
              width: 6,
              color: [
                [0.25, getColor('danger')],
                [0.5, getColor('warning')],
                [0.75, getColor('info')],
                [1, getColor('success')]
              ]
            }
          },
          pointer: {
            icon: 'path://M12.8,0.7l12,40.1H0.7L12.8,0.7z',
            length: '12%',
            width: 20,
            offsetCenter: [0, '-60%'],
            itemStyle: {
              color: 'auto'
            }
          },
          axisTick: {
            length: 12,
            lineStyle: {
              color: 'auto',
              width: 2
            }
          },
          splitLine: {
            length: 20,
            lineStyle: {
              color: 'auto',
              width: 5
            }
          },
          axisLabel: {
            color: getColor('gray-600'),
            distance: -60,
            formatter: value => {
              if (value === 0.875) {
                return 'Excellent';
              }
              if (value === 0.625) {
                return 'Good';
              }
              if (value === 0.375) {
                return 'Well';
              }
              if (value === 0.125) {
                return 'Bad';
              }
              return '';
            }
          },
          title: {
            offsetCenter: [0, '-20%'],
            color: getColor('gray-600')
          },
          detail: {
            offsetCenter: [0, '0%'],
            valueAnimation: true,
            formatter: value => {
              return Math.round(value * 100);
            },
            color: 'auto'
          },
          data: [
            {
              value: 0.7,
              name: 'Grade'
            }
          ]
        }
      ]
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default gaugeGradeChartInit;
