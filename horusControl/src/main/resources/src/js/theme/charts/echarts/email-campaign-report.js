import { echartSetOption } from './echarts-utils';

/* -------------------------------------------------------------------------- */
/*                             Echarts Total Sales                            */
/* -------------------------------------------------------------------------- */

const emailCampaignReportsChartInit = () => {
  const { getColor, getData, toggleColor } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-email-campaign-report');

  const tooltipFormatter = (params, dateFormatter = 'MMM DD') => {
    const el = params[1];

    const tooltipItem = `<div class='ms-1'>
          <h6 class="text-700"><span class="fas fa-circle me-1 fs--2" style="color:${
            el.borderColor ? el.borderColor : el.color
          }"></span>
            ${el.axisValue} : ${
      typeof el.value === 'object' ? el.value[1] : el.value
    }
          </h6>
        </div>`;

    return `<div>
              <p class='mb-2 text-600'>
                ${el.seriesName}
              </p>
              ${tooltipItem}
            </div>`;
  };

  const data1 = [0, 1466, 966, 0];
  const data2 = [
    {
      value: 2832,
      itemStyle: {
        color: getColor('primary-300')
      }
    },
    1366,
    500,
    966
  ];

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = echarts.init($chartEl);

    const getDefaultOptions = () => ({
      color: [getColor('primary'), getColor('gray-300')],
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
      xAxis: {
        type: 'category',
        data: ['Total Emails', 'Sent', 'Bounce', 'Delivered'],
        splitLine: { show: false },
        axisLabel: {
          color: getColor('gray-900'),
          fontFamily: 'Nunito Sans',
          fontWeight: 400,
          fontSize: 12.8,
          margin: 24,
          rotate: 30
          // formatter: value => `${value.slice(0, 5)}...`,
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: getColor('gray-300')
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
          margin: 24
          // formatter: value => value / 1000,
        },
        interval: 500
      },
      series: [
        {
          name: 'Placeholder',
          type: 'bar',
          barWidth: '64px',
          stack: 'Total',
          backgroundColor: getColor('white'),
          label: {
            show: false
          },
          itemStyle: {
            borderColor: 'transparent',
            color: 'transparent'
          },
          emphasis: {
            itemStyle: {
              borderColor: 'transparent',
              color: 'transparent'
            }
          },
          data: data1
        },
        {
          name: 'Email Campaign',
          type: 'bar',
          stack: 'Total',
          itemStyle: {
            color: getColor('primary-200')
          },
          data: data2,
          label: {
            show: true,
            position: 'inside',
            color: toggleColor(getColor('gray-1100'), getColor('gray-200')),
            fontWeight: 'normal',
            fontSize: '12.8px',
            formatter: value => `${value.value.toLocaleString()}`
          }
        }
      ],
      grid: {
        right: '0',
        left: 6,
        bottom: 10,
        top: '5%',
        containLabel: true
      },
      animation: false
    });

    const responsiveOptions = {
      xs: {
        series: [
          {
            barWidth: '48px'
          }
        ],
        xAxis: {
          axisLabel: {
            show: true,
            formatter: value => `${value.slice(0, 5)}...`
          }
        }
      },
      sm: {
        series: [
          {
            barWidth: '64px'
          }
        ],
        xAxis: {
          axisLabel: {
            show: true,
            formatter: value => `${value.slice(0, 11)}`,
            rotate: 0
          }
        }
      },
      md: {
        series: [
          {
            barWidth: '56px'
          }
        ],
        xAxis: {
          axisLabel: {
            show: false
          }
        }
      },
      lg: {
        series: [
          {
            barWidth: '64px'
          }
        ],
        xAxis: {
          axisLabel: {
            show: true,
            formatter: value => `${value.slice(0, 11)}`
          }
        }
      }
    };

    echartSetOption(chart, userOptions, getDefaultOptions, responsiveOptions);
  }
};

export default emailCampaignReportsChartInit;
