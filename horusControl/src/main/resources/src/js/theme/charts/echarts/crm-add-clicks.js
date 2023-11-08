import { echartSetOption } from './echarts-utils';

/* -------------------------------------------------------------------------- */
/*                             Echarts Total Sales                            */
/* -------------------------------------------------------------------------- */

const addClicksChartInit = () => {
  const { getColor, getData, getPastDates, getItemFromStore } =
    window.phoenix.utils;
  const $addClicksChart = document.querySelector('.echart-add-clicks-chart');

  // getItemFromStore('phoenixTheme')
  const dates = getPastDates(11);
  const currentMonthData = [
    2000, 2250, 1070, 1200, 1000, 1450, 3100, 2900, 1800, 1450, 1700
  ];

  const prevMonthData = [
    1100, 1200, 2700, 1700, 2100, 2000, 2300, 1200, 2600, 2900, 1900
  ];

  const tooltipFormatter = params => {
    const currentDate = window.dayjs(params[0].axisValue);
    const prevDate = window.dayjs(params[0].axisValue).subtract(1, 'month');

    const result = params.map((param, index) => ({
      value: param.value,
      date: index > 0 ? prevDate : currentDate,
      color: param.color
    }));

    let tooltipItem = ``;
    result.forEach((el, index) => {
      tooltipItem += `<h6 class="fs--1 text-700 ${
        index > 0 && 'mb-0'
      }"><span class="fas fa-circle me-2" style="color:${el.color}"></span>
      ${el.date.format('MMM DD')} : ${el.value}
    </h6>`;
    });
    return `<div class='ms-1'>
              ${tooltipItem}
            </div>`;
  };

  if ($addClicksChart) {
    const userOptions = getData($addClicksChart, 'echarts');
    const chart = window.echarts.init($addClicksChart);

    const getDefaultOptions = () => ({
      // color: [getColor('primary'), getColor('info')],
      tooltip: {
        trigger: 'axis',
        padding: 10,
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        },
        formatter: tooltipFormatter
      },
      xAxis: [
        {
          type: 'category',
          data: dates,
          axisLabel: {
            formatter: value => window.dayjs(value).format('DD MMM, YY'),
            interval: 3,
            showMinLabel: true,
            showMaxLabel: false,
            color: getColor('gray-800'),
            align: 'left',
            fontFamily: 'Nunito Sans',
            fontWeight: 700,
            fontSize: 12.8,
            margin: 15
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: getColor('gray-300')
            }
          },
          axisTick: {
            show: true,
            interval: 5
          },
          boundaryGap: false
        },
        {
          type: 'category',
          position: 'bottom',
          data: dates,
          axisLabel: {
            formatter: value => window.dayjs(value).format('DD MMM, YY'),
            interval: 130,
            showMaxLabel: true,
            showMinLabel: false,
            color: getColor('gray-900'),
            align: 'right',
            fontFamily: 'Nunito Sans',
            fontWeight: 700,
            fontSize: 12.8,
            margin: 15
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: getColor('gray-300')
            }
          },
          axisTick: {
            show: true
          },
          splitLine: {
            show: false
          },
          boundaryGap: false
        }
      ],
      yAxis: {
        axisPointer: { type: 'none' },
        axisTick: 'none',
        splitLine: {
          show: true,
          lineStyle: {
            color:
              getItemFromStore('phoenixTheme') === 'dark'
                ? getColor('gray-100')
                : getColor('gray-200')
          }
        },
        axisLine: { show: false },
        axisLabel: {
          show: true,
          fontFamily: 'Nunito Sans',
          fontWeight: 700,
          fontSize: 12.8,
          color: getColor('gray-900'),
          margin: 25,
          // verticalAlign: 'bottom',
          formatter: value => `${value / 1000}k`
        }
        // axisLabel: { show: true }
      },
      series: [
        {
          name: 'e',
          type: 'line',
          data: prevMonthData,
          // symbol: 'none',
          lineStyle: {
            type: 'line',
            width: 3,
            color: getColor('info-200')
          },
          showSymbol: false,
          symbol: 'emptyCircle',
          symbolSize: 6,
          itemStyle: {
            color: getColor('info-200'),
            borderWidth: 3
          }
        },
        {
          name: 'd',
          type: 'line',
          data: currentMonthData,
          showSymbol: false,
          symbol: 'emptyCircle',
          symbolSize: 6,
          itemStyle: {
            color: getColor('primary'),
            borderWidth: 3
          },

          lineStyle: {
            type: 'line',
            width: 3,
            color: getColor('primary')
          }
        }
      ],
      grid: {
        right: 2,
        left: 5,
        bottom: '10px',
        top: '2%',
        containLabel: true
      },
      animation: false
    });

    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default addClicksChartInit;
