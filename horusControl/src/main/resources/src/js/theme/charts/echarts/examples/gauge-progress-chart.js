import { echartSetOption } from '../echarts-utils';

const gaugeProgressChartInit = () => {
  const { getColor, getData, rgbaColor } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-gauge-progress-chart-example'
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
          center: ['50%', '60%'],
          radius: '100%',
          startAngle: 180,
          endAngle: 0,
          progress: {
            show: true,
            width: 18,
            itemStyle: {
              color: getColor('info')
            }
          },
          itemStyle: {
            color: getColor('info'),
            shadowColor: rgbaColor(getColor('primary'), 0.5),
            shadowBlur: 10,
            shadowOffsetX: 2,
            shadowOffsetY: 2
          },
          axisLine: {
            lineStyle: {
              width: 18,
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
          anchor: {
            show: true,
            showAbove: true,
            size: 25,
            itemStyle: {
              color: getColor('info')
            }
          },
          title: {
            show: false
          },
          detail: {
            valueAnimation: true,
            fontSize: 80,
            offsetCenter: [0, '70%']
          },
          data: [
            {
              value: 70,
              detail: {
                fontSize: 30,
                color: getColor('gray-600'),
                offsetCenter: [0, '40%']
              }
            }
          ]
        }
      ]
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default gaugeProgressChartInit;
