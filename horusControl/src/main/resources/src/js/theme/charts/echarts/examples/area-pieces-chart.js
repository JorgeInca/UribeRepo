import { echartSetOption, tooltipFormatter } from '../echarts-utils';

const areaPiecesChartInit = () => {
  const { getColor, getData, rgbaColor } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-area-pieces-chart-example');

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'axis',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        },
        formatter: params => tooltipFormatter(params)
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        axisLine: {
          lineStyle: {
            color: getColor('gray-300'),
            type: 'solid'
          }
        },
        axisTick: { show: false },
        axisLabel: {
          color: getColor('gray-400'),
          margin: 15,
          formatter: value => window.dayjs(value).format('MMM DD')
        },
        splitLine: {
          show: false
        }
      },
      yAxis: {
        type: 'value',
        splitLine: {
          lineStyle: {
            color: getColor('gray-200')
          }
        },
        boundaryGap: false,
        axisLabel: {
          show: true,
          color: getColor('gray-400'),
          margin: 15
        },
        axisTick: { show: false },
        axisLine: { show: false }
      },
      visualMap: {
        type: 'piecewise',
        show: false,
        dimension: 0,
        seriesIndex: 0,
        pieces: [
          {
            gt: 1,
            lt: 3,
            color: rgbaColor(getColor('primary'), 0.4)
          },
          {
            gt: 5,
            lt: 7,
            color: rgbaColor(getColor('primary'), 0.4)
          }
        ]
      },
      series: [
        {
          type: 'line',
          name: 'Total',
          smooth: 0.6,
          symbol: 'none',
          lineStyle: {
            color: getColor('primary'),
            width: 5
          },
          markLine: {
            symbol: ['none', 'none'],
            label: { show: false },
            data: [{ xAxis: 1 }, { xAxis: 3 }, { xAxis: 5 }, { xAxis: 7 }]
          },
          areaStyle: {},
          data: [
            ['2019-10-10', 100],
            ['2019-10-11', 300],
            ['2019-10-12', 450],
            ['2019-10-13', 300],
            ['2019-10-14', 250],
            ['2019-10-15', 750],
            ['2019-10-16', 650],
            ['2019-10-17', 550],
            ['2019-10-18', 200]
          ]
        }
      ],
      grid: { right: 20, left: 5, bottom: 5, top: 8, containLabel: true }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default areaPiecesChartInit;
