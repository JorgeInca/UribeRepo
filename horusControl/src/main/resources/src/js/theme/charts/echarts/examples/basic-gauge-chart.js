import { echartSetOption } from '../echarts-utils';

const basicGaugeChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-basic-gauge-chart-example');

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
      radius: '100%',
      series: [
        {
          name: 'Pressure',
          type: 'gauge',
          splitLine: {
            lineStyle: {
              color: getColor('gray-600')
            }
          },
          axisLabel: {
            color: getColor('gray-600')
          },
          detail: {
            formatter: '{value}'
          },
          title: {
            color: getColor('gray-600')
          },
          data: [
            {
              value: 50,
              name: 'SCORE',
              detail: {
                color: getColor('gray-600')
              }
            }
          ]
        }
      ]
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default basicGaugeChartInit;
