import { echartSetOption } from '../echarts-utils';

const pieChartInit = () => {
  const { getColor, getData, rgbaColor } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-pie-chart-example');

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
          radius: window.innerWidth < 530 ? '45%' : '60%',
          label: {
            color: getColor('gray-700')
          },
          center: ['50%', '55%'],
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
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: rgbaColor(getColor('gray-600'), 0.5)
            }
          }
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

    const responsiveOptions = {
      xs: {
        series: [
          {
            radius: '45%'
          }
        ]
      },
      sm: {
        series: [
          {
            radius: '60%'
          }
        ]
      }
    };

    echartSetOption(chart, userOptions, getDefaultOptions, responsiveOptions);
  }
};

export default pieChartInit;
