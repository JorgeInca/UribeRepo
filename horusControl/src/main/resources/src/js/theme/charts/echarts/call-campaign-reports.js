import { echartSetOption } from './echarts-utils';

/* -------------------------------------------------------------------------- */
/*                             Echarts Total Sales                            */
/* -------------------------------------------------------------------------- */

const callCampaignChartInit = () => {
  const { getColor, getData, getPastDates, rgbaColor } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-call-campaign');

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

  const data1 = [8000, 7700, 5900, 10100, 5100, 6000, 4300];

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
      xAxis: [
        {
          type: 'category',
          data: dates,
          boundaryGap: false,
          splitLine: {
            show: true,
            lineStyle: {
              color: getColor('gray-200')
            }
          },
          axisLabel: {
            color: getColor('gray-900'),
            // interval: 1,
            showMaxLabel: false,
            showMinLabel: true,
            align: 'left',
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
        {
          type: 'category',
          data: dates,
          boundaryGap: false,
          splitLine: {
            show: true,
            lineStyle: {
              color: getColor('gray-200')
            }
          },
          axisLabel: {
            color: getColor('gray-900'),
            interval: 130,
            showMaxLabel: true,
            showMinLabel: false,
            align: 'right',
            formatter: value => window.dayjs(value).format('ddd'),
            fontFamily: 'Nunito Sans',
            fontWeight: 400,
            fontSize: 12.8,
            margin: 16
          },
          position: 'bottom',
          axisLine: {
            lineStyle: {
              color: getColor('gray-200')
            }
          },
          axisTick: false
        }
      ],
      yAxis: {
        type: 'value',
        axisLine: {
          lineStyle: {
            color: getColor('gray-200')
          }
        },
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
          margin: 16,
          formatter: value => `${value / 1000}k`
        }
        // interval: 150,
      },
      series: [
        {
          name: 'Campaign',
          type: 'line',
          smooth: 0.4,
          symbolSize: 11,
          itemStyle: {
            color: getColor('primary')
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
                  color: rgbaColor(getColor('primary-300'), 0.2)
                },
                {
                  offset: 1,
                  color: rgbaColor(getColor('primary-300'), 0.2)
                }
              ]
            }
          },
          data: data1
        }
      ],
      grid: {
        right: '8',
        left: 6,
        bottom: '-10',
        top: 10,
        containLabel: true
      },
      animation: false
    });

    const responsiveOptions = {
      xs: {
        xAxis: [
          {},
          {
            axisLabel: {
              showMaxLabel: false
            }
          }
        ]
      },
      sm: {
        xAxis: [
          {},
          {
            axisLabel: {
              showMaxLabel: true
            }
          }
        ]
      }
    };

    echartSetOption(chart, userOptions, getDefaultOptions, responsiveOptions);
  }
};

export default callCampaignChartInit;
