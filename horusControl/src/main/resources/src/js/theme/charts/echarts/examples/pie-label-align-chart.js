import { echartSetOption } from '../echarts-utils';

const pieLabelAlignChartInit = () => {
  const { getColor, getData, rgbaColor } = window.phoenix.utils;
  const $chartEl = document.querySelector(
    '.echart-pie-label-align-chart-example'
  );

  const data = [
    {
      value: 850,
      name: 'Starter',
      itemStyle: {
        color: rgbaColor(getColor('primary'), 0.5)
      }
    },
    {
      value: 750,
      name: 'Starter Pro',
      itemStyle: {
        color: getColor('danger')
      }
    },
    {
      value: 457,
      name: 'Basic',
      itemStyle: {
        color: getColor('primary')
      }
    },
    {
      value: 654,
      name: 'Optimal',
      itemStyle: {
        color: getColor('secondary')
      }
    },
    {
      value: 447,
      name: 'Business',
      itemStyle: {
        color: getColor('warning')
      }
    },
    {
      value: 682,
      name: 'Classic addition',
      itemStyle: {
        color: rgbaColor(getColor('warning'), 0.8)
      }
    },
    {
      value: 471,
      name: 'Premium',
      itemStyle: {
        color: getColor('success')
      }
    },
    {
      value: 524,
      name: 'Platinum',
      itemStyle: {
        color: getColor('info')
      }
    },
    {
      value: 200,
      name: 'Platinum Pro',
      itemStyle: {
        color: rgbaColor(getColor('primary'), 0.5)
      }
    }
  ];

  if ($chartEl) {
    const userOptions = getData($chartEl, 'echarts');
    const chart = window.echarts.init($chartEl);
    const getDefaultOptions = () => ({
      title: [
        {
          text: 'Pie Label Align Chart',
          left: 'center',
          textStyle: {
            color: getColor('gray-600')
          }
        },
        {
          subtext: 'alignTo: "labelLine"',
          left: '50%',
          top: '85%',
          textAlign: 'center',
          subtextStyle: {
            color: getColor('gray-700')
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
        },
        position(pos, ...size) {
          if (window.innerWidth <= 540) {
            const tooltipHeight = size[1].offsetHeight;
            const obj = { top: pos[1] - tooltipHeight - 20 };
            obj[pos[0] < size[3].viewSize[0] / 2 ? 'left' : 'right'] = 5;
            return obj;
          }
          return null;
        }
      },

      series: [
        {
          type: 'pie',
          radius: window.innerWidth < 530 ? '45%' : '60%',
          center: ['50%', '50%'],
          data,
          label: {
            position: 'outer',
            alignTo: 'labelLine',
            bleedMargin: 5,
            color: getColor('gray-700')
          },
          left: '5%',
          right: '5%',
          top: 0,
          bottom: 0
        }
      ]
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

export default pieLabelAlignChartInit;
