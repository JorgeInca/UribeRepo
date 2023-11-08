import { echartSetOption } from '../echarts-utils';

const doughnutChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-doughnut-chart-example');

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      legend: {
        left: 'left',
        textStyle: {
          color: getColor('gray-600')
        }
      },
      series: [
        {
          type: 'pie',
          radius: ['40%', '70%'],
          center: ['50%', '55%'],
          avoidLabelOverlap: false,
          label: {
            show: false,
            position: 'center'
          },
          labelLine: {
            show: false
          },
          data: [
            {
              value: 1200,
              name: 'Facebook',
              itemStyle: {
                color: getColor('primary')
              }
            },
            {
              value: 1000,
              name: 'Youtube',
              itemStyle: {
                color: getColor('danger')
              }
            },
            {
              value: 800,
              name: 'Twitter',
              itemStyle: {
                color: getColor('info')
              }
            },
            {
              value: 600,
              name: 'Linkedin',
              itemStyle: {
                color: getColor('success')
              }
            },
            {
              value: 400,
              name: 'Github',
              itemStyle: {
                color: getColor('warning')
              }
            }
          ]
        }
      ],
      tooltip: {
        trigger: 'item',
        padding: [7, 10],
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        }
      }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default doughnutChartInit;
