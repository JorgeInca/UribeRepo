import { echartSetOption, handleTooltipPosition } from './echarts-utils';

const { echarts } = window;

/* -------------------------------------------------------------------------- */
/*                                Market Share                                */
/* -------------------------------------------------------------------------- */

const payingCustomerChartInit = () => {
  const { getData, getColor } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echarts-paying-customer-chart');

  if ($chartEl) {
    const userOptions = getData($chartEl, 'options');
    const chart = echarts.init($chartEl);

    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'item',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        position: (...params) => handleTooltipPosition(params),
        transitionDuration: 0,
        formatter: params => {
          return `<strong>${params.seriesName}:</strong> ${params.value}%`;
        }
      },
      legend: { show: false },
      series: [
        {
          type: 'gauge',
          center: ['50%', '60%'],
          name: 'Paying customer',
          startAngle: 180,
          endAngle: 0,
          min: 0,
          max: 100,
          splitNumber: 12,
          itemStyle: {
            color: getColor('primary')
          },
          progress: {
            show: true,
            roundCap: true,
            width: 12,
            itemStyle: {
              shadowBlur: 0,
              shadowColor: '#0000'
            }
          },
          pointer: {
            show: false
          },
          axisLine: {
            roundCap: true,
            lineStyle: {
              width: 12,
              color: [[1, getColor('primary-100')]]
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: false
          },
          axisLabel: {
            show: false
          },
          title: {
            show: false
          },
          detail: {
            show: false
          },
          data: [
            {
              value: 30
            }
          ]
        }
      ]
    });

    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default payingCustomerChartInit;
