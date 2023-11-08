import { echartSetOption } from './echarts-utils';

// dayjs.extend(advancedFormat);

/* -------------------------------------------------------------------------- */
/*                             Echarts Total Sales                            */
/* -------------------------------------------------------------------------- */

const socialMarketingRadarChartInit = () => {
  const { getColor, getData, rgbaColor, toggleColor } = window.phoenix.utils;
  const $leadConversionChartEl = document.querySelector(
    '.echart-social-marketing-radar'
  );
  if ($leadConversionChartEl) {
    const userOptions = getData($leadConversionChartEl, 'echarts');
    const chart = echarts.init($leadConversionChartEl);

    const getDefaultOptions = () => ({
      color: [getColor('primary-300'), getColor('warning-300')],
      tooltip: {
        trigger: 'item',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: {
          color: getColor('gray-900'),
          fontSize: 12.8,
          fontFamily: 'Nunito Sans',
        },
        borderWidth: 1,
        transitionDuration: 0,
        // formatter: getformatter
      },
      radar: {
        splitNumber: 5,
        axisNameGap: 10,
        radius: '87%',
        // center: ['45%', '50%'],
        splitLine: {
          lineStyle: {
            color: getColor('gray-200'),
          },
        },
        splitArea: {
          show: true,
          areaStyle: {
            shadowBlur: 0.5,
            color: [
              toggleColor(getColor('gray-100'), getColor('gray-100')),
              toggleColor(getColor('gray-soft'), getColor('gray-200')),
            ],
          },
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-200'),
          },
        },
        name: {
          textStyle: {
            color: getColor('gray-700'),
            fontWeight: 800,
            fontSize: 10.2,
          },
        },
        indicator: [
          { name: 'SAT', max: 5000 },
          { name: 'FRI', max: 5000 },
          { name: 'THU', max: 5000 },
          { name: 'WED', max: 5000 },
          { name: 'TUE', max: 5000 },
          { name: 'MON', max: 5000 },
          { name: 'SUN', max: 5000 },
        ],
      },
      series: [
        {
          name: 'Budget vs spending',
          type: 'radar',
          symbol: 'emptyCircle',
          symbolSize: 6,

          data: [
            {
              value: [2100, 2300, 1600, 3700, 3000, 2500, 2500],
              name: 'Offline Marketing',
              itemStyle: {
                color: getColor('primary-300'),
              },
              areaStyle: {
                color: rgbaColor(getColor('primary-300'), 0.3),
              },
            },
            {
              value: [3000, 1600, 3700, 500, 3700, 3000, 3200],
              name: 'Online Marketing',
              areaStyle: {
                color: rgbaColor(getColor('warning-300'), 0.3),
              },
              itemStyle: {
                color: getColor('warning-300'),
              },
            },
          ],
        },
      ],
      grid: {
        top: 10,
        left: 0,
      },
    });

    const responsiveOptions = {
      md: {
        radar: {
          radius: '74%',
        },
      },
      xl: {
        radar: {
          radius: '85%',
        },
      },
    };

    echartSetOption(chart, userOptions, getDefaultOptions, responsiveOptions);
  }
};

export default socialMarketingRadarChartInit;
