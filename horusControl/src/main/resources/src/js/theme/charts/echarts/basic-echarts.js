// import dayjs from 'dayjs';
// import * as echarts from 'echarts';
import { echartSetOption } from './echarts-utils';
/* -------------------------------------------------------------------------- */
/*                     Echart Bar Member info                                 */
/* -------------------------------------------------------------------------- */

const basicEchartsInit = () => {
  const { getColor, getData, getDates } = window.phoenix.utils;

  const $echartBasicCharts = document.querySelectorAll('[data-echarts]');
  $echartBasicCharts.forEach($echartBasicChart => {
    const userOptions = getData($echartBasicChart, 'echarts');
    const chart = window.echarts.init($echartBasicChart);
    const getDefaultOptions = () => ({
      color: getColor('primary'),
      tooltip: {
        trigger: 'item',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
      },
      xAxis: {
        type: 'category',
        data: getDates(
          new Date('5/1/2022'),
          new Date('5/7/2022'),
          1000 * 60 * 60 * 24
        ),
        show: true,
        boundaryGap: false,
        axisLine: {
          show: true,
          lineStyle: { color: getColor('gray-200') },
        },
        axisTick: {
          show: false,
        },
        axisLabel: {
          formatter: value => window.dayjs(value).format('DD MMM'),
          interval: 6,
          showMinLabel: true,
          showMaxLabel: true,
          color: getColor('gray-800'),
        },
      },
      yAxis: {
        show: false,
        type: 'value',
        boundaryGap: false,
      },
      series: [
        {
          type: 'bar',
          symbol: 'none',
        },
      ],
      grid: { left: 22, right: 22, top: 0, bottom: 20 },
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  });
};

export default basicEchartsInit;
