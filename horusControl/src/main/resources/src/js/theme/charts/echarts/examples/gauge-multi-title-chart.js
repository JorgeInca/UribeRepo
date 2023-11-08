import { echartSetOption } from '../echarts-utils';

const gaugeMultiTitleChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-gauge-multi-title-chart-example'
  );

  const tooltipFormatter = params => {
    return `
    <div>
        <h6 class="fs--1 text-700 mb-0">
          <span class="fas fa-circle me-1" style='color:${params[0].color}'></span>
          ${params[0].name} : ${params[0].value}
        </h6>
    </div>
    `;
  };

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
        formatter: params => tooltipFormatter(params),
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        }
      },
      series: [
        {
          type: 'gauge',
          radius: '100%',
          anchor: {
            show: true,
            showAbove: true,
            size: 18,
            itemStyle: {
              color: getColor('warning')
            }
          },

          progress: {
            show: true,
            overlap: true,
            roundCap: true
          },
          axisLine: {
            roundCap: true,
            lineStyle: {
              color: [[1, getColor('gray-200')]]
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            lineStyle: {
              width: 2,
              color: getColor('gray-600')
            }
          },
          axisLabel: {
            distance: 25,
            color: getColor('gray-600')
          },
          data: [
            {
              value: 20,
              name: 'Perfect',
              title: {
                offsetCenter: ['-40%', '80%']
              },
              detail: {
                offsetCenter: ['-40%', '95%']
              },
              itemStyle: {
                color: getColor('primary')
              }
            },
            {
              value: 40,
              name: 'Good',
              title: {
                offsetCenter: ['0%', '80%']
              },
              detail: {
                offsetCenter: ['0%', '95%']
              },

              itemStyle: {
                color: getColor('success')
              }
            },
            {
              value: 60,
              name: 'Commonly',
              title: {
                offsetCenter: ['40%', '80%']
              },
              detail: {
                offsetCenter: ['40%', '95%']
              },

              itemStyle: {
                color: getColor('warning')
              }
            }
          ],
          title: {
            fontSize: 14,
            color: getColor('gray-600')
          },
          detail: {
            width: 40,
            height: 14,
            fontSize: 14,
            color: '#fff',
            backgroundColor: 'auto',
            borderRadius: 3,
            formatter: '{value}%'
          }
        }
      ]
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default gaugeMultiTitleChartInit;
