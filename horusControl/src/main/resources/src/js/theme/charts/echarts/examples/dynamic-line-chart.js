import { echartSetOption, tooltipFormatter } from '../echarts-utils';

const dynamicLineChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-dynamic-line-chart-example');

  const data = [];
  let now = +new Date(1997, 9, 3);
  const oneDay = 24 * 3600 * 1000;
  let value = Math.random() * 1000;

  const randomData = () => {
    now = new Date(+now + oneDay);
    value = value + Math.random() * 21 - 10;
    return {
      name: now.toString(),
      value: [
        [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
        Math.round(value)
      ]
    };
  };

  // eslint-disable-next-line no-plusplus
  for (let i = 0; i < 1000; i++) {
    data.push(randomData());
  }

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          animation: false
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
        type: 'time',
        splitLine: {
          show: false
        },
        axisLabel: {
          color: getColor('gray-500')
        },

        axisLine: {
          lineStyle: {
            color: getColor('gray-300')
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
        boundaryGap: [0, '100%'],
        splitLine: {
          show: false
        },
        axisLabel: {
          color: getColor('gray-500')
        }
      },
      series: [
        {
          name: 'Total',
          type: 'line',
          showSymbol: false,
          hoverAnimation: false,
          data,
          lineStyle: {
            color: getColor('primary')
          },
          itemStyle: {
            color: getColor('white'),
            borderColor: getColor('primary'),
            borderWidth: 2
          },
          symbol: 'circle',
          symbolSize: 10
        }
      ],
      grid: { right: 5, left: '7%', bottom: '10%', top: '5%' }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);

    setInterval(() => {
      // eslint-disable-next-line no-plusplus
      for (let i = 0; i < 5; i++) {
        data.shift();
        data.push(randomData());
      }

      chart.setOption({
        series: [
          {
            data
          }
        ]
      });
    }, 1000);
  }
};

export default dynamicLineChartInit;
