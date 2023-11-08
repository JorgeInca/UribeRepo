import { echartSetOption } from '../echarts-utils';

const gaugeRingChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-gauge-ring-chart-example');

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
          startAngle: 90,
          endAngle: -270,
          pointer: {
            show: false
          },
          progress: {
            show: true,
            overlap: false,
            roundCap: true,
            clip: false,
            itemStyle: {
              borderWidth: 1,
              borderColor: getColor('gray-500')
            }
          },
          axisLine: {
            lineStyle: {
              width: 18,
              color: [[1, getColor('gray-200')]]
            }
          },
          splitLine: {
            show: false,
            distance: 0,
            length: 10
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            show: false,
            distance: 50
          },
          data: [
            {
              value: 80,
              title: {
                offsetCenter: ['0%', '0%']
              },
              detail: {
                offsetCenter: ['0%', '0%']
              },
              itemStyle: {
                color: getColor('primary')
              }
            }
          ],
          title: {
            fontSize: 14
          },
          detail: {
            width: 50,
            height: 14,
            fontSize: 20,
            color: 'auto',
            formatter: '{value}%'
          }
        }
      ]
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default gaugeRingChartInit;
