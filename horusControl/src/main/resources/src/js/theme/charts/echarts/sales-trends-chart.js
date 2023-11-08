import { echartSetOption } from './echarts-utils';

/* -------------------------------------------------------------------------- */
/*                             Echarts Total Sales                            */
/* -------------------------------------------------------------------------- */

const salesTrendsChartInit = () => {
  const { getColor, getData, getPastDates, rgbaColor, toggleColor } =
    window.phoenix.utils;
  // const phoenixTheme = window.config.config;
  const $chartEl = document.querySelector('.echart-sales-trends');

  const tooltipFormatter = (params, dateFormatter = 'MMM DD') => {
    let tooltipItem = ``;
    params.forEach(el => {
      tooltipItem += `<div class='ms-1'>
          <h6 class="text-700"><span class="fas fa-circle me-1 fs--2" style="color:${
            el.color
          }"></span>
            ${el.seriesName} : ${
        typeof el.value === 'object' ? el.value[1] : el.value
      }
          </h6>
        </div>`;
    });
    return `<div>
              <p class='mb-2 text-600'>
                ${
                  window.dayjs(params[0].axisValue).isValid()
                    ? window.dayjs(params[0].axisValue).format('DD MMM, YYYY')
                    : params[0].axisValue
                }
              </p>
              ${tooltipItem}
            </div>`;
  };

  const dates = getPastDates(7);

  const data1 = [2000, 5700, 3700, 5500, 8000, 4000, 5500];
  const data2 = [10500, 9000, 7000, 9000, 10400, 7500, 9300];

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);

    const getDefaultOptions = () => ({
      color: [getColor('primary-200'), getColor('info-300')],
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
        formatter: tooltipFormatter
      },
      // legend: {
      //   left: '76%',
      //   top: 'auto',
      //   icon: 'circle',
      // },
      xAxis: {
        type: 'category',
        data: dates,
        axisLabel: {
          color: getColor('gray-900'),
          formatter: value => window.dayjs(value).format('ddd'),
          fontFamily: 'Nunito Sans',
          fontWeight: 400,
          fontSize: 12.8,
          margin: 16
        },
        axisLine: {
          lineStyle: {
            color: getColor('gray-200')
          }
        },
        axisTick: false
      },
      yAxis: {
        type: 'value',
        splitLine: {
          lineStyle: {
            color: getColor('gray-200')
          }
        },
        axisLabel: {
          color: getColor('gray-900'),
          fontFamily: 'Nunito Sans',
          fontWeight: 700,
          fontSize: 12.8,
          margin: 24,
          formatter: value => `${value / 1000}k`
        }
        // interval: 1000,
      },
      series: [
        {
          name: 'Revenue',
          type: 'bar',
          barWidth: '16px',
          label: {
            show: false
          },
          itemStyle: {
            color: toggleColor(getColor('primary-200'), getColor('primary')),

            borderRadius: [4, 4, 0, 0]
          },
          data: data2
        },
        {
          name: 'Profit',
          type: 'line',
          symbol: 'circle',
          symbolSize: 11,
          itemStyle: {
            color: getColor('info-300'),
            borderColor: toggleColor(getColor('white'), getColor('dark')),
            borderWidth: 2
          },
          areaStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                {
                  offset: 0,
                  color: rgbaColor(getColor('info-300'), 0.2)
                },
                {
                  offset: 1,
                  color: rgbaColor(getColor('info-300'), 0.2)
                }
              ]
            }
          },
          data: data1
        }
      ],
      grid: {
        right: '0',
        left: '0',
        bottom: 0,
        top: 10,
        containLabel: true
      },
      animation: false
    });

    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default salesTrendsChartInit;
