import { echartSetOption } from '../echarts-utils';

const radarChartInit = () => {
  const { getColor, getData, rgbaColor } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-radar-chart-example');

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      legend: {
        orient: 'vertical',
        left: 'left',
        textStyle: {
          color: getColor('gray-600')
        }
      },
      tooltip: {
        trigger: 'item',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        },
        position(pos, ...size) {
          if (window.innerWidth <= 540) {
            const tooltipHeight = size[1].offsetHeight;
            const obj = { top: pos[1] - tooltipHeight - 20 };
            obj[pos[0] < size[3].viewSize[0] / 2 ? 'left' : 'right'] = 5;
            return obj;
          }
          return null;
        }
      },

      radar: {
        indicator: [
          { name: 'Marketing', max: 6500 },
          { name: 'Admin', max: 16000 },
          { name: 'Tech', max: 30000 },
          { name: 'Support', max: 38000 },
          { name: 'Dev ', max: 52000 },
          { name: 'Sales ', max: 25000 }
        ],
        radius: 120,
        splitLine: {
          lineStyle: {
            color: rgbaColor(getColor('gray-700'))
          }
        }
      },

      series: [
        {
          type: 'radar',
          data: [
            {
              value: [4200, 3000, 20000, 35000, 50000, 18000],
              name: 'Data A',
              itemStyle: {
                color: getColor('primary')
              }
            },
            {
              value: [5000, 14000, 28000, 26000, 42000, 21000],
              name: 'Data B',
              itemStyle: {
                color: getColor('warning')
              }
            }
          ]
        }
      ]
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default radarChartInit;
