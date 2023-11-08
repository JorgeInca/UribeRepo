import { echartSetOption, tooltipFormatter } from './echarts-utils';

/* -------------------------------------------------------------------------- */
/*                             Echarts Total Sales                            */
/* -------------------------------------------------------------------------- */

const zeroBurnOutChartInit = () => {
  const { getColor, getData, getPastDates } = window.phoenix.utils;
  const $zeroBurnOutChartEl = document.querySelector(
    '.echart-zero-burnout-chart'
  );

  if ($zeroBurnOutChartEl) {
    const userOptions = getData($zeroBurnOutChartEl, 'echarts');
    const chart = window.echarts.init($zeroBurnOutChartEl);

    const getDefaultOptions = () => ({
      color: [
        getColor('gray-400'),
        getColor('success'),
        getColor('info'),
        getColor('warning'),
      ],
      tooltip: {
        trigger: 'axis',
        backgroundColor: getColor('gray-soft'),
        borderColor: getColor('gray-200'),
        formatter: params => tooltipFormatter(params, 'MMM DD, YYYY'),
        axisPointer: {
          shadowStyle: {
            color: 'red',
          },
        },
      },
      legend: {
        bottom: '10',
        data: [
          {
            name: 'Open',
            icon: 'roundRect',
          },
          {
            name: 'Issues found',
            icon: 'roundRect',
          },
          {
            name: 'In Progress',
            icon: 'roundRect',
          },
        ],
        itemWidth: 16,
        itemHeight: 8,
        itemGap: 10,
        inactiveColor: getColor('gray-500'),
        inactiveBorderWidth: 0,
        textStyle: {
          color: getColor('gray-900'),
          fontWeight: 600,
          fontSize: 16,
          fontFamily: 'Nunito Sans',
        },
      },

      // grid: {
      //   left: '0%',
      //   right: '4%',
      //   bottom: '15%',
      //   top: '10%',
      //   containLabel: true,
      //   show: true
      // },

      xAxis: [
        {
          show: true,
          interval: 2,
          axisLine: {
            lineStyle: {
              type: 'solid',
              color: getColor('gray-300'),
            },
          },
          axisLabel: {
            color: getColor('gray-900'),
            formatter: data => window.dayjs(data).format('D MMM'),
            interval: 5,
            align: 'left',
            margin: 20,
            fontSize: 12.8,
          },
          axisTick: {
            show: true,
            length: 15,
            // alignWithLabel: true
          },
          splitLine: {
            interval: 0,
            show: true,
            lineStyle: {
              color: getColor('gray-300'),
              type: 'dashed',
            },
          },
          type: 'category',
          boundaryGap: false,
          data: getPastDates(15),
        },
        {
          show: true,
          interval: 2,
          axisLine: {
            show: false,
          },
          axisLabel: {
            show: false,
          },
          axisTick: {
            show: false,
          },
          splitLine: {
            interval: 1,
            show: true,
            lineStyle: {
              color: getColor('gray-300'),
              type: 'solid',
            },
          },
          boundaryGap: false,
          data: getPastDates(15),
        },
      ],
      yAxis: {
        show: true,
        type: 'value',
        axisLine: {
          lineStyle: {
            type: 'solid',
            color: getColor('gray-300'),
          },
        },
        axisLabel: {
          color: getColor('gray-900'),
          margin: 20,
          fontSize: 12.8,
          interval: 0,
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-300'),
            type: 'solid',
          },
        },
        axisTick: {
          show: true,
          length: 15,
          alignWithLabel: true,
          lineStyle: {
            color: getColor('gray-300'),
          },
        },
        // data: ['0', '10', '20']
      },
      series: [
        {
          name: 'Estimated',
          type: 'line',
          symbol: 'none',
          data: [20, 17.5, 15, 15, 15, 12.5, 10, 7.5, 5, 2.5, 2.5, 2.5, 0],
          lineStyle: {
            width: 0,
          },
          areaStyle: {
            color: getColor('primary-300'),
            opacity: 0.075,
          },
          tooltip: {
            show: false,
          },
        },
        {
          name: 'Issues found',
          type: 'line',
          symbolSize: 6,
          data: [3, 1, 2, 4, 3, 1],
        },
        {
          name: 'Open',
          type: 'line',
          symbolSize: 6,
          data: [6, 5, 4, 6, 5, 5],
        },
        {
          name: 'In Progress',
          type: 'line',
          symbolSize: 6,
          data: [11, 12, 11, 9, 11, 6],
        },
        {
          name: 'Actual',
          type: 'line',
          symbolSize: 6,
          data: [20, 19, 15, 14, 12, 8],
          lineStyle: {
            type: 'dashed',
          },
        },
      ],
      grid: {
        right: 5,
        left: 0,
        bottom: '15%',
        top: 20,
        containLabel: true,
      },
    });

    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default zeroBurnOutChartInit;
