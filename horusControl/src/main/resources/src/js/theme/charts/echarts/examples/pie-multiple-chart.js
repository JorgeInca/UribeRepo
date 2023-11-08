import { echartSetOption } from '../echarts-utils';

const pieMultipleChartInit = () => {
  const { getColor, getData } = window.phoenix.utils;
  const $chartEl = document.querySelector('.echart-pie-multiple-chart-example');
  const data1 = [
    {
      value: 1048,
      name: 'Starter',
      itemStyle: {
        color: getColor('danger')
      }
    },
    {
      value: 735,
      name: 'Basic',
      itemStyle: {
        color: getColor('primary')
      }
    },
    {
      value: 580,
      name: 'Optimal',
      itemStyle: {
        color: getColor('secondary')
      }
    },
    {
      value: 484,
      name: 'Business',
      itemStyle: {
        color: getColor('warning')
      }
    },
    {
      value: 300,
      name: 'Premium',
      itemStyle: {
        color: getColor('success')
      }
    },
    {
      value: 300,
      name: 'Platinum',
      itemStyle: {
        color: getColor('info')
      }
    }
  ];

  const data2 = [
    {
      value: 1048,
      name: 'Facebook',
      itemStyle: {
        color: getColor('primary')
      }
    },
    {
      value: 735,
      name: 'Youtube',
      itemStyle: {
        color: getColor('danger')
      }
    },
    {
      value: 580,
      name: 'Twitter',
      itemStyle: {
        color: getColor('info')
      }
    },
    {
      value: 484,
      name: 'Linkedin',
      itemStyle: {
        color: getColor('success')
      }
    },
    {
      value: 300,
      name: 'Github',
      itemStyle: {
        color: getColor('warning')
      }
    }
  ];

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      title: [
        {
          text: 'Pie Multiple Chart',
          left: 'center',
          textStyle: {
            color: getColor('gray-600')
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
      },

      series: [
        {
          type: 'pie',
          radius: window.innerWidth < 450 ? '48%' : '55%',
          center: ['25%', '50%'],
          data: data1,
          label: {
            show: false
          }
        },
        {
          type: 'pie',
          radius: window.innerWidth < 450 ? '48%' : '55%',
          center: ['75%', '50%'],
          avoidLabelOverlap: false,
          label: {
            show: false
          },
          data: data2
        }
      ]
    });

    const responsiveOptions = {
      xs: {
        series: [{ radius: '48%' }, { radius: '48%' }]
      },
      sm: {
        series: [{ radius: '55%' }, { radius: '55%' }]
      }
    };

    echartSetOption(chart, userOptions, getDefaultOptions, responsiveOptions);
  }
};

export default pieMultipleChartInit;
