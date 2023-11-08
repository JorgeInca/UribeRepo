import {
  echartSetOption,
  handleTooltipPosition,
  tooltipFormatter
} from '../echarts-utils';

const barLineMixedChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-bar-line-mixed-chart-example'
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

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: getColor('gray-500')
          },
          label: {
            show: true,
            backgroundColor: getColor('gray-600'),
            color: getColor('gray-100')
          }
        },
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        position: (...params) => handleTooltipPosition(params),
        formatter: params => tooltipFormatter(params)
      },
      toolbox: {
        top: 0,
        feature: {
          dataView: { show: false },
          magicType: {
            show: true,
            type: ['line', 'bar']
          },
          restore: { show: true },
          saveAsImage: { show: true }
        },
        iconStyle: {
          borderColor: getColor('gray-700'),
          borderWidth: 1
        },

        emphasis: {
          iconStyle: {
            textFill: getColor('gray-600')
          }
        }
      },
      legend: {
        top: 40,
        data: ['Evaporation', 'Precipitation', 'Average temperature'],
        textStyle: {
          color: getColor('gray-600')
        }
      },
      xAxis: [
        {
          type: 'category',
          data: months,
          axisLabel: {
            color: getColor('gray-600'),
            formatter: value => value.slice(0, 3)
          },
          axisPointer: {
            type: 'shadow'
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: getColor('gray-300')
            }
          }
        }
      ],
      yAxis: [
        {
          type: 'value',
          min: 0,
          max: 250,
          interval: 50,
          axisLabel: {
            color: getColor('gray-600'),
            formatter: '{value} ml'
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: getColor('gray-200')
            }
          }
        },
        {
          type: 'value',
          min: 0,
          max: 25,
          interval: 5,
          axisLabel: {
            color: getColor('gray-600'),
            formatter: '{value} °C'
          },

          splitLine: {
            show: true,
            lineStyle: {
              color: getColor('gray-200')
            }
          }
        }
      ],
      series: [
        {
          name: 'Evaporation',
          type: 'bar',
          data: [
            2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
          ],
          itemStyle: {
            color: getColor('primary'),
            barBorderRadius: [3, 3, 0, 0]
          }
        },
        {
          name: 'Precipitation',
          type: 'bar',
          data: [
            2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
          ],
          itemStyle: {
            color: getColor('info'),
            barBorderRadius: [3, 3, 0, 0]
          }
        },
        {
          name: 'Average temperature',
          type: 'line',
          yAxisIndex: 1,
          data: [
            2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2
          ],
          lineStyle: {
            color: getColor('warning')
          },
          itemStyle: {
            color: getColor('white'),
            borderColor: getColor('warning'),
            borderWidth: 2
          },
          symbol: 'circle',
          symbolSize: 10
        }
      ],
      grid: {
        right: 5,
        left: 5,
        bottom: 5,
        top: '23%',
        containLabel: true
      }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default barLineMixedChartInit;
